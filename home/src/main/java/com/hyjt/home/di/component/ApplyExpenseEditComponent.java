package com.hyjt.home.di.component;

import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.di.scope.ActivityScope;

import dagger.Component;


import com.hyjt.home.di.module.ApplyExpenseEditModule;

import com.hyjt.home.mvp.ui.activity.ApplyExpenseEditActivity;

@ActivityScope
@Component(modules = ApplyExpenseEditModule.class, dependencies = AppComponent.class)
public interface ApplyExpenseEditComponent {
    void inject(ApplyExpenseEditActivity activity);
}