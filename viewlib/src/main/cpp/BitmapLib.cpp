// c 代码
#include <jni.h>
#include <android/bitmap.h>
#include <android/log.h>

#define TAG "bitmaplibtag"
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, TAG ,__VA_ARGS__)

extern "C"
JNIEXPORT void JNICALL
Java_com_perdev_viewlib_jnipackage_BitmapFilter_blackWhite(
    JNIEnv *env, jobject obj, jobject source, jobject target
) {
  LOGE(" BitmapFilter_blackWhite  start  ");
  int result;
  // 获取源Bitmap相关信息：宽、高等
  AndroidBitmapInfo sourceInfo;
  result = AndroidBitmap_getInfo(env, source, &sourceInfo);
  LOGE("res  get bitmap info  : %d", result);
  if (result < 0) {
    LOGE("get bitmap info error : %d", result);
    return;
  }
  // 获取源Bitmap像素数据 这里用的是32位的int类型 argb每个8位
  uint32_t *sourceData;
  result = AndroidBitmap_lockPixels(env, source, (void **) &sourceData);
  if (result < 0) {
    LOGE("bitmap lock pixels error : %d", result);
    return;
  }
  // 锁定Bitmap
  // 并获取目标Bitmap像素数据
  // 注意：传进来的Bitmap只是一张空的Bitmap
  uint32_t *targetData;
  result = AndroidBitmap_lockPixels(env, target, (void **) &targetData);
  if (result < 0) {
    LOGE("bitmap lock pixels error : %d", result);
    return;
  }
  // 遍历各个像素点
  int color;
  int alpha = 0xff << 24;
  int red, green, blue;
  int width = sourceInfo.width;
  int height = sourceInfo.height;
  int w, h;
  for (h = 0; h < height; h++) {
    for (w = 0; w < width; w++) {
      color = sourceData[h * width + w];
      red = (color & 0x00ff0000) >> 16;
      green = (color & 0x0000ff00) >> 8;
      blue = color & 0x000000ff;
      // rgb颜色相同就是黑白图片了 取平均值只是一个方案
      color = (red + green + blue) / 3;
      targetData[h * width + w] = alpha | (color << 16) | (color << 8) | color;
    }
  }
  AndroidBitmap_unlockPixels(env, source);
  AndroidBitmap_unlockPixels(env, target);
}






extern "C"
JNIEXPORT void JNICALL
Java_com_perdev_viewlib_jnipackage_BitmapFilter_warm(
    JNIEnv *env, jobject obj, jobject source, jobject target
) {
  LOGE(" BitmapFilter_blackWhite  start  ");
  int result;
  // 获取源Bitmap相关信息：宽、高等
  AndroidBitmapInfo sourceInfo;
  result = AndroidBitmap_getInfo(env, source, &sourceInfo);
  LOGE("res  get bitmap info  : %d", result);
  if (result < 0) {
    LOGE("get bitmap info error : %d", result);
    return;
  }
  // 获取源Bitmap像素数据 这里用的是32位的int类型 argb每个8位
  uint32_t *sourceData;
  result = AndroidBitmap_lockPixels(env, source, (void **) &sourceData);
  if (result < 0) {
    LOGE("bitmap lock pixels error : %d", result);
    return;
  }
  // 锁定Bitmap
  // 并获取目标Bitmap像素数据
  // 注意：传进来的Bitmap只是一张空的Bitmap
  uint32_t *targetData;
  result = AndroidBitmap_lockPixels(env, target, (void **) &targetData);
  if (result < 0) {
    LOGE("bitmap lock pixels error : %d", result);
    return;
  }
  // 遍历各个像素点
  int color;
  int alpha = 0xff << 24;
  int red, green, blue;
  int width = sourceInfo.width;
  int height = sourceInfo.height;
  int w, h;
  for (h = 0; h < height; h++) {
    for (w = 0; w < width; w++) {
      color = sourceData[h*width+w];
      red = (color & 0x00ff0000) >> 16;
      green = (color & 0x0000ff00) >> 8;
      blue = color & 0x000000ff;
      blue += 60;
      if (blue > 255) {
        blue = 255;
      }
      color = (red + green + blue) / 3;
      targetData[h*width+w] = alpha | (red << 16) | (green << 8) | blue;
    }
  }
  AndroidBitmap_unlockPixels(env, source);
  AndroidBitmap_unlockPixels(env, target);
}





extern "C"
JNIEXPORT void JNICALL
Java_com_perdev_viewlib_jnipackage_BitmapFilter_cold(
    JNIEnv *env, jobject obj, jobject source, jobject target
) {
  LOGE(" BitmapFilter_blackWhite  start  ");
  int result;
  // 获取源Bitmap相关信息：宽、高等
  AndroidBitmapInfo sourceInfo;
  result = AndroidBitmap_getInfo(env, source, &sourceInfo);
  LOGE("res  get bitmap info  : %d", result);
  if (result < 0) {
    LOGE("get bitmap info error : %d", result);
    return;
  }
  // 获取源Bitmap像素数据 这里用的是32位的int类型 argb每个8位
  uint32_t *sourceData;
  result = AndroidBitmap_lockPixels(env, source, (void **) &sourceData);
  if (result < 0) {
    LOGE("bitmap lock pixels error : %d", result);
    return;
  }
  // 锁定Bitmap
  // 并获取目标Bitmap像素数据
  // 注意：传进来的Bitmap只是一张空的Bitmap
  uint32_t *targetData;
  result = AndroidBitmap_lockPixels(env, target, (void **) &targetData);
  if (result < 0) {
    LOGE("bitmap lock pixels error : %d", result);
    return;
  }
  // 遍历各个像素点
  int color;
  int alpha = 0xff << 24;
  int red, green, blue;
  int width = sourceInfo.width;
  int height = sourceInfo.height;
  int w, h;
  for (h = 0; h < height; h++) {
    for (w = 0; w < width; w++) {
      color = sourceData[h*width+w];
      blue = (color & 0x00ff0000) >> 16;
      green = (color & 0x0000ff00) >> 8;
      red = color & 0x000000ff;
      blue += 80;
      if (blue > 255) {
        blue = 255;
      }
      color = (red + green + blue) / 3;
      targetData[h*width+w] = alpha | (blue << 16) | (green << 8) | red;
    }
  }
  AndroidBitmap_unlockPixels(env, source);
  AndroidBitmap_unlockPixels(env, target);
}
