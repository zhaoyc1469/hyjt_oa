package com.hyjt.home.di.module;


import com.hyjt.frame.di.scope.ActivityScope;


import dagger.Module;
import dagger.Provides;

import com.hyjt.home.mvp.contract.ContributeIdeaListContract;
import com.hyjt.home.mvp.model.ContributeIdeaListModel;


@Module
public class ContributeIdeaListModule {
    private ContributeIdeaListContract.View view;


    public ContributeIdeaListModule(ContributeIdeaListContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ContributeIdeaListContract.View provideContributeIdeaListView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ContributeIdeaListContract.Model provideContributeIdeaListModel(ContributeIdeaListModel model) {
        return model;
    }
}