package com.hyjt.home.di.module;

import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.home.mvp.contract.WaitSolveListContract;
import com.hyjt.home.mvp.model.WaitSolveListModel;

import dagger.Module;
import dagger.Provides;


@Module
public class WaitSolveListModule {
    private WaitSolveListContract.View view;

    /**
     * 构建WaitSolveListModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public WaitSolveListModule(WaitSolveListContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    WaitSolveListContract.View provideWaitSolveListView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    WaitSolveListContract.Model provideWaitSolveListModel(WaitSolveListModel model) {
        return model;
    }
}