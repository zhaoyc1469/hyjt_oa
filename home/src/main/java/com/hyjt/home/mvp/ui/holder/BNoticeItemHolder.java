package com.hyjt.home.mvp.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.hyjt.frame.base.BaseHolder;
import com.hyjt.home.R;
import com.hyjt.home.mvp.model.entity.Resp.BlocNoticeListResp;

/**
 * @author 赵宇驰
 * @time 2017/7/21  10:12
 * @desc ${TODO}
 */
public class BNoticeItemHolder extends BaseHolder<BlocNoticeListResp.RowsBean> {


    private final TextView bnTitle;
    private final TextView bnDate;

    public BNoticeItemHolder(View v) {
        super(v);
        bnTitle = (TextView) v.findViewById(R.id.tv_bn_title);
        bnDate = (TextView) v.findViewById(R.id.tv_bn_date);
    }

    @Override
    public void setData(BlocNoticeListResp.RowsBean data, int position) {
        bnTitle.setText(data.getTitle());
        bnDate.setText(data.getCreateTime());
    }

}
