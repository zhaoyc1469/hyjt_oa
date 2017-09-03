package com.hyjt.home.di.module;


import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.home.mvp.contract.ImageViewerContract;
import com.hyjt.home.mvp.model.ImageViewerModel;

import dagger.Module;
import dagger.Provides;


@Module
public class ImageViewerModule {
    private ImageViewerContract.View view;

    /**
     * 构建ImageViewerModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public ImageViewerModule(ImageViewerContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ImageViewerContract.View provideImageViewerView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ImageViewerContract.Model provideImageViewerModel(ImageViewerModel model) {
        return model;
    }
}