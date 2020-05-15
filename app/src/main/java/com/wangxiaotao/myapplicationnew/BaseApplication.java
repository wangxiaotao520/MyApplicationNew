package com.wangxiaotao.myapplicationnew;

import android.app.Application;
import android.content.Context;

import com.coder.zzq.smartshow.toast.SmartToast;
import com.coder.zzq.smartshow.toast.core.SmartShow;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.wangxiaotao.myapplicationnew.utils.CommonUtils;
import com.wangxiaotao.myapplicationnew.utils.CrashHandler;
import com.wangxiaotao.myapplicationnew.utils.fresco.FrescoUtils;

/**
 * Description:自定义BaseApplication
 * created by wangxiaotao
 * 2020/5/6 0006 上午 9:39
 */
public class BaseApplication extends Application {
    public static Context mContext;
    private static BaseApplication mInstance;
    @Override
    public void onCreate() {
        super.onCreate();


        mContext=this.getApplicationContext();
        mInstance = this;
        //初始化Fresco
        FrescoUtils.getInstance().initializeFresco(this);
        //初始化smartshow
        SmartShow.init(this);
        SmartToast.setting()
//                .backgroundColorRes(R.color.colorPrimary)
                .dismissOnLeave(false);
        // 崩溃处理
        if (!CommonUtils.isApkDebugable(this)){
            CrashHandler.getCrashHander().init(this);
        }
    }
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                //    layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                  return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
              //  return new MaterialHeader(context).setColorSchemeColors(getApplication().getResources().getColor(R.color.orange));
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }
}
