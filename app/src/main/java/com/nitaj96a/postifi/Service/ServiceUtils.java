package com.nitaj96a.postifi.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nitaj96a.postifi.Model.User;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by n on 5/27/2018.
 */

public class ServiceUtils {

    public static OkHttpClient createClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(interceptor).build();
        return client;
    }

    static final String BASE_URL="http://580b8697.ngrok.io/PostifiRest/api/";

    static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
            .create();

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(createClient())
            .build();

    public static PostService postService = retrofit.create(PostService.class);
    public static UserService userService = retrofit.create(UserService.class);
    public static CommentService commentService = retrofit.create(CommentService.class);
    public static TagService tagService = retrofit.create(TagService.class);
}
