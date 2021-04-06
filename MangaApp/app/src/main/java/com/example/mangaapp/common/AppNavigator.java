package com.example.mangaapp.common;

import android.os.Bundle;

import com.example.mangaapp.modules.manga.MangaFragment;
import com.example.mangaapp.modules.manga.MangaInteractor;
import com.example.mangaapp.modules.manga.MangaPresenter;
import com.example.mangaapp.modules.manga.latest.LatestFragment;
import com.example.mangaapp.modules.manga.latest.LatestInteractor;
import com.example.mangaapp.modules.manga.latest.LatestPresenter;
import com.example.mangaapp.modules.manga.popular.PopularFragment;
import com.example.mangaapp.modules.manga.popular.PopularInteractor;
import com.example.mangaapp.modules.manga.popular.PopularPresenter;
import com.example.mangaapp.modules.manga.updates.UpdatesFragment;
import com.example.mangaapp.modules.manga.updates.UpdatesInteractor;
import com.example.mangaapp.modules.manga.updates.UpdatesPresenter;
import com.example.mangaapp.modules.mangadetail.DetailFragment;
import com.example.mangaapp.modules.mangadetail.DetailInteractor;
import com.example.mangaapp.modules.mangadetail.DetailPresenter;
import com.example.mangaapp.modules.mangadetail.chapter.ChapterFragment;
import com.example.mangaapp.modules.mangadetail.chapter.ChapterInteractor;
import com.example.mangaapp.modules.mangadetail.chapter.ChapterPresenter;
import com.example.mangaapp.modules.mangadetail.description.DesInteractor;
import com.example.mangaapp.modules.mangadetail.description.DesPresenter;
import com.example.mangaapp.modules.mangadetail.description.DescriptionFragment;
import com.example.mangaapp.modules.read.ReadFragment;
import com.example.mangaapp.modules.read.ReadInteractor;
import com.example.mangaapp.modules.read.ReadPresenter;
import com.example.mangaapp.modules.reading.ReadingFragment;
import com.example.mangaapp.modules.reading.ReadingInteractor;
import com.example.mangaapp.modules.reading.ReadingPresenter;
import com.example.mangaapp.modules.reading.favourite.FavouriteFragment;
import com.example.mangaapp.modules.reading.favourite.FavouriteInteractor;
import com.example.mangaapp.modules.reading.favourite.FavouritePresenter;
import com.example.mangaapp.modules.reading.recent.RecentFragment;
import com.example.mangaapp.modules.reading.recent.RecentInteractor;
import com.example.mangaapp.modules.reading.recent.RecentPresenter;
import com.example.mangaapp.modules.search.SearchFragment;
import com.example.mangaapp.modules.search.SearchInteractor;
import com.example.mangaapp.modules.search.SearchPresenter;
import com.example.mangaapp.modules.search.result.ResultFragment;
import com.example.mangaapp.modules.search.result.ResultInteractor;
import com.example.mangaapp.modules.search.result.ResultPresenter;

public class AppNavigator {
    public static final String ROUTE_LATEST = "route_latest";
    public static final String ROUTE_UPDATES = "route_updates";
    public static final String ROUTE_POPULAR = "route_popular";
    public static final String ROUTE_MANGA = "route_manga";
    public static final String ROUTE_READING = "route_reading";
    public static final String ROUTE_CHAPTER = "route_chapter";
    public static final String ROUTE_DESCRIPTION = "route_description";
    public static final String ROUTE_DETAIL = "route_detail";
    public static final String ROUTE_SEARCH = "route_search";
    public static final String ROUTE_RESULT = "route_result";
    public static final String ROUTE_RECENT = "route_recent";
    public static final String ROUTE_FAVOURITE = "route_favourite";
    public static final String ROUTE_READ = "route_read";
    public static MVP.View viewWithRoute(String route) {
        return viewWithRoute(route, null);
    }

    public static MVP.View viewWithRoute(String route, Bundle parameters) {
        switch (route) {
            case ROUTE_MANGA:
                return new MangaPresenter(new MangaFragment(),new MangaInteractor(),parameters).getView();
            case ROUTE_READING:
                return new ReadingPresenter(new ReadingFragment(),new ReadingInteractor(),parameters).getView();
            case ROUTE_LATEST:
                return new LatestPresenter(new LatestFragment(),new LatestInteractor(),parameters).getView();
            case ROUTE_POPULAR:
                return new PopularPresenter(new PopularFragment(),new PopularInteractor(),parameters).getView();
            case ROUTE_UPDATES:
                return new UpdatesPresenter(new UpdatesFragment(),new UpdatesInteractor(),parameters).getView();
            case ROUTE_CHAPTER:
                return new ChapterPresenter(new ChapterFragment(),new ChapterInteractor(),parameters).getView();
            case ROUTE_DESCRIPTION:
                return new DesPresenter(new DescriptionFragment(),new DesInteractor(),parameters).getView();
            case ROUTE_DETAIL:
                return new DetailPresenter(new DetailFragment(),new DetailInteractor(),parameters).getView();
            case ROUTE_SEARCH:
                return new SearchPresenter(new SearchFragment(),new SearchInteractor(),parameters).getView();
            case ROUTE_RESULT:
                return new ResultPresenter(new ResultFragment(),new ResultInteractor(),parameters).getView();
            case ROUTE_RECENT:
                return new RecentPresenter(new RecentFragment(),new RecentInteractor(),parameters).getView();
            case ROUTE_FAVOURITE:
                return new FavouritePresenter(new FavouriteFragment(),new FavouriteInteractor(),parameters).getView();
            case ROUTE_READ:
                return new ReadPresenter(new ReadFragment(),new ReadInteractor(),parameters).getView();
            default:
                break;
        }

        return null;
    }
}

