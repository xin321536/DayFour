package com.example.dayfour;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface APi {
    String  ap = "https://www.wanandroid.com/project/";
    @GET("list/1/json?cid=294")
    Observable<Bean> getBean();
}
