package com.hyjt.home.mvp.ui.adapter;

import android.view.View;

import com.hyjt.frame.base.BaseHolder;
import com.hyjt.frame.base.DefaultAdapter;
import com.hyjt.home.R;
import com.hyjt.home.mvp.model.entity.Resp.CIdeaListResp;
import com.hyjt.home.mvp.model.entity.Resp.SReportListResp;
import com.hyjt.home.mvp.ui.holder.ContributeIItemHolder;
import com.hyjt.home.mvp.ui.holder.SReportItemHolder;

import java.util.List;

/**
 * @author 赵宇驰
 * @time 2017/8/23  17:06
 * @desc ${TODO}
 */

public class ContributeIAdapter extends DefaultAdapter<CIdeaListResp.RowsBean> {

    private String type;

    public ContributeIAdapter(List<CIdeaListResp.RowsBean> infos, String type) {
        super(infos);
        this.type = type;
    }

    @Override
    public BaseHolder<CIdeaListResp.RowsBean> getHolder(View v, int viewType) {
        return new ContributeIItemHolder(v, type);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.home_item_contribute_i;
    }
}
