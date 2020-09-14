package net.joshuad.waveformjni.test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import net.joshuad.waveformjni.HWaveOut;
import net.joshuad.waveformjni.WaveHeader;

public class LoopTest {
  static {
    System.load(System.getProperty("user.dir") + "\\vs17-workspace\\x64\\Release\\WaveformJNI.dll");
  }

  public static void main(String[] args) throws Exception {
    InputStream inputStream = new FileInputStream("test4.wav");
    byte[] fh = new byte[44];
    inputStream.read(fh);
    ByteBuffer bf = ByteBuffer.wrap(fh);
    bf.order(ByteOrder.LITTLE_ENDIAN);

    int chunkSize = bf.getInt(4);
    System.out.println("Chunk size: " + chunkSize);
    
    int subChunk1Size = bf.getInt(16);
    System.out.println("subChunk1Size: " + subChunk1Size);
    
    short audioFormat = bf.getShort(20);
    System.out.println("audioFormat: " + audioFormat);
    
    short nChannels = bf.getShort(22);
    System.out.println("nChannels: " + nChannels);
    
    int sampleRate = bf.getInt(24);
    System.out.println("sampleRate: " + sampleRate);
    
    int byteRateIn = bf.getInt(28);
    System.out.println("byteRateIn: " + byteRateIn);
    
    int blockAlignIn = bf.getShort(32);
    System.out.println("blockAlignIn: " + blockAlignIn);
    
    int bitsPerSample = bf.getShort(34);
    System.out.println("bitsPerSample: " + bitsPerSample);
    
    int byteRateCalc = sampleRate * nChannels * bitsPerSample / 8;
    int blockAlignCalc = nChannels * bitsPerSample / 8;
   
    System.out.println("byteRateCalc: " + byteRateCalc);
    System.out.println("blockAlignCalc: " + blockAlignCalc);
    
    int subChunk2Size = bf.getInt(40);
    System.out.println("subChunk2Size: " + subChunk2Size);
    
    byte[] data = new byte[subChunk2Size];
    inputStream.read(data);
    
    inputStream.close();
    
    HWaveOut waveOut = new HWaveOut(nChannels, sampleRate, byteRateCalc, blockAlignCalc, bitsPerSample);
    WaveHeader header = new WaveHeader(data);
    waveOut.prepareHeader(header);
    waveOut.write(header);
    WaveHeader header2 = new WaveHeader(data);
    waveOut.prepareHeader(header2);
    waveOut.write(header2);
    while ((header2.getFlags() & WaveHeader.DONE) != WaveHeader.DONE) {
      Thread.sleep(1);
    }
  }
}
