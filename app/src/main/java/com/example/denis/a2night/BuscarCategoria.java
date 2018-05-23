package com.example.denis.a2night;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.denis.a2night.entidades.AlmacenamientoGlobal;
import com.example.denis.a2night.entidades.Empresa;
import com.example.denis.a2night.entidades.Horario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BuscarCategoria extends Fragment {

    AlmacenamientoGlobal aGlobal = AlmacenamientoGlobal.getInstance();
    private ArrayList<Empresa> listItems = new ArrayList<>();
    private ListView listView;
    private AdapterItemSearch adapterItemSearch;
    ImageView MiImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_buscar_categoria, container, false);
        LlenarListaObjetos();
        listView = (ListView) view.findViewById(R.id.lvItems);
        listView.setDivider(null);
        MiImageView   = (ImageView) view.findViewById(R.id.atrasPerfil);

        MiImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content, new Buscar01()).commit();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                aGlobal.setIdEmpresaActual(listItems.get(i).getIdEmpresa());
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content, new PerfilNegocio()).commit();
            }
        });

        return view;
    }

    private void LlenarListaObjetos() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        db.getReference().child("Bares").addValueEventListener(new ValueEventListener() {
                                           @Override
                                           public void onDataChange(DataSnapshot dataSnapshot)
                                           {
                                               for (DataSnapshot child : dataSnapshot.getChildren()) {
                                                   String nombre = (String) child.child("nombre").getValue();
                                                   String etiquetaUbicacion = (String) child.child("etiquetaUbicacion").getValue();
                                                   String telefono1 = "";
                                                   String telefono2 = "";
                                                   String correo = "";
                                                   String descripcion = "";
                                                   String codigoVestimenta = "";
                                                   String entrada = "";
                                                   String tipoNegocio = (String) child.child("tipoNegocio").getValue();
                                                   String paginaFacebook = "";
                                                   String paginaInstagram = "";
                                                   String paginaTwitter = "";
                                                   int cantidadAsistentes =  0;
                                                   List<Horario> horarioSemanal = null;
                                                   listItems.add(new Empresa(aGlobal.getIdEmpresaActual(), nombre, etiquetaUbicacion, cantidadAsistentes,  tipoNegocio,
                                                           paginaFacebook,  paginaInstagram, paginaTwitter,  telefono1,
                                                           telefono2,  correo, descripcion,  codigoVestimenta,  entrada, horarioSemanal));
                                               }

                                               Collections.sort(listItems, new Comparator<Empresa>() {
                                                   @Override
                                                   public int compare(Empresa empresa, Empresa t1) {
                                                       return empresa.getEtiquetaUbicacion().compareTo(t1.getEtiquetaUbicacion());
                                                   }
                                               });

                                               String nuevaEtiqueta = "";
                                               for(int i = 0; i<listItems.size(); i++){
                                                   if(!listItems.get(i).getEtiquetaUbicacion().equals(nuevaEtiqueta))
                                                       nuevaEtiqueta = listItems.get(i).getEtiquetaUbicacion();
                                                   else
                                                       listItems.get(i).setEtiquetaUbicacion("");
                                               }
                                               LlenarListView(listItems);
                                           }
                                           @Override
                                           public void onCancelled(DatabaseError databaseError) {
                                               Log.d("---OBJECT-----", "-----ERROR2-----");
                                           }
                                       }
                );
    }

    private void LlenarListView(ArrayList<Empresa> listItems) {
        adapterItemSearch = new AdapterItemSearch(getActivity(), listItems);
        listView.setAdapter(adapterItemSearch);
    }

}
