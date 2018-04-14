package com.example.denis.a2night;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registro01 extends AppCompatActivity {

    private EditText email;
    private EditText password;
    // Firebase Authentication
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro01);
        getSupportActionBar().hide();

        email = (EditText) findViewById(R.id.registerEmail);
        password = (EditText) findViewById(R.id.registerPassword);

        // Inicializando autenticación
        mAuth = FirebaseAuth.getInstance();

        //Eventos
        OnclickButton(R.id.btnSiguiente);
        OnclickImageView(R.id.imgAtras02);

    }

    //-------------------------------- Funcionalidad -----------------------------------------------------------

    private boolean validateForm() {
        boolean valid = true;

        String email = this.email.getText().toString();
        if (TextUtils.isEmpty(email)) {
            this.email.setError("Requerido.");
            valid = false;
        } else {
            this.email.setError(null);
        }

        String password = this.password.getText().toString();
        if (TextUtils.isEmpty(password)) {
            this.password.setError("Requerido.");
            valid = false;
        } else {
            this.password.setError(null);
        }

        return valid;
    }

    public void registrar(){
        String email = this.email.getText().toString();
        String password = this.password.getText().toString();

        if (validateForm())
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                           Mensaje("Registro Correcto");
                           FirebaseUser user = mAuth.getCurrentUser();
                           registro02();
                        } else {
                            Mensaje("Error");
                        }
                    }
                });
    }


    // ------------------------------- Paso de Actividades -----------------------------------------------------

    public void atras(){
        Intent intento2 = new Intent(getApplicationContext(), Principal.class);
        startActivity(intento2);
    }

    public void registro02() {
        Intent intento = new Intent(getApplicationContext(), Registro02.class);
        startActivity(intento);
    }

    //------------------------------- Alertas ------------------------------------------------------------------
    public void Mensaje(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    };


    //------------------------------- Listeners ----------------------------------------------------------------
    public void OnclickButton(int ref) {
        View view =findViewById(ref);
        Button miButton = (Button) view;

        miButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (v.getId()) {

                    case R.id.btnSiguiente:
                        registrar();
                        break;

                    default:break; }
            }
        });
    }// fin OnclickBT


    public void OnclickImageView(int ref) {
        View view =findViewById(ref);
        ImageView miImageView = (ImageView) view;

        miImageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.imgAtras02:
                        atras();
                      break;

                    default:break;
                }
            }
        });
    }// fin de OnclickIV

}
