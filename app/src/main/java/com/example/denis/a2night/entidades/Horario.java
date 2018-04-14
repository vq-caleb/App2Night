package com.example.denis.a2night.entidades;

import java.text.SimpleDateFormat;

/**
 * Created by Anthony on 13/4/2018.
 */

public class Horario {
    private String dia, horaAbierto, horaCierre;
    private boolean estaAbierto;

    public Horario(String dia, String horaAbierto, String horaCierre) {
        this.dia = dia;
        this.horaAbierto = horaAbierto;
        this.horaCierre = horaCierre;
        this.estaAbierto = true;
    }

    public Horario(boolean estaAbierto) {
        if (!estaAbierto){
            this.estaAbierto = false;
        } else {
            this.dia = " ";
            this.horaAbierto = " ";
            this.horaCierre = " ";
            this.estaAbierto = true;
        }
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHoraAbierto() {
        return horaAbierto;
    }

    public void setHoraAbierto(String horaAbierto) {
        this.horaAbierto = horaAbierto;
    }

    public String getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(String horaCierre) {
        this.horaCierre = horaCierre;
    }

    public boolean isEstaAbierto() {
        return estaAbierto;
    }

    public void setEstaAbierto(boolean estaAbierto) {
        this.estaAbierto = estaAbierto;
    }

    public int getSoloHoraAbierto(){
        return Integer.parseInt(this.horaAbierto.toString().substring(0,2));
    }

    public int getSoloMinAbierto(){
        return Integer.parseInt(this.horaAbierto.toString().substring(2,4));
    }

    public int getSoloHoraCierre(){
        return Integer.parseInt(this.horaCierre.toString().substring(0,2));
    }

    public int getSoloMinCierre(){
        return Integer.parseInt(this.horaCierre.toString().substring(2,4));
    }

    public String toString(){
        if (estaAbierto){
            return this.dia+": "+this.horaAbierto+" - "+this.horaCierre;
        }
        return "CERRADO";
    }
}
