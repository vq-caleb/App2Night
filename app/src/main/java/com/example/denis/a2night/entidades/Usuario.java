package com.example.denis.a2night.entidades;

/**
 * Created by Anthony on 12/4/2018.
 */

public class Usuario {
    private String correo;

    public Usuario(String correo) {
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
