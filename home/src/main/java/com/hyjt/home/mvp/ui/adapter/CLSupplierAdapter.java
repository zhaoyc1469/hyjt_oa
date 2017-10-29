package com.hyjt.home.mvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyjt.home.R;
import com.hyjt.home.mvp.model.entity.Resp.CpSupplierListResp;

import java.util.List;


public class CLSupplierAdapter extends BaseAdapter {


    private List<CpSupplierListResp.SupPackBean> cpSupplierList;
    private LayoutInflater mInflater;

    public CLSupplierAdapter(Context context, List<CpSupplierListResp.SupPackBean> cpContractList) {
        this.cpSupplierList = cpContractList;
        mInflater = LayoutInflater.from(context);//Supplier
    }

    @Override
    public int getCount() {
        return cpSupplierList.size();
    }

    @Override
    public Object getItem(int position) {
        return cpSupplierList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CpSupplierListResp.SupPackBean supPackBean = cpSupplierList.get(position);

        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.home_dialog_sel_supplier, null);
            holder = new ViewHolder();

            holder.mRlItemSupplier = (RelativeLayout) convertView.findViewById(R.id.rl_item_supplier);
            holder.mTvSupplierName = (TextView) convertView.findViewById(R.id.tv_supplier_name);
            holder.mTvOpenBank = (TextView) convertView.findViewById(R.id.tv_open_bank);
            holder.mTvBankAccount = (TextView) convertView.findViewById(R.id.tv_bank_account);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mTvSupplierName.setText(supPackBean.getCwCSupplierI());
        holder.mTvOpenBank.setText(supPackBean.getCwCSupbank());
        holder.mTvBankAccount.setText(supPackBean.getCwCSupnum());
        holder.mRlItemSupplier.setOnClickListener(v -> {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(position);
            }
        });

        return convertView;
    }

    private onItemClickListener mOnItemClickListener = null;

    public void setItemClickListener(onItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    private void initView() {
    }

    public interface onItemClickListener {
        void onItemClick(int position);
    }

    class ViewHolder {
        RelativeLayout mRlItemSupplier;
        TextView mTvSupplierName;
        TextView mTvOpenBank;
        TextView mTvBankAccount;
    }


}
