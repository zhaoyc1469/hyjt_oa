package com.hyjt.client.mvp.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyjt.app.R;
import com.hyjt.client.mvp.model.entity.Bean.ModuleBean;

import java.util.List;


public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ModuleVH> {

    private List<ModuleBean> moduleBeanList;
    private Activity context;


    public ModuleAdapter(List<ModuleBean> moduleBeanList, Activity context) {
        this.moduleBeanList = moduleBeanList;
        this.context = context;
    }

    @Override
    public ModuleVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ModuleVH(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_work_module, parent, false));
    }

    @Override
    public void onBindViewHolder(ModuleVH holder, int position) {
        ModuleBean moduleBean = moduleBeanList.get(position);
        holder.tvModuleName.setText(moduleBean.getName());
        holder.ivModuleIcon.setBackgroundResource(getImageId(moduleBean.getImg()));
        if (moduleBean.getMsg() != null && moduleBean.getMsg() > 0) {
            holder.rlModuleMsg.setVisibility(View.VISIBLE);
            holder.tvModuleMsg.setText(String.valueOf(moduleBean.getMsg()));
        }
        holder.llModule.setOnClickListener(v -> {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v, holder.getAdapterPosition(), moduleBean);
            }
        });
        if (moduleBean.isShow_del()){
            holder.rlModuleDel.setVisibility(View.VISIBLE);
        }
        holder.rlModuleDel.setOnClickListener(v -> {
//            if (mOnItemClickListener != null) {
//                mOnItemClickListener.onItemClick(v, holder.getAdapterPosition(), moduleBean);
//            }
        });
    }

    @Override
    public int getItemCount() {
        return moduleBeanList.size();
    }


    class ModuleVH extends RecyclerView.ViewHolder {
        private LinearLayout llModule;
        private RelativeLayout rlModuleMsg;
        private RelativeLayout rlModuleDel;
        private ImageView ivModuleIcon;
        private TextView tvModuleMsg;
        private TextView tvModuleName;

        ModuleVH(View itemView) {
            super(itemView);
            ivModuleIcon = (ImageView) itemView.findViewById(R.id.iv_module_icon);
            rlModuleMsg = (RelativeLayout) itemView.findViewById(R.id.rl_module_msg);
            rlModuleDel = (RelativeLayout) itemView.findViewById(R.id.rl_module_del);
            tvModuleMsg = (TextView) itemView.findViewById(R.id.tv_module_msg);
            llModule = (LinearLayout) itemView.findViewById(R.id.ll_module);
            tvModuleName = (TextView) itemView.findViewById(R.id.tv_module_name);
        }
    }

    private int getImageId(String imageName) {
        Context ctx = context.getBaseContext();
        return ctx.getResources().getIdentifier(imageName, "mipmap", ctx.getPackageName());
    }

    //声明一个接口
    private OnItemClickListener mOnItemClickListener = null;

    //提供一个方法去赋值
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position, ModuleBean moduleBean);
    }
}
