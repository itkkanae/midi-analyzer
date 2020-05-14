package io.github.itkkanae.midi.entity;

import io.github.itkkanae.midi.events.Event;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unused", "WeakerAccess"})
public class Mtrk {

    private int len;
    private int end;
    private List<Event> events;

    public Mtrk(int len, InputStream is) throws IOException {
        events = new ArrayList<>();
        this.len = len;
        int count = 0;
        int offset = 0;
        Event last;
        while (count < len) {
            count += Event.addEvent(is, offset, events);
            last = events.get(events.size() - 1);
            offset += last.getDeltaTime();
        }
        end = offset;
    }

    public int length() {
        return len;
    }

    public int endTicks() {
        return end;
    }

    public List<Event> getEvents() {
        return events;
    }

}
