package net.joshuad.waveformjni;

public class WaveOutCaps {
  /* NOTE: If you change the method names in this class you also have to change 
   * net_joshua_wavefomjni_HWaveOut.cpp in waveOutGetDevCaps or you will get a core dump
   */
  
  //for dwFormats
  public static final int WAVE_FORMAT_1M08  = 0b00000000000000000000000000000001;
  public static final int WAVE_FORMAT_1M16  = 0b00000000000000000000000000000100;
  public static final int WAVE_FORMAT_1S08  = 0b00000000000000000000000000000010;
  public static final int WAVE_FORMAT_1S16  = 0b00000000000000000000000000001000;
  public static final int WAVE_FORMAT_2M08  = 0b00000000000000000000000000010000;
  public static final int WAVE_FORMAT_2M16  = 0b00000000000000000000000001000000;
  public static final int WAVE_FORMAT_2S08  = 0b00000000000000000000000000100000;
  public static final int WAVE_FORMAT_2S16  = 0b00000000000000000000000010000000;
  public static final int WAVE_FORMAT_4M08  = 0b00000000000000000000000100000000;
  public static final int WAVE_FORMAT_4M16  = 0b00000000000000000000010000000000;
  public static final int WAVE_FORMAT_4S08  = 0b00000000000000000000001000000000;
  public static final int WAVE_FORMAT_4S16  = 0b00000000000000000000100000000000;
  public static final int WAVE_FORMAT_96M08 = 0b00000000000000010000000000000000;
  public static final int WAVE_FORMAT_96M16 = 0b00000000000001000000000000000000;
  public static final int WAVE_FORMAT_96S08 = 0b00000000000000100000000000000000;
  public static final int WAVE_FORMAT_96S16 = 0b00000000000010000000000000000000;
     
  //for dwSupport
  public static final int WAVECAPS_LRVOLUME       = 0b00000000000000000000000000001000;
  public static final int WAVECAPS_PITCH          = 0b00000000000000000000000000000001;
  public static final int WAVECAPS_PLAYBACKRATE   = 0b00000000000000000000000000000010;
  public static final int WAVECAPS_SYNC           = 0b00000000000000000000000000010000;
  public static final int WAVECAPS_VOLUME         = 0b00000000000000000000000000000100;
  public static final int WAVECAPS_SAMPLEACCURATE = 0b00000000000000000000000000100000;

  private short wMid;
  private short wPid;
  private short vDriverVersion;
  private String productName;
  private int dwFormats;
  private short wChannels;
  // short wReserved1;
  private int dwSupport;
  
  public WaveOutCaps() {}

  public WaveOutCaps(int wMid, int wPid, int vDriverVersion, String productName,
      int dwFormats, int wChannels, int dwSupport) {
    this.wMid = (short)wMid;
    this.wPid = (short)wPid;
    this.vDriverVersion = (short)vDriverVersion;
    this.productName = productName;
    this.dwFormats = dwFormats;
    this.wChannels = (short)wChannels;
    this.dwSupport = dwSupport;
  }
  
  public void setWMid(short wMid) {
    this.wMid = wMid;
  }

  public void setWPid(short wPid) {
    this.wPid = wPid;
  }

  public void setVDriverVersion(short vDriverVersion) {
    this.vDriverVersion = vDriverVersion;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public void setDwFormats(int dwFormats) {
    this.dwFormats = dwFormats;
  }

  public void setWChannels(short wChannels) {
    this.wChannels = wChannels;
  }

  public void setDwSupport(int dwSupport) {
    this.dwSupport = dwSupport;
  }
  
  public short getWMid() {
    return wMid;
  }

  public short getWPid() {
    return wPid;
  }

  public short getVDriverVersion() {
    return vDriverVersion;
  }

  public String getProductName() {
    return productName;
  }

  public int getDwFormats() {
    return dwFormats;
  }

  public short getWChannels() {
    return wChannels;
  }

  public int getDwSupport() {
    return dwSupport;
  }
}
