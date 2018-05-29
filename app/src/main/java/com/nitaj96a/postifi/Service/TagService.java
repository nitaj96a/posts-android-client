package com.nitaj96a.postifi.Service;

import com.nitaj96a.postifi.Model.JSONArrayContainerTags;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by n on 5/29/2018.
 */

public interface TagService {
    @Headers({
            "User-Agent: Mobile-Android",
            "Content-Type:application/json"
    })

    @GET("tags")
    Call<JSONArrayContainerTags> getTags();

}
