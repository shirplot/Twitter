package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

import java.util.List;

/**
 * Created by splotnik on 6/26/17.
 */
public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.ViewHolder> {
    private List<Tweet> mTweets;
    Context context ;
    private TweetAdapterListener mListener;

    public interface TweetAdapterListener{
        public void onItemSelected(View view, int position);
    }


    public TweetAdapter(List<Tweet> tweets, TweetAdapterListener listener) {
    mTweets =tweets;
        mListener = listener;
}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View tweetView = inflater.inflate(R.layout.item_tweet, parent, false);
        ViewHolder viewHolder = new ViewHolder(tweetView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Tweet tweet = mTweets.get(position);

        holder.tvUsername.setText(tweet.user.name);
        holder.tvBody.setText(tweet.body);
        holder.createdAt.setText(tweet.getRelativeCreatedAt());
        holder.tvScreenName.setText(tweet.user.screenName);

        Glide.with(context).load(tweet.user.profileImageUrl).into(holder.ivProfileImage);

        // Add on click listener for detail button
        Button profile_button = holder.profile_button;
        profile_button.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Package and send over the parent tweet
                Intent i = new Intent(context, ProfileActivity.class);

                // Pass relevant data back as a result
                i.putExtra("profile", Parcels.wrap(tweet));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTweets.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivProfileImage;
        public TextView tvUsername;
        public TextView tvBody;
        public TextView createdAt;
        public TextView tvScreenName;
        public Button profile_button;


        public ViewHolder(View itemView) {
            super(itemView);

            ivProfileImage = (ImageView) itemView.findViewById(R.id.ivProfileImage);
            tvUsername = (TextView) itemView.findViewById(R.id.tvUsername);
            tvBody = (TextView) itemView.findViewById(R.id.tvBody);
            createdAt = (TextView) itemView.findViewById(R.id.tvCreatedAt);
            tvScreenName = (TextView) itemView.findViewById(R.id.tvScreenName);
            profile_button = (Button) itemView.findViewById(R.id.profile_button);
            //handle click event
            itemView.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view){
                    if (mListener != null){
                        int position = getAdapterPosition();
                        mListener.onItemSelected(view, position);
                    }
                }
            });
        }

    }



    // Clean all elements of the recycler
    public void clear() {
        mTweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Tweet> list) {
        mTweets.addAll(list);
        notifyDataSetChanged();
    }

}