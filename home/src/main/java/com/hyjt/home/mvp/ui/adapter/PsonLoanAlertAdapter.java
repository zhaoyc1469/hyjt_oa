package com.hyjt.home.mvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyjt.home.R;
import com.hyjt.home.mvp.model.entity.Resp.CompLoanListResp;
import com.hyjt.home.mvp.model.entity.Resp.PsonLoanListResp;
import com.hyjt.home.mvp.ui.activity.ApplyExpenseCreateActivity;

import java.util.List;

/**
 * Created by Administrator on 2017/10/15/015.
 */

public class PsonLoanAlertAdapter extends BaseAdapter {


    private List<PsonLoanListResp.RowsBean> psonList;
    private LayoutInflater mInflater;

    public PsonLoanAlertAdapter(Context context, List<PsonLoanListResp.RowsBean> psonList) {
        this.psonList = psonList;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return psonList.size();
    }

    @Override
    public Object getItem(int position) {
        return psonList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.home_item_psonloan_alert, null);
            holder = new ViewHolder();
            holder.mTvPlNum = (TextView) convertView.findViewById(R.id.tv_pl_num);
            holder.mTvPlAmount = (TextView) convertView.findViewById(R.id.tv_pl_amount);
            holder.rlItem = (RelativeLayout) convertView.findViewById(R.id.rl_item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mTvPlNum.setText(psonList.get(position).getCwPnum());
        holder.mTvPlAmount.setText(psonList.get(position).getCwPmoney());
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

    private void initView() {
    }

    public interface onItemClickListener {
        void onItemClick(int position);
    }

    class ViewHolder {
        TextView mTvPlNum;
        TextView mTvPlAmount;
        RelativeLayout rlItem;
    }


}
