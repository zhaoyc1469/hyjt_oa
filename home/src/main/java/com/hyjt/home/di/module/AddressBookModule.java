package com.hyjt.home.di.module;


import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.home.mvp.contract.AddressBookContract;
import com.hyjt.home.mvp.model.AddressBookModel;

import dagger.Module;
import dagger.Provides;


@Module
public class AddressBookModule {
    private AddressBookContract.View view;

    /**
     * 构建AddressBookModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public AddressBookModule(AddressBookContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    AddressBookContract.View provideAddressBookView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    AddressBookContract.Model provideAddressBookModel(AddressBookModel model) {
        return model;
    }
}