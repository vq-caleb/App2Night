package com.example.denis.a2night.entidades;

/**
 * Created by Anthony on 8/4/2018.
 */

public class Publicacion {
    private String nombre;
    private String circle;
    private String imagen;
    private String horas;
    private String nombreD;
    private String descripcion;

    public Publicacion(String nombre, String circle, String imagen, String horas, String nombreD, String descripcion){
        this.nombre = nombre;
        this.circle = circle;
        this.imagen = imagen;
        this.horas = horas;
        this.nombreD = nombreD;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCircle() {
        return circle;
    }

    public void setCircle(String circle) {
        this.circle = circle;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String publicacion) {
        this.imagen = publicacion;
    }

    public String getHoras() {
        return horas;
    }

    public void setHoras(String horas) {
        this.horas = horas;
    }

    public String getNombreD() {
        return nombreD;
    }

    public void setNombreD(String nombreD) {
        this.nombreD = nombreD;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}