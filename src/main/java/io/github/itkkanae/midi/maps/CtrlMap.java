package io.github.itkkanae.midi.maps;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"unused", "WeakerAccess"})
public class CtrlMap {

    public static Map<Integer, String> ctrls;

    public static String getName(int ctrl) {
        return ctrls.get(ctrl);
    }

    static {
        ctrls = new HashMap<>();
        ctrls.put(0x00, "Bank Select MSB");
        ctrls.put(0x01, "Modulation wheel MSB");
        ctrls.put(0x02, "Breath control MSB");
        ctrls.put(0x04, "Foot controller MSB");
        ctrls.put(0x05, "Portamento time MSB");
        ctrls.put(0x06, "Data Entry MSB");
        ctrls.put(0x07, "Channel Volume MSB");
        ctrls.put(0x08, "Balance MSB");
        ctrls.put(0x0A, "Pan MSB");
        ctrls.put(0x0B, "Expression Controller MSB");
        ctrls.put(0x0C, "Effect control 1 MSB");
        ctrls.put(0x0D, "Effect control 2 MSB");
        ctrls.put(0x10, "General Purpose Controller 1 MSB");
        ctrls.put(0x11, "General Purpose Controller 2 MSB");
        ctrls.put(0x12, "General Purpose Controller 3 MSB");
        ctrls.put(0x13, "General Purpose Controller 4 MSB");
        ctrls.put(0x20, "Bank Select LSB");
        ctrls.put(0x21, "Modulation wheel LSB");
        ctrls.put(0x22, "Breath control LSB");
        ctrls.put(0x24, "Foot controller LSB");
        ctrls.put(0x25, "Portamento time LSB");
        ctrls.put(0x26, "Data entry LSB");
        ctrls.put(0x27, "Channel Volume LSB");
        ctrls.put(0x28, "Balance LSB");
        ctrls.put(0x2A, "Pan LSB");
        ctrls.put(0x2B, "Expression Controller LSB");
        ctrls.put(0x2C, "Effect control 1 LSB");
        ctrls.put(0x2D, "Effect control 2 LSB");
        ctrls.put(0x30, "General Purpose Controller 1 LSB");
        ctrls.put(0x31, "General Purpose Controller 2 LSB");
        ctrls.put(0x32, "General Purpose Controller 3 LSB");
        ctrls.put(0x33, "General Purpose Controller 4 LSB");
        ctrls.put(0x40, "Damper pedal");
        ctrls.put(0x41, "Portamento");
        ctrls.put(0x42, "Sustenuto");
        ctrls.put(0x43, "Soft pedal");
        ctrls.put(0x44, "Legato Footswitch");
        ctrls.put(0x45, "Hold 2");
        ctrls.put(0x46, "Sound Controller 1 (Sound Variation)");
        ctrls.put(0x47, "Sound Controller 2 (Timbre)");
        ctrls.put(0x48, "Sound Controller 3 (Release Time)");
        ctrls.put(0x49, "Sound Controller 4 (Attack Time)");
        ctrls.put(0x4A, "Sound Controller 5 (Brightness)");
        ctrls.put(0x4B, "Sound Controller 6");
        ctrls.put(0x4C, "Sound Controller 7");
        ctrls.put(0x4D, "Sound Controller 8");
        ctrls.put(0x4E, "Sound Controller 9");
        ctrls.put(0x4F, "Sound Controller 10");
        ctrls.put(0x50, "General Purpose Controller 5");
        ctrls.put(0x51, "General Purpose Controller 6");
        ctrls.put(0x52, "General Purpose Controller 7");
        ctrls.put(0x53, "General Purpose Controller 8");
        ctrls.put(0x54, "Portamento Control");
        ctrls.put(0x5B, "Effects 1 Depth");
        ctrls.put(0x5C, "Effects 2 Depth");
        ctrls.put(0x5D, "Effects 3 Depth");
        ctrls.put(0x5E, "Effects 4 Depth");
        ctrls.put(0x5F, "Effects 5 Depth");
        ctrls.put(0x60, "Data entry +1");
        ctrls.put(0x61, "Data entry -1");
        ctrls.put(0x62, "Non-Registered Parameter Number LSB");
        ctrls.put(0x63, "Non-Registered Parameter Number MSB");
        ctrls.put(0x64, "Registered Parameter Number LSB");
        ctrls.put(0x65, "Registered Parameter Number MSB");
        ctrls.put(0x78, "All Sound Off");
        ctrls.put(0x79, "Reset All Controllers");
        ctrls.put(0x7A, "Local control");
        ctrls.put(0x7B, "All notes off");
        ctrls.put(0x7C, "Omni mode off");
        ctrls.put(0x7D, "Omni mode on");
        ctrls.put(0x7E, "Poly mode off");
        ctrls.put(0x7F, "Poly mode on");
    }


}
