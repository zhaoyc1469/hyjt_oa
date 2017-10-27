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
import com.hyjt.home.mvp.model.entity.Resp.PLFristLeaderResp;

import java.util.List;

/**
 * Created by Administrator on 2017/10/15/015.
 */

public class CLContractAdapter extends BaseAdapter {


    private List<CpContractListResp.RowsBean> cpContractList;
    private LayoutInflater mInflater;

    public CLContractAdapter(Context context, List<CpContractListResp.RowsBean> cpContractList) {
        this.cpContractList = cpContractList;
        mInflater = LayoutInflater.from(context);
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

        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.home_dialog_sel_item, null);
            holder = new ViewHolder();
            holder.simpleText = (TextView) convertView.findViewById(R.id.tv_simple_text);
            holder.rlItem = (RelativeLayout) convertView.findViewById(R.id.rl_item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.simpleText.setText(cpContractList.get(position).getCwCOnum());
        holder.rlItem.setOnClickListener(v -> {
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
        TextView simpleText;
        RelativeLayout rlItem;
    }


}
