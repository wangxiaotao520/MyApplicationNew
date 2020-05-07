package com.coder.zzq.smartshow.toast;

import android.graphics.drawable.GradientDrawable;
import androidx.annotation.RestrictTo;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.coder.zzq.smartshow.toast.core.SmartShow;


@RestrictTo(RestrictTo.Scope.LIBRARY)
public class TypeToastManager extends BaseToastManager implements ITypeShow {

    public static final int TYPE_INFO_NORMAL = 0;
    public static final int TYPE_INFO_WARNING = 1;
    public static final int TYPE_INFO_SUCCESS = 2;
    public static final int TYPE_INFO_ERROR = 3;
    public static final int TYPE_INFO_FAIL = 4;
    public static final int TYPE_INFO_COMPLETE = 5;
    public static final int TYPE_INFO_FORBID = 6;
    public static final int TYPE_INFO_WAITING = 7;

    private int mCurInfoType;
    private ImageView mIconView;
    private int mCurIcon;

    public TypeToastManager() {
        super();
    }

    @Override
    protected Toast createToast() {
        mToast = new Toast(SmartShow.getContext());
        mView = LayoutInflater.from(SmartShow.getContext()).inflate(R.layout.layout_type_info, null);
        mMsgView = mView.findViewById(R.id.type_info_message);
        mIconView = mView.findViewById(R.id.type_info_icon);
        mToast.setView(mView);
        return mToast;
    }


    @Override
    protected void updateToast() {
        super.updateToast();
        mIconView.setImageResource(mCurIcon);
    }

    @Override
    protected void setupToast() {
        super.setupToast();
        mCurInfoType = TYPE_INFO_NORMAL;
        mCurIcon = R.drawable.type_info_normal;
        mWindowParams.windowAnimations = R.style.type_info_toast_anim;
        mWindowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        if (ToastDelegate.get().hasToastSetting()
                && ToastDelegate.get().getToastSetting().isTypeInfoThemeColorSetup()) {
            GradientDrawable drawable = (GradientDrawable) mView.getBackground();
            drawable.setColor(ToastDelegate.get().getToastSetting().getTypeInfoThemeColor());
        }
    }

    @Override
    protected int getToastType() {
        return TYPE_TOAST;
    }

    private final void showHelper(CharSequence msg, int infoType, int duration) {
        ToastDelegate.get().dismissPlainShowIfNeed();
        msg = (msg == null) ? "" : msg;
        //文本是否改变
        boolean contentChanged = !mCurMsg.equals(msg.toString().trim());

        //类型是否改变
        boolean typeChanged = mCurInfoType != infoType;


        boolean needInvokeShow = !isShowing();
        if (isShowing() && (contentChanged || typeChanged)) {
            dismiss();
            needInvokeShow = true;
        }

        mCurMsg = msg;
        mDuration = duration;
        mCurInfoType = infoType;
        mCurIcon = getIcon(mCurInfoType);

        updateToast();

        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.setDuration(mDuration);
        if (needInvokeShow) {
            showToast();
        }
    }

    private final int getIcon(int infoType) {
        switch (infoType) {
            case TYPE_INFO_NORMAL:
                return R.drawable.type_info_normal;
            case TYPE_INFO_WARNING:
                return R.drawable.type_info_warning;
            case TYPE_INFO_SUCCESS:
                return R.drawable.type_info_success;
            case TYPE_INFO_ERROR:
                return R.drawable.type_info_error;
            case TYPE_INFO_FAIL:
                return R.drawable.type_info_fail;
            case TYPE_INFO_COMPLETE:
                return R.drawable.type_info_complete;
            case TYPE_INFO_WAITING:
                return R.drawable.type_info_waiting;
            case TYPE_INFO_FORBID:
                return R.drawable.type_info_forbid;
            default:
                return R.drawable.type_info_normal;
        }
    }

    @Override
    public void info(CharSequence msg) {
        showHelper(msg, TYPE_INFO_NORMAL, Toast.LENGTH_SHORT);
    }

    @Override
    public void infoLong(CharSequence msg) {
        showHelper(msg, TYPE_INFO_NORMAL, Toast.LENGTH_LONG);
    }

    @Override
    public void warning(CharSequence msg) {
        showHelper(msg, TYPE_INFO_WARNING, Toast.LENGTH_SHORT);
    }

    @Override
    public void warningLong(CharSequence msg) {
        showHelper(msg, TYPE_INFO_WARNING, Toast.LENGTH_LONG);
    }

    @Override
    public void success(CharSequence msg) {
        showHelper(msg, TYPE_INFO_SUCCESS, Toast.LENGTH_SHORT);
    }

    @Override
    public void successLong(CharSequence msg) {
        showHelper(msg, TYPE_INFO_SUCCESS, Toast.LENGTH_LONG);
    }

    @Override
    public void error(CharSequence msg) {
        showHelper(msg, TYPE_INFO_ERROR, Toast.LENGTH_SHORT);
    }

    @Override
    public void errorLong(CharSequence msg) {
        showHelper(msg, TYPE_INFO_ERROR, Toast.LENGTH_LONG);
    }

    @Override
    public void fail(CharSequence msg) {
        showHelper(msg, TYPE_INFO_FAIL, Toast.LENGTH_SHORT);
    }

    @Override
    public void failLong(CharSequence msg) {
        showHelper(msg, TYPE_INFO_FAIL, Toast.LENGTH_LONG);
    }

    @Override
    public void complete(CharSequence msg) {
        showHelper(msg, TYPE_INFO_COMPLETE, Toast.LENGTH_SHORT);
    }

    @Override
    public void completeLong(CharSequence msg) {
        showHelper(msg, TYPE_INFO_COMPLETE, Toast.LENGTH_LONG);
    }

    @Override
    public void forbid(CharSequence msg) {
        showHelper(msg, TYPE_INFO_FORBID, Toast.LENGTH_SHORT);
    }

    @Override
    public void forbidLong(CharSequence msg) {
        showHelper(msg, TYPE_INFO_FORBID, Toast.LENGTH_LONG);
    }

    @Override
    public void waiting(CharSequence msg) {
        showHelper(msg, TYPE_INFO_WAITING, Toast.LENGTH_SHORT);
    }

    @Override
    public void waitingLong(CharSequence msg) {
        showHelper(msg, TYPE_INFO_WAITING, Toast.LENGTH_LONG);
    }

}
