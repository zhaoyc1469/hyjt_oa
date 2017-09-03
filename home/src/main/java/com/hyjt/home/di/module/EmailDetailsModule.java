package com.hyjt.home.di.module;

import com.hyjt.frame.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.hyjt.home.mvp.contract.EmailDetailsContract;
import com.hyjt.home.mvp.model.EmailDetailsModel;


@Module
public class EmailDetailsModule {
    private EmailDetailsContract.View view;

    /**
     * 构建EmailDetailsModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public EmailDetailsModule(EmailDetailsContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    EmailDetailsContract.View provideEmailDetailsView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    EmailDetailsContract.Model provideEmailDetailsModel(EmailDetailsModel model) {
        return model;
    }
}