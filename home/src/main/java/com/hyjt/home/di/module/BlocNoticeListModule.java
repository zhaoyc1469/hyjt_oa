package com.hyjt.home.di.module;

import com.hyjt.frame.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.hyjt.home.mvp.contract.BlocNoticeListContract;
import com.hyjt.home.mvp.model.BlocNoticeListModel;


@Module
public class BlocNoticeListModule {
    private BlocNoticeListContract.View view;

    /**
     * 构建BlocNoticeListModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public BlocNoticeListModule(BlocNoticeListContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    BlocNoticeListContract.View provideBlocNoticeListView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    BlocNoticeListContract.Model provideBlocNoticeListModel(BlocNoticeListModel model) {
        return model;
    }
}