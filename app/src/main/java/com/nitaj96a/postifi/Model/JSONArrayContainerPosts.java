package com.nitaj96a.postifi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by n on 5/29/2018.
 */

public class JSONArrayContainerPosts {
    @SerializedName("Post")
    @Expose
    private List<Post> post = null;

    public List<Post> getPost() {
        return post;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }
}
