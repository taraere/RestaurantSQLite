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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends DialogFragment {

    MyOrderDatabase mOrderDb;
    private OrderAdapter orderAdapter;
    private ListView listView;
    private Context theContext;
    private TextView yourOrder;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        theContext = getActivity();
        mOrderDb = MyOrderDatabase.getInstance(theContext.getApplicationContext());

        View mView = inflater.inflate(R.layout.fragment_order, container, false);
        View layoView = inflater.inflate(R.layout.row_layout, container, false);
//        yourOrder = layoView.findViewById(R.id.title);

        yourOrder = mView.findViewById(R.id.THIS_title);

        listView = mView.findViewById(R.id.list_view);
        listView.setOnItemClickListener(new ItemClickListener());


        // Inflate the layout for this fragment
        return mView;

    }

    private class ItemClickListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, final long id) {
            TextView title = view.findViewById(R.id.title);

            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(theContext);
            final CharSequence name = title.getText();
            alertDialogBuilder.setMessage("Are you sure you want to delete " + name);
            alertDialogBuilder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // if this button is clicked, delete and close
                    String newName = name.toString();
                    mOrderDb.delete(newName);
                    // current activity
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        orderAdapter = new OrderAdapter(theContext.getApplicationContext(), mOrderDb.getData(), yourOrder);
        listView.setAdapter(orderAdapter);
    }

}


