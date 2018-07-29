package com.tamrakar.uguess.bakingapp.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tamrakar.uguess.bakingapp.R;
import com.tamrakar.uguess.bakingapp.models.RecipeStep;

public class RecipeStepDetailActivity extends AppCompatActivity {

    private static String SELECTED_RECIPE_STEP = "selected_recipe_step";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step_detail);

        // get Recipe step as parcelable
        RecipeStep recipeStep = getIntent().getParcelableExtra(SELECTED_RECIPE_STEP);

        // find the controls
        ImageView ivRecipeStepThumbnail = findViewById(R.id.iv_recipe_step_thumbnail);
        ImageView ivRecipeStepPlay = findViewById(R.id.iv_recipe_step_play);
        TextView tvRecipeStepInstruction = findViewById(R.id.tv_recipe_step_instruction);

        // get the required data
        String recipeThumbnailUrl = recipeStep.getThumbnailUrl();

        if (recipeThumbnailUrl.isEmpty()) {
            ivRecipeStepThumbnail.setVisibility(View.INVISIBLE);
        } else {
            ivRecipeStepPlay.setVisibility(View.INVISIBLE);
        }

        tvRecipeStepInstruction.setText(recipeStep.getDescription());
    }
}
