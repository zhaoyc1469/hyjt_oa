package com.hyjt.home.di.module;


import com.hyjt.frame.di.scope.ActivityScope;


import dagger.Module;
import dagger.Provides;

import com.hyjt.home.mvp.contract.ApplyExpenseCreateContract;
import com.hyjt.home.mvp.model.ApplyExpenseCreateModel;


@Module
public class ApplyExpenseCreateModule {
    private ApplyExpenseCreateContract.View view;


    public ApplyExpenseCreateModule(ApplyExpenseCreateContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ApplyExpenseCreateContract.View provideApplyExpenseCreateView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ApplyExpenseCreateContract.Model provideApplyExpenseCreateModel(ApplyExpenseCreateModel model) {
        return model;
    }
}