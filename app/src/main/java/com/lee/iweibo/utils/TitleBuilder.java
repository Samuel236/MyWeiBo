package com.lee.iweibo.utils;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lee.iweibo.R;

/**
 * 使用构造器模式来处理不确定项的事件
 */
public class TitleBuilder {

    //整个title的view
    private View viewTitle;
    private TextView tvTitle;
    private TextView tvLeft;
    private TextView tvRight;
    private ImageView ivLeft;
    private ImageView ivRight;

    public TitleBuilder(View context){
        //获取布局
        viewTitle = context.findViewById(R.id.rl_titlebar);
        tvTitle = (TextView) viewTitle.findViewById(R.id.titlebar_tv);
        tvLeft = (TextView) viewTitle.findViewById(R.id.titlebar_tv_left);
        tvRight = (TextView) viewTitle.findViewById(R.id.titlebar_tv_right);
        ivLeft = (ImageView) viewTitle.findViewById(R.id.titlebar_iv_left);
        ivRight = (ImageView) viewTitle.findViewById(R.id.titlebar_iv_right);
    }

    //设置标题栏背景
    public TitleBuilder setTitleBgRs(int resId){
        viewTitle.setBackgroundResource(resId);
        return this;
    }

    public TitleBuilder setTitleText(String text){
        tvTitle.setVisibility(TextUtils.isEmpty(text)?
                        View.GONE:View.VISIBLE);
        tvTitle.setText(text);
        return this;
    }
    public TitleBuilder setLeftText(String text){
        tvLeft.setVisibility(TextUtils.isEmpty(text)?
                        View.GONE:View.VISIBLE);
        tvLeft.setText(text);
        return this;
    }


    public TitleBuilder setLeftImage(int resId){
        ivLeft.setVisibility(resId>0?
                    View.VISIBLE:View.GONE);
        ivLeft.setImageResource(resId);
        return this;
    }

    //设置监听
    public TitleBuilder setLeftOnClickListener(View.OnClickListener listener){
        if(tvLeft.getVisibility() == View.VISIBLE){
            tvLeft.setOnClickListener(listener);
        }else if(ivLeft.getVisibility() == View.VISIBLE){
            ivLeft.setOnClickListener(listener);
        }
        return this;
    }

    public TitleBuilder setRightText(String text){
        tvRight.setVisibility(TextUtils.isEmpty(text)?
                View.GONE:View.VISIBLE);
        tvRight.setText(text);
        return this;
    }
    public TitleBuilder setRightImage(int resId){
        ivRight.setVisibility(resId>0?
                    View.VISIBLE:View.GONE);
        ivRight.setImageResource(resId);
        return this;
    }

    public TitleBuilder setRightOnClickListener(View.OnClickListener listener){
        if(tvRight.getVisibility() == View.VISIBLE){
            tvRight.setOnClickListener(listener);
        }else if(ivRight.getVisibility() == View.VISIBLE){
            ivRight.setOnClickListener(listener);
        }
        return this;
    }

    public View build(){
        return viewTitle;
    }
}
