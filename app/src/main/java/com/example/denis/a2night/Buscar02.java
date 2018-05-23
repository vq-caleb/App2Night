package com.example.denis.a2night;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 */
public class Buscar02 extends Fragment {

    private AdapterItemSearch adapterItemSearch;
    AlmacenamientoGlobal aGlobal = AlmacenamientoGlobal.getInstance();
    ArrayList<Empresa> listItems, listItems2, listItems3; //list2 = bsuqueda autcomplete , list3 = busqueda recientes
    ListView listaAutoComp, listaReciente;
    EditText editText;
    TextView textView;
    ImageView MiImageView;
    View view;

    public Buscar02() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view     = inflater.inflate(R.layout.fragment_buscar02, container, false);
        MiImageView   = (ImageView) view.findViewById(R.id.regresarBusqueda);
        listaAutoComp = (ListView) view.findViewById(R.id.listComp);
        listaReciente = (ListView) view.findViewById(R.id.listReciente);
        editText      = (EditText) view.findViewById(R.id.busqueda);
        textView      = (TextView) view.findViewById(R.id.reciente);

        listaAutoComp.setDivider(null);
        listaReciente.setDivider(null);
        LlenarListAutoComp();
        listItems3 = new ArrayList<>();
        //listItems3.add(listItems.get(0));
        //LlenarListView2();

        MiImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content, new Buscar01()).commit();
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().equals("")){
                    listaAutoComp.setVisibility(listaAutoComp.INVISIBLE);
                    textView.setVisibility(textView.VISIBLE);
                    listaReciente.setVisibility(listaReciente.VISIBLE);
                    LlenarListAutoComp();
                }
                else{
                    searchItem(charSequence.toString());
                    listaAutoComp.setVisibility(listaAutoComp.VISIBLE);
                    textView.setVisibility(textView.INVISIBLE);
                    listaReciente.setVisibility(listaReciente.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        listaAutoComp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                aGlobal.setIdEmpresaActual(listItems2.get(i).getIdEmpresa());
                aGlobal.setEmpresa(listItems2.get(i));
                listItems3.add(listItems2.get(i));
                Set<Empresa> hs = new HashSet<>();
                hs.addAll(listItems3);
                listItems3.clear();
                listItems3.addAll(hs);
                LlenarListView3();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content, new PerfilNegocio()).commit();
            }
        });

        return view;
    }

    public void searchItem(String textToSearch){
        listItems2 = new ArrayList<>();
        for(int i = 0; i < listItems.size(); i++){
            if(listItems.get(i).getNombre().toLowerCase().contains(textToSearch.toLowerCase())){
                listItems2.add(listItems.get(i));
            }
        }
        adapterItemSearch = new AdapterItemSearch(getActivity(), listItems2);
        listaAutoComp.setAdapter(adapterItemSearch);
    }

    private void LlenarListAutoComp() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        db.getReference().child("Bares").orderByValue().addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               listItems = new ArrayList<>();
               for (DataSnapshot child : dataSnapshot.getChildren()) {
                   String idEmpresa = child.getKey();
                   String nombre = (String) child.child("nombre").getValue();
                   String etiquetaUbicacion = (String) child.child("etiquetaUbicacion").getValue();
                   String telefono1 = (String) child.child("telefono1").getValue();
                   String telefono2 = (String) child.child("telefono2" ).getValue();
                   String correo = (String) child.child("correo").getValue();
                   String descripcion = (String) child.child("descripcion").getValue();
                   String codigoVestimenta = (String) child.child("codigoVestimenta").getValue();
                   String entrada = (String) child.child("entrada").getValue();
                   String tipoNegocio = (String) child.child("tipoNegocio").getValue();
                   String paginaFacebook = (String) child.child("paginaFacebook").getValue();
                   String paginaInstagram = (String) child.child("paginaInstagram").getValue();
                   String paginaTwitter = (String) child.child("paginaTwitter").getValue();
                   int cantidadAsistentes = Integer.parseInt((String) child.child("cantidadAsistentes").getValue());
                   List<Horario> horarioSemanal = null;
                   listItems.add(new Empresa(idEmpresa, nombre, etiquetaUbicacion, cantidadAsistentes,  tipoNegocio,
                           paginaFacebook,  paginaInstagram, paginaTwitter,  telefono1,
                           telefono2,  correo, descripcion,  codigoVestimenta,  entrada, horarioSemanal));
               }

               for(int i = 0; i<listItems.size(); i++){
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
        listaAutoComp.setAdapter(adapterItemSearch);
    }

    public void LlenarListView3(){
        adapterItemSearch = new AdapterItemSearch(getActivity(), listItems3);
        listaReciente.setAdapter(adapterItemSearch);
    }

    public void Mensaje(String msg){
        Toast.makeText(getActivity(), msg,Toast.LENGTH_SHORT).show();};
}




