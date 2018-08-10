package com.tamrakar.uguess.bakingapp;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.tamrakar.uguess.bakingapp.views.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityEspressoTest {

    private static String NUTELLA_PIE = "Nutella Pie";
    private static String BROWNIES = "Brownies";
    private static String YELLOW_CAKE = "Yellow Cake";
    private static String CHEESECAKE = "Cheesecake";

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testRecyclerViewItemCount_matchesFour() {
        RecyclerView recyclerView = mainActivityActivityTestRule.
                getActivity().findViewById(R.id.rv_recipes);
        assert (recyclerView.getAdapter().getItemCount() == 4);
    }

    @Test
    public void testRecyclerViewHasItem_withTextNutellaPie() {
        onView(withText(NUTELLA_PIE)).check(matches(isDisplayed()));
    }

    @Test
    public void testRecyclerViewHasItem_withTextBrownies() {
        onView(withText(BROWNIES)).check(matches(isDisplayed()));
    }

    @Test
    public void testRecyclerViewHasItem_withTextYellowCake() {
        onView(withText(YELLOW_CAKE)).check(matches(isDisplayed()));
    }

    @Test
    public void testRecyclerViewHasItem_withTextCheesecake() {
        onView(withText(CHEESECAKE)).check(matches(isDisplayed()));
    }

    @Test
    public void clickRecyclerViewItemPositionZero_checkTextMatches() {
        onView(ViewMatchers.withId(R.id.rv_recipes)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, click()));
        testRecyclerViewHasItem_withTextNutellaPie();
    }
}
