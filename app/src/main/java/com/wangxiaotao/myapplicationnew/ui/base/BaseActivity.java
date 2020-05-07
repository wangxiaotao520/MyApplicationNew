package com.wangxiaotao.myapplicationnew.ui.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wangxiaotao.myapplicationnew.R;
import com.wangxiaotao.myapplicationnew.dialog.SmallDialog;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

/**
 * Description:基类Activity
 * Author: wangxiaotao
 * Create: 2018/6/10 16:40
 */
public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";
    /**
     * 是否沉浸状态栏
     **/
    protected boolean isStatusBar = false;
    /**
     * 是否允许全屏
     **/
    protected boolean isFullScreen = false;
    /**
     * 是否允许旋转屏幕
     **/
    protected boolean isScreenRoate = false;

    // 监听当前fragment
    protected BaseFragment currentFragment;

    /**
     * context
     **/
    protected Context mContext;
    /**
     * 是否输出日志信息
     **/
    protected boolean isDebug;
    protected TextView titleName; //标题
    protected TextView tv_right;   //右上按钮
    protected ImageView iv_right;   //右上按钮
    protected SmallDialog smallDialog;//等待对话框

    /**
     * 初始化界面
     **/
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 绑定事件
     */
    protected abstract void initListener();

    /**
     * 布局
     *
     * @return
     */
    protected abstract int getLayoutId();


    protected ScreenManager screenManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        screenManager = ScreenManager.getInstance();
        initScreenConfig();
        super.onCreate(savedInstanceState);
       // setTheme(R.style.AppCompatTheme_Base);
//        if (enableSliding()) {
//            setTheme(R.style.AppTheme_Slide);
//            SlidingLayout rootView = new SlidingLayout(this);
//            rootView.bindActivity(this);
//        }else {
            setTheme(R.style.AppCompatTheme_Base);
//        }
        initConfigBeforeSetContentView();
        setContentView(getLayoutId());
        mContext = this;
        smallDialog = new SmallDialog(mContext);
        initIntentData();
        initView();
        initFragment();
        initListener();
        initData();
        //各种初始化：方法初始化、通用配置、
        ActivityStackManager.getActivityStackManager().pushActivity(this);
//        //判断是否暗黑模式
//        if (NightModeUtils.getThemeMode()==NightModeUtils.ThemeMode.NIGHT){
//            alphaWindow(0.5f);
//        }else {
//            alphaWindow(1f);
//        }
    }

    /**
     * 屏幕阴影
     * @param alpha
     */
    private void alphaWindow(float alpha) {
        // 产生背景变暗效果
        WindowManager.LayoutParams lp = ((Activity)mContext).getWindow().getAttributes();

        lp.alpha =alpha;

        ((Activity)mContext).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        ((Activity)mContext).getWindow().setAttributes(lp);
    }

    /**
     * 在setContentView之前调用的方法，在super.onCreate(savedInstanceState)之后调用
     */
    protected void initConfigBeforeSetContentView() {

    }

    /**
     * 初始化屏幕相关配置
     */
    protected  void initScreenConfig(){
        screenManager.setFullScreen(isFullScreen, this);
        screenManager.setStatusBar(isStatusBar, this);
        screenManager.setScreenRoate(isScreenRoate, this);
    }

    /**
     * 是否允许右滑退出
     * @return
     */
    protected boolean enableSliding() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            return false;
        }
        return false;
    }
    /**
     * 初始化intent
     */
    protected abstract void initIntentData();

    /**
     * 获取Fragment的container的id(有需要使用Fragment时)
     */
    protected abstract int getFragmentCotainerId();

    /**
     * 如果有需要使用Fragment就在这里初始化Fragment
     */
    protected abstract void initFragment();

    /**
     * Method: fragment切换(Fragment的名字不能相同)
     * Creator: LiYing
     * Date: 2017/3/22 0022 下午 3:54
     */
    protected void switchFragmentNoBack(BaseFragment fragmentInstance) {
        currentFragment = fragmentInstance;
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments != null) {
            for (int i = 0; i < fragments.size(); i++) {
                BaseFragment tempFragment = (BaseFragment) getSupportFragmentManager().findFragmentByTag(fragments.get(i).getClass().getName());
                if (tempFragment != null) {
                    if (tempFragment.getClass().getName().equals(fragmentInstance.getClass().getName())) {
                        t.show(tempFragment);
                    } else {
                        t.hide(tempFragment);
                    }
                }
            }
        }
        Fragment fragmentTarget = getSupportFragmentManager().findFragmentByTag(fragmentInstance.getClass().getName());
        if (fragmentTarget == null && !fragmentInstance.isAdded()) {
            t.add(getFragmentCotainerId(), fragmentInstance, fragmentInstance.getClass().getName());
        }
        t.commitAllowingStateLoss();
        getSupportFragmentManager().executePendingTransactions();
    }

    /**
     * 退出应用
     * called while exit app.
     */
    public void exit() {
        ActivityStackManager.getActivityStackManager().finishAllActivity();//remove all activity.
        System.exit(0);//system exit.
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        hideDialog(smallDialog);
        super.onStop();
    }


    @Override
    protected void onDestroy() {
        // 一些销毁  等等

        super.onDestroy();
        ActivityStackManager.getActivityStackManager().popActivity(this);
    }

    /**
     * 初始化titleView
     */
    protected void findTitleViews() {
        LinearLayout lin_left = findViewById(R.id.lin_left);
        lin_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleName = findViewById(R.id.title_name);
        tv_right = findViewById(R.id.tv_right);
        iv_right = findViewById(R.id.iv_right);
    }

    /**
     * 隐藏对话框 （防止窗体溢出）
     *
     * @param mDialog
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void hideDialog(Dialog mDialog) {
        if (mDialog != null && mDialog.isShowing()) {
            Context context = ((ContextWrapper) mDialog.getContext()).getBaseContext();
            if (context instanceof Activity) {
                if (!((Activity) context).isFinishing() && !((Activity) context).isDestroyed())
                    mDialog.dismiss();
            } else {
                mDialog.dismiss();
            }
//				mDialog = null;
        }
    }

    /**
     * 显示对话框（防止窗体溢出）
     *
     * @param mDialog
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void showDialog(Dialog mDialog) {
        if (mDialog != null) {
            Context context = ((ContextWrapper) mDialog.getContext()).getBaseContext();
            if (context instanceof Activity) {
                if (!((Activity) context).isFinishing() && !((Activity) context).isDestroyed())
                    mDialog.show();
            } else {
                mDialog.show();
            }
        }
    }
}
