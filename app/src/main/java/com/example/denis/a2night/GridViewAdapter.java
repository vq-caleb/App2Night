package com.example.denis.a2night;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Denis on 13/04/2018.
 */

public class GridViewAdapter extends BaseAdapter {

    List<Integer> lstSource;
    Context mContext;

    public GridViewAdapter(List<Integer> lstSource, Context mContext) {
        this.lstSource = lstSource;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return lstSource.size();
    }

    @Override
    public Object getItem(int position) {
        return lstSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_grid, null);
        }
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imagen_post);
        imageView.setImageResource(lstSource.get(position));

        return convertView;
    }
}