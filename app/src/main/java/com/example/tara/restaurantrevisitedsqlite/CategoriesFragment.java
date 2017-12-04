package com.example.tara.restaurantrevisitedsqlite;

import android.content.Context;
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

    private String TAG = "Error";
    public ArrayList<String> foodCategories = new ArrayList<>();;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toast.makeText(getContext(), "inslide", Toast.LENGTH_SHORT).show();

        // Instantiate the RequestQueue.
        com.android.volley.RequestQueue queue = Volley.newRequestQueue(this.getContext());

        // add adapter to adapt categories into views
        String url = "https://resto.mprog.nl/categories";

        // request string response from url
        JsonObjectRequest jObjRequest = new JsonObjectRequest(
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
                                Log.d("foodCategories", String.valueOf(foodCategories));
                            }
                            showFragment(foodCategories);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("json", e.toString());
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("No internet connection");
                Toast.makeText(getContext(), "No Internet connection", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onErrorResponse: No internet connection", error);
            }
        });

        // add request to RequestQueue
        queue.add(jObjRequest);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_categories, container, false);

        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        MenuFragment menuFragment = new MenuFragment();
        String s = foodCategories.get(position);

        Bundle args = new Bundle();
        args.putString("category", s);
        menuFragment.setArguments(args);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, menuFragment)
                .addToBackStack(null)
                .commit();
    }

    public void showFragment(ArrayList<String> al) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_list_item_1, al);
        this.setListAdapter(adapter);
    }

    public ArrayList<String> getFoodCategories() {
        return foodCategories;
    }

}
