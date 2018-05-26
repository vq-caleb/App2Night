package com.example.denis.a2night;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.denis.a2night.entidades.Empresa;
import com.example.denis.a2night.entidades.Producto;

import java.util.ArrayList;

public class AdapterItemMenu extends BaseAdapter {

    private Context context;
    private ArrayList<Producto> listItems;

    public AdapterItemMenu(Context context, ArrayList<Producto> listItems){
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
        Producto item = (Producto) getItem(i);
        view = LayoutInflater.from(context).inflate(R.layout.lineaproducto, null);

        ImageView imageView = (ImageView)  view.findViewById(R.id.imgProducto);
        TextView nombre = (TextView) view.findViewById(R.id.nombreProducto);
        TextView precio = (TextView) view.findViewById(R.id.precioProducto);
        nombre.setText(item.getNombre());
        precio.setText("" + item.getPrecio());
//            Bitmap decodeByte = BitmapFactory.decodeByteArray(ObjetoActual.getImagen2(),0,ObjetoActual.getImagen2().length);
        //          imageView.setImageBitmap(decodeByte);
        return view;
    }
}
