package com.example.denis.a2night;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Seguidores extends Fragment {

    View view;
    private ArrayList<String> listItems = new ArrayList<>();
    TextView textView;

    public Seguidores() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_seguidores, container, false);

        LlenarListView();
        cantidadSeguidores();

        return view;
    }

    private void LlenarListView() {
        listItems.add("Dwight D. Eisenhower");
        listItems.add("John F. Kennedy");
        listItems.add("Richard Nixon");
        listItems.add("Lyndon B. Johnson");
        listItems.add("Gerald Ford");
        listItems.add("Jimmy Carter");
        listItems.add("Ronald Reagan");
        listItems.add("George H. W. Bush");
        listItems.add("Bill Clinton");
        listItems.add("George W. Bush");
        listItems.add("Barack Obama");

    ArrayAdapter<String> adaptador =new ArrayAdapter(getActivity(),
            android.R.layout.simple_list_item_1, listItems);

    ListView milistview = (ListView) view.findViewById(R.id.lvSeguidores);
        milistview.setAdapter(adaptador);

    }

    public void cantidadSeguidores(){
        textView = (TextView) view.findViewById(R.id.cantidad);
        for(int i=1; i< listItems.size(); i++){
            textView.setText(i + " seguidores");
        }
    }
}
