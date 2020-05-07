package com.wangxiaotao.myapplicationnew.ui.base;

import android.app.Activity;

import java.util.Stack;

/**
 * Description: Activity管理类
 * Author: wangxiaotao
 * Create: 2018/6/9 0009 17:21
 */
public class ActivityStackManager {
    private static Stack<Activity> activityStack;
    private static ActivityStackManager instance;

    private ActivityStackManager() {
    }

    public synchronized static ActivityStackManager getActivityStackManager() {
        if (instance == null) {
            instance = new ActivityStackManager();
        }
        return instance;
    }

    /**
     * 关闭activity
     * finish the activity and remove it from stack.
     *
     * @param activity
     */
    public void popActivity(Activity activity) {
        try{
            if (activity != null) {
                activityStack.remove(activity);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 获取当前的Activity
     * get the current activity.
     *
     * @return
     */
    public Activity currentActivity() {
        if (activityStack == null || activityStack.isEmpty()) return null;
        Activity activity = (Activity) activityStack.lastElement();
        return activity;
    }

    /**
     * 获取最后一个的Activity
     * get the first activity in the stack.
     *
     * @return
     */
    public Activity firstActivity() {
        if (activityStack == null || activityStack.isEmpty()) return null;
        Activity activity = (Activity) activityStack.firstElement();
        return activity;
    }


    /**
     * 添加activity到Stack
     * add the activity to the stack.
     *
     * @param activity
     */
    public void pushActivity(Activity activity) {
        try {
            if (activityStack == null) {
                activityStack = new Stack<Activity>();
            }
            activityStack.add(activity);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * remove所有Activity
     * remove all activity.
     */
    public void finishAllActivity() {
        if (activityStack != null){
            for (Activity activity : activityStack) {
                activity.finish();
            }
            activityStack.clear();
        }
    }
}