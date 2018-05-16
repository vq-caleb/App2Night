package com.example.denis.a2night;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.denis.a2night.entidades.AlmacenamientoGlobal;
import com.example.denis.a2night.entidades.Empresa;
import com.example.denis.a2night.entidades.Horario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Buscar01 extends Fragment {

    AlmacenamientoGlobal aGlobal = AlmacenamientoGlobal.getInstance();
    public Buscar01() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buscar01, container, false);


        ImageView MiImageView = (ImageView) view.findViewById(R.id.entrenouscr);
        ImageView MiImageView2 = (ImageView) view.findViewById(R.id.saulbistrocr);
        ImageView MiImageView3 = (ImageView) view.findViewById(R.id.aguizotescr);
        ImageView MiImageView4 = (ImageView) view.findViewById(R.id.mercaditocr);
        ImageView MiImageView5 = (ImageView) view.findViewById(R.id.laconchacr);
        ImageView MiImageView6 = (ImageView) view.findViewById(R.id.antikcr);
        ImageView MiImageView7 = (ImageView) view.findViewById(R.id.lacalicr);
        ImageView MiImageView8 = (ImageView) view.findViewById(R.id.cuartelcr);
        ImageView MiImageView9 = (ImageView) view.findViewById(R.id.einsteincr);
        ImageView MiImageView10 = (ImageView) view.findViewById(R.id.frathousecr);
        ImageView MiImageView11 = (ImageView) view.findViewById(R.id.caccioscr);
        ImageView MiImageView12 = (ImageView) view.findViewById(R.id.xcapecr);
        ImageView MiImageView13 = (ImageView) view.findViewById(R.id.irBusqueda);
        TextView textView = (TextView) view.findViewById(R.id.irBusqueda2);
        Button button = (Button) view.findViewById(R.id.sanjose);

        MiImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aGlobal.setIdEmpresaActual("entrenouscr");
                setEmpresaActual();
            }
        });

        MiImageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aGlobal.setIdEmpresaActual("saulbistrocr");
                setEmpresaActual();
            }
        });

        MiImageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aGlobal.setIdEmpresaActual("aguizotescr");
                setEmpresaActual();
            }
        });

        MiImageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aGlobal.setIdEmpresaActual("mercaditocr");
                setEmpresaActual();
            }
        });

        MiImageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aGlobal.setIdEmpresaActual("laconchacr");
                setEmpresaActual();
            }
        });

        MiImageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aGlobal.setIdEmpresaActual("antikcr");
                setEmpresaActual();

            }
        });

        MiImageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aGlobal.setIdEmpresaActual("lacalicr");
                setEmpresaActual();
            }
        });

        MiImageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aGlobal.setIdEmpresaActual("cuartelcr");
                setEmpresaActual();
            }
        });

        MiImageView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aGlobal.setIdEmpresaActual("einsteincr");
                setEmpresaActual();
            }
        });

        MiImageView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aGlobal.setIdEmpresaActual("frathousecr");
                setEmpresaActual();
            }
        });

        MiImageView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aGlobal.setIdEmpresaActual("caccioscr");
                setEmpresaActual();
            }
        });

        MiImageView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aGlobal.setIdEmpresaActual("xcapecr");
                setEmpresaActual();
            }
        });

        MiImageView13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content, new Buscar02()).commit();
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content, new Buscar02()).commit();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content, new BuscarCategoria()).commit();
            }
        });
        return view;

    }

    public void Mensaje(String msg){
        Toast.makeText(getActivity(), msg,Toast.LENGTH_SHORT).show();};

    public void pasaPerfilEmpresa(){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content, new PerfilNegocio()).commit();
    }


    public void setEmpresaActual(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        db.getReference().child("Bares").child(aGlobal.getIdEmpresaActual()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot child) {
                    String idEmpresa = child.getKey();
                    String nombre = (String) child.child("nombre").getValue();
                    String etiquetaUbicacion = (String) child.child("etiquetaUbicacion").getValue();
                    String telefono1 = (String) child.child("telefono1").getValue();
                    String telefono2 = (String) child.child("telefono2" ).getValue();
                    String correo = (String) child.child("correo").getValue();
                    String descripcion = (String) child.child("descripcion").getValue();
                    String codigoVestimenta = (String) child.child("codigoVestimenta").getValue();
                    String entrada = (String) child.child("entrada").getValue();
                    String tipoNegocio = (String) child.child("tipoNegocio").getValue();
                    String paginaFacebook = (String) child.child("paginaFacebook").getValue();
                    String paginaInstagram = (String) child.child("paginaInstagram").getValue();
                    String paginaTwitter = (String) child.child("paginaTwitter").getValue();
                    int cantidadAsistentes = Integer.parseInt((String) child.child("cantidadAsistentes").getValue());
                    List<Horario> horarioSemanal = null;
                    aGlobal.setEmpresa(new Empresa(idEmpresa, nombre, etiquetaUbicacion, cantidadAsistentes,  tipoNegocio,
                            paginaFacebook,  paginaInstagram, paginaTwitter,  telefono1,
                            telefono2,  correo, descripcion,  codigoVestimenta,  entrada, horarioSemanal));
                    Mensaje(aGlobal.getEmpresa().getNombre());
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content, new PerfilNegocio()).commit();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("---OBJECT-----", "-----ERROR2-----");
            }
            }
        );
    }
}

