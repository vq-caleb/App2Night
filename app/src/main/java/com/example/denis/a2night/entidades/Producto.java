package com.example.denis.a2night.entidades;

/**
 * Created by Anthony on 8/4/2018.
 */

public class Producto {
    private String atributo01;
    private String atributo02;
    private int NumDibujo;
    public Producto(String atributo01, String atributo02, int NumDibujo){
        super();
        this.atributo01 = atributo01;
        this.atributo02 = atributo02;
        this.NumDibujo = NumDibujo;
    }
    public String getAtributo01() {
        return atributo01;
    }
    public String getAtributo02() {
        return atributo02;
    }
    public int getNumDibujo() {
        return NumDibujo;
    }
}
