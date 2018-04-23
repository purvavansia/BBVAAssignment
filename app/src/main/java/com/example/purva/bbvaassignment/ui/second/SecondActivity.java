package com.example.purva.bbvaassignment.ui.second;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.purva.bbvaassignment.ui.list.ListFragment;
import com.example.purva.bbvaassignment.R;
import com.example.purva.bbvaassignment.ui.maps.MapsActivity;

public class SecondActivity extends AppCompatActivity implements IViewSecond {

    IPresenterSecond iPresenterSecond;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        iPresenterSecond = new PrsenterSecond(this, (IViewSecond) this);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        iPresenterSecond.onOptionsItemSelected(item);
        return true;
    }

    @Override
    public void loadFragment() {
        ListFragment listFragment = new ListFragment();
        getFragmentManager().beginTransaction().add(R.id.linearLayout,listFragment,"adding fragment").commit();
    }
}
