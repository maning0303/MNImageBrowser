package com.maning.mnimagebrowser;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.maning.imagebrowserlibrary.ImageEngine;
import com.maning.imagebrowserlibrary.MNImageBrowser;
import com.maning.imagebrowserlibrary.listeners.OnActivityLifeListener;
import com.maning.imagebrowserlibrary.listeners.OnClickListener;
import com.maning.imagebrowserlibrary.listeners.OnLongClickListener;
import com.maning.imagebrowserlibrary.listeners.OnPageChangeListener;
import com.maning.imagebrowserlibrary.model.ImageBrowserConfig;
import com.maning.mndialoglibrary.MStatusDialog;
import com.maning.mndialoglibrary.MToast;
import com.maning.mnimagebrowser.dialog.ListFragmentDialog;
import com.maning.mnimagebrowser.engine.FrescoImageEngine;
import com.maning.mnimagebrowser.engine.GlideImageEngine;
import com.maning.mnimagebrowser.engine.PicassoImageEngine;
import com.maning.mnimagebrowser.utils.BitmapUtils;
import com.maning.mnimagebrowser.utils.DatasUtils;
import com.maning.mnimagebrowser.view.SuperGridView;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private static final String TAG = "MainActivity";

    private ArrayList<String> sourceImageList;

    private Context context;

    public ImageBrowserConfig.TransformType transformType = ImageBrowserConfig.TransformType.Transform_Default;
    public ImageBrowserConfig.IndicatorType indicatorType = ImageBrowserConfig.IndicatorType.Indicator_Number;
    public ImageBrowserConfig.ScreenOrientationType screenOrientationType = ImageBrowserConfig.ScreenOrientationType.ScreenOrientation_Portrait;
    private ImageEngine imageEngine = new GlideImageEngine();
    private int openAnim = R.anim.mn_browser_enter_anim;
    private int exitAnim = R.anim.mn_browser_exit_anim;

    //显示自定义遮盖层
    private boolean showCustomShadeView = false;
    //显示ProgressView
    private boolean showCustomProgressView = false;
    //显示自定义ImageView
    private boolean showCustomImageView = false;
    //是不是全屏模式
    private boolean isFulScreenMode = false;
    //下拉缩小效果：默认开启true
    private boolean isOpenPullDownGestureEffect = true;
    //自定义背景色
    private String windowBackgroundColor = "#000000";
    //文字指示器颜色
    private String indicatorTextColor = "#FFFFFF";
    //状态栏黑色字体
    private boolean statusBarDarkFont = false;

    /**
     * Glide
     */
    private RadioButton mRbGlide;
    /**
     * Picasso
     */
    private RadioButton mRbPicasso;
    /**
     * Fresco(必须配合自定义ImageView实现-setCustomImageViewLayoutID)
     */
    private RadioButton mRbFresco;
    private RadioGroup mRgImageEngine;
    /**
     * 黑色背景
     */
    private RadioButton mRbWindowbgBlack;
    /**
     * 白色背景
     */
    private RadioButton mRbWindowbgWhite;
    private RadioGroup mRgWindowbg;
    /**
     * 文字指示器(支持自定义文字大小和颜色)
     */
    private RadioButton mRbIndicatorTxt;
    /**
     * 圆点指示器(支持自定义drawable)
     */
    private RadioButton mRbIndicatorDrawable;
    private RadioGroup mRgIndicator;
    /**
     * 竖屏（默认）
     */
    private RadioButton mRbScreenOrientationPortrait;
    /**
     * 横屏
     */
    private RadioButton mRbScreenOrientationLandscape;
    /**
     * 全部支持
     */
    private RadioButton mRbScreenOrientationAll;
    private RadioGroup mRgScreenOrientation;
    /**
     * 非全屏（默认）
     */
    private RadioButton mRbFullscreenNo;
    /**
     * 全屏
     */
    private RadioButton mRbFullscreenYes;
    private RadioGroup mRgFullscreen;
    /**
     * 关闭手势下拉缩小效果
     */
    private CheckBox mCbPulldownGesture;
    /**
     * 自定义加载Progress效果
     */
    private CheckBox mCbCustomProgress;
    /**
     * 自定义遮罩层
     */
    private CheckBox mCbCustomShade;
    /**
     * 自定义打开关闭动画
     */
    private CheckBox mCbCustomAnim;
    private Spinner mSpinner;
    private SuperGridView mGvImages;
    private RelativeLayout mActivityMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        initView();
        initDatas();
    }

    private void initDatas() {
        sourceImageList = DatasUtils.getDatas();
        mGvImages.setAdapter(new NineGridAdapter());
    }

    private void initView() {
        mRbGlide = (RadioButton) findViewById(R.id.rb_glide);
        mRbPicasso = (RadioButton) findViewById(R.id.rb_picasso);
        mRbFresco = (RadioButton) findViewById(R.id.rb_fresco);
        mRgImageEngine = (RadioGroup) findViewById(R.id.rg_image_engine);
        mRbWindowbgBlack = (RadioButton) findViewById(R.id.rb_windowbg_black);
        mRbWindowbgWhite = (RadioButton) findViewById(R.id.rb_windowbg_white);
        mRgWindowbg = (RadioGroup) findViewById(R.id.rg_windowbg);
        mRbIndicatorTxt = (RadioButton) findViewById(R.id.rb_indicator_txt);
        mRbIndicatorDrawable = (RadioButton) findViewById(R.id.rb_indicator_drawable);
        mRgIndicator = (RadioGroup) findViewById(R.id.rg_indicator);
        mRbScreenOrientationPortrait = (RadioButton) findViewById(R.id.rb_screen_orientation_portrait);
        mRbScreenOrientationLandscape = (RadioButton) findViewById(R.id.rb_screen_orientation_landscape);
        mRbScreenOrientationAll = (RadioButton) findViewById(R.id.rb_screen_orientation_all);
        mRgScreenOrientation = (RadioGroup) findViewById(R.id.rg_screen_orientation);
        mRbFullscreenNo = (RadioButton) findViewById(R.id.rb_fullscreen_no);
        mRbFullscreenYes = (RadioButton) findViewById(R.id.rb_fullscreen_yes);
        mRgFullscreen = (RadioGroup) findViewById(R.id.rg_fullscreen);
        mCbPulldownGesture = (CheckBox) findViewById(R.id.cb_pulldown_gesture);
        mCbCustomProgress = (CheckBox) findViewById(R.id.cb_custom_progress);
        mCbCustomShade = (CheckBox) findViewById(R.id.cb_custom_shade);
        mCbCustomAnim = (CheckBox) findViewById(R.id.cb_custom_anim);
        mSpinner = (Spinner) findViewById(R.id.spinner);
        mGvImages = (SuperGridView) findViewById(R.id.gv_images);
        mActivityMain = (RelativeLayout) findViewById(R.id.activity_main);

        mRgImageEngine.setOnCheckedChangeListener(this);
        mRgWindowbg.setOnCheckedChangeListener(this);
        mRgIndicator.setOnCheckedChangeListener(this);
        mRgScreenOrientation.setOnCheckedChangeListener(this);
        mRbScreenOrientationPortrait.setChecked(true);
        mRgFullscreen.setOnCheckedChangeListener(this);
        mRbGlide.setChecked(true);
        mRbWindowbgBlack.setChecked(true);
        mRbIndicatorDrawable.setChecked(true);
        mRbFullscreenNo.setChecked(true);
        mCbPulldownGesture.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isOpenPullDownGestureEffect = !isChecked;
            }
        });
        mCbCustomProgress.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                showCustomProgressView = isChecked;
            }
        });
        mCbCustomShade.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                showCustomShadeView = isChecked;
            }
        });
        mCbCustomAnim.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    openAnim = R.anim.activity_anmie_in;
                    exitAnim = R.anim.activity_anmie_out;
                } else {
                    openAnim = R.anim.mn_browser_enter_anim;
                    exitAnim = R.anim.mn_browser_exit_anim;
                }
            }
        });
        mCbPulldownGesture.setChecked(false);
        mCbCustomProgress.setChecked(false);
        mCbCustomShade.setChecked(false);
        mCbCustomAnim.setChecked(false);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    transformType = ImageBrowserConfig.TransformType.Transform_Default;
                } else if (position == 1) {
                    transformType = ImageBrowserConfig.TransformType.Transform_DepthPage;
                } else if (position == 2) {
                    transformType = ImageBrowserConfig.TransformType.Transform_RotateDown;
                } else if (position == 3) {
                    transformType = ImageBrowserConfig.TransformType.Transform_RotateUp;
                } else if (position == 4) {
                    transformType = ImageBrowserConfig.TransformType.Transform_ZoomIn;
                } else if (position == 5) {
                    transformType = ImageBrowserConfig.TransformType.Transform_ZoomOut;
                } else if (position == 6) {
                    transformType = ImageBrowserConfig.TransformType.Transform_ZoomOutSlide;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (R.id.rb_glide == checkedId) {
            imageEngine = new GlideImageEngine();
            showCustomImageView = false;
        } else if (R.id.rb_picasso == checkedId) {
            imageEngine = new PicassoImageEngine();
            showCustomImageView = false;
        } else if (R.id.rb_fresco == checkedId) {
            imageEngine = new FrescoImageEngine();
            showCustomImageView = true;
        } else if (R.id.rb_windowbg_black == checkedId) {
            windowBackgroundColor = "#000000";
            indicatorTextColor = "#FFFFFF";
            statusBarDarkFont = false;
        } else if (R.id.rb_windowbg_white == checkedId) {
            windowBackgroundColor = "#FFFFFF";
            indicatorTextColor = "#000000";
            statusBarDarkFont = true;
        } else if (R.id.rb_indicator_txt == checkedId) {
            indicatorType = ImageBrowserConfig.IndicatorType.Indicator_Number;
        } else if (R.id.rb_indicator_drawable == checkedId) {
            indicatorType = ImageBrowserConfig.IndicatorType.Indicator_Circle;
        } else if (R.id.rb_screen_orientation_portrait == checkedId) {
            screenOrientationType = ImageBrowserConfig.ScreenOrientationType.ScreenOrientation_Portrait;
        } else if (R.id.rb_screen_orientation_landscape == checkedId) {
            screenOrientationType = ImageBrowserConfig.ScreenOrientationType.Screenorientation_Landscape;
        } else if (R.id.rb_screen_orientation_all == checkedId) {
            screenOrientationType = ImageBrowserConfig.ScreenOrientationType.Screenorientation_All;
        } else if (R.id.rb_fullscreen_no == checkedId) {
            isFulScreenMode = false;
        } else if (R.id.rb_fullscreen_yes == checkedId) {
            isFulScreenMode = true;
        }
    }

    public void defaultOpen(View view) {
        MNImageBrowser.with(context)
                //当前位置
                .setCurrentPosition(0)
                //图片引擎
                .setImageEngine(imageEngine)
                //图片集合
                .setImageList(sourceImageList)
                .show(view);
    }

    private class NineGridAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return sourceImageList.size();
        }

        @Override
        public Object getItem(int position) {
            return sourceImageList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup viewGroup) {
            final ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.image_item, null);
                viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            Glide.with(context)
                    .asBitmap()
                    .load(sourceImageList.get(position))
                    .apply(new RequestOptions().fitCenter().error(R.mipmap.ic_launcher).placeholder(R.drawable.default_placeholder))
                    .into(viewHolder.imageView);

            viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //获取一个自定义遮盖层View
                    View customView = LayoutInflater.from(context).inflate(R.layout.layout_custom_view, null);
                    ImageView ic_close = (ImageView) customView.findViewById(R.id.iv_close);
                    ImageView iv_more = (ImageView) customView.findViewById(R.id.iv_more);
                    ImageView iv_comment = (ImageView) customView.findViewById(R.id.iv_comment);
                    ImageView iv_zan = (ImageView) customView.findViewById(R.id.iv_zan);
                    ImageView iv_delete = (ImageView) customView.findViewById(R.id.iv_delete);
                    final TextView tv_number_indicator = (TextView) customView.findViewById(R.id.tv_number_indicator);
                    ic_close.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //关闭图片浏览
                            MNImageBrowser.finishImageBrowser();
                        }
                    });
                    iv_zan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MToast.makeTextShort(context, "点赞" + MNImageBrowser.getCurrentPosition());
                        }
                    });
                    iv_more.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            FragmentActivity currentActivity = MNImageBrowser.getCurrentActivity();
                            ImageView currentImageView = MNImageBrowser.getCurrentImageView();
                            if (currentImageView != null && currentActivity != null) {
                                showListDialog(currentActivity, currentImageView);
                            }
                        }
                    });
                    iv_comment.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MToast.makeTextShort(context, "评论" + MNImageBrowser.getCurrentPosition());
                        }
                    });
                    iv_delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //删除当前位置的图片
                            MNImageBrowser.removeCurrentImage();
                            //刷新指示器
                            tv_number_indicator.setText((MNImageBrowser.getCurrentPosition() + 1) + "/" + MNImageBrowser.getImageList().size());
                        }
                    });
                    tv_number_indicator.setText((position + 1) + "/" + sourceImageList.size());


                    MNImageBrowser.with(context)
                            //页面切换效果
                            .setTransformType(transformType)
                            //指示器效果
                            .setIndicatorType(indicatorType)
                            //设置隐藏指示器
                            .setIndicatorHide(false)
                            //设置自定义遮盖层，定制自己想要的效果，当设置遮盖层后，原本的指示器会被隐藏
                            .setCustomShadeView(showCustomShadeView ? customView : null)
                            //自定义ProgressView，不设置默认默认没有
                            .setCustomProgressViewLayoutID(showCustomProgressView ? R.layout.layout_custom_progress_view : 0)
                            //当前位置
                            .setCurrentPosition(position)
                            //图片引擎
                            .setImageEngine(imageEngine)
                            //图片集合
                            .setImageList(sourceImageList)
                            //方向设置
                            .setScreenOrientationType(screenOrientationType)
                            //点击监听
                            .setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(FragmentActivity activity, View view, int position, String url) {
                                    //TODO:注意，这里的View可能是ImageView,也可能是自定义setCustomImageViewLayout的View
                                }
                            })
                            //长按监听
                            .setOnLongClickListener(new OnLongClickListener() {
                                @Override
                                public void onLongClick(final FragmentActivity activity, final View imageView, int position, String url) {
                                    //TODO:注意，这里的View可能是ImageView,也可能是自定义setCustomImageViewLayout的View
                                    if (imageView instanceof ImageView) {
                                        showListDialog(activity, (ImageView) imageView);
                                    } else {
                                        MToast.makeTextShort(context, "自定义setCustomImageViewLayout的View,自己实现长按功能");
                                    }
                                }
                            })
                            //页面切换监听
                            .setOnPageChangeListener(new OnPageChangeListener() {
                                @Override
                                public void onPageSelected(int position) {
                                    Log.i(TAG, "onPageSelected:" + position);
                                    if (tv_number_indicator != null) {
                                        tv_number_indicator.setText((position + 1) + "/" + MNImageBrowser.getImageList().size());
                                    }
                                }
                            })
                            //生命周期监听
                            .setOnActivityLifeListener(new OnActivityLifeListener() {
                                @Override
                                public void onCreate() {
                                    Log.i(TAG, "OnActivityLifeListener:onCreate");
                                }

                                @Override
                                public void onResume() {
                                    Log.i(TAG, "OnActivityLifeListener:onResume");
                                }

                                @Override
                                public void onPause() {
                                    Log.i(TAG, "OnActivityLifeListener:onPause");
                                }

                                @Override
                                public void onDestory() {
                                    Log.i(TAG, "OnActivityLifeListener:onDestory");
                                }
                            })
                            //全屏模式
                            .setFullScreenMode(isFulScreenMode)
                            //打开动画
                            .setActivityOpenAnime(openAnim)
                            //关闭动画
                            .setActivityExitAnime(exitAnim)
                            //手势下拉缩小效果
                            .setOpenPullDownGestureEffect(isOpenPullDownGestureEffect)
                            //自定义显示View
                            .setCustomImageViewLayoutID(showCustomImageView ? R.layout.layout_custom_image_view_fresco : 0)
                            //自定义指示器显示
                            .setIndicatorBackgroundResId(R.drawable.custom_indicator_bg_selected, R.drawable.custom_indicator_bg_unselected)
                            //状态栏黑色字体
                            .setStatusBarDarkFont(statusBarDarkFont)
                            //数字指示器文字大小，sp
                            .setIndicatorTextSize(18)
                            //数字指示器文字颜色
                            .setIndicatorTextColor(indicatorTextColor)
                            //整体背景色
                            .setWindowBackgroundColor(windowBackgroundColor)
                            //显示：传入当前View,可以不传
                            .show(view);
                }
            });


            return convertView;
        }

        public final class ViewHolder {
            ImageView imageView;
        }
    }

    private void showListDialog(final FragmentActivity activity, final ImageView imageView) {
        new ListFragmentDialog(new ListFragmentDialog.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                if (position == 1) {
                    //检查权限
                    AndPermission
                            .with(context)
                            .runtime()
                            .permission(Permission.WRITE_EXTERNAL_STORAGE)
                            .onGranted(new Action<List<String>>() {
                                @Override
                                public void onAction(List<String> data) {
                                    //保存图片
                                    imageView.buildDrawingCache(true);
                                    imageView.buildDrawingCache();
                                    Bitmap bitmap = imageView.getDrawingCache();
                                    String path = Environment.getExternalStorageDirectory() + "/test.png";
                                    boolean flag = BitmapUtils.saveBitmap(bitmap, path);
                                    imageView.setDrawingCacheEnabled(false);
                                    if (flag) {
                                        new MStatusDialog(activity).show("保存成功", activity.getResources().getDrawable(R.drawable.icon_save_success));
                                    } else {
                                        new MStatusDialog(activity).show("保存失败", activity.getResources().getDrawable(R.drawable.icon_save_fail));
                                    }
                                }
                            })
                            .onDenied(new Action<List<String>>() {
                                @Override
                                public void onAction(List<String> data) {
                                    new MStatusDialog(activity).show("权限获取失败", activity.getResources().getDrawable(R.drawable.icon_save_fail));
                                }
                            })
                            .start();

                }
            }
        }).show(activity.getSupportFragmentManager(), "");
    }

}
