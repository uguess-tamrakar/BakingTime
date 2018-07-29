package com.tamrakar.uguess.bakingapp.helpers;

import android.util.Log;

import com.tamrakar.uguess.bakingapp.models.Recipe;
import com.tamrakar.uguess.bakingapp.models.RecipeIngredient;
import com.tamrakar.uguess.bakingapp.models.RecipeStep;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecipeJsonHelper {

    private static String LOG_TAG = RecipeJsonHelper.class.getName();
    private String json;

    public RecipeJsonHelper(String json) {
        this.json = json;
    }

    public ArrayList<Recipe> parseRecipesJson() {
        ArrayList<Recipe> recipes = new ArrayList<>();

        try {

            if (json != null) {
                JSONArray recipesArray = new JSONArray(json);

                for (int i = 0; i < recipesArray.length(); i++) {
                    JSONObject recipe = recipesArray.getJSONObject(i);

                    if (recipe != null) {
                        int recipeId = recipe.optInt("id");
                        String name = recipe.optString("name");

                        ArrayList<RecipeIngredient> recipeIngredients = new ArrayList<>();
                        JSONArray recipeIngredientsArray = recipe.getJSONArray("ingredients");

                        for (int j = 0; j < recipeIngredientsArray.length(); j++) {
                            JSONObject recipeIngredient = recipeIngredientsArray.getJSONObject(j);

                            String ingredient = recipeIngredient.optString("ingredient");
                            int quantity = recipeIngredient.optInt("quantity");
                            String measureType = recipeIngredient.optString("measure");

                            recipeIngredients.add(new RecipeIngredient(ingredient, quantity, measureType));
                        }

                        ArrayList<RecipeStep> recipeSteps = new ArrayList<>();
                        JSONArray recipeStepsArray = recipe.getJSONArray("steps");

                        for (int k = 0; k < recipeStepsArray.length(); k++) {
                            JSONObject recipeStep = recipeStepsArray.getJSONObject(k);

                            int stepId = recipeStep.optInt("id");
                            String shortDescription = recipeStep.optString("shortDescription");
                            String description = recipeStep.optString("description");
                            String videoUrl = recipeStep.optString("videoURL");
                            String thumbnailUrl = recipeStep.getString("thumbnailURL");

                            recipeSteps.add(new RecipeStep(stepId, shortDescription, description, videoUrl, thumbnailUrl));
                        }

                        int servings = recipe.optInt("servicings");
                        String image = recipe.optString("image");

                        recipes.add(new Recipe(recipeId, name, servings, image, recipeIngredients, recipeSteps));
                    }
                }
            }

        } catch (JSONException ex) {
            Log.e(LOG_TAG, ex.getMessage());
        }

        return recipes;
    }
}
