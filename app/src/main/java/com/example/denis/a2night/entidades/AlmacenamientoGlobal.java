package com.example.denis.a2night.entidades;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anthony on 12/4/2018.
 */

public class AlmacenamientoGlobal {
    List<Horario> horarioSemanal  = new ArrayList<Horario>();
    private Empresa empresa ;
    private static AlmacenamientoGlobal instance = null;

    protected AlmacenamientoGlobal() {
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
