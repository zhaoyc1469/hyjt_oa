package com.hyjt.home.mvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyjt.home.R;
import com.hyjt.home.mvp.model.entity.Resp.CpContractListResp;
import com.hyjt.home.mvp.model.entity.Resp.CpSupplierListResp;
import com.hyjt.home.mvp.model.entity.Resp.PLFristLeaderResp;

import java.util.List;


public class CLContractAdapter extends BaseAdapter {


    private List<CpContractListResp.RowsBean> cpContractList;
    private LayoutInflater mInflater;

    public CLContractAdapter(Context context, List<CpContractListResp.RowsBean> cpContractList) {
        this.cpContractList = cpContractList;
        mInflater = LayoutInflater.from(context);//Supplier
    }

    @Override
    public int getCount() {
        return cpContractList.size();
    }

    @Override
    public Object getItem(int position) {
        return cpContractList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CpContractListResp.RowsBean rowsBean = cpContractList.get(position);

        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.home_dialog_sel_contract, null);
            holder = new ViewHolder();
            holder.mRlItemSupplier = (RelativeLayout) convertView.findViewById(R.id.rl_item_supplier);
            holder.mTvSupplierName = (TextView) convertView.findViewById(R.id.tv_supplier_name);
            holder.mTvContractNum = (TextView) convertView.findViewById(R.id.tv_contract_num);
            holder.mTvContractName = (TextView) convertView.findViewById(R.id.tv_contract_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mTvSupplierName.setText("供应商名称：" + rowsBean.getCwCSupplierI());
        holder.mTvContractName.setText("合同名称：" + rowsBean.getCwCOname());
        holder.mTvContractNum.setText(rowsBean.getCwCSupnum());
        holder.mRlItemSupplier.setOnClickListener(v -> {
            if (mOnItemClickListener != null){
                mOnItemClickListener.onItemClick(position);
            }
        });

        return convertView;
    }

    private onItemClickListener mOnItemClickListener = null;

    public void setItemClickListener(onItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public interface onItemClickListener {
        void onItemClick(int position);
    }

    class ViewHolder {
        RelativeLayout mRlItemSupplier;
        TextView mTvSupplierName;
        TextView mTvContractNum;
        TextView mTvContractName;
    }


}
