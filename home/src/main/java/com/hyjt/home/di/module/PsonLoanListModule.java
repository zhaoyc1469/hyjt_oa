package com.hyjt.home.di.module;


import com.hyjt.frame.di.scope.ActivityScope;


import dagger.Module;
import dagger.Provides;

import com.hyjt.home.mvp.contract.PsonLoanListContract;
import com.hyjt.home.mvp.model.PsonLoanListModel;


@Module
public class PsonLoanListModule {
    private PsonLoanListContract.View view;


    public PsonLoanListModule(PsonLoanListContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    PsonLoanListContract.View providePsonLoanListView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    PsonLoanListContract.Model providePsonLoanListModel(PsonLoanListModel model) {
        return model;
    }
}