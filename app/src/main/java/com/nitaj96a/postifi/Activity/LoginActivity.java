package com.nitaj96a.postifi.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.nitaj96a.postifi.Model.User;
import com.nitaj96a.postifi.R;
import com.nitaj96a.postifi.Service.ServiceUtils;
import com.nitaj96a.postifi.Service.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    User user;
    String username;
    String password;

    UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences=getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
    }

    public void login(final String username, final String password) {
        userService = ServiceUtils.userService;

        Call<User> call = userService.getUserByUsername(username);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                user = response.body();
                if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    // Better store a JWT ...
                    editor.putString("currentUser", new Gson().toJson(user));

                    editor.commit();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
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
