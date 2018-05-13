package com.example.denis.a2night;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.denis.a2night.entidades.AlmacenamientoGlobal;
import com.example.denis.a2night.entidades.Empresa;
import com.example.denis.a2night.entidades.Horario;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */

public class PerfilNegocio extends Fragment {
    AlmacenamientoGlobal aGlobal = AlmacenamientoGlobal.getInstance();
    TextView idEmpresa, nombreEmpresa, etiquetaUbicacion, cantidadSeguidores, cantidadAsistentes;
    ImageView imagenPerfil,imagenPortada;

    public PerfilNegocio() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil_negocio, container, false);

        ImageView MiImageView = (ImageView) view.findViewById(R.id.imagenAtrasBuscar);
        MiImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content, new Buscar01()).commit();
            }
        });

        alambrarVariables(view);
        completarInformacion();


        OnclickDelTextView(R.id.info, view);
        OnclickDelTextView(R.id.publicacion, view);
        OnclickDelTextView(R.id.promo, view);
        OnclickDelTextView(R.id.evento, view);

        imagenPerfil = (ImageView) view.findViewById(R.id.perfil);
        imagenPortada = (ImageView) view.findViewById(R.id.portada);

        String imgPortada = "portadas/" + aGlobal.getIdEmpresaActual() + ".png";
        FirebaseStorage.getInstance().getReference().child(imgPortada).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getActivity()).load(uri).into(imagenPortada);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
            }
        });

        String imgPerfil = "perfil/" + aGlobal.getIdEmpresaActual() + ".png";
        FirebaseStorage.getInstance().getReference().child(imgPerfil).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getActivity()).load(uri).into(imagenPerfil);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

            }
        });

        TextView info01 = (TextView) view.findViewById(R.id.publicacion);
        info01.setTextColor(getResources().getColor(R.color.colorPrimary));

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.contenido, new TabPost()).commit();
        return view;
    }

    public void completarInformacion(){
        this.idEmpresa.setText(this.aGlobal.getIdEmpresaActual());
        this.nombreEmpresa.setText(this.aGlobal.getEmpresa().getNombre());
        this.etiquetaUbicacion.setText(this.aGlobal.getEmpresa().getEtiquetaUbicacion());
        this.cantidadSeguidores.setText(this.aGlobal.getEmpresa().getCantidadSeguidoresFormartoK());
        this.cantidadAsistentes.setText(Integer.toString(this.aGlobal.getEmpresa().getCantidadAsistentes()));
    }

    public void alambrarVariables(View view){
        this.idEmpresa =  (TextView) view.findViewById(R.id.idEmpresa);
        this.nombreEmpresa =  (TextView) view.findViewById(R.id.nombre);
        this.cantidadSeguidores =  (TextView) view.findViewById(R.id.cantidadSeguidores);
        this.cantidadAsistentes =  (TextView) view.findViewById(R.id.cantidadAsistentes);
        this.etiquetaUbicacion = (TextView) view.findViewById(R.id.etiquetaUbicacion);
    }

    public void OnclickDelTextView(int ref, View view) {

        View view2 = view.findViewById(ref);
        TextView miTextView = (TextView) view2;

        miTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView info01 = (TextView) getActivity().findViewById(R.id.info);
                TextView promo01 = (TextView) getActivity().findViewById(R.id.promo);
                TextView public01 = (TextView) getActivity().findViewById(R.id.publicacion);
                TextView evento01 = (TextView) getActivity().findViewById(R.id.evento);
                switch (v.getId()) {
                    case R.id.info:
                        info01.setTextColor(getResources().getColor(R.color.colorPrimary));
                        promo01.setTextColor(getResources().getColor(R.color.colorAccent));
                        public01.setTextColor(getResources().getColor(R.color.colorAccent));
                        evento01.setTextColor(getResources().getColor(R.color.colorAccent));
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.contenido, new TabInformacion()).commit();
                        break;

                    case R.id.publicacion:
                        info01.setTextColor(getResources().getColor(R.color.colorAccent));
                        promo01.setTextColor(getResources().getColor(R.color.colorAccent));
                        public01.setTextColor(getResources().getColor(R.color.colorPrimary));
                        evento01.setTextColor(getResources().getColor(R.color.colorAccent));
                        FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
                        transaction2.replace(R.id.contenido, new TabPost()).commit();
                        break;

                    case R.id.evento:
                        info01.setTextColor(getResources().getColor(R.color.colorAccent));
                        promo01.setTextColor(getResources().getColor(R.color.colorAccent));
                        public01.setTextColor(getResources().getColor(R.color.colorAccent));
                        evento01.setTextColor(getResources().getColor(R.color.colorPrimary));
                        /*FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
                        transaction2.replace(R.id.contenido, new TabPost()).commit();*/
                        break;

                    case R.id.promo:
                        info01.setTextColor(getResources().getColor(R.color.colorAccent));
                        promo01.setTextColor(getResources().getColor(R.color.colorPrimary));
                        public01.setTextColor(getResources().getColor(R.color.colorAccent));
                        evento01.setTextColor(getResources().getColor(R.color.colorAccent));
                        /*FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
                        transaction2.replace(R.id.contenido, new TabPost()).commit();*/
                        break;
                    default:
                        break;
                }// fin de casos
            }// fin del onclick
        });
    }// fin de OnclickDelTextView

    public void Mensaje(String msg){
        Toast.makeText(getActivity(), msg,Toast.LENGTH_SHORT).show();};
}