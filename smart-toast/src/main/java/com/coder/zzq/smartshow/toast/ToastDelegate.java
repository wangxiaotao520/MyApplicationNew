package com.coder.zzq.smartshow.toast;

import androidx.annotation.RestrictTo;

import com.coder.zzq.smartshow.toast.core.SmartShow;


/**
 * Created by 朱志强 on 2018/9/8.
 */
@RestrictTo(RestrictTo.Scope.LIBRARY)
public final class ToastDelegate {
    private ToastSettingImpl mToastSetting;
    private PlainToastManager mPlainToastManager;
    private TypeToastManager mTypeToastManager;
    private MyTypeToastManager myTypeToastManager;


    private ToastDelegate() {

    }

    public ToastSettingImpl createToastSetting() {
        if (mToastSetting == null) {
            mToastSetting = new ToastSettingImpl();
        }
        return mToastSetting;
    }


    protected boolean hasToastSetting() {
        return mToastSetting != null;
    }


    public boolean isDismissOnLeave() {
        return hasToastSetting() && getToastSetting().isDismissOnleave();
    }

    public boolean isShowing() {
        return isPlainShowing() || isTypeShowing()||isMyTypeShowing();
    }

    public void dismiss() {
        if (isPlainShowing()) {
            getPlainShowManager().dismiss();
        }

        if (isTypeShowing()) {
            getTypeShowManager().dismiss();
        }
        if (isMyTypeShowing()) {
            getMyTypeShowManager().dismiss();
        }
    }

    protected boolean isTypeShowing() {
        return mTypeToastManager != null && mTypeToastManager.isShowing();
    }
    protected boolean isMyTypeShowing() {
        return myTypeToastManager != null && myTypeToastManager.isShowing();
    }

    protected boolean isPlainShowing() {
        return mPlainToastManager != null && mPlainToastManager.isShowing();
    }


    protected PlainToastManager getPlainShowManager() {
        if (mPlainToastManager == null) {
            mPlainToastManager = new PlainToastManager();
        }

        return mPlainToastManager;
    }

    protected TypeToastManager getTypeShowManager() {
        if (mTypeToastManager == null) {
            mTypeToastManager = new TypeToastManager();
        }
        return mTypeToastManager;
    }
    protected MyTypeToastManager getMyTypeShowManager() {
        if (myTypeToastManager == null) {
            myTypeToastManager = new MyTypeToastManager();
        }
        return myTypeToastManager;
    }


    public void show(CharSequence msg) {

        getPlainShowManager().show(msg);
    }


    public void showAtTop(CharSequence msg) {

        getPlainShowManager().showAtTop(msg);
    }


    public void showInCenter(CharSequence msg) {

        getPlainShowManager().showInCenter(msg);
    }


    public void showAtLocation(CharSequence msg, int gravity, float xOffsetDp, float yOffsetDp) {

        getPlainShowManager().showAtLocation(msg, gravity, xOffsetDp, yOffsetDp);
    }


    public void showLong(CharSequence msg) {

        getPlainShowManager().showLong(msg);
    }


    public void showLongAtTop(CharSequence msg) {

        getPlainShowManager().showLongAtTop(msg);
    }


    public void showLongInCenter(CharSequence msg) {

        getPlainShowManager().showLongInCenter(msg);
    }


    public void showLongAtLocation(CharSequence msg, int gravity, float xOffsetDp, float yOffsetDp) {

        getPlainShowManager().showLongAtLocation(msg, gravity, xOffsetDp, yOffsetDp);
    }

    protected void dismissTypeShowIfNeed() {
        if (mTypeToastManager != null) {
            mTypeToastManager.dismiss();
        }
    }

    protected void dismissMyTypeShowIfNeed() {
        if (myTypeToastManager != null) {
            myTypeToastManager.dismiss();
        }
    }


    public void info(String msg) {

        getTypeShowManager().info(msg);
    }


    public void infoLong(String msg) {
        getTypeShowManager().infoLong(msg);
    }


    public void warning(String msg) {
        getTypeShowManager().warning(msg);
    }


    public void warningLong(String msg) {
        getTypeShowManager().warningLong(msg);
    }


    public void success(String msg) {
        getTypeShowManager().success(msg);
    }


    public void successLong(String msg) {
        getTypeShowManager().successLong(msg);
    }

    public void error(String msg) {
        getTypeShowManager().error(msg);
    }


    public void errorLong(String msg) {
        getTypeShowManager().errorLong(msg);
    }

    public void dismissPlainShowIfNeed() {
        if (mPlainToastManager != null) {
            mPlainToastManager.dismiss();
        }
    }


    private static ToastDelegate sSmartToastDelegate;

    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public static ToastDelegate get() {
        SmartShow.getContext();
        if (sSmartToastDelegate == null) {
            sSmartToastDelegate = new ToastDelegate();
            SmartShow.setToastCallback(new ToastCallback());
        }

        return sSmartToastDelegate;
    }

    public ToastSettingImpl getToastSetting() {
        return mToastSetting;
    }


    public static boolean hasCreated() {
        return sSmartToastDelegate != null;
    }
}
