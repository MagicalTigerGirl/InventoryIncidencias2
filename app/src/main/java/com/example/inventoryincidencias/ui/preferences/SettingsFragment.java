package com.example.inventoryincidencias.ui.preferences;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.inventoryincidencias.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }
}