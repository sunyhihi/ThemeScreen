package com.caiquocdat.theme.model;

import java.util.ArrayList;

public class ThemeModel {
    private int id;
    private String title;
    private String imagePath; // Chứa đường dẫn đến hình ảnh
    private ArrayList<FavouritesModel> listDetailImagePaths; // Chứa danh sách các đường dẫn đến hình ảnh chi tiết

    public ThemeModel(int id, String title, String imagePath, ArrayList<FavouritesModel> listDetailImagePaths) {
        this.id = id;
        this.title = title;
        this.imagePath = imagePath;
        this.listDetailImagePaths = listDetailImagePaths;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public ArrayList<FavouritesModel> getListDetailImagePaths() {
        return listDetailImagePaths;
    }

    public void setListDetailImagePaths(ArrayList<FavouritesModel> listDetailImagePaths) {
        this.listDetailImagePaths = listDetailImagePaths;
    }
}
