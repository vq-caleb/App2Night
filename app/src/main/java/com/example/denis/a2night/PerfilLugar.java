package com.example.denis.a2night;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.denis.a2night.entidades.Empresa;

public class PerfilLugar extends AppCompatActivity {
    static Empresa empresa = new Empresa("Frat House", "La Calle",1450, 230);
    TextView nombreEmpresa, etiquetaUbicacion, cantidadSeguidores, cantidadAsistentes;
    ImageView fotoPerfilEmpresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_lugar);
        this.alambrarVariables();
        this.completarInformacion();
    }

    public void completarInformacion(){
        this.nombreEmpresa.setText(this.empresa.getNombre());
        this.etiquetaUbicacion.setText(this.empresa.getEtiquetaUbicacion());
        this.cantidadSeguidores.setText(this.empresa.getCantidadSeguidoresFormartoK());
        this.cantidadAsistentes.setText(Integer.toString(this.empresa.getCantidadAsistentes()));
    }

    public void alambrarVariables(){
        this.nombreEmpresa =  (TextView) findViewById(R.id.nombre);
        this.cantidadSeguidores =  (TextView) findViewById(R.id.cantidadSeguidores);
        this.cantidadAsistentes =  (TextView) findViewById(R.id.cantidadAsistentes);
        this.etiquetaUbicacion = (TextView) findViewById(R.id.etiquetaUbicacion);
    }

    public void Mensaje(String msg){
        View v1 = getWindow().getDecorView().getRootView();
        AlertDialog.Builder builder1 = new AlertDialog.Builder( v1.getContext());
        builder1.setMessage(msg);
        builder1.setCancelable(true);
        builder1.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {} });
        AlertDialog alert11 = builder1.create();
        alert11.show();
        ;};
}
