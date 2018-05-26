package com.example.denis.a2night;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.denis.a2night.entidades.AlmacenamientoGlobal;
import com.example.denis.a2night.entidades.Producto;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditarMenu extends Fragment {

    FirebaseDatabase db;
    DatabaseReference myRef;
    View view;
    TextView imagen, nombre, precio, eliminar, nombreProducto, precioProducto, editar;
    ListView list;
    static EditText Texto_01;
    ImageView imageView;
    ArrayList<String> uris = new ArrayList();
    int cont = 0;
    int index = 0;
    Producto ObjEscogido;
    AlmacenamientoGlobal aGlobal = AlmacenamientoGlobal.getInstance();
    ArrayAdapter<Producto> adapter;
    private List<Producto> misObjetos = new ArrayList<Producto>();

    public EditarMenu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_editar_menu, container, false);
        this.misObjetos = aGlobal.getProductosEmpresaActual();

        RegistrarClicks();
        Mensaje(aGlobal.getEmpresa().getNombre());
        LlenarListView();

        editar = (TextView) view.findViewById(R.id.editar);
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        list = (ListView) view.findViewById(R.id.editarMenuProductos);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {

                imagen = (TextView) view.findViewById(R.id.editarImagen);
                nombre = (TextView) view.findViewById(R.id.editarNombre);
                precio = (TextView) view.findViewById(R.id.editarPrecio);
                eliminar = (TextView) view.findViewById(R.id.eliminar);
                nombreProducto = (TextView) view.findViewById(R.id.nombreProducto);
                precioProducto = (TextView) view.findViewById(R.id.precioProducto);

                eliminar.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View arg0) {
                        borrarProducto(""+i);
                        adapter.notifyDataSetChanged();
                    }
                });

                nombre.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DemeTexto_01(i, 1);
                    }
                });
                precio = (TextView) view.findViewById(R.id.editarPrecio);
                precio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ObjEscogido = misObjetos.get(i);
                        ModificarObjetoEnFirebase(i+"", ObjEscogido.getNombre(), "900", ObjEscogido.getImagen());
                        misObjetos.get(i).setPrecio("900");
                        //adapter.getItem(i).setPrecio("1212");
                        adapter.notifyDataSetChanged();
                        Mensaje(misObjetos.get(i).getPrecio().toString());
                    }
                });

                if (imagen.getVisibility() == imagen.VISIBLE &&
                        nombre.getVisibility() == nombre.VISIBLE &&
                        precio.getVisibility() == precio.VISIBLE &&
                        eliminar.getVisibility() == eliminar.VISIBLE){
                    imagen.setVisibility(imagen.INVISIBLE);
                    nombre.setVisibility(imagen.INVISIBLE);
                    precio.setVisibility(imagen.INVISIBLE);
                    eliminar.setVisibility(imagen.INVISIBLE);
                } else {
                    imagen.setVisibility(imagen.VISIBLE);
                    nombre.setVisibility(imagen.VISIBLE);
                    precio.setVisibility(imagen.VISIBLE);
                    eliminar.setVisibility(imagen.VISIBLE);
                }
            }
        });
        return view;
    }

    private void LlenarListView() {
        adapter = new MyListAdapter();
        list = (ListView) view.findViewById(R.id.editarMenuProductos);
        list.setDivider(null);
        list.setAdapter(adapter);
    }
    private void RegistrarClicks() {

    }

    private class MyListAdapter extends ArrayAdapter<Producto> {

        public MyListAdapter() {

            super(getActivity(), R.layout.lineaproducto, misObjetos);
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.lineaproducto, parent, false);
            }
            Producto ObjetoActual = aGlobal.getProductosEmpresaActual().get(position);

            imageView = (ImageView)  itemView.findViewById(R.id.imgProducto);

            TextView elatributo01 = (TextView) itemView.findViewById(R.id.nombreProducto);
            elatributo01.setText(ObjetoActual.getNombre());
            TextView elatributo02 = (TextView) itemView.findViewById(R.id.precioProducto);
            elatributo02.setText("" + ObjetoActual.getPrecio());
//            Bitmap decodeByte = BitmapFactory.decodeByteArray(ObjetoActual.getImagen2(),0,ObjetoActual.getImagen2().length);
  //          imageView.setImageBitmap(decodeByte);
            return itemView;

        }
    }

    public void DemeTexto_01(final int ubicacion, final int numero){
        Texto_01 =  new EditText(getActivity());
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
        builder1.setMessage("Digite su dato:");
        Texto_01.setText("");
        Texto_01.selectAll();
        builder1.setView(Texto_01);

        builder1.setCancelable(true);
        builder1.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ObjEscogido = misObjetos.get(ubicacion);
                        if(numero == 1){
                            nombreProducto.setText(Texto_01.getText().toString());
                            ModificarObjetoEnFirebase(ubicacion+"", Texto_01.getText().toString(), ObjEscogido.getPrecio(), ObjEscogido.getImagen());
                        }
                        else{
                            precioProducto.setText(Texto_01.getText().toString());
                            ModificarObjetoEnFirebase(ubicacion+"", ObjEscogido.getNombre(), Texto_01.getText().toString(), ObjEscogido.getImagen());
                            adapter.getItem(ubicacion).setPrecio(Texto_01.getText().toString());
                            adapter.notifyDataSetChanged();
                            Mensaje(adapter.getItem(ubicacion).getPrecio().toString());
                        }
                    }
                });

        builder1.setNegativeButton("Cancelar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Mensaje("Cancelado");
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    };

    public void ModificarObjetoEnFirebase(String objeto, String nombre, String precio, String imagen) {
        db = FirebaseDatabase.getInstance();
        myRef = db.getReference().child("Menu").child(aGlobal.getIdEmpresaActual());
        imagen = "menus/" + aGlobal.getIdEmpresaActual() + "/"+nombre+".jpg";
        Producto  producto = new Producto(nombre, precio, imagen);
        myRef.child(objeto).setValue(producto);
    }

    public void borrarProducto(String nombreobj) {
        myRef = db.getReference().child("Menu").child(aGlobal.getIdEmpresaActual());
        myRef.child(nombreobj).removeValue();
    }

    public void Mensaje(String msg){ Toast.makeText(getActivity(), msg,Toast.LENGTH_SHORT).show();};
}
