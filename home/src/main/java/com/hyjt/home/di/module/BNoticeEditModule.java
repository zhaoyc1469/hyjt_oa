package com.hyjt.home.di.module;

import com.hyjt.frame.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.hyjt.home.mvp.contract.BNoticeEditContract;
import com.hyjt.home.mvp.model.BNoticeEditModel;


@Module
public class BNoticeEditModule {
    private BNoticeEditContract.View view;

    /**
     * 构建BNoticeEditModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public BNoticeEditModule(BNoticeEditContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    BNoticeEditContract.View provideBNoticeEditView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    BNoticeEditContract.Model provideBNoticeEditModel(BNoticeEditModel model) {
        return model;
    }
}