package com.hyjt.client.di.component;


import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.client.di.module.MsgModule;
import com.hyjt.client.mvp.ui.fragment.MsgFragment;

import dagger.Component;

@ActivityScope
@Component(modules = MsgModule.class, dependencies = AppComponent.class)
public interface MsgComponent {
    void inject(MsgFragment fragment);
}