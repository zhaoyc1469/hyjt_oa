package com.hyjt.home.mvp.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.hyjt.frame.base.BaseHolder;
import com.hyjt.home.R;
import com.hyjt.home.mvp.model.entity.Resp.ApplyExpenseListResp;
import com.hyjt.home.mvp.model.entity.Resp.CompLoanListResp;

/**
 * Created by Administrator on 2017/10/10.
 */

public class ApplyExpenseItemHolder extends BaseHolder<ApplyExpenseListResp.RowsBean> {

    private TextView mTvAeNum;
    private TextView mTvAeAmount;
    private TextView mTvAeType;
    private TextView mTvAeTime;

    public ApplyExpenseItemHolder(View itemView) {
        super(itemView);
        mTvAeNum = (TextView) itemView.findViewById(R.id.tv_ae_num);
        mTvAeAmount = (TextView) itemView.findViewById(R.id.tv_ae_amount);
        mTvAeType = (TextView) itemView.findViewById(R.id.tv_ae_type);
        mTvAeTime = (TextView) itemView.findViewById(R.id.tv_ae_time);
    }

    @Override
    public void setData(ApplyExpenseListResp.RowsBean data, int position) {
        mTvAeNum.setText(data.getCwEnum());
        mTvAeAmount.setText(data.getCwEmoney());
        mTvAeType.setText(data.getCwEState());
        mTvAeTime.setText(data.getSqDate());
    }
}
