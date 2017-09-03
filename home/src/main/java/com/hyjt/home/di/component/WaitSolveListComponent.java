package com.hyjt.home.di.component;

import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.home.di.module.WaitSolveListModule;
import com.hyjt.home.mvp.ui.activity.WaitSolveListActivity;

import dagger.Component;

@ActivityScope
@Component(modules = WaitSolveListModule.class, dependencies = AppComponent.class)
public interface WaitSolveListComponent {
    void inject(WaitSolveListActivity activity);
}