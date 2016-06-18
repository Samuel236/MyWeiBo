package com.lee.iweibo.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.lee.iweibo.R;
import com.lee.iweibo.fragment.FragmentController;

public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private RadioGroup rgTab;
    private FragmentController controller;
    private ImageView ivAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        controller = FragmentController.getInstance(this,R.id.fl_content);
        controller.showFragment(0);
        rgTab.setOnCheckedChangeListener(this);
        ivAdd.setOnClickListener(this);
    }

    private void initView() {
        rgTab = (RadioGroup) findViewById(R.id.rg_tab);
        ivAdd = (ImageView) findViewById(R.id.iv_add);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rb_home:
                controller.showFragment(0);
                break;
            case R.id.rb_msg:
                controller.showFragment(1);
                break;
            case R.id.rb_discover:
                controller.showFragment(2);
                break;
            case R.id.rb_profile:
                controller.showFragment(3);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_add:

                break;
        }
    }
}
