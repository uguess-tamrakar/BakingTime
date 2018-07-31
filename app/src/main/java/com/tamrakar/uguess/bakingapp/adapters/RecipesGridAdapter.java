package com.tamrakar.uguess.bakingapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tamrakar.uguess.bakingapp.R;
import com.tamrakar.uguess.bakingapp.models.Recipe;
import com.tamrakar.uguess.bakingapp.views.RecipeDetailActivity;

import java.util.List;

public class RecipesGridAdapter extends ArrayAdapter<Recipe> {

    private Context mContext;

    public RecipesGridAdapter(@NonNull Context context, List<Recipe> recipes) {
        super(context, 0, recipes);
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Get the recipe at this position
        final Recipe recipe = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater
                    .from(mContext)
                    .inflate(R.layout.fragment_recipe, parent, false);
        }

        if (recipe != null) {
            TextView tvRecipeName = convertView.findViewById(R.id.tv_recipe_name);
            tvRecipeName.setText(recipe.getName());
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, RecipeDetailActivity.class);
                intent.putExtra(RecipeDetailActivity.SELECTED_RECIPE, recipe);
                intent.putExtra(RecipeDetailActivity.IS_TWO_PANE, true);
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }
}
