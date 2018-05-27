package com.nitaj96a.postifi.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.nitaj96a.postifi.CommentAdapter;
import com.nitaj96a.postifi.Model.Comment;
import com.nitaj96a.postifi.Pager;
import com.nitaj96a.postifi.R;

import java.util.ArrayList;

public class ReadPostActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener, ViewPager.OnPageChangeListener{

    private ListView listView;
    private CommentAdapter commentAdapter;

    private DrawerLayout drawerLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String comment;

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

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        tabLayout.addTab(tabLayout.newTab().setText("Post Info"));
        tabLayout.addTab(tabLayout.newTab().setText("Comments"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.pager);

        Pager adapter = new Pager(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(adapter);

        tabLayout.addOnTabSelectedListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_read_post, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_delete:
                Log.i("delete", "clicked delete");
                return true;

            case R.id.action_comment:
                Log.i("comment", "clicked comment");
                startCommentDialog(this);
                // Send a POST request to api and create a new comment
                // toast "Sending comment"
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
            public void onClick(DialogInterface dialog, int which) {
                comment = input.getText().toString();
                Log.i("comment", comment);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                comment = null;
            }
        });

        builder.show();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
        if (tabLayout.getSelectedTabPosition() == 1) {

            listView = (ListView) findViewById(R.id.list_view_comments);
            Log.i("list_view", listView.toString());
            ArrayList<Comment> commentsList = new ArrayList<>();

            // Filtering and Sorting code will probably go here
            // get a list of all posts
            // filter it with a lambda func ?
            // apply sort from SharedPreferences...


            commentAdapter = new CommentAdapter(this, commentsList);
            listView.setAdapter(commentAdapter);
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
