package com.hyjt.client.mvp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hyjt.app.R;
import com.hyjt.db.bean.ModuleBeanDb;

import java.util.List;

/**
 * Created by Administrator on 2017/9/30.
 */

public class ModuleEditAdapter extends RecyclerView.Adapter<ModuleEditAdapter.EditVH> {

    List<ModuleBeanDb> moduleBeanDbList;

    public ModuleEditAdapter(List<ModuleBeanDb> moduleBeanDbList) {
        this.moduleBeanDbList = moduleBeanDbList;
    }

    @Override
    public EditVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EditVH(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_work_module_edit, parent, false));
    }

    @Override
    public void onBindViewHolder(EditVH holder, int position) {
        ModuleBeanDb moduleBeanDb = moduleBeanDbList.get(position);
        holder.mTvModuleName.setText(moduleBeanDb.getName());
        holder.mTvAddOrReduce.setText(moduleBeanDb.getIsShow()? "删除":"添加");
        holder.mTvAddOrReduce.setOnClickListener(v -> {
            if (mOnItemClickListener != null){
                mOnItemClickListener.onItemClick(v, holder.getAdapterPosition(), moduleBeanDb);
                if (holder.mTvAddOrReduce.getText().toString().equals("删除")){
                    holder.mTvAddOrReduce.setText("添加");
                } else {
                    holder.mTvAddOrReduce.setText("删除");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return moduleBeanDbList.size();
    }

    class EditVH extends RecyclerView.ViewHolder {
        private TextView mTvModuleName;
        private TextView mTvAddOrReduce;
        public EditVH(View itemView) {
            super(itemView);
            mTvModuleName = (TextView) itemView.findViewById(R.id.tv_module_name);
            mTvAddOrReduce = (TextView) itemView.findViewById(R.id.tv_add_or_reduce);
        }
    }

    //声明一个接口
    private OnItemClickListener mOnItemClickListener = null;

    //提供一个方法去赋值
    public void setOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(View itemView, int position, ModuleBeanDb moduleBeanDb);
    }
}
