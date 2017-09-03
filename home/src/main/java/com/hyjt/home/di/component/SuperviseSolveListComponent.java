package com.hyjt.home.di.component;

import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.home.di.module.SuperviseSolveListModule;
import com.hyjt.home.mvp.ui.activity.SuperviseSolveListActivity;

import dagger.Component;

@ActivityScope
@Component(modules = SuperviseSolveListModule.class, dependencies = AppComponent.class)
public interface SuperviseSolveListComponent {
    void inject(SuperviseSolveListActivity activity);
}