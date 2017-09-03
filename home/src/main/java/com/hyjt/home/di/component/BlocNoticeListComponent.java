package com.hyjt.home.di.component;

import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.di.scope.ActivityScope;

import dagger.Component;

import com.hyjt.home.di.module.BlocNoticeListModule;

import com.hyjt.home.mvp.ui.activity.BlocNoticeListActivity;

@ActivityScope
@Component(modules = BlocNoticeListModule.class, dependencies = AppComponent.class)
public interface BlocNoticeListComponent {
    void inject(BlocNoticeListActivity activity);
}