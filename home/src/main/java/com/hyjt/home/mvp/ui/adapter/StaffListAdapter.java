package com.hyjt.home.mvp.ui.adapter;

import android.view.View;

import com.hyjt.frame.base.BaseHolder;
import com.hyjt.frame.base.DefaultAdapter;
import com.hyjt.home.R;
import com.hyjt.home.mvp.model.entity.Resp.StaffListResp;
import com.hyjt.home.mvp.ui.holder.StaffItemHolder;

import java.util.List;

/**
 * @author 赵宇驰
 * @time 2017/8/10  10:13
 * @desc ${TODO}
 */

public class StaffListAdapter extends DefaultAdapter<StaffListResp.RowsBean> {

    public StaffListAdapter(List<StaffListResp.RowsBean> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<StaffListResp.RowsBean> getHolder(View v, int viewType) {
        return new StaffItemHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.home_item_staff;
    }

}
