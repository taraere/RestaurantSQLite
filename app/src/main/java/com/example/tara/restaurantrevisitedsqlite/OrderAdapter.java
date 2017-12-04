package com.example.tara.restaurantrevisitedsqlite;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

/**
 * Created by Tara on 04/12/2017.
 */

public class OrderAdapter extends ResourceCursorAdapter {
    TextView yourOrder;
    public OrderAdapter(Context context, Cursor cursor, TextView textView){
        super(context, R.layout.row_layout, cursor);
        Log.d("taart", "cake1");
        yourOrder = textView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        Log.d("taart", "cake");
        String title = cursor.getString(cursor.getColumnIndex(MyOrderDatabase.COL_1));
        Log.d("title", title);
//        int amount = cursor.getInt(cursor.getColumnIndex("amount"));

        TextView row = view.findViewById(R.id.amount);
        row.setText(title);
        yourOrder.setText(title);
    }
}
