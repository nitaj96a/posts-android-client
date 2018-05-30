package com.nitaj96a.postifi.Activity;

import android.preference.PreferenceActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.nitaj96a.postifi.R;

public class SettingsActivity extends PreferenceActivity {

    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_settings);
        addPreferencesFromResource(R.xml.preferences);

    }

}