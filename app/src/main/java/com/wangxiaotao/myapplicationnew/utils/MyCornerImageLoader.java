package com.wangxiaotao.myapplicationnew.utils;

import android.content.Context;
import android.widget.ImageView;

import com.wangxiaotao.myapplicationnew.utils.glide.GlideUtils;
import com.wangxiaotao.myapplicationnew.view.RoundAngleImageView;
import com.youth.banner.loader.ImageLoader;

/**
 * Description: 角度的Banner 图片加载器
 * created by wangxiaotao
 * 2019/10/16 0016 下午 4:49
 */
public class MyCornerImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //   Glide.clear(imageView);
        //  Glide.with(context.getApplicationContext()).load(path).into(imageView);
        if (context!=null){
            GlideUtils.getInstance().showNetImage(context, imageView,(String) path);
        }

        //  imageView.setImageResource((Integer) path);
    }

    @Override
    public ImageView createImageView(Context context) {
        //圆角
        return new RoundAngleImageView(context);
    }
}
