package com.vanderkruk.localtourguide.userinterface;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vanderkruk.localtourguide.R;
import com.vanderkruk.localtourguide.datamodel.Tour;

import java.util.List;

/**
 * Created by koen on 16-6-2017.
 */

public class TourOverviewAdapter extends RecyclerView.Adapter<TourOverviewAdapter.MyViewHolder> implements View.OnClickListener, View.OnLongClickListener {
    private List<Tour> tourList;
    private Handler theHandler;

    @Override
    public boolean onLongClick(View v) {
        TextView pressedView = (TextView) v;
        Log.v("REMOVINING: ", pressedView.getText().toString() + " WITH ID: " + v.getId());

        return true;
    }

    @Override
    public void onClick(View v) {
        TextView pressedView = (TextView) v;
        Log.v("YouClickedOn: ", pressedView.getText().toString() + " WITH ID: " + v.getId());
        Message msg = theHandler.obtainMessage();

        msg.obj = Integer.toString(v.getId());
        theHandler.sendMessage(msg);

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView city, name;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.tourName);
            city = (TextView) view.findViewById(R.id.tourCity);
        }
    }

    public TourOverviewAdapter(List<Tour> tours, Context co, Handler ha) {
        theHandler = ha;
        this.tourList = tours;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tour_overview_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Tour tour = tourList.get(position);
        holder.city.setText(tour.getCity());
        holder.name.setOnLongClickListener(this);
        holder.name.setOnClickListener(this);
        holder.name.setId(tour.getId());
        holder.name.setText(tour.getName());
    }

    public void updateAdapterWithNewData(List<Tour> tours) {
        this.tourList = tours;
    }

    @Override
    public int getItemCount() {
        return tourList.size();
    }
}
