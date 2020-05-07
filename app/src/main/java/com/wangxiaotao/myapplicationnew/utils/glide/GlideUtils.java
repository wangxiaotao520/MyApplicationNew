package com.wangxiaotao.myapplicationnew.utils.glide;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.wangxiaotao.myapplicationnew.R;
import com.wangxiaotao.myapplicationnew.utils.TDevice;

/**
 * Glide加载工具类
 */
public class GlideUtils {

    private String TAG = "GlideUtils";

    /**
     * 借助内部类 实现线程安全的单例模式
     * 属于懒汉式单例，因为Java机制规定，内部类SingletonHolder只有在getInstance()
     * 方法第一次调用的时候才会被加载（实现了lazy），而且其加载过程是线程安全的。
     * 内部类加载的时候实例化一次instance。
     */
    public GlideUtils() {
    }

    private static class GlideLoadUtilsHolder {
        private final static GlideUtils INSTANCE = new GlideUtils();
    }

    public static GlideUtils getInstance() {
        return GlideLoadUtilsHolder.INSTANCE;
    }


    /**
     * 加载本地资源图片
     * @param view
     * @param resourceId  本地资源id
     * @param placeHolder
     * @param isRound
     * @param target
     */
    public  void showImage(Context mContext,ImageView view, int resourceId, int placeHolder,int isRound, Target target) {
        RequestOptions options = (new RequestOptions()).placeholder(placeHolder).error(placeHolder).priority(Priority.HIGH);
        if (isRound == 1) {
            options.transform(new GlideCircleStrokeTransform()); //圆形带有边框效果
        } else if (isRound == 2) {
            options.circleCrop();//圆形无边框效果
        } else if (isRound == 3) {
            //自定义所加载View的弧度
            options.transforms(new Transformation[]{new CenterCrop(), new RoundedCorners(TDevice.dip2px(mContext,6))});
        }

        RequestBuilder builder = Glide.with(mContext).load(resourceId).apply(options);
        if (target == null) {
            builder.into(view);
        } else {
            builder.into(target);
        }
    }



    /**
     *  加载网络图片
     * @param view
     * @param url
     * @param placeHolder 占位图
     * @param isRound 1圆形带有边框效果 2圆形无边框效果 3自定义所加载View的弧度
     * @param target 类似于回调 请求图片是否加载完成，或者加载失败
     */
    public  void showImage(Context mContext,ImageView view, String url, int placeHolder, int isRound, Target target) {
        RequestOptions options = (new RequestOptions()).placeholder(placeHolder).error(placeHolder).priority(Priority.HIGH);
        if (isRound == 1) {
            options.transform(new GlideCircleStrokeTransform());
            //边框的颜色和宽度自己定义完事，这里没有写具体的方法
        } else if (isRound == 2) {
            options.circleCrop();
        } else if (isRound == 3) {
            options.transforms(new Transformation[]{new CenterCrop(), new RoundedCorners(TDevice.dip2px(mContext,6))});
        }

        RequestBuilder builder = Glide.with(mContext).load(url).transition(DrawableTransitionOptions.withCrossFade()).apply(options);
        if (target == null) {
            builder.into(view);
        } else {
            builder.into(target);
        }
    }


    /**
     * 加载毛玻璃效果
     * @param view
     * @param url
     * @param target 可以为null
     */
    //加载毛玻璃效果,BlurTransformation()括号内数字为毛玻璃效果的程度
    public  void showGaosImage(Context mContext,ImageView view, String  url, Target target) {
        if (mContext!=null){
            //高斯模糊 范围在 0 -- 25 越大模糊程度越高
            RequestOptions options = (new RequestOptions()).diskCacheStrategy(DiskCacheStrategy.ALL).transforms(new Transformation[]{new BlurTransformation(mContext,14)}).priority(Priority.HIGH);
            RequestBuilder builder = Glide.with(mContext).load(url).apply(options);
            if (target == null) {
                builder.into(view);
            } else {
                builder.into(target);
            }
        }else {
            Log.e(TAG, "Picture loading failed,context is null");
        }
    }
    /**
     * 加载网络图片
     * @param view
     * @param url
     *
     */
    public  void showNetImage(Context mContext,ImageView view, String url) {
        if (mContext!=null){
            if (!TextUtils.isEmpty(url)) {
                showImage(mContext,view, url,R.color.default_color, 0, null);
            }
        }else {

        }
    }

    /**
     * 加载网络图片 带target
     * @param view
     * @param url
     * @param target
     */
    public  void showNetImageWithTarget(Context mContext,ImageView view, String url, Target target) {
        if (mContext!=null){
            if (!TextUtils.isEmpty(url)) {
                showImage(mContext,view, url, R.color.default_color,0, target);
            }
        }else {

        }
    }

    /**
     * 加载网络圆形图片
     * @param view
     * @param url
     */
    public  void showRoundImage(Context mContext,ImageView view, String url) {
        if (mContext!=null){
            if (!TextUtils.isEmpty(url)) {
                showImage(mContext,view, url, R.drawable.shape_oval_grey_default_color, 2, (Target)null);
            }
        }else {

        }
    }
    /**
     * 加载网络圆角图片
     * @param view
     * @param url
     */
    public  void showRoundCornerImage(Context mContext,ImageView view, String url) {
        if (mContext!=null){
            if (!TextUtils.isEmpty(url)) {
                showImage(mContext,view, url,R.drawable.shape_rectange_grey_default_color, 3, (Target)null);
            }
        }
    }
    /**
     * 加载本地图片
     * @param view
     * @param resourceId
     */
    public  void showLocalImage(Context mContext,ImageView view, int resourceId) {
        if (mContext!=null){
            showImage(mContext,view, resourceId,R.color.default_color ,0, null);
        }
    }
}