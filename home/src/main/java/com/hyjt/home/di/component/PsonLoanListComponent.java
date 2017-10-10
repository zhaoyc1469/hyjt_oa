package com.hyjt.home.di.component;

import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.di.scope.ActivityScope;

import dagger.Component;


import com.hyjt.home.di.module.PsonLoanListModule;

import com.hyjt.home.mvp.ui.activity.PsonLoanListActivity;

@ActivityScope
@Component(modules = PsonLoanListModule.class, dependencies = AppComponent.class)
public interface PsonLoanListComponent {
    void inject(PsonLoanListActivity activity);
}