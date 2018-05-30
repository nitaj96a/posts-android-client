package com.nitaj96a.postifi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by n on 5/30/2018.
 */

public class JSONArrayContainerComments {
    @SerializedName("comment")
    @Expose
    private List<Comment> comment = null;

    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }
}
