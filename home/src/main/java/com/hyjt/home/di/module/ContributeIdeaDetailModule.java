package com.hyjt.home.di.module;


import com.hyjt.frame.di.scope.ActivityScope;


import dagger.Module;
import dagger.Provides;

import com.hyjt.home.mvp.contract.ContributeIdeaDetailContract;
import com.hyjt.home.mvp.model.ContributeIdeaDetailModel;


@Module
public class ContributeIdeaDetailModule {
    private ContributeIdeaDetailContract.View view;


    public ContributeIdeaDetailModule(ContributeIdeaDetailContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ContributeIdeaDetailContract.View provideContributeIdeaDetailView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ContributeIdeaDetailContract.Model provideContributeIdeaDetailModel(ContributeIdeaDetailModel model) {
        return model;
    }
}