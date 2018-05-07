package com.nitaj96a.postifi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void btnStartPostsActivity(View view) {
        Intent intent = new Intent(this, PostsActivity.class);
        startActivity(intent);
    }

}
