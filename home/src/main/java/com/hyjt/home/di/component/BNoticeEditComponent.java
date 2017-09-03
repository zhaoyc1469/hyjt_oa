package com.hyjt.home.di.component;

import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.di.scope.ActivityScope;

import dagger.Component;

import com.hyjt.home.di.module.BNoticeEditModule;

import com.hyjt.home.mvp.ui.activity.BNoticeEditActivity;

@ActivityScope
@Component(modules = BNoticeEditModule.class, dependencies = AppComponent.class)
public interface BNoticeEditComponent {
    void inject(BNoticeEditActivity activity);
}