package com.example.tara.restaurantrevisitedsqlite;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends DialogFragment {

    private MyOrderDatabase mOrderDb;
    private OrderAdapter orderAdapter;
//    private List<String> yourOrder = new ArrayList<>();
    private ListView listView;
    private Context theContext;
    private TextView amount;
    private TextView yourOrder;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("taart", "cake2");

        theContext = getActivity();

        View mView = inflater.inflate(R.layout.fragment_order, container, false);
        listView = mView.findViewById(R.id.list_view);

        amount = mView.findViewById(R.id.amount);
        yourOrder = mView.findViewById(R.id.your_order);
        listView.setOnItemClickListener(new ItemClickListener());

        mOrderDb = MyOrderDatabase.getInstance(theContext.getApplicationContext());






        // Inflate the layout for this fragment
        return mView;

    }

    private class ItemClickListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, final long id) {
            TextView title = view.findViewById(R.id.title);

            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(theContext);
            alertDialogBuilder.setMessage("Are you sure you want to delete " + title + "?");

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }


    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        Log.d("taart", "cake3");

        orderAdapter = new OrderAdapter(theContext.getApplicationContext(), mOrderDb.getData(), yourOrder);

        listView.setAdapter(orderAdapter);
    }

}


