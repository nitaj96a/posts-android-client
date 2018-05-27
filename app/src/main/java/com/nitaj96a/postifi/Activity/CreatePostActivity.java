package com.nitaj96a.postifi.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.nitaj96a.postifi.Model.Post;
import com.nitaj96a.postifi.R;

public class CreatePostActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private FusedLocationProviderClient mFusedLocationClient;
    private Post newPost;


    final int MY_PERMISSION_ACCESS_FINE_LOCATION = 45;
    final int MY_PERMISSION_ACCESS_COARSE_LOCATION = 46;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.create_post_toolbar);
        setSupportActionBar(myToolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        mDrawerLayout = findViewById(R.id.create_post_drawer_layout);

        NavigationView navigationView = findViewById(R.id.create_post_left_drawer);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        //menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        switch (menuItem.getItemId()) {
                            case R.id.nav_create_post:
                                // Already here ...
                                return true;
                            case R.id.nav_all_posts:
                                Intent intent = new Intent(getBaseContext(), PostsActivity.class);
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
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if ( ContextCompat.checkSelfPermission( this, Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions( this, new String[] {  android.Manifest.permission.ACCESS_FINE_LOCATION }, MY_PERMISSION_ACCESS_FINE_LOCATION);
        }
        if ( ContextCompat.checkSelfPermission( this, Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions( this, new String[] {  android.Manifest.permission.ACCESS_COARSE_LOCATION }, MY_PERMISSION_ACCESS_COARSE_LOCATION);
        }




    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_create_post, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_confirm:
                Log.i("confirm", "clicked confirm");
                return true;

            case R.id.action_cancel:
                Log.i("cancel", "clicked cancel");
                return true;

            // no "home" id...
            case R.id.homeAsUp:
                Log.i("home", "clicked home");
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;

            default:
                Log.i("default", "clicked default");
                mDrawerLayout.openDrawer(GravityCompat.START);
                Log.i("default", "after drawer");
                return super.onOptionsItemSelected(item);

        }
    }

}
