package com.demo.song.network.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by song on 2017/7/26.
 */
public interface TestService {

    //根路径
    public static String HTTP_ROOT="http://www.ldgo8.com:8080/lifeplan/api/";
    //接口请求路径
    public static String HTTP_URL = HTTP_ROOT+"/lifeplan/api/"+ "common/countytown?";

    @GET("photos.json")
    public Call<ResponseBody> getTest();
}
