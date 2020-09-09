package net.joshuad.waveformjni;

public class WaveFormatEx {
  final public short nChannels;
  final public int nSamplesPerSec;
  final public int nAvgBytesPerSec;
  final public short nBlockAlign;
  final public short wBitsPerSample;
  final public int cbSize = 0;

  public WaveFormatEx(int nChannels, int nSamplesPerSec, int nAvgBytesPerSec, int nBlockAlign,
      int wBitsPerSample) {
    this.nChannels = (short) nChannels;
    this.nSamplesPerSec = nSamplesPerSec;
    this.nAvgBytesPerSec = nAvgBytesPerSec;
    this.nBlockAlign = (short) nBlockAlign;
    this.wBitsPerSample = (short) wBitsPerSample;
  }
}
