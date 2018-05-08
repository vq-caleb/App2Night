package com.example.denis.a2night;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.denis.a2night.entidades.AlmacenamientoGlobal;
import com.example.denis.a2night.entidades.Empresa;
import com.example.denis.a2night.entidades.Horario;
import com.example.denis.a2night.entidades.Producto;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class menuproductos extends Fragment {
    View view;
    AlmacenamientoGlobal aGlobal = AlmacenamientoGlobal.getInstance();
    public menuproductos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_menuproductos, container, false);
        LlenarListaObjetos();
        return view;
    }

    private List<Producto> misObjetos = new ArrayList<Producto>();
    private void LlenarListaObjetos() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        db.getReference().child("Menu").child(aGlobal.getIdEmpresaActual())
            .addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot)
               {
                   for (DataSnapshot child : dataSnapshot.getChildren()) {
                       String nombre = child.child("nombre").getValue().toString();
                       String precio = child.child("precio").getValue().toString();
                       misObjetos.add(new Producto(nombre, precio, R.drawable.producto));
                   }
                   LlenarListView();
                   RegistrarClicks();
               }
               @Override
               public void onCancelled(DatabaseError databaseError) {
                   Log.d("---OBJECT-----", "-----ERROR2-----");
               }
            }
        );
        //Mensaje(misObjetos.toString());
        /*misObjetos.add(new Producto("A1-01", "A1-02", R.drawable.producto));
        misObjetos.add(new Producto("A2-01", "A2-02", R.drawable.producto));
        misObjetos.add(new Producto("A3-01", "A3-02", R.drawable.producto));
        misObjetos.add(new Producto("A4-01", "A4-02", R.drawable.producto));
        misObjetos.add(new Producto("A5-01", "A5-02", R.drawable.producto));
        misObjetos.add(new Producto("A6-01", "A6-02", R.drawable.producto));
        misObjetos.add(new Producto("A7-01", "A7-02", R.drawable.producto));*/
    }
    private void LlenarListView() {
        ArrayAdapter<Producto> adapter = new MyListAdapter();
        ListView list = (ListView) view.findViewById(R.id.menuProductos);
        list.setAdapter(adapter);
    }
    private void RegistrarClicks() {
        ListView list = (ListView) view.findViewById(R.id.menuProductos);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked,
                                    int position, long id) {
                Producto ObjEscogido = misObjetos.get(position);
                Mensaje(ObjEscogido.getAtributo01());
            }
        });
    }
    private class MyListAdapter extends ArrayAdapter<Producto> {
        public MyListAdapter() {
            super(getActivity(), R.layout.lineaproducto, misObjetos);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Make sure we have a view to work with (may have been given null)
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.lineaproducto, parent, false);
            }
            Producto ObjetoActual = misObjetos.get(position);
            // Fill the view
            ImageView imageView = (ImageView)  itemView.findViewById(R.id.imgProducto);
            imageView.setImageResource(ObjetoActual.getNumDibujo());
            TextView elatributo01 = (TextView) itemView.findViewById(R.id.nombreProducto);
            elatributo01.setText(ObjetoActual.getAtributo01());
            TextView elatributo02 = (TextView) itemView.findViewById(R.id.precioProducto);
            elatributo02.setText("" + ObjetoActual.getAtributo02());
            return itemView;
        }
    }

    public void Mensaje(String msg){ Toast.makeText(getActivity(), msg,Toast.LENGTH_SHORT).show();};
}
