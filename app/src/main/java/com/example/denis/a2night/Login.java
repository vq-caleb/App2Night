package com.example.denis.a2night;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        Button MiBoton = (Button) findViewById(R.id.btnIniciar);
        MiBoton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                Intent intento1 = new Intent(getApplicationContext(), Navegador.class);
                startActivity(intento1);
            }
        });

        ImageView MiImageView = (ImageView) findViewById(R.id.imgAtrasBuscar);
        MiImageView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                Intent intento2 = new Intent(getApplicationContext(), Principal.class);
                startActivity(intento2);
            }
        });

    }

}
