package com.bgirlogic.flare.ui;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bgirlogic.flare.R;
import com.bgirlogic.flare.data.models.Response1;

/**
 * Created by kimsuh on 5/15/16.
 */
public class JobViewAdapter extends RecyclerView.Adapter<JobViewHolder> {

    private Response1 mResponse;

    private Cursor mCursor;

    public JobViewAdapter(Response1 response) {
        mResponse = response;
    }

    public JobViewAdapter(Cursor cursor) {
        mCursor = cursor;
    }

    @Override
    public JobViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View contactView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.job_view_item, parent, false);

        return new JobViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(JobViewHolder holder, int position) {
        if (mCursor != null) {
            mCursor.moveToFirst();
            if (mCursor.moveToPosition(position)) {
                Log.d("mcursor", "job name :"+ mCursor.getString(0));
                Log.d("mcursor", "companyName name :"+ mCursor.getString(1));
                holder.jobName.setText(mCursor.getString(0));
                holder.companyName.setText(mCursor.getString(1));
            }
        } else {
            Log.d("mcursor", "mCursor null");
        }
    }

    @Override
    public int getItemCount() {
        Log.d("mcursor", "jobs item count is : " + mCursor.getCount() + "in adapter");
        return mCursor.getCount();
    }

    public void setCursor(Cursor mCursor) {
        this.mCursor = mCursor;
    }
}
