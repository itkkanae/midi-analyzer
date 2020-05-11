package io.github.itkkanae.midi.maps;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"unused", "WeakerAccess"})
public class PgmMap {

    public static Map<Integer, String> pgms;

    public static String getName(int pgm) {
        return pgms.get(pgm);
    }

    static {
        pgms = new HashMap<>();
        pgms.put(0x00, "Acoustic Grand Piano");
        pgms.put(0x01, "Bright Acoustic Piano");
        pgms.put(0x02, "Electric Grand Piano");
        pgms.put(0x03, "Honky-tonk Piano");
        pgms.put(0x04, "Rhodes Piano");
        pgms.put(0x05, "Chorused Piano");
        pgms.put(0x06, "Harpsichord");
        pgms.put(0x07, "Clavinet");
        pgms.put(0x08, "Celesta");
        pgms.put(0x09, "Glockenspiel");
        pgms.put(0x0A, "Music Box");
        pgms.put(0x0B, "Vibraphone");
        pgms.put(0x0C, "Marimba");
        pgms.put(0x0D, "Xylophone");
        pgms.put(0x0E, "Tubular Bells");
        pgms.put(0x0F, "Dulcimer");
        pgms.put(0x10, "Drawbar Organ");
        pgms.put(0x11, "Percussive Organ");
        pgms.put(0x12, "Rock Organ");
        pgms.put(0x13, "Church Organ");
        pgms.put(0x14, "Reed Organ");
        pgms.put(0x15, "Accordion");
        pgms.put(0x16, "Harmonica");
        pgms.put(0x17, "Tango Accordion");
        pgms.put(0x18, "Acoustic Guitar (nylon)");
        pgms.put(0x19, "Acoustic Guitar (steel)");
        pgms.put(0x1A, "Electric Guitar (jazz)");
        pgms.put(0x1B, "Electric Guitar (clean)");
        pgms.put(0x1C, "Electric Guitar (muted)");
        pgms.put(0x1D, "Overdriven Guitar");
        pgms.put(0x1E, "Distortion Guitar");
        pgms.put(0x1F, "Guitar harmonics");
        pgms.put(0x20, "Acoustic Bass");
        pgms.put(0x21, "Electric Bass (fingered)");
        pgms.put(0x22, "Electric Bass (picked)");
        pgms.put(0x23, "Fretless Bass");
        pgms.put(0x24, "Slap Bass 1");
        pgms.put(0x25, "Slap Bass 2");
        pgms.put(0x26, "Synth Bass 1");
        pgms.put(0x27, "Synth Bass 2");
        pgms.put(0x28, "Violin");
        pgms.put(0x29, "Viola");
        pgms.put(0x2A, "Cello");
        pgms.put(0x2B, "Contrabass");
        pgms.put(0x2C, "Tremolo Strings");
        pgms.put(0x2D, "Pizzicato Strings");
        pgms.put(0x2E, "Orchestral Harp");
        pgms.put(0x2F, "Timpani");
        pgms.put(0x30, "String Ensemble 1 (strings)");
        pgms.put(0x31, "String Ensemble 2 (slow strings)");
        pgms.put(0x32, "SynthStrings 1");
        pgms.put(0x33, "SynthStrings 2");
        pgms.put(0x34, "Choir Aahs");
        pgms.put(0x35, "Voice Oohs");
        pgms.put(0x36, "Synth Voice");
        pgms.put(0x37, "Orchestra Hit");
        pgms.put(0x38, "Trumpet");
        pgms.put(0x39, "Trombone");
        pgms.put(0x3A, "Tuba");
        pgms.put(0x3B, "Muted Trumpet");
        pgms.put(0x3C, "French Horn");
        pgms.put(0x3D, "Brass Section");
        pgms.put(0x3E, "SynthBrass 1");
        pgms.put(0x3F, "SynthBrass 2");
        pgms.put(0x40, "Soprano Sax");
        pgms.put(0x41, "Alto Sax");
        pgms.put(0x42, "Tenor Sax");
        pgms.put(0x43, "Baritone Sax");
        pgms.put(0x44, "Oboe");
        pgms.put(0x45, "English Horn");
        pgms.put(0x46, "Bassoon");
        pgms.put(0x47, "Clarinet");
        pgms.put(0x48, "Piccolo");
        pgms.put(0x49, "Flute");
        pgms.put(0x4A, "Recorder");
        pgms.put(0x4B, "Pan Flute");
        pgms.put(0x4C, "Blown Bottle");
        pgms.put(0x4D, "Shakuhachi");
        pgms.put(0x4E, "Whistle");
        pgms.put(0x4F, "Ocarina");
        pgms.put(0x50, "Lead 1 (square wave)");
        pgms.put(0x51, "Lead 2 (sawtooth wave)");
        pgms.put(0x52, "Lead 3 (calliope)");
        pgms.put(0x53, "Lead 4 (chiffer)");
        pgms.put(0x54, "Lead 5 (charang)");
        pgms.put(0x55, "Lead 6 (voice solo)");
        pgms.put(0x56, "Lead 7 (fifths)");
        pgms.put(0x57, "Lead 8 (bass + lead)");
        pgms.put(0x58, "Pad 1 (new age Fantasia)");
        pgms.put(0x59, "Pad 2 (warm)");
        pgms.put(0x5A, "Pad 3 (polysynth)");
        pgms.put(0x5B, "Pad 4 (choir space voice)");
        pgms.put(0x5C, "Pad 5 (bowed glass)");
        pgms.put(0x5D, "Pad 6 (metallic pro)");
        pgms.put(0x5E, "Pad 7 (halo)");
        pgms.put(0x5F, "Pad 8 (sweep)");
        pgms.put(0x60, "FX 1 (rain)");
        pgms.put(0x61, "FX 2 (soundtrack)");
        pgms.put(0x62, "FX 3 (crystal)");
        pgms.put(0x63, "FX 4 (atmosphere)");
        pgms.put(0x64, "FX 5 (brightness)");
        pgms.put(0x65, "FX 6 (goblins)");
        pgms.put(0x66, "FX 7 (echoes, drops)");
        pgms.put(0x67, "FX 8 (sci-fi, star theme)");
        pgms.put(0x68, "Sitar");
        pgms.put(0x69, "Banjo");
        pgms.put(0x6A, "Shamisen");
        pgms.put(0x6B, "Koto");
        pgms.put(0x6C, "Kalimba");
        pgms.put(0x6D, "Bag pipe");
        pgms.put(0x6E, "Fiddle");
        pgms.put(0x6F, "Shanai");
        pgms.put(0x70, "Tinkle Bell");
        pgms.put(0x71, "Agogo");
        pgms.put(0x72, "Steel Drums");
        pgms.put(0x73, "Woodblock");
        pgms.put(0x74, "Taiko Drum");
        pgms.put(0x75, "Melodic Tom");
        pgms.put(0x76, "Synth Drum");
        pgms.put(0x77, "Reverse Cymbal");
        pgms.put(0x78, "Guitar Fret Noise");
        pgms.put(0x79, "Breath Noise");
        pgms.put(0x7A, "Seashore");
        pgms.put(0x7B, "Bird Tweet");
        pgms.put(0x7C, "Telephone Ring");
        pgms.put(0x7D, "Helicopter");
        pgms.put(0x7E, "Applause");
        pgms.put(0x7F, "Gunshot");

    }

}
