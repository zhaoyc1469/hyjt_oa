package com.hyjt.client.di.component;


import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.client.di.module.LinkmanModule;
import com.hyjt.client.mvp.ui.fragment.LinkmanFragment;

import dagger.Component;

@ActivityScope
@Component(modules = LinkmanModule.class, dependencies = AppComponent.class)
public interface LinkmanComponent {
    void inject(LinkmanFragment fragment);
}