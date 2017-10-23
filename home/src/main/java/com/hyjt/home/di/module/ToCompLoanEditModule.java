package com.hyjt.home.di.module;


import dagger.Module;
import dagger.Provides;

import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.home.mvp.contract.ToCompLoanEditContract;
import com.hyjt.home.mvp.model.ToCompLoanEditModel;


@Module
public class ToCompLoanEditModule {
    private ToCompLoanEditContract.View view;

    /**
     * 构建ToCompLoanEditModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public ToCompLoanEditModule(ToCompLoanEditContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ToCompLoanEditContract.View provideToCompLoanEditView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ToCompLoanEditContract.Model provideToCompLoanEditModel(ToCompLoanEditModel model) {
        return model;
    }
}