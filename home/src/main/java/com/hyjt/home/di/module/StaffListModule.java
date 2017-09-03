package com.hyjt.home.di.module;

import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.home.mvp.contract.StaffListContract;
import com.hyjt.home.mvp.model.StaffListModel;

import dagger.Module;
import dagger.Provides;

@Module
public class StaffListModule {
    private StaffListContract.View view;

    /**
     * 构建StaffListModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public StaffListModule(StaffListContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    StaffListContract.View provideStaffListView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    StaffListContract.Model provideStaffListModel(StaffListModel model) {
        return model;
    }
}