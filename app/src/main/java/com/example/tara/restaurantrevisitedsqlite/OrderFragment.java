package com.example.tara.restaurantrevisitedsqlite;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class OrderFragment extends DialogFragment {

    private MenuAdapter menuAdapter;
    private MyOrderDatabase mOrderDb;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_order);

//        mOrderDb = MyOrderDatabase.getInstance(getApplicationContext());

        final Bundle bundle = this.getArguments();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order, container, false);
    }

//    @Override
//    public void onListItemClick(ListView l, View v, int position, long id) {
//        // delete
//    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        // get all items from database
//        final Cursor data = MyOrderDatabase.getData();
        // link a MenuAdapter to the ListView

    }

}
