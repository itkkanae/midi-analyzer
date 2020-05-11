package io.github.itkkanae.midi.maps;

@SuppressWarnings({"unused"})
public class ToneMap {

    private static String[] majorMap = {
            "Cb", "Gb", "Db", "Ab", "Eb", "Bb", "F",
            "C",
            "G", "D", "A", "E", "B", "F#", "C#"
    };

    private static String[] minorMap = {
            "Ab", "Eb", "Bb", "F", "C", "G", "D",
            "A",
            "E", "B", "F#", "C#", "G#", "D#", "A#"
    };

    public static String getTone(byte hByte, byte lbyte) {
        if (lbyte == 0) return "major " + majorMap[hByte + 7];
        return "minor " + minorMap[hByte + 7];
    }

}
