package com.example.denis.a2night;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.denis.a2night.entidades.Empresa;

import java.util.ArrayList;

/**
 * Created by Denis on 21/04/2018.
 */

public class AdapterItemSearch extends BaseAdapter {

    private Context context;
    private ArrayList<Empresa> listItems;

    public AdapterItemSearch(Context context, ArrayList<Empresa> listItems){
        this.context = context;
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int i) {
        return listItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Empresa item = (Empresa) getItem(i);
        view = LayoutInflater.from(context).inflate(R.layout.busqueda_item, null);
        ImageView imgFoto = (ImageView) view.findViewById(R.id.imgFoto);
        TextView etiqueta = (TextView) view.findViewById(R.id.etiqueta);
        TextView titulo = (TextView) view.findViewById(R.id.titulo);
        TextView contenido = (TextView) view.findViewById(R.id.contenido);

        if(item.getEtiquetaUbicacion() == ""){
            etiqueta.setVisibility(View.GONE);
            //imgFoto.setImageResource(item.getFotoPerfil());
            titulo.setText(item.getNombre());
            contenido.setText(item.getTipoNegocio());
        }
        else {
            etiqueta.setText(item.getEtiquetaUbicacion());
            //imgFoto.setImageResource(item.getFotoPerfil());
            titulo.setText(item.getNombre());
            contenido.setText(item.getTipoNegocio());
        }
        return view;
    }
}
