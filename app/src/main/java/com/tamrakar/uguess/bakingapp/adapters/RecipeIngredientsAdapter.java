package com.tamrakar.uguess.bakingapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tamrakar.uguess.bakingapp.R;
import com.tamrakar.uguess.bakingapp.models.RecipeIngredient;

import java.util.List;

public class RecipeIngredientsAdapter
        extends RecyclerView.Adapter<RecipeIngredientsAdapter.ViewHolder> {

    private Context mContext;
    private List<RecipeIngredient> mRecipeIngredients;

    public RecipeIngredientsAdapter(Context context, List<RecipeIngredient> recipeIngredients) {
        mContext = context;
        mRecipeIngredients = recipeIngredients;
    }

    @NonNull
    @Override
    public RecipeIngredientsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.fragment_recipe_ingredient, parent, false);
        return new RecipeIngredientsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeIngredientsAdapter.ViewHolder holder, int position) {
        final RecipeIngredient recipeIngredient = mRecipeIngredients.get(position);
        StringBuilder recipeIngredientBuilder = new StringBuilder();
        recipeIngredientBuilder.append(recipeIngredient.getIngredient()).append(" - ");

        if (recipeIngredient.getQuantity() != 0) {
            recipeIngredientBuilder.append(recipeIngredient.getQuantity()).append(" ");
        }

        if (recipeIngredient.getMeasureType() != null) {
            recipeIngredientBuilder.append(recipeIngredient.getMeasureType().toLowerCase());
        }

        holder.mTvRecipeIngredient.setText(recipeIngredientBuilder);
    }

    @Override
    public int getItemCount() {
        return mRecipeIngredients.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTvRecipeIngredient;

        ViewHolder(View view) {
            super(view);
            mTvRecipeIngredient = view.findViewById(R.id.tv_recipe_ingredient);
        }
    }
}
