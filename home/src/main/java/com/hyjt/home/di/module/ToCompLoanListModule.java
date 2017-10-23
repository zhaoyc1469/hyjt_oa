package com.hyjt.home.di.module;


import dagger.Module;
import dagger.Provides;

import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.home.mvp.contract.ToCompLoanListContract;
import com.hyjt.home.mvp.model.ToCompLoanListModel;


@Module
public class ToCompLoanListModule {
    private ToCompLoanListContract.View view;

    /**
     * 构建ToCompLoanListModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public ToCompLoanListModule(ToCompLoanListContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ToCompLoanListContract.View provideToCompLoanListView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ToCompLoanListContract.Model provideToCompLoanListModel(ToCompLoanListModel model) {
        return model;
    }
}