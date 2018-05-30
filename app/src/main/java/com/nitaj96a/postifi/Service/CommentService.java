package com.nitaj96a.postifi.Service;

import com.nitaj96a.postifi.Model.Comment;
import com.nitaj96a.postifi.Model.JSONArrayContainerComments;
import com.nitaj96a.postifi.Model.Post;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by n on 5/27/2018.
 */

public interface CommentService {

    @Headers({
            "User-Agent: Mobile-Android",
            "Content-Type:application/json"
    })

    // tricky url design?
    @GET("comments/post/{id}")
    Call<JSONArrayContainerComments> getCommentsByPostId(@Path("id") Integer id);

    // whichever you prefer...
    @POST("comments/post/{id}")
    Call<Comment> createCommentByPostId(@Path("id") Integer id, @Body Comment comment);

    @POST("comments")
    Call<Comment> createComment(@Body Comment comment);

    @DELETE("comments/{id}")
    Call<Void> deleteCommentById(@Path("id") Integer id);

    @DELETE("comments/post/{id}")
    Call<Void> deleteCommentByPostId(@Path("id") Integer id);
}
