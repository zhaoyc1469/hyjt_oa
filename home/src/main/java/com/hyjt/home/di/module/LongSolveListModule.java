package com.hyjt.home.di.module;


import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.home.mvp.contract.LongSolveListContract;
import com.hyjt.home.mvp.model.LongSolveListModel;

import dagger.Module;
import dagger.Provides;

@Module
public class LongSolveListModule {
    private LongSolveListContract.View view;

    /**
     * 构建LongSolveListModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public LongSolveListModule(LongSolveListContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    LongSolveListContract.View provideLongSolveListView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    LongSolveListContract.Model provideLongSolveListModel(LongSolveListModel model) {
        return model;
    }
}