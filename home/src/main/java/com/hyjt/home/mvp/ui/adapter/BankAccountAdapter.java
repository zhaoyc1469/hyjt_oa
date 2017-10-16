package com.hyjt.home.mvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyjt.home.R;
import com.hyjt.home.mvp.model.entity.Resp.PLBankAccountResp;

import java.util.List;

/**
 * Created by Administrator on 2017/10/15/015.
 */

public class BankAccountAdapter extends BaseAdapter {


    private List<PLBankAccountResp.BankPackBean> bankActList;
    private LayoutInflater mInflater;

    public BankAccountAdapter(Context context, List<PLBankAccountResp.BankPackBean> bankActList) {
        this.bankActList = bankActList;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return bankActList.size();
    }

    @Override
    public Object getItem(int position) {
        return bankActList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.home_bank_sel_item, null);
            holder = new ViewHolder();
            holder.accountName = (TextView) convertView.findViewById(R.id.tv_account_name);
            holder.accountBank = (TextView) convertView.findViewById(R.id.tv_account_bank);
            holder.accountNum = (TextView) convertView.findViewById(R.id.tv_account_num);
            holder.rlItem = (RelativeLayout) convertView.findViewById(R.id.rl_item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.accountName.setText("户名：" + bankActList.get(position).getBankPerson());
        holder.accountBank.setText("开户银行：" + bankActList.get(position).getBankName());
        holder.accountNum.setText("账户：" + bankActList.get(position).getBankNum());
        holder.rlItem.setOnClickListener(v -> {
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

    public interface onItemClickListener {
        void onItemClick(int position);
    }

    class ViewHolder {
        TextView accountNum;
        TextView accountName;
        TextView accountBank;
        RelativeLayout rlItem;
    }


}
