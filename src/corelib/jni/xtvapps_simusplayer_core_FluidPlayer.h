/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class xtvapps_simusplayer_core_FluidPlayer */

#ifndef _Included_xtvapps_simusplayer_core_FluidPlayer
#define _Included_xtvapps_simusplayer_core_FluidPlayer
#ifdef __cplusplus
extern "C" {
#endif
#undef xtvapps_simusplayer_core_FluidPlayer_SLEEP_TIME
#define xtvapps_simusplayer_core_FluidPlayer_SLEEP_TIME 10L
/*
 * Class:     xtvapps_simusplayer_core_FluidPlayer
 * Method:    fluidInit
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_xtvapps_simusplayer_core_FluidPlayer_fluidInit
  (JNIEnv *, jobject, jint);

/*
 * Class:     xtvapps_simusplayer_core_FluidPlayer
 * Method:    fluidLoadSoundFontFile
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_xtvapps_simusplayer_core_FluidPlayer_fluidLoadSoundFontFile
  (JNIEnv *, jobject, jstring);

/*
 * Class:     xtvapps_simusplayer_core_FluidPlayer
 * Method:    fluidRelease
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_xtvapps_simusplayer_core_FluidPlayer_fluidRelease
  (JNIEnv *, jobject);

/*
 * Class:     xtvapps_simusplayer_core_FluidPlayer
 * Method:    fluidFillBuffer
 * Signature: ([B)I
 */
JNIEXPORT jint JNICALL Java_xtvapps_simusplayer_core_FluidPlayer_fluidFillBuffer
  (JNIEnv *, jobject, jbyteArray);

/*
 * Class:     xtvapps_simusplayer_core_FluidPlayer
 * Method:    fluidSendEventNote
 * Signature: (IIII)V
 */
JNIEXPORT void JNICALL Java_xtvapps_simusplayer_core_FluidPlayer_fluidSendEventNote
  (JNIEnv *, jclass, jint, jint, jint, jint);

/*
 * Class:     xtvapps_simusplayer_core_FluidPlayer
 * Method:    fluidSendEventController
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_xtvapps_simusplayer_core_FluidPlayer_fluidSendEventController
  (JNIEnv *, jclass, jint, jint, jint);

/*
 * Class:     xtvapps_simusplayer_core_FluidPlayer
 * Method:    fluidSendEventChange
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_xtvapps_simusplayer_core_FluidPlayer_fluidSendEventChange
  (JNIEnv *, jclass, jint, jint, jint);

/*
 * Class:     xtvapps_simusplayer_core_FluidPlayer
 * Method:    fluidSendEventPitchBend
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_xtvapps_simusplayer_core_FluidPlayer_fluidSendEventPitchBend
  (JNIEnv *, jclass, jint, jint);

/*
 * Class:     xtvapps_simusplayer_core_FluidPlayer
 * Method:    fluidSendEventSysex
 * Signature: ([I)V
 */
JNIEXPORT void JNICALL Java_xtvapps_simusplayer_core_FluidPlayer_fluidSendEventSysex
  (JNIEnv *, jclass, jintArray);

#ifdef __cplusplus
}
#endif
#endif
