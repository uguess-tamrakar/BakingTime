package com.tamrakar.uguess.bakingapp.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tamrakar.uguess.bakingapp.R;
import com.tamrakar.uguess.bakingapp.adapters.RecipeIngredientsAdapter;
import com.tamrakar.uguess.bakingapp.adapters.RecipeStepsAdapter;
import com.tamrakar.uguess.bakingapp.models.Recipe;

public class RecipeDetailActivity
        extends AppCompatActivity {

    public static final String SELECTED_RECIPE = "selected_recipe";
    private static final String RECIPE_KEY = "recipe";
    private Recipe mRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // If bundle is not null then, get the recipe from bundle, otherwise get as parcelable
        if (savedInstanceState != null) {
            mRecipe = savedInstanceState.getParcelable(RECIPE_KEY);
        } else {
            mRecipe = getIntent().getParcelableExtra(SELECTED_RECIPE);
        }

        setContentView(R.layout.activity_recipe_detail);

        if (mRecipe != null) {
            // find the recycler views
            RecyclerView rvRecipeIngredients = findViewById(R.id.rv_recipe_ingredients);
            RecyclerView rvRecipeSteps = findViewById(R.id.rv_recipe_steps);

            // set linear layout manager for recycler views
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            rvRecipeIngredients.setLayoutManager(linearLayoutManager);

            LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
            linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
            rvRecipeSteps.setLayoutManager(linearLayoutManager1);

            // set adapters for recycler views
            RecipeIngredientsAdapter recipeIngredientsAdapter =
                    new RecipeIngredientsAdapter(this, mRecipe.getRecipeIngredients());
            rvRecipeIngredients.setAdapter(recipeIngredientsAdapter);

            RecipeStepsAdapter recipeStepsAdapter =
                    new RecipeStepsAdapter(this, mRecipe.getRecipeSteps());
            rvRecipeSteps.setAdapter(recipeStepsAdapter);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Put recipe into a bundle
        outState.putParcelable(RECIPE_KEY, mRecipe);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore recipe info from a bundle
        mRecipe = savedInstanceState.getParcelable(RECIPE_KEY);
        super.onRestoreInstanceState(savedInstanceState);
    }
}
