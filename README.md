# MNImageBrowser
一个基本的图片浏览框架,向下滑动关闭,方便以后使用.
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
   	     compile 'com.github.maning0303:MNImageBrowser:V1.0.1'
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

```

## 内部依赖库:
``` gradle

    //图片手势缩放
    compile 'com.github.chrisbanes:PhotoView:2.0.0'
    //图片加载
    compile 'com.squareup.picasso:picasso:2.5.2'

```

## 详情见Demo
