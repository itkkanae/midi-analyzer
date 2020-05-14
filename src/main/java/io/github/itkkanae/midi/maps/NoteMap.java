package io.github.itkkanae.midi.maps;

@SuppressWarnings({"WeakerAccess"})
public class NoteMap {

    public static String[] notes = {
            "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"
    };

    public static String getName(int note) {
        return NoteMap.notes[note % 12] + (note / 12 - 1);
    }

}
