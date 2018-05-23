package com.example.denis.a2night;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.denis.a2night.entidades.AlmacenamientoGlobal;


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
                //Fragment perfilNegocio = new PerfilNegocio();
                aGlobal.setIdEmpresaActual("entrenouscr");
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content, new TabPost()).commit();
            }
        });

        MiImageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aGlobal.setIdEmpresaActual("saulbistrocr");
                //Fragment perfilNegocio = new PerfilNegocio();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content, new PerfilNegocio()).commit();
            }
        });

        MiImageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aGlobal.setIdEmpresaActual("aguizotescr");
                //Fragment perfilNegocio = new PerfilNegocio();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content, new PerfilNegocio()).commit();
            }
        });

        MiImageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aGlobal.setIdEmpresaActual("mercaditocr");
                //Fragment perfilNegocio = new PerfilNegocio();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content, new PerfilNegocio()).commit();
            }
        });

        MiImageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aGlobal.setIdEmpresaActual("laconchacr");
                //Fragment perfilNegocio = new PerfilNegocio();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content, new PerfilNegocio()).commit();
            }
        });

        MiImageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aGlobal.setIdEmpresaActual("antikcr");
                //Fragment perfilNegocio = new PerfilNegocio();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content, new PerfilNegocio()).commit();
            }
        });

        MiImageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aGlobal.setIdEmpresaActual("lacalicr");
                //Fragment perfilNegocio = new PerfilNegocio();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content, new PerfilNegocio()).commit();
            }
        });

        MiImageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aGlobal.setIdEmpresaActual("cuartelcr");
                //Fragment perfilNegocio = new PerfilNegocio();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content, new PerfilNegocio()).commit();
            }
        });

        MiImageView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aGlobal.setIdEmpresaActual("einsteincr");
                //Fragment perfilNegocio = new PerfilNegocio();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content, new PerfilNegocio()).commit();
            }
        });

        MiImageView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aGlobal.setIdEmpresaActual("frathousecr");
                //Fragment perfilNegocio = new PerfilNegocio();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content, new PerfilNegocio()).commit();
            }
        });

        MiImageView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aGlobal.setIdEmpresaActual("caccioscr");
                //Fragment perfilNegocio = new PerfilNegocio();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content, new PerfilNegocio()).commit();
            }
        });

        MiImageView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aGlobal.setIdEmpresaActual("xcapecr");
                //Fragment perfilNegocio = new PerfilNegocio();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content, new PerfilNegocio()).commit();
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

}

