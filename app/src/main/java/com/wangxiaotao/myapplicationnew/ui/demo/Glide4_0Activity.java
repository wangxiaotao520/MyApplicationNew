package com.wangxiaotao.myapplicationnew.ui.demo;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.wangxiaotao.myapplicationnew.R;
import com.wangxiaotao.myapplicationnew.ui.base.BaseActivity;
import com.wangxiaotao.myapplicationnew.utils.TDevice;
import com.wangxiaotao.myapplicationnew.utils.glide.BlackWhiteTransformation;
import com.wangxiaotao.myapplicationnew.utils.glide.BlurTransformation;
import com.wangxiaotao.myapplicationnew.utils.glide.GlideUtils;

/**
 * Description: 测试Glide的Activity
 * created by wangxiaotao
 * 2020/5/3 0003 下午 4:20
 */
public class Glide4_0Activity extends BaseActivity {


    private ImageView iv_img2;
    private ImageView iv_img7;
    String  img_url = "http://img.hui-shenghuo.cn/huacheng/product/20/03/25/thumb_5e7b1a864b995.jpg";
    String  img_url2 = "http://img.hui-shenghuo.cn/huacheng/product/20/03/25/thumb_5e7b142835c76.jpg";
    String  img_url3 = "http://img.hui-shenghuo.cn/huacheng/product/20/03/19/thumb_5e7348120d8db.jpg";
    private ImageView iv_img8;

    @Override
    protected void initView() {
        //普通
        ImageView iv_img1 = findViewById(R.id.iv_img1);
        //target
        iv_img2 = findViewById(R.id.iv_img2);
        //高斯模糊
        ImageView iv_img3 = findViewById(R.id.iv_img3);
        //圆形
        ImageView iv_img4 = findViewById(R.id.iv_img4);
        //本地
        ImageView iv_img5 = findViewById(R.id.iv_img5);
        //圆角
        ImageView iv_img6 = findViewById(R.id.iv_img6);

        //黑白图片
        iv_img7 = findViewById(R.id.iv_img7);
        //高斯+圆角
        iv_img8 = findViewById(R.id.iv_img8);



        GlideUtils.getInstance().showNetImage(this,iv_img1,img_url);
        GlideUtils.getInstance().showNetImageWithTarget(this, iv_img2, img_url2, new SimpleTarget<Drawable>(){

            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                iv_img2.setImageDrawable(resource);
            }

            @Override
            public void onLoadFailed(Drawable errorDrawable) {
                super.onLoadFailed(errorDrawable);
                iv_img2.setImageDrawable(errorDrawable);
            }
        });
        GlideUtils.getInstance().showGaosImage(this,iv_img3,img_url,null);
        GlideUtils.getInstance().showRoundImage(this,iv_img4,img_url2);
        GlideUtils.getInstance().showLocalImage(this,iv_img5,R.mipmap.bg_index_sell_house);
        GlideUtils.getInstance().showRoundCornerImage(this,iv_img6,img_url3);
        showWhiteBlackImg();
        showGaosiCorner();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_glide;
    }

    @Override
    protected void initIntentData() {

    }

    @Override
    protected int getFragmentCotainerId() {
        return 0;
    }

    @Override
    protected void initFragment() {

    }

    /**
     * 黑白图片 +圆角
     */
    private void showWhiteBlackImg() {
        MultiTransformation multi = new MultiTransformation(
                new BlackWhiteTransformation(),
                new RoundedCorners( TDevice.dip2px(this,6) ) //设置图片圆角角度
        );

        RequestOptions options = (new RequestOptions()).placeholder(R.drawable.shape_rectange_grey_default_color).error(R.drawable.shape_rectange_grey_default_color).priority(Priority.HIGH);
        options.transforms(multi);
        RequestBuilder builder = Glide.with(this).load(img_url3).transition(DrawableTransitionOptions.withCrossFade()).apply(options);
        builder.into(iv_img7);

    }
    /**
     * 高斯 +圆角
     */
    private void showGaosiCorner() {
        MultiTransformation multi = new MultiTransformation(
                new BlurTransformation(this,25),
                new RoundedCorners( TDevice.dip2px(this,6) ) //设置图片圆角角度
        );
        RequestOptions options = (new RequestOptions()).placeholder(R.drawable.shape_rectange_grey_default_color).error(R.drawable.shape_rectange_grey_default_color).priority(Priority.HIGH);
        options.transforms(multi);
        RequestBuilder builder = Glide.with(this).load(img_url3).transition(DrawableTransitionOptions.withCrossFade()).apply(options);
        builder.into(iv_img8);

    }
}
