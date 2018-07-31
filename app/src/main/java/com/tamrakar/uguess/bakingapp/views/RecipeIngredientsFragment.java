package com.tamrakar.uguess.bakingapp.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tamrakar.uguess.bakingapp.R;
import com.tamrakar.uguess.bakingapp.adapters.RecipeIngredientsAdapter;
import com.tamrakar.uguess.bakingapp.models.Recipe;

public class RecipeIngredientsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe_ingredients,
                container, false);

        Recipe recipe = getArguments().getParcelable(RecipeDetailActivity.RECIPE_KEY);

        RecyclerView rvRecipeIngredients = rootView.findViewById(R.id.rv_recipe_ingredients);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvRecipeIngredients.setLayoutManager(linearLayoutManager);

        RecipeIngredientsAdapter recipeIngredientsAdapter =
                new RecipeIngredientsAdapter(getActivity(), recipe.getRecipeIngredients());
        rvRecipeIngredients.setAdapter(recipeIngredientsAdapter);

        return rootView;
    }
}
