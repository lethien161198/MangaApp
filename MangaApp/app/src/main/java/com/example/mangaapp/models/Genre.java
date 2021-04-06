package com.example.mangaapp.models;

public class Genre {
    private String nameGen;
    private boolean isChecked;

    public String getNameGen() {
        return nameGen;
    }

    public void setNameGen(String nameGen) {
        this.nameGen = nameGen;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public Genre(String nameGen, boolean isChecked) {
        this.nameGen = nameGen;
        this.isChecked = isChecked;
    }
}
