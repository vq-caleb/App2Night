package com.example.denis.a2night;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.denis.a2night.entidades.AlmacenamientoGlobal;


/**
 * A simple {@link Fragment} subclass.
 */
public class Buscar extends Fragment {

    AlmacenamientoGlobal aGlobal = AlmacenamientoGlobal.getInstance();
    public Buscar() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buscar, container, false);
        //ImageView MiImageView = (ImageView) view.findViewById(R.id.perfilFrat);


        /*
        OnclickDelImageView(R.id.entrenouscr,inflater,container);
        OnclickDelImageView(R.id.saulbistrocr,inflater,container);
        OnclickDelImageView(R.id.aguizotescr,inflater,container);
        OnclickDelImageView(R.id.mercaditocr,inflater,container);
        OnclickDelImageView(R.id.laconchacr,inflater,container);
        OnclickDelImageView(R.id.antikcr,inflater,container);
        OnclickDelImageView(R.id.lacalicr,inflater,container);
        OnclickDelImageView(R.id.cuartelcr,inflater,container);
        OnclickDelImageView(R.id.einsteincr,inflater,container);
        OnclickDelImageView(R.id.perfilFrat,inflater,container);
        OnclickDelImageView(R.id.xcapecr,inflater,container);
        OnclickDelImageView(R.id.caccioscr,inflater,container);*/


        ImageView MiImageView = (ImageView) view.findViewById(R.id.entrenouscr);
        ImageView MiImageView2 = (ImageView) view.findViewById(R.id.saulbistrocr);
        ImageView MiImageView3 = (ImageView) view.findViewById(R.id.aguizotescr);
        ImageView MiImageView4 = (ImageView) view.findViewById(R.id.mercaditocr);
        ImageView MiImageView5 = (ImageView) view.findViewById(R.id.laconchacr);
        ImageView MiImageView6 = (ImageView) view.findViewById(R.id.antikcr);
        ImageView MiImageView7 = (ImageView) view.findViewById(R.id.lacalicr);
        ImageView MiImageView8 = (ImageView) view.findViewById(R.id.cuartelcr);
        ImageView MiImageView9 = (ImageView) view.findViewById(R.id.einsteincr);
        ImageView MiImageView10 = (ImageView) view.findViewById(R.id.perfilFrat);
        ImageView MiImageView11 = (ImageView) view.findViewById(R.id.caccioscr);

        MiImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fragment perfilNegocio = new PerfilNegocio();
                aGlobal.setIdEmpresaActual("entrenouscr");
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content, new PerfilNegocio()).commit();
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
                aGlobal.setIdEmpresaActual("perfilFrat");
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
        return view;

        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_buscar, container, false);


    }


    public void OnclickDelImageView(int ref,LayoutInflater inflater ,ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_buscar, container, false);
        ImageView MiImageView = (ImageView) view.findViewById(ref);


        MiImageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.entrenouscr:
                        aGlobal.setIdEmpresaActual("entrenouscr");
                        Mensaje("listener");
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.content, new PerfilNegocio()).commit();
                    break;

                    case R.id.saulbistrocr:
                        aGlobal.setIdEmpresaActual("entrenouscr");
                        Mensaje(aGlobal.getIdEmpresaActual());
                        pasaPerfilEmpresa();
                    break;

                    case R.id.aguizotescr:
                        aGlobal.setIdEmpresaActual("entrenouscr");
                        pasaPerfilEmpresa();
                    break;

                    case R.id.mercaditocr:
                        aGlobal.setIdEmpresaActual("entrenouscr");
                        pasaPerfilEmpresa();
                    break;

                    case R.id.laconchacr:
                        aGlobal.setIdEmpresaActual("entrenouscr");
                        pasaPerfilEmpresa();
                    break;

                    case R.id.antikcr:
                        aGlobal.setIdEmpresaActual("entrenouscr");
                        pasaPerfilEmpresa();
                    break;

                    case R.id.lacalicr:
                        aGlobal.setIdEmpresaActual("entrenouscr");
                        pasaPerfilEmpresa();
                    break;

                    case R.id.cuartelcr:
                        aGlobal.setIdEmpresaActual("entrenouscr");
                        pasaPerfilEmpresa();
                    break;

                    case R.id.einsteincr:
                        aGlobal.setIdEmpresaActual("entrenouscr");
                        pasaPerfilEmpresa();
                    break;

                    case R.id.perfilFrat:
                        aGlobal.setIdEmpresaActual("entrenouscr");
                        pasaPerfilEmpresa();
                    break;

                    case R.id.xcapecr:
                        aGlobal.setIdEmpresaActual("entrenouscr");
                        pasaPerfilEmpresa();
                    break;

                    case R.id.caccioscr:
                        aGlobal.setIdEmpresaActual("entrenouscr");
                        pasaPerfilEmpresa();
                    break;

                    default:break; }// fin de casos
            }// fin del onclick

        });
    }// fin de OnclickDelImageView

    public void Mensaje(String msg){
        Toast.makeText(getActivity(), msg,Toast.LENGTH_SHORT).show();};


    public void pasaPerfilEmpresa(){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content, new PerfilNegocio()).commit();
    }
}
