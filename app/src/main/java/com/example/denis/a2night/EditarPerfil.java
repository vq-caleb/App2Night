package com.example.denis.a2night;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditarPerfil extends Fragment {


    public EditarPerfil() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_editar_perfil, container, false);

        /*OnChangeDelEditText(view.findViewById(R.id.correoElectronico));
        OnChangeDelEditText(view.findViewById(R.id.nombre));
        OnChangeDelEditText(view.findViewById(R.id.etiqueta));
        OnChangeDelEditText(view.findViewById(R.id.telefono1));
        OnChangeDelEditText(view.findViewById(R.id.telefono2));
        OnChangeDelEditText(view.findViewById(R.id.pagFacebook));
        OnChangeDelEditText(view.findViewById(R.id.pagInstagram));
        OnChangeDelEditText(view.findViewById(R.id.pagTwitter));
        OnChangeDelEditText(view.findViewById(R.id.tipo));
        OnChangeDelEditText(view.findViewById(R.id.cover));
        OnChangeDelEditText(view.findViewById(R.id.codVestimenta));*/


        return view;
    }

    public void Mensaje(String msg){ Toast.makeText(getActivity(), msg,Toast.LENGTH_SHORT).show();};
}