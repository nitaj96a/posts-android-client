package com.nitaj96a.postifi.Service;

import com.nitaj96a.postifi.Model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by n on 5/27/2018.
 */

public interface UserService {

    @Headers({
            "Content-Type:application/json"
    })

    // or use a query parameter like GET /users?username={username}
    @GET("users/username/{username}")
    Call<User> getUserByUsername(@Path("username") String username);
}
