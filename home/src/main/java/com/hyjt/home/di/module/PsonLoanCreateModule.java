package com.hyjt.home.di.module;


import com.hyjt.frame.di.scope.ActivityScope;


import dagger.Module;
import dagger.Provides;

import com.hyjt.home.mvp.contract.PsonLoanCreateContract;
import com.hyjt.home.mvp.model.PsonLoanCreateModel;


@Module
public class PsonLoanCreateModule {
    private PsonLoanCreateContract.View view;


    public PsonLoanCreateModule(PsonLoanCreateContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    PsonLoanCreateContract.View providePsonLoanCreateView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    PsonLoanCreateContract.Model providePsonLoanCreateModel(PsonLoanCreateModel model) {
        return model;
    }
}