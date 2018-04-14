package com.example.denis.a2night;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

// import entidades

import com.example.denis.a2night.entidades.Usuario;

import java.util.HashMap;
import java.util.Map;

public class Registro02 extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText name;
    private EditText lastName;
    private EditText birthday;
    private DatabaseReference mDatabase;
    private String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro02);
        getSupportActionBar().hide();

        name = findViewById(R.id.registerName);
        lastName = findViewById(R.id.registerLastName);
        birthday = findViewById(R.id.registerBirthday);

        gender = "";
        // Inicializando autenticación
        mAuth = FirebaseAuth.getInstance();

        // Inicializando la instancia de Firebase
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //Eventos
        OnclickButton(R.id.btnRegistrarse);
        OnclickImageView(R.id.imgAtras02);


        OnclickRadioButton(R.id.radioButton);
        OnclickRadioButton(R.id.radioButton2);

        String encodeUser = EncodeString(mAuth.getCurrentUser().toString());

        Mensaje("Usuario: "+encodeUser);

    }

    //-------------------------------- Funcionalidad -----------------------------------------------------------

    private boolean validateForm() {
        boolean valid = true;

        String name = this.name.getText().toString();
        if (TextUtils.isEmpty(name)) {
            this.name.setError("Requerido");
            valid = false;
        } else {
            this.name.setError(null);
        }

        String lastName = this.lastName.getText().toString();
        if (TextUtils.isEmpty(lastName)) {
            this.lastName.setError("Requerido");
            valid = false;
        } else {
            this.lastName.setError(null);
        }

        String birthday = this.birthday.getText().toString();
        if (TextUtils.isEmpty(birthday)) {
            this.birthday.setError("Requerido");
            valid = false;
        } else {
            this.birthday.setError(null);
        }

        RadioButton rbtn = findViewById(R.id.radioButton2);
        if (gender == "") {
            rbtn.setError("Requerido");
            valid = false;
        } else {
           rbtn.setError(null);
        }

        return valid;

    }

    // ------------------------------- Paso de Actividades -----------------------------------------------------

    public void atras(){
        Intent intento2 = new Intent(getApplicationContext(), Registro01.class);
        startActivity(intento2);
    }

    public void registrar() {

        if(validateForm()) {
            String name = this.name.getText().toString();
            String lastName = this.lastName.getText().toString();
            String birthday = this.birthday.getText().toString();

            Map<String,String> usuario = new HashMap<>();
            usuario.put("nombre",name);
            usuario.put("apellidos",lastName);
            usuario.put("fechaNacimiento",birthday);
            usuario.put("confirmado","false");
            usuario.put("anonimo","false");
            usuario.put("genero",gender);


            Mensaje("Usuario: "+mAuth.getCurrentUser().toString());

            String encodeUser = EncodeString(mAuth.getCurrentUser().toString());


            mDatabase.child("Usuarios").child(encodeUser).setValue(usuario).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(@NonNull Void T) {
                    Mensaje("Usuario añadido correctamente");
                    Intent intento = new Intent(getApplicationContext(), Navegador.class);
                    startActivity(intento);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Mensaje("Error");
                }
            });
        }else{
            Mensaje("Complete los espacios en blanco");
        }
    }

    public static String EncodeString(String string) {
        return string.replace(".", ",");
    }

    public static String DecodeString(String string) {
        return string.replace(",", ".");
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

                    case R.id.btnRegistrarse:
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


    public void OnclickRadioButton(int ref) {
        View view =findViewById(ref);
        RadioButton miRadioButton = (RadioButton) view;

        miRadioButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // if(msg.equals("Texto")){Mensaje("Texto en el botón ");};
                switch (v.getId()) {

                    case R.id.radioButton:
                        gender = "F";
                        break;

                    case R.id.radioButton2:
                        gender = "M";

                        break;
                    default:break; }
            }
        });
    }// fin de Onclick




}
