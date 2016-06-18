package com.lee.iweibo.api;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.lee.iweibo.constants.AccessTokenKeeper;
import com.lee.iweibo.constants.Urls;
import com.lee.iweibo.constants.WeiboConstants;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.sina.weibo.sdk.openapi.AbsOpenAPI;

/**
 * 微博接口
 */
public class MyWeiboApi extends AbsOpenAPI {
    //获取主线程的handler
    private Handler mainLooperHandler = new Handler(Looper.getMainLooper());
    public static final String HTTP_GET = "GET";
    /**
     * 构造函数，使用各个 API 接口提供的服务前必须先获取 Token。
     *
     * @param context
     * @param appKey
     * @param accessToken
     */
    public MyWeiboApi(Context context, String appKey, Oauth2AccessToken accessToken) {
        super(context, appKey, accessToken);
    }
    //创建的构造方法
    public MyWeiboApi(Context context){
        this(context, WeiboConstants.APP_KEY, AccessTokenKeeper.readAccessToken(context));
    }

    @Override
    protected void requestAsync(String url, WeiboParameters params, String httpMethod, RequestListener listener) {
        super.requestAsync(url, params, httpMethod, listener);
    }

    //创建直接回调到主线程的请求
    public void requestInMainLooper(String url, WeiboParameters params, String httpMethod, final RequestListener listener){
        requestAsync(url, params, httpMethod, new RequestListener() {
            @Override
            public void onComplete(final String s) {
                mainLooperHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onComplete(s);
                    }
                });
            }

            @Override
            public void onWeiboException(final WeiboException e) {
                //返回到主线程处理
                mainLooperHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onWeiboException(e);
                    }
                });
            }
        });
    }

    //获取最新的微博
    public void statusesHomeTimeline(long page,RequestListener listener){
        WeiboParameters parameters = new WeiboParameters(WeiboConstants.APP_KEY);
        parameters.put("page",page);
        requestInMainLooper(Urls.statuses_home_timeline,parameters,HTTP_GET,listener);
    }
}
