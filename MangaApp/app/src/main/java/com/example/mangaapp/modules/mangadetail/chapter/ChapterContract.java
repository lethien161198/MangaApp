package com.example.mangaapp.modules.mangadetail.chapter;

import com.example.mangaapp.common.MVP;
import com.example.mangaapp.models.Chapter;
import com.example.mangaapp.models.Version;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface ChapterContract {
    interface View extends MVP.View<Presenter> {
        void loadListChapter(HashMap<Version, List<Chapter>> listMap);
    }

    interface Presenter extends MVP.Presenter<View, Interactor> {
        void getListChapter();
        void onFinishGetListChapter(HashMap<Version, List<Chapter>> listMap);
    }

    interface Interactor extends MVP.Interactor<Presenter> {
    }
}
