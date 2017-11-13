# MNImageBrowser
一个基本的图片浏览框架,向下滑动关闭,方便使用.
[![](https://jitpack.io/v/maning0303/MNImageBrowser.svg)](https://jitpack.io/#maning0303/MNImageBrowser)

## 截图

#### gif比较慢:
![](https://github.com/maning0303/MNImageBrowser/raw/master/screenshots/mn_imagebrowser001.gif)

#### 截图:
![](https://github.com/maning0303/MNImageBrowser/raw/master/screenshots/mn_imagebrowser002.png)
![](https://github.com/maning0303/MNImageBrowser/raw/master/screenshots/mn_imagebrowser003.png)

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
   	     compile 'com.github.maning0303:MNImageBrowser:V1.0.3'
   	}
   ```

### 方式二:(方便自定义修改)下载源码使用Module添加：imagebrowserlibrary

``` gradle
	compile project(':imagebrowserlibrary')

```

## 代码使用:
``` java

    /**
     * 打开浏览页面
     * @param context   上下文
     * @param view      点击的当前View
     * @param position  默认打开第几个
     * @param imageList 数据源ArrayList<String>
     */
    MNImageBrowser.showImageBrowser(context, imageView, position, sourceImageList);

    /**
     * 打开浏览页面，配置切换效果
     *
     * @param context   上下文
     * @param view      点击的当前View
     * @param position  默认打开第几个
     * @param viewPagerTransformType  图片滑动切换的效果：支持七种效果
     * @param imageList 数据源ArrayList<String>
     */
    MNImageBrowser.showImageBrowser(context, viewHolder.imageView, position, ViewPagerTransformType, sourceImageList);

```

## ViewPagerTransform 提供7种效果：
``` java

    MNImageBrowserActivity：

    public final static int ViewPagerTransform_Default = 0;
    public final static int ViewPagerTransform_DepthPage = 1;
    public final static int ViewPagerTransform_RotateDown = 2;
    public final static int ViewPagerTransform_RotateUp = 3;
    public final static int ViewPagerTransform_ZoomIn = 4;
    public final static int ViewPagerTransform_ZoomOutSlide = 5;
    public final static int ViewPagerTransform_ZoomOut = 6;

```


## 内部依赖库:
``` gradle

    //图片手势缩放
    compile 'com.github.chrisbanes:PhotoView:2.0.0'
    //图片加载
    compile 'com.squareup.picasso:picasso:2.5.2'

```

## 详情见Demo

## 推荐:
Name | Describe |
--- | --- |
[GankMM](https://github.com/maning0303/GankMM) | （Material Design & MVP & Retrofit + OKHttp & RecyclerView ...）Gank.io Android客户端：每天一张美女图片，一个视频短片，若干Android，iOS等程序干货，周一到周五每天更新，数据全部由 干货集中营 提供,持续更新。 |
[MNUpdateAPK](https://github.com/maning0303/MNUpdateAPK) | Android APK 版本更新的下载和安装,适配7.0,简单方便。 |
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

