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
    long[] retValue =
        waveOutOpen(nChannels, nSamplesPerSec, nAvgBytesPerSec, nBlockAlign, wBitsPerSample);
    hWaveOutPointer = retValue[0];
    long returnStatus = retValue[1];
    if (returnStatus == MMSysErr.MMSYSERR_ALLOCATED) {
      throw new AllocatedException();
    } else if (returnStatus == MMSysErr.MMSYSERR_BADDEVICEID) {
      throw new BadDeviceException();
    } else if (returnStatus == MMSysErr.MMSYSERR_NODRIVER) {
      throw new NoDriverException();
    } else if (returnStatus == MMSysErr.MMSYSERR_NOMEM) {
      throw new NoMemoryException();
    } else if (returnStatus == MMSysErr.WAVERR_BADFORMAT) {
      throw new BadFormatException();
    } else if (returnStatus == MMSysErr.WAVERR_SYNC) {
      throw new SyncException();
    }
  }

  public void close()
      throws InvalidHandleException, NoDriverException, NoMemoryException, StillPlayingException {
    int returnStatus = waveOutClose(hWaveOutPointer);
    if (returnStatus == MMSysErr.MMSYSERR_INVALHANDLE) {
      throw new InvalidHandleException();
    } else if (returnStatus == MMSysErr.MMSYSERR_NODRIVER) {
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

  public int getPitch() 
      throws InvalidHandleException, NoDriverException, NoMemoryException, NotSupportedException {
    int[] retValue = waveOutGetPitch(hWaveOutPointer);
    int returnStatus = retValue[1];
    if (returnStatus == MMSysErr.MMSYSERR_INVALHANDLE) {
      throw new InvalidHandleException();
    } else if (returnStatus == MMSysErr.MMSYSERR_NODRIVER) {
      throw new NoDriverException();
    } else if (returnStatus == MMSysErr.MMSYSERR_NOMEM) {
      throw new NoMemoryException();
    } else if (returnStatus == MMSysErr.MMSYSERR_NOTSUPPORTED) {
      throw new NotSupportedException();
    }
    return retValue[0];
  }

  public int getPlaybackRate() 
      throws InvalidHandleException, NoDriverException, NoMemoryException, NotSupportedException {
    int[] retValue = waveOutGetPlaybackRate(hWaveOutPointer);
    int returnStatus = retValue[1];
    if (returnStatus == MMSysErr.MMSYSERR_INVALHANDLE) {
      throw new InvalidHandleException();
    } else if (returnStatus == MMSysErr.MMSYSERR_NODRIVER) {
      throw new NoDriverException();
    } else if (returnStatus == MMSysErr.MMSYSERR_NOMEM) {
      throw new NoMemoryException();
    } else if (returnStatus == MMSysErr.MMSYSERR_NOTSUPPORTED) {
      throw new NotSupportedException();
    }
    return retValue[0];
  }

  public long getPosition()
      throws InvalidHandleException, NoDriverException, NoMemoryException, NotSupportedException {
    long[] retValue = waveOutGetPositionBytes(hWaveOutPointer);
    int returnStatus = (int) retValue[1];
    if (returnStatus == MMSysErr.MMSYSERR_INVALHANDLE) {
      throw new InvalidHandleException();
    } else if (returnStatus == MMSysErr.MMSYSERR_NODRIVER) {
      throw new NoDriverException();
    } else if (returnStatus == MMSysErr.MMSYSERR_NOMEM) {
      throw new NoMemoryException();
    } else if (retValue[0] == -1) {
      // This happens when we ask for TIME_BYTES, but get something else instead
      throw new NotSupportedException();
    }
    return retValue[0];
  }
  
  public int getVolume()
      throws InvalidHandleException, NoDriverException, NoMemoryException, NotSupportedException {
    int[] retValue = waveOutGetVolume(hWaveOutPointer);
    int returnStatus = retValue[1];
    if (returnStatus == MMSysErr.MMSYSERR_INVALHANDLE) {
      throw new InvalidHandleException();
    } else if (returnStatus == MMSysErr.MMSYSERR_NODRIVER) {
      throw new NoDriverException();
    } else if (returnStatus == MMSysErr.MMSYSERR_NOMEM) {
      throw new NoMemoryException();
    } else if (returnStatus == MMSysErr.MMSYSERR_NOTSUPPORTED) {
      throw new NotSupportedException();
    }
    return retValue[0];
  }

  public void pause()
      throws InvalidHandleException, NoDriverException, NoMemoryException, NotSupportedException {
    int returnStatus = waveOutPause(hWaveOutPointer);
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

  public void prepareHeader(WaveHeader header)
      throws InvalidHandleException, NoDriverException, NoMemoryException, HeaderDeletedException {
    int returnStatus = waveOutPrepareHeader(hWaveOutPointer, header.getPointerAddress());
    if (returnStatus == MMSysErr.MMSYSERR_INVALHANDLE) {
      throw new InvalidHandleException();
    } else if (returnStatus == MMSysErr.MMSYSERR_NODRIVER) {
      throw new NoDriverException();
    } else if (returnStatus == MMSysErr.MMSYSERR_NOMEM) {
      throw new NoMemoryException();
    }
  }

  public void reset()
      throws InvalidHandleException, NoDriverException, NoMemoryException, NotSupportedException {
    int returnStatus = waveOutReset(hWaveOutPointer);
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

  public void restart()
      throws InvalidHandleException, NoDriverException, NoMemoryException, NotSupportedException {
    int returnStatus = waveOutRestart(hWaveOutPointer);
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
    int returnStatus = waveOutSetVolume(hWaveOutPointer, volume);
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

  public void unprepareHeader(WaveHeader header) throws InvalidHandleException, NoDriverException,
      NoMemoryException, StillPlayingException, HeaderDeletedException {
    int returnStatus = waveOutUnprepareHeader(hWaveOutPointer, header.getPointerAddress());
    if (returnStatus == MMSysErr.MMSYSERR_INVALHANDLE) {
      throw new InvalidHandleException();
    } else if (returnStatus == MMSysErr.MMSYSERR_NODRIVER) {
      throw new NoDriverException();
    } else if (returnStatus == MMSysErr.MMSYSERR_NOMEM) {
      throw new NoMemoryException();
    } else if (returnStatus == MMSysErr.WAVERR_STILLPLAYING) {
      throw new StillPlayingException();
    }
  }

  public void write(WaveHeader header) throws InvalidHandleException, NoDriverException,
      NoMemoryException, UnpreparedException, HeaderDeletedException {
    int returnStatus = waveOutWrite(hWaveOutPointer, header.getPointerAddress());
    if (returnStatus == MMSysErr.MMSYSERR_INVALHANDLE) {
      throw new InvalidHandleException();
    } else if (returnStatus == MMSysErr.MMSYSERR_NODRIVER) {
      throw new NoDriverException();
    } else if (returnStatus == MMSysErr.MMSYSERR_NOMEM) {
      throw new NoMemoryException();
    } else if (returnStatus == MMSysErr.WAVERR_UNPREPARED) {
      throw new UnpreparedException();
    }
  }

  //////////////////////////////////
  // NATIVE STATIC PRIVATE INTERFACE
  //////////////////////////////////


  private static native int waveOutClose(long hWaveOutPointer);

  private static native int waveOutGetNumDevs();

  private static native int[] waveOutGetPitch(long hWaveOutPointer);

  private static native int[] waveOutGetPlaybackRate(long hWaveOutPointer);
  
  private static native long[] waveOutGetPositionBytes(long hWaveOutPointer);

  private static native int[] waveOutGetVolume(long hWaveOutPointer);
  
  private static native long[] waveOutOpen(int nChannels, int nSamplesPerSec, int nAvgBytesPerSec,
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

  // helper functions without an immediate 1:1 relationship to the windows waveout library
  static native long createHeader(byte[] buffer, int bufferSize);

  static native void destroyHeader(long waveHeaderPointer);

  static native int getHeaderFlags(long waveHeaderPointer);
}
