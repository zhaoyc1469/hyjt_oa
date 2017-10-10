package com.hyjt.home.di.component;

import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.di.scope.ActivityScope;

import dagger.Component;


import com.hyjt.home.di.module.PsonLoanCreateModule;

import com.hyjt.home.mvp.ui.activity.PsonLoanCreateActivity;

@ActivityScope
@Component(modules = PsonLoanCreateModule.class, dependencies = AppComponent.class)
public interface PsonLoanCreateComponent {
    void inject(PsonLoanCreateActivity activity);
}