package com.nitaj96a.postifi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nitaj96a.postifi.Model.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by n on 5/26/2018.
 */

public class PostAdapter extends ArrayAdapter<Post> {
    private Context mContext;
    private ArrayList<Post> postsList = new ArrayList<>();

    public PostAdapter(Context context, ArrayList<Post> list) {
        super(context, 0, list);
        mContext = context;
        postsList = list;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        if (listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item_post, parent, false);

        Post currentPost = postsList.get(position);

//        AppCompatImageView image = (AppCompatImageView) listItem.findViewById(R.id.imageView_post_thumb);
//
//        Log.i("tagname", currentPost.getTag().getName());
//        if (currentPost.getTag().getName() == "boxing")
//            image.setImageResource(R.drawable.bg);
//        else if (currentPost.getTag().getName() == "ps4")
//            image.setImageResource(R.drawable.ps4);
//        else if (currentPost.getTag().getName() == "crypto")
//            image.setImageResource(R.drawable.ethereum);
//
//        image.refreshDrawableState();


        TextView title = (TextView) listItem.findViewById(R.id.textView_post_title);
        title.setText(currentPost.getTitle());
        Log.i("currentPost", currentPost.toString());

        TextView likesDislikes = (TextView) listItem.findViewById(R.id.textView_like_dislike_count);
        likesDislikes.setText(currentPost.getLikes() + " \uD83D\uDC4D " + currentPost.getDislikes() + " \uD83D\uDC4E");

        TextView tagName = (TextView) listItem.findViewById(R.id.textView_tag_name);
        tagName.setText(currentPost.getTag().getName());

        return listItem;
    }

}
