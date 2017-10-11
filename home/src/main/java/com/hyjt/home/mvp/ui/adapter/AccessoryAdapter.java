package com.hyjt.home.mvp.ui.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.hyjt.home.R;
import com.hyjt.home.mvp.model.entity.AccessoryResq;

import java.util.List;

public class AccessoryAdapter extends RecyclerView.Adapter<AccessoryAdapter.VH> {

    private List<AccessoryResq> AccessoryList;

    public AccessoryAdapter(List<AccessoryResq> accessoryList) {
        AccessoryList = accessoryList;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_item_filepack, parent, false));
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        AccessoryResq accessoryResq = AccessoryList.get(position);
        holder.mTvCertificate.setText(accessoryResq.getName());
        holder.mTvCertificate.setOnClickListener(v -> {
            if (OnClickListener != null) {
                OnClickListener.onItemClick(holder.getAdapterPosition(), accessoryResq);
            }
        });
        holder.mBtnDelFile.setOnClickListener(v -> {
            if (OnClickListener != null) {
                OnClickListener.onDelClick(holder.getAdapterPosition(), accessoryResq);
            }
        });
    }

    @Override
    public int getItemCount() {
        return AccessoryList.size();
    }

    private OnItemClickListener OnClickListener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.OnClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, AccessoryResq accessoryResq);

        void onDelClick(int position, AccessoryResq accessoryResq);
    }

    class VH extends RecyclerView.ViewHolder {
        private TextView mTvCertificate;
        private Button mBtnDelFile;

        VH(View itemView) {
            super(itemView);
            mTvCertificate = (TextView) itemView.findViewById(R.id.tv_certificate);
            mBtnDelFile = (Button) itemView.findViewById(R.id.btn_del_file);
        }
    }
}
