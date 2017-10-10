package com.hyjt.home.mvp.ui.adapter;

import android.view.View;
import android.widget.TextView;

import com.hyjt.frame.base.BaseHolder;
import com.hyjt.frame.base.DefaultAdapter;
import com.hyjt.home.R;
import com.hyjt.home.mvp.model.entity.Resp.PsonLoanListResp;
import com.hyjt.home.mvp.ui.holder.PsonLoanItemHolder;

import java.util.List;


public class PsonLoanAdapter extends DefaultAdapter<PsonLoanListResp.RowsBean> {


    public PsonLoanAdapter(List<PsonLoanListResp.RowsBean> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<PsonLoanListResp.RowsBean> getHolder(View v, int viewType) {
        return new PsonLoanItemHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.home_item_psonloan;
    }

}
