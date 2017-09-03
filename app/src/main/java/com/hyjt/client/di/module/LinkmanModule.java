package com.hyjt.client.di.module;

import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.client.mvp.contract.LinkmanContract;
import com.hyjt.client.mvp.model.LinkmanModel;

import dagger.Module;
import dagger.Provides;


@Module
public class LinkmanModule {
    private LinkmanContract.View view;

    /**
     * 构建LinkmanModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public LinkmanModule(LinkmanContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    LinkmanContract.View provideLinkmanView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    LinkmanContract.Model provideLinkmanModel(LinkmanModel model) {
        return model;
    }
}