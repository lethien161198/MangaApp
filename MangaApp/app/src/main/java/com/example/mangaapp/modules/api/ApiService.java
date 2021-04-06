package com.example.mangaapp.modules.api;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiService {
    @GET("/latest")
    Observable<String> getLatest();
    @GET("/")
    Observable<String> getUpdates();
    @GET()
    Observable<String> getDetail(@Url String url);

}
