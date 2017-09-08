package com.hyjt.home.di.module;


import com.hyjt.frame.di.scope.ActivityScope;


import dagger.Module;
import dagger.Provides;

import com.hyjt.home.mvp.contract.LaborUnionReqsCreateContract;
import com.hyjt.home.mvp.model.LaborUnionReqsCreateModel;


@Module
public class LaborUnionReqsCreateModule {
    private LaborUnionReqsCreateContract.View view;


    public LaborUnionReqsCreateModule(LaborUnionReqsCreateContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    LaborUnionReqsCreateContract.View provideLaborUnionReqsCreateView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    LaborUnionReqsCreateContract.Model provideLaborUnionReqsCreateModel(LaborUnionReqsCreateModel model) {
        return model;
    }
}