package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    String[] names_data, distance_data, time_data;
    int[] icons;
    Context context;

    public RecyclerViewAdapter(Context ct, String[] route_name, String[] route_distance, String[] route_time, int[] ic){
        context = ct;
        names_data = route_name;
        distance_data = route_distance;
        time_data = route_time;
        icons = ic;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.route_name.setText(names_data[position]);
        holder.route_distance.setText(distance_data[position]);
        holder.route_time.setText(time_data[position]);
        holder.route_icon.setImageResource(icons[position]);
    }

    @Override
    public int getItemCount() {
        return icons.length;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView route_name, route_distance, route_time;
        ImageView route_icon;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            route_name = itemView.findViewById(R.id.route_name);
            route_distance = itemView.findViewById(R.id.route_distance);
            route_time = itemView.findViewById(R.id.route_time);
            route_icon = itemView.findViewById(R.id.route_icon);
        }
    }
}
