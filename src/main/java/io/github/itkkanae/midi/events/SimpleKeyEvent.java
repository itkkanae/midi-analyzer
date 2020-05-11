package io.github.itkkanae.midi.events;

public class SimpleKeyEvent extends SimpleEvent {

    private double noteLen;
    private String noteName;

    public SimpleKeyEvent(int startTime, String noteName) {
        setStartTime(startTime);
        this.noteName = noteName;
    }

    public double getNoteLen() {
        return noteLen;
    }

    public void setNoteLen(double noteLen) {
        this.noteLen = noteLen;
    }

    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

}
