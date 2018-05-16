package com.example.denis.a2night;


import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.example.denis.a2night.entidades.Producto;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabMenu extends Fragment {
    View view;
    ImageView imageView;
    String nombre, precio;
    ArrayList<String> uris = new ArrayList();
    int cont = 0;
    int index = 0;
    AlmacenamientoGlobal aGlobal = AlmacenamientoGlobal.getInstance();
    public TabMenu() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tab_menu, container, false);
        this.misObjetos = aGlobal.getProductosEmpresaActual();
        LlenarListView();
        return view;
    }

    private List<Producto> misObjetos = new ArrayList<Producto>();

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
            Producto ObjetoActual = aGlobal.getProductosEmpresaActual().get(position);

            // Fill the view
            imageView = (ImageView)  itemView.findViewById(R.id.imgProducto);

            TextView elatributo01 = (TextView) itemView.findViewById(R.id.nombreProducto);
            elatributo01.setText(ObjetoActual.getAtributo01());
            TextView elatributo02 = (TextView) itemView.findViewById(R.id.precioProducto);
            elatributo02.setText("" + ObjetoActual.getAtributo02());
            Bitmap decodeByte = BitmapFactory.decodeByteArray(ObjetoActual.getImagen2(),0,ObjetoActual.getImagen2().length);
            imageView.setImageBitmap(decodeByte);
            return itemView;

        }
    }

    public void Mensaje(String msg){ Toast.makeText(getActivity(), msg,Toast.LENGTH_SHORT).show();};
}
