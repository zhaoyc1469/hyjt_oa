package com.hyjt.home.mvp.ui.adapter;

import android.view.View;

import com.hyjt.frame.base.BaseHolder;
import com.hyjt.frame.base.DefaultAdapter;
import com.hyjt.home.R;
import com.hyjt.home.mvp.model.entity.Resp.ReportTListResp;
import com.hyjt.home.mvp.model.entity.Resp.SReportListResp;
import com.hyjt.home.mvp.ui.holder.ReportTopItemHolder;
import com.hyjt.home.mvp.ui.holder.SReportItemHolder;

import java.util.List;

/**
 * @author 赵宇驰
 * @time 2017/8/23  17:06
 * @desc ${TODO}
 */

public class SkipReportAdapter extends DefaultAdapter<SReportListResp.RowsBean> {

    private String type;

    public SkipReportAdapter(List<SReportListResp.RowsBean> infos, String type) {
        super(infos);
        this.type = type;
    }

    @Override
    public BaseHolder<SReportListResp.RowsBean> getHolder(View v, int viewType) {
        return new SReportItemHolder(v, type);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.home_item_report_top;
    }
}
