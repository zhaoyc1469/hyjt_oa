package com.hyjt.home.mvp.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyjt.home.R;
import com.hyjt.home.mvp.model.entity.Resp.PLFristLeaderResp;
import java.util.List;

/**
 * Created by Administrator on 2017/10/15/015.
 */

public class FrstLdAdapter extends BaseAdapter {


    private List<PLFristLeaderResp.FlowDetailsBean> flowDetailsList;
    private LayoutInflater mInflater;

    public FrstLdAdapter(Context context, List<PLFristLeaderResp.FlowDetailsBean> flowDetailsList) {
        this.flowDetailsList = flowDetailsList;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return flowDetailsList.size();
    }

    @Override
    public Object getItem(int position) {
        return flowDetailsList.get(position);
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

        holder.simpleText.setText(flowDetailsList.get(position).getLeader());
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
