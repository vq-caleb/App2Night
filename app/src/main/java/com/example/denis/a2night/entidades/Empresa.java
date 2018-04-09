package com.example.denis.a2night.entidades;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anthony on 7/4/2018.
 */

public class Empresa {
    private String nombre, etiquetaUbicacion, telefono1, telefono2, correo, descripcion, codigoVestimenta, entrada,
            tipoNegocio, paginaFacebook, paginaInstagram, paginaTwitter  ;
    private int cantidadSeguidores, cantidadAsistentes, calificacion;
    private List<Publicacion> publicaciones = new ArrayList<Publicacion>();
    private List<Producto> menu = new ArrayList<Producto>();
    // ubicacion googleMaps, listaDeUsuariosSeguidores, imagenPortada, imagenPerfil, horario

    public Empresa(String nombre, String etiquetaUbicacion, int cantidadSeguidores, int cantidadAsistentes) {
        this.nombre = nombre;
        this.etiquetaUbicacion = etiquetaUbicacion;
        this.cantidadSeguidores = cantidadSeguidores;
        this.cantidadAsistentes = cantidadAsistentes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEtiquetaUbicacion() {
        return etiquetaUbicacion;
    }

    public void setEtiquetaUbicacion(String etiquetaUbicacion) {
        this.etiquetaUbicacion = etiquetaUbicacion;
    }

    public int getCantidadSeguidores() {
        return cantidadSeguidores;
    }

    public void setCantidadSeguidores(int cantidadSeguidores) {
        this.cantidadSeguidores = cantidadSeguidores;
    }

    public int getCantidadAsistentes() {
        return cantidadAsistentes;
    }

    public void setCantidadAsistentes(int cantidadAsistentes) {
        this.cantidadAsistentes = cantidadAsistentes;
    }

    public String getCantidadSeguidoresFormartoK(){
        String temp = Integer.toString(this.cantidadSeguidores);
        String nuevoFormato = "";
        if (this.cantidadSeguidores < 1000){
            return Integer.toString(this.cantidadSeguidores);
        } else if (this.cantidadSeguidores < 10000){
            nuevoFormato = temp.substring(0,temp.length()-2);
            nuevoFormato = nuevoFormato.charAt(0)+","+nuevoFormato.charAt(1);
        } else if (this.cantidadSeguidores < 1000000){
            nuevoFormato = temp.substring(0,temp.length()-3);
        }
        nuevoFormato = nuevoFormato.concat("k");
        return nuevoFormato;
    }
}
