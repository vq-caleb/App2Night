package com.example.denis.a2night;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditarMenu extends Fragment {

    FirebaseDatabase db;
    DatabaseReference myRef;
    AlmacenamientoGlobal aGlobal = AlmacenamientoGlobal.getInstance();
    private ArrayList<Producto> listItems = new ArrayList<>();
    private ListView listView;
    private AdapterItemMenu adapterItemMenu;
    ImageView MiImageView;
    String nombre,precio,imagen;
    int productosCargados = 0;
    int index = 0, lastIndex;
    View view;
    TextView imagenTV, nombreTV, precioTV, eliminarTV, nombreProducto, precioProducto;
    static EditText Texto_01;

    public EditarMenu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_editar_menu, container, false);
        cargaProductos();
        listView = (ListView) view.findViewById(R.id.editarMenuProductos);
        listView.setDivider(null);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                imagenTV = (TextView) view.findViewById(R.id.editarImagen);
                nombreTV = (TextView) view.findViewById(R.id.editarNombre);
                precioTV = (TextView) view.findViewById(R.id.editarPrecio);
                eliminarTV = (TextView) view.findViewById(R.id.eliminar);
                nombreProducto = (TextView) view.findViewById(R.id.nombreProduc);
                precioProducto = (TextView) view.findViewById(R.id.precioProduc);
                mostrarEdiciones();
                eliminarProducto(""+i);
                editarNombre(i);
                editarPrecio(i);
            }
        });

        return view;
    }

    public void cargaProductos(){
        aGlobal.setProductosEmpresaActual(new ArrayList<Producto>());
        db = FirebaseDatabase.getInstance();
        db.getReference().child("Menu").child(aGlobal.getIdEmpresaActual())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            nombre = (child.child("nombre").getValue().toString());
                            precio = (child.child("precio").getValue().toString());
                            imagen = "menus/" + aGlobal.getIdEmpresaActual() + "/"+nombre+".jpg";
                            listItems.add(new Producto(nombre,precio,imagen));
                            productosCargados++;

                            if(productosCargados ==  dataSnapshot.getChildrenCount()){
                                lastIndex = aGlobal.getProductosEmpresaActual().size();
                                //cargarImagenes();
                            }
                            LlenarListView(listItems);
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d("---OBJECT-----", "-----ERROR2-----");
                    }
                });
    }

    /*public void cargarImagenes(){
        final long ONE_MEGABYTE = 1024 * 1024;
        FirebaseStorage.getInstance().getReference().
                child(aGlobal.getProductosEmpresaActual().get(this.index).getImagen()).getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                index++;
                aGlobal.getProductosEmpresaActual().get(index-1).setImagen2(bytes);
                if(index < aGlobal.getProductosEmpresaActual().size())
                    cargarImagenes();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
    }*/

    private void LlenarListView(ArrayList<Producto> listItems) {
        adapterItemMenu = new AdapterItemMenu(getActivity(), listItems);
        listView.setAdapter(adapterItemMenu);
    }

    public void mostrarEdiciones(){
        if (imagenTV.getVisibility() == imagenTV.VISIBLE &&
                nombreTV.getVisibility() == nombreTV.VISIBLE &&
                precioTV.getVisibility() == precioTV.VISIBLE &&
                eliminarTV.getVisibility() == eliminarTV.VISIBLE){
            imagenTV.setVisibility(imagenTV.INVISIBLE);
            nombreTV.setVisibility(nombreTV.INVISIBLE);
            precioTV.setVisibility(precioTV.INVISIBLE);
            eliminarTV.setVisibility(eliminarTV.INVISIBLE);
        } else {
            imagenTV.setVisibility(imagenTV.VISIBLE);
            nombreTV.setVisibility(nombreTV.VISIBLE);
            precioTV.setVisibility(precioTV.VISIBLE);
            eliminarTV.setVisibility(eliminarTV.VISIBLE);
        }
    }

    public void eliminarProducto(final String pos){
        eliminarTV.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                db = FirebaseDatabase.getInstance();
                myRef = db.getReference().child("Menu").child(aGlobal.getIdEmpresaActual());
                myRef.child(pos).removeValue();
                listItems.remove(pos);
                listItems.clear();
            }
        });
    }

    public void editarNombre(final int pos){
        nombreTV.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                DemeTexto_01(pos, 1);
            }
        });
    }

    public void editarPrecio(final int pos){
        precioTV.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                DemeTexto_01(pos, 2);
            }
        });
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
                        String aux = Texto_01.getText().toString();
                        if(numero == 1){
                            actualizarProducto(ubicacion+"", aux, listItems.get(ubicacion).getPrecio(),
                                    listItems.get(ubicacion).getImagen());
                            listItems.get(ubicacion).setNombre(aux);
                            listItems.clear();
                        }
                        else{
                            actualizarProducto(ubicacion+"", listItems.get(ubicacion).getNombre(),
                                    aux, listItems.get(ubicacion).getImagen());
                            listItems.get(ubicacion).setPrecio(aux);
                            listItems.clear();
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

    public void actualizarProducto(String objeto, String nombre, String precio, String imagen) {
        db = FirebaseDatabase.getInstance();
        myRef = db.getReference().child("Menu").child(aGlobal.getIdEmpresaActual());
        imagen = "menus/" + aGlobal.getIdEmpresaActual() + "/"+nombre+".jpg";
        Producto  producto = new Producto(nombre, precio, imagen);
        myRef.child(objeto).setValue(producto);
    }

    public void Mensaje(String msg){ Toast.makeText(getActivity(), msg,Toast.LENGTH_SHORT).show();};
}
