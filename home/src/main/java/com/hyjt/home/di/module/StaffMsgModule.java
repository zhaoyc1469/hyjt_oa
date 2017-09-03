package com.hyjt.home.di.module;

import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.home.mvp.contract.StaffMsgContract;
import com.hyjt.home.mvp.model.StaffMsgModel;

import dagger.Module;
import dagger.Provides;


@Module
public class StaffMsgModule {
    private StaffMsgContract.View view;

    /**
     * 构建StaffMsgModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public StaffMsgModule(StaffMsgContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    StaffMsgContract.View provideStaffMsgView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    StaffMsgContract.Model provideStaffMsgModel(StaffMsgModel model) {
        return model;
    }
}