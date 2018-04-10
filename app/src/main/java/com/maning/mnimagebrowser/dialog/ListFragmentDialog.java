package com.maning.mnimagebrowser.dialog;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.maning.mndialoglibrary.base.BaseFragmentDialog;
import com.maning.mnimagebrowser.R;

/**
 * <pre>
 *     author : maning
 *     e-mail : xxx@xx
 *     time   : 2018/04/10
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@SuppressLint("ValidFragment")
public class ListFragmentDialog extends BaseFragmentDialog {

    public interface OnItemClickListener{
        void onClick(int position);
    }

    private OnItemClickListener onItemClickListener;

    private Button btn_cancle;
    private Button btn_other;
    private Button btn_save;
    private RelativeLayout rl_bg;

    @SuppressLint("ValidFragment")
    public ListFragmentDialog(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    protected View initView(LayoutInflater layoutInflater) {
        View view = layoutInflater.inflate(R.layout.dialog_list,null);
        btn_cancle = (Button) view.findViewById(R.id.btn_cancle);
        btn_other = (Button) view.findViewById(R.id.btn_other);
        btn_save = (Button) view.findViewById(R.id.btn_save);
        rl_bg = (RelativeLayout) view.findViewById(R.id.rl_bg);

        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null){
                    onItemClickListener.onClick(0);
                }
                dismiss();
            }
        });

        rl_bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null){
                    onItemClickListener.onClick(1);
                }
                dismiss();
            }
        });

        return view;
    }

    @Override
    protected int initAnimations() {
        return 0;
    }
}
