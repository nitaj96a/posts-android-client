package com.nitaj96a.postifi.Service;

import com.nitaj96a.postifi.Model.JSONArrayContainerPosts;
import com.nitaj96a.postifi.Model.Post;

import okhttp3.ResponseBody;
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

public interface PostService {

    @Headers({
            "User-Agent: Mobile-Android",
            "Content-Type:application/json"
    })

    //localhost:8080/api/posts
    @GET("posts")
    Call<JSONArrayContainerPosts> getPosts();

    @GET("posts")
    Call<ResponseBody> getPostsResponseBody();

    @GET("posts/{id}")
    Call<Post> getPostById(@Path("id") Integer id);

    @POST("posts")
    Call<Post> createPost(@Body Post post);

    @DELETE("posts/{id}")
    Call<Void>deletePost(@Path("id") Integer id);

    @GET("posts/like/{id}")
    Call<Void> likePostById(@Path("id") Integer id);

    @GET("posts/dislike/{id}")
    Call<Void> dislikePostById(@Path("id") Integer id);
}
