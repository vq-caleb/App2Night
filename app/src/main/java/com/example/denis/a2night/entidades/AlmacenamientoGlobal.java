package com.example.denis.a2night.entidades;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.example.denis.a2night.Navegador;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Anthony on 12/4/2018.
 */

public class AlmacenamientoGlobal {
    private Empresa empresa ;
    private String idEmpresaActual;
    private static AlmacenamientoGlobal instance = null;

    protected AlmacenamientoGlobal() {
    }

    public static AlmacenamientoGlobal getInstance() {
        if(instance == null) {instance = new AlmacenamientoGlobal(); }
        return instance;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getIdEmpresaActual() {return idEmpresaActual;}

    public void setIdEmpresaActual(String idEmpresaActual) {this.idEmpresaActual = idEmpresaActual;}
}
