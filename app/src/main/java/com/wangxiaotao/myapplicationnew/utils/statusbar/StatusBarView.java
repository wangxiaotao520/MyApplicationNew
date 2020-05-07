package com.wangxiaotao.myapplicationnew.utils.statusbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.wangxiaotao.myapplicationnew.utils.TDevice;

import androidx.annotation.Nullable;

/**
 * Description: statusbar的view
 * created by wangxiaotao
 * 2020/5/6 0006 下午 2:59
 */
public class StatusBarView extends LinearLayout {
    public StatusBarView(Context context) {
        super(context);
        init();
    }

    public StatusBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public StatusBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, TDevice.getStatuBarHeight(this.getContext()));
        View statusbar = new View(this.getContext());
        this.addView(statusbar,layoutParams);
    }

}
