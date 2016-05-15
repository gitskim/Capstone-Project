package com.bgirlogic.flare.data;

import com.bgirlogic.flare.data.models.Response1;
import com.bgirlogic.flare.utils.Constants;
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
                .setEndpoint(Constants.MUSE_BASE_URL)
                .setClient(new OkClient(mClient))
                .setConverter(new GsonConverter(gson))
                .build();
    }

    //NOTE: the following string "page" cannot be added to strings.
    //Retrofit in this case only accepts direct string constants.
    @Override
    public Response1 getInitialResults(@Query("page") String page) {
        return mRetrofit.create(MuseRetrofitService.class).getInitialResults(page);
    }
}
