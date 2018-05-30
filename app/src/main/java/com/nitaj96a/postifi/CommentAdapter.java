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

import com.nitaj96a.postifi.Model.Comment;
import com.nitaj96a.postifi.Model.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by n on 5/27/2018.
 */

public class CommentAdapter extends ArrayAdapter<Comment> {
    private Context mContext;
    private List<Comment> commentsList = new ArrayList<>();

    public CommentAdapter(Context context, List<Comment> list) {
        super(context, 0, list);
        mContext = context;
        commentsList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        if (listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item_comment, parent, false);

        Comment currentComment = commentsList.get(position);

        ImageView image = (ImageView) listItem.findViewById(R.id.imageView_commenter_thumb);
        image.setImageBitmap(currentComment.getOwner().getPhoto());

        TextView text = (TextView) listItem.findViewById(R.id.textView_comment);
        text.setText(currentComment.getDescription());

        TextView likesDislikes = (TextView) listItem.findViewById(R.id.textView_like_dislike_count);
        likesDislikes.setText(currentComment.getLikes() + " \uD83D\uDC4D " + currentComment.getDislikes() + " \uD83D\uDC4E");

        TextView author = (TextView) listItem.findViewById(R.id.textView_author_name);
        author.setText(currentComment.getOwner().getUsername());

        return listItem;
    }
}
