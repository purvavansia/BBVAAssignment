package com.example.purva.bbvaassignment.ui.list;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.purva.bbvaassignment.R;
import com.example.purva.bbvaassignment.data.Location;
import com.example.purva.bbvaassignment.data.adapters.DataAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by purva on 4/21/18.
 */

public class ListFragment extends Fragment {


    Location locationObj;
    ArrayList<Location> locationArrayList;
    RecyclerView recyclerView;
    DataAdapter dataAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_list,container,false);

        recyclerView = v.findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        locationArrayList = new ArrayList<>();
        dataAdapter = new DataAdapter(locationArrayList,getActivity());

        recyclerView.setAdapter(dataAdapter);
        getData();
        return v;
    }

    private void getData() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://maps.googleapis.com/maps/api/place/textsearch/json?query=BBVA+Compass&location="+41.9142+","+88.3087+"&radius=10000&key=AIzaSyADr_qv3QlNb7MnhYTzJsEn4yfQdNprsc4", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("response", response);


                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray results = jsonObject.getJSONArray("results");
                    for(int i = 0; i<results.length(); i++){

                        JSONObject myresults = results.getJSONObject(i);
                        String formattedAddr = myresults.getString("formatted_address");
                        JSONObject geometry = myresults.getJSONObject("geometry");
                        JSONObject location = geometry.getJSONObject("location");
                        String latitude = location.getString("lat");
                        String longitude = location.getString("lng");
                        //id,name,icon,rating
                        String icon = myresults.getString("icon");
                        String id = myresults.getString("id");
                        String name = myresults.getString("name");
                        String rating = myresults.getString("rating");
                        locationObj = new Location(formattedAddr,longitude,latitude,id,name,icon,rating);
                        locationArrayList.add(locationObj);
                        dataAdapter.notifyDataSetChanged();


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("response", ""+error);
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
}
