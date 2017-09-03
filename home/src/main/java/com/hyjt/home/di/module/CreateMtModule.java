package com.hyjt.home.di.module;


import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.home.mvp.contract.CreateMtContract;
import com.hyjt.home.mvp.model.CreateMtModel;

import dagger.Module;
import dagger.Provides;

@Module
public class CreateMtModule {
    private CreateMtContract.View view;

    /**
     * 构建CreateMtModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public CreateMtModule(CreateMtContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    CreateMtContract.View provideCreateMtView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    CreateMtContract.Model provideCreateMtModel(CreateMtModel model) {
        return model;
    }
}