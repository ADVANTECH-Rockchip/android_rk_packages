LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)
LOCAL_SRC_FILES := $(call all-java-files-under, src)
LOCAL_MODULE := advanlib
#LOCAL_SDK_VERSION := current
LOCAL_MODULE_CLASS := SHARED_LIBRARIES
LOCAL_CERTIFICATE := platform
LOCAL_JACK_ENABLED := disabled
include $(BUILD_STATIC_JAVA_LIBRARY) 
