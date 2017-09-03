package com.hyjt.client.di.component;


import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.client.di.module.MineModule;
import com.hyjt.client.mvp.ui.fragment.MineFragment;

import dagger.Component;

@ActivityScope
@Component(modules = MineModule.class, dependencies = AppComponent.class)
public interface MineComponent {
    void inject(MineFragment fragment);
}