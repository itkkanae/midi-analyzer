package io.github.itkkanae.midi.entity;

import io.github.itkkanae.midi.enums.FileFmt;
import io.github.itkkanae.midi.enums.TimeFmt;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"all"})
public class Midi {

    private int tracksCount;
    private FileFmt fileFmt;
    private TimeFmt timeFmt;
    private int ticks;
    private int uspt;
    private List<Mtrk> mtrks;


    public Midi(InputStream is) throws IOException {
        mtrks = new ArrayList<>();
        while (getBlock(is)) ;
    }

    private boolean getBlock(InputStream is) throws IOException {
        byte[] mthd = new byte[4];
        if (4 != is.read(mthd)) return false;
        switch (new String(mthd)) {
            case "MThd":
                setMthd(is);
                return true;
            case "MTrk":
                setMtrk(is);
                return true;
            default:
                return false;
        }
    }

    private void setMthd(InputStream is) throws IOException {
        int len = getLength(is);
        byte[] mthd = new byte[len];
        if (6 == len && len == is.read(mthd)) {
            setFileFmt(mthd[1]);
            setTracksCount(mthd[2], mthd[3]);
            setTimeInfo(mthd[4], mthd[5]);
        }
    }

    private void setMtrk(InputStream is) throws IOException {
        int len = getLength(is);
        mtrks.add(new Mtrk(len, is));
    }


    private int getLength(InputStream is) throws IOException {
        byte[] len = new byte[4];
        if (4 != is.read(len)) throw new RuntimeException("len error");
        return ((len[0] & 0xFF) << 24) + ((len[1] & 0xFF) << 16) +
                ((len[2] & 0xFF) << 8) + (len[3] & 0xFF);
    }

    private void setFileFmt(byte code) {
        switch (code) {
            case 0:
                fileFmt = FileFmt.SINGLE;
                break;
            case 1:
                fileFmt = FileFmt.MULTIPLE;
                break;
            case 2:
                fileFmt = FileFmt.ASYNC;
                break;
            default:
                throw new RuntimeException("format false");
        }
    }

    private void setTracksCount(byte hByte, byte lByte) {
        tracksCount = ((hByte & 0xFF) << 8) + (lByte & 0xFF);
    }

    private void setTimeInfo(byte hByte, byte lByte) {
        if ((hByte & 0xFF) < 0x80) {
            timeFmt = TimeFmt.TICKS;
            ticks = ((hByte & 0xFF) << 8) + (lByte & 0xFF);
        } else {
            timeFmt = TimeFmt.SMTPE;
            int frames = hByte & 0x7F;
            int tpf = lByte & 0xFF;
            uspt = 1000000 / frames / tpf;
        }
    }

    public int getTracksCount() {
        return tracksCount;
    }

    public FileFmt getFileFmt() {
        return fileFmt;
    }

    public TimeFmt getTimeFmt() {
        return timeFmt;
    }

    public int getTicks() {
        return ticks;
    }

    public int getUspt() {
        return uspt;
    }

    public List<Mtrk> getMtrks() {
        return mtrks;
    }

}