package com.hyjt.home.mvp.ui.adapter;

import android.view.View;

import com.hyjt.frame.base.BaseHolder;
import com.hyjt.frame.base.DefaultAdapter;
import com.hyjt.home.R;
import com.hyjt.home.mvp.model.entity.Resp.BlocNoticeListResp;
import com.hyjt.home.mvp.ui.holder.BNoticeItemHolder;

import java.util.List;

/**
 * @author 赵宇驰
 * @time 2017/8/23  17:06
 * @desc ${TODO}
 */

public class BlocNoticeAdapter extends DefaultAdapter<BlocNoticeListResp.RowsBean> {

    public BlocNoticeAdapter(List<BlocNoticeListResp.RowsBean> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<BlocNoticeListResp.RowsBean> getHolder(View v, int viewType) {
        return new BNoticeItemHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.home_item_bloc_notice;
    }
}
