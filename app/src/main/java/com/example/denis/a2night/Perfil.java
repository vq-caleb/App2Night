package com.example.denis.a2night;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Perfil extends Fragment {

    //TextView video;

    public Perfil() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        ImageView MiImageView = (ImageView) view.findViewById(R.id.editarPerfil);
        ImageView MiImageView2 = (ImageView) view.findViewById(R.id.seguidores);
        ImageView MiImageView3 = (ImageView) view.findViewById(R.id.editarMenu);
        ImageView MiImageView4 = (ImageView) view.findViewById(R.id.agregarPubli);
        ImageView MiImageView5 = (ImageView) view.findViewById(R.id.agregarProduc);
        ImageView MiImageView6 = (ImageView) view.findViewById(R.id.cerrarSesion);

        MiImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content, new EditarPerfil()).commit();
            }
        });

        MiImageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content, new Seguidores()).commit();
            }
        });

        MiImageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content, new EditarPerfil()).commit();
            }
        });

        MiImageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content, new AgregarPublicacion()).commit();
            }
        });

        MiImageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content, new AgregarProducto()).commit();
            }
        });

        MiImageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content, new Buscar01()).commit();

            }
        });

        return view;
    }



}
