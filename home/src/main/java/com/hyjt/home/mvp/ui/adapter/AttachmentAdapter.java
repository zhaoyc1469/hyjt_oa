package com.hyjt.home.mvp.ui.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.hyjt.home.R;

import java.util.List;

/**
 * @author 赵宇驰
 * @time 2017/7/17  17:11
 * @desc ${TODO}
 */

public class AttachmentAdapter extends RecyclerView.Adapter<AttachmentAdapter.VH> {

    private List<String> AttachmentName;
    private boolean ForbidEdit;

    public AttachmentAdapter(List<String> AttachmentName, boolean ForbidEdit) {
        this.AttachmentName = AttachmentName;
        this.ForbidEdit = ForbidEdit;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_item_file, parent, false));
    }

    @Override
    public int getItemCount() {
        return AttachmentName.size();
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {

        holder.fileName.setText(AttachmentName.get(position));
        if (ForbidEdit)
            holder.delFile.setVisibility(View.GONE);
        holder.delFile.setOnClickListener(v -> {
            if (mDelFileListener != null){
                mDelFileListener.onDelClick(holder.getAdapterPosition());
            }
        });

    }


    private DelFileListener mDelFileListener = null;

    public void setDelFileListenerr(DelFileListener listener) {
        this.mDelFileListener = listener;
    }
    public interface DelFileListener {
        void onDelClick(int position);
    }

    class VH extends RecyclerView.ViewHolder {

        TextView fileName;
        Button delFile;

        VH(View itemView) {
            super(itemView);
            fileName = (TextView) itemView.findViewById(R.id.tv_cm_name);
            delFile = (Button) itemView.findViewById(R.id.btn_del_file);
        }
    }
}
