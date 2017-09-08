package com.hyjt.home.di.component;

import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.di.scope.ActivityScope;

import dagger.Component;


import com.hyjt.home.di.module.ContributeIdeaListModule;

import com.hyjt.home.mvp.ui.activity.ContributeIdeaListActivity;

@ActivityScope
@Component(modules = ContributeIdeaListModule.class, dependencies = AppComponent.class)
public interface ContributeIdeaListComponent {
    void inject(ContributeIdeaListActivity activity);
}