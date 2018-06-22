## ImageLoader介绍

> 为了方便网络加载库后期更换，现在内部通过Glide实现。更换方式为新建一个类继承ILoader，并现实其中的方法操作图片，替换为默认的加载器即可。

## API

> 下面为全局设置，通过ImageLoader操作

### 1、在图片加载前初始化图片加载器
```
public static void init(Context context) {

}
```
### 2、加载图片传入(Activity/Fragment/Context),返回一个Builder对象
```
public static ImageConfig.ConfigBuilder with(Object requestContext) {

}
```
### 3、网络验证
```
/**
 *
 * @param verify true:不加载图片
 */
public static void setNetVerify(boolean verify) {

}
```
### 4、中断图片加载
```
/**
 * @param targetContext 1、如果当前 targetContext 是 Activity，那么依附它的所有 fragments 的请求都会中止
 *                      2、如果当前 targetContext 是 Fragment，那么依附它的所有 childFragment 的请求都会中止
 *                      3、如果当前 targetContext 是 ApplicationContext，或者当前的 Fragment 处于 detached 状态，那么只有当前的 RequestManager 的请求会被中止
 */
public static void pause(Object targetContext) {

}
```
### 5、恢复图片 同上
```
public static void resume(ContextObject targetContext) {

}
```
### 6、清除磁盘缓存
```
public static void clearDiskCache() {

}
```
### 7、清除内存缓存
```
public static void clearMemory() {

}
```
### 8、onLowMemory
```
public static void onLowMemory() {

}
```
### 9、trimMemory
```
public static void trimMemory(int level) {

}
```
### 10、取消加载某个View
```
public static void clear(View view) {

}
```
### 11、设置图片加载器
```
public static void setLoader(ILoader loader) {

}
```

> 通过上面2中返回的ImageConfig.ConfigBuilder添加图片操作具体参数

### 1、图片资源

```
load(Object load)
```
### 2、磁盘缓存，默认DATA

```
diskCacheStrategy(ImageMode.DiskCache diskCacheStrategy)
```
### 3、优先策略，默认LOW

```
priority(ImageMode.LoadPriority priority)
```
### 4、占位图

```
placeholder(@DrawableRes int placeholder)
placeholder(Drawable placeholder)
```
### 5、失败占位图

```
error(@DrawableRes int error)
error(Drawable error)
```
### 6、跳过内存缓存

```
/**
 *
 * @param checkUrl skip：false
 */
skipMemoryCache(boolean skip)
```
### 7、根据url检查是否加载图片

```
/**
 *
 * @param checkUrl def：false
 */
checkUrl(boolean checkUrl)
```
### 8、设置scaleType

```
scaleType(ImageMode.ScaleMode scaleMode)
```
### 9、设置图片大小

```
size(int width, int height)
size(int size)
```
### 10、是否强制加载图片

```
/**
 *
 * @param forceDisplay def：false
 */
forceDisplay(boolean forceDisplay)
```
### 11、加载图片成功与失败回调

```
listener(LoaderListener loaderListener)
```
### 12、加载Gif图进度回调

```
progress(OnProgressListener progressListener)
```
### 13、开始加载图片

```
into(ImageView imageView)
```
### 14、开始加载图片并返回Bitmap

```
intoListener(BitmapListener bitmapListener)
```
### 15、开始预加载图片

```
preload()
```

> 下面为图片变换，消耗较大，在列表中慎用

### 16、开启图片变换，可以同时使用多种变换

```
filterType(ImageMode.FilterType... filterType)
```
### 17、圆角半径，和RoundedCorners搭配使用

```
/**
 * @param rectRoundRadius （px）
 */
rectRoundRadius(int rectRoundRadius)
```
### 18、设置圆角的模式，默认ImageMode.CornerMode.ALL(四个角)，和RoundedCorners搭配使用

```
cornerMode(ImageMode.CornerMode cornerMode)
```
### 19、裁剪大小，和Crop搭配使用

```
/**
 * @param cropWidth （px）
 * @param cropHeight （px）
 */
cropSize(int cropWidth, int cropHeight)
```
### 20、裁剪部位，默认ImageMode.CropMode.CENTER，和Crop搭配使用

```
cropMode(ImageMode.CropMode cropMode)
```
### 21、模糊半径，和Blur搭配使用

```
/**
 * @param blurRadius def：25
 */
blurRadius(int blurRadius)
```
### 22、滤镜颜色，和ColorFilter搭配使用

```
colorFilter(@ColorRes int colorFilter)
```
### 23、水墨强度，和Sepia搭配使用

```
/**
 * @param intensity def 1.0f
 */
intensity(float intensity)
```
### 24、油画，和Toon搭配使用

```
/**
 * @param toonThreshold def:.2f
 * @param toonQuantizationLevels def:10.0f
 */
toon(float toonThreshold, float toonQuantizationLevels)
```
### 25、锐化对比度，和Contrast搭配使用

```
/**
 * @param contrast def：1.0f（正常情况）   取值：0.0-4.0
 */
contrast(float contrast)
```
### 26、亮度值，和Brightness搭配使用

```
/**
 * @param brightness def：0.0f（正常情况）   取值：-1.0-1.0
 */
brightness(float brightness)
```
### 27、像素密度，和Pixelation搭配使用

```
/**
 * @param pixelation def：10.0f
 */
pixelation(float pixelation)
```
### 28、旋涡，和Swirl搭配使用

```
/**
 * @param swirlRadius 半径 0.0 to 1.0, default 0.5
 * @param swirlRadius 角度  minimum 0.0, default 1.0
 * @param swirlRadius 中心点坐标x     default 0.5
 * @param swirlRadius 中心点坐标y    default 0.5
 */
swirl(float swirlRadius, float swirlAngle, float swirlX, float swirlY)
```
### 29、映晕，和Vignette搭配使用

```
/**
 * @param vignetteX          中心点坐标x     default 0.5
 * @param vignetteY          中心点坐标y    default 0.5
 * @param vignetteColorRed   红  default 0.0
 * @param vignetteColorGreen 绿 default 0.0
 * @param vignetteColorBlue  蓝  default 0.0
 * @param vignetteStart      default 0.0
 * @param vignetteEnd        default 0.75
 */
vignette(float vignetteX, float vignetteY, float vignetteColorRed,
                                      float vignetteColorGreen, float vignetteColorBlue, float vignetteStart, float vignetteEnd)
```
### 30、图片变化，和Mask搭配使用

```
/**
 * @param maskId 图片和.9图资源
 */
maskId(@DrawableRes int maskId)
```

##  eg:
![image](https://raw.githubusercontent.com/YBill/TextScroll/master/screenshots/sr_1.gif)

