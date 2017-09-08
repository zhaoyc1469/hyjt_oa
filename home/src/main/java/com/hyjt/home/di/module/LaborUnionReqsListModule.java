package com.hyjt.home.di.module;


import com.hyjt.frame.di.scope.ActivityScope;


import dagger.Module;
import dagger.Provides;

import com.hyjt.home.mvp.contract.LaborUnionReqsListContract;
import com.hyjt.home.mvp.model.LaborUnionReqsListModel;


@Module
public class LaborUnionReqsListModule {
    private LaborUnionReqsListContract.View view;


    public LaborUnionReqsListModule(LaborUnionReqsListContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    LaborUnionReqsListContract.View provideLaborUnionReqsListView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    LaborUnionReqsListContract.Model provideLaborUnionReqsListModel(LaborUnionReqsListModel model) {
        return model;
    }
}