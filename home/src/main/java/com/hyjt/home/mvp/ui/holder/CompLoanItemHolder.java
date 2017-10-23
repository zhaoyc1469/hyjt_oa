package com.hyjt.home.mvp.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.hyjt.frame.base.BaseHolder;
import com.hyjt.home.R;
import com.hyjt.home.mvp.model.entity.Resp.CompLoanListResp;
import com.hyjt.home.mvp.model.entity.Resp.PsonLoanListResp;

/**
 * Created by Administrator on 2017/10/10.
 */

public class CompLoanItemHolder extends BaseHolder<CompLoanListResp.RowsBean> {

    private TextView mTvClNum;
    private TextView mTvClAmount;
    private TextView mTvClType;
    private TextView mTvClTime;

    public CompLoanItemHolder(View itemView) {
        super(itemView);
        mTvClNum = (TextView) itemView.findViewById(R.id.tv_cl_num);
        mTvClAmount = (TextView) itemView.findViewById(R.id.tv_cl_amount);
        mTvClType = (TextView) itemView.findViewById(R.id.tv_cl_type);
        mTvClTime = (TextView) itemView.findViewById(R.id.tv_cl_time);
    }

    @Override
    public void setData(CompLoanListResp.RowsBean data, int position) {
        mTvClNum.setText(data.getCwPnum());
        mTvClAmount.setText(data.getCwPmoney());
        mTvClType.setText(data.getCwJKState());
        mTvClTime.setText(data.getSqDate());
    }
}
