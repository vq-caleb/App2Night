package com.example.denis.a2night;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.denis.a2night.Adapter.MyAdapter;
import com.example.denis.a2night.Interface.ILoadMore;
import com.example.denis.a2night.Model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Minor on 15/04/2018.
 */

public class MainActivity extends AppCompatActivity {

    List<Item> items = new ArrayList<>();
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        random10Data();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(recyclerView, this, items);
        recyclerView.setAdapter(adapter);

        adapter.setLoadMore(new ILoadMore(){
            @Override
            public void onLoadMore() {
                if(items.size() <= 40){
                    items.add(null);
                    adapter.notifyItemInserted(items.size()-1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            items.remove(items.size()-1);
                            adapter.notifyItemRemoved(items.size());

                            int index = items.size();
                            int end = index+10;
                            for(int i = index; i < end; i++){
                                String name = UUID.randomUUID().toString();
                                Item item = new Item(name, name.length());
                                items.add(item);
                            }
                            adapter.notifyDataSetChanged();
                            adapter.setLoaded();
                        }
                    }, 3000);
                } else {
                    Toast.makeText(MainActivity.this, "Load data complete!", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    private void random10Data() {
        for(int i = 0; i < 10; i++){
            String name = UUID.randomUUID().toString();
            Item item = new Item(name, name.length());
            items.add(item);
        }
    }
}

