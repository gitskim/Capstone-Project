package com.bgirlogic.flare;

import android.os.AsyncTask;
import android.util.Log;

import com.bgirlogic.flare.data.Results;


/**
 * Created by kimsuh on 5/6/16.
 */
public class MuseAsyncTask extends AsyncTask<Void, Void, Results> {

    private MuseRetrofitApiClient mMuseRetrofitApiClient;

    private Results mResults;

    @Override
    protected Results doInBackground(Void... params) {
        mMuseRetrofitApiClient = new MuseRetrofitApiClient();
        return mMuseRetrofitApiClient.getInitialResults("0");
    }

    @Override
    protected void onPostExecute(Results results) {
        super.onPostExecute(results);
        Log.d("hola ", " resutls are : " + results.toString());
    }
}
