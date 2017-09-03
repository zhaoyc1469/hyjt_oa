package com.hyjt.client.di.module;


import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.client.mvp.contract.MsgContract;
import com.hyjt.client.mvp.model.MsgModel;

import dagger.Module;
import dagger.Provides;

@Module
public class MsgModule {
    private MsgContract.View view;

    /**
     * 构建MsgModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public MsgModule(MsgContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    MsgContract.View provideMsgView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    MsgContract.Model provideMsgModel(MsgModel model) {
        return model;
    }
}