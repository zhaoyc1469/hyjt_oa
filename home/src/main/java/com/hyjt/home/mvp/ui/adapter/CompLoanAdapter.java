package com.hyjt.home.mvp.ui.adapter;

import android.view.View;

import com.hyjt.frame.base.BaseHolder;
import com.hyjt.frame.base.DefaultAdapter;
import com.hyjt.home.R;
import com.hyjt.home.mvp.model.entity.Resp.CompLoanListResp;
import com.hyjt.home.mvp.model.entity.Resp.PsonLoanListResp;
import com.hyjt.home.mvp.ui.holder.CompLoanItemHolder;
import com.hyjt.home.mvp.ui.holder.PsonLoanItemHolder;

import java.util.List;


public class CompLoanAdapter extends DefaultAdapter<CompLoanListResp.RowsBean> {


    public CompLoanAdapter(List<CompLoanListResp.RowsBean> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<CompLoanListResp.RowsBean> getHolder(View v, int viewType) {
        return new CompLoanItemHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.home_item_comploan;
    }

}
