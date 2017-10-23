package com.hyjt.home.di.module;


import dagger.Module;
import dagger.Provides;

import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.home.mvp.contract.ToCompLoanCreateContract;
import com.hyjt.home.mvp.model.ToCompLoanCreateModel;


@Module
public class ToCompLoanCreateModule {
    private ToCompLoanCreateContract.View view;

    /**
     * 构建ToCompLoanCreateModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public ToCompLoanCreateModule(ToCompLoanCreateContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ToCompLoanCreateContract.View provideToCompLoanCreateView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ToCompLoanCreateContract.Model provideToCompLoanCreateModel(ToCompLoanCreateModel model) {
        return model;
    }
}