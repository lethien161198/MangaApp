package com.example.mangaapp.modules.api;

import android.util.Log;

import com.example.mangaapp.common.Utilities;
import com.example.mangaapp.models.Chapter;
import com.example.mangaapp.models.Genre;
import com.example.mangaapp.models.Manga;
import com.example.mangaapp.models.MangaDetail;
import com.example.mangaapp.models.Read;
import com.example.mangaapp.models.Result;
import com.example.mangaapp.models.Version;
import com.example.mangaapp.modules.database.MangaDatabase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;

public class Client {
    public static Observable<Result> getLatestManga(int page) {
        List<Manga> mangaList = new ArrayList<>();
        return Observable.create((ObservableOnSubscribe<Result>) emitter -> {
            Document doc = null;
            try {
                doc = Jsoup.connect(Utilities.baseUrl + Utilities.URL_LATEST + page).userAgent("Chrome").get();


                Elements elements = doc.getElementsByClass("ls1").first().children();
                Elements elementPage = doc.getElementById("paging-bar").children().select("ul").select("select").select("option");
                int size = elements.size();

                for (int i = 0; i < size; i++) {


                    String title = elements.select("ul").select("h3")
                            .select("a")
                            .eq(i)
                            .text();

                    String image = elements.select("a.cover").select("img")
                            .eq(i)
                            .attr("src");
                    Elements elements1 = elements.eq(i).select("li");
                    String chapter = elements1.select("span").select("a.visited").eq(0)
                            .text();

                    String url = elements.select("ul").select("h3")
                            .select("a")
                            .eq(i)
                            .attr("href");

                    String urlChapter = elements1.select("span").select("a.visited").eq(0)
                            .attr("href");
                    Manga manga = new Manga(title, image, chapter);

                    manga.setUrl(url);
                    manga.setUrlChapter(urlChapter);
                    manga.setFavourite(false);
                    List<Manga> lista = MangaDatabase.getInstance().mangaDAO().getAll();
                    if (lista != null) {
                        for (Manga a : lista) {
                            if (manga.getName().equals(a.getName())) {
                                manga.setFavourite(true);
                                break;
                            } else manga.setFavourite(false);
                        }
                    } else manga.setFavourite(false);

                    mangaList.add(manga);

                }
                int page1 = elementPage.size();
                Log.d("1234", "totalPage: " + page1);
                Result result = new Result(mangaList, page1);
                emitter.onNext(result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static Observable<List<Manga>> getPopularManga() {
        List<Manga> mangaList = new ArrayList<>();
        return Observable.create((ObservableOnSubscribe<List<Manga>>) emitter -> {
            Document doc = Jsoup.connect(Utilities.baseUrl).userAgent("Chrome").get();

            Elements elements = doc.getElementsByClass("top").select("ul").select("li");

            int size = elements.size();
            for (int i = 0; i < size; i++) {

                String title = elements.select("h3")
                        .select("a")
                        .eq(i)
                        .text();

                String image = elements.select("a").select("img")
                        .eq(i)
                        .attr("src");
                String chapter = elements
                        .select("a.visited")
                        .eq(i)
                        .text();

                String url = elements.select("h3")
                        .select("a")
                        .eq(i)
                        .attr("href");

                String urlChapter = elements
                        .select("a.visited")
                        .eq(i)
                        .attr("href");

                Manga manga = new Manga(title, image, chapter);

                manga.setFavourite(false);

                List<Manga> lista = MangaDatabase.getInstance().mangaDAO().getAll();
                if (lista != null) {
                    for (Manga a : lista) {
                        if (manga.getName().equals(a.getName())) {
                            manga.setFavourite(true);
                            break;
                        } else manga.setFavourite(false);
                    }
                } else manga.setFavourite(false);

                manga.setUrl(url);
                manga.setUrlChapter(urlChapter);
                mangaList.add(manga);
                Log.d("123", "getPopularManga: "+ manga.getUrl());
            }
            emitter.onNext(mangaList);
        });
    }

    public static Observable<List<Manga>> getUpdateManga() {
        List<Manga> mangaList = new ArrayList<>();
        return Observable.create((ObservableOnSubscribe<List<Manga>>) emitter -> {
            Document doc = Jsoup.connect(Utilities.baseUrl).userAgent("Chrome").get();

            Elements elements = doc.getElementsByClass("screen thm-list").select("li");

            int size = elements.size();
            for (int i = 0; i < size; i++) {


                String title = elements.select("h3")
                        .select("a")
                        .eq(i)
                        .text();

                String image = elements.select("a").select("img")
                        .eq(i)
                        .attr("src");
                String chapter = elements.select("h4")
                        .select("a.visited")
                        .eq(i)
                        .text();

                String url = elements.select("h3")
                        .select("a")
                        .eq(i)
                        .attr("href");

                String urlChapter = elements.select("h4")
                        .select("a.visited")
                        .eq(i)
                        .attr("href");
                Manga manga = new Manga(title, image, chapter);


                manga.setFavourite(false);

                List<Manga> lista = MangaDatabase.getInstance().mangaDAO().getAll();
                if (lista != null) {
                    for (Manga a : lista) {
                        if (manga.getName().equals(a.getName())) {
                            manga.setFavourite(true);
                            break;
                        } else manga.setFavourite(false);
                    }
                } else manga.setFavourite(false);


                manga.setUrl(url);
                manga.setUrlChapter(urlChapter);
                mangaList.add(manga);

            }
            emitter.onNext(mangaList);
        });
    }

    public static Observable<MangaDetail> getMangaDetail(String url) {
        return Observable.create(new ObservableOnSubscribe<MangaDetail>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<MangaDetail> emitter) throws Exception {
                Document doc = Jsoup.connect(Utilities.baseUrl + url).userAgent("Chrome").get();

                Elements elements = doc.getElementsByClass("w-100 cover").first().children();

                Elements elementInfo = doc.getElementsByClass("attr").first().children().select("tbody").select("tr");
                String image = elements.select("img").eq(0).attr("src");
                String rating = elementInfo.eq(1).select("td").text();
                String alternative = elementInfo.eq(3).select("td").text();
                String author = elementInfo.eq(4).select("td").text();
                String artist = elementInfo.eq(5).select("td").text();
                String genre = elementInfo.eq(6).select("td").text();
                String status = elementInfo.eq(8).select("td").text();
                Elements elements1 = doc.getElementsByClass("pb-1 mb-2 line-b-f hd").first().children().select("a");
                String name = elements1.text();
                Elements elements2 = doc.getElementsByClass("container content").first().children();
                String description = elements2.eq(3).text();

                MangaDetail mangaDetail = new MangaDetail(image, author, artist, genre, status, alternative, rating);

                HashMap<Version, List<Chapter>> listMap = new HashMap<>();

                if (doc.getElementById("stream_4") != null) {
                    List<Chapter> listChapter1 = new ArrayList<>();
                    Elements elementVersion1 = doc.getElementById("stream_4").children();
                    Version version1 = new Version(1, elementVersion1.eq(0).select("span").text());
                    Elements elementsChapter1 = elementVersion1.eq(1).select("li");
                    int size1 = elementsChapter1.size();
                    for (int i = 0; i < size1; i++) {
                        String nameChapter = elementsChapter1.select("a.visited").eq(i).text();
                        String urlchapter = elementsChapter1.select("a.visited").eq(i).attr("href");
                        listChapter1.add(new Chapter(nameChapter, urlchapter));
                    }
                    listMap.put(version1, listChapter1);
                }

                if (doc.getElementById("stream_1") != null) {
                    Elements elementVersion2 = doc.getElementById("stream_1").children();
                    Version version2 = new Version(2, elementVersion2.eq(0).select("span").text());
                    Elements elementsChapter2 = elementVersion2.eq(1).select("li");
                    int size2 = elementsChapter2.size();
                    List<Chapter> listChapter2 = new ArrayList<>();
                    for (int i = 0; i < size2; i++) {
                        String nameChapter = elementsChapter2.select("a.visited").eq(i).text();
                        String urlchapter = elementsChapter2.select("a.visited").eq(i).attr("href");
                        listChapter2.add(new Chapter(nameChapter, urlchapter));
                    }
                    listMap.put(version2, listChapter2);
                }

                mangaDetail.setListMap(listMap);
                mangaDetail.setName(name);
                mangaDetail.setDescription(description);


                emitter.onNext(mangaDetail);
            }
        });
    }

    public static Observable<List<Read>> getReadImage(String url) {
        return Observable.create((ObservableOnSubscribe<List<Read>>) emitter -> {
            char[] list = url.toCharArray();
            char[] newlist = new char[list.length - 1];
            for (int i = 0; i < newlist.length; i++) {
                newlist[i] = list[i];
            }

            String newUrl = String.valueOf(newlist);

            Log.d("1234", "subscribe: " + Utilities.baseUrl + newUrl);
            Document doc = Jsoup.connect(Utilities.baseUrl + newUrl).userAgent("Chrome").get();
            List<Read> listRead = new ArrayList<>();

            Elements elements = doc.body().select("script").eq(1);

            String text = elements.toString().split("\\s")[5];
            String json = text.split(";")[0];
            Log.d("1234", "json: " + json);


            Gson gson = new Gson();

            Type type = new TypeToken<ArrayList<Read>>() {
            }.getType();

            listRead = gson.fromJson(json, type);


            Log.d("1234", "array: " + listRead.size());

            emitter.onNext(listRead);
        });
    }

    public static Observable<Result> getResultSearch(String url, int page) {
        List<Manga> mangaList = new ArrayList<>();
        return Observable.create(new ObservableOnSubscribe<Result>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Result> emitter) throws Exception {
                Document doc = null;
                try {
                    doc = Jsoup.connect(Utilities.baseUrl + Utilities.URL_RESULT + url).userAgent("Chrome").get();
                    Log.d("1234", "url: " + Utilities.baseUrl + Utilities.URL_RESULT + url);

                    Elements elements = doc.getElementsByClass("manga-list").first().children();

                    int size = elements.size();

                    for (int i = 1; i < size - 2; i++) {

                        String title = elements.select("a.cover")
                                .eq(i)
                                .attr("title");
                        String url = elements.select("a.cover")
                                .eq(i)
                                .attr("href");
                        String img = elements.select("a.cover").select("img")
                                .eq(i)
                                .attr("src");
                        String chapter = elements.select("a.visited").select("b")
                                .eq(i)
                                .text();
                        String urlChapter = elements.select("a.visited")
                                .eq(i)
                                .attr("href");

                        String rating = elements.select("div.rate").select("i")
                                .eq(i)
                                .text();
                        Log.d("123", "rate: " + rating);


                        Manga manga = new Manga(title, img, chapter);
                        manga.setUrl(url);
                        manga.setUrlChapter(urlChapter);
                        manga.setRating(Float.parseFloat(rating));
                        mangaList.add(manga);

                    }
                    int page = elements.select("ul.pager").select("select").select("option").size();
                    Log.d("1234", "totalPage: " + page);
                    Result result = new Result(mangaList, page);
                    emitter.onNext(result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static Observable<List<Genre>> getGenreSearch() {
        return Observable.create(new ObservableOnSubscribe<List<Genre>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<Genre>> emitter) throws Exception {
                Document doc = null;
                try {
                    doc = Jsoup.connect(Utilities.baseUrl + Utilities.URL_SEARCH).userAgent("Chrome").get();
                    List<Genre> list = new ArrayList<>();
                    Elements elements = doc.getElementsByClass("genres opt-item triploid").first().children();
                    Log.d("123", "subscribe: " + elements.size());
                    for (int i = 0; i < elements.size(); i++) {
                        String genre = elements.select("li").select("span").eq(i).text();
                        list.add(new Genre(genre, false));
                    }
                    emitter.onNext(list);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
