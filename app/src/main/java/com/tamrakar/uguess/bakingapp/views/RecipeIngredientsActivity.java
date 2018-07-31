package com.tamrakar.uguess.bakingapp.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tamrakar.uguess.bakingapp.R;
import com.tamrakar.uguess.bakingapp.adapters.RecipeIngredientsAdapter;
import com.tamrakar.uguess.bakingapp.models.Recipe;

public class RecipeIngredientsActivity extends AppCompatActivity {

    private Recipe mRecipe;
    private static final String RECIPE_KEY = "recipe";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_ingredients);

        mRecipe = getIntent().getParcelableExtra(RECIPE_KEY);

        RecyclerView rvRecipeIngredients = findViewById(R.id.rv_recipe_ingredients);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvRecipeIngredients.setLayoutManager(linearLayoutManager);

        RecipeIngredientsAdapter recipeIngredientsAdapter =
                new RecipeIngredientsAdapter(this, mRecipe.getRecipeIngredients());
        rvRecipeIngredients.setAdapter(recipeIngredientsAdapter);
    }
}
