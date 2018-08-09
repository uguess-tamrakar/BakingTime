package com.tamrakar.uguess.bakingapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.ViewSwitcher;

import com.tamrakar.uguess.bakingapp.R;
import com.tamrakar.uguess.bakingapp.models.Recipe;
import com.tamrakar.uguess.bakingapp.models.RecipeIngredient;
import com.tamrakar.uguess.bakingapp.views.MainActivity;
import com.tamrakar.uguess.bakingapp.views.RecipeIngredientsActivity;

/**
 * Implementation of App Widget functionality.
 */
public class BakingAppWidgetProvider extends AppWidgetProvider {

    public static void updateWidgets(Context context, AppWidgetManager appWidgetManager,
                                     int[] appWidgetIds, Recipe recipe) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId, recipe);
        }
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId, Recipe recipe) {
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_app_widget);

        if (recipe != null) {
            // Hide empty text view and show recipe ingredients text view
            views.setViewVisibility(R.id.tv_empty_widget, View.GONE);
            views.setViewVisibility(R.id.tv_widget_recipe_ingredients, View.VISIBLE);
            views.setViewVisibility(R.id.tv_widget_recipe_title, View.VISIBLE);

            // Set Recipe Title text view text
            views.setTextViewText(R.id.tv_widget_recipe_title, recipe.getName());

            // Build recipe ingredients
            StringBuilder recipeIngredients = new StringBuilder();
            for (RecipeIngredient recipeIngredient : recipe.getRecipeIngredients()) {

                StringBuilder recipeIngredientBuilder = new StringBuilder();
                recipeIngredientBuilder.append(recipeIngredient.getIngredient()).append(" - ");

                if (recipeIngredient.getQuantity() != 0) {
                    recipeIngredientBuilder.append(recipeIngredient.getQuantity()).append(" ");
                }

                if (recipeIngredient.getMeasureType() != null) {
                    recipeIngredientBuilder.append(recipeIngredient.getMeasureType().toLowerCase());
                }

                recipeIngredients.append(recipeIngredientBuilder.toString() + "\n");
            }

            // Set Recipe Ingredients
            views.setTextViewText(R.id.tv_widget_recipe_ingredients, recipeIngredients);

            // Set Click listener on the image that will open the app
            Intent intent = new Intent(context, RecipeIngredientsActivity.class);
            intent.putExtra(RecipeIngredientsActivity.RECIPE_KEY, recipe);
            views.setOnClickPendingIntent(R.id.tv_widget_recipe_ingredients, PendingIntent.
                    getActivity(context, appWidgetId, intent, 0));
        } else {
            // Hide recipe ingredients text view and show empty text view
            views.setViewVisibility(R.id.tv_widget_recipe_ingredients, View.GONE);
            views.setViewVisibility(R.id.tv_empty_widget, View.VISIBLE);
            views.setViewVisibility(R.id.tv_widget_recipe_title, View.GONE);

            // Show message on empty text view
            views.setTextViewText(R.id.tv_empty_widget, "No recipe has been selected.\n" +
                    "Please tap here and select a recipe to show its ingredients here");

            // Set Click listener on the image that will open the app
            Intent intent = new Intent(context, MainActivity.class);
            views.setOnClickPendingIntent(R.id.tv_empty_widget, PendingIntent.
                    getActivity(context, appWidgetId, intent, 0));
        }

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        for (int i = 0; i < appWidgetIds.length; i++) {
            updateAppWidget(context, appWidgetManager, appWidgetIds[i], null);
        }

        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }
}

