package com.hyjt.client.di.module;


import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.client.mvp.contract.WorkContract;
import com.hyjt.client.mvp.model.WorkModel;

import dagger.Module;
import dagger.Provides;

@Module
public class WorkModule {
    private WorkContract.View view;

    /**
     * 构建WorkModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public WorkModule(WorkContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    WorkContract.View provideWorkView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    WorkContract.Model provideWorkModel(WorkModel model) {
        return model;
    }
}