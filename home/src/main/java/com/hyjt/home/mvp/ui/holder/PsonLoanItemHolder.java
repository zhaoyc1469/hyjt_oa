package com.hyjt.home.mvp.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.hyjt.frame.base.BaseHolder;
import com.hyjt.home.R;
import com.hyjt.home.mvp.model.entity.Resp.PsonLoanListResp;
import com.hyjt.home.mvp.model.entity.Resp.ReportTListResp;

/**
 * Created by Administrator on 2017/10/10.
 */

public class PsonLoanItemHolder extends BaseHolder<PsonLoanListResp.RowsBean> {

    private TextView mTvPlNum;
    private TextView mTvPlAmount;
    private TextView mTvPlType;
    private TextView mTvPlTime;

    public PsonLoanItemHolder(View itemView) {
        super(itemView);
        mTvPlNum = (TextView) itemView.findViewById(R.id.tv_pl_num);
        mTvPlAmount = (TextView) itemView.findViewById(R.id.tv_pl_amount);
        mTvPlType = (TextView) itemView.findViewById(R.id.tv_pl_type);
        mTvPlTime = (TextView) itemView.findViewById(R.id.tv_pl_time);
    }

    @Override
    public void setData(PsonLoanListResp.RowsBean data, int position) {
        mTvPlNum.setText(data.getCwPnum());
        mTvPlAmount.setText(data.getCwPmoney());
        mTvPlType.setText(data.getCwJKState());
        mTvPlTime.setText(data.getSqDate());
    }
}
