package com.example.denis.a2night;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Buscar extends Fragment {


    public Buscar() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_buscar, container, false);
        ImageView MiImageView = (ImageView) view.findViewById(R.id.perfilFrat);
        MiImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), PerfilLugar.class);
                startActivity(intent);
            }
        });
        return view;

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_buscar, container, false);
    }

    public void Mensaje(String msg){
        Toast.makeText(getActivity(), msg,Toast.LENGTH_SHORT).show();};

}
