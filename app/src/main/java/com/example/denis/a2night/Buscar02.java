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

/**
 * A simple {@link Fragment} subclass.
 */
public class Buscar02 extends Fragment {

    private AdapterItemSearch adapterItemSearch;
    AlmacenamientoGlobal aGlobal = AlmacenamientoGlobal.getInstance();
    ArrayList<Empresa> listItems, listItems2, listItems3;
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
                //FragmentTransaction transaction = getFragmentManager().beginTransaction();
                //transaction.replace(R.id.content, new PerfilNegocio()).commit();
                listItems3.add(listItems2.get(i));
                LlenarListView2();
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
        db.getReference().child("Bares").addValueEventListener(new ValueEventListener() {
                                                                   @Override
                                                                   public void onDataChange(DataSnapshot dataSnapshot)
                                                                   {
                                                                       listItems = new ArrayList<>();
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

    public void LlenarListView2(){
        adapterItemSearch = new AdapterItemSearch(getActivity(), listItems3);
        listaReciente.setAdapter(adapterItemSearch);
    }

}




