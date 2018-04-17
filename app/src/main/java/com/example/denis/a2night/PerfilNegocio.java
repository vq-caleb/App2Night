package com.example.denis.a2night;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */

public class PerfilNegocio extends Fragment {
    AlmacenamientoGlobal aGlobal = AlmacenamientoGlobal.getInstance();
    TextView nombreEmpresa, etiquetaUbicacion, cantidadSeguidores, cantidadAsistentes;
    ImageView fotoPerfilEmpresa;
    Empresa empresa;


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
                transaction.replace(R.id.content, new Buscar()).commit();
            }
        });
        alambrarVariables(view);

        OnclickDelTextView(R.id.info, view);
        OnclickDelTextView(R.id.publicacion, view);
        OnclickDelTextView(R.id.promo, view);
        OnclickDelTextView(R.id.evento, view);

        Map<String,Integer> images = new HashMap();
        images.put("mercaditocr",R.drawable.mercadoescalante);
        images.put("entrenouscr",R.drawable.entrenous);
        images.put("saulbistrocr",R.drawable.saulbistro);
        images.put("aguizotescr",R.drawable.aguizotes);
        images.put("laconchacr",R.drawable.laconcha);
        images.put("antikcr",R.drawable.antik);
        images.put("lacalicr",R.drawable.lacali);
        images.put("cuartelcr",R.drawable.elcuartel);
        images.put("einsteincr",R.drawable.einstein);
        images.put("frathousecr",R.drawable.frat);
        images.put("xcapecr",R.drawable.xcape);
        images.put("caccioscr",R.drawable.caccios);

        ImageView imagenPerfil = (ImageView) view.findViewById(R.id.perfil);
        imagenPerfil.setImageResource(images.get(aGlobal.getIdEmpresaActual()));

        TextView info01 = (TextView) view.findViewById(R.id.publicacion);
        info01.setTextColor(getResources().getColor(R.color.colorPrimary));
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
        this.nombreEmpresa.setText(this.aGlobal.getEmpresa().getNombre());
        this.etiquetaUbicacion.setText(this.aGlobal.getEmpresa().getEtiquetaUbicacion());
        this.cantidadSeguidores.setText(this.aGlobal.getEmpresa().getCantidadSeguidoresFormartoK());
        this.cantidadAsistentes.setText(Integer.toString(this.aGlobal.getEmpresa().getCantidadAsistentes()));
    }

    public void alambrarVariables(View view){
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