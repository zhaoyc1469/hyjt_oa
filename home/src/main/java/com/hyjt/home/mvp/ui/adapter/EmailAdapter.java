package com.hyjt.home.mvp.ui.adapter;

import android.view.View;

import com.hyjt.frame.base.BaseHolder;
import com.hyjt.frame.base.DefaultAdapter;
import com.hyjt.home.R;
import com.hyjt.home.mvp.model.entity.Resp.CEmailListResp;
import com.hyjt.home.mvp.ui.holder.EmailItemHolder;

import java.util.List;

/**
 * @author 赵宇驰
 * @time 2017/8/23  17:06
 * @desc ${TODO}
 */

public class EmailAdapter extends DefaultAdapter<CEmailListResp.RowsBean> {

    private String type;

    public EmailAdapter(List<CEmailListResp.RowsBean> infos, String type) {
        super(infos);
        this.type = type;
    }

    @Override
    public BaseHolder<CEmailListResp.RowsBean> getHolder(View v, int viewType) {
        return new EmailItemHolder(v, type);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.home_item_email;
    }
}
