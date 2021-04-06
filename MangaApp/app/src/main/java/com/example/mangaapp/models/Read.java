package com.example.mangaapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Read implements Serializable {
    @SerializedName("n")
    @Expose
    private Integer n;
    @SerializedName("w")
    @Expose
    private String w;
    @SerializedName("h")
    @Expose
    private String h;
    @SerializedName("u")
    @Expose
    private String u;

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }

    public String getW() {
        return w;
    }

    public void setW(String w) {
        this.w = w;
    }

    public String getH() {
        return h;
    }

    public void setH(String h) {
        this.h = h;
    }

    public String getU() {
        return u;
    }

    public void setU(String u) {
        this.u = u;
    }

}
