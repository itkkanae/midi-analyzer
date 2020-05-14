package io.github.itkkanae.midi.utils;

import com.alibaba.fastjson.JSONObject;
import io.github.itkkanae.midi.entity.Midi;
import io.github.itkkanae.midi.entity.Mtrk;
import io.github.itkkanae.midi.events.Event;
import io.github.itkkanae.midi.events.SimpleKeyEvent;
import io.github.itkkanae.midi.events.SimpleTempoEvent;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@SuppressWarnings({"unused"})
public class MidiUtil {

    public static void main(String[] args) throws IOException {

        String str = "";

        File file = new File(str);
        FileInputStream fis = new FileInputStream(file);
        System.out.println(midFfTransformer(null, fis));
/*
        String fName = file.getPath();

        File newFile = new File(fName.subSequence(0, fName.lastIndexOf(".")) + "_speed_" + speed + ".txt");
        System.out.println(newFile.getPath());
        FileWriter fw = new FileWriter(newFile);
        BufferedWriter bw = new BufferedWriter(fw);

        for (SimpleKeyEvent event : keyList.get(0)) {
            String len = event.getAbsLen() + "";
            if (len.length() > 6) len = len.substring(0, 7);
            bw.write(event.getKey() + "|" + len + "\n");
        }
        bw.close();

        System.out.println(JSONObject.toJSONString(midi));
*/
    }

    public static String midFfTransformer(String keys, InputStream is) throws IOException {

        Midi midi = new Midi(is);
        List<Mtrk> mtrks = midi.getMtrks();
        int ticks = midi.getTicks(); //ticks per qn
        int speed;

        Map<String, Object> map = new HashMap<>();
        List<SimpleTempoEvent> tempoList = null;
        List<List<SimpleKeyEvent>> keyList = new ArrayList<>();
        map.put("key-rail", keyList);

        //get key-press-events & tempo-events
        for (Mtrk mtrk : mtrks) {
            int end = mtrk.endTicks();
            List<SimpleKeyEvent> keyEvents = mtrk.getEvents().stream()
                    .filter(Event::isKeyPress).map(event -> {
                        int startTime = event.getAbsTime();
                        String name = (String) event.getInfo().get("note-name");
                        int num = (Integer) event.getInfo().get("note");
                        return new SimpleKeyEvent(startTime, name, num, keys);
                    }).collect(Collectors.toList());

            if (!keyEvents.isEmpty()) {
                for (int i = 0; i < keyEvents.size(); i++) {
                    SimpleKeyEvent event = keyEvents.get(i);
                    if (i == (keyEvents.size() - 1)) {
                        event.setEndTime(end);
                    } else {
                        event.setEndTime(keyEvents.get(i + 1).getStartTime());
                    }
                    event.setNoteLen((double) event.getDeltaTime() / ticks);
                }
                keyList.add(keyEvents);
            } else {
                List<SimpleTempoEvent> tempoEvents = mtrk.getEvents().stream()
                        .filter(Event::isTempo).map(event -> {
                            int startTime = event.getAbsTime();
                            int us = (Integer) event.getInfo().get("us-per-quarter-note");
                            return new SimpleTempoEvent(startTime, us);
                        }).collect(Collectors.toList());

                int baseSpeed = tempoEvents.get(0).getUs();
                speed = 60000000 / baseSpeed;
                for (int i = 0; i < tempoEvents.size(); i++) {
                    SimpleTempoEvent event = tempoEvents.get(i);
                    if (i == (tempoEvents.size() - 1)) {
                        event.setEndTime(end);
                    } else {
                        event.setEndTime(tempoEvents.get(i + 1).getStartTime());
                    }
                    event.setScale(event.getUs() / (double) baseSpeed);
                }
                tempoList = tempoEvents;
                map.put("tempo-rail", tempoEvents);
                map.put("speed", speed);
            }
        }

        //count absolute delta time
        if (tempoList != null && !keyList.isEmpty()) {
            for (List<SimpleKeyEvent> list : keyList) {
                int i = 0;
                for (SimpleTempoEvent tempo : tempoList) {
                    int endTime = tempo.getEndTime();
                    double scale = tempo.getScale();
                    for (; i < list.size(); ) {
                        SimpleKeyEvent keyEvent = list.get(i);
                        int startTime = keyEvent.getStartTime();
                        if (startTime < endTime) {
                            keyEvent.setAbsLen(scale);
                            i++;
                        } else {
                            break;
                        }
                    }
                }
            }
        }

        //swap chord with low to height
        if (tempoList != null && !keyList.isEmpty()) {
            for (List<SimpleKeyEvent> list : keyList) {
                int i = 0;
                List<SimpleKeyEvent> noLen = new ArrayList<>();
                for (; i < list.size(); i++) {
                    if (list.get(i).isNoLen()) {
                        noLen.add(list.get(i));
                    } else if (!noLen.isEmpty()) {
                        noLen.add(list.get(i));
                        if (noLen.get(0).getNoteNum() > noLen.get(noLen.size() - 1).getNoteNum()) {
                            for (int j = (noLen.size() / 2) - 1; j >= 0; j--) {
                                SimpleKeyEvent.swap(noLen.get(j), noLen.get(noLen.size() - 1 - j));
                            }
                        }
                        noLen = new ArrayList<>();
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (SimpleKeyEvent event : keyList.get(0)) {
            String len = event.getAbsLen() + "";
            if (len.length() > 6) len = len.substring(0, 7);
            sb.append(event.getKey()).append("|").append(len).append("\n");
        }

        return sb.toString();
    }


}
