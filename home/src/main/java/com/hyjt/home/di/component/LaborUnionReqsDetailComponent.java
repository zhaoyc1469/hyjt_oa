package com.hyjt.home.di.component;

import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.di.scope.ActivityScope;

import dagger.Component;


import com.hyjt.home.di.module.LaborUnionReqsDetailModule;

import com.hyjt.home.mvp.ui.activity.LaborUnionReqsDetailActivity;

@ActivityScope
@Component(modules = LaborUnionReqsDetailModule.class, dependencies = AppComponent.class)
public interface LaborUnionReqsDetailComponent {
    void inject(LaborUnionReqsDetailActivity activity);
}