package com.example.denis.a2night.entidades;

/**
 * Created by Anthony on 8/4/2018.
 */

public class Producto {
    private String atributo01;
    private String atributo02;
    private String imagen;
    private byte[] imagen2;
   /* public Producto(String atributo01, String atributo02, String imagen, byte[] imagen2){
        super();
        this.atributo01 = atributo01;
        this.atributo02 = atributo02;
        this.imagen = imagen;
        this.imagen2 = imagen2;
    }*/

   public Producto(String atributo01, String atributo02, String imagen){
       this.atributo01 = atributo01;
       this.atributo02 = atributo02;
       this.imagen = imagen;
   }

    public Producto(String atributo01, String atributo02, String imagen, byte[] imagen2){
        super();
        this.atributo01 = atributo01;
        this.atributo02 = atributo02;
        this.imagen = imagen;
        this.imagen2 = imagen2;
    }

    public String getAtributo01() {
        return atributo01;
    }
    public String getAtributo02() {
        return atributo02;
    }
    public String getImagen() {
        return imagen;
    }
    public byte[] getImagen2(){return imagen2;}
    public void setAtributo01(String atributo01) {this.atributo01 = atributo01;}
    public void setAtributo02(String atributo02) {this.atributo02 = atributo02;}
    public void setImagen2(byte[] imagen2){this.imagen2 = imagen2;}
}
