package net.joshuad.waveformjni;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

public class Test {
  static {
    System.load(System.getProperty("user.dir") + "\\vs17-workspace\\x64\\Release\\WaveformJNI.dll");
  }

  public static void main(String[] args) throws Exception {
    HWaveOut waveOut = new HWaveOut(2, 44100, 176400, 4, 16);
    for (int k = 0; k < 50; k++) {
      waveOut.getVolume();
      InputStream inputStream = new FileInputStream("test5.wav");
      byte[] fileHeader = new byte[44];
      inputStream.read(fileHeader);
      final int increment = 80000;
      WaveHeader oldHeader = null;
      while (true) {
        byte[] buffer = new byte[increment];
        int bytesRead = inputStream.read(buffer, 0, increment);
        if (bytesRead <= 0) {
          while ((oldHeader.getFlags() & WaveHeader.DONE) != WaveHeader.DONE) {
            Thread.sleep(1);
          }
          break;
        } else if (bytesRead < increment) {
          buffer = Arrays.copyOfRange(buffer, 0, bytesRead);
        }
        WaveHeader header = new WaveHeader(buffer);
        waveOut.prepareHeader(header);
        waveOut.write(header);
        if (oldHeader != null) {
          while ((oldHeader.getFlags() & WaveHeader.DONE) != WaveHeader.DONE) {
            Thread.sleep(1);
          }
          waveOut.unprepareHeader(oldHeader);
          oldHeader.close();
        }
        oldHeader = header;
      }
      inputStream.close();
    }
  }
}
