package com.jy.day_03;

import com.jy.day_03.bean.Bean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {

    String baseUrl = "http://gank.io/api/data/%E7%A6%8F%E5%88%A9/";
    @GET("10/2")
    Observable<Bean> getData();

}
