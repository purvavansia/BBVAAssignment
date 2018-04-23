package com.example.purva.bbvaassignment.data.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.purva.bbvaassignment.R;
import com.example.purva.bbvaassignment.data.Location;
import com.example.purva.bbvaassignment.ui.info.InfoActivity;

import java.util.Collections;
import java.util.List;

/**
 * Created by purva on 4/6/18.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {
    List<Location> myDataList;
    Context context;

    public DataAdapter(List<Location> myDataList, Context context) {
        this.myDataList = myDataList;
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        //Collections.sort(myDataList, Location.locationDistanceComparator);
        final Location location = myDataList.get(position);

        holder.addr.setText("Address: "+location.getAddr());

        holder.addr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,InfoActivity.class);
                i.putExtra("name",location.getName());
                i.putExtra("id",location.getId());
                i.putExtra("addr",location.getAddr());
                i.putExtra("longitude",location.getLongitude());
                i.putExtra("latitude",location.getLatitude());
                i.putExtra("icon",location.getIcon());
                i.putExtra("rating",location.getRating());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return myDataList.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder{

        TextView addr;

        public MyViewHolder(View itemView) {
            super(itemView);

            addr = itemView.findViewById(R.id.textViewAddr);

        }
    }
}
