package com.nitaj96a.postifi.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nitaj96a.postifi.Model.JSONContainerUser;
import com.nitaj96a.postifi.Model.User;
import com.nitaj96a.postifi.R;
import com.nitaj96a.postifi.Service.ServiceUtils;
import com.nitaj96a.postifi.Service.UserService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private User user;
    private String username;
    private String password;

    private UserService userService;
    private JSONContainerUser containerUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences=getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
    }

    public void login(final String username, final String password) {
        userService = ServiceUtils.userService;

//        Call<ResponseBody> responseBodyCall = userService.getUserByUsernameResponseBody(username);
//
//        responseBodyCall.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                //Log.i("response body", response.body().string());
//                Log.w("2.0 getFeed => ",new GsonBuilder().setPrettyPrinting().create().toJson(response));
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//            }
//        });

        Call<JSONContainerUser> call = userService.getUserByUsername(username);

        call.enqueue(new Callback<JSONContainerUser>() {
            @Override
            public void onResponse(Call<JSONContainerUser> call, Response<JSONContainerUser> response) {
                containerUser = response.body();
                user = containerUser.getUser();
                Log.i("response", user.toString());
                Log.i("response1", response.raw().body().toString());
                Log.i("responsebody", response.body().toString());
                //Log.i("responsebodyusername", response.body().getUsername());
                if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    // Better store a JWT ...
                    editor.putString("currentUser", new Gson().toJson(user));

                    editor.commit();
                }
            }

            @Override
            public void onFailure(Call<JSONContainerUser> call, Throwable t) {
                // Make a toast or something...
                t.printStackTrace();
            }
        });
    }

    public void btnStartPostsActivity(View view) {
        // grab text from ui
        // don't use camelCase in xml?
        EditText usernameEditText = (EditText)findViewById(R.id.editTextUsername);
        EditText passwordEditText = (EditText)findViewById(R.id.editTextPassword);

        username = usernameEditText.getText().toString();
        password = passwordEditText.getText().toString();

        login(username, password);

        Intent intent = new Intent(this, PostsActivity.class);
        startActivity(intent);

        finish();
    }

}
