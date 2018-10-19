# MNImageBrowser
一个图片浏览框架,类似微信图片浏览,手势向下滑动关闭,图片加载引擎自定义,支持长按,单击监听,切换监听,自定义任意的遮罩层，实现各种效果,支持横竖屏切换,简单方便。
[![](https://jitpack.io/v/maning0303/MNImageBrowser.svg)](https://jitpack.io/#maning0303/MNImageBrowser)

## 截图

#### gif比较慢:
![](https://github.com/maning0303/MNImageBrowser/raw/master/screenshots/mn_imagebrowser001.gif)

#### 截图:
![](https://github.com/maning0303/MNImageBrowser/raw/master/screenshots/mn_imagebrowser002.png)
![](https://github.com/maning0303/MNImageBrowser/raw/master/screenshots/mn_imagebrowser004.png)
![](https://github.com/maning0303/MNImageBrowser/raw/master/screenshots/mn_imagebrowser003.png)

##### 自定义任意的遮罩层:
![](https://github.com/maning0303/MNImageBrowser/raw/master/screenshots/mn_imagebrowser005.png)

## 如何添加

### 方式一:Gradle添加：
   #### 1.在Project的build.gradle中添加仓库地址

   ``` gradle
   	allprojects {
   		repositories {
   			...
   			maven { url "https://jitpack.io" }
   		}
   	}
   ```

   #### 2.在app目录下的build.gradle中添加依赖
   ``` gradle
   	dependencies {
   	     implementation 'com.github.maning0303:MNImageBrowser:V1.1.8'
   	}
   ```

### 方式二:(方便自定义修改)下载源码使用Module添加：imagebrowserlibrary

``` gradle
	implementation project(':imagebrowserlibrary')

```

## 代码使用(详情见Demo):
``` java

    public ImageBrowserConfig.TransformType transformType = ImageBrowserConfig.TransformType.Transform_Default;
    public ImageBrowserConfig.IndicatorType indicatorType = ImageBrowserConfig.IndicatorType.Indicator_Number;
    public ImageBrowserConfig.ScreenOrientationType screenOrientationType = ImageBrowserConfig.ScreenOrientationType.Screenorientation_Default;

    MNImageBrowser.with(context)
             //必须-当前位置
             .setCurrentPosition(position)
             //必须-图片加载用户自己去选择
             .setImageEngine(new XXXImageEngine())
             //必须（setImageList和setImageUrl二选一，会覆盖）-图片集合
             .setImageList(sourceImageList)
             //必须（setImageList和setImageUrl二选一，会覆盖）-设置单张图片
             .setImageUrl("xxx")
             //非必须-图片切换动画
             .setTransformType(transformType)
             //非必须-指示器样式（默认文本样式：两种模式）
             .setIndicatorType(indicatorType)
             //设置隐藏指示器
             .setIndicatorHide(false)
             //设置自定义遮盖层，定制自己想要的效果，当设置遮盖层后，原本的指示器会被隐藏
             .setCustomShadeView(customShadeView)
             //自定义ProgressView，不设置默认默认没有
             .setCustomProgressViewLayoutID(R.layout.layout_custom_progress_view)
             //非必须-屏幕方向：横屏，竖屏，Both（默认：横竖屏都支持）
             .setScreenOrientationType(screenOrientationType)
             //非必须-图片单击监听
             .setOnClickListener(new OnClickListener() {
                 @Override
                 public void onClick(FragmentActivity activity, ImageView view, int position, String url) {
                    //单击监听
                 }
             })
             //非必须-图片长按监听
             .setOnLongClickListener(new OnLongClickListener() {
                 @Override
                 public void onLongClick(FragmentActivity activity, ImageView imageView, int position, String url) {
                    //长按监听
                 }
             })
             //非必须-图片滑动切换监听
             .setOnPageChangeListener(new OnPageChangeListener() {
                 @Override
                 public void onPageSelected(int position) {
                    //图片滑动切换监听
                 }
             }
             //全屏模式：默认全屏模式
             .setFullScreenMode(isFulScreenMode)
             //打开
             .show(viewHolder.imageView);
             
             
     //----MNImageBrowser提供其他方法----
     /**
      * 获取当前Activity实例
      */
     MNImageBrowser.getCurrentActivity();
      
     /**
      * 手动关闭图片浏览器
      */
     MNImageBrowser.finishImageBrowser();
     
     /**
      * 获取当前ImageView
      */
     MNImageBrowser.getCurrentImageView();
     
     /**
      * 获取当前位置
      */
     MNImageBrowser.getCurrentPosition();
     
     /**
      * 获取ViewPager
      */
     MNImageBrowser.getViewPager();
     
     /**
      * 删除图片
      *
      * @param position
      */
     MNImageBrowser.removeImage(int position) {
     
     /**
      * 删除图片
      */
     MNImageBrowser.removeCurrentImage() {
    
     /**
      * 获取图片集合
      */
     MNImageBrowser.getImageList() {
    
```


## 图片加载需要实现ImageEngine：
``` java

    /**
     * author : maning
     * time   : 2018/04/10
     * desc   : 图片引擎--需要实现
     * version: 1.0
     */
    public interface ImageEngine {
    
        /**
         * 加载图片方法
         *
         * @param context      上下文
         * @param url          图片地址
         * @param imageView    ImageView
         * @param progressView 进度View
         */
        void loadImage(Context context, String url, ImageView imageView, View progressView);
    
    }

    //Picasso
    public class PicassoImageEngine implements ImageEngine {
        @Override
            public void loadImage(Context context, String url, ImageView imageView, final View progressView) {
                Picasso.with(context).load(url)
                        .placeholder(R.drawable.default_placeholder)
                        .error(R.mipmap.ic_launcher)
                        .into(imageView, new Callback() {
                            @Override
                            public void onSuccess() {
                                //隐藏进度View,必须设置setCustomProgressViewLayoutID
                                progressView.setVisibility(View.GONE);
                            }
        
                            @Override
                            public void onError() {
                                //隐藏进度View,必须设置setCustomProgressViewLayoutID
                                progressView.setVisibility(View.GONE);
                            }
                        });
            }
    }
    
    //Glide
    public class GlideImageEngine implements ImageEngine {
        @Override
        public void loadImage(Context context, String url, ImageView imageView, final View progressView) {
            Glide.with(context)
                    .load(url)
                    .asBitmap()
                    .fitCenter()
                    .placeholder(R.drawable.default_placeholder)
                    .error(R.mipmap.ic_launcher)
                    .listener(new RequestListener<String, Bitmap>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                            //隐藏进度View,必须设置setCustomProgressViewLayoutID
                            progressView.setVisibility(View.GONE);
                            return false;
                        }
        
                        @Override
                        public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            //隐藏进度View,必须设置setCustomProgressViewLayoutID
                            progressView.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(imageView);
        }
    }
    
    //其它
    public class XXXImageEngine implements ImageEngine {
        @Override
        public void loadImage(Context context, String url, ImageView imageView,View progressView) {
            //加载图片实现
        }
        
     }

```


## TransformType 切换效果提供7种效果：
``` java

    ImageBrowserConfig：

    //枚举类型：切换动画类型
    public enum TransformType {
        Transform_Default,
        Transform_DepthPage,
        Transform_RotateDown,
        Transform_RotateUp,
        Transform_ZoomIn,
        Transform_ZoomOutSlide,
        Transform_ZoomOut,
    }

```

## IndicatorType 指示器提供2种效果：
``` java

    ImageBrowserConfig：

    //枚举类型：指示器类型
    public enum IndicatorType {
        Indicator_Circle,
        Indicator_Number
    }

```


## ScreenOrientationType 屏幕方向3种效果：
``` java

    ImageBrowserConfig：

    //枚举类型：屏幕方向
    public enum ScreenOrientationType {
        //默认：横竖屏全部支持
        Screenorientation_Default,
        //竖屏
        ScreenOrientation_Portrait,
        //横屏
        Screenorientation_Landscape,
    }

```


## 内部依赖库:
``` gradle

    //图片手势缩放
    api 'com.github.chrisbanes:PhotoView:2.1.3'

```

## 详情见Demo

## 版本记录：
##### 版本 V1.1.8:
    1.新增全屏模式和非全屏模式
    2.minSdkVersion == 14
    
##### 版本 V1.1.5:
    1.添加动态设置自定义ProgressView，可以显示进度
    2.动态删除某一张图片，实现浏览过程中删除图片操作
    3.提供获取ViewPager等方便方法（查看README）

##### 版本 V1.1.3:
    1.设置隐藏指示器
    2.监图片滑动切换监听
    3.自定义任意遮罩层
    4.公开新方法：获取当前ImageView，获取ViewPager，获取Position，获取当前Activity实例，手动关闭图片浏览器
    
    
##### 版本 V1.1.2:
    1.支持单张图片，不需要传入集合
    2.支持设置屏幕方向设置：横屏，竖屏，都支持
    
    
##### 版本 V1.1.0:
    1.调用方式更加方便
    2.图片加载方式外部实现
    3.修复大图下拉和手势关闭冲突问题
    4.单个图片不显示数量指示器
    5.数量指示器两种模式（数字和圆点）
    6.添加单击和长按监听

## 推荐:
Name | Describe |
--- | --- |
[GankMM](https://github.com/maning0303/GankMM) | （Material Design & MVP & Retrofit + OKHttp & RecyclerView ...）Gank.io Android客户端：每天一张美女图片，一个视频短片，若干Android，iOS等程序干货，周一到周五每天更新，数据全部由 干货集中营 提供,持续更新。 |
[MNUpdateAPK](https://github.com/maning0303/MNUpdateAPK) | Android APK 版本更新的下载和安装,适配7.0，8.0,简单方便。 |
[MNImageBrowser](https://github.com/maning0303/MNImageBrowser) | 交互特效的图片浏览框架,微信向下滑动动态关闭 |
[MNCalendar](https://github.com/maning0303/MNCalendar) | 简单的日历控件练习，水平方向日历支持手势滑动切换，跳转月份；垂直方向日历选取区间范围。 |
[MClearEditText](https://github.com/maning0303/MClearEditText) | 带有删除功能的EditText |
[MNCrashMonitor](https://github.com/maning0303/MNCrashMonitor) | Debug监听程序崩溃日志,展示崩溃日志列表，方便自己平时调试。 |
[MNProgressHUD](https://github.com/maning0303/MNProgressHUD) | MNProgressHUD是对常用的自定义弹框封装,加载ProgressDialog,状态显示的StatusDialog和自定义Toast,支持背景颜色,圆角,边框和文字的自定义。 |
[MNXUtilsDB](https://github.com/maning0303/MNXUtilsDB) | xUtils3 数据库模块单独抽取出来，方便使用。 |
[MNVideoPlayer](https://github.com/maning0303/MNVideoPlayer) | SurfaceView + MediaPlayer 实现的视频播放器，支持横竖屏切换，手势快进快退、调节音量，亮度等。------代码简单，新手可以看一看。 |
[MNZXingCode](https://github.com/maning0303/MNZXingCode) | 快速集成二维码扫描和生成二维码 |
[MNChangeSkin](https://github.com/maning0303/MNChangeSkin) | Android夜间模式，通过Theme实现 |
[SwitcherView](https://github.com/maning0303/SwitcherView) | 垂直滚动的广告栏文字展示。 |
[MNPasswordEditText](https://github.com/maning0303/MNPasswordEditText) | 类似微信支付宝的密码输入框。 |
[MNSwipeToLoadDemo](https://github.com/maning0303/MNSwipeToLoadDemo) | 利用SwipeToLoadLayout实现的各种下拉刷新效果（饿了吗，京东，百度外卖，美团外卖，天猫下拉刷新等）。 |

