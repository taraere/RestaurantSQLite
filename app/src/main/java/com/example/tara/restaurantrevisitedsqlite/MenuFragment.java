package com.example.tara.restaurantrevisitedsqlite;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class MenuFragment extends ListFragment {

    private List<String> foodPlatters = new ArrayList<>();
    private JSONObject c;
    String output;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Bundle arguments = this.getArguments();

        // add adapter to adapt categories into views
        Context context = getActivity().getApplicationContext();

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(
                context, android.R.layout.simple_list_item_1, foodPlatters);

        this.setListAdapter(adapter);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this.getContext());
        String url = "https://resto.mprog.nl/menu";

        // request string response from url
        JsonObjectRequest stringRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray courses = response.getJSONArray("items");

                            //foodCourses = new String[courses.length()];
                            for (int i = 0; i < courses.length(); i++) {

                                c = courses.getJSONObject(i);
                                String sCategory = c.getString("category");

                                Log.d("sCategories", sCategory);

                                String value = arguments.getString("category");
                                Log.d("category in bundle", value);

                                if (sCategory.equals(value)){

                                    String sPrice = c.getString("price");
                                    String sName = c.getString("name");
                                    output = sName + "    $" + sPrice + "0   ";
                                    foodPlatters.add(output);
                                    Log.d("foodPlatters", String.valueOf(foodPlatters));
                                }
                                adapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d(TAG, "onErrorResponse: courses stuff");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: No internet connection");
            }
        });

        // add request to RequestQueue
        queue.add(stringRequest);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String s = foodPlatters.get(position);

        // something with the adapters and putting things into sqlite

        MyOrderDatabase mOrderDb = MyOrderDatabase.getInstance(getContext());
        mOrderDb.addItem(s, 1);
        Log.d("on list click", s);
    }
}
