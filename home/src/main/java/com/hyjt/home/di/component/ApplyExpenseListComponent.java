package com.hyjt.home.di.component;

import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.di.scope.ActivityScope;

import dagger.Component;


import com.hyjt.home.di.module.ApplyExpenseListModule;

import com.hyjt.home.mvp.ui.activity.ApplyExpenseListActivity;

@ActivityScope
@Component(modules = ApplyExpenseListModule.class, dependencies = AppComponent.class)
public interface ApplyExpenseListComponent {
    void inject(ApplyExpenseListActivity activity);
}