package com.hyjt.home.di.module;


import com.hyjt.frame.di.scope.ActivityScope;


import dagger.Module;
import dagger.Provides;

import com.hyjt.home.mvp.contract.ApplyExpenseListContract;
import com.hyjt.home.mvp.model.ApplyExpenseListModel;


@Module
public class ApplyExpenseListModule {
    private ApplyExpenseListContract.View view;


    public ApplyExpenseListModule(ApplyExpenseListContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ApplyExpenseListContract.View provideApplyExpenseListView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ApplyExpenseListContract.Model provideApplyExpenseListModel(ApplyExpenseListModel model) {
        return model;
    }
}