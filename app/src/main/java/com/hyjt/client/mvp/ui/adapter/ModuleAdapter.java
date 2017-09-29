package com.hyjt.client.mvp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hyjt.app.R;
import com.hyjt.client.mvp.ui.adapter.Bean.ModuleBean;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;

/**
 * Created by Administrator on 2017/9/29.
 */

public class ModuleAdapter extends RecyclerView.Adapter {

    List<ModuleBean> moduleBeen;

    public ModuleAdapter(List<ModuleBean> moduleBeen) {
        this.moduleBeen = moduleBeen;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0){
            LayoutInflater.from(parent.getContext()).inflate(R.layout.item_work_title, parent, false);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return moduleBeen.get(position).getType();
    }
}
