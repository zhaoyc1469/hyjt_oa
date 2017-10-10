package com.hyjt.home.di.module;


import com.hyjt.frame.di.scope.ActivityScope;


import dagger.Module;
import dagger.Provides;

import com.hyjt.home.mvp.contract.PsonLoanEditContract;
import com.hyjt.home.mvp.model.PsonLoanEditModel;


@Module
public class PsonLoanEditModule {
    private PsonLoanEditContract.View view;


    public PsonLoanEditModule(PsonLoanEditContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    PsonLoanEditContract.View providePsonLoanEditView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    PsonLoanEditContract.Model providePsonLoanEditModel(PsonLoanEditModel model) {
        return model;
    }
}