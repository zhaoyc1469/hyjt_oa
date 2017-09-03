package com.hyjt.home.di.component;


import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.home.di.module.MeetingListModule;
import com.hyjt.home.mvp.ui.activity.MeetingListActivity;

import dagger.Component;

@ActivityScope
@Component(modules = MeetingListModule.class, dependencies = AppComponent.class)
public interface MeetingListComponent {
    void inject(MeetingListActivity activity);
}