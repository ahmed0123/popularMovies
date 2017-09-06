package com.demo.mody.popularmovies.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.demo.mody.popularmovies.R;
import com.demo.mody.popularmovies.fragments.DetailsFragment;
import com.demo.mody.popularmovies.fragments.MainFragment;
import com.demo.mody.popularmovies.models.DiscoverMovie;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainFragment.Communicator {

    @Bind(R.id.frame_detailsFragment)
    FrameLayout frameDetailsFragment;
    private boolean twoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setting Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Checking if it's a tablet or phone
        twoPane = getResources().getBoolean(R.bool.isTablet);

        if (twoPane) {

            // Initializing views
            ButterKnife.bind(this);
        }
    }

    @Override
    protected void onResume() {

        super.onResume();

        if (twoPane) {

            // Hide the fragment
            frameDetailsFragment.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            // Starting setting activity
            Intent intent = new Intent(this, PreferenceActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Invoked when an item is clicked on the RecyclerView
    @Override
    public void onResponse(DiscoverMovie discoverMovie) {

        if (twoPane) {

            // Start DetailsFragment if it's a tablet
            Bundle arguments = new Bundle();
            arguments.putSerializable("EXTRA_SESSION_ID", discoverMovie);

            DetailsFragment detailViewFragment = new DetailsFragment();
            detailViewFragment.setArguments(arguments);

            // Start the fragment transaction
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_detailsFragment, detailViewFragment, "fragmentDetails").commit();

            // Show the fragment
            frameDetailsFragment.setVisibility(View.VISIBLE);
        }
        else {

            // Start DetailsActivity if it's a phone
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra("EXTRA_SESSION_ID", discoverMovie);
            startActivity(intent);
        }
    }
}
