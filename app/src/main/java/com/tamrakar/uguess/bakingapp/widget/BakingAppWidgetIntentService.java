package com.tamrakar.uguess.bakingapp.widget;

import android.app.IntentService;
import android.app.LauncherActivity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.tamrakar.uguess.bakingapp.R;
import com.tamrakar.uguess.bakingapp.models.Recipe;

import java.util.ArrayList;

public class BakingAppWidgetIntentService extends IntentService {

    public static String WIDGET_RECIPE_DATA = "widget_recipe_data";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public BakingAppWidgetIntentService() {
        super(BakingAppWidgetIntentService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
             Recipe recipe = intent.getParcelableExtra(WIDGET_RECIPE_DATA);

             if (recipe != null) {
                 AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
                 int[] appWidgetIds = appWidgetManager.getAppWidgetIds(
                         new ComponentName(this, BakingAppWidgetProvider.class));
                 BakingAppWidgetProvider.updateWidgets(this,
                         appWidgetManager, appWidgetIds, recipe);
             }
        }
    }

    public static void updateWidgets(Context context, Recipe recipe) {
        Intent intent = new Intent(context, BakingAppWidgetIntentService.class);
        intent.putExtra(WIDGET_RECIPE_DATA, recipe);
        context.startService(intent);
    }
}
