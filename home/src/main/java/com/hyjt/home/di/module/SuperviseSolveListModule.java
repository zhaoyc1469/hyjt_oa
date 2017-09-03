package com.hyjt.home.di.module;


import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.home.mvp.contract.SuperviseSolveListContract;
import com.hyjt.home.mvp.model.SuperviseSolveListModel;

import dagger.Module;
import dagger.Provides;

@Module
public class SuperviseSolveListModule {
    private SuperviseSolveListContract.View view;

    /**
     * 构建SuperviseSolveListModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public SuperviseSolveListModule(SuperviseSolveListContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    SuperviseSolveListContract.View provideSuperviseSolveListView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    SuperviseSolveListContract.Model provideSuperviseSolveListModel(SuperviseSolveListModel model) {
        return model;
    }
}