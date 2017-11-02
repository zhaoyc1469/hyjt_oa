package com.hyjt.home.mvp.ui.adapter;

import android.view.View;

import com.hyjt.frame.base.BaseHolder;
import com.hyjt.frame.base.DefaultAdapter;
import com.hyjt.home.R;
import com.hyjt.home.mvp.model.entity.Resp.ApplyExpenseListResp;
import com.hyjt.home.mvp.model.entity.Resp.CompLoanListResp;
import com.hyjt.home.mvp.ui.holder.ApplyExpenseItemHolder;
import com.hyjt.home.mvp.ui.holder.CompLoanItemHolder;

import java.util.List;


public class ApplyExpenseAdapter extends DefaultAdapter<ApplyExpenseListResp.RowsBean> {


    public ApplyExpenseAdapter(List<ApplyExpenseListResp.RowsBean> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<ApplyExpenseListResp.RowsBean> getHolder(View v, int viewType) {
        return new ApplyExpenseItemHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.home_item_applyexpense;
    }

}
