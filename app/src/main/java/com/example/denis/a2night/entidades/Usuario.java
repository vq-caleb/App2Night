package com.example.denis.a2night.entidades;

/**
<<<<<<< HEAD
 * Created by Steven on 13/04/2018.
 */

public class Usuario {
    private String correo;
    private String nombre;
    private String apellidos;
    private boolean anonimo;
    private boolean confirmado;
    private String fechaNacimiento;
    private String genero;

    public Usuario() {

    }

    public Usuario(String correo, String nombre, String apellidos, boolean anonimo, boolean confirmado, String fechaNacimiento, String genero) {
        this.correo = correo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.anonimo = anonimo;
        this.confirmado = confirmado;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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

    public boolean isAnonimo() {
        return anonimo;
    }

    public void setAnonimo(boolean anonimo) {
        this.anonimo = anonimo;
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public void setConfirmado(boolean confirmado) {
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
