package com.nitaj96a.postifi;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nitaj96a.postifi.Model.Post;

/**
 * Created by n on 5/26/2018.
 */

public class TabPostInfo extends Fragment {
    TextView textViewTitle;
    TextView textViewDescription;
    TextView textViewLikesDislikes;
    TextView textViewAuthor;
    TextView textViewLocation;
    Post currentPost;

    public TabPostInfo() {
    }

    public static TabPostInfo newInstance(Post post) {
        TabPostInfo tabPostInfo = new TabPostInfo();
        Bundle args = new Bundle();
        tabPostInfo.currentPost = post;
        String postJSON = new Gson().toJson(post);
        args.putString("currentPost", postJSON);
        tabPostInfo.setArguments(args);
        return tabPostInfo;
    }


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.tab_post_info, container, false);
        textViewTitle = view.findViewById(R.id.textView4);
        textViewTitle.setText(currentPost.getTitle());
        textViewDescription = view.findViewById(R.id.textView5);
        textViewDescription.setText(currentPost.getDescription());
        textViewLikesDislikes = view.findViewById(R.id.textView6);
        textViewLikesDislikes.setText(currentPost.getLikes() + " \uD83D\uDC4D " + currentPost.getDislikes() + " \uD83D\uDC4E");
        textViewAuthor = view.findViewById(R.id.textView8);
        textViewAuthor.setText(currentPost.getOwner().getUsername());
        textViewLocation = view.findViewById(R.id.textView7);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentPost = new Gson().fromJson(getArguments().getString("currentPost"), Post.class);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void setTextViewAuthorText(String text) {
        this.textViewAuthor.setText(text);
    }

    public void setTextViewTitleText(String text) {
        this.textViewTitle.setText(text);
    }

    public void setTextViewDescriptionText(String text) {
        this.textViewDescription.setText(text);
    }

    public void setTextViewLikesDislikesText(String text) {
        this.textViewLikesDislikes.setText(text);
    }

    public void setTextViewLocationText(String text) {
        this.textViewLocation.setText(text);
    }
}
