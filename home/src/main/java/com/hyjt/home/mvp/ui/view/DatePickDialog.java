package com.hyjt.home.mvp.ui.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;
import android.widget.Toast;

import com.hyjt.home.R;

import java.util.Calendar;

public class DatePickDialog implements OnDateChangedListener, OnTimeChangedListener {

    private Context mContext;
    private DatePicker dp;
    private int year;
    private int monthOfYear;
    private int dayOfMonth;
    private Calendar c;
    private OnDateTimeSetListener mListener;

    public DatePickDialog(Context context, Calendar c) {
        this.mContext = context;
        this.c = c;
        year = c.get(Calendar.YEAR);
        monthOfYear = c.get(Calendar.MONTH);
        dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(mContext).inflate(
                R.layout.home_comm_date, null);
        dp = (DatePicker) view.findViewById(R.id.datepicker);
        dp.init(year, monthOfYear, dayOfMonth, this);
        AlertDialog.Builder b = new AlertDialog.Builder(mContext);
        b.setTitle("请选择时间");
        b.setView(view);
        //点击确定，回调数据
        b.setPositiveButton("确定", new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(
                        mContext,
                        "" + year + "-" + (monthOfYear + 1) + "-" + dayOfMonth,
                        Toast.LENGTH_SHORT).show();
                if (null != mListener) {
                    mListener.onDateTimeSet(dp, year, monthOfYear, dayOfMonth);
                }
            }
        });
        //取消后恢复原来选择的时间
        b.setNegativeButton("取消", new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                if (null != mListener) {
                    mListener.onDateTimeSet(dp, c.get(Calendar.YEAR),
                            c.get(Calendar.MONTH),
                            c.get(Calendar.DAY_OF_MONTH));
                }
            }
        });
        b.create().show();

    }

    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {


    }

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
        // TODO Auto-generated method stub
        this.dp = view;
        this.year = year;
        this.monthOfYear = monthOfYear;
        this.dayOfMonth = dayOfMonth;

    }

    public void setOnDateTimeSetListener(OnDateTimeSetListener listener) {
        this.mListener = listener;
    }

    public interface OnDateTimeSetListener {
        void onDateTimeSet(DatePicker dp, int year,
                           int monthOfYear, int dayOfMonth);
    }
}