//
// Created by xiaopeng on 16/6/22.
//

#include "com_example_xiaopeng_androiddemo_JNIClass_NdkJniUtils.h"

JNIEXPORT jstring JNICALL Java_com_example_xiaopeng_androiddemo_JNIClass_NdkJniUtils_getCLanguageString
  (JNIEnv *env, jobject obj)
{

  return (*env)->NewStringUTF(env, "This is Jni test!!!");
}