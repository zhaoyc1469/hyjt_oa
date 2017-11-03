package com.hyjt.home.di.component;

import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.di.scope.ActivityScope;

import dagger.Component;


import com.hyjt.home.di.module.ApplyExpenseCreateModule;

import com.hyjt.home.mvp.ui.activity.ApplyExpenseCreateActivity;

@ActivityScope
@Component(modules = ApplyExpenseCreateModule.class, dependencies = AppComponent.class)
public interface ApplyExpenseCreateComponent {
    void inject(ApplyExpenseCreateActivity activity);
}