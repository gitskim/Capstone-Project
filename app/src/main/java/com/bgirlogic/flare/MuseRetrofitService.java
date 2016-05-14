package com.bgirlogic.flare;

import com.bgirlogic.flare.data.Results;

import retrofit.http.GET;
import retrofit.http.Query;


/**
 * Created by kimsuh on 5/10/16.
 */
public interface MuseRetrofitService {
    @GET("/jobs")
    Results getInitialResults(@Query("page") String page);
}
