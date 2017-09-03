package com.hyjt.home.di.module;

import com.hyjt.frame.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.hyjt.home.mvp.contract.EmailCreateContract;
import com.hyjt.home.mvp.model.EmailCreateModel;


@Module
public class EmailCreateModule {
    private EmailCreateContract.View view;

    /**
     * 构建EmailCreateModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public EmailCreateModule(EmailCreateContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    EmailCreateContract.View provideEmailCreateView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    EmailCreateContract.Model provideEmailCreateModel(EmailCreateModel model) {
        return model;
    }
}