package com.hyjt.home.mvp.model.entity;

import android.net.Uri;

/**
 * Created by Administrator on 2017/10/11.
 */

public class AccessoryResq {

    private String name;
    private Uri uri;
    private String fileId;
    private String loadUrl;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getLoadUrl() {
        return loadUrl;
    }

    public void setLoadUrl(String loadUrl) {
        this.loadUrl = loadUrl;
    }
}
