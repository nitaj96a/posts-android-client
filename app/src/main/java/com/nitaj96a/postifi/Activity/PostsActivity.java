package com.nitaj96a.postifi.Activity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.nitaj96a.postifi.Model.Post;
import com.nitaj96a.postifi.PostAdapter;
import com.nitaj96a.postifi.R;
import com.nitaj96a.postifi.Service.PostService;
import com.nitaj96a.postifi.Service.ServiceUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by n on 5/7/2018.
 */

public class PostsActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;

    private ListView listView;
    private PostAdapter postsAdapter;

    private PostService postService;
    private ArrayList<Post> postsList = new ArrayList<>();

    private Post selectedPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        // ----------- NAVIGATION DRAWER -------------------
        mDrawerLayout = findViewById(R.id.posts_drawer_layout);

        NavigationView navigationView = findViewById(R.id.left_drawer);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        //menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        switch (menuItem.getItemId()) {
                            case R.id.nav_all_posts:
                                // Already here ...
                                return true;
                            case R.id.nav_create_post:
                                Intent intent = new Intent(getBaseContext(), CreatePostActivity.class);
                                startActivity(intent);
                                return true;
                            case R.id.nav_settings:
                                Intent intent_s = new Intent(getBaseContext(), SettingsActivity.class);
                                startActivity(intent_s);
                                return true;
                            case R.id.nav_logout:
                                // Remove login data from SharedPreferences...
                                Intent intent_l = new Intent(getBaseContext(), LoginActivity.class);
                                // Clear back-stack to disable back navigation ?
                                startActivity(intent_l);
                                return true;
                        }
                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });
        // ------------- LIST VIEW -------------------

        listView = (ListView) findViewById(R.id.list_view_posts);


        postService = ServiceUtils.postService;


        Call call = postService.getPosts();

        call.enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                postsList = response.body();
                // Filtering and Sorting code will probably go here
                // get a list of all posts
                // filter it with a lambda func ?
                // apply sort from SharedPreferences...
                postsAdapter = new PostAdapter(getApplicationContext(), postsList);
                listView.setAdapter(postsAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedPost = postsList.get(i);
                Intent startReadPost = new Intent(PostsActivity.this, ReadPostActivity.class);
                startReadPost.putExtra("Post", new Gson().toJson(selectedPost));
                startActivity(startReadPost);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_posts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_add:
                Log.i("add", "clicked add");
                return true;

            case R.id.action_filter:
                Log.i("filter", "clicked filter");
                return true;

            // no "home" id...
            case R.id.homeAsUp:
                Log.i("home", "clicked");
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;

            default:
                Log.i("default", "clicked default");
                mDrawerLayout.openDrawer(GravityCompat.START);
                return super.onOptionsItemSelected(item);

        }
    }
}
