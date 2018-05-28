package com.example.denis.a2night.Model;

import com.bumptech.glide.request.target.ImageViewTarget;

/**
 * Created by Minor on 15/04/2018.
 */

public class Item {
    private String name;
    private String circleView;
    private String length;
    private String horas;
    private String nombreD;
    private String descripcion;

    public Item(String name, String circleView, String length, String horas, String nombreD, String descripcion) {
        this.name = name;
        this.circleView = circleView;
        this.length = length;
        this.horas = horas;
        this.nombreD = nombreD;
        this.descripcion = descripcion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getCircleView() {
        return circleView;
    }

    public void setCircleView(String circleView) {
        this.circleView = circleView;
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