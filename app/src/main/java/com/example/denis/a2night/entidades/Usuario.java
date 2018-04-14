package com.example.denis.a2night.entidades;

/**
 * Created by Steven on 13/04/2018.
 */

public class Usuario {
    public String nombre;
    public String apellidos;
    public String anonimo;
    public String confirmado;
    public String fechaNacimiento;
    public String genero;


    public Usuario() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Usuario(String nombre, String apellidos, String anonimo, String confirmado, String fechaNacimiento, String genero) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.anonimo = anonimo;
        this.confirmado = confirmado;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getAnonimo() {
        return anonimo;
    }

    public void setAnonimo(String anonimo) {
        this.anonimo = anonimo;
    }

    public String getConfirmado() {
        return confirmado;
    }

    public void setConfirmado(String confirmado) {
        this.confirmado = confirmado;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
