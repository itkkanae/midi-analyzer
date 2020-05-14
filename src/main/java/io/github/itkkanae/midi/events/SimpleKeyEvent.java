package io.github.itkkanae.midi.events;

import io.github.itkkanae.midi.maps.KeyMap;

@SuppressWarnings({"unused"})
public class SimpleKeyEvent extends SimpleEvent {

    private double noteLen;
    private double absLen;
    private String noteName;
    private int noteNum;
    private String key;
    private boolean isNoLen;

    public static void swap(SimpleKeyEvent a, SimpleKeyEvent b) {
        int noteNum = a.getNoteNum();
        String noteName = a.getNoteName();
        String key = a.getKey();
        a.noteNum = b.noteNum;
        a.key = b.key;
        a.setNoteName(b.getNoteName());
        b.noteNum = noteNum;
        b.setNoteName(noteName);
        b.key = key;
    }

    public SimpleKeyEvent(int startTime, String noteName, int noteNum, String keys) {
        setStartTime(startTime);
        setNoteNum(noteNum, keys);
        setNoteName(noteName);
    }

    public void setNoteNum(int noteNum, String keys) {
        this.key = KeyMap.getKeySign(noteNum, keys);
        this.noteNum = noteNum;
    }

    public void setNoteLen(double noteLen) {
        if (noteLen < 0x1P-10) isNoLen = true;
        this.noteLen = noteLen;
    }

    public void setAbsLen(double scale) {
        this.absLen = scale * noteLen;
    }

    public double getAbsLen() {
        return absLen;
    }

    public boolean isNoLen() {
        return isNoLen;
    }

    public double getNoteLen() {
        return noteLen;
    }


    public String getKey() {
        return key;
    }

    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public int getNoteNum() {
        return noteNum;
    }


}
