package com.hyjt.home.di.component;


import dagger.Component;


import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.home.di.module.ToCompLoanEditModule;

import com.hyjt.home.mvp.ui.activity.ToCompLoanEditActivity;

@ActivityScope
@Component(modules = ToCompLoanEditModule.class, dependencies = AppComponent.class)
public interface ToCompLoanEditComponent {
    void inject(ToCompLoanEditActivity activity);
}