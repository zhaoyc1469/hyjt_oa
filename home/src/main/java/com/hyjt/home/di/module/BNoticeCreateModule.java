package com.hyjt.home.di.module;

import com.hyjt.frame.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.hyjt.home.mvp.contract.BNoticeCreateContract;
import com.hyjt.home.mvp.model.BNoticeCreateModel;


@Module
public class BNoticeCreateModule {
    private BNoticeCreateContract.View view;

    /**
     * 构建BNoticeCreateModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public BNoticeCreateModule(BNoticeCreateContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    BNoticeCreateContract.View provideBNoticeCreateView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    BNoticeCreateContract.Model provideBNoticeCreateModel(BNoticeCreateModel model) {
        return model;
    }
}