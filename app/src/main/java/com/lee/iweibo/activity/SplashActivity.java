package com.lee.iweibo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.lee.iweibo.BaseActivity;
import com.lee.iweibo.R;
import com.lee.iweibo.constants.AccessTokenKeeper;
import com.lee.iweibo.utils.Contract;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

/**
 * 欢迎界面
 */
public class SplashActivity extends BaseActivity implements Animation.AnimationListener {

    private ImageView ivSlogan;
    private AlphaAnimation alphaAnimation;
    private boolean isFirst = true;
    private Oauth2AccessToken accessToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        ivSlogan = (ImageView) findViewById(R.id.iv_slogan);
        //读取Token对象
        accessToken = AccessTokenKeeper.readAccessToken(this);
        //动画
        alphaAnimation = new AlphaAnimation(0f,1.0f);
        alphaAnimation.setDuration(3000);
        ivSlogan.startAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(this);
        isFirst = sp.getBoolean(Contract.ISFIRST,true);

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if(accessToken.isSessionValid()){//代表已经授权
            intent2Activity(MainActivity.class);
        }else{
            intent2Activity(LoginActivity.class);
        }
        finish();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
