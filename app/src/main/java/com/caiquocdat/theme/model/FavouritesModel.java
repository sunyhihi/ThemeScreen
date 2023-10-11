package com.caiquocdat.theme.model;

public class FavouritesModel {
    private int id;
    private String check;
    private String path;

    public FavouritesModel(int id, String check, String path) {
        this.id = id;
        this.check = check;
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
