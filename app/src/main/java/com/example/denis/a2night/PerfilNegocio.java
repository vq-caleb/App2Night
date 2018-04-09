package com.example.denis.a2night;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilNegocio extends Fragment {


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
        return view;
    }

}
