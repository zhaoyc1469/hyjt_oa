package com.hyjt.home.mvp.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.hyjt.frame.base.BaseHolder;
import com.hyjt.home.R;
import com.hyjt.home.mvp.model.entity.Resp.MeetingListResp;

/**
 * @author 赵宇驰
 * @time 2017/7/21  10:12
 * @desc ${TODO}
 */
public class MeetingItemHolder extends BaseHolder<MeetingListResp.RowsBean> {

    private final TextView department;
    private final TextView cmTime;
    private final TextView cmName;
    private final TextView cmNum;

    public MeetingItemHolder(View v) {
        super(v);
        cmNum = (TextView) v.findViewById(R.id.tv_cm_num);
        cmName = (TextView) v.findViewById(R.id.tv_rt_name);
        cmTime = (TextView) v.findViewById(R.id.tv_cm_time);
        department = (TextView) v.findViewById(R.id.tv_cm_department);
    }

    @Override
    public void setData(MeetingListResp.RowsBean data, int position) {

        cmNum.setText(data.getCM_Num());
        cmName.setText(data.getCM_name());
        cmTime.setText(data.getCM_starttime());
        department.setText(data.getCM_department());

    }
}
