package com.bgirlogic.flare.widgets;

import android.appwidget.AppWidgetManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Binder;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bgirlogic.flare.R;
import com.bgirlogic.flare.data.models.Job;
import com.bgirlogic.flare.data.sql.MuseContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kimsuh on 2/14/16.
 */
public class MuseWidgetService extends RemoteViewsService {
    private static final String TAG = MuseWidgetService.class.getSimpleName();
    private Context mContext;
    private ContentResolver mContentResolver;
    private Cursor mCursor = null;
    private List<Job> jobs = new ArrayList<>();
    private int mAppWidgetId;

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        Log.d(TAG, "cursor " + "onGetViewFactory");
        return new FootballWidgetRemoteViewsFactory(getApplicationContext(), intent);
    }

    public class FootballWidgetRemoteViewsFactory implements RemoteViewsFactory {

        public FootballWidgetRemoteViewsFactory(Context context, Intent intent) {
            mContext = context;
            mContentResolver = mContext.getContentResolver();
            mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
            Log.d("cursor ", "MuseWidgetProvider onUpdate ");

        }

        @Override
        public void onCreate() {
        }

        @Override
        public void onDataSetChanged() {
            if (mCursor != null) {
                mCursor.close();
                Log.d(TAG, "mCursor is not null in dataset changed");
            }

            final long identityToken = Binder.clearCallingIdentity();
            mCursor = mContentResolver.query(
                    MuseContract.CONTENT_URI,
                    MuseContract.getProjection(),
                    null,
                    null,
                    null);

            Log.d(TAG, "mCursor is : " + mCursor.toString());
            Log.d(TAG, "mCursor count is " + mCursor.getCount());
        }

        @Override
        public void onDestroy() {
            if (mCursor != null) {
                mCursor.close();
            }
        }

        @Override
        public int getCount() {
            Log.d(TAG, "getcount of cursor is : " + mCursor.getCount());
            return mCursor == null ? 0 : mCursor.getCount();
        }

        @Override
        public RemoteViews getViewAt(int position) {
            Log.d(TAG, "cursor getViewAt");
            RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(),
                    R.layout.widget_list_item);

            Log.d(TAG, "cursor mCursor = " + mCursor);
            Log.d(TAG, "cursor mCursor.Count = " + mCursor.getCount());
            Log.d(TAG, "cursor mCursor.getColumnCount = " + mCursor.getColumnCount());

            assert mCursor != null;

            if (mCursor.moveToPosition(position)) {
                Log.d(TAG, "cursor at index 0 job name : " + mCursor.getString(0));
                Log.d(TAG, "cursor at index 1 company : " + mCursor.getString(1));
                remoteViews.setTextViewText(R.id.job_name, mCursor.getString(0));
                remoteViews.setTextViewText(R.id.job_company, mCursor.getString(1));
            }

            return remoteViews;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
