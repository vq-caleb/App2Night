package com.example.denis.a2night;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.denis.a2night.entidades.Empresa;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilNegocio extends Fragment {
    static Empresa empresa = new Empresa("Frat House", "La Calle",1450, 230);
    TextView nombreEmpresa, etiquetaUbicacion, cantidadSeguidores, cantidadAsistentes;
    ImageView fotoPerfilEmpresa;

    public PerfilNegocio() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perfil_negocio, container, false);
        ImageView MiImageView = (ImageView) view.findViewById(R.id.imagenAtrasBuscar);
        MiImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //Fragment perfilNegocio = new PerfilNegocio();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content, new Buscar()).commit();
            }
        });


        OnclickDelTextView(R.id.info, view);
        OnclickDelTextView(R.id.promo, view);

        // alambramos el TextView
        /*TextView MiTextView = (TextView) view.findViewById(R.id.info);
        MiTextView.setOnClickListener(new View.OnClickListener(){

            @Override

            public void onClick(View arg0) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.contenido, new TabInformacion()).commit();
            }

        });*/

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.contenido, new TabPost()).commit();

        this.alambrarVariables(view);
        this.completarInformacion();
        return view;
    }

    public void completarInformacion(){
        this.nombreEmpresa.setText(this.empresa.getNombre());
        this.etiquetaUbicacion.setText(this.empresa.getEtiquetaUbicacion());
        this.cantidadSeguidores.setText(this.empresa.getCantidadSeguidoresFormartoK());
        this.cantidadAsistentes.setText(Integer.toString(this.empresa.getCantidadAsistentes()));
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

        miTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (v.getId()) {

                    case R.id.info:
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.contenido, new TabInformacion()).commit();
                        break;

                    case R.id.publicacion:
                        FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
                        transaction2.replace(R.id.contenido, new TabPost()).commit();

                        break;
                    default:break; }// fin de casos
            }// fin del onclick
        });
    }// fin de OnclickDelTextView

}