package com.example.mangaapp.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class MangaDetail implements Serializable {
    private String name, image, author, artist, genre, status, alternative, rating, description;
    private Float ratingbar;
    private HashMap<Version, List<Chapter>> listMap = new HashMap<>();

    public MangaDetail(String image, String author, String artist, String genre, String status, String alternative, String rating) {
        this.image = image;
        this.author = author;
        this.artist = artist;
        this.genre = genre;
        this.status = status;
        this.alternative = alternative;
        this.rating = rating;
    }

    public HashMap<Version, List<Chapter>> getListMap() {
        return listMap;
    }

    public void setListMap(HashMap<Version, List<Chapter>> listMap) {
        this.listMap = listMap;
    }

    public Float getRatingbar() {
        return ratingbar;
    }

    public void setRatingbar(Float ratingbar) {
        this.ratingbar = ratingbar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAlternative() {
        return alternative;
    }

    public void setAlternative(String alternative) {
        this.alternative = alternative;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
