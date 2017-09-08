package com.hyjt.home.di.component;

import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.di.scope.ActivityScope;

import dagger.Component;


import com.hyjt.home.di.module.ContributeIdeaDetailModule;

import com.hyjt.home.mvp.ui.activity.ContributeIdeaDetailActivity;

@ActivityScope
@Component(modules = ContributeIdeaDetailModule.class, dependencies = AppComponent.class)
public interface ContributeIdeaDetailComponent {
    void inject(ContributeIdeaDetailActivity activity);
}