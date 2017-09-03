package com.hyjt.home.mvp.ui.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * @author 赵宇驰
 * @time 2017/3/10  17:34
 * @desc ${TODO}
 */

public abstract class LoadRecListener extends
        RecyclerView.OnScrollListener {

    private int previousTotal = 0;
    private boolean loading = true;
    int firstVisibleItem, visibleItemCount, totalItemCount;

    private int currentPage = 1;
    private int pageCount;

    private LinearLayoutManager mLinearLayoutManager;

    public LoadRecListener(
            LinearLayoutManager linearLayoutManager,
            int pageCount) {
        this.mLinearLayoutManager = linearLayoutManager;
        this.pageCount = pageCount;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mLinearLayoutManager.getItemCount();
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading
                && (totalItemCount - visibleItemCount) <= firstVisibleItem
                && totalItemCount >= pageCount) {
            currentPage++;
            onLoadMore(currentPage);
            loading = true;
        }
    }

    public abstract void onLoadMore(int currentPage);
}