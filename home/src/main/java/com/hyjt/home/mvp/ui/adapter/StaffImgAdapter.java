package com.hyjt.home.mvp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hyjt.home.R;
import com.hyjt.home.mvp.model.entity.Resp.StaffMsgResp;

import java.util.List;

/**
 * @author 赵宇驰
 * @time 2017/8/22  13:58
 * @desc ${TODO}
 */

public class StaffImgAdapter extends RecyclerView.Adapter<StaffImgAdapter.VH> {

    private List<StaffMsgResp.FilePackBean> stafPicList;
    private Context context;

    public StaffImgAdapter(Context context, List<StaffMsgResp.FilePackBean> stafPicList) {
        this.stafPicList = stafPicList;
        this.context = context;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_item_staf_pic, parent, false));
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.mTvStafPicType.setText(stafPicList.get(position).getFileName());
        Glide.with(context).load(context.getString(R.string.home_base_url) +
                stafPicList.get(position).getFilePath()).into(holder.mIvStafPic);
        holder.mTvStafPicType.setOnClickListener(v -> {
            if (mShowBigListener != null)
                mShowBigListener.onShowClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return stafPicList.size();
    }

    private ShowBigListener mShowBigListener = null;

    public void setShowBigListener(ShowBigListener listener) {
        this.mShowBigListener = listener;
    }

    public interface ShowBigListener {
        void onShowClick(int position);
    }

    public class VH extends RecyclerView.ViewHolder {
        private ImageView mIvStafPic;
        private TextView mTvStafPicType;

        public VH(View itemView) {
            super(itemView);
            mIvStafPic = (ImageView) itemView.findViewById(R.id.iv_staf_pic);
            mTvStafPicType = (TextView) itemView.findViewById(R.id.tv_staf_pic_type);
        }
    }
}
