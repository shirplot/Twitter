package com.codepath.apps.restclienttemplate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

public class DetailsActivity extends AppCompatActivity {
    Tweet tweet;
    String screenName;
    String userName;
    TextView tvBody;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        tweet = (Tweet) Parcels.unwrap(getIntent().getParcelableExtra("current_tweet"));
        screenName = tweet.getUser().getScreenName();
        String body = tweet.getBody();
        userName = tweet.getUser().getName();
        id = tweet.getUid();

        tvBody = (TextView) findViewById(R.id.tvBody);
        tvBody.setText(body);

        TextView tvUsername = (TextView) findViewById(R.id.tvUsername);
        tvUsername.setText(userName);

        TextView tvScreenName = (TextView) findViewById(R.id.tvScreenName);
        tvScreenName.setText(screenName);
        ImageView ivProfileImage = (ImageView) findViewById(R.id.ivProfileImage);
        Glide.with(this).load(tweet.user.profileImageUrl).into(ivProfileImage);

    }
}
