package com.hyjt.home.di.component;

import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.home.di.module.AddressBookModule;
import com.hyjt.home.mvp.ui.activity.AddressBookActivity;

import dagger.Component;

@ActivityScope
@Component(modules = AddressBookModule.class, dependencies = AppComponent.class)
public interface AddressBookComponent {
    void inject(AddressBookActivity activity);
}