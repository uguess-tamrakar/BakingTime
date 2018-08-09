package com.tamrakar.uguess.bakingapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class RecipeIngredient implements Parcelable {

    public static final Creator<RecipeIngredient> CREATOR = new Creator<RecipeIngredient>() {
        @Override
        public RecipeIngredient createFromParcel(Parcel in) {
            return new RecipeIngredient(in);
        }

        @Override
        public RecipeIngredient[] newArray(int size) {
            return new RecipeIngredient[size];
        }
    };
    private String mIngredient;
    private int mQuantity;
    private String mMeasureType;

    public RecipeIngredient(String ingredient, int quantity, String measureType) {
        this.mIngredient = ingredient;
        this.mQuantity = quantity;
        this.mMeasureType = measureType;
    }

    private RecipeIngredient(Parcel in) {
        mIngredient = in.readString();
        mQuantity = in.readInt();
        mMeasureType = in.readString();
    }

    public String getIngredient() {
        return mIngredient;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public String getMeasureType() {
        return mMeasureType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mIngredient);
        dest.writeInt(mQuantity);
        dest.writeString(mMeasureType);
    }
}
