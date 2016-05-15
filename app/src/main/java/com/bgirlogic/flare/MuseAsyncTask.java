package com.bgirlogic.flare;

import android.os.AsyncTask;
import android.util.Log;

import com.bgirlogic.flare.data.Response1;


/**
 * Created by kimsuh on 5/6/16.
 */
public class MuseAsyncTask extends AsyncTask<Void, Void, Response1> {

    private MuseRetrofitApiClient mMuseRetrofitApiClient;

    private Response1 mResults;

    @Override
    protected Response1 doInBackground(Void... params) {
        mMuseRetrofitApiClient = new MuseRetrofitApiClient();
        Response1 results = mMuseRetrofitApiClient.getInitialResults("0");
        return results;
    }

    @Override
    protected void onPostExecute(Response1 results) {
        super.onPostExecute(results);
        Log.d("hola ", " resutls are : " + results.toString());
    }
}
