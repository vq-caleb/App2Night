package com.example.denis.a2night;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Registro01 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro01);
        getSupportActionBar().hide();

        Button MiBoton = (Button) findViewById(R.id.btnSiguiente);

        MiBoton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intento = new Intent(getApplicationContext(), Registro02.class);
                startActivity(intento);
            }
        });

        ImageView MiImageView = (ImageView) findViewById(R.id.imgAtras02);
        MiImageView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                Intent intento2 = new Intent(getApplicationContext(), Principal.class);
                startActivity(intento2);
            }
        });

    }
}
