/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class xtvapps_simusplayer_core_ModPlayer */

#ifndef _Included_xtvapps_simusplayer_core_ModPlayer
#define _Included_xtvapps_simusplayer_core_ModPlayer
#ifdef __cplusplus
extern "C" {
#endif
#undef xtvapps_simusplayer_core_ModPlayer_MIN_PRIORITY
#define xtvapps_simusplayer_core_ModPlayer_MIN_PRIORITY 1L
#undef xtvapps_simusplayer_core_ModPlayer_NORM_PRIORITY
#define xtvapps_simusplayer_core_ModPlayer_NORM_PRIORITY 5L
#undef xtvapps_simusplayer_core_ModPlayer_MAX_PRIORITY
#define xtvapps_simusplayer_core_ModPlayer_MAX_PRIORITY 10L
/*
 * Class:     xtvapps_simusplayer_core_ModPlayer
 * Method:    xmpInit
 * Signature: (Ljava/lang/String;I)Z
 */
JNIEXPORT jboolean JNICALL Java_xtvapps_simusplayer_core_ModPlayer_xmpInit
  (JNIEnv *, jobject, jstring, jint);

/*
 * Class:     xtvapps_simusplayer_core_ModPlayer
 * Method:    xmpRelease
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_xtvapps_simusplayer_core_ModPlayer_xmpRelease
  (JNIEnv *, jobject);

/*
 * Class:     xtvapps_simusplayer_core_ModPlayer
 * Method:    xmpFillBuffer
 * Signature: ([BI)I
 */
JNIEXPORT jint JNICALL Java_xtvapps_simusplayer_core_ModPlayer_xmpFillBuffer
  (JNIEnv *, jobject, jbyteArray, jint);

/*
 * Class:     xtvapps_simusplayer_core_ModPlayer
 * Method:    xmpGetModuleName
 * Signature: (Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_xtvapps_simusplayer_core_ModPlayer_xmpGetModuleName
  (JNIEnv *, jclass, jstring);

/*
 * Class:     xtvapps_simusplayer_core_ModPlayer
 * Method:    xmpSetVolume
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_xtvapps_simusplayer_core_ModPlayer_xmpSetVolume
  (JNIEnv *, jobject, jint);

#ifdef __cplusplus
}
#endif
#endif
