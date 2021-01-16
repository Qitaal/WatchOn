package com.example.watchon.ui.main;

import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;

import com.example.watchon.utils.EspressoIdlingResource;
import com.example.watchon.R;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

public class MainActivityTest {

    @Before
    public void setUp() {
        ActivityScenario.launch(MainActivity.class);
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void loadMovie(){
        onView(withId(R.id.rv_movie_list)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_movie_list)).perform(RecyclerViewActions.scrollToPosition(2));
    }

    @Test
    public void loadTvShow(){
        Matcher<View> matcher = allOf(withText("TV SHOW"),
                isDescendantOfA(withId(R.id.tab_layout_main)));
        onView(matcher).perform(click());

        onView(withId(R.id.rv_tv_show_list)).check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.rv_tv_show_list)).perform(RecyclerViewActions.scrollToPosition(2));
    }

    @Test
    public void loadDetailMovie(){
        onView(withId(R.id.rv_movie_list)).check(matches(isCompletelyDisplayed()));

        onView(withId(R.id.rv_movie_list)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_movie_list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_genre)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_length)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_release_date_detail)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()));
    }

    @Test
    public void loadDetailTvShow(){
        Matcher<View> matcher = allOf(withText("TV SHOW"),
                isDescendantOfA(withId(R.id.tab_layout_main)));
        onView(matcher).perform(click());
        onView(withId(R.id.rv_tv_show_list)).check(matches(isCompletelyDisplayed()));

        onView(withId(R.id.rv_tv_show_list)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_tv_show_list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_genre)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_length)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_release_date_detail)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()));
    }

    @Test
    public void loadFavoriteMovies(){
        onView(withId(R.id.rv_movie_list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.fab_favorite)).perform(click());
        onView(isRoot()).perform(ViewActions.pressBack());
        onView(withId(R.id.menu_favorite)).perform(click());

        onView(withId(R.id.rv_favorite_movie_list)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_favorite_movie_list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_genre)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_length)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_release_date_detail)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()));
    }

    @Test
    public void loadFavoriteTvShow(){
        Matcher<View> matcher = allOf(withText("TV SHOW"),
                isDescendantOfA(withId(R.id.tab_layout_main)));
        onView(matcher).perform(click());
        onView(withId(R.id.rv_tv_show_list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.fab_favorite)).perform(click());
        onView(isRoot()).perform(ViewActions.pressBack());
        onView(withId(R.id.menu_favorite)).perform(click());
        Matcher<View> favoriteMatcher = allOf(withText("TV SHOW"),
                isDescendantOfA(withId(R.id.tab_layout_favorite)));
        onView(favoriteMatcher).perform(click());

        onView(withId(R.id.rv_favorite_tv_show_list)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_favorite_tv_show_list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_genre)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_length)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_release_date_detail)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()));
    }
}