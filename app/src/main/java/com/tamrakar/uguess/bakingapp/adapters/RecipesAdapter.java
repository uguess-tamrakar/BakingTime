package com.tamrakar.uguess.bakingapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tamrakar.uguess.bakingapp.R;
import com.tamrakar.uguess.bakingapp.models.Recipe;
import com.tamrakar.uguess.bakingapp.views.RecipeDetailActivity;
import com.tamrakar.uguess.bakingapp.widget.BakingAppWidgetIntentService;

import java.util.List;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {

    private boolean mIsPhone;
    private static final String SELECTED_RECIPE = "selected_recipe";
    private Context mContext;
    private List<Recipe> mRecipes;

    public RecipesAdapter(Context context, List<Recipe> recipes, boolean isPhone) {
        mContext = context;
        mRecipes = recipes;
        mIsPhone = isPhone;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.fragment_recipe, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Recipe recipe = mRecipes.get(position);
        holder.mTvRecipeName.setText(recipe.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, RecipeDetailActivity.class);
                intent.putExtra(SELECTED_RECIPE, recipe);
                intent.putExtra(RecipeDetailActivity.IS_PHONE, mIsPhone);
                BakingAppWidgetIntentService.updateWidgets(mContext, recipe);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTvRecipeName;

        ViewHolder(View view) {
            super(view);
            mTvRecipeName = view.findViewById(R.id.tv_recipe_name);
        }
    }
}
