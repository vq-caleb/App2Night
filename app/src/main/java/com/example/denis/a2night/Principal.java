package com.example.denis.a2night;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        getSupportActionBar().hide();

        OnclickDelButton(R.id.btnLogin);
        OnclickDelButton(R.id.btnRegistro);

    }

    public void OnclickDelButton(int ref) {
        View view =findViewById(ref);
        Button miButton = (Button) view;
        miButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnLogin:
                        Intent intento1 = new Intent(getApplicationContext(), Login.class);
                        startActivity(intento1);
                        break;

                    case R.id.btnRegistro:
                        Intent intento2 = new Intent(getApplicationContext(), Registro01.class);
                        startActivity(intento2);
                        break;

                    default:break; }// fin de casos
            }// fin del onclick
        });
    }// fin de OnclickDelButton

}
