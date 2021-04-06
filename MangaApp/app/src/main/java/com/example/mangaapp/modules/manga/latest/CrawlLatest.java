package com.example.mangaapp.modules.manga.latest;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class CrawlLatest extends Worker {
    public CrawlLatest(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        String url = getInputData().getString("url");

        crawl(url);

        return Result.success(new Data.Builder()
                .putString("receive", url)
                .build());
    }
    public void crawl(String url){
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
            Elements elements = doc.getElementsByClass("ls1").first().children();

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


                Log.d("items", title);
                Log.d("items", image);
                Log.d("items", chapter);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
