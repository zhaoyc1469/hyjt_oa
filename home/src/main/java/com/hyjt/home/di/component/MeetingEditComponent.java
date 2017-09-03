package com.hyjt.home.di.component;

import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.home.di.module.MeetingEditModule;
import com.hyjt.home.mvp.ui.activity.MeetingEditActivity;

import dagger.Component;

@ActivityScope
@Component(modules = MeetingEditModule.class, dependencies = AppComponent.class)
public interface MeetingEditComponent {
    void inject(MeetingEditActivity activity);
}