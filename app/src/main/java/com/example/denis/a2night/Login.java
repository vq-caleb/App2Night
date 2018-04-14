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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {

    private EditText email;
    private EditText password;
    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.loginEmail);
        password = (EditText) findViewById(R.id.loginPassword);
        getSupportActionBar().hide();

        // Inicializando autenticación
        mAuth = FirebaseAuth.getInstance();
        OnclickDelButton(R.id.btnIniciar);
    }

    @Override
    public void onStart() {
        super.onStart();
        //FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    private void cerrarSesion() {
        mAuth.signOut();
    }

    public void vuelveInicio(){
        Intent intento2 = new Intent(getApplicationContext(), Principal.class);
        startActivity(intento2);
    }

    public void iniciarSesion(){
        /*
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usuarios = database.getReference("Usuario");*/

        String email = this.email.getText().toString();
        String password = this.password.getText().toString();


        if(validateForm())
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Mensaje("Inicio de sesión corrrecto");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intento1 = new Intent(getApplicationContext(), Navegador.class);
                            startActivity(intento1);
                        } else {
                            // If sign in fails, display a message to the user.
                          Mensaje("Inicio de sesión erróneo");
                        }

                        // [START_EXCLUDE]
                        if (!task.isSuccessful()) {
                         Mensaje("Ha ocurrido un error");
                        }

                    }
                });
        else
            Mensaje("Complete los espacios en blanco");

    }

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



    public void Mensaje(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    };

    public void OnclickDelButton(int ref) {
        View view =findViewById(ref);
        Button miButton = (Button) view;

        miButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (v.getId()) {

                    case R.id.btnIniciar:
                        iniciarSesion();
                        break;

                    case R.id.btnInicio:
                        vuelveInicio();
                    default:break; }
            }
        });
    }// fin Onclick

}




