package com.hyjt.home.di.component;


import dagger.Component;


import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.home.di.module.ToCompLoanListModule;

import com.hyjt.home.mvp.ui.activity.ToCompLoanListActivity;

@ActivityScope
@Component(modules = ToCompLoanListModule.class, dependencies = AppComponent.class)
public interface ToCompLoanListComponent {
    void inject(ToCompLoanListActivity activity);
}