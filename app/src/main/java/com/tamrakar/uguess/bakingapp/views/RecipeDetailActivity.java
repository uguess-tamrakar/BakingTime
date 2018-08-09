package com.tamrakar.uguess.bakingapp.views;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.tamrakar.uguess.bakingapp.R;
import com.tamrakar.uguess.bakingapp.adapters.RecipeIngredientsAdapter;
import com.tamrakar.uguess.bakingapp.adapters.RecipeStepsAdapter;
import com.tamrakar.uguess.bakingapp.models.Recipe;

public class RecipeDetailActivity
        extends AppCompatActivity {

    public static final String IS_TWO_PANE = "is_two_pane";
    public static final String SELECTED_RECIPE = "selected_recipe";
    public static final String RECIPE_KEY = "recipe";
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

        // Check to see if it is two pane; if yes, then setup master detail flow layout
        boolean isTwoPane = getIntent().getBooleanExtra(IS_TWO_PANE, false);

        setContentView(R.layout.activity_recipe_detail);

        if (mRecipe != null) {
            // Set Recipe name as title
            setTitle(mRecipe.getName());

            // find the recycler views
            RecyclerView rvRecipeSteps = findViewById(R.id.rv_recipe_steps);
            TextView tvRecipeIngredients = findViewById(R.id.tv_recipe_ingredients);

            // set linear layout manager for recycler views
            LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
            linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
            rvRecipeSteps.setLayoutManager(linearLayoutManager1);

            // set adapters for recycler views
            RecipeStepsAdapter recipeStepsAdapter =
                    new RecipeStepsAdapter(this, mRecipe.getRecipeSteps(), isTwoPane);
            rvRecipeSteps.setAdapter(recipeStepsAdapter);

            if (isTwoPane) {
                // Set click listener on recipe ingredient text view
                tvRecipeIngredients.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putParcelable(RECIPE_KEY, mRecipe);
                        RecipeIngredientsFragment fragment = new RecipeIngredientsFragment();
                        fragment.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.recipe_detail_container, fragment)
                                .commit();
                    }
                });

                // Call OnClick on Recipe Ingredients text view if its two pane
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
                    tvRecipeIngredients.callOnClick();
                }

            } else {
                tvRecipeIngredients.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(),
                                RecipeIngredientsActivity.class);
                        intent.putExtra(RECIPE_KEY, mRecipe);
                        startActivity(intent);
                    }
                });
            }
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

        super.onRestoreInstanceState(savedInstanceState);
    }
}
