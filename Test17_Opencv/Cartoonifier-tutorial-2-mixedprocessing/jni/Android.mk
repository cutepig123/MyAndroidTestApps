LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

include ../OpenCV-2.4.8-android-sdk/sdk/native/jni/OpenCV.mk

LOCAL_MODULE    := mixed_sample
LOCAL_SRC_FILES := jni_part.cpp
LOCAL_SRC_FILES += cartoon.cpp
LOCAL_SRC_FILES += ImageUtils_0.7.cpp
LOCAL_SRC_FILES += qx_basic.cpp
LOCAL_SRC_FILES += qx_recursive_bilateral_filter.cpp


LOCAL_LDLIBS +=  -llog -ldl

include $(BUILD_SHARED_LIBRARY)
