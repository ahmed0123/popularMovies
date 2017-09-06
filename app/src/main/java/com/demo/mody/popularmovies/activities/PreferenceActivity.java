package com.demo.mody.popularmovies.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.demo.mody.popularmovies.fragments.PreferenceFragment;

/**
 * Created by Mahmoud El-Metainy on 08-Jan-16.
 */
public class PreferenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Attaching preferences fragment to the activity
        getFragmentManager().beginTransaction().replace(android.R.id.content, new PreferenceFragment()).commit();
    }
}
