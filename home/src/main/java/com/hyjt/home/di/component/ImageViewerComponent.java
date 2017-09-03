package com.hyjt.home.di.component;

import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.home.di.module.ImageViewerModule;
import com.hyjt.home.mvp.ui.activity.ImageViewerActivity;

import dagger.Component;

@ActivityScope
@Component(modules = ImageViewerModule.class, dependencies = AppComponent.class)
public interface ImageViewerComponent {
    void inject(ImageViewerActivity activity);
}