package com.nitaj96a.postifi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.nitaj96a.postifi.Model.JSONArrayContainerPosts;
import com.nitaj96a.postifi.Model.JSONArrayContainerTags;
import com.nitaj96a.postifi.Model.Post;
import com.nitaj96a.postifi.Model.Tag;
import com.nitaj96a.postifi.PostAdapter;
import com.nitaj96a.postifi.R;
import com.nitaj96a.postifi.Service.PostService;
import com.nitaj96a.postifi.Service.ServiceUtils;
import com.nitaj96a.postifi.Service.TagService;

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
    private TagService tagService;
    private List<Post> postsList = new ArrayList<>();
    private List<Tag> tagsList = new ArrayList<>();

    private Post selectedPost;
    private JSONArrayContainerPosts posts;
    private JSONArrayContainerTags tags;

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

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
                                finish();
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
                                finish();
                                return true;
                        }
                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });


        // --------------- LOADING TAGS BEFORE POSTS

        tagService = ServiceUtils.tagService;

        Call<JSONArrayContainerTags> callTags = tagService.getTags();

        callTags.enqueue(new Callback<JSONArrayContainerTags>() {
            @Override
            public void onResponse(Call<JSONArrayContainerTags> call, Response<JSONArrayContainerTags> response) {
                tags = response.body();
                tagsList = tags.getTag();
            }

            @Override
            public void onFailure(Call<JSONArrayContainerTags> call, Throwable t) {

            }
        });


//        Call<ResponseBody> responseBodyCall = postService.getPostsResponseBody();
//
//        responseBodyCall.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                Log.w("2. Full json res ",new GsonBuilder().setPrettyPrinting().create().toJson(response));
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//            }
//        });

        // ------------- LIST VIEW -------------------

        listView = (ListView) findViewById(R.id.list_view_posts);

        postService = ServiceUtils.postService;


        Call<JSONArrayContainerPosts> call = postService.getPosts();


        call.enqueue(new Callback<JSONArrayContainerPosts>() {
            @Override
            public void onResponse(Call<JSONArrayContainerPosts> call, Response<JSONArrayContainerPosts> response) {
                Log.i("postslist", response.body().toString());

                posts = response.body();
                postsList = posts.getPost();

                // Filtering and Sorting code will probably go here
                // get a list of all posts
                // filter it with a lambda func ?
                // apply sort from SharedPreferences...
                postsAdapter = new PostAdapter(getApplicationContext(),(ArrayList<Post>) postsList);
                listView.setAdapter(postsAdapter);
            }

            @Override
            public void onFailure(Call<JSONArrayContainerPosts> call, Throwable t) {
                Log.i("call", call.toString());
                Log.i("call", "in enqueue");
                t.printStackTrace();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedPost = postsList.get(i);
                Log.i("selected post",selectedPost.toString());
                Intent startReadPost = new Intent(PostsActivity.this, ReadPostActivity.class);
                startReadPost.putExtra("Post", new Gson().toJson(selectedPost));
                startActivity(startReadPost);
                finish();
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
                Intent intent = new Intent(getBaseContext(), CreatePostActivity.class);
                finish();
                startActivity(intent);
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
