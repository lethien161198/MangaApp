package com.example.mangaapp.modules.reading.recent;

import com.example.mangaapp.common.Interactor;
import com.example.mangaapp.modules.database.MangaDatabase;

public class RecentInteractor extends Interactor<RecentContract.Presenter> implements RecentContract.Interactor {
    @Override
    public void crawl() {
        getPresenter().onFinishGetListManga(MangaDatabase.getInstance().recentDAO().getAll());
    }
}
