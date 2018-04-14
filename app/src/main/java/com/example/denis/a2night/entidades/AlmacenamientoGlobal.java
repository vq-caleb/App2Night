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
    private static AlmacenamientoGlobal instance = null;

    protected AlmacenamientoGlobal() {
/*
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        db.getReference().child("Bares")
                .orderByKey()
                .equalTo("frathousecr")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {
                        for (DataSnapshot child : dataSnapshot.getChildren())
                        {
                            String nombre = (String) child.child("nombre").getValue();
                            String etiquetaUbicacion = (String) child.child("etiquetaUbicacion").getValue();
                            String telefono1 = (String) child.child("telefono1").getValue();
                            String telefono2 = (String) child.child("telefono2" ).getValue();
                            String correo = (String) child.child("correo").getValue();
                            String descripcion = (String) child.child("descripcion").getValue();
                            String codigoVestimenta = (String) child.child("codigoVestimenta").getValue();
                            String entrada = (String) child.child("entrada").getValue();
                            String tipoNegocio = (String) child.child("tipoNegocio").getValue();
                            String paginaFacebook = (String) child.child("paginaFacebook").getValue();
                            String paginaInstagram = (String) child.child("paginaInstagram").getValue();
                            String paginaTwitter = (String) child.child("paginaTwitter").getValue();
                            int cantidadAsistentes =  Integer.parseInt((String) child.child("cantidadAsistentes").getValue());
                            List<Horario> horarioSemanal = null;
                            empresa = new Empresa( nombre, etiquetaUbicacion, cantidadAsistentes,  tipoNegocio,
                                                   paginaFacebook,  paginaInstagram, paginaTwitter,  telefono1,
                                                   telefono2,  correo, descripcion,  codigoVestimenta,  entrada, horarioSemanal);


                            // HERE WHAT CORRESPONDS TO JOIN
                            FirebaseDatabase db = FirebaseDatabase.getInstance();
                            db.getReference()
                                    .child("HorarioBar")
                                    .orderByKey()
                                    .equalTo("frathousecr")
                                    .addValueEventListener(
                                            new ValueEventListener()
                                            {
                                                @Override
                                                public void onDataChange(DataSnapshot dataSnapshot)
                                                {
                                                    List<Horario> horarioSemanal = new ArrayList<Horario>();

                                                    for (DataSnapshot child : dataSnapshot.getChildren()){
                                                        for(int i=0; i<7;i++){
                                                            String dia = (String) child.child(""+i).child("dia").getValue();
                                                            String horarioApertura = (String) child.child(""+i).child("horarioApertura").getValue();
                                                            String horarioCierre = (String) child.child(""+i).child("horarioCierre").getValue();
                                                            boolean abierto =  Boolean.parseBoolean((String)child.child(""+i).child("abierto").getValue());
                                                            Horario h = new Horario(dia,horarioApertura,horarioCierre,abierto);
                                                            horarioSemanal.add(h);
                                                        }
                                                        empresa.setHorarioSemanal(horarioSemanal);
                                                    }
                                                }

                                                @Override
                                                public void onCancelled(DatabaseError databaseError)
                                                {
                                                    Log.d("---OBJECT-----","-----ERROR---");
                                                }
                                            }
                                    );
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError)
                    {
                        Log.d("---OBJECT-----","-----ERROR2-----");
                    }
                }
                );
*/


/*
        horarioSemanal.add(new Horario("Lunes","08:00","03:00"));
        horarioSemanal.add(new Horario("Martes","08:00","03:00"));
        horarioSemanal.add(new Horario("Miercoles","08:00","03:00"));
        horarioSemanal.add(new Horario("Jueves","08:00","03:00"));
        horarioSemanal.add(new Horario("Viernes","08:00","03:00"));
        horarioSemanal.add(new Horario("Sabado","08:00","03:00"));
        horarioSemanal.add(new Horario(false));
        empresa = new Empresa("Frat House", "La Calle",0,
                "Bar","facebook.com/frathousecr", "instagram.com/frahhousecr",
                "twitter.com/frathousecr","2257-0582","8445-6402",
                "frathousecr@gmail.com","","Casual","₡ 2000",horarioSemanal);

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        Map<String,String> bar = new HashMap<>();
        bar.put("nombre",empresa.getNombre());
        bar.put("etiquetaUbicacion",empresa.getEtiquetaUbicacion());
        bar.put("cantidadAsistentes",""+empresa.getCantidadAsistentes());
        bar.put("tipoNegocio",empresa.getTipoNegocio());
        bar.put("paginaFacebook",empresa.getPaginaFacebook());
        bar.put("paginaTwitter",""+empresa.getPaginaTwitter());
        bar.put("paginaInstagram",empresa.getPaginaInstagram());
        bar.put("telefono1",empresa.getTelefono1());
        bar.put("telefono2",empresa.getTelefono2());
        bar.put("correo",empresa.getCorreo());
        bar.put("descripcion",empresa.getDescripcion());
        bar.put("codigoVestimenta",empresa.getCodigoVestimenta());
        bar.put("entrada",empresa.getEntrada());

        db.getReference().child("Bares").child("frathousecr").setValue(bar).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(@NonNull Void T) {
                Log.d("---","---AÑADIDO---------");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("---","---ERROR---------");
            }
        });
*/
        //Map<String,String> horario = new HashMap<>();
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


}
