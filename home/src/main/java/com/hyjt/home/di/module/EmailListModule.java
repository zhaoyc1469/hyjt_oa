package com.hyjt.home.di.module;

import com.hyjt.frame.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.hyjt.home.mvp.contract.EmailListContract;
import com.hyjt.home.mvp.model.EmailListModel;


@Module
public class EmailListModule {
    private EmailListContract.View view;

    /**
     * 构建EmailListModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public EmailListModule(EmailListContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    EmailListContract.View provideEmailListView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    EmailListContract.Model provideEmailListModel(EmailListModel model) {
        return model;
    }
}