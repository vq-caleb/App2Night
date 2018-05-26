package com.example.denis.a2night;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.denis.a2night.entidades.AlmacenamientoGlobal;
import com.example.denis.a2night.entidades.Producto;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AgregarProducto extends Fragment {

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    AlmacenamientoGlobal aGlobal = AlmacenamientoGlobal.getInstance();
    private List<Producto> misObjetos = new ArrayList<Producto>();
    View view;
    EditText nombre, precio;
    Button button;

    public AgregarProducto() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_agregar_producto, container, false);
        this.misObjetos = aGlobal.getProductosEmpresaActual();
        button = (Button) view.findViewById(R.id.agregar);
        nombre = (EditText) view.findViewById(R.id.nombreProduc);
        precio = (EditText) view.findViewById(R.id.precioProduc);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarProducto(nombre.getText().toString(), precio.getText().toString());
            }
        });
        return view;
    }

    private void agregarProducto(final String nombre2, String precio2) {
        myRef = db.getReference().child("Menu").child(aGlobal.getIdEmpresaActual());
            Producto producto = new Producto(nombre2, precio2, "");
            myRef.child(""+(misObjetos.size()+1)).setValue(producto);
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Mensaje("Producto" + nombre2 + " agregado");
                    nombre.setText("");
                    precio.setText("");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
    }

    public void Mensaje(String msg){
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    };


}
