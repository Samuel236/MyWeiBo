package com.lee.iweibo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lee.iweibo.BaseFragment;
import com.lee.iweibo.R;
import com.lee.iweibo.api.MyWeiboApi;
import com.lee.iweibo.api.SimpleRequestListener;
import com.lee.iweibo.entity.response.StatusTimeLineResponse;
import com.lee.iweibo.utils.Logger;
import com.lee.iweibo.utils.TitleBuilder;
import com.lee.iweibo.utils.ToastUtils;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    private View view;
    private MyWeiboApi api;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        new TitleBuilder(view).setTitleText("首页");
        api = new MyWeiboApi(activity);
        api.statusesHomeTimeline(1, new SimpleRequestListener(activity,null){
            @Override
            public void onComplete(String response) {
                super.onComplete(response);
                StatusTimeLineResponse timeLineResponse =
                        new Gson()
                                .fromJson(response,StatusTimeLineResponse.class);
                Logger.show("info",timeLineResponse.toString());
            }
        });
        return view;
    }

}
