package com.example.denis.a2night;


import android.content.Intent;
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
import com.example.denis.a2night.entidades.Empresa;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class Perfil extends Fragment {
    AlmacenamientoGlobal aGlobal = AlmacenamientoGlobal.getInstance();

    public Perfil() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        aGlobal.setEmpresaUsuario(new Empresa());

        OnclickDelButton(view.findViewById(R.id.editarPerfil));
        OnclickDelButton(view.findViewById(R.id.seguidores));
        OnclickDelButton(view.findViewById(R.id.editarMenu));
        OnclickDelButton(view.findViewById(R.id.agregarPublicacion));
        OnclickDelButton(view.findViewById(R.id.agregarProducto));
        OnclickDelButton(view.findViewById(R.id.salir));

        return view;
    }

    public void OnclickDelButton(View view) {
        ImageView opciones = (ImageView)  view;
        opciones.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (v.getId()) {

                    case R.id.editarPerfil:
                        Mensaje("Editar");
                        FragmentTransaction transaction1 = getFragmentManager().beginTransaction();
                        transaction1.replace(R.id.content, new EditarPerfil()).commit();
                        break;

                    case R.id.seguidores:
                        FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
                        transaction2.replace(R.id.content, new Seguidores()).commit();
                        break;

                    case R.id.editarMenu:
                        FragmentTransaction transaction3 = getFragmentManager().beginTransaction();
                        transaction3.replace(R.id.content, new EditarMenu()).commit();
                        break;

                    case R.id.agregarPublicacion:
                        FragmentTransaction transaction4 = getFragmentManager().beginTransaction();
                        transaction4.replace(R.id.content, new AgregarPublicacion()).commit();
                        break;

                    case R.id.agregarProducto:
                        FragmentTransaction transaction5 = getFragmentManager().beginTransaction();
                        transaction5.replace(R.id.content, new AgregarProducto()).commit();
                        break;

                    case R.id.salir:
                        FirebaseAuth.getInstance().signOut();
                        Intent intento = new Intent(getActivity(), Login.class);
                        startActivity(intento);
                        break;
                    default:break; }// fin de casos
            }// fin del onclick
        });
    }// fin de OnclickDelButton

    public void Mensaje(String msg){ Toast.makeText(getActivity(), msg,Toast.LENGTH_SHORT).show();};
}

