#include <windows.h>
#include <mmsystem.h>
#include <iostream>
#include <fstream>
#include <jni.h>
#include <bitset>
#include "net_joshuad_waveformjni_HWaveOut.h"
#pragma comment(lib, "winmm.lib")

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

JNIEXPORT jint JNICALL Java_net_joshuad_waveformjni_HWaveOut_waveOutGetPitch
(JNIEnv *, jobject, jlong hWaveOutPointer) {
	DWORD retMe;
	waveOutGetPitch(*(HWAVEOUT*)hWaveOutPointer, &retMe);
	return retMe;
}

JNIEXPORT jint JNICALL Java_net_joshuad_waveformjni_HWaveOut_waveOutGetPlaybackRate
(JNIEnv *, jobject, jlong hWaveOutPointer) {
	DWORD retMe;
	waveOutGetPlaybackRate(*(HWAVEOUT*)hWaveOutPointer, &retMe);
	return retMe;
}

JNIEXPORT jint JNICALL Java_net_joshuad_waveformjni_HWaveOut_waveOutGetVolume
(JNIEnv *, jobject, jlong hWaveOutPointer) {
	DWORD retMe;
	waveOutGetVolume(*(HWAVEOUT*)hWaveOutPointer, &retMe);
	return retMe;
}

JNIEXPORT jlong JNICALL Java_net_joshuad_waveformjni_HWaveOut_waveOutOpen
(JNIEnv *, jclass, jint nChannels, jint nSamplesPerSec, jint nAvgBytesPerSec, jint nBlockAlign, jint wBitsPerSample) {
	HWAVEOUT* hWaveOut = new HWAVEOUT();
	WAVEFORMATEX waveFormat = { WAVE_FORMAT_PCM, (WORD)nChannels, (DWORD)nSamplesPerSec, (DWORD)nAvgBytesPerSec, (WORD)nBlockAlign, (WORD)wBitsPerSample, 0 };
	waveOutOpen(hWaveOut, WAVE_MAPPER, &waveFormat, 0, 0, CALLBACK_NULL);
	return (jlong)hWaveOut;
}

JNIEXPORT void JNICALL Java_net_joshuad_waveformjni_HWaveOut_waveOutPause
(JNIEnv *, jclass, jlong hWaveOutPointer) {
	waveOutPause(*(HWAVEOUT*)hWaveOutPointer);
}

JNIEXPORT void JNICALL Java_net_joshuad_waveformjni_HWaveOut_waveOutPrepareHeader
(JNIEnv* env, jclass jclass, jlong hWaveOutPointer, jlong headerPointer) {
	waveOutPrepareHeader(*(HWAVEOUT*)hWaveOutPointer, (WAVEHDR*)headerPointer, sizeof(WAVEHDR));
}

JNIEXPORT void JNICALL Java_net_joshuad_waveformjni_HWaveOut_waveOutReset
(JNIEnv *, jclass, jlong hWaveOutPointer) {
	waveOutReset(*(HWAVEOUT*)hWaveOutPointer);
}

JNIEXPORT void JNICALL Java_net_joshuad_waveformjni_HWaveOut_waveOutRestart
(JNIEnv *, jclass, jlong hWaveOutPointer) {
	waveOutRestart(*(HWAVEOUT*)hWaveOutPointer);
}

JNIEXPORT void JNICALL Java_net_joshuad_waveformjni_HWaveOut_waveOutSetPitch
(JNIEnv *, jclass, jlong hWaveOutPointer, jint jPitch) {
	waveOutSetPitch(*(HWAVEOUT*)hWaveOutPointer, (DWORD)jPitch);
}

JNIEXPORT void JNICALL Java_net_joshuad_waveformjni_HWaveOut_waveOutSetPlaybackRate
(JNIEnv *, jclass, jlong hWaveOutPointer, jint jRate) {
	waveOutSetPlaybackRate(*(HWAVEOUT*)hWaveOutPointer, (DWORD)jRate);
}

JNIEXPORT void JNICALL Java_net_joshuad_waveformjni_HWaveOut_waveOutSetVolume
(JNIEnv *, jclass, jlong hWaveOutPointer, jint jVolume) {
	waveOutSetVolume(*(HWAVEOUT*)hWaveOutPointer, (DWORD)jVolume);
}

JNIEXPORT void JNICALL Java_net_joshuad_waveformjni_HWaveOut_waveOutUnprepareHeader
(JNIEnv * env, jclass, jlong hWaveOutPointer, jlong headerPointer) {
	waveOutUnprepareHeader(*(HWAVEOUT*)hWaveOutPointer, (WAVEHDR*)headerPointer, sizeof(WAVEHDR));
}

JNIEXPORT void JNICALL Java_net_joshuad_waveformjni_HWaveOut_waveOutWrite
(JNIEnv * env, jclass, jlong hWaveOutPointer, jlong headerPointer) {
	waveOutWrite(*(HWAVEOUT*)hWaveOutPointer, (WAVEHDR*)headerPointer, sizeof(WAVEHDR));
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