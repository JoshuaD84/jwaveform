package net.joshuad.waveformjni.test;

import net.joshuad.waveformjni.HWaveOut;
import net.joshuad.waveformjni.WaveOutCaps;

public class CapabilitiesTest {
  static {
    System.load(System.getProperty("user.dir") + "\\vs17-workspace\\x64\\Release\\WaveformJNI.dll");
  }

  public static void main(String[] args) throws Exception {
    System.out.println("Number of devices: " + HWaveOut.getNumDevs());
    WaveOutCaps caps = HWaveOut.getDevCaps();
    System.out.println("Manufacturer ID: " + caps.getWMid());
    System.out.println("Device name: " + caps.getProductName());
    System.out.println("Product ID: " + caps.getWPid());
    System.out.println("Supported Channels: " + caps.getWChannels());
    byte majorVersion = (byte)(caps.getVDriverVersion() >> 8);
    byte minorVersion = (byte)caps.getVDriverVersion();
    System.out.println("Driver Version: " + majorVersion + "." + minorVersion);
    
    System.out.println();
    
    System.out.println(
        "11.025 kHz, mono, 8-bit : " + ((caps.getDwFormats() & WaveOutCaps.WAVE_FORMAT_1M08) > 0));
    System.out.println(
        "11.025 kHz, mono, 16-bit: " + ((caps.getDwFormats() & WaveOutCaps.WAVE_FORMAT_1M16) > 0));
    System.out.println(
        "11.025 kHz, stereo, 8-bit: " + ((caps.getDwFormats() & WaveOutCaps.WAVE_FORMAT_1S08) > 0));
    System.out.println(
        "11.025 kHz, stereo, 16-bit: " + ((caps.getDwFormats() & WaveOutCaps.WAVE_FORMAT_1S16) > 0));
    System.out.println(
        "22.05 kHz, mono, 8-bit: " + ((caps.getDwFormats() & WaveOutCaps.WAVE_FORMAT_2M08) > 0));
    System.out.println(
        "22.05 kHz, mono, 16-bit: " + ((caps.getDwFormats() & WaveOutCaps.WAVE_FORMAT_2M16) > 0));
    System.out.println(
        "22.05 kHz, stereo, 8-bit: " + ((caps.getDwFormats() & WaveOutCaps.WAVE_FORMAT_2S08) > 0));
    System.out.println(
        "22.05 kHz, stereo, 16-bit: " + ((caps.getDwFormats() & WaveOutCaps.WAVE_FORMAT_2S16) > 0));
    System.out.println(
        "44.1 kHz, mono, 8-bit: " + ((caps.getDwFormats() & WaveOutCaps.WAVE_FORMAT_4M08) > 0));
    System.out.println(
        "44.1 kHz, mono, 16-bit: " + ((caps.getDwFormats() & WaveOutCaps.WAVE_FORMAT_4M16) > 0));
    System.out.println(
        "44.1 kHz, stereo, 8-bit: " + ((caps.getDwFormats() & WaveOutCaps.WAVE_FORMAT_4S08) > 0));
    System.out.println(
        "44.1 kHz, stereo, 16-bit: " + ((caps.getDwFormats() & WaveOutCaps.WAVE_FORMAT_4S16) > 0));
    System.out.println(
        "96 kHz, mono, 8-bit: " + ((caps.getDwFormats() & WaveOutCaps.WAVE_FORMAT_96M08) > 0));
    System.out.println(
        "96 kHz, mono, 16-bit: " + ((caps.getDwFormats() & WaveOutCaps.WAVE_FORMAT_96M16) > 0));
    System.out.println(
        "96 kHz, stereo, 8-bit: " + ((caps.getDwFormats() & WaveOutCaps.WAVE_FORMAT_96S08) > 0));
    System.out.println(
        "96 kHz, stereo, 16-bit: " + ((caps.getDwFormats() & WaveOutCaps.WAVE_FORMAT_96S16) > 0));
    System.out.println("Note: sound card may have other bit-depth capability not listed here.");
    
    System.out.println();
    
    System.out.println("LR Volume: " + ((caps.getDwSupport() & WaveOutCaps.WAVECAPS_LRVOLUME) > 0));
    System.out.println("Pitch: " + ((caps.getDwSupport() & WaveOutCaps.WAVECAPS_PITCH) > 0));
    System.out.println(
        "Playback Rate: " + ((caps.getDwSupport() & WaveOutCaps.WAVECAPS_PLAYBACKRATE) > 0));
    System.out.println("Sync: " + ((caps.getDwSupport() & WaveOutCaps.WAVECAPS_SYNC) > 0));
    System.out.println("Volume: " + ((caps.getDwSupport() & WaveOutCaps.WAVECAPS_VOLUME) > 0));
    System.out.println(
        "Sample Accurate: " + ((caps.getDwSupport() & WaveOutCaps.WAVECAPS_SAMPLEACCURATE) > 0));
  }
}
