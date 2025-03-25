package com.example.tpstarsandroid.beans;

public class Star {
    private int id;
    private String name;
    private int img;
    private float star;

    private static int comp;

    public Star(String name, int img, float star) {
        this.id = ++comp;
        this.name = name;
        this.img = img;
        this.star = star;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public float getStar() {
        return star;
    }

    public void setStar(float star) {
        this.star = star;
    }
}
