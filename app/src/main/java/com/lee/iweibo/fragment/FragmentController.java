package com.lee.iweibo.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import java.util.ArrayList;

/**
 * 创建单例的控制器
 */
public class FragmentController {
    public static FragmentController controll;
    private int containerId;//放置fragment的布局
    private FragmentManager fm;
    private ArrayList<Fragment> fragments;
    //私有化构造方法
    private FragmentController(FragmentActivity activity, int containerId){
        this.fm = activity.getFragmentManager();
        this.containerId = containerId;
        initFragment();
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new MessageFragment());
        fragments.add(new SearchFragment());
        fragments.add(new UserFragment());
        //将所有的fragmentdouble加入，使用显示隐藏的方式来实现切换
        FragmentTransaction ft = fm.beginTransaction();
        for(Fragment fragment:fragments){
            ft.add(containerId,fragment);
        }
        ft.commit();
    };
    //给出获取实例的方法
    public static FragmentController getInstance(FragmentActivity activity, int containerId){
        if(controll == null){
            controll = new FragmentController(activity,containerId);
        }
        return controll;
    }

    //隐藏fragment
    public void hideFragment(){
        FragmentTransaction ft = fm.beginTransaction();
        for(Fragment fragment:fragments){
            if(fragment != null){
                ft.hide(fragment);
            }
        }
        ft.commit();
    }

    //显示当前的fragment
    public void showFragment(int position){
        hideFragment();
        Fragment fragment = fragments.get(position);
        FragmentTransaction ft = fm.beginTransaction();
        ft.show(fragment);
        ft.commit();
    }

    //获取当前的fragment
    public Fragment getFragment(int position){
        return fragments.get(position);
    }
}
