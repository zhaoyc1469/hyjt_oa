package com.hyjt.home.di.module;


import com.hyjt.frame.di.scope.ActivityScope;


import dagger.Module;
import dagger.Provides;

import com.hyjt.home.mvp.contract.ApplyExpenseEditContract;
import com.hyjt.home.mvp.model.ApplyExpenseEditModel;


@Module
public class ApplyExpenseEditModule {
    private ApplyExpenseEditContract.View view;


    public ApplyExpenseEditModule(ApplyExpenseEditContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ApplyExpenseEditContract.View provideApplyExpenseEditView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ApplyExpenseEditContract.Model provideApplyExpenseEditModel(ApplyExpenseEditModel model) {
        return model;
    }
}