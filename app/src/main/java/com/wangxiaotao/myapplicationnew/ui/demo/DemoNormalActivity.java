package com.wangxiaotao.myapplicationnew.ui.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coder.zzq.smartshow.toast.SmartToast;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wangxiaotao.myapplicationnew.R;
import com.wangxiaotao.myapplicationnew.http.MyOkHttp;
import com.wangxiaotao.myapplicationnew.http.response.JsonResponseHandler;
import com.wangxiaotao.myapplicationnew.ui.base.BaseActivity;
import com.wangxiaotao.myapplicationnew.ui.demo.model.ModelServiceCat;
import com.wangxiaotao.myapplicationnew.ui.photo.PhotoViewPagerAcitivity;
import com.wangxiaotao.myapplicationnew.utils.baseadapter_listview.CommonAdapter;
import com.wangxiaotao.myapplicationnew.utils.baseadapter_listview.ViewHolder;
import com.wangxiaotao.myapplicationnew.utils.json.JsonUtil;
import com.wangxiaotao.myapplicationnew.utils.statusbar.StatusBarView;
import com.wangxiaotao.myapplicationnew.view.loadmorelistview.PagingListView;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Description: 测试封装的功能的activity
 * created by wangxiaotao
 * 2020/5/6 0006 下午 3:06
 */
public class DemoNormalActivity extends BaseActivity {
    StatusBarView mStatusbar;
    protected int page = 1;
    protected SmartRefreshLayout mRefreshLayout;
    protected PagingListView mListview;
    protected RelativeLayout mRelNoData;
    private List<ModelServiceCat> mDatas = new ArrayList<>();
    private CommonAdapter<ModelServiceCat> commonAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        isStatusBar = true;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        mStatusbar = findViewById(R.id.mStatusbar);
        findTitleViews();
        titleName.setText("DemoNormalActivity");
        mListview = findViewById(R.id.listview);
        mRefreshLayout = findViewById(R.id.refreshLayout);
        mRelNoData = findViewById(R.id.rel_no_data);
        mRefreshLayout.setEnableRefresh(true);
        mRefreshLayout.setEnableLoadMore(false);
        commonAdapter = new CommonAdapter<ModelServiceCat>(this, R.layout.item_demo_activity, mDatas) {
            @Override
            protected void convert(ViewHolder viewHolder, ModelServiceCat item, int position) {
                viewHolder.<TextView>getView(R.id.tv_text).setText(item.getName());
            }
        };
        mListview.setAdapter(commonAdapter);
        mListview.setHasMoreItems(false);
    }

    @Override
    protected void initData() {
        showDialog(smallDialog);
        requestData();
    }

    private void requestData() {

        String API_SERVICE_URL = "http://m.hui-shenghuo.cn/service/" + "index/serviceClassif";
        MyOkHttp.get().post(API_SERVICE_URL, new HashMap<String, String>(), new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) {
                hideDialog(smallDialog);
                mRefreshLayout.finishRefresh();
                mListview.setIsLoading(false);
                if (JsonUtil.getInstance().isSuccess(response)) {
                    List<ModelServiceCat> list = (List<ModelServiceCat>) JsonUtil.getInstance().getDataArrayByName(response, "data", ModelServiceCat.class);
                    if (list.size() > 0) {
                        //todo  测试呢,随便写的

                        if (page == 1) {
                            mDatas.clear();
                            mListview.setHasMoreItems(true);
                        } else {
                            mListview.setHasMoreItems(false);
                        }
                        page++;
                        mDatas.addAll(list);
                        commonAdapter.notifyDataSetChanged();
                    }
                }

            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                hideDialog(smallDialog);
                mRefreshLayout.finishLoadMore();
                mListview.setIsLoading(false);
                SmartToast.showInfo("网络异常");
            }
        });

    }

    @Override
    protected void initListener() {
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                mListview.post(new Runnable() {
                    @Override
                    public void run() {
                        mListview.setSelection(0);
                    }
                });
                requestData();
            }
        });
        mListview.setPagingableListener(new PagingListView.Pagingable() {
            @Override
            public void onLoadMoreItems() {
                requestData();
            }
        });
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //测试跳转页面
                String url1 = "http://img.hui-shenghuo.cn/huacheng_property/property/houses/19/06/11/5cff0096c618e.jpeg";
                String url2 = "http://img.hui-shenghuo.cn/huacheng_property/property/houses/19/06/11/5cff0098356be.jpeg";
                String url3 = "http://img.hui-shenghuo.cn/huacheng_property/property/houses/19/06/11/5cff00990ec6a.jpeg";
                ArrayList<String> imgs = new ArrayList<>();
                imgs.add(url1);
                imgs.add(url2);
                imgs.add(url3);
                Intent intent = new Intent(DemoNormalActivity.this, PhotoViewPagerAcitivity.class);
                intent.putExtra("img_list", imgs);
                intent.putExtra("position", 0);
                intent.putExtra("isShowDelete", false);//是否显示删除,本地图片时候用
                startActivity(intent);

            }
        }
        );
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_normal;
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

    @Override
    public void finish() {
        super.finish();

        EventBus.getDefault().post(new ModelServiceCat());
    }
}
