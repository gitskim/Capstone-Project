package com.bgirlogic.flare;

import com.bgirlogic.flare.data.Results;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;
import retrofit.http.Query;

/**
 * Created by kimsuh on 5/10/16.
 */
public class MuseRetrofitApiClient implements MuseRetrofitService {

    private OkHttpClient mClient = null;
    private RestAdapter mRetrofit;

    public MuseRetrofitApiClient() {
        if (mClient == null) {
            mClient = new OkHttpClient();
        }

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        mRetrofit = new RestAdapter.Builder()
                .setEndpoint("https://api-v2.themuse.com")
                .setClient(new OkClient(mClient))
                .setConverter(new GsonConverter(gson))
                .build();
    }

    @Override
    public Results getInitialResults(@Query("page") String page) {
        return mRetrofit.create(MuseRetrofitService.class).getInitialResults(page);
    }
}
