package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.codepath.apps.restclienttemplate.fragments.HomeTimelineFragment;
import com.codepath.apps.restclienttemplate.fragments.TweetsListFragment;
import com.codepath.apps.restclienttemplate.fragments.TweetsPagesAdapter;
import com.codepath.apps.restclienttemplate.models.Tweet;

/**
 * Created by splotnik on 6/27/17.
 */
public class TimelineActivity extends AppCompatActivity implements TweetsListFragment.TweetSelectedListener{

    private SwipeRefreshLayout swipeContainer;
    HomeTimelineFragment fragmentHomeTimeline;
    // Instance of the progress action-view
    MenuItem miActionProgressItem;



    //    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    public void onComposeAction(MenuItem mi) {
        // first parameter is the context, second is the class of the activity to launch
        Intent i = new Intent(this, ComposeActivity.class);
        startActivityForResult(i, 1); // brings up the second activity
    }

    public void onProfileView(MenuItem item){
        Intent i = new Intent(this, ProfileActivity.class);
        startActivity(i);
    }

    public void onTweetSelected(Tweet tweet){
        Intent i = new Intent(this, DetailsActivity.class);
        startActivity(i);
        //Toast.makeText(this, tweet.body, Toast.LENGTH_SHORT).show();
    }

    public void onReplyAction(View view){
        Intent i = new Intent(this, ReplyActivity.class);
        startActivityForResult(i,1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_profile);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);

        // Display icon in the toolbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.twitter_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

            ViewPager vpPager = (ViewPager) findViewById(R.id.viewpager);
            vpPager.setAdapter(new TweetsPagesAdapter(getSupportFragmentManager(), this));
            TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
            tabLayout.setupWithViewPager(vpPager);

    }





    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // Store instance of the menu item containing progress
        miActionProgressItem = menu.findItem(R.id.miActionProgress);
        // Extract the action-view from the menu item
        ProgressBar v =  (ProgressBar) MenuItemCompat.getActionView(miActionProgressItem);
        // Return to finish
        return super.onPrepareOptionsMenu(menu);
    }



}