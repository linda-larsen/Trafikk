//
// Created by Linda on 12/17/2021.
//

#include <jni.h>
#include <string>
#include <opencv2/core.hpp>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_myapplication_MainActivity_validate(JNIEnv *env, jobject thiz, jlong mad_addr_gr,jlong mat_addr_rgba) {
    cv::Rect();
    std::string hello2="hello from validate";
    return env->NewStringUTF(hello2.c_str());
    // TODO: implement validate()
}
