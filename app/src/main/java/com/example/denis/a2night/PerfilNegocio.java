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
    ImageView fotoPerfilEmpresa;
    Empresa empresa;
    FirebaseStorage storage;
    StorageReference storageRef;
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
            public void onClick(View v){
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content, new Buscar01()).commit();
            }
        });
        alambrarVariables(view);

        OnclickDelTextView(R.id.info, view);
        OnclickDelTextView(R.id.publi, view);
        OnclickDelTextView(R.id.productos, view);

        imagenPerfil = (ImageView) view.findViewById(R.id.perfil);
        imagenPortada = (ImageView) view.findViewById(R.id.portada);
       /* storage = FirebaseStorage.getInstance();
        //creates a storage reference
        storageRef = storage.getReference();*/

        String imgPortada = "portadas/"+aGlobal.getIdEmpresaActual()+".png";
        FirebaseStorage.getInstance().getReference().child(imgPortada).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getActivity()).load(uri).into(imagenPortada);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
               // Mensaje("hostiaTio");
            }
        });


        String imgPerfil = "perfil/"+aGlobal.getIdEmpresaActual()+".png";
        FirebaseStorage.getInstance().getReference().child(imgPerfil).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getActivity()).load(uri).into(imagenPerfil);
                // Mensaje(imagenPerfil.getDrawable().toString());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
               // Mensaje("hostiaTio");
            }
        });


        TextView publi = (TextView) view.findViewById(R.id.publi);
        TextView info = (TextView) view.findViewById(R.id.info);
        TextView productos = (TextView) view.findViewById(R.id.productos);
        publi.setTextColor(getResources().getColor(R.color.colorPrimary));
        info.setTextColor(getResources().getColor(R.color.colorGray));
        productos.setTextColor(getResources().getColor(R.color.colorGray));

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.contenido, new TabPost()).commit();
        this.aGlobal.setEmpresa(new Empresa());
        this.cargaEmpresa();
        return view;
    }

    public void cargaEmpresa(){

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        db.getReference().child("Bares")
                .orderByKey()
                .equalTo(aGlobal.getIdEmpresaActual())
                .addValueEventListener(new ValueEventListener() {
                                           @Override
                                           public void onDataChange(DataSnapshot dataSnapshot)
                                           {
                                               for (DataSnapshot child : dataSnapshot.getChildren())
                                               {
                                                   String nombre = (String) child.child("nombre").getValue();
                                                   String etiquetaUbicacion = (String) child.child("etiquetaUbicacion").getValue();
                                                   String telefono1 = (String) child.child("telefono1").getValue();
                                                   String telefono2 = (String) child.child("telefono2" ).getValue();
                                                   String correo = (String) child.child("correo").getValue();
                                                   String descripcion = (String) child.child("descripcion").getValue();
                                                   String codigoVestimenta = (String) child.child("codigoVestimenta").getValue();
                                                   String entrada = (String) child.child("entrada").getValue();
                                                   String tipoNegocio = (String) child.child("tipoNegocio").getValue();
                                                   Log.d("---empresaaaa-----",tipoNegocio);
                                                   String paginaFacebook = (String) child.child("paginaFacebook").getValue();
                                                   String paginaInstagram = (String) child.child("paginaInstagram").getValue();
                                                   String paginaTwitter = (String) child.child("paginaTwitter").getValue();
                                                   int cantidadAsistentes =  Integer.parseInt((String) child.child("cantidadAsistentes").getValue());
                                                   List<Horario> horarioSemanal = null;
                                                   empresa = new Empresa(aGlobal.getIdEmpresaActual(), nombre, etiquetaUbicacion, cantidadAsistentes,  tipoNegocio,
                                                           paginaFacebook,  paginaInstagram, paginaTwitter,  telefono1,
                                                           telefono2,  correo, descripcion,  codigoVestimenta,  entrada, horarioSemanal);


                                                   // HERE WHAT CORRESPONDS TO JOIN
                                                   FirebaseDatabase db = FirebaseDatabase.getInstance();
                                                   db.getReference()
                                                           .child("HorarioBar")
                                                           .orderByKey()
                                                           .equalTo(""+aGlobal.getIdEmpresaActual())
                                                           .addValueEventListener(
                                                                   new ValueEventListener()
                                                                   {
                                                                       @Override
                                                                       public void onDataChange(DataSnapshot dataSnapshot)
                                                                       {
                                                                           List<Horario> horarioSemanal = new ArrayList<Horario>();

                                                                           for (DataSnapshot child : dataSnapshot.getChildren()){
                                                                               for(int i=0; i<7;i++){
                                                                                   String dia = (String) child.child(""+i).child("dia").getValue();
                                                                                   String horarioApertura = (String) child.child(""+i).child("horarioApertura").getValue();
                                                                                   String horarioCierre = (String) child.child(""+i).child("horarioCierre").getValue();
                                                                                   boolean abierto =  Boolean.parseBoolean((String)child.child(""+i).child("abierto").getValue());
                                                                                   Horario h = new Horario(dia,horarioApertura,horarioCierre,abierto);
                                                                                   horarioSemanal.add(h);
                                                                               }
                                                                               empresa.setHorarioSemanal(horarioSemanal);
                                                                               aGlobal.setEmpresa(empresa);
                                                                               completarInformacion();
                                                                           }
                                                                       }

                                                                       @Override
                                                                       public void onCancelled(DatabaseError databaseError)
                                                                       {
                                                                           Log.d("---OBJECT-----","-----ERROR---");
                                                                       }
                                                                   }
                                                           );
                                               }
                                           }
                                           @Override
                                           public void onCancelled(DatabaseError databaseError)
                                           {
                                               Log.d("---OBJECT-----","-----ERROR2-----");
                                           }
                                       }
                );
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
                TextView public01 = (TextView) getActivity().findViewById(R.id.publi);
                TextView product01 = (TextView) getActivity().findViewById(R.id.productos);
                switch (v.getId()) {

                    case R.id.info:
                        info01.setTextColor(getResources().getColor(R.color.colorPrimary));
                        product01.setTextColor(getResources().getColor(R.color.colorGray));
                        public01.setTextColor(getResources().getColor(R.color.colorGray));
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.contenido, new TabInformacion()).commit();
                        break;

                    case R.id.publi:
                        info01.setTextColor(getResources().getColor(R.color.colorGray));
                        product01.setTextColor(getResources().getColor(R.color.colorGray));
                        public01.setTextColor(getResources().getColor(R.color.colorPrimary));
                        FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
                        transaction2.replace(R.id.contenido, new TabPost()).commit();
                        break;

                    case R.id.productos:
                        info01.setTextColor(getResources().getColor(R.color.colorGray));
                        public01.setTextColor(getResources().getColor(R.color.colorGray));
                        product01.setTextColor(getResources().getColor(R.color.colorPrimary));
                        FragmentTransaction transaction3 = getFragmentManager().beginTransaction();
                        transaction3.replace(R.id.contenido, new TabMenu()).commit();
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