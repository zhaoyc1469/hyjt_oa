package com.hyjt.client.di.module;


import com.hyjt.frame.di.scope.ActivityScope;


import dagger.Module;
import dagger.Provides;

import com.hyjt.client.mvp.contract.MdManageContract;
import com.hyjt.client.mvp.model.MdManageModel;


@Module
public class MdManageModule {
    private MdManageContract.View view;


    public MdManageModule(MdManageContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    MdManageContract.View provideMdManageView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    MdManageContract.Model provideMdManageModel(MdManageModel model) {
        return model;
    }
}