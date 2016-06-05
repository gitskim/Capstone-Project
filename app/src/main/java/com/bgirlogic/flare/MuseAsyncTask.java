package com.bgirlogic.flare;

import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.bgirlogic.flare.data.MuseRetrofitApiClient;
import com.bgirlogic.flare.data.models.Response1;
import com.bgirlogic.flare.data.sql.MuseContract;


/**
 * Created by kimsuh on 5/6/16.
 */
public class MuseAsyncTask extends AsyncTask<Void, Void, Response1> {

    private MuseRetrofitApiClient mMuseRetrofitApiClient;

    private Response1 mResults;

    private Context mContext;

    private String mZipcode;

    public MuseAsyncTask(Context context) {
        this.mContext = context;
    }

    public MuseAsyncTask(Context context, String zipCode) {
        this.mContext = context;
        this.mZipcode = zipCode;
    }

    @Override
    protected Response1 doInBackground(Void... params) {
        mMuseRetrofitApiClient = new MuseRetrofitApiClient();
        Response1 results;
        if (mZipcode == null) {
            results = mMuseRetrofitApiClient.getInitialResults("0");
        } else {
            results = mMuseRetrofitApiClient.getResultsWithLocation("0", mZipcode);
        }
        return results;
    }

    @Override
    protected void onPostExecute(Response1 results) {
        super.onPostExecute(results);
        Log.d("hola ", " resutls are : " + results.toString());

        if (results.getResults().size() > 0) {
            ContentValues values = new ContentValues();
            for (int i = 0; i < results.getResults().size(); i++) {
                values.put(MuseContract.JobEntry.COLUMN_JOB_NAME,
                        results.getResults().get(i).getName());
                values.put(MuseContract.JobEntry.COLUMN_COMPANY_NAME,
                        results.getResults().get(i).getCompany().getShort_name());
                mContext.getContentResolver().insert(MuseContract.CONTENT_URI, values);
            }
        }
    }
}
