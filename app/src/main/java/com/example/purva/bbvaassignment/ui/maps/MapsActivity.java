package com.example.purva.bbvaassignment.ui.maps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.purva.bbvaassignment.R;
import com.example.purva.bbvaassignment.ui.second.SecondActivity;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback{
    private GoogleMap mMap;

    double lat = 41.9142, longitude = 88.3087;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (!isGooglePlayServicesAvailable()) {
            return;
        }*/
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        /*Bundle receive = getIntent().getExtras().getBundle("receive");
        lat = receive.getDouble("lat");
        longitude = receive.getDouble("long");
        Toast.makeText(this,""+lat+" "+longitude,Toast.LENGTH_SHORT).show();*/
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.listView:
                Intent intent = new Intent(MapsActivity.this, SecondActivity.class);
                startActivity(intent);
                break;

            case R.id.mapView:
                Intent i = new Intent(MapsActivity.this, MapsActivity.class);
                startActivity(i);
                break;

        }
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

            Log.d("latitude", lat + "  ----" + longitude);

            // Add a marker in Sydney and move the camera
            LatLng currentLoc = new LatLng(41.9142, 88.3087);
            mMap.addMarker(new MarkerOptions().position(currentLoc).title("Marker in United States"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLoc));

            LatLng coordinate = new LatLng(41.9142, 88.3087);
            CameraUpdate yourLocation =    CameraUpdateFactory.newLatLngZoom(coordinate, 5);
            mMap.animateCamera(yourLocation);

            CameraPosition cameraPosition = new CameraPosition.Builder()

                    .target(new LatLng(lat, longitude))      // Sets the center of the map to location user
                    .zoom(5)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        loadNearByPlaces();
        }




    private void loadNearByPlaces() {
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

                        MarkerOptions markerOptions = new MarkerOptions();
                        LatLng latLng = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));

                        mMap.addMarker(markerOptions.position(latLng).title(name));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                        mMap.animateCamera(CameraUpdateFactory.zoomTo(5));
                        markerOptions.position(latLng);
                        mMap.addMarker(markerOptions);

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

        RequestQueue requestQueue = Volley.newRequestQueue(MapsActivity.this);
        requestQueue.add(stringRequest);
    }

}
