package com.wangxiaotao.myapplicationnew.http.response;

/**
 * Created by wangxiaotao
 */
public interface IResponseHandler {

    void onFailure(int statusCode, String error_msg);

    void onProgress(long currentBytes, long totalBytes);
}
