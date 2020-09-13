package net.joshuad.waveformjni;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

public class Test {
  static {
    System.load(System.getProperty("user.dir") + "\\vs17-workspace\\x64\\Release\\WaveformJNI.dll");
  }

  public static void main(String[] args) throws Exception {
    int channels = 2;
    int sampleRate = 44100;
    int bitsPerSample = 16;
    int byteRate = sampleRate * channels * bitsPerSample / 8;
    int blockAlign = channels * bitsPerSample / 8;
    HWaveOut waveOut = new HWaveOut(channels, sampleRate, byteRate, blockAlign, bitsPerSample);
    WaveHeader oldHeader = null;
    final int increment = 20000;
    for (int k = 0; k < 50; k++) {
      InputStream inputStream = new FileInputStream("test2.wav");
      byte[] fileHeader = new byte[44];
      inputStream.read(fileHeader);
      while (true) {
        byte[] buffer = new byte[increment];
        int bytesRead = inputStream.read(buffer, 0, increment);
        if (bytesRead <= 0) {
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
