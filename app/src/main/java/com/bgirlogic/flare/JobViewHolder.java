package com.bgirlogic.flare;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by kimsuh on 5/15/16.
 */
public class JobViewHolder extends RecyclerView.ViewHolder {

    public TextView jobName, companyName;

    public JobViewHolder(View itemView) {
        super(itemView);

        jobName = (TextView) itemView.findViewById(R.id.job_name);
        companyName = (TextView) itemView.findViewById(R.id.job_company);
    }
}
