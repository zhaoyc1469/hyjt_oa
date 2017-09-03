package com.hyjt.home.mvp.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.hyjt.frame.base.BaseHolder;
import com.hyjt.home.R;
import com.hyjt.home.mvp.model.entity.Resp.StaffListResp;

/**
 * @author 赵宇驰
 * @time 2017/7/21  10:12
 * @desc ${TODO}
 */
public class StaffItemHolder extends BaseHolder<StaffListResp.RowsBean> {

    private TextView mTvSfDepartment;
    private TextView mTvSfName;
    private TextView mTvSfPosition;
    private TextView mTvSfPhonenum;

    public StaffItemHolder(View v) {
        super(v);
        mTvSfDepartment = (TextView) v.findViewById(R.id.tv_sf_department);
        mTvSfName = (TextView) v.findViewById(R.id.tv_sf_name);
        mTvSfPosition = (TextView) v.findViewById(R.id.tv_sf_position);
        mTvSfPhonenum = (TextView) v.findViewById(R.id.tv_sf_phonenum);
    }

    @Override
    public void setData(StaffListResp.RowsBean data, int position) {

        mTvSfDepartment.setText(data.getCell().get(1));
        mTvSfName.setText(data.getCell().get(2));
        mTvSfPosition.setText(data.getCell().get(3));
        mTvSfPhonenum.setText(data.getCell().get(4));

    }
}
