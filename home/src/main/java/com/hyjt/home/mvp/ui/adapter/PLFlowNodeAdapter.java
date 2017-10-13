package com.hyjt.home.mvp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyjt.home.R;
import com.hyjt.home.mvp.model.entity.Resp.PsonLoanDetailResp;

import java.util.List;


public class PLFlowNodeAdapter extends RecyclerView.Adapter<PLFlowNodeAdapter.VH> {

    List<PsonLoanDetailResp.FlowPackBean> flowPackList;

    public PLFlowNodeAdapter(List<PsonLoanDetailResp.FlowPackBean> flowPackList) {
        this.flowPackList = flowPackList;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_item_plflownode, parent, false));
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return flowPackList.size();
    }

    class VH extends RecyclerView.ViewHolder{
        public VH(View itemView) {
            super(itemView);
        }
    }
}
