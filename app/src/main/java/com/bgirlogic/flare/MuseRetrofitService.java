package com.bgirlogic.flare;

import com.bgirlogic.flare.data.Response1;

import retrofit.http.GET;
import retrofit.http.Query;


/**
 * Created by kimsuh on 5/10/16.
 */
public interface MuseRetrofitService {
    @GET("/jobs")
    Response1 getInitialResults(@Query("page") String page);
}
