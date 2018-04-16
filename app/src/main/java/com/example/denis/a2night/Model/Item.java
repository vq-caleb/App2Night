package com.example.denis.a2night.Model;

/**
 * Created by Minor on 15/04/2018.
 */

public class Item {
    private String name;
    private int length;

    public Item(String name, int length) {
        this.name = name;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
