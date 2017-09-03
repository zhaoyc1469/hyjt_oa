package com.hyjt.home.mvp.ui.adapter;


import android.view.View;

import com.hyjt.frame.base.BaseHolder;
import com.hyjt.frame.base.DefaultAdapter;
import com.hyjt.home.R;
import com.hyjt.home.mvp.model.entity.Resp.MeetingListResp;
import com.hyjt.home.mvp.ui.holder.MeetingItemHolder;

import java.util.List;

/**
 * @author 赵宇驰
 * @time 2017/7/17  17:11
 * @desc ${TODO}
 */

public class MeetingAdapter extends DefaultAdapter<MeetingListResp.RowsBean> {
    public MeetingAdapter(List<MeetingListResp.RowsBean> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<MeetingListResp.RowsBean> getHolder(View v, int viewType) {
        return new MeetingItemHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.home_item_meeting;
    }
}

