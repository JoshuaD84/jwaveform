package net.joshuad.waveformjni.test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import net.joshuad.waveformjni.HWaveOut;
import net.joshuad.waveformjni.WaveHeader;

public class DoubleBufferTest {
  static {
    System.load(
        System.getProperty("user.dir") + "\\vs17-workspace\\x64\\Release\\WaveformJNI.dll");
  }

  public static void main(String[] args) throws Exception {
    int channels = 2;
    int sampleRate = 44100;
    int bitsPerSample = 16;
    int byteRate = sampleRate * channels * bitsPerSample / 8;
    int blockAlign = channels * bitsPerSample / 8;
    HWaveOut waveOut = new HWaveOut(channels, sampleRate, byteRate, blockAlign, bitsPerSample);
    WaveHeader oldHeader = null;
    final int increment = 40000;
    int bytesRead = 0;
    int subChunk2Size;
    for (int k = 0; k < 50; k++) {
      InputStream inputStream = new FileInputStream("test2.wav");
      byte[] fileHeader = new byte[44];
      inputStream.read(fileHeader);
      ByteBuffer bf = ByteBuffer.wrap(fileHeader);
      bf.order(ByteOrder.LITTLE_ENDIAN);
      subChunk2Size = bf.getInt(40);
      System.out.println("subChunk2Size: " + subChunk2Size);
      while (true) {
        System.out.println("Position: " + waveOut.getPosition());
        byte[] buffer;
        if ( subChunk2Size - bytesRead <= 0) {
          bytesRead = 0;
          break;
        } else if (subChunk2Size - bytesRead > increment) {
          buffer = new byte[increment];
          int bytesReadThisLoop = inputStream.read(buffer, 0, increment);
          bytesRead += bytesReadThisLoop;
        } else {
          int remainingBytes = subChunk2Size - bytesRead;
          buffer = new byte[remainingBytes];
          int bytesReadThisLoop = inputStream.read(buffer, 0, remainingBytes);
          bytesRead += bytesReadThisLoop;
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
