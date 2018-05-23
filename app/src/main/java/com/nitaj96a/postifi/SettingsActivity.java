package com.nitaj96a.postifi;

import android.preference.PreferenceActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_settings);
        addPreferencesFromResource(R.xml.preferences);

    }

}