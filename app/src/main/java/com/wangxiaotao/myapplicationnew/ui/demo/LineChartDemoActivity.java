package com.wangxiaotao.myapplicationnew.ui.demo;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.wangxiaotao.myapplicationnew.R;
import com.wangxiaotao.myapplicationnew.model.ModelStatisticsLine;
import com.wangxiaotao.myapplicationnew.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 折线Activity
 * created by wangxiaotao
 * 2020/5/13 0013 09:13
 */
public class LineChartDemoActivity extends BaseActivity implements OnChartValueSelectedListener {

    protected Typeface tfRegular;
    protected Typeface tfLight;
    private LineChart chart;

    protected List<ModelStatisticsLine> mDatas_chart= new ArrayList<>();//折线图   水
    protected List<ModelStatisticsLine> mDatas_chart2= new ArrayList<>();//折线图  电
    @Override
    protected void initView() {
        tfRegular = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        tfLight = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");

        chart = findViewById(R.id.chart);
        chart.setOnChartValueSelectedListener(this);
        chart.setVisibility(View.INVISIBLE);

        // no description text
        chart.getDescription().setEnabled(false);

        // enable touch gestures
        chart.setTouchEnabled(true);

        chart.setDragDecelerationFrictionCoef(0.9f);

        // enable scaling and dragging
        chart.setDragEnabled(true);//设置可以滑动
        chart.setScaleEnabled(false);
        chart.setDrawGridBackground(false);
        chart.setHighlightPerDragEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        chart.setPinchZoom(true);

        // set an alternative background color
        chart.setBackgroundColor(Color.WHITE);


        chart.animateX(500);

        chart.zoom(3f,1f,0,0);//todo 在这里设置图表放大多少倍 关键

        // get the legend (only possible after setting data)
        Legend l = chart.getLegend();

        // modify the legend ...
        l.setForm(Legend.LegendForm.CIRCLE);
        l.setTypeface(tfLight);
        l.setTextSize(11f);
        l.setTextColor(Color.parseColor("#333333"));
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setYOffset(5f);
        l.setDrawInside(false);
        l.setXEntrySpace(20f);
        l.setYEntrySpace(20f);
        l.setFormToTextSpace(10f);//设置 legend-form 和 legend-label 之间的空间。

        //横坐标
        XAxis xAxis = chart.getXAxis();
        xAxis.setTypeface(tfLight);
        xAxis.setTextSize(11f);
        xAxis.setTextColor(Color.parseColor("#999999"));
        xAxis.setDrawGridLines(true);
        xAxis.setGridColor(Color.parseColor("#AED9FF"));

        xAxis.setDrawAxisLine(true);
        xAxis.setDrawLabels(true);//绘制标签  指x轴上的对应数值
        xAxis.setLabelCount(7);
        xAxis.setGranularity(1f);//todo 这行代码一定要设置 否则x轴出问题   //缩放的时候有用，比如放大的时候，我不想把横轴的月份再细分
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的显示位置
        ValueFormatter xAxisFormatter = new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                int position = (int) value;
                //  在这里根据position 取出值 显示
                String time_x = mDatas_chart.get(position).getTime_x();
                return time_x+"";
            }
        };
        xAxis.setValueFormatter(xAxisFormatter);


        //设置X轴值为字符串
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setTypeface(tfLight);
        leftAxis.setTextColor(Color.parseColor("#999999"));
        leftAxis.setDrawGridLines(true);
        leftAxis.setGridColor(Color.parseColor("#AED9FF"));
        //设置Y轴坐标之间的最小间隔
        leftAxis.setGranularity(1);
        leftAxis.setGranularityEnabled(true);
        leftAxis.setDrawZeroLine(false);
        leftAxis.setLabelCount(10,true); //几个标签
        leftAxis.setAxisMaximum(100f);//  根据返回的数据修改
        leftAxis.setAxisMinimum(50f); //   根据返回的数据修改
        leftAxis.setAxisLineColor(getResources().getColor(R.color.transparent));



        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setTypeface(tfLight);
        rightAxis.setEnabled(false);//设置不可用
        rightAxis.setTextColor(Color.parseColor("#999999"));
        rightAxis.setDrawGridLines(false);
        rightAxis.setGridColor(Color.parseColor("#AED9FF"));
        //设置Y轴坐标之间的最小间隔
        rightAxis.setGranularity(1);
        rightAxis.setGranularityEnabled(true);
        rightAxis.setDrawZeroLine(false);
        rightAxis.setLabelCount(10,true);
        rightAxis.setAxisMaximum(500f);//  根据返回的数据修改
        rightAxis.setAxisMinimum(440f); //  根据返回的数据修改
        rightAxis.setAxisLineColor(getResources().getColor(R.color.transparent));
    }

    @Override
    protected void initData() {
        //模拟数值
        float start = 0f;
       for (int i = (int) start; i < 20; i++) {
            float val = (float) (Math.random() * (30)) + 50;
           ModelStatisticsLine modelStatisticsLine = new ModelStatisticsLine();
           modelStatisticsLine.setTime_x(i+"my");
           modelStatisticsLine.setValue(val);
           modelStatisticsLine.setIndex(i);
           mDatas_chart.add(modelStatisticsLine);
       }
       for (int i = (int) start; i < 20; i++) {
            float val = (float) (Math.random() * (30 )) + 60;
           ModelStatisticsLine modelStatisticsLine = new ModelStatisticsLine();
           modelStatisticsLine.setTime_x(i+"my");
           modelStatisticsLine.setValue(val);
           modelStatisticsLine.setIndex(i);
           mDatas_chart2.add(modelStatisticsLine);
       }
        setMaxAndMinValue();
        setLineData();
        chart.setVisibility(View.VISIBLE);
        chart.invalidate();
        chart.animateX(800);

    }
    /**
     * 设置最大最小值
     */
    private void setMaxAndMinValue() {
        float chat_max= mDatas_chart.get(0).getValue();
        float chat_min=  mDatas_chart.get(0).getValue();
        if (mDatas_chart.size()>0){
            for (int i = 0; i < mDatas_chart.size(); i++) {
                if (mDatas_chart.get(i).getValue()>chat_max){
                    chat_max=mDatas_chart.get(i).getValue();
                }
                if (mDatas_chart.get(i).getValue()<chat_min){
                    chat_min=mDatas_chart.get(i).getValue();
                }
            }

        }
        if (mDatas_chart2.size()>0){
            for (int i = 0; i < mDatas_chart2.size(); i++) {
                if (mDatas_chart2.get(i).getValue()>chat_max){
                    chat_max=mDatas_chart2.get(i).getValue();
                }
                if (mDatas_chart2.get(i).getValue()<chat_min){
                    chat_min=mDatas_chart2.get(i).getValue();
                }
            }
        }
        YAxis axisLeft = chart.getAxisLeft();
        axisLeft.setAxisMaximum(chat_max+10f);
        axisLeft.setAxisMinimum(chat_min-10f);
    }
    //设置数据
    private void setLineData() {

        ArrayList<Entry> values1 = new ArrayList<>();
//        float start = 1f;
//        for (int i = (int) start; i < start + count; i++) {
//            float val = (float) (Math.random() * (range / 2f)) + 50;
//            values1.add(new Entry(i, val));
//        }
        for (int i = 0; i < mDatas_chart.size(); i++) {
            values1.add(new Entry(i, mDatas_chart.get(i).getValue()));
        }

        ArrayList<Entry> values2 = new ArrayList<>();

//        for (int i = (int) start; i < start + count; i++) {
//            float val = (float) (Math.random() * range) + 450f;
//            values2.add(new Entry(i, val));
//        }

        for (int i = 0; i < mDatas_chart2.size(); i++) {
            values2.add(new Entry(i, mDatas_chart2.get(i).getValue()));
        }

        LineDataSet set1, set2;


        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chart.getData().getDataSetByIndex(0);
            set2 = (LineDataSet) chart.getData().getDataSetByIndex(1);
            set1.setValues(values1);
            set2.setValues(values2);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values1, "水费(单位:吨)");

            set1.setAxisDependency(YAxis.AxisDependency.LEFT);
            set1.setColor(Color.parseColor("#FFBB00"));
            set1.setCircleColor(Color.parseColor("#FFBB00"));
            set1.setLineWidth(2f);
            set1.setCircleRadius(3f);
            //  set1.setFillAlpha(65);
            //   set1.setFillColor(ColorTemplate.getHoloBlue());
            //   set1.setHighLightColor(Color.rgb(244, 117, 117));
            set1.setDrawCircleHole(false);
            //set1.setFillFormatter(new MyFillFormatter(0f));
            //set1.setDrawHorizontalHighlightIndicator(false);
            //set1.setVisible(false);
            //set1.setCircleHoleColor(Color.WHITE);

            set1.setMode(LineDataSet.Mode .CUBIC_BEZIER);//设置成平滑

            // create a dataset and give it a type
            set2 = new LineDataSet(values2, "电表(单位:度)");
            set2.setAxisDependency(YAxis.AxisDependency.LEFT);// 依赖y轴
            set2.setColor(Color.parseColor("#7ED320"));
            set2.setCircleColor(Color.parseColor("#7ED320"));
            set2.setLineWidth(2f);
            set2.setCircleRadius(3f);
            //     set2.setFillAlpha(65);
            //     set2.setFillColor(Color.RED);
            set2.setDrawCircleHole(false);
            //    set2.setHighLightColor(Color.rgb(244, 117, 117));
            //set2.setFillFormatter(new MyFillFormatter(900f));

            set2.setMode(LineDataSet.Mode .CUBIC_BEZIER);//设置成平滑
            // create a data object with the data sets
            LineData data = new LineData(set1, set2);
            data.setValueTextColor(mContext.getResources().getColor(R.color.title_color));
            data.setValueTextSize(8f);
            data.setDrawValues(true);

            // set data
            chart.setData(data);
        }
    }
    @Override
    protected void initListener() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_line_chart_demo;
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
    public void onValueSelected(Entry e, Highlight h) {
        //选中回调
    }

    @Override
    public void onNothingSelected() {

    }
}
