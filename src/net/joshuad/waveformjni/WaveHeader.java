package net.joshuad.waveformjni;

import java.io.Closeable;
import net.joshuad.waveformjni.exception.HeaderDeletedException;

public class WaveHeader implements Closeable {
  
  public static final int DONE = 0x00000001;
  public static final int PREPARED = 0x00000010;
  public static final int BEGINLOOP = 0x00000100;
  public static final int ENDLOOP = 0x00001000;
  public static final int WHDR_INQUEUE = 0x00010000;
  
  long waveHeaderPointer;

  private boolean deleted = false;

  public WaveHeader(byte[] buffer) {
    waveHeaderPointer = HWaveOut.createHeader(buffer, buffer.length);
  }

  public synchronized long getPointerAddress() throws HeaderDeletedException {
    if (deleted) {
      throw new HeaderDeletedException();
    }
    return waveHeaderPointer;
  }
  
  public int getFlags() {
    return HWaveOut.getHeaderFlags(waveHeaderPointer);
  }

  @Override
  public synchronized void close() {
    deleted = true;
    HWaveOut.destroyHeader(waveHeaderPointer);
  }
}
