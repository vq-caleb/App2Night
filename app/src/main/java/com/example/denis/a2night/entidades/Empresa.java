package com.example.denis.a2night.entidades;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anthony on 7/4/2018.
 */

public class Empresa {
    private String nombre;
    private String etiquetaUbicacion;
    private String telefono1;
    private String telefono2;
    private String correo;
    private String descripcion;
    private String codigoVestimenta;
    private String entrada;
    private String tipoNegocio;
    private String paginaFacebook;
    private String paginaInstagram;
    private String paginaTwitter;
    private int cantidadAsistentes;
    private List<Publicacion> publicaciones = new ArrayList<Publicacion>();
    private List<Producto> menu = new ArrayList<Producto>();
    private List<Usuario> seguidores = new ArrayList<Usuario>();
    private List<Calificacion> calificaciones = new ArrayList<Calificacion>();
    private List<Horario> horarioSemanal;

    // ubicacion googleMaps, listaDeUsuariosSeguidores, imagenPortada, imagenPerfil, horario

    public Empresa(String nombre, String etiquetaUbicacion, int cantidadAsistentes, String tipoNegocio,
        String paginaFacebook, String paginaInstagram,String paginaTwitter, String telefono1, String telefono2, String correo,
                   String descripcion, String codigoVestimenta, String entrada, List<Horario> horarioSemanal) {
        this.nombre = nombre;
        this.etiquetaUbicacion = etiquetaUbicacion;
        this.cantidadAsistentes = cantidadAsistentes;
        this.telefono1 = telefono1;
        this.telefono2 = telefono2;
        this.correo = correo;
        this.descripcion = descripcion;
        this.codigoVestimenta = codigoVestimenta;
        this.entrada = entrada;
        this.tipoNegocio = tipoNegocio;
        this.paginaFacebook = paginaFacebook;
        this.paginaInstagram = paginaInstagram;
        this.paginaTwitter = paginaTwitter;
        this.horarioSemanal = horarioSemanal;
        this.calificaciones.add(new Calificacion(new Usuario("anthony"),4.5f));
        this.calificaciones.add(new Calificacion(new Usuario("caleb"),4.5f));
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
        return this.seguidores.size();
    }

    public int getCantidadAsistentes() {
        return cantidadAsistentes;
    }

    public void setCantidadAsistentes(int cantidadAsistentes) {
        this.cantidadAsistentes = cantidadAsistentes;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigoVestimenta() {
        return codigoVestimenta;
    }

    public void setCodigoVestimenta(String codigoVestimenta) {
        this.codigoVestimenta = codigoVestimenta;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getTipoNegocio() {
        return tipoNegocio;
    }

    public void setTipoNegocio(String tipoNegocio) {
        this.tipoNegocio = tipoNegocio;
    }

    public String getPaginaFacebook() {
        return paginaFacebook;
    }

    public void setPaginaFacebook(String paginaFacebook) {
        this.paginaFacebook = paginaFacebook;
    }

    public String getPaginaInstagram() {
        return paginaInstagram;
    }

    public void setPaginaInstagram(String paginaInstagram) {
        this.paginaInstagram = paginaInstagram;
    }

    public String getPaginaTwitter() {
        return paginaTwitter;
    }

    public void setPaginaTwitter(String paginaTwitter) {
        this.paginaTwitter = paginaTwitter;
    }

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public List<Producto> getMenu() {
        return menu;
    }

    public void setMenu(List<Producto> menu) {
        this.menu = menu;
    }

    public List<Usuario> getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(List<Usuario> seguidores) {
        this.seguidores = seguidores;
    }

    public List<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(List<Calificacion> calificaciones) {
        this.calificaciones = calificaciones;
    }

    public List<Horario> getHorarioSemanal() {
        return horarioSemanal;
    }

    public void setHorarioSemanal(List<Horario> horarioSemanal) {
        this.horarioSemanal = horarioSemanal;
    }

    public float getCalificacion() {
        float temp = 0f;
        for (Calificacion x: this.calificaciones) {
            temp += x.getCalificacion();
        }
        if (temp != 0f){ return temp/this.calificaciones.size(); } else { return 0f;}
    }

    public String getCantidadSeguidoresFormartoK(){
        int cantidadSeguidores = this.seguidores.size();
        String temp = Integer.toString(cantidadSeguidores);
        String nuevoFormato = "";
        if (cantidadSeguidores < 1000){
            return Integer.toString(cantidadSeguidores);
        } else if (cantidadSeguidores < 10000){
            nuevoFormato = temp.substring(0,temp.length()-2);
            nuevoFormato = nuevoFormato.charAt(0)+","+nuevoFormato.charAt(1);
        } else if (cantidadSeguidores < 1000000){
            nuevoFormato = temp.substring(0,temp.length()-3);
        }
        nuevoFormato = nuevoFormato.concat("k");
        return nuevoFormato;
    }
}
