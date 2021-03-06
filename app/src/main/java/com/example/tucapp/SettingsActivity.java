/*
 * File: SettingsActivity.java
 * Author: Brendan Ortmann
 * Owner: Ring-Co LLC
 * For: TUC Companion App
 * Date: July 2019
 *
 * Desc: "Settings" activity where users can change preferences for both the app and the TUC.
 */

package com.example.tucapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreferenceCompat;

import java.util.Objects;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);

            EditTextPreference edp = findPreference("password");
            Objects.requireNonNull(edp).setOnBindEditTextListener(new EditTextPreference.OnBindEditTextListener() {
                @Override
                public void onBindEditText(@NonNull EditText editText) {
                    editText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    editText.setMaxLines(1);
                    editText.setSelection(editText.getText().length());
                    editText.setHint("Enter a password...");
                }
            });

            Objects.requireNonNull(findPreference("dark_mode")).setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    if(preference.equals(findPreference("dark_mode"))){
                        if(newValue.equals(true)){
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                            //return true;
                        } else if(newValue.equals(false)){
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                            //return true;
                        }
                    }
                    return true;
                }
            });
        }

    }

    @Override
    protected void onPause(){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        if(sp.getString("password", getDefaultPassword()).length() == 0){
            sp.edit().putString("password", getDefaultPassword()).apply();
            Toast.makeText(this, "Password reset.", Toast.LENGTH_SHORT).show();
        }
        super.onPause();
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    // Returns default password
    protected static String getDefaultPassword(){
        return "admin";
    }
    // Returns override password
    protected static String getOverridePassword(){
        return "overridepassword";
    }
}