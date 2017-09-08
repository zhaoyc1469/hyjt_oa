package com.hyjt.home.di.module;


import com.hyjt.frame.di.scope.ActivityScope;


import dagger.Module;
import dagger.Provides;

import com.hyjt.home.mvp.contract.LaborUnionReqsDetailContract;
import com.hyjt.home.mvp.model.LaborUnionReqsDetailModel;


@Module
public class LaborUnionReqsDetailModule {
    private LaborUnionReqsDetailContract.View view;


    public LaborUnionReqsDetailModule(LaborUnionReqsDetailContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    LaborUnionReqsDetailContract.View provideLaborUnionReqsDetailView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    LaborUnionReqsDetailContract.Model provideLaborUnionReqsDetailModel(LaborUnionReqsDetailModel model) {
        return model;
    }
}