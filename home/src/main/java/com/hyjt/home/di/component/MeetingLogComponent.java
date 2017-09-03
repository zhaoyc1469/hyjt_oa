package com.hyjt.home.di.component;

import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.home.di.module.MeetingLogModule;
import com.hyjt.home.mvp.ui.activity.MeetingLogActivity;

import dagger.Component;

@ActivityScope
@Component(modules = MeetingLogModule.class, dependencies = AppComponent.class)
public interface MeetingLogComponent {
    void inject(MeetingLogActivity activity);
}