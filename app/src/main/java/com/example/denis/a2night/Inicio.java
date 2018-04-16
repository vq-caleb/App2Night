package com.example.denis.a2night;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.denis.a2night.Adapter.MyAdapter;
import com.example.denis.a2night.Interface.ILoadMore;
import com.example.denis.a2night.Model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class Inicio extends Fragment {

    List<Item> items = new ArrayList<>();
    MyAdapter adapter;

    public Inicio() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);
        random10Data();

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MyAdapter(recyclerView, getActivity(), items);
        recyclerView.setAdapter(adapter);

        adapter.setLoadMore(new ILoadMore(){
            @Override
            public void onLoadMore() {
                if(items.size() <= 40){
                    items.add(null);
                    adapter.notifyItemInserted(items.size()-1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            items.remove(items.size()-1);
                            adapter.notifyItemRemoved(items.size());

                            int index = items.size();
                            int end = index+10;
                            for(int i = index; i < end; i++){
                                String name = UUID.randomUUID().toString();
                                Item item = new Item(name, name.length());
                                items.add(item);
                            }
                            adapter.notifyDataSetChanged();
                            adapter.setLoaded();
                        }
                    }, 3000);
                } else {
                    //Toast.makeText(MainActivity.this, "Load data complete!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    return view;
    }

    private void random10Data() {
        for(int i = 0; i < 10; i++){
            String name = UUID.randomUUID().toString();
            Item item = new Item(name, name.length());
            items.add(item);
        }
    }

}
