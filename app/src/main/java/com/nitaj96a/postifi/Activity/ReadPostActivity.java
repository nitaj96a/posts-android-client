package com.nitaj96a.postifi.Activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;

import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nitaj96a.postifi.CommentAdapter;
import com.nitaj96a.postifi.Model.Comment;
import com.nitaj96a.postifi.Model.JSONArrayContainerComments;
import com.nitaj96a.postifi.Model.Post;
import com.nitaj96a.postifi.Model.User;
import com.nitaj96a.postifi.Pager;
import com.nitaj96a.postifi.R;
import com.nitaj96a.postifi.Service.CommentService;
import com.nitaj96a.postifi.Service.PostService;
import com.nitaj96a.postifi.Service.ServiceUtils;
import com.nitaj96a.postifi.TabPostInfo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ReadPostActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener, ViewPager.OnPageChangeListener{

    private ListView listView;
    private CommentAdapter commentAdapter;

    private DrawerLayout drawerLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String commentText;
    private Comment comment;

    private Post currentPost;

    private Comment selectedComment;
    private CommentService commentService;

    private PostService postService;
    private JSONArrayContainerComments commentsList;
    private List<Comment> comments = new ArrayList<>();

    private SharedPreferences sharedPreferences;

    private List<Comment> newCommentList = new ArrayList<>();
    private JSONArrayContainerComments newComment;
    private User currentUser = new User();

    private boolean success = false;
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
        setContentView(R.layout.activity_read_post);

        Toolbar toolbar = (Toolbar) findViewById(R.id.read_post_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        drawerLayout = findViewById(R.id.read_post_drawer_layout);

        NavigationView navigationView = findViewById(R.id.read_post_left_drawer);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        //menuItem.setChecked(true);
                        // close drawer when item is tapped
                        drawerLayout.closeDrawers();

                        switch (menuItem.getItemId()) {
                            case R.id.nav_all_posts:
                                Intent intent_a = new Intent(getBaseContext(), PostsActivity.class);
                                startActivity(intent_a);
                                finish();
                                return true;
                            case R.id.nav_create_post:
                                Intent intent = new Intent(getBaseContext(), CreatePostActivity.class);
                                startActivity(intent);
                                finish();
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

        String jsonPost = null;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jsonPost = extras.getString("Post");
            Log.i("json current post", jsonPost);
        }
        currentPost = new Gson().fromJson(jsonPost, Post.class);
        Log.i("current post", currentPost.toString());


        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Post Info"));
        tabLayout.addTab(tabLayout.newTab().setText("Comments"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.pager);
        Pager adapter = new Pager(getSupportFragmentManager(), tabLayout.getTabCount(), currentPost);

        viewPager.setAdapter(adapter);
        tabLayout.addOnTabSelectedListener(this);

        sharedPreferences =  getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String currentUserJSON = sharedPreferences.getString("currentUser", "");
        currentUser.setUsername(new Gson().fromJson(currentUserJSON, User.class).getUsername());
        currentUser.setId(sharedPreferences.getInt("currentUserId", 0));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_read_post, menu);

        if (!currentPost.getOwner().getUsername().trim().equals(currentUser.getUsername().trim())){
            Log.i("name", currentPost.getOwner().getUsername());
            Log.i("logged-in name", currentUser.getUsername());
            MenuItem item = menu.findItem(R.id.action_delete);
            item.setVisible(false);
            item.setEnabled(false);
        } else {
            MenuItem item = menu.findItem(R.id.action_delete);
            item.setVisible(true);
            item.setEnabled(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_delete:
                Log.i("delete", "clicked delete");
                Toast.makeText(ReadPostActivity.this,"Attempting to delete",Toast.LENGTH_SHORT).show();

                postService = ServiceUtils.postService;
                Call<Void> call = postService.deletePost(currentPost.getId());

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(ReadPostActivity.this,"Successful delete",Toast.LENGTH_SHORT).show();
                        Intent intent_a = new Intent(getBaseContext(), PostsActivity.class);
                        finish();
                        startActivity(intent_a);

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(ReadPostActivity.this,"Failed to delete",Toast.LENGTH_SHORT).show();
                    }
                });
                return true;

            case R.id.action_comment:
                Log.i("comment", "clicked comment");
                startCommentDialog(this);
                // Send a POST request to api and create a new comment
                // toast "Sending comment"
                if (success == true) {

                    //Refresh the fragment
//                    Fragment currentFragment = getActivity().getFragmentManager().findFragmentById(R.id.fragment_container);
//                    if (currentFragment instanceof ...)) {
//                        FragmentTransaction fragTransaction =   (getActivity()).getFragmentManager().beginTransaction();
//                        fragTransaction.detach(currentFragment);
//                        fragTransaction.attach(currentFragment);
//                        fragTransaction.commit();}
//                }
                    success = false;
                }
                // if a proper response is received toast "commented successfully"
                // else toast "commenting failed"
                return true;

            // no "home" id...
            case R.id.homeAsUp:
                Log.i("home", "clicked home");
                drawerLayout.openDrawer(GravityCompat.START);
                return true;

            default:
                Log.i("default", "clicked default");
                drawerLayout.openDrawer(GravityCompat.START);
                Log.i("default", "after drawer");
                return super.onOptionsItemSelected(item);

        }
    }

    public void startCommentDialog(Context context){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Your comment");

        final EditText input = new EditText(context);
        // Try to make the input stop scrolling horizontally and break into a new line!
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setLines(5);
        input.setMaxLines(10);
        input.setGravity(Gravity.LEFT);

        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, int which) {
                commentText = input.getText().toString();
                Toast.makeText(ReadPostActivity.this,"Sending Comment",Toast.LENGTH_SHORT).show();

                newComment = new JSONArrayContainerComments();
                comment = new Comment();
                comment.setDescription(commentText);
                comment.setOwner(currentUser);

                newCommentList.add(comment);

                newComment.setComment(newCommentList);
                commentService = ServiceUtils.commentService;
                Call<Comment> call = commentService.createCommentByPostId(currentPost.getId(), comment);

                call.enqueue(new Callback<Comment>() {
                    @Override
                    public void onResponse(Call<Comment> call, Response<Comment> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(ReadPostActivity.this, "Commented succesfully", Toast.LENGTH_SHORT).show();

                        }
                        else Toast.makeText(ReadPostActivity.this,"Failed to comment",Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }

                    @Override
                    public void onFailure(Call<Comment> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                comment = null;
                commentText = null;
            }
        });

        builder.show();
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());

        if (tabLayout.getSelectedTabPosition() == 0) {

        }

        if (tabLayout.getSelectedTabPosition() == 1) {

            listView = (ListView) findViewById(R.id.list_view_comments);
            Log.i("list_view", listView.toString());

            commentService = ServiceUtils.commentService;

            Call<JSONArrayContainerComments> call = commentService.getCommentsByPostId(currentPost.getId());

            call.enqueue(new Callback<JSONArrayContainerComments>() {
                @Override
                public void onResponse(Call<JSONArrayContainerComments> call, Response<JSONArrayContainerComments> response) {
                    commentsList = response.body();
                    comments = commentsList.getComment();
                    Log.i("comments",comments.toString());

                    /*
                    Sort the list here by the criteria
                    specified in SharedPreferences

                    or design an api to support sorting

                    GET /comments?sort=date,popularity
                    */

                    commentAdapter = new CommentAdapter(getApplicationContext(), comments);
                    listView.setAdapter(commentAdapter);
                }

                @Override
                public void onFailure(Call<JSONArrayContainerComments> call, Throwable t) {
                    // maybe add some message infoming
                    // the user of the failure, maybe not...
                    t.printStackTrace();
                }
            });

        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return super.onContextItemSelected(item);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPageSelected(int position) {
        TabLayout.Tab tab = tabLayout.getTabAt(position);
        tab.select();
        Log.i("event", "onPageSelected");
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        TabLayout.Tab tab = tabLayout.getTabAt(position);
        tab.select();
        Log.i("event", "onPageScrolled");
    }
}
