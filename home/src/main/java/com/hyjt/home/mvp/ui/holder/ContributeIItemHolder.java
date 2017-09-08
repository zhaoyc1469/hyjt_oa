package com.hyjt.home.mvp.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.hyjt.frame.base.BaseHolder;
import com.hyjt.home.R;
import com.hyjt.home.mvp.model.entity.Resp.CIdeaListResp;
import com.hyjt.home.mvp.model.entity.Resp.SReportListResp;

/**
 * @author 赵宇驰
 * @time 2017/7/21  10:12
 * @desc ${TODO}
 */
public class ContributeIItemHolder extends BaseHolder<CIdeaListResp.RowsBean> {


    private final TextView rtNum;
    private final TextView rtType;
    private final TextView rtName;
    private final TextView rtTime;
    private String type;

    public ContributeIItemHolder(View v, String type) {
        super(v);
        rtNum = (TextView) v.findViewById(R.id.tv_rt_num);
        rtType = (TextView) v.findViewById(R.id.tv_rt_name);
        rtName = (TextView) v.findViewById(R.id.tv_rt_type);
        rtTime = (TextView) v.findViewById(R.id.tv_rt_time);
        this.type = type;
    }

    @Override
    public void setData(CIdeaListResp.RowsBean data, int position) {
        rtNum.setText(data.getNumber());
        rtType.setText(data.getState());
        rtTime.setText(data.getReportTime());

        if ("Mine".equals(type)){
            rtName.setText(data.getBoss());
        } else {
            rtName.setText(data.getReportPerson());
        }

    }


}
