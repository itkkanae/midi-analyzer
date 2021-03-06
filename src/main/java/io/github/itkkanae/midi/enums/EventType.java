package io.github.itkkanae.midi.enums;

public enum EventType {
    KEY_RELEASE,
    KEY_PRESS,
    KEY_AFTER_TOUCH,
    CONTROL_CHANGE,
    PROGRAM_CHANGE,
    CHANNEL_AFTER_TOUCH,
    PITCH_WHEEL_CHANGE,

    SYSTEM_EXCLUSIVE,
    SONG_POSITION_POINTER,
    SONG_SELECT,
    TUNE_REQUEST,
    END_OF_EXCLUSIVE,
    TIMING_CLOCK,
    START,
    CONTINUE,
    STOP,
    ACTIVE_SENSING,

    META,
}
