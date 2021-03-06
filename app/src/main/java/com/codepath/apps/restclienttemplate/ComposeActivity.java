package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ComposeActivity extends AppCompatActivity {

    EditText tweet_txt;
    TwitterClient client;
    private final int REQUEST_CODE = 20;

    String tweet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        client = TwitterApp.getRestClient();
        tweet_txt = (EditText) findViewById(R.id.tweet_txt);
        tweet = tweet_txt.getText().toString();

    }

    public void onSubmit(View v) {

        tweet = tweet_txt.getText().toString();

        // closes the activity and returns to first screen
        client.sendTweet(tweet, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("TwitterClient", response.toString());


                try {
                    Tweet tweet = Tweet.fromJSON(response);
                    Intent i = new Intent(ComposeActivity.this, TimelineActivity.class);
                    i.putExtra("tweet", tweet); // pass arbitrary data to launched activity
                    startActivityForResult(i, REQUEST_CODE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("TwitterClient", responseString);
                throwable.printStackTrace();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("TwitterClient", errorResponse.toString());
                throwable.printStackTrace();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                Log.d("TwitterClient", errorResponse.toString());
                throwable.printStackTrace();
            }
        });

        Intent data = new Intent();
        // Pass relevant data back as a result
        data.putExtra("name", tweet_txt.getText().toString());
        data.putExtra("code", 200); // ints work too
        // Activity finished ok, return the data
        setResult(RESULT_OK, data);

        this.finish();

    }
}