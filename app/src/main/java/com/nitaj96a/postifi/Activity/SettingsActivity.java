package com.nitaj96a.postifi.Activity;

import android.content.Intent;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;

import android.os.Bundle;
import android.util.Log;


import com.nitaj96a.postifi.R;

public class SettingsActivity extends PreferenceActivity {

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getBaseContext(), PostsActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_settings);
        addPreferencesFromResource(R.xml.preferences);

        final CheckBoxPreference cbSortByDate = (CheckBoxPreference) getPreferenceManager().findPreference("pref_sort_by_date");
        cbSortByDate.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                Log.d("MyApp", "Pref " + preference.getKey() + " changed to " );
                CheckBoxPreference cbSortByPopularity = (CheckBoxPreference) findPreference("pref_sort_by_popularity");

                cbSortByPopularity.setChecked(false);
                //cbSortByPopularity.setEnabled(true);
                cbSortByDate.setChecked(true);
                //cbSortByDate.setEnabled(true);
                return true;
            }
        });

        final CheckBoxPreference cbSortByPopularity = (CheckBoxPreference) getPreferenceManager().findPreference("pref_sort_by_popularity");
        cbSortByPopularity.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                Log.d("MyApp", "Pref " + preference.getKey() + " changed to " );
                cbSortByPopularity.setChecked(true);
                //cbSortByPopularity.setEnabled(true);
                cbSortByDate.setChecked(false);
                //cbSortByDate.setEnabled(false);
                return true;
            }
        });

        final CheckBoxPreference cbSortCommentByPopularity = (CheckBoxPreference) getPreferenceManager().findPreference("pref_sort_comments_by_popularity");
        cbSortCommentByPopularity.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                Log.d("MyApp", "Pref " + preference.getKey() + " changed to ");
                CheckBoxPreference cbSortCommentsByDate = (CheckBoxPreference) findPreference("pref_sort_comments_by_date");

                cbSortCommentByPopularity.setChecked(true);
                //cbSortByPopularity.setEnabled(true);
                cbSortCommentsByDate.setChecked(false);
                return true;
            }
        });

        final CheckBoxPreference cbSortCommentByDate = (CheckBoxPreference) getPreferenceManager().findPreference("pref_sort_comments_by_date");
        cbSortCommentByDate.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                Log.d("MyApp", "Pref " + preference.getKey() + " changed to ");
                cbSortCommentByPopularity.setChecked(false);
                cbSortCommentByDate.setChecked(true);
                return true;
            }
        });

    }

}