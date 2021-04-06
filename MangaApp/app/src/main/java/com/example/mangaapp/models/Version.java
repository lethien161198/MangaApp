package com.example.mangaapp.models;

import java.io.Serializable;

public class Version implements Serializable {
    private long id;
    private String nameGroup;

    public Version(long id, String nameGroup) {
        this.id = id;
        this.nameGroup = nameGroup;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }
}
