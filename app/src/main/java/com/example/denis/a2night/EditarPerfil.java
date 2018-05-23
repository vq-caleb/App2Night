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

import com.example.denis.a2night.entidades.AlmacenamientoGlobal;
import com.example.denis.a2night.entidades.Empresa;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditarPerfil extends Fragment {
    AlmacenamientoGlobal aGlobal = AlmacenamientoGlobal.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    View view;
    public EditarPerfil() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_editar_perfil, container, false);

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

    public void editarEmpresa(){
        Empresa temp = aGlobal.getEmpresaUsuario();
        myRef = database.getReference("bares").child(temp.getIdEmpresa());
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        EditText correoElectronico = (EditText) view.findViewById(R.id.correoElectronico);
        EditText nombre = (EditText) view.findViewById(R.id.nombre);
        EditText etiqueta = (EditText) view.findViewById(R.id.etiqueta);
        EditText telefono1 = (EditText) view.findViewById(R.id.telefono1);
        EditText telefono2 = (EditText) view.findViewById(R.id.telefono2);
        EditText pagFacebook = (EditText) view.findViewById(R.id.pagFacebook);
        EditText pagInstagram = (EditText) view.findViewById(R.id.pagInstagram);
        EditText pagTwitter = (EditText) view.findViewById(R.id.pagTwitter);
        EditText tipo = (EditText) view.findViewById(R.id.tipo);
        EditText cover = (EditText) view.findViewById(R.id.cover);
        EditText codVestimenta = (EditText) view.findViewById(R.id.codVestimenta);

        if (correoElectronico.getText().length() != 0){
            temp.setCorreo(correoElectronico.getText().toString());
            myRef.child("correo").setValue(temp.getCorreo());
        }
        if (nombre.getText().length() != 0){
            temp.setNombre(nombre.getText().toString());
            myRef.child("nombre").setValue(temp.getNombre());
        }
        if (etiqueta.getText().length() != 0){
            temp.setEtiquetaUbicacion(etiqueta.getText().toString());
            myRef.child("etiquetaUbicacion").setValue(temp.getEtiquetaUbicacion());
        }
        if (telefono1.getText().length() != 0){
            temp.setTelefono1(telefono1.getText().toString());
            myRef.child("telefono1").setValue(temp.getTelefono1());
        }
        if (telefono2.getText().length() != 0){
            temp.setTelefono2(telefono2.getText().toString());
            myRef.child("telefono2").setValue(temp.getTelefono2());
        }
        if (pagFacebook.getText().length() != 0){
            temp.setPaginaFacebook(pagFacebook.getText().toString());
            myRef.child("paginaFacebook").setValue(temp.getPaginaFacebook());
        }
        if (pagInstagram.getText().length() != 0){
            temp.setPaginaInstagram(pagInstagram.getText().toString());
            myRef.child("paginaInstagram").setValue(temp.getPaginaInstagram());
        }
        if (pagTwitter.getText().length() != 0){
            temp.setPaginaTwitter(pagTwitter.getText().toString());
            myRef.child("paginaTwitter").setValue(temp.getPaginaTwitter());
        }
        if (tipo.getText().length() != 0){
            temp.setTipoNegocio(tipo.getText().toString());
            myRef.child("tipoNegocio").setValue(temp.getTipoNegocio());
        }
        if (cover.getText().length() != 0){
            temp.setEntrada(cover.getText().toString());
            myRef.child("entrada").setValue("₡ "+temp.getEntrada());
        }
        if (codVestimenta.getText().length() != 0){
            temp.setCodigoVestimenta(codVestimenta.getText().toString());
            myRef.child("codigoVestimenta").setValue(temp.getCodigoVestimenta());
        }
    }

    public void Mensaje(String msg){ Toast.makeText(getActivity(), msg,Toast.LENGTH_SHORT).show();};
}