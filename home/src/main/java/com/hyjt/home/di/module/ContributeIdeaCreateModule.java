package com.hyjt.home.di.module;


import com.hyjt.frame.di.scope.ActivityScope;


import dagger.Module;
import dagger.Provides;

import com.hyjt.home.mvp.contract.ContributeIdeaCreateContract;
import com.hyjt.home.mvp.model.ContributeIdeaCreateModel;


@Module
public class ContributeIdeaCreateModule {
    private ContributeIdeaCreateContract.View view;


    public ContributeIdeaCreateModule(ContributeIdeaCreateContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ContributeIdeaCreateContract.View provideContributeIdeaCreateView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ContributeIdeaCreateContract.Model provideContributeIdeaCreateModel(ContributeIdeaCreateModel model) {
        return model;
    }
}