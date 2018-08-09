package com.tamrakar.uguess.bakingapp.views;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;

import com.tamrakar.uguess.bakingapp.R;
import com.tamrakar.uguess.bakingapp.adapters.RecipesAdapter;
import com.tamrakar.uguess.bakingapp.adapters.RecipesGridAdapter;
import com.tamrakar.uguess.bakingapp.loaders.RecipesLoader;
import com.tamrakar.uguess.bakingapp.models.Recipe;

import java.util.ArrayList;

public class MainActivity
        extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<ArrayList<Recipe>> {

    private GridView mGVRecipes;
    private RecyclerView mRVRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        // find the recycler view
        mRVRecipes = findViewById(R.id.rv_recipes);
        mGVRecipes = findViewById(R.id.gv_recipes);

        if (mRVRecipes != null) {
            // set linear layout manager for recycler view
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mRVRecipes.setLayoutManager(linearLayoutManager);
        }

        // initialize the loader to get recipes
        getLoaderManager().initLoader(RecipesLoader.RECIPE_LOADER_ID, null, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        getLoaderManager().destroyLoader(RecipesLoader.RECIPE_LOADER_ID);
    }

    @Override
    public Loader<ArrayList<Recipe>> onCreateLoader(int id, Bundle args) {
        return new RecipesLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Recipe>> loader, ArrayList<Recipe> data) {
        if (mRVRecipes != null) {
            RecipesAdapter recipesAdapter = new RecipesAdapter(this, data);
            mRVRecipes.setAdapter(recipesAdapter);
        } else {
            RecipesGridAdapter recipesGridAdapter = new RecipesGridAdapter(this, data);
            mGVRecipes.setAdapter(recipesGridAdapter);
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Recipe>> loader) {

    }
}
