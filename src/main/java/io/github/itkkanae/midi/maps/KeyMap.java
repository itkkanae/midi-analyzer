package io.github.itkkanae.midi.maps;

public class KeyMap {

    public static String getKeySign(int note, String keys) {
        if (keys == null || "".equals(keys)) keys = "cxz/.,mnbv\';lkjhg][poiuy=-0987654321`";
        if (60 <= note && note <= 96)
            return keys.substring(note - 60, note - 59);
        return " ";
    }

}
