package com.hyjt.home.di.component;

import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.home.di.module.LongSolveListModule;
import com.hyjt.home.mvp.ui.activity.LongSolveListActivity;

import dagger.Component;

@ActivityScope
@Component(modules = LongSolveListModule.class, dependencies = AppComponent.class)
public interface LongSolveListComponent {
    void inject(LongSolveListActivity activity);
}