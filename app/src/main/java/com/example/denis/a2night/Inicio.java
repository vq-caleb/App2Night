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
import com.example.denis.a2night.entidades.AlmacenamientoGlobal;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class Inicio extends Fragment {

    List<Item> items = new ArrayList<>();
    MyAdapter adapter;
    FirebaseDatabase db;
    boolean flag = true;
    AlmacenamientoGlobal aGlobal = AlmacenamientoGlobal.getInstance();
    public Inicio() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);
        //random10Data();
        /*db = FirebaseDatabase.getInstance();
        db.getReference("Publicaciones").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                aGlobal.setTotalPublicaciones(Integer.parseInt(""+dataSnapshot.getChildrenCount()));
                Mensaje(""+aGlobal.getTotalPublicaciones());
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    random10Data(child.child("imagen").getValue());
                    if (items.size() >= aGlobal.getTotalPublicaciones())
                        flag = false;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/



        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MyAdapter(recyclerView, getActivity(), items);
        recyclerView.setAdapter(adapter);


       /* db.getReference("Publicaciones").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                aGlobal.setTotalPublicaciones(Integer.parseInt(""+dataSnapshot.getChildrenCount()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/




        adapter.setLoadMore(new ILoadMore(){
            @Override
            public void onLoadMore() {
                if(flag){
                    items.add(null);
                    adapter.notifyItemInserted(items.size()-1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            items.remove(items.size()-1);
                            adapter.notifyItemRemoved(items.size());

                            for(int i = 0; i < 5; i++){
                                if(aGlobal.getUltimaPublicacion()<aGlobal.getTotalPublicaciones()) {
                                    String name = UUID.randomUUID().toString();
                                    Item item = new Item(name, aGlobal.getUltimaPublicacion());
                                    aGlobal.setUltimaPublicacion(aGlobal.getUltimaPublicacion()+1);
                                    items.add(item);
                                    Mensaje(aGlobal.getTotalPublicaciones()+"");
                                }
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

    private void random10Data(Object value) {
        //Mensaje(value.toString());
       /* Mensaje("invocando");
        for(int i = 0; i < 5; i++){
            String name = UUID.randomUUID().toString();
            Item item = new Item(name, name.length());
            items.add(item);
        }*/
       // items.add(item);
    }

    public void Mensaje(String msg){
        Toast.makeText(getActivity(), msg,Toast.LENGTH_SHORT).show();};

}
