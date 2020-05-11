package io.github.itkkanae.midi;


import com.alibaba.fastjson.JSONObject;
import io.github.itkkanae.midi.entity.Midi;
import io.github.itkkanae.midi.entity.Mtrk;
import io.github.itkkanae.midi.enums.EventType;
import io.github.itkkanae.midi.events.Event;
import io.github.itkkanae.midi.events.SimpleEvent;
import io.github.itkkanae.midi.events.SimpleKeyEvent;
import io.github.itkkanae.midi.events.SimpleTempoEvent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MidiUtil {

    public static void main(String[] args) throws IOException {

        String str = "E:\\Projects\\Overture\\sp\\summerpockets.mid";

        File file = new File(str);
        FileInputStream fis = new FileInputStream(file);

        Midi midi = new Midi(fis);

        List<Mtrk> mtrks = midi.getMtrks();

        int ticks = midi.getTicks(); //ticks per qn

        int speed = 96;

        List<List> list = new ArrayList<>();
        for (Mtrk mtrk : mtrks) {
            int end = mtrk.endTicks();
            List<SimpleKeyEvent> keyEvents = mtrk.getEvents().stream()
                    .filter(Event::isKeyPress).map(event -> {
                        int startTime = event.getAbsTime();
                        String name = (String) event.getInfo().get("note-name");
                        return new SimpleKeyEvent(startTime, name);
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
                list.add(keyEvents);
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
                    event.setScale((double) baseSpeed / event.getUs());
                }
                list.add(tempoEvents);
            }
        }
        System.out.println(JSONObject.toJSONString(list));

    }


}
