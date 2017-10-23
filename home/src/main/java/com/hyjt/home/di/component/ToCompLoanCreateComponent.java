package com.hyjt.home.di.component;


import dagger.Component;

import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.home.di.module.ToCompLoanCreateModule;

import com.hyjt.home.mvp.ui.activity.ToCompLoanCreateActivity;

@ActivityScope
@Component(modules = ToCompLoanCreateModule.class, dependencies = AppComponent.class)
public interface ToCompLoanCreateComponent {
    void inject(ToCompLoanCreateActivity activity);
}