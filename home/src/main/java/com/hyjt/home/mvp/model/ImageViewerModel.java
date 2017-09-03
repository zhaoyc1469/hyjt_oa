package com.hyjt.home.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.IRepositoryManager;
import com.hyjt.frame.mvp.BaseModel;
import com.hyjt.home.mvp.contract.ImageViewerContract;

import javax.inject.Inject;


@ActivityScope
public class ImageViewerModel extends BaseModel implements ImageViewerContract.Model {

    @Inject
    public ImageViewerModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}