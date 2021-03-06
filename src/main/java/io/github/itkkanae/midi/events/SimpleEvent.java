package io.github.itkkanae.midi.events;

@SuppressWarnings({"unused", "WeakerAccess"})
public class SimpleEvent {

    private int startTime;
    private int endTime;

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getDeltaTime() {
        return endTime - startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

}
