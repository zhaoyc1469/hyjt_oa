package com.hyjt.client.di.component;


import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.client.di.module.WorkModule;
import com.hyjt.client.mvp.ui.fragment.WorkFragment;

import dagger.Component;

@ActivityScope
@Component(modules = WorkModule.class, dependencies = AppComponent.class)
public interface WorkComponent {
    void inject(WorkFragment fragment);
}