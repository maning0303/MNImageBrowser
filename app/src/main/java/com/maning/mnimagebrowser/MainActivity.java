package com.maning.mnimagebrowser;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.maning.imagebrowserlibrary.ImageEngine;
import com.maning.imagebrowserlibrary.MNImageBrowser;
import com.maning.imagebrowserlibrary.listeners.OnClickListener;
import com.maning.imagebrowserlibrary.listeners.OnLongClickListener;
import com.maning.imagebrowserlibrary.listeners.OnPageChangeListener;
import com.maning.imagebrowserlibrary.model.ImageBrowserConfig;
import com.maning.mndialoglibrary.MStatusDialog;
import com.maning.mnimagebrowser.dialog.ListFragmentDialog;
import com.maning.mnimagebrowser.engine.GlideImageEngine;
import com.maning.mnimagebrowser.engine.PicassoImageEngine;
import com.maning.mnimagebrowser.utils.BitmapUtils;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    protected GridView gvImages;

    private ArrayList<String> sourceImageList;

    private Context context;

    public ImageBrowserConfig.TransformType transformType = ImageBrowserConfig.TransformType.Transform_Default;
    public ImageBrowserConfig.IndicatorType indicatorType = ImageBrowserConfig.IndicatorType.Indicator_Number;
    public ImageBrowserConfig.ScreenOrientationType screenOrientationType = ImageBrowserConfig.ScreenOrientationType.Screenorientation_Default;
    private ImageEngine imageEngine = new GlideImageEngine();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        gvImages = (GridView) findViewById(R.id.gv_images);


        sourceImageList = new ArrayList<>();
        sourceImageList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523527461022&di=cf56170de9cd14bd24299af8597ee88d&imgtype=0&src=http%3A%2F%2Fpic13.nipic.com%2F20110414%2F7056815_094945110000_2.jpg");
        sourceImageList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523527636870&di=756de67da481b197037dc5bfbe4bf1a6&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F012bc0585250e8a801219c77cf3db4.jpg");
        sourceImageList.add("http://7xi8d6.com1.z0.glb.clouddn.com/2017-05-12-18380140_455327614813449_854681840315793408_n.jpg");
        sourceImageList.add("http://7xi8d6.com1.z0.glb.clouddn.com/2017-05-11-18380166_305443499890139_8426655762360565760_n.jpg");
        sourceImageList.add("http://7xi8d6.com1.z0.glb.clouddn.com/2017-05-10-18382517_1955528334668679_3605707761767153664_n.jpg");
        sourceImageList.add("http://7xi8d6.com1.z0.glb.clouddn.com/2017-05-09-18443931_429618670743803_5734501112254300160_n.jpg");
        sourceImageList.add("http://7xi8d6.com1.z0.glb.clouddn.com/2017-05-08-18252341_289400908178710_9137908350942445568_n.jpg");
        sourceImageList.add("http://7xi8d6.com1.z0.glb.clouddn.com/2017-05-05-18251898_1013302395468665_8734429858911748096_n.jpg");
        sourceImageList.add("http://ww1.sinaimg.cn/large/61e74233ly1feuogwvg27j20p00zkqe7.jpg");
        sourceImageList.add("http://7xi8d6.com1.z0.glb.clouddn.com/2017-05-02-926821_1453024764952889_775781470_n.jpg");
        sourceImageList.add("http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-28-18094719_120129648541065_8356500748640452608_n.jpg");
        sourceImageList.add("https://ws1.sinaimg.cn/mw690/610dc034ly1ffwb7npldpj20u00u076z.jpg");
        sourceImageList.add("https://ws1.sinaimg.cn/large/610dc034ly1ffv3gxs37oj20u011i0vk.jpg");
        sourceImageList.add("https://ws1.sinaimg.cn/large/610dc034ly1fftusiwb8hj20u00zan1j.jpg");
        sourceImageList.add("http://ww1.sinaimg.cn/large/610dc034ly1ffmwnrkv1hj20ku0q1wfu.jpg");
        sourceImageList.add("https://ws1.sinaimg.cn/large/610dc034ly1ffyp4g2vwxj20u00tu77b.jpg");
        sourceImageList.add("https://ws1.sinaimg.cn/large/610dc034ly1ffxjlvinj5j20u011igri.jpg");
        sourceImageList.add("http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-11-17881546_248332202297978_2420944671002853376_n.jpg");
        sourceImageList.add("http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-12-17662441_1675934806042139_7236493360834281472_n.jpg");
        sourceImageList.add("http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-13-17882785_926451654163513_7725522121023029248_n.jpg");
        sourceImageList.add("http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-14-17881962_1329090457138411_8289893708619317248_n.jpg");

        gvImages.setAdapter(new NineGridAdapter());
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


            Picasso.with(context)
                    .load(sourceImageList.get(position))
                    .placeholder(R.drawable.default_placeholder)
                    .error(R.mipmap.ic_launcher)
                    .into(viewHolder.imageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            Log.i("----", "onSuccess:" + position);
                        }

                        @Override
                        public void onError() {
                            Log.i("----", "onError:" + position);
                        }
                    });

            viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MNImageBrowser.with(context)
                            .setTransformType(transformType)
                            .setIndicatorType(indicatorType)
                            .setCurrentPosition(position)
                            .setImageEngine(imageEngine)
                            .setImageList(sourceImageList)
                            .setScreenOrientationType(screenOrientationType)
                            .setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(FragmentActivity activity, ImageView view, int position, String url) {
                                }
                            })
                            .setOnLongClickListener(new OnLongClickListener() {
                                @Override
                                public void onLongClick(final FragmentActivity activity, final ImageView imageView, int position, String url) {
                                    showListDialog(activity, imageView);
                                }
                            })
                            .setOnPageChangeListener(new OnPageChangeListener() {
                                @Override
                                public void onPageSelected(int position) {
                                    Log.i(TAG, "onPageSelected:" + position);
                                }
                            })
                            .show(viewHolder.imageView);
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
                    AndPermission.with(activity)
                            .permission(Permission.WRITE_EXTERNAL_STORAGE)
                            .onGranted(new Action() {
                                @Override
                                public void onAction(List<String> permissions) {
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
                            .onDenied(new Action() {
                                @Override
                                public void onAction(List<String> permissions) {
                                    new MStatusDialog(activity).show("权限获取失败", activity.getResources().getDrawable(R.drawable.icon_save_fail));
                                }
                            })
                            .start();

                }
            }
        }).show(activity.getSupportFragmentManager(), "");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_01:
                transformType = ImageBrowserConfig.TransformType.Transform_Default;
                break;
            case R.id.menu_02:
                transformType = ImageBrowserConfig.TransformType.Transform_DepthPage;
                break;
            case R.id.menu_03:
                transformType = ImageBrowserConfig.TransformType.Transform_RotateDown;
                break;
            case R.id.menu_04:
                transformType = ImageBrowserConfig.TransformType.Transform_RotateUp;
                break;
            case R.id.menu_05:
                transformType = ImageBrowserConfig.TransformType.Transform_ZoomIn;
                break;
            case R.id.menu_06:
                transformType = ImageBrowserConfig.TransformType.Transform_ZoomOutSlide;
                break;
            case R.id.menu_07:
                transformType = ImageBrowserConfig.TransformType.Transform_ZoomOut;
                break;
            case R.id.menu_08:
                indicatorType = ImageBrowserConfig.IndicatorType.Indicator_Number;
                break;
            case R.id.menu_09:
                indicatorType = ImageBrowserConfig.IndicatorType.Indicator_Circle;
                break;
            case R.id.menu_10:
                imageEngine = new GlideImageEngine();
                break;
            case R.id.menu_11:
                imageEngine = new PicassoImageEngine();
                break;
            case R.id.menu_12:
                screenOrientationType = ImageBrowserConfig.ScreenOrientationType.Screenorientation_Default;
                break;
            case R.id.menu_13:
                screenOrientationType = ImageBrowserConfig.ScreenOrientationType.ScreenOrientation_Portrait;
                break;
            case R.id.menu_14:
                screenOrientationType = ImageBrowserConfig.ScreenOrientationType.Screenorientation_Landscape;
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
