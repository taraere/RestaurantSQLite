package com.example.tara.restaurantrevisitedsqlite;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriesFragment extends ListFragment {

    private List<String> foodCategories = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String url = "https://resto.mprog.nl/categories";

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this.getContext());

        // request string response from url
        JsonObjectRequest stringRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray courses = response.getJSONArray("categories");

                            for (int i = 0; i < courses.length(); i++) {
                                String sCourse = courses.getString(i);
                                // add to array of courses
                                foodCategories.add(sCourse);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        // add request to RequestQueue
        queue.add(stringRequest);

        // add adapter to adapt categories into views
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this.getContext(),
                android.R.layout.simple_list_item_1,
                foodCategories);
        this.setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        return view;
    }

    public List<String> getFoodCategories() {
        return foodCategories;
    }

}
