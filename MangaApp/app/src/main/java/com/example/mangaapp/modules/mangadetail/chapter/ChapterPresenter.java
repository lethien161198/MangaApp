package com.example.mangaapp.modules.mangadetail.chapter;

import android.os.Bundle;

import com.example.mangaapp.common.Presenter;
import com.example.mangaapp.common.Utilities;
import com.example.mangaapp.models.Chapter;
import com.example.mangaapp.models.Version;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChapterPresenter extends Presenter<ChapterContract.View, ChapterContract.Interactor> implements ChapterContract.Presenter {

    public ChapterPresenter(ChapterContract.View view, ChapterContract.Interactor interactor, Bundle parameters) {
        super(view, interactor, parameters);
    }

    @Override
    public void getListChapter() {
        HashMap<Version, List<Chapter>> listMap = (HashMap<Version, List<Chapter>>) getBundle().getSerializable(Utilities.KEY_MAP_CHAPTER);
        if(listMap == null){
            getView().loadListChapter(new HashMap<>());
        }else onFinishGetListChapter(listMap);
    }

    @Override
    public void onFinishGetListChapter(HashMap<Version, List<Chapter>> listMap) {
        getView().loadListChapter(listMap);
    }
}
