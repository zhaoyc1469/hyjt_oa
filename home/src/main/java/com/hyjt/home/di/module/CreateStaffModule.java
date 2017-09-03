package com.hyjt.home.di.module;


import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.home.mvp.contract.CreateStaffContract;
import com.hyjt.home.mvp.model.CreateStaffModel;

import dagger.Module;
import dagger.Provides;

@Module
public class CreateStaffModule {
    private CreateStaffContract.View view;

    /**
     * 构建CreateStaffModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public CreateStaffModule(CreateStaffContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    CreateStaffContract.View provideCreateStaffView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    CreateStaffContract.Model provideCreateStaffModel(CreateStaffModel model) {
        return model;
    }
}