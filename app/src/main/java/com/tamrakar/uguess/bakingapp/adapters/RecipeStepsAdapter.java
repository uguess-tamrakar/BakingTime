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
import com.tamrakar.uguess.bakingapp.models.RecipeStep;
import com.tamrakar.uguess.bakingapp.views.RecipeStepDetailActivity;

import java.util.List;

public class RecipeStepsAdapter extends RecyclerView.Adapter<RecipeStepsAdapter.ViewHolder> {

    private static String SELECTED_RECIPE_STEP = "selected_recipe_step";
    private Context mContext;
    private List<RecipeStep> mRecipeSteps;

    public RecipeStepsAdapter(Context context, List<RecipeStep> recipeSteps) {
        mContext = context;
        mRecipeSteps = recipeSteps;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.fragment_recipe_step, parent, false);
        return new RecipeStepsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // get the Recipe step and description on the text view
        final RecipeStep recipeStep = mRecipeSteps.get(position);
        String stepTitle = String.valueOf(position + 1) + ". " + recipeStep.getShortDescription();
        holder.mTvRecipeStepShortDescription.setText(stepTitle);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, RecipeStepDetailActivity.class);
                intent.putExtra(SELECTED_RECIPE_STEP, recipeStep);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRecipeSteps.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTvRecipeStepShortDescription;

        ViewHolder(View view) {
            super(view);
            mTvRecipeStepShortDescription = view.findViewById(R.id.tv_recipe_step_short_description);
        }
    }
}
