package net.joshuad.waveformjni;

import net.joshuad.waveformjni.exception.*;

public class HWaveOut {
  
  final long hWaveOutPointer;

  ////////////////////////
  // PUBLIC JAVA INTERFACE
  ////////////////////////

  public HWaveOut(int nChannels, int nSamplesPerSec, int nAvgBytesPerSec, int nBlockAlign,
      int wBitsPerSample) throws AllocatedException, BadDeviceException, NoDriverException,
      NoMemoryException, BadFormatException, SyncException {
    hWaveOutPointer =
        waveOutOpen(nChannels, nSamplesPerSec, nAvgBytesPerSec, nBlockAlign, wBitsPerSample);
    System.out
        .println("[java]WaveOut at Initialization: " + String.format("%08x", hWaveOutPointer));
  }

  public void close()
      throws InvalidHandleException, NoDriverException, NoMemoryException, StillPlayingException {
    int returnStatus = waveOutClose(hWaveOutPointer);
    if (returnStatus == MMSysErr.MMSYSERR_NODRIVER) {
      throw new NoDriverException();
    } else if (returnStatus == MMSysErr.MMSYSERR_NOMEM) {
      throw new NoMemoryException();
    } else if (returnStatus == MMSysErr.WAVERR_STILLPLAYING) {
      throw new StillPlayingException();
    }
  }

  public static int getNumDevs() {
   return waveOutGetNumDevs(); 
  }

  public int getPitch() {
    return waveOutGetPitch(hWaveOutPointer);
  }
  
  public int getPlaybackRate() {
    return waveOutGetPlaybackRate(hWaveOutPointer);
  }

  public int getPosition() throws InvalidHandleException, NoDriverException, NoMemoryException {
    return waveOutGetPosition(hWaveOutPointer);
  }

  public int getVolume()
      throws InvalidHandleException, NoDriverException, NoMemoryException, NotSupportedException {
    return waveOutGetVolume(hWaveOutPointer);
  }

  public void pause()
      throws InvalidHandleException, NoDriverException, NoMemoryException, NotSupportedException {
    waveOutPause(hWaveOutPointer);
  }

  public void prepareHeader(WaveHeader header)
      throws InvalidHandleException, NoDriverException, NoMemoryException, HeaderDeletedException {
    waveOutPrepareHeader(hWaveOutPointer, header.getPointerAddress());
  }

  public void reset()
      throws InvalidHandleException, NoDriverException, NoMemoryException, NotSupportedException {
    waveOutReset(hWaveOutPointer);
  }
  
  public void restart()
      throws InvalidHandleException, NoDriverException, NoMemoryException, NotSupportedException {
    waveOutRestart(hWaveOutPointer);
  }
  
  public void setPitch(int pitch)
      throws InvalidHandleException, NoDriverException, NoMemoryException, NotSupportedException {
    int returnStatus = waveOutSetPitch(hWaveOutPointer, pitch);
    if (returnStatus == MMSysErr.MMSYSERR_INVALHANDLE) {
      throw new InvalidHandleException();
    } else if (returnStatus == MMSysErr.MMSYSERR_NODRIVER) {
      throw new NoDriverException();
    } else if (returnStatus == MMSysErr.MMSYSERR_NOMEM) {
      throw new NoMemoryException();
    } else if (returnStatus == MMSysErr.MMSYSERR_NOTSUPPORTED) {
      throw new NotSupportedException();
    }
  }

  public void setPlaybackRate(int playbackRate)
      throws InvalidHandleException, NoDriverException, NoMemoryException, NotSupportedException {
    int returnStatus = waveOutSetPlaybackRate(hWaveOutPointer, playbackRate);
    if (returnStatus == MMSysErr.MMSYSERR_INVALHANDLE) {
      throw new InvalidHandleException();
    } else if (returnStatus == MMSysErr.MMSYSERR_NODRIVER) {
      throw new NoDriverException();
    } else if (returnStatus == MMSysErr.MMSYSERR_NOMEM) {
      throw new NoMemoryException();
    } else if (returnStatus == MMSysErr.MMSYSERR_NOTSUPPORTED) {
      throw new NotSupportedException();
    }
  }

  public void setVolume(int volume)
      throws InvalidHandleException, NoDriverException, NoMemoryException, NotSupportedException {
    waveOutSetVolume(hWaveOutPointer, volume);
  }

  public void unprepareHeader(WaveHeader header) throws InvalidHandleException, NoDriverException,
      NoMemoryException, StillPlayingException, HeaderDeletedException {
    waveOutUnprepareHeader(hWaveOutPointer, header.getPointerAddress());
  }

  public void write(WaveHeader header) throws InvalidHandleException, NoDriverException,
      NoMemoryException, UnpreparedException, HeaderDeletedException {
    waveOutWrite(hWaveOutPointer, header.getPointerAddress());
  }

  //////////////////////////////////
  // NATIVE STATIC PRIVATE INTERFACE
  //////////////////////////////////


  private static native int waveOutClose(long hWaveOutPointer);

  private static native int waveOutGetNumDevs();
  
  private static native int waveOutGetPitch(long hWaveOutPointer);
  
  private static native int waveOutGetPlaybackRate(long hWaveOutPointer);

  private static native int waveOutGetPosition(long hWaveOutPointer);
  
  private static native int waveOutGetVolume(long hWaveOutPointer);

  private static native long waveOutOpen(int nChannels, int nSamplesPerSec, int nAvgBytesPerSec,
      int nBlockAlign, int wBitsPerSample);

  private static native int waveOutPause(long hWaveOutPointer);

  private static native int waveOutPrepareHeader(long hWaveOutPointer, long headerPointer);

  private static native int waveOutReset(long hWaveOutPointer);

  private static native int waveOutRestart(long hWaveOutPointer);

  private static native int waveOutSetPitch(long hWaveOutPointer, int pitch);

  private static native int waveOutSetPlaybackRate(long hWaveOutPointer, int playbackRate);
  
  private static native int waveOutSetVolume(long hWaveOutPointer, int volume);

  private static native int waveOutUnprepareHeader(long hWaveOutPointer, long headerPointer);

  private static native int waveOutWrite(long hWaveOutPointer, long headerPointer);

  //helper functions without an immediate 1:1 relationship to the windows waveout library
  static native long createHeader(byte[] buffer, int bufferSize);

  static native void destroyHeader(long waveHeaderPointer);

  static native int getHeaderFlags(long waveHeaderPointer);
}
