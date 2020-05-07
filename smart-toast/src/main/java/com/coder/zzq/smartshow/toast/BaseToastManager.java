package com.coder.zzq.smartshow.toast;

import android.os.Build;
import android.os.Handler;
import androidx.core.view.ViewCompat;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.coder.zzq.smartshow.toast.core.Utils;

import java.lang.reflect.Field;

public abstract class BaseToastManager {
    public static final int PLAIN_TOAST = 1;
    public static final int TYPE_TOAST = 2;
    public static final int CUSTOM_TOAST = 3;
    protected Toast mToast;
    protected CharSequence mCurMsg;
    protected int mDuration;
    protected View mView;
    protected TextView mMsgView;
    protected Object mTn;
    protected WindowManager.LayoutParams mWindowParams;


    public BaseToastManager() {
        mToast = createToast();
        setupReflectInfo();
        setupToast();
    }


    protected abstract Toast createToast();

    protected void rebuildToast() {
        mToast = createToast();
        setupReflectInfo();
        setupToast();
    }

    protected void updateToast() {
        mMsgView.setText(mCurMsg);
    }

    protected void setupReflectInfo() {
        try {
            Field tnField = Toast.class.getDeclaredField("mTN");
            tnField.setAccessible(true);
            mTn = tnField.get(mToast);

            Field windowParamsField = mTn.getClass().getDeclaredField("mParams");
            windowParamsField.setAccessible(true);
            mWindowParams = (WindowManager.LayoutParams) windowParamsField.get(mTn);

            if (isSdk25()) {
                Field handlerField = mTn.getClass().getDeclaredField("mHandler");
                handlerField.setAccessible(true);
                Handler handlerOfTn = (Handler) handlerField.get(mTn);
                handlerField.set(mTn, new SafeHandler(handlerOfTn));
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    protected void setupToast() {
        mCurMsg = "";
        mDuration = Toast.LENGTH_SHORT;
    }

    protected boolean isShowing() {
        if (Utils.isNotificationPermitted()) {
            return ViewCompat.isAttachedToWindow(mView);
        } else {
            return VirtualToastManager.get().isShowing(getToastType());
        }
    }

    protected abstract int getToastType();


    public void dismiss() {
        mToast.cancel();
        rebuildToast();
        if (!Utils.isNotificationPermitted()){
            VirtualToastManager.get().dismiss(getToastType());
        }
    }

    public void showToast() {
        if (Utils.isNotificationPermitted()) {
            mToast.show();
        } else{
            VirtualToastManager.get().show(getToastType(),mToast,mWindowParams);
        }
    }

    protected boolean isSdk25() {
        return Build.VERSION.SDK_INT == Build.VERSION_CODES.N_MR1;
    }

}
