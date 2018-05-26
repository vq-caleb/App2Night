package com.example.denis.a2night.entidades;

/**
 * Created by Anthony on 8/4/2018.
 */

public class Producto {
    private String nombre;
    private String precio;
    private String imagen;
    private byte[] imagen2;
   /* public Producto(String nombre, String precio, String imagen, byte[] imagen2){
        super();
        this.nombre = atributonombre01;
        this.precio = precio;
        this.imagen = imagen;
        this.imagen2 = imagen2;
    }*/

   public Producto(String nombre, String precio, String imagen){
       this.nombre = nombre;
       this.precio = precio;
       this.imagen = imagen;
   }

    public Producto(String nombre, String precio, String imagen, byte[] imagen2){
        super();
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
        this.imagen2 = imagen2;
    }

    public String getNombre() {
        return nombre;
    }
    public String getPrecio() {
        return precio;
    }
    public String getImagen() {
        return imagen;
    }
    public byte[] getImagen2(){return imagen2;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setPrecio(String precio) {this.precio = precio;}
    public void setImagen2(byte[] imagen2){this.imagen2 = imagen2;}
}
