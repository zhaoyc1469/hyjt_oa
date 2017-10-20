package com.hyjt.home.mvp.ui.holder;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.hyjt.frame.base.BaseApplication;
import com.hyjt.frame.base.BaseHolder;
import com.hyjt.home.R;
import com.hyjt.home.mvp.model.entity.Resp.CEmailListResp;

/**
 * @author 赵宇驰
 * @time 2017/7/21  10:12
 * @desc ${TODO}
 */
public class EmailItemHolder extends BaseHolder<CEmailListResp.RowsBean> {


    private final TextView emTitle;
    private final TextView emCreater;
    private final TextView emDate;
    private final Context context;
    private String type;

    public EmailItemHolder(View v, String type) {
        super(v);
        context = v.getContext();
        emTitle = (TextView) v.findViewById(R.id.tv_em_title);
        emCreater = (TextView) v.findViewById(R.id.tv_em_creater);
        emDate = (TextView) v.findViewById(R.id.tv_em_date);
        this.type = type;
    }

    @Override
    public void setData(CEmailListResp.RowsBean data, int position) {
        emTitle.setText(data.getTheme());
        emDate.setText(data.getCreateTime());

        if (!"已读".equals(data.getOpened()) && !"SendBox".equals(type)){
            emTitle.setTextColor(ContextCompat.getColor(context, R.color.home_common_FF2626));
        } else {
            emTitle.setTextColor(ContextCompat.getColor(BaseApplication.getContext(), R.color.home_common_000000));
        }

        if( "SendBox".equals(type)){
            emCreater.setText(data.getName());
        } else {
            emCreater.setText(data.getSender());
        }
    }


}
