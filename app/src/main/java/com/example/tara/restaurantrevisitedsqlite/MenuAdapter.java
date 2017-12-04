package com.example.tara.restaurantrevisitedsqlite;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import java.util.ResourceBundle;

/**
 * Created by Tara on 04/12/2017.
 */

public class MenuAdapter extends ResourceCursorAdapter {
    public MenuAdapter(Context context, Cursor cursor) {
        super(context, R.layout.row_layout, cursor);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        Log.d("inside bindView of ", "menu adapter");

        // set title
        String title = cursor.getString(cursor.getColumnIndex("title"));
        TextView titleView = view.findViewById(R.id.title);
        titleView.setText(title);

        // set price
        String price = cursor.getString(cursor.getColumnIndex("price"));
        TextView priceView = view.findViewById(R.id.price);
        priceView.setText(price);
    }
}
