package com.tamrakar.uguess.bakingapp.loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.tamrakar.uguess.bakingapp.helpers.RecipeJsonHelper;
import com.tamrakar.uguess.bakingapp.models.Recipe;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class RecipesLoader extends AsyncTaskLoader<ArrayList<Recipe>> {

    public static final int RECIPE_LOADER_ID = 111;
    private static final String LOG_TAG = RecipesLoader.class.getSimpleName();
    private ArrayList<Recipe> mData;

    public RecipesLoader(Context context) {
        super(context);
    }

    @Override
    public ArrayList<Recipe> loadInBackground() {
        ArrayList<Recipe> recipes = new ArrayList<>();

        try {
            URL url = new URL("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json");

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            try {
                //Read the input stream into buffer
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(httpURLConnection.getInputStream()));
                StringBuilder responseBuilder = new StringBuilder();
                String responseLine;

                //Read the buffer line by line and append to responseBuilder
                while (null != (responseLine = bufferedReader.readLine())) {
                    responseBuilder.append(responseLine).append("\n");
                }

                bufferedReader.close();

                RecipeJsonHelper helper = new RecipeJsonHelper(responseBuilder.toString());
                recipes = helper.parseRecipesJson();
            } finally {
                httpURLConnection.disconnect();
            }

        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, e.getMessage());
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
        }

        return recipes;
    }

    @Override
    public void deliverResult(ArrayList<Recipe> movieTrailers) {
        mData = movieTrailers;
        if (isStarted()) {
            super.deliverResult(movieTrailers);
        } else if (isReset()) {
        }
    }

    @Override
    protected void onStartLoading() {
        if (mData != null) {
            deliverResult(mData);
        } else {
            forceLoad();
        }
    }
}
