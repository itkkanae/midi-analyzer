package io.github.itkkanae.midi.events;

import io.github.itkkanae.midi.enums.EventType;
import io.github.itkkanae.midi.enums.MetaType;
import io.github.itkkanae.midi.maps.CtrlMap;
import io.github.itkkanae.midi.maps.NoteMap;
import io.github.itkkanae.midi.maps.PgmMap;
import io.github.itkkanae.midi.maps.ToneMap;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"unused"})
public class Event {

    private int len;
    private int deltaTime;
    private int absTime;
    private boolean isEoT;
    private boolean isTempo;
    private EventType eventType;
    private Map<String, Object> info;

    {
        info = new HashMap<>();
    }

    public static int addEvent(InputStream is, int offset, List<Event> events) throws IOException {
        Event event = new Event();
        int count = 0;
        count += event.setDeltaTime(is, offset);
        count += event.analyzeEvent(is);
        events.add(event);
        return count;
    }

    private int setDeltaTime(InputStream is, int offset) throws IOException {
        int count = 0;
        deltaTime = 0;
        int val;
        do {
            count++;
            val = is.read();
            deltaTime = (deltaTime << 7) + (val & 0x7F);
        } while (val >= 0x80);
        absTime = offset;
        return count;
    }

    private int analyzeEvent(InputStream is) throws IOException {
        int count = 1;
        int type = is.read();
        if (0xF0 > type) {
            switch (0xF0 & type) {
                case 0x80:
                    eventType = EventType.KEY_RELEASE;
                    count += keyEvent(is);
                    break;
                case 0x90:
                    eventType = EventType.KEY_PRESS;
                    count += keyEvent(is);
                    break;
                case 0xA0:
                    eventType = EventType.KEY_AFTER_TOUCH;
                    count += keyEvent(is);
                    break;
                case 0xB0:
                    eventType = EventType.CONTROL_CHANGE;
                    count += ctrlChange(is);
                    break;
                case 0xC0:
                    eventType = EventType.PROGRAM_CHANGE;
                    count += pgmChange(is);
                    break;
                case 0xD0:
                    eventType = EventType.CHANNEL_AFTER_TOUCH;
                    count += channelPressure(is);
                    break;
                case 0xE0:
                    eventType = EventType.PITCH_WHEEL_CHANGE;
                    count += pwChange(is);
                    break;
            }
        }
        switch (type) {
            case 0xFF:
                eventType = EventType.META;
                count += metaEvent(is);
                break;
            case 0xF0:
                eventType = EventType.SYSTEM_EXCLUSIVE;
                count += sysMessage(is);
                break;
            case 0xF2:
                eventType = EventType.SONG_POSITION_POINTER;
                count += position(is);
                break;
            case 0xF3:
                eventType = EventType.SONG_SELECT;
                count += songSelect(is);
                break;
            case 0xF6:
                eventType = EventType.TUNE_REQUEST;
                break;
            case 0xF7:
                eventType = EventType.END_OF_EXCLUSIVE;
                break;
            case 0xF8:
                eventType = EventType.TIMING_CLOCK;
                break;
            case 0xFA:
                eventType = EventType.START;
                break;
            case 0xFB:
                eventType = EventType.CONTINUE;
                break;
            case 0xFC:
                eventType = EventType.STOP;
                break;
            case 0xFE:
                eventType = EventType.ACTIVE_SENSING;
                break;
        }
        return count;
    }

    private int keyEvent(InputStream is) throws IOException {
        int note = is.read();
        info.put("note", note);
        info.put("note-name", NoteMap.getName(note));
        info.put("velocity", is.read());
        return 2;
    }

    private int ctrlChange(InputStream is) throws IOException {
        int ctrl = is.read();
        info.put("ctrl", ctrl);
        info.put("ctrl-name", CtrlMap.getName(ctrl));
        info.put("value", is.read());
        return 2;
    }

    private int pgmChange(InputStream is) throws IOException {
        int pgm = is.read();
        info.put("program", pgm);
        info.put("pgm-name", PgmMap.getName(pgm));
        return 1;
    }

    private int channelPressure(InputStream is) throws IOException {
        info.put("pressure", is.read());
        return 1;
    }

    private int pwChange(InputStream is) throws IOException {
        Integer val = is.read() + (is.read() << 7);
        info.put("value", val);
        return 2;
    }

    private int sysMessage(InputStream is) throws IOException {
        int count = 1;
        info.put("id-code", is.read());
        int val;
        StringBuilder sb = new StringBuilder();
        do {
            count++;
            val = is.read();
            sb.append((char) val);
        } while (0xF7 != val);
        sb.deleteCharAt(count - 2);
        info.put("message", sb.toString());
        return count;
    }

    private int position(InputStream is) throws IOException {
        Integer val = is.read() + (is.read() << 7);
        info.put("position", val);
        return 2;
    }

    private int songSelect(InputStream is) throws IOException {
        info.put("song", is.read());
        return 1;
    }

    private int metaEvent(InputStream is) throws IOException {
        int count = 1;
        int meta = is.read();
        switch (meta) {
            case 0x00:
                setMetaType(MetaType.SEQUENCE_NUM);
                count += sequenceNum(is);
                break;
            case 0x01:
                setMetaType(MetaType.TEXT);
                count += textEvent(is);
                break;
            case 0x02:
                setMetaType(MetaType.COPYRIGHT);
                count += textEvent(is);
                break;
            case 0x03:
                setMetaType(MetaType.TRACK_NAME);
                count += textEvent(is);
                break;
            case 0x04:
                setMetaType(MetaType.INSTRUMENT_NAME);
                count += textEvent(is);
                break;
            case 0x05:
                setMetaType(MetaType.LYRIC);
                count += textEvent(is);
                break;
            case 0x06:
                setMetaType(MetaType.MARKER);
                count += textEvent(is);
                break;
            case 0x07:
                setMetaType(MetaType.CUE_POINT);
                count += textEvent(is);
                break;
            case 0x20:
                setMetaType(MetaType.CHANNEL_PREFIX);
                count += channelPrefix(is);
                break;
            case 0x2F:
                setMetaType(MetaType.END_OF_TRACK);
                isEoT = true;
                count += endOfTrack(is);
                break;
            case 0x51:
                setMetaType(MetaType.TEMPO);
                isTempo = true;
                count += tempo(is);
                break;
            case 0x54:
                setMetaType(MetaType.SMPTE_OFFSET);
                count += smpteOffset(is);
                break;
            case 0x58:
                setMetaType(MetaType.TIME_SIGNATURE);
                count += timeSignature(is);
                break;
            case 0x59:
                setMetaType(MetaType.KEY_SIGNATURE);
                count += keySignature(is);
                break;
            case 0x7F:
                setMetaType(MetaType.SEQUENCER_INFO);
                count += textEvent(is);
                break;
        }
        return count;
    }

    private void setMetaType(MetaType type) {
        info.put("type", type);
    }

    private int sequenceNum(InputStream is) throws IOException {
        byte[] bytes = getByteInfo(is);
        if (bytes != null && 2 == bytes.length) {
            info.put("sequence-number", ((bytes[0] & 0xFF) << 8) + (bytes[1] & 0xFF));
        }
        return 3;
    }

    private int textEvent(InputStream is) throws IOException {
        byte[] bytes = getByteInfo(is);
        if (bytes == null) return 0;
        info.put("text", new String(bytes, "GBK"));
        return bytes.length + 1;
    }

    private int channelPrefix(InputStream is) throws IOException {
        info.put("channel-num", getByteInfo(is));
        return 2;
    }

    private int endOfTrack(InputStream is) throws IOException {
        return 1 + getLength(is);
    }

    private int tempo(InputStream is) throws IOException {
        byte[] bytes = getByteInfo(is);
        if (bytes == null || bytes.length != 3) return 0;
        int us = ((bytes[0] & 0xFF) << 16) +
                ((bytes[1] & 0xFF) << 8) + (bytes[2] & 0xFF);
        info.put("us-per-quarter-note", us);
        return 4;
    }

    private int smpteOffset(InputStream is) throws IOException {
        byte[] bytes = getByteInfo(is);
        info.put("smpte-offset", bytes);
        return 6;
    }

    private int timeSignature(InputStream is) throws IOException {
        byte[] bytes = getByteInfo(is);
        if (bytes == null || bytes.length != 4) return 0;
        String timeSig = (bytes[0]) + "/" + (1 << bytes[1]);
        info.put("time-sig", timeSig);
        info.put("metronome", bytes[2] & 0xFF);
        info.put("32nd-quarter-note-index", bytes[3] & 0xFF);
        return 5;
    }

    private int keySignature(InputStream is) throws IOException {
        byte[] bytes = getByteInfo(is);
        if (bytes == null || bytes.length != 2) return 0;
        info.put("tone", ToneMap.getTone(bytes[0], bytes[1]));
        return 3;
    }


    private int getLength(InputStream is) throws IOException {
        int len = is.read();
        info.put("meta-len", len);
        return len;
    }

    private byte[] getByteInfo(InputStream is) throws IOException {
        int len = getLength(is);
        byte[] bytes = new byte[len];
        if (len != is.read(bytes)) return null;
        return bytes;
    }

    public boolean isEoT() {
        return isEoT;
    }

    public boolean isTempo() {
        return isTempo;
    }

    public boolean isKeyPress() {
        return eventType == EventType.KEY_PRESS;
    }

    public int getDeltaTime() {
        return deltaTime;
    }

    public EventType getEventType() {
        return eventType;
    }

    public Map<String, Object> getInfo() {
        return info;
    }

    public int getLen() {
        return len;
    }

    public int getAbsTime() {
        return absTime;
    }

}
