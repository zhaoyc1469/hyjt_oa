package com.hyjt.client.mvp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyjt.app.R;
import com.hyjt.client.mvp.ui.adapter.Bean.ModuleBean;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;

public class ModuleText1Adapter extends RecyclerView.Adapter {

    List<ModuleBean> moduleBeen;

    public ModuleText1Adapter(List<ModuleBean> moduleBeen) {
        this.moduleBeen = moduleBeen;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0){
            return new TitleHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_work_title, parent, false));
        } else {
            return new ModuleHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_work_title, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return moduleBeen.size();
    }

    @Override
    public int getItemViewType(int position) {
//        return moduleBeen.get(position).getType();
        return 1;
    }

    private class TitleHolder extends RecyclerView.ViewHolder {
        TitleHolder(View itemView) {
            super(itemView);
        }
    }

    private class ModuleHolder extends RecyclerView.ViewHolder {
        ModuleHolder(View itemView) {
            super(itemView);
        }
    }

}
