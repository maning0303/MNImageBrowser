package com.maning.imagebrowserlibrary;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.maning.imagebrowserlibrary.listeners.OnActivityLifeListener;
import com.maning.imagebrowserlibrary.listeners.OnClickListener;
import com.maning.imagebrowserlibrary.listeners.OnLongClickListener;
import com.maning.imagebrowserlibrary.listeners.OnPageChangeListener;
import com.maning.imagebrowserlibrary.model.ImageBrowserConfig;
import com.maning.imagebrowserlibrary.transforms.DefaultTransformer;
import com.maning.imagebrowserlibrary.transforms.DepthPageTransformer;
import com.maning.imagebrowserlibrary.transforms.RotateDownTransformer;
import com.maning.imagebrowserlibrary.transforms.RotateUpTransformer;
import com.maning.imagebrowserlibrary.transforms.ZoomInTransformer;
import com.maning.imagebrowserlibrary.transforms.ZoomOutSlideTransformer;
import com.maning.imagebrowserlibrary.transforms.ZoomOutTransformer;
import com.maning.imagebrowserlibrary.utils.immersionbar.BarHide;
import com.maning.imagebrowserlibrary.utils.immersionbar.ImmersionBar;
import com.maning.imagebrowserlibrary.view.MNGestureView;
import com.maning.imagebrowserlibrary.view.MNViewPager;
import com.maning.imagebrowserlibrary.view.indicator.CircleIndicator;
import com.maning.imagebrowserlibrary.view.photoview.PhotoView;

import java.lang.ref.SoftReference;
import java.util.ArrayList;


/**
 * 图片浏览的页面
 */
public class MNImageBrowserActivity extends AppCompatActivity {
    //用来保存当前Activity
    private static SoftReference<MNImageBrowserActivity> sActivityRef;
    //相关配置信息
    public static ImageBrowserConfig imageBrowserConfig;

    private Context context;

    private MNGestureView mnGestureView;
    private MNViewPager viewPagerBrowser;
    private RelativeLayout rl_black_bg;
    private RelativeLayout rl_indicator;
    private TextView numberIndicator;
    private CircleIndicator circleIndicator;
    private LinearLayout ll_custom_view;

    //图片地址
    private ArrayList<String> imageUrlList;
    //当前位置
    private int currentPosition;
    //当前切换的动画
    private ImageBrowserConfig.TransformType transformType;
    //切换器
    private ImageBrowserConfig.IndicatorType indicatorType;
    //图片加载引擎
    public ImageEngine imageEngine;
    //监听
    public OnLongClickListener onLongClickListener;
    public OnClickListener onClickListener;
    public OnActivityLifeListener onActivityLifeListener;
    public OnPageChangeListener onPageChangeListener;
    private MyAdapter imageBrowserAdapter;
    private ImageBrowserConfig.ScreenOrientationType screenOrientationType;
    //图片加载进度View的布局ID
    private int progressViewLayoutId = 0;
    //自定义图片显示的布局ID
    private int customImageViewLayoutId = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_mnimage_browser);
            sActivityRef = new SoftReference<>(this);
            context = this;
            getImageBrowserConfig();
            initBar();
            initViews();
            initData();
            initViewPager();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(">>MNImageBrowser>>", "MNImageBrowserActivity-onCreate异常：" + e.toString());
            finishBrowser();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (onActivityLifeListener != null) {
            onActivityLifeListener.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (onActivityLifeListener != null) {
            onActivityLifeListener.onPause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (onActivityLifeListener != null) {
            onActivityLifeListener.onDestory();
        }
    }

    private void initBar() {
        try {
            //判断是否全屏模式，隐藏状态栏
            if (getImageBrowserConfig().isFullScreenMode()) {
                ImmersionBar.with(this).statusBarColor(R.color.mn_ib_black).navigationBarColor(R.color.mn_ib_black).hideBar(BarHide.FLAG_HIDE_BAR).init();
            } else {
                ImmersionBar.with(this).statusBarDarkFont(getImageBrowserConfig().isStatusBarDarkFont()).statusBarColor(R.color.mn_ib_trans).navigationBarColor(R.color.mn_ib_black).init();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(">>MNImageBrowser>>", "MNImageBrowserActivity-initBar异常：" + e.toString());
        }
    }

    private void initViews() {
        viewPagerBrowser = (MNViewPager) findViewById(R.id.viewPagerBrowser);
        mnGestureView = (MNGestureView) findViewById(R.id.mnGestureView);
        rl_black_bg = (RelativeLayout) findViewById(R.id.rl_black_bg);
        rl_indicator = (RelativeLayout) findViewById(R.id.rl_indicator);
        circleIndicator = (CircleIndicator) findViewById(R.id.circleIndicator);
        numberIndicator = (TextView) findViewById(R.id.numberIndicator);
        ll_custom_view = (LinearLayout) findViewById(R.id.ll_custom_view);
        circleIndicator.setVisibility(View.GONE);
        numberIndicator.setVisibility(View.GONE);
        ll_custom_view.setVisibility(View.GONE);
    }

    private ImageBrowserConfig getImageBrowserConfig() {
        if (imageBrowserConfig == null) {
            imageBrowserConfig = new ImageBrowserConfig();
        }
        return imageBrowserConfig;
    }

    private void initData() {
        imageUrlList = getImageBrowserConfig().getImageList();
        currentPosition = getImageBrowserConfig().getPosition();
        transformType = getImageBrowserConfig().getTransformType();
        imageEngine = getImageBrowserConfig().getImageEngine();
        onClickListener = getImageBrowserConfig().getOnClickListener();
        onLongClickListener = getImageBrowserConfig().getOnLongClickListener();
        indicatorType = getImageBrowserConfig().getIndicatorType();
        screenOrientationType = getImageBrowserConfig().getScreenOrientationType();
        onPageChangeListener = getImageBrowserConfig().getOnPageChangeListener();
        onActivityLifeListener = getImageBrowserConfig().getOnActivityLifeListener();
        if (onActivityLifeListener != null) {
            onActivityLifeListener.onCreate();
        }
        if (imageUrlList == null) {
            imageUrlList = new ArrayList<>();
            //直接关闭
            finishActivity();
            return;
        }

        if (imageUrlList.size() <= 1) {
            rl_indicator.setVisibility(View.GONE);
        } else {
            rl_indicator.setVisibility(View.VISIBLE);

            if (getImageBrowserConfig().isIndicatorHide()) {
                rl_indicator.setVisibility(View.GONE);
            } else {
                rl_indicator.setVisibility(View.VISIBLE);
            }
            if (indicatorType == ImageBrowserConfig.IndicatorType.Indicator_Number) {
                numberIndicator.setVisibility(View.VISIBLE);
                numberIndicator.setText((currentPosition + 1) + "/" + imageUrlList.size());
            } else {
                circleIndicator.setVisibility(View.VISIBLE);
            }
        }

        //自定义View
        View customShadeView = getImageBrowserConfig().getCustomShadeView();
        if (customShadeView != null) {
            ll_custom_view.setVisibility(View.VISIBLE);
            ll_custom_view.removeAllViews();
            ll_custom_view.addView(customShadeView);
            rl_indicator.setVisibility(View.GONE);
        }

        //横竖屏梳理
        if (screenOrientationType == ImageBrowserConfig.ScreenOrientationType.ScreenOrientation_Portrait) {
            //设置横竖屏
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else if (screenOrientationType == ImageBrowserConfig.ScreenOrientationType.Screenorientation_Landscape) {
            //设置横横屏
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            //设置默认:由设备的物理方向传感器决定
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        }

        //自定义ProgressView
        progressViewLayoutId = getImageBrowserConfig().getCustomProgressViewLayoutID();
        customImageViewLayoutId = getImageBrowserConfig().getCustomImageViewLayoutID();

        //背景色
        rl_black_bg.setBackgroundColor(Color.parseColor(getImageBrowserConfig().getWindowBackgroundColor()));
        //文字指示器颜色
        numberIndicator.setTextColor(Color.parseColor(getImageBrowserConfig().getIndicatorTextColor()));
        numberIndicator.setTextSize(TypedValue.COMPLEX_UNIT_SP, getImageBrowserConfig().getIndicatorTextSize());
        //指示器自定义
        circleIndicator.updateIndicatorBackgroundResId(getImageBrowserConfig().getIndicatorSelectedResId(), getImageBrowserConfig().getIndicatorUnSelectedResId());

    }

    private void initViewPager() {
        imageBrowserAdapter = new MyAdapter();
        viewPagerBrowser.setAdapter(imageBrowserAdapter);
        viewPagerBrowser.setCurrentItem(currentPosition);
        setViewPagerTransforms();
        circleIndicator.setViewPager(viewPagerBrowser);
        imageBrowserAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
        viewPagerBrowser.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPosition = position;
                numberIndicator.setText((currentPosition + 1) + "/" + imageUrlList.size());
                if (onPageChangeListener != null) {
                    onPageChangeListener.onPageSelected(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mnGestureView.setOnGestureListener(new MNGestureView.OnCanSwipeListener() {
            @Override
            public boolean canSwipe() {
                //8.0去掉下拉缩小效果,8.0背景透明的Activity不能设置方向
                if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) {
                    return false;
                }
                //是否开启手势下拉效果
                if (!getImageBrowserConfig().isOpenPullDownGestureEffect()) {
                    return false;
                }
                View view = imageBrowserAdapter.getPrimaryItem();
                PhotoView imageView = (PhotoView) view.findViewById(R.id.mn_ib_photoview);
                if (imageView.getScale() != 1.0) {
                    return false;
                }
                return true;
            }
        });

        mnGestureView.setOnSwipeListener(new MNGestureView.OnSwipeListener() {
            @Override
            public void downSwipe() {
                finishBrowser();
            }

            @Override
            public void onSwiping(float deltaY) {
                rl_indicator.setVisibility(View.GONE);
                ll_custom_view.setVisibility(View.GONE);

                float mAlpha = 1 - deltaY / 500;
                if (mAlpha < 0.3) {
                    mAlpha = 0.3f;
                }
                if (mAlpha > 1) {
                    mAlpha = 1;
                }
                rl_black_bg.setAlpha(mAlpha);
            }

            @Override
            public void overSwipe() {
                if (imageUrlList.size() <= 1) {
                    rl_indicator.setVisibility(View.GONE);
                } else {
                    rl_indicator.setVisibility(View.VISIBLE);

                    if (!getImageBrowserConfig().isIndicatorHide()) {
                        rl_indicator.setVisibility(View.VISIBLE);
                    } else {
                        rl_indicator.setVisibility(View.GONE);
                    }
                }
                //自定义View
                View customShadeView = getImageBrowserConfig().getCustomShadeView();
                if (customShadeView != null) {
                    ll_custom_view.setVisibility(View.VISIBLE);
                    rl_indicator.setVisibility(View.GONE);
                } else {
                    ll_custom_view.setVisibility(View.GONE);
                }

                rl_black_bg.setAlpha(1);
            }
        });
    }

    private void setViewPagerTransforms() {
        if (transformType == ImageBrowserConfig.TransformType.Transform_Default) {
            viewPagerBrowser.setPageTransformer(true, new DefaultTransformer());
        } else if (transformType == ImageBrowserConfig.TransformType.Transform_DepthPage) {
            viewPagerBrowser.setPageTransformer(true, new DepthPageTransformer());
        } else if (transformType == ImageBrowserConfig.TransformType.Transform_RotateDown) {
            viewPagerBrowser.setPageTransformer(true, new RotateDownTransformer());
        } else if (transformType == ImageBrowserConfig.TransformType.Transform_RotateUp) {
            viewPagerBrowser.setPageTransformer(true, new RotateUpTransformer());
        } else if (transformType == ImageBrowserConfig.TransformType.Transform_ZoomIn) {
            viewPagerBrowser.setPageTransformer(true, new ZoomInTransformer());
        } else if (transformType == ImageBrowserConfig.TransformType.Transform_ZoomOutSlide) {
            viewPagerBrowser.setPageTransformer(true, new ZoomOutSlideTransformer());
        } else if (transformType == ImageBrowserConfig.TransformType.Transform_ZoomOut) {
            viewPagerBrowser.setPageTransformer(true, new ZoomOutTransformer());
        } else {
            viewPagerBrowser.setPageTransformer(true, new DefaultTransformer());
        }
    }

    private void finishBrowser() {
        try {
            ImmersionBar.with(this).transparentBar().init();
            rl_black_bg.setAlpha(0);
            ll_custom_view.setVisibility(View.GONE);
            rl_indicator.setVisibility(View.GONE);
            finish();
            this.overridePendingTransition(0, getImageBrowserConfig().getActivityExitAnime());
        } catch (Exception e) {
            finish();
        } finally {
            //销毁相关数据
            sActivityRef = null;
            imageBrowserConfig = null;
        }
    }

    @Override
    public void onBackPressed() {
        finishBrowser();
    }


    private class MyAdapter extends PagerAdapter {

        private View mCurrentView;

        private LayoutInflater layoutInflater;

        public MyAdapter() {
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            mCurrentView = (View) object;
        }

        public View getPrimaryItem() {
            return mCurrentView;
        }

        @Override
        public int getCount() {
            return imageUrlList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View inflate = layoutInflater.inflate(R.layout.mn_image_browser_item_show_image, container, false);
            final PhotoView photoView = (PhotoView) inflate.findViewById(R.id.mn_ib_photoview);
            final RelativeLayout rl_browser_root = (RelativeLayout) inflate.findViewById(R.id.mn_ib_rl_browser_root);
            final RelativeLayout custom_image_view = (RelativeLayout) inflate.findViewById(R.id.mn_ib_custom_image_view);
            final RelativeLayout progress_view = (RelativeLayout) inflate.findViewById(R.id.mn_ib_progress_view);

            final String url = imageUrlList.get(position);

            rl_browser_root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finishBrowser();
                }
            });

            photoView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //单击事件
                    if (onClickListener != null) {
                        onClickListener.onClick(MNImageBrowserActivity.this, photoView, position, url);
                    }
                    finishBrowser();
                }
            });

            custom_image_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //单击事件
                    if (onClickListener != null) {
                        onClickListener.onClick(MNImageBrowserActivity.this, custom_image_view, position, url);
                    }
                    finishBrowser();
                }
            });

            photoView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (onLongClickListener != null) {
                        onLongClickListener.onLongClick(MNImageBrowserActivity.this, photoView, position, url);
                    }
                    return false;
                }
            });

            custom_image_view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (onLongClickListener != null) {
                        onLongClickListener.onLongClick(MNImageBrowserActivity.this, custom_image_view, position, url);
                    }
                    return false;
                }
            });

            //自定义ImageView显示
            if (customImageViewLayoutId != 0) {
                View customImageViewLayout = layoutInflater.inflate(customImageViewLayoutId, null);
                if (customImageViewLayout != null) {
                    custom_image_view.removeAllViews();
                    custom_image_view.addView(customImageViewLayout);
                    custom_image_view.setVisibility(View.VISIBLE);
                } else {
                    custom_image_view.setVisibility(View.GONE);
                }
            } else {
                custom_image_view.setVisibility(View.GONE);
            }

            //ProgressView
            if (progressViewLayoutId != 0) {
                View customProgressView = layoutInflater.inflate(progressViewLayoutId, null);
                if (customProgressView != null) {
                    progress_view.removeAllViews();
                    progress_view.addView(customProgressView);
                    progress_view.setVisibility(View.VISIBLE);
                } else {
                    progress_view.setVisibility(View.GONE);
                }
            } else {
                progress_view.setVisibility(View.GONE);
            }

            //图片加载
            imageEngine.loadImage(context, url, photoView, progress_view, custom_image_view);

            container.addView(inflate);
            return inflate;
        }
    }

    /**
     * 获取当前Activity实例
     */
    public static FragmentActivity getCurrentActivity() {
        if (sActivityRef != null && sActivityRef.get() != null) {
            return sActivityRef.get();
        } else {
            return null;
        }
    }

    /**
     * 关闭当前Activity
     */
    public static void finishActivity() {
        if (sActivityRef != null && sActivityRef.get() != null) {
            sActivityRef.get().finishBrowser();
        }
    }

    /**
     * 获取ViewPager
     *
     * @return
     */
    public static ViewPager getViewPager() {
        if (sActivityRef != null && sActivityRef.get() != null) {
            return sActivityRef.get().viewPagerBrowser;
        } else {
            return null;
        }
    }

    /**
     * 获取当前位置
     *
     * @return
     */
    public static int getCurrentPosition() {
        if (sActivityRef != null && sActivityRef.get() != null) {
            return sActivityRef.get().currentPosition;
        } else {
            return -1;
        }
    }

    /**
     * 获取当前ImageView
     *
     * @return
     */
    public static ImageView getCurrentImageView() {
        if (sActivityRef != null && sActivityRef.get() != null) {
            MyAdapter imageBrowserAdapter = sActivityRef.get().imageBrowserAdapter;
            if (imageBrowserAdapter == null) {
                return null;
            }
            View view = imageBrowserAdapter.getPrimaryItem();
            if (view == null) {
                return null;
            }
            PhotoView imageView = (PhotoView) view.findViewById(R.id.mn_ib_photoview);
            return imageView;
        } else {
            return null;
        }
    }

    /**
     * 删除一张图片
     *
     * @param position
     * @return
     */
    public static void removeImage(int position) {
        if (sActivityRef != null && sActivityRef.get() != null) {
            if (sActivityRef.get().imageUrlList.size() > 1) {
                sActivityRef.get().imageUrlList.remove(position);
                //更新当前位置
                if (sActivityRef.get().currentPosition >= sActivityRef.get().imageUrlList.size() && sActivityRef.get().currentPosition >= 1) {
                    sActivityRef.get().currentPosition--;
                }
                if (sActivityRef.get().currentPosition >= sActivityRef.get().imageUrlList.size()) {
                    sActivityRef.get().currentPosition = sActivityRef.get().imageUrlList.size() - 1;
                }
                sActivityRef.get().initViewPager();
                sActivityRef.get().imageBrowserAdapter.notifyDataSetChanged();
            }
        }
    }

    /**
     * 删除一张图片
     *
     * @return
     */
    public static void removeCurrentImage() {
        removeImage(getCurrentPosition());
    }

    /**
     * 图片资源列表
     *
     * @return
     */
    public static ArrayList<String> getImageList() {
        if (sActivityRef != null && sActivityRef.get() != null) {
            return sActivityRef.get().imageUrlList;
        }
        return new ArrayList<>();
    }

}
