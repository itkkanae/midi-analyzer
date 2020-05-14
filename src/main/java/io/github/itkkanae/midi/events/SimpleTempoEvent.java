package io.github.itkkanae.midi.events;

@SuppressWarnings({"unused"})
public class SimpleTempoEvent extends SimpleEvent {

    private int us;
    private double scale;

    public SimpleTempoEvent(int startTime, int us) {
        setStartTime(startTime);
        this.us = us;
    }

    public int getUs() {
        return us;
    }

    public void setUs(int us) {
        this.us = us;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

}
