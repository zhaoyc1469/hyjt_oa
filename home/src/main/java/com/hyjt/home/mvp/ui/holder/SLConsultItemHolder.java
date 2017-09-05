package com.hyjt.home.mvp.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.hyjt.frame.base.BaseHolder;
import com.hyjt.home.R;
import com.hyjt.home.mvp.model.entity.Resp.SLConsultListResp;

/**
 * @author 赵宇驰
 * @time 2017/7/21  10:12
 * @desc ${TODO}
 */
public class SLConsultItemHolder extends BaseHolder<SLConsultListResp.RowsBean> {


    private final TextView rtNum;
    private final TextView rtType;
    private final TextView rtName;
    private final TextView rtTime;
    private String type;

    public SLConsultItemHolder(View v, String type) {
        super(v);
        rtNum = (TextView) v.findViewById(R.id.tv_rt_num);
        rtType = (TextView) v.findViewById(R.id.tv_rt_name);
        rtName = (TextView) v.findViewById(R.id.tv_rt_type);
        rtTime = (TextView) v.findViewById(R.id.tv_rt_time);
        this.type = type;
    }

    @Override
    public void setData(SLConsultListResp.RowsBean data, int position) {
        rtNum.setText(data.getPID());
        rtType.setText(data.getState());
        rtName.setText(data.getCreatePerson());
        rtTime.setText(data.getCreateTime());

    }


}
