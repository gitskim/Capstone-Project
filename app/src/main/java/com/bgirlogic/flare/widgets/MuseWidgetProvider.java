package com.bgirlogic.flare.widgets;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

import com.bgirlogic.flare.R;

/**
 * Created by kimsuh on 5/21/16.
 */
public class MuseWidgetProvider extends AppWidgetProvider {

    //onupdate and onrecieve get called on the ui thread, so no long-running tasks
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.d("cursor ", "MuseWidgetProvider onReceive ");
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, getClass()));
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_list);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.d("cursor ", "MuseWidgetProvider onUpdate after super");

        for (int appWidgetId : appWidgetIds) {
            //Instantiate the Remoteviews object for the ap widget layout
            RemoteViews views = new RemoteViews(context.getPackageName(),
                    R.layout.widget_list_layout);

            Intent intent = new Intent(context, MuseWidgetService.class);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));

            // Set up the RemoteViews object to use a RemoteViews adapter.
            // This adapter connects
            // to a RemoteViewsService  through the specified intent.
            // This is how you populate the data.
            views.setRemoteAdapter(appWidgetId, R.id.widget_list, intent);
            // The empty view is displayed when the collection has no items.
            // It should be in the same layout used to instantiate the RemoteViews
            // object above.
            views.setEmptyView(R.id.widget_list, R.id.empty_view);
            Log.d("cursor ", "MuseWidgetProvider onUpdate ");
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
}
