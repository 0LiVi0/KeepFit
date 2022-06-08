package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RouteList extends Fragment {

    RecyclerView recyclerView;

    String[] route_name, route_distance, route_time;
    int[] image = {R.drawable.theatre, R.drawable.park, R.drawable.tv_tower,
            R.drawable.park, R.drawable.swan};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.route_list, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView);

        route_name = getResources().getStringArray(R.array.route_names);
        route_distance = getResources().getStringArray(R.array.route_distance);
        route_time = getResources().getStringArray(R.array.route_time);

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext().getApplicationContext(),
                route_name, route_distance, route_time, image);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext().getApplicationContext()));

        FloatingActionButton go_to_map = rootView.findViewById(R.id.map_icon);
        go_to_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new MainMenu();
                replaceFragment(fragment);
            }
        });


        return rootView;
    }

    private void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}