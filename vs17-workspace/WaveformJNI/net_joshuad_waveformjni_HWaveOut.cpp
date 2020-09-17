#include <windows.h>
#include <mmsystem.h>
#include <iostream>
#include <fstream>
#include <jni.h>
#include <bitset>
#include "net_joshuad_waveformjni_HWaveOut.h"
#pragma comment(lib, "winmm.lib")

void asCharArray(JNIEnv * env, jbyteArray array, char* targetBuffer) {
	int len = env->GetArrayLength(array);
	env->GetByteArrayRegion(array, 0, len, reinterpret_cast<jbyte*>(targetBuffer));
}

JNIEXPORT jint JNICALL Java_net_joshuad_waveformjni_HWaveOut_waveOutClose
(JNIEnv *, jclass, jlong hWaveOutPointer) {
	return waveOutClose(*(HWAVEOUT*)hWaveOutPointer);
}

JNIEXPORT jint JNICALL Java_net_joshuad_waveformjni_HWaveOut_waveOutGetNumDevs
(JNIEnv *, jclass) {
	return waveOutGetNumDevs();
}

JNIEXPORT jintArray JNICALL Java_net_joshuad_waveformjni_HWaveOut_waveOutGetPitch
(JNIEnv* env, jclass, jlong hWaveOutPointer) {
	DWORD pitch;
	DWORD returnCode = waveOutGetPitch(*(HWAVEOUT*)hWaveOutPointer, &pitch);
	jintArray retMe;
	retMe = env->NewIntArray(2);
	jint fill[2];
	fill[0] = pitch;
	fill[1] = returnCode;
	env->SetIntArrayRegion(retMe, 0, 2, fill);
	return retMe;
}

JNIEXPORT jintArray JNICALL Java_net_joshuad_waveformjni_HWaveOut_waveOutGetPlaybackRate
(JNIEnv* env, jclass, jlong hWaveOutPointer) {
	DWORD playbackRate;
	DWORD returnCode = waveOutGetPlaybackRate(*(HWAVEOUT*)hWaveOutPointer, &playbackRate);
	jintArray retMe;
	retMe = env->NewIntArray(2);
	jint fill[2];
	fill[0] = playbackRate;
	fill[1] = returnCode;
	env->SetIntArrayRegion(retMe, 0, 2, fill);
	return retMe;
}

JNIEXPORT jlongArray JNICALL Java_net_joshuad_waveformjni_HWaveOut_waveOutGetPositionBytes
(JNIEnv* env, jclass, jlong hWaveOutPointer) {
	MMTIME cMMTime = MMTIME{ TIME_BYTES, 0 };
	DWORD returnCode = waveOutGetPosition(*(HWAVEOUT*)hWaveOutPointer, &cMMTime, sizeof(cMMTime));
	long value;
	if (cMMTime.wType == TIME_BYTES) {
		value = cMMTime.u.cb;
	} else {
		value = -1;
	}
	jlongArray retMe = env->NewLongArray(2);
	jlong fill[2];
	fill[0] = value;
	fill[1] = returnCode;
	env->SetLongArrayRegion(retMe, 0, 2, fill);
	return retMe;
}

JNIEXPORT jintArray JNICALL Java_net_joshuad_waveformjni_HWaveOut_waveOutGetVolume
(JNIEnv* env, jclass, jlong hWaveOutPointer) {
	DWORD volume;
	DWORD returnCode = waveOutGetVolume(*(HWAVEOUT*)hWaveOutPointer, &volume);
	jintArray retMe = env->NewIntArray(2);
	jint fill[2];
	fill[0] = volume;
	fill[1] = returnCode;
	env->SetIntArrayRegion(retMe, 0, 2, fill);
	return retMe;
}

JNIEXPORT jlongArray JNICALL Java_net_joshuad_waveformjni_HWaveOut_waveOutOpen
(JNIEnv* env, jclass, jint nChannels, jint nSamplesPerSec, jint nAvgBytesPerSec, jint nBlockAlign, jint wBitsPerSample) {
	HWAVEOUT* hWaveOut = new HWAVEOUT();
	WAVEFORMATEX waveFormat = { WAVE_FORMAT_PCM, (WORD)nChannels, (DWORD)nSamplesPerSec, (DWORD)nAvgBytesPerSec, (WORD)nBlockAlign, (WORD)wBitsPerSample, 0 };
	int returnCode = waveOutOpen(hWaveOut, WAVE_MAPPER, &waveFormat, 0, 0, CALLBACK_NULL);
	//return (jlong)hWaveOut;
	jlongArray retMe;
	retMe = env->NewLongArray(2);
	jlong fill[2];
	fill[0] = (jlong)hWaveOut;
	fill[1] = returnCode;
	env->SetLongArrayRegion(retMe, 0, 2, fill);
	return retMe;
}

JNIEXPORT jint JNICALL Java_net_joshuad_waveformjni_HWaveOut_waveOutPause
(JNIEnv *, jclass, jlong hWaveOutPointer) {
	return waveOutPause(*(HWAVEOUT*)hWaveOutPointer);
}

JNIEXPORT jint JNICALL Java_net_joshuad_waveformjni_HWaveOut_waveOutPrepareHeader
(JNIEnv* env, jclass jclass, jlong hWaveOutPointer, jlong headerPointer) {
	return waveOutPrepareHeader(*(HWAVEOUT*)hWaveOutPointer, (WAVEHDR*)headerPointer, sizeof(WAVEHDR));
}

JNIEXPORT jint JNICALL Java_net_joshuad_waveformjni_HWaveOut_waveOutReset
(JNIEnv *, jclass, jlong hWaveOutPointer) {
	return waveOutReset(*(HWAVEOUT*)hWaveOutPointer);
}

JNIEXPORT jint JNICALL Java_net_joshuad_waveformjni_HWaveOut_waveOutRestart
(JNIEnv *, jclass, jlong hWaveOutPointer) {
	return waveOutRestart(*(HWAVEOUT*)hWaveOutPointer);
}

JNIEXPORT jint JNICALL Java_net_joshuad_waveformjni_HWaveOut_waveOutSetPitch
(JNIEnv *, jclass, jlong hWaveOutPointer, jint jPitch) {
	return waveOutSetPitch(*(HWAVEOUT*)hWaveOutPointer, (DWORD)jPitch);
}

JNIEXPORT jint JNICALL Java_net_joshuad_waveformjni_HWaveOut_waveOutSetPlaybackRate
(JNIEnv *, jclass, jlong hWaveOutPointer, jint jRate) {
	return waveOutSetPlaybackRate(*(HWAVEOUT*)hWaveOutPointer, (DWORD)jRate);
}

JNIEXPORT jint JNICALL Java_net_joshuad_waveformjni_HWaveOut_waveOutSetVolume
(JNIEnv *, jclass, jlong hWaveOutPointer, jint jVolume) {
	return waveOutSetVolume(*(HWAVEOUT*)hWaveOutPointer, (DWORD)jVolume);
}

JNIEXPORT jint JNICALL Java_net_joshuad_waveformjni_HWaveOut_waveOutUnprepareHeader
(JNIEnv * env, jclass, jlong hWaveOutPointer, jlong headerPointer) {
	return waveOutUnprepareHeader(*(HWAVEOUT*)hWaveOutPointer, (WAVEHDR*)headerPointer, sizeof(WAVEHDR));
}

JNIEXPORT jint JNICALL Java_net_joshuad_waveformjni_HWaveOut_waveOutWrite
(JNIEnv * env, jclass, jlong hWaveOutPointer, jlong headerPointer) {
	return waveOutWrite(*(HWAVEOUT*)hWaveOutPointer, (WAVEHDR*)headerPointer, sizeof(WAVEHDR));
}

JNIEXPORT jlong JNICALL Java_net_joshuad_waveformjni_HWaveOut_createHeader
(JNIEnv * env, jclass jclass, jbyteArray jbuffer, jint jBufferLength) {
	char* buffer = new char[jBufferLength];
	asCharArray(env, jbuffer, buffer);
	WAVEHDR* headerOut = new WAVEHDR{ buffer, (DWORD)jBufferLength, 0, 0, 0, 0, 0, 0 };
	return (jlong)headerOut;
}

JNIEXPORT void JNICALL Java_net_joshuad_waveformjni_HWaveOut_destroyHeader
(JNIEnv *env , jclass jclass, jlong jHeaderPointer) {
	delete[]((WAVEHDR*)jHeaderPointer)->lpData;
	delete (WAVEHDR*)jHeaderPointer;	
}

JNIEXPORT jint JNICALL Java_net_joshuad_waveformjni_HWaveOut_getHeaderFlags
(JNIEnv *env, jclass, jlong jHeaderPointer) {
	return ((WAVEHDR*)jHeaderPointer)->dwFlags;
}