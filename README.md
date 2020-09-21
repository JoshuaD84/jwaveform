# jwaveform
A Java Wrapper for the [Windows Waveform Functions](https://docs.microsoft.com/en-us/windows/win32/multimedia/waveform-functions). 

The following functions are implemented: 
* waveOutClose
* waveOutGetDevCaps
* waveOutGetNumDevs
* waveOutGetPitch
* waveOutGetPlaybackRate
* waveOutGetVolume
* waveOutOpen
* waveOutPause
* waveOutPrepareHeader
* waveOutReset
* waveOutRestart
* waveOutSetPitch
* waveOutSetPlaybackRate
* waveOutSetVolume
* waveOutUnprepareHeader
* waveOutWrite

The following functions are partially implemented: 
* waveOutGetPosition (Bytes only)

The following functions are not implemented:
* auxGetDevCaps
* auxGetNumDevs
* auxGetVolume
* auxOutMessage
* auxSetVolume
* PlaySound
* sndPlaySound
* waveInAddBuffer
* waveInClose
* waveInGetDevCaps
* waveInGetErrorText
* waveInGetID
* waveInGetNumDevs
* waveInGetPosition
* waveInMessage
* waveInOpen
* waveInPrepareHeader
* waveInProc
* waveInReset
* waveInStart
* waveInStop
* waveInUnprepareHeader
* waveOutBreakLoop
* waveOutGetErrorText
* waveOutGetID
* waveOutMessage
* waveOutProc

In addition:
* not all WAVEHDR functionality is implemented
* callbacks are not implemented. 

This is an incomplete library that suits my needs for an audioplayer in java.  I may never add the missing functionality. Please feel free to fork and expand completion. It's not terribly hard to do once you've got a good work environment setup. 

