package com.example.denis.a2night;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
        final ImageView imageView;
        if(convertView == null){
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(250, 250));
            imageView.setImageResource(lstSource.get(position));
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, imageView.getId()+"", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else
            imageView = (ImageView) convertView;
        return imageView;
    }
}

