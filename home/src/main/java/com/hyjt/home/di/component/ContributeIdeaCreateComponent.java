package com.hyjt.home.di.component;

import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.di.scope.ActivityScope;

import dagger.Component;


import com.hyjt.home.di.module.ContributeIdeaCreateModule;

import com.hyjt.home.mvp.ui.activity.ContributeIdeaCreateActivity;

@ActivityScope
@Component(modules = ContributeIdeaCreateModule.class, dependencies = AppComponent.class)
public interface ContributeIdeaCreateComponent {
    void inject(ContributeIdeaCreateActivity activity);
}