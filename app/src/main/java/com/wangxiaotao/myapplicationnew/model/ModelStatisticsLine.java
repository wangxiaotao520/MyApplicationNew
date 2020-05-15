package com.wangxiaotao.myapplicationnew.model;

/**
 * Description: 折线图
 * created by wangxiaotao
 * 2019/6/5 0005 上午 8:26
 */
public class ModelStatisticsLine {
    float value;

    int index;

    String time_x;


    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getTime_x() {
        return time_x;
    }

    public void setTime_x(String time_x) {
        this.time_x = time_x;
    }

}
