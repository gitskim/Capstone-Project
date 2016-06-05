package com.bgirlogic.flare;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bgirlogic.flare.data.models.Response1;

/**
 * Created by kimsuh on 5/15/16.
 */
public class JobViewAdapter extends RecyclerView.Adapter<JobViewHolder> {

    private Response1 mResponse;
    public JobViewAdapter(Response1 response) {
        mResponse = response;
    }
    @Override
    public JobViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View contactView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.job_view_item, parent, false);

        return new JobViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(JobViewHolder holder, int position) {
        holder.jobName.setText(mResponse.getResults().get(position).getName());
        holder.companyName.setText(mResponse.getResults().get(position).getCompany().getShort_name());
    }

    @Override
    public int getItemCount() {
        Log.d("JobViewAdapter", "jobs item count is : " + mResponse.getResults().size());
        return mResponse.getResults().size();
    }
}
