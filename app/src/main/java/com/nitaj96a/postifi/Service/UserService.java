package com.nitaj96a.postifi.Service;

import com.nitaj96a.postifi.Model.JSONContainerUser;
import com.nitaj96a.postifi.Model.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by n on 5/27/2018.
 */

public interface UserService {

    @Headers({
            "User-Agent: Mobile-Android",
            "Content-Type:application/json"
    })

    // or use a query parameter like GET /users?username={username}
    @GET("users/username/{username}")
    Call<JSONContainerUser> getUserByUsername(@Path("username") String username);

    @GET("users/username/{username}")
    Call<ResponseBody> getUserByUsernameResponseBody(@Path("username") String username);
}
