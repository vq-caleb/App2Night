package com.example.denis.a2night;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabPost extends Fragment {

    private GridView gridView;
    private GridViewAdapter adapter;

    private static final String  TAG = "TabPost";

    Integer[] array_characters = {
            R.drawable.einstein,
            R.drawable.antik,
            R.drawable.xcape,
            R.drawable.lacali,
            R.drawable.laconcha,
            R.drawable.caccios,
            R.drawable.einstein,
            R.drawable.antik,
            R.drawable.xcape,
            R.drawable.lacali,
            R.drawable.laconcha,
            R.drawable.caccios,
            R.drawable.caccios,
            R.drawable.einstein,
            R.drawable.antik,
            R.drawable.xcape,
            R.drawable.lacali,
            R.drawable.laconcha,
            R.drawable.caccios
    };

    List<Integer> lstSource = new ArrayList<>();

    public TabPost() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_tab_post, container, false);
        setUpList();
        gridView = (GridView) view.findViewById(R.id.grid02);
        adapter = new GridViewAdapter(lstSource, getActivity());
        gridView.setAdapter(adapter);

        return view;
    }

    private void setUpList() {
        for(Integer item:array_characters)
            lstSource.add(item);
    }


}
