package com.codepath.apps.restclienttemplate.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by splotnik on 7/5/17.
 */

public class TweetsPagesAdapter extends FragmentPagerAdapter {

    private String tabTitles[] = new String[] {"Home", "Mentions"};
    private Context context;

    public TweetsPagesAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }
    //return total # of fragments

    @Override
    public int getCount() {
        return 2;
    }


    //return the fragment to use depending on the positiuon

    @Override
    public Fragment getItem(int position) {
        if(position ==0){
            return new HomeTimelineFragment();
        }
        else if (position==1){
            return new MentionsTimelineFragment();
        }else{
            return null;
        }
    }


    //return title

    @Override
    public CharSequence getPageTitle(int position){
        return tabTitles[position];
    }
}
