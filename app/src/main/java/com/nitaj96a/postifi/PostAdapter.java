package com.nitaj96a.postifi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
    private List<Post> postsList = new ArrayList<>();

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

        ImageView image = (ImageView) listItem.findViewById(R.id.imageView_post_thumb);
        image.setImageBitmap(currentPost.getPhoto());

        TextView title = (TextView) listItem.findViewById(R.id.textView_post_title);
        title.setText(currentPost.getTitle());

        TextView likesDislikes = (TextView) listItem.findViewById(R.id.textView_like_dislike_count);
        likesDislikes.setText(currentPost.getLikes() + " \uD83D\uDC4D " + currentPost.getDislikes() + " \uD83D\uDC4E");

        TextView tagName = (TextView) listItem.findViewById(R.id.textView_tag_name);
        tagName.setText(currentPost.getTag().getName());

        return listItem;
    }
}
