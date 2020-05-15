package com.wangxiaotao.myapplicationnew.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;

/**
 * Description: 一些不知道放在哪里的工具类方法
 * created by wangxiaotao
 * 2020/5/14 0014 11:07
 */
public class CommonUtils {

    /**
     * 判断是release包还是debug
     * @param context
     * @return
     */
    public static boolean isApkDebugable(Context context) {
        try {
            ApplicationInfo info= context.getApplicationInfo();
            return (info.flags&ApplicationInfo.FLAG_DEBUGGABLE)!=0;
        } catch (Exception e) {

        }
        return false;
    }




}
