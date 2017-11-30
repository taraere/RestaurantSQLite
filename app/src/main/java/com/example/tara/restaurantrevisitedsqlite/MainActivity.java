package com.example.tara.restaurantrevisitedsqlite;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private CategoriesFragment mDataFragment;
    private static final String TAG_RETAINED_FRAGMENT = "CategoriesFragment";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find the retained fragment as activity restarts
        FragmentManager fm = getSupportFragmentManager();
        CategoriesFragment fragment = new CategoriesFragment();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, fragment, TAG_RETAINED_FRAGMENT);
        ft.commit();
    }
}
