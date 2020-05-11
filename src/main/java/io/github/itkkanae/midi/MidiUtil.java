package io.github.itkkanae.midi;


import com.alibaba.fastjson.JSONObject;
import io.github.itkkanae.midi.entity.Midi;
import io.github.itkkanae.midi.entity.Mtrk;
import io.github.itkkanae.midi.enums.EventType;
import io.github.itkkanae.midi.events.Event;

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
        List<List<Event>> list = new ArrayList<>();
        for (Mtrk mtrk : mtrks) {
            List<Event> events = mtrk.getEvents().stream()
                    .filter(event -> event.isKeyPress() || event.isTempo())
                    .collect(Collectors.toList());
            list.add(events);
        }
        System.out.println(JSONObject.toJSONString(list));

    }


}
