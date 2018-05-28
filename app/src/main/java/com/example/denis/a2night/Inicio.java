package com.example.denis.a2night;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.denis.a2night.Adapter.MyAdapter;
import com.example.denis.a2night.Interface.ILoadMore;
import com.example.denis.a2night.Model.Item;
import com.example.denis.a2night.entidades.AlmacenamientoGlobal;
import com.example.denis.a2night.entidades.Publicacion;
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

    AlmacenamientoGlobal aGlobal = AlmacenamientoGlobal.getInstance();
    List<Item> items = new ArrayList<>();
    List<String> publicaciones = new ArrayList<>();
    MyAdapter adapter;
    FirebaseDatabase db;
    String nombre, horas, nombreD, descripcion, imagen;
    String circleView;
    ImageView picture;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);
        //gestos = new GestureDetector(getActivity(),this);

        /*final GestureDetector gesture = new GestureDetector(getActivity(),
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onDoubleTapEvent(MotionEvent motionEvent) {

                        try {

                            }
                        } catch (Exception e) {
                            // nothing
                        }
                        return super.onDoubleTapEvent(e);
                    });*/



        cargarPublicaciones();
        random10Data();

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MyAdapter(recyclerView, getActivity(), items);
        recyclerView.setAdapter(adapter);


        /*adapter.setLoadMore(new ILoadMore(){
            @Override
            public void onLoadMore() {
                if(items.size() <= 3){
                    items.add(null);
                    adapter.notifyItemInserted(items.size()-1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            items.remove(items.size()-1);
                            adapter.notifyItemRemoved(items.size());

                            for(int i = 0; i < aGlobal.getPublicaciones().size(); i++) {
                                Item item = new Item(aGlobal.getPublicaciones().get(i).getNombre().toString(), aGlobal.getPublicaciones().get(i).getCircle().toString(), aGlobal.getPublicaciones().get(i).getPublicacion().toString());
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
        });*/

        return view;
    }



    public void cargarPublicaciones(){
        db = FirebaseDatabase.getInstance();
        db.getReference().child("Publicaciones").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    nombre = (child.child("nombre").getValue().toString());
                    imagen = (child.child("imagen").getValue().toString());
                    horas = (child.child("horas").getValue().toString());
                    descripcion = (child.child("descripcion").getValue().toString());
                    aGlobal.agregaPublicacion(new Publicacion(nombre, nombre, imagen, horas, nombre, descripcion));
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("---OBJECT-----", "-----ERROR2-----");
            }
        });
    }

    public void Mensaje(String msg){ Toast.makeText(getActivity(), msg,Toast.LENGTH_SHORT).show();};

    private void random10Data() {
        for(int i = 0; i < aGlobal.getPublicaciones().size(); i++) {
            Item item = new Item(aGlobal.getPublicaciones().get(i).getNombre().toString(),
                    aGlobal.getPublicaciones().get(i).getCircle().toString(),
                    aGlobal.getPublicaciones().get(i).getImagen().toString(),
                    aGlobal.getPublicaciones().get(i).getHoras().toString(),
                    aGlobal.getPublicaciones().get(i).getNombreD().toString(),
                    aGlobal.getPublicaciones().get(i).getDescripcion().toString());
            items.add(item);
        }
    }



}
