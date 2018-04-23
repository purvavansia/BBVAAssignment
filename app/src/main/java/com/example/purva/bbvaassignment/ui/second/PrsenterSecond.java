package com.example.purva.bbvaassignment.ui.second;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.purva.bbvaassignment.R;
import com.example.purva.bbvaassignment.ui.maps.MapsActivity;

/**
 * Created by purva on 4/22/18.
 */

public class PrsenterSecond implements IPresenterSecond {

    Context context;
    IViewSecond iViewSecond;
    public PrsenterSecond(Context context, IViewSecond iViewSecond) {
        this.context = context;
        this.iViewSecond = iViewSecond;
        iViewSecond.loadFragment();
    }

    @Override
    public void onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.listView:
                Intent intent = new Intent(context, SecondActivity.class);
                context.startActivity(intent);
                break;

            case R.id.mapView:
                Intent i = new Intent(context, MapsActivity.class);
                context.startActivity(i);
                break;

        }
    }
}
