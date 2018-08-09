package com.tamrakar.uguess.bakingapp;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.GridView;

import com.tamrakar.uguess.bakingapp.views.MainActivity;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.regex.Matcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityEspressoTest {

    private static String PACKAGE_NAME = "com.tamrakar.uguess.bakingapp.views";

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testShouldLaunchTheMainActivityAndLoadRecipes() {
        onView(withId(R.id.gv_recipes)).check(matches(isDisplayed()));
        onView(withId(R.id.gv_recipes)).perform(RecyclerViewActions.scrollToPosition(0));
    }

//    @Test
//    public void verifyRecipeSentToRecipeDetailActivity() {
//        // Create a fake recipe object
//        List<RecipeIngredient> recipeIngredients = new ArrayList<>();
//        recipeIngredients.add(new RecipeIngredient("Test ingredient 1",
//                1, "ounces"));
//        recipeIngredients.add(new RecipeIngredient("Test ingredient 2",
//                2, "ounces"));
//        recipeIngredients.add(new RecipeIngredient("Test ingredient 3",
//                3, "ounces"));
//        List<RecipeStep> recipeSteps = new ArrayList<>();
//        recipeSteps.add(new RecipeStep(1, "Recipe Step 1",
//                "This is a Recipe Step 1", "", ""));
//        recipeSteps.add(new RecipeStep(2, "Recipe Step 2",
//                "This is a Recipe Step 2", "", ""));
//
//        recipeSteps.add(new RecipeStep(3, "Recipe Step 3",
//                "This is a Recipe Step 3", "", ""));
//
//        Recipe recipe = new Recipe(1, "Test Recipe",
//                2, "", recipeIngredients, recipeSteps);
//
//        onView(withId(R.id.gv_recipes)).perform(RecyclerViewActions.actionOnItemAtPosition)
//    }

}
