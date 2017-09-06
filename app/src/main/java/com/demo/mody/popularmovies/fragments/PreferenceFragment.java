package com.demo.mody.popularmovies.fragments;

import android.os.Bundle;

import com.demo.mody.popularmovies.R;

/**
 * Created by Mahmoud El-Metainy on 08-Jan-16.
 */
public class PreferenceFragment extends android.preference.PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Attaching preference resource
        addPreferencesFromResource(R.xml.preferences);
    }
}
