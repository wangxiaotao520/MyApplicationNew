package com.wangxiaotao.myapplicationnew.http;

/**
 * Description: 接口
 * created by wangxiaotao
 * 谨记在下方定义完接口后要在下方invalidate中再写一遍
 */
public class ApiHttpClient {
    //    public static  String API_VERSION = "apk41/";
    public static String API_VERSION = "apk46/";
    //测试
//    public static final String API_URL_FINAL = "http://com.hui-shenghuo.cn/";//固定域名
//    public static String API_URL = "http://test.hui-shenghuo.cn/";
//    public static String API_URL_SHARE = "http://test.hui-shenghuo.cn/";//分享
//    public static String API_SERVICE_URL = "http://test.hui-shenghuo.cn/service/";//服务


//    正式
    public static final String API_URL_FINAL = "http://common.hui-shenghuo.cn/";
    public static  String API_URL = "http://m.hui-shenghuo.cn/" ;
    public static  String API_URL_SHARE = "http://m.hui-shenghuo.cn/";
    public static  String API_SERVICE_URL = "http://m.hui-shenghuo.cn/service/";


    public static String IMG_URL = "http://img.hui-shenghuo.cn/";
    public static String IMG_SERVICE_URL = "http://img.hui-shenghuo.cn/";

    public static String TOKEN;
    public static String TOKEN_SECRET;

    public static String THUMB_1080_1920_ = "thumb_1080_1920_";
    public static String THUMB_800_1280_ = "thumb_800_1280_";
    public static String THUMB_480_800_ = "thumb_480_800_";
    public static String THUMB__500_500_ = "thumb_500_500_";

    public static void setTokenInfo(String token, String tokenSecret) {
        TOKEN = token;
        TOKEN_SECRET = tokenSecret;
    }

}
