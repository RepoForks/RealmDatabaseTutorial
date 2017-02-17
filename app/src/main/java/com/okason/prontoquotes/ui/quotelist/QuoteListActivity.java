package com.okason.prontoquotes.ui.quotelist;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.okason.prontoquotes.R;
import com.okason.prontoquotes.data.SampleData;
import com.okason.prontoquotes.utils.Constants;

public class QuoteListActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
        addDefaultData();




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        openFragment(new QuoteListFragment(), "Motivational Quotes");
    }

    private void openFragment(Fragment fragment, String screenTitle){
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
        getSupportActionBar().setTitle(screenTitle);
    }

    @Override
    protected void onDestroy() {
       super.onDestroy();
    }

    //Checks if this is the first time this app is running and then
    //starts an Intent Services that adds some default data
    private void addDefaultData() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        editor = sharedPreferences.edit();
        if (sharedPreferences.getBoolean(Constants.FIRST_RUN, true)) {
            SampleData data = new SampleData(this);
            editor.putBoolean(Constants.FIRST_RUN, false).commit();
        }

    }


}
