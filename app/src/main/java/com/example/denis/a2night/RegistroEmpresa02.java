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

import java.util.HashMap;
import java.util.Map;

public class RegistroEmpresa02 extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText name;
    private EditText description;
    private EditText inaugurationDate;
    private EditText companyID;
    private DatabaseReference mDatabase;
    private String companyType;
    Map<String,String> empresa = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_empresa02);
        getSupportActionBar().hide();

        name = findViewById(R.id.companyRegisterName);
        inaugurationDate = findViewById(R.id.companyRegisterInaugurationDate);
        companyID = findViewById(R.id.companyRegisterPK);

        companyType = "";
        // Inicializando autenticación
        mAuth = FirebaseAuth.getInstance();

        // Inicializando la instancia de Firebase
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //Eventos
        OnclickButton(R.id.btnCompanyRegister2);
        OnclickImageView(R.id.imgAtras02);


        OnclickRadioButton(R.id.radioButton3);
        OnclickRadioButton(R.id.radioButton4);

        String encodeUser = EncodeString(mAuth.getCurrentUser().toString());

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

        String description = this.description.getText().toString();
        if (TextUtils.isEmpty(description)) {
            this.description.setError("Requerido");
            valid = false;
        } else {
            this.description.setError(null);
        }

        String inaugurationDate = this.inaugurationDate.getText().toString();
        if (TextUtils.isEmpty(inaugurationDate)) {
            this.inaugurationDate.setError("Requerido");
            valid = false;
        } else {
            this.inaugurationDate.setError(null);
        }


        RadioButton rbtn = findViewById(R.id.radioButton3);
        if (companyType == "") {
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
            String lastName = this.name.getText().toString();
            String birthday = this.description.getText().toString();
            final String companyID = this.companyID.getText().toString();

            empresa.put("cantidadAsistentes","0");
            empresa.put("codigoVestimenta","");
            empresa.put("correo","");
            empresa.put("descripcion",lastName);
            empresa.put("entrada","");
            empresa.put("etiquetaUbicacion","");
            empresa.put("fechaInauguracion","");
            empresa.put("nombre",name);
            empresa.put("paginaFacebook","");
            empresa.put("paginaInstagram","");
            empresa.put("paginaTwitter","");
            empresa.put("telefono1","");
            empresa.put("telefono2","");
            empresa.put("tipoNegocio",companyType);
            String encodeUser = EncodeString(mAuth.getCurrentUser().toString());

            Map<String,String> usuario = new HashMap<>();
            usuario.put("bar",companyID);
            usuario.put("confirmado","false");


            mDatabase.child("Usuarios").child(encodeUser).setValue(usuario).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(@NonNull Void T) {
                    mDatabase.child("Bares").child(companyID).setValue(empresa).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(@NonNull Void T) {
                            Mensaje("Empresa añadida correctamente");
                            Intent intento = new Intent(getApplicationContext(), Navegador.class);
                            startActivity(intento);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Mensaje("Empresa existente");
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Mensaje("Empresa Existente");
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

                    case R.id.btnCompanyRegister2:
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

                    case R.id.radioButton3:
                        companyType = "B";
                        break;

                    case R.id.radioButton4:
                        companyType = "R";

                        break;
                    default:break; }
            }
        });
    }// fin de Onclick




}
