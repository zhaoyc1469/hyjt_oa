package com.hyjt.home.mvp.ui.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.hyjt.home.R;

/**
 * @author 赵宇驰
 * @time 2017/7/18  21:30
 * @desc ${TODO}
 */

public class StaffStatePop extends PopupWindow {

    private View mMenuView; // PopupWindow 菜单布局
    private Context context; // 上下文参数
    private View.OnClickListener listener;
    private LinearLayout mLlOnJob;
    private LinearLayout mLlPractice;
    private LinearLayout mLlPartJob;
    private LinearLayout mLlHoliday;
    private LinearLayout mLlLeaveOffice;

    public StaffStatePop(Activity context, View.OnClickListener listener) {
        super(context);
        this.context = context;
        this.listener = listener;
        Init();
    }

    private void Init() {
        // PopupWindow 导入
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.home_staff_state_sel, null);

        mLlOnJob = (LinearLayout) mMenuView.findViewById(R.id.ll_on_job);
        mLlPractice = (LinearLayout) mMenuView.findViewById(R.id.ll_practice);
        mLlPartJob = (LinearLayout) mMenuView.findViewById(R.id.ll_part_job);
        mLlHoliday = (LinearLayout) mMenuView.findViewById(R.id.ll_holiday);
        mLlLeaveOffice = (LinearLayout) mMenuView.findViewById(R.id.ll_leave_office);

        mLlOnJob.setOnClickListener(listener);
        mLlPractice.setOnClickListener(listener);
        mLlPartJob.setOnClickListener(listener);
        mLlHoliday.setOnClickListener(listener);
        mLlLeaveOffice.setOnClickListener(listener);


        // 导入布局
        this.setContentView(mMenuView);
        // 设置动画效果
        this.setAnimationStyle(R.style.home_popwindow_anim_style);
        //防止虚拟键挡住
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        //设置弹出窗体的 宽，高
        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置可触
        this.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0x0000000);
        this.setBackgroundDrawable(dw);
        // 单击弹出窗以外处 关闭弹出窗
        mMenuView.setOnTouchListener((v, event) -> {

            int height = mMenuView.findViewById(R.id.pop_layout).getTop();
            int y = (int) event.getY();
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (y < height) {
                    dismiss();
                }
            }
            return true;
        });
    }
}
