package com.tamrakar.uguess.bakingapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Recipe implements Parcelable {

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
    private int mRecipeId;
    private int mServings;
    private String mName;
    private String mImage;
    private List<RecipeIngredient> mRecipeIngredients;
    private List<RecipeStep> mRecipeSteps;

    public Recipe(int recipeId, String name, int servings, String image,
                  List<RecipeIngredient> recipeIngredients, List<RecipeStep> recipeSteps) {
        this.mRecipeId = recipeId;
        this.mName = name;
        this.mServings = servings;
        this.mImage = image;
        this.mRecipeIngredients = recipeIngredients;
        this.mRecipeSteps = recipeSteps;
    }

    private Recipe(Parcel in) {
        mRecipeId = in.readInt();
        mServings = in.readInt();
        mName = in.readString();
        mImage = in.readString();
        mRecipeIngredients = new ArrayList<>();
        in.readTypedList(mRecipeIngredients, RecipeIngredient.CREATOR);
        mRecipeSteps = new ArrayList<>();
        in.readTypedList(mRecipeSteps, RecipeStep.CREATOR);
    }

    public String getName() {
        return mName;
    }

    public List<RecipeStep> getRecipeSteps() {
        return mRecipeSteps;
    }

    public List<RecipeIngredient> getRecipeIngredients() {
        return mRecipeIngredients;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mRecipeId);
        dest.writeInt(mServings);
        dest.writeString(mName);
        dest.writeString(mImage);
        dest.writeTypedList(mRecipeIngredients);
        dest.writeTypedList(mRecipeSteps);
    }
}
