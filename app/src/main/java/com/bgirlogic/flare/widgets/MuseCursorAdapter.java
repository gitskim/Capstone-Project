package com.bgirlogic.flare.widgets;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bgirlogic.flare.R;

/**
 * Created by yehya khaled on 2/26/2015.
 */
public class MuseCursorAdapter extends CursorAdapter {
    public static final int COLUMN_JOB_NAME = 0;
    public static final int COLUMN_COMPANY_NAME = 1;

    public MuseCursorAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
    }

    private static class ViewHolder{
        public TextView jobName;
        public TextView companyName;

        public ViewHolder(View view) {
            jobName = (TextView) view.findViewById(R.id.job_name);
            companyName = (TextView) view.findViewById(R.id.job_company);
        }
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View mItem = LayoutInflater.from(context).inflate(R.layout.widget_list_item, parent, false);
        ViewHolder mHolder = new ViewHolder(mItem);
        mItem.setTag(mHolder);
        //Log.v(FetchScoreTask.LOG_TAG,"new View inflated");
        return mItem;
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        final ViewHolder mHolder = (ViewHolder) view.getTag();
        mHolder.jobName.setText(cursor.getString(COLUMN_JOB_NAME));
        mHolder.companyName.setText(cursor.getString(COLUMN_COMPANY_NAME));

//        LayoutInflater vi = (LayoutInflater) context.getApplicationContext()
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View v = vi.inflate(R.layout.detail_fragment, null);
//        ViewGroup container = (ViewGroup) view.findViewById(R.id.details_fragment_container);
//        if (mHolder.match_id == detail_match_id) {
//            //Log.v(FetchScoreTask.LOG_TAG,"will insert extraView");
//
//            container.addView(v, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
//                    , ViewGroup.LayoutParams.MATCH_PARENT));
//            TextView match_day = (TextView) v.findViewById(R.id.matchday_textview);
//            match_day.setText(Utilies.getMatchDay(cursor.getInt(COL_MATCHDAY),
//                    cursor.getInt(COL_LEAGUE)));
//            TextView league = (TextView) v.findViewById(R.id.league_textview);
//            league.setText(Utilies.getLeague(cursor.getInt(COL_LEAGUE)));
//            Button share_button = (Button) v.findViewById(R.id.share_button);
//            share_button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //add Share Action
//                    context.startActivity(createShareForecastIntent(mHolder.home_name.getText() + " "
//                            + mHolder.score.getText() + " " + mHolder.away_name.getText() + " "));
//                }
//            });
//        } else {
//            container.removeAllViews();
//        }

    }

//    public Intent createShareForecastIntent(String ShareText) {
//        Intent shareIntent = new Intent(Intent.ACTION_SEND);
//        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
//        shareIntent.setType("text/plain");
//        shareIntent.putExtra(Intent.EXTRA_TEXT, ShareText + FOOTBALL_SCORES_HASHTAG);
//        return shareIntent;
//    }

}
