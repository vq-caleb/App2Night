package com.example.denis.a2night.entidades;

/**
 * Created by Anthony on 12/4/2018.
 */

public class Calificacion {
    private Usuario usuario;
    private float calificacion;

    public Calificacion(Usuario usuario, float calificacion) {
        this.usuario = usuario;
        this.calificacion = calificacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }
}
