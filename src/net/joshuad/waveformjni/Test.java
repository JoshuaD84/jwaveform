package net.joshuad.waveformjni;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

public class Test {
  static {
    System.load(System.getProperty("user.dir")
        + "\\visual-studio-17-workspace\\x64\\Release\\HypnosNativeSoundWin.dll");
  }

  public static void main(String[] args) throws Exception {
    HWaveOut waveOut = new HWaveOut(2, 44100, 176400, 4, 16);
    for (int k = 0; k < 50; k++) {
      InputStream inputStream = new FileInputStream("test2.wav");
      byte[] fileHeader = new byte[44];
      inputStream.read(fileHeader);
      final int increment = 80000;
      WaveHeader oldHeader = null;
      while (true) {
        System.out.println("\n[" + System.currentTimeMillis() + "]: " + "Queueing new buffer");
        byte[] buffer = new byte[increment];
        int bytesRead = inputStream.read(buffer, 0, increment);
        System.out.println("Bytes read: " + bytesRead);
        if (bytesRead <= 0) {
          System.out.println("Breaking");
          break;
        } else if (bytesRead < increment) {
          int trim = 96 - (bytesRead % 2); //Make sure we send an even number of bytes
          //odd causes static
          //I'm not confident about what I'm diong here. But it stops a click on loop
          buffer = Arrays.copyOfRange(buffer, 0, bytesRead - trim);
          System.out.println("Truncating to: " + (bytesRead - trim));
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
