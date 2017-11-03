package com.hyjt.frame.mvp;

import android.view.View;

import com.hyjt.frame.event.RefreshListEvent;

/**
 * @author 赵宇驰
 * @time 2017/7/15  22:16
 * @desc ${TODO}
 */

public interface IListView extends IView {

    void showLoading();

    void hideLoading();

    void refreshList(RefreshListEvent refreshListEvent);
}
