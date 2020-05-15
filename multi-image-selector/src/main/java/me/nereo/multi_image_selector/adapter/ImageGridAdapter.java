package me.nereo.multi_image_selector.adapter;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.nereo.multi_image_selector.R;
import me.nereo.multi_image_selector.bean.Image;

//import com.squareup.picasso.Picasso;

/**
 * 图片Adapter
 * Created by Nereo on 2015/4/7.
 * Updated by nereo on 2016/1/19.
 */
public class ImageGridAdapter extends BaseAdapter {

    private static final int TYPE_CAMERA = 0;
    private static final int TYPE_NORMAL = 1;
    private static final int TYPE_VIDEO = 2;
    private static final int TYPE_LOCAL_VIDEO = 3;

    private Context mContext;

    private LayoutInflater mInflater;
    private boolean showCamera = true;
    private boolean showVideo = false;
    private boolean showSelectIndicator = true;

    private List<Image> mImages = new ArrayList<>();
    private List<Image> mSelectedImages = new ArrayList<>();

    final int mGridWidth;

    public ImageGridAdapter(Context context, boolean showCamera, boolean showVideo, int column) {
        mContext = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.showCamera = showCamera;
        this.showVideo = showVideo;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            Point size = new Point();
            wm.getDefaultDisplay().getSize(size);
            width = size.x;
        } else {
            width = wm.getDefaultDisplay().getWidth();
        }
        mGridWidth = width / column;
    }

    /**
     * 显示选择指示器
     *
     * @param b
     */
    public void showSelectIndicator(boolean b) {
        showSelectIndicator = b;
    }

    public void setShowCamera(boolean b) {
        if (showCamera == b) return;

        showCamera = b;
        notifyDataSetChanged();
    }

    public void setShowVideo(boolean b) {
        if (showVideo == b) return;

        showVideo = b;
        notifyDataSetChanged();
    }

    public boolean isShowVideo() {
        return showVideo;
    }

    public boolean isShowCamera() {
        return showCamera;
    }

    /**
     * 选择某个图片，改变选择状态
     *
     * @param image
     */
    public void select(Image image) {
        if (mSelectedImages.contains(image)) {
            mSelectedImages.remove(image);
        } else {
            mSelectedImages.add(image);
        }
        notifyDataSetChanged();
    }

    /**
     * 通过图片路径设置默认选择
     *
     * @param resultList
     */
    public void setDefaultSelected(ArrayList<String> resultList) {
        for (String path : resultList) {
            Image image = getImageByPath(path);
            if (image != null) {
                mSelectedImages.add(image);
            }
        }
        if (mSelectedImages.size() > 0) {
            notifyDataSetChanged();
        }
    }

    private Image getImageByPath(String path) {
        if (mImages != null && mImages.size() > 0) {
            for (Image image : mImages) {
                if (image.path.equalsIgnoreCase(path)) {
                    return image;
                }
            }
        }
        return null;
    }

    /**
     * 设置数据集
     *
     * @param images
     */
    public void setData(List<Image> images) {
        mSelectedImages.clear();

        if (images != null && images.size() > 0) {
            mImages = images;
        } else {
            mImages.clear();
        }
        notifyDataSetChanged();
    }

    public void setDataByPath(List<String> paths) {
        mSelectedImages.clear();
        for (String path : paths) {
            Image image = getImageByPath(path);
            if (image != null) {
                mSelectedImages.add(image);
            }
        }
//        if(mSelectedImages.size() > 0){
        notifyDataSetChanged();
//        }
    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        if (showCamera) {
            if (position == 0) {
                return TYPE_CAMERA;
            }
            /*if(showVideo&&position==1){
                return TYPE_VIDEO;
            }*/
            if (showVideo) {//显示视频才显示选择视频选项
                if (position == 1) {
                    return TYPE_VIDEO;
                }
                if (position == 2) {
                    return TYPE_LOCAL_VIDEO;
                }
            }
        } else {
            if (showVideo) {
                if (position == 0) {
                    return TYPE_VIDEO;
                }
                if (position == 1) {
                    return TYPE_LOCAL_VIDEO;
                }
            }
        }
        return TYPE_NORMAL;
    }

    @Override
    public int getCount() {
        if (showCamera && showVideo) {
            return mImages.size() + 3;
        }
        if (showCamera && !showVideo) {
            return mImages.size() + 1;
        }
        if (!showCamera && showVideo) {
            return mImages.size() + 2;
        }
        return mImages.size();
    }

    @Override
    public Image getItem(int i) {
        if (showCamera) {
            if (showVideo) {
                if (i == 0 || i == 1 || i == 2) {
                    return null;
                } else {
                    return mImages.get(i - 3);
                }
            } else {
                if (i == 0) {
                    return null;
                }
            }
            return mImages.get(i - 1);
        } else {
            if (showVideo) {
                if (i == 0 || i == 1) {
                    return null;
                } else {
                    return mImages.get(i - 2);
                }
            }
            return mImages.get(i);
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
//
//        if(isShowCamera()){
//            if(i == 0){
//                view = mInflater.inflate(R.layout.list_item_camera, viewGroup, false);
//                return view;
//            }
//            if(isShowVideo()){
//                if(i==1){
//                    view = mInflater.inflate(R.layout.list_item_video, viewGroup, false);
//                    return view;
//                }
//                if(i==2){
//                    view = mInflater.inflate(R.layout.list_item_native_video, viewGroup, false);
//                    return view;
//                }
//            }
//        }else {
//            if (isShowVideo()) {
//                if (i == 0) {
//                    view = mInflater.inflate(R.layout.list_item_video, viewGroup, false);
//                    return view;
//                }
//                if (i == 1) {
//                    view = mInflater.inflate(R.layout.list_item_native_video, viewGroup, false);
//                    return view;
//                }
//            }
//        }
//        ViewHolder holder;
//        if(view == null){
//            view = mInflater.inflate(R.layout.list_item_image1, viewGroup, false);
//            holder = new ViewHolder(view);
//        }else{
//            holder = (ViewHolder) view.getTag();
//        }
//
//        if(holder != null) {
//            holder.bindData(getItem(i));
//        }
//
//        return view;

        int itemViewType = getItemViewType(i);
        if (itemViewType==TYPE_CAMERA){
            if (view==null){
                view = mInflater.inflate(R.layout.list_item_camera, viewGroup, false);
            }
            return view;
        }else if (itemViewType==TYPE_VIDEO){
            if (view==null){
                view = mInflater.inflate(R.layout.list_item_video, viewGroup, false);
            }
            return view;
        }else if (itemViewType==TYPE_LOCAL_VIDEO){
            if (view==null){
                view = mInflater.inflate(R.layout.list_item_native_video, viewGroup, false);
            }
            return view;
        }else  {
            ViewHolder holder;
            if(view == null){
            view = mInflater.inflate(R.layout.list_item_image1, viewGroup, false);
            holder = new ViewHolder(view);
        }else{
            holder = (ViewHolder) view.getTag();
        }
            holder.bindData(getItem(i));
            return view;
        }
    }

    class ViewHolder {
        ImageView image;
        ImageView indicator;
        View mask;

        ViewHolder(View view) {
            image = (ImageView) view.findViewById(R.id.image);
            indicator = (ImageView) view.findViewById(R.id.checkmark);
            mask = view.findViewById(R.id.mask);
            view.setTag(this);
        }

        void bindData(final Image data) {
            if (data == null) return;
            // 处理单选和多选状态
            if (showSelectIndicator) {
                indicator.setVisibility(View.VISIBLE);
                if (mSelectedImages.contains(data)) {
                    // 设置选中状态
                    indicator.setImageResource(R.drawable.btn_selected);
                    mask.setVisibility(View.VISIBLE);
                } else {
                    // 未选择
                    indicator.setImageResource(R.drawable.btn_unselected);
                    mask.setVisibility(View.GONE);
                }
            } else {
                indicator.setVisibility(View.GONE);
            }
            File imageFile = new File(data.path);
            if (imageFile.exists()) {
                // 显示图片
                Glide.with(mContext)
                        .load(imageFile)
                        .placeholder(R.color.default_color)
                        .centerCrop()
                        .crossFade()
                        .into(image);


            } else {
              image.setImageResource(R.color.default_color);
               // image.setImageResource(R.drawable.default_error);
            }
        }
    }

}
