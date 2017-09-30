package com.hyjt.client.mvp.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyjt.app.R;
import com.hyjt.client.mvp.model.entity.Bean.ModuleBean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/29/029.
 */

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
    }

    @Override
    public int getItemCount() {
        return moduleBeanList.size();
    }


    class ModuleVH extends RecyclerView.ViewHolder {
        private ImageView ivModuleIcon;
        private RelativeLayout rlModuleMsg;
        private TextView tvModuleMsg;
        private TextView tvModuleName;

        public ModuleVH(View itemView) {
            super(itemView);
            ivModuleIcon = (ImageView) itemView.findViewById(R.id.iv_module_icon);
            rlModuleMsg = (RelativeLayout) itemView.findViewById(R.id.rl_module_msg);
            tvModuleMsg = (TextView) itemView.findViewById(R.id.tv_module_msg);
            tvModuleName = (TextView) itemView.findViewById(R.id.tv_module_name);
        }
    }

    public int getImageId(String imageName) {
        Context ctx = context.getBaseContext();
        int resId = ctx.getResources().getIdentifier(imageName, "mipmap", ctx.getPackageName());
        return resId;
    }
}
