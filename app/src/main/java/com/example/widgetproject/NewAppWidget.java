package com.example.widgetproject;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {

    public static final String ACTION_TOAST = "actionShowToast";
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);

        Intent intent = new Intent(context, NewAppWidget.class);
        intent.setAction(ACTION_TOAST);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,intent,0);

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);
        views.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent);

        Intent intentService = new Intent(context, WidgetService.class);
        views.setRemoteAdapter(R.id.widget_list_view, intentService);

        Intent intentActivities = new Intent(context,MainActivity.class);

        PendingIntent clickPendingIntent = PendingIntent.getActivity(context,
                0, intentActivities, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.id.widget_list_view,clickPendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (ACTION_TOAST.equals(intent.getAction())) {
            Toast.makeText(context, "Clicked Text", Toast.LENGTH_SHORT).show();
            CharSequence widgetText = context.getString(R.string.appwidget_text_update);
            RemoteViews views = new RemoteViews(context.getPackageName(),
                    R.layout.new_app_widget);
            views.setTextViewText(R.id.appwidget_text, widgetText);
            AppWidgetManager.getInstance(context).updateAppWidget(
                    new ComponentName(context, NewAppWidget.class),views);
        }
        super.onReceive(context, intent);
    }
}

