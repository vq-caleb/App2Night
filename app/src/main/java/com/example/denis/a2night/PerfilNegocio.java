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
import android.widget.Button;

import com.example.denis.a2night.entidades.AlmacenamientoGlobal;
import com.example.denis.a2night.entidades.Empresa;
import com.example.denis.a2night.entidades.Horario;
import com.example.denis.a2night.entidades.Producto;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    ImageView imagenPerfil, imagenPortada;
    Button btnSeguir;
    byte[] imagen2;
    String nombre, precio, imagen;
    int index = 0, lastIndex;
    int productosCargados = 0;
    boolean seguidor = false;
    FirebaseDatabase db;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


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


        OnclickDelButton(R.id.btnSeguir, view);
        OnclickDelTextView(R.id.info, view);
        OnclickDelTextView(R.id.publi, view);
        OnclickDelTextView(R.id.productos, view);
        OnclickDelButton(R.id.btnSeguir, view);

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

        cargaProductos();

        verificaSeguidor();

        TextView publi = (TextView) view.findViewById(R.id.publi);
        TextView info = (TextView) view.findViewById(R.id.info);
        TextView productos = (TextView) view.findViewById(R.id.productos);
        publi.setTextColor(getResources().getColor(R.color.colorPrimary));
        info.setTextColor(getResources().getColor(R.color.colorGray));
        productos.setTextColor(getResources().getColor(R.color.colorGray));

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.contenido, new TabPost()).commit();
        return view;
    }

    public void completarInformacion() {
        this.idEmpresa.setText(this.aGlobal.getIdEmpresaActual());
        this.nombreEmpresa.setText(this.aGlobal.getEmpresa().getNombre());
        this.etiquetaUbicacion.setText(this.aGlobal.getEmpresa().getEtiquetaUbicacion());
        this.cantidadSeguidores.setText(this.aGlobal.getEmpresa().getCantidadSeguidoresFormartoK());
        this.cantidadAsistentes.setText(Integer.toString(this.aGlobal.getEmpresa().getCantidadAsistentes()));
    }

    public void alambrarVariables(View view) {
        this.idEmpresa = (TextView) view.findViewById(R.id.idEmpresa);
        this.nombreEmpresa = (TextView) view.findViewById(R.id.nombre);
        this.cantidadSeguidores = (TextView) view.findViewById(R.id.cantidadSeguidores);
        this.cantidadAsistentes = (TextView) view.findViewById(R.id.cantidadAsistentes);
        this.etiquetaUbicacion = (TextView) view.findViewById(R.id.etiquetaUbicacion);
        this.btnSeguir = (Button) view.findViewById(R.id.btnSeguir);
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

    public void OnclickDelButton(int ref, View view) {
        Button btnSeguir = view.findViewById(ref);

        btnSeguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {

                    case R.id.btnSeguir:
                        Mensaje("ab");
                        cambiaSeguimiento();
                        break;

                    default:
                        break;
                }
            }
        });
    }// fin de OnclickDelButton

    public void Mensaje(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    ;

    public void cargaProductos() {
        aGlobal.setProductosEmpresaActual(new ArrayList<Producto>());
        db = FirebaseDatabase.getInstance();
        db.getReference().child("Menu").child(aGlobal.getIdEmpresaActual())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            nombre = (child.child("nombre").getValue().toString());
                            precio = (child.child("precio").getValue().toString());
                            imagen = "menus/" + aGlobal.getIdEmpresaActual() + "/" + nombre;
                            aGlobal.agregaProducto(new Producto(nombre, precio, imagen));
                            productosCargados++;

                            if (productosCargados == dataSnapshot.getChildrenCount()) {
                                lastIndex = aGlobal.getProductosEmpresaActual().size();
                                cargarImagenes();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d("---OBJECT-----", "-----ERROR2-----");
                    }
                });
        aGlobal.setProductosEmpresaActual(new ArrayList<Producto>());

    }

//
    public void cargarImagenes(){
        final long ONE_MEGABYTE = 1024 * 1024;
        FirebaseStorage.getInstance().getReference().
                child(aGlobal.getProductosEmpresaActual().get(this.index).getImagen()).getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                index++;
                aGlobal.getProductosEmpresaActual().get(index-1).setImagen2(bytes);
                if(index < aGlobal.getProductosEmpresaActual().size())
                    cargarImagenes();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

            }
        });
    }


    public void verificaSeguidor() {
        db = FirebaseDatabase.getInstance();
        db.getReference().child("Seguidores").child(aGlobal.getIdEmpresaActual())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        setSeguidores(Integer.parseInt("" + dataSnapshot.getChildrenCount()));
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            if (child.getKey().equals(user.getUid())) {
                                esSeguidor();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d("---OBJECT-----", "-----ERROR2-----");
                    }
                });
    }

    public void esSeguidor() {
        this.seguidor = true;
        this.btnSeguir.setText("Dejar de seguir");
    }

    public void setSeguidores(int seg) {
        this.cantidadSeguidores.setText("" + seg);
    }


    public void cambiaSeguimiento() {
        if(!this.seguidor){
            seguir();
        }else{
            dejarDeSeguir();
        }


    }

    public void seguir(){
        Map<String, String> usuario = new HashMap<>();
        usuario.put ("FechaSeguimiento","DATE");
        usuario.put("Asistencias", "0");
        db = FirebaseDatabase.getInstance();
        db.getReference().child("Seguidores").child(aGlobal.getIdEmpresaActual()).child(user.getUid()).setValue(usuario).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(@NonNull Void T) {
                esSeguidor();
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Mensaje("Error");
            }
        });
    }

    public void dejarDeSeguir(){
        // Dejar de seguir
        db = FirebaseDatabase.getInstance();
        db.getReference().child("Seguidores").child(aGlobal.getIdEmpresaActual()).child(user.getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        //dataSnapshot.getRef().removeValue();
                        dataSnapshot.getRef().setValue(null);
                        seguidor = false;
                        btnSeguir.setText("Seguir");
                        setSeguidores(Integer.parseInt("" + dataSnapshot.getChildrenCount()));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d("---OBJECT-----", "-----ERROR2-----");
                    }
                });
    }
}


