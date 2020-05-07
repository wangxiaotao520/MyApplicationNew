package com.wangxiaotao.myapplicationnew.ui;

import android.content.Intent;
import android.os.Bundle;

import com.coder.zzq.smartshow.toast.SmartToast;
import com.wangxiaotao.myapplicationnew.R;
import com.wangxiaotao.myapplicationnew.ui.base.BaseActivity;
import com.wangxiaotao.myapplicationnew.ui.demo.DemoNormalActivity;
import com.wangxiaotao.myapplicationnew.ui.demo.model.ModelServiceCat;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.annotation.Nullable;

/**
 * 主页Activity
 */
public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        startActivity(new Intent(this, DemoNormalActivity.class));
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void initIntentData() {

    }

    @Override
    protected int getFragmentCotainerId() {
        return 0;
    }

    @Override
    protected void initFragment() {

    }
    /**
     * eventbus回调
     *
     * @param
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void back(ModelServiceCat info) {
        SmartToast.showInfo("eventbus");
    }

    @Override
    protected void onDestroy() {
        //测试了
     //   EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
