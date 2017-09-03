package com.hyjt.home.mvp.ui.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.hyjt.home.R;

import java.util.Calendar;
import java.util.List;

/**
 * @author 赵宇驰
 * @time 2017/7/20  13:54
 * @desc ${TODO}
 */

public class StaffMsgPop extends PopupWindow {
    private View mMenuView; // PopupWindow 菜单布局
    private Context context; // 上下文参数
    private LinearLayout mPopLayout;
    private EditText mEdtDeptName;
    private EditText mEdtStaffName;
    private EditText mEdtStaffEdu;
    private EditText mEdtPhonenum;
    private EditText mEdtOtherPhonenum;
    private EditText mEdtWorkPhonenum;
    private List<String> cell;



    public StaffMsgPop(Activity context, List<String> cell) {
        super(context);
        this.context = context;
        this.cell = cell;
        Init();
    }

    private void Init() {
        // PopupWindow 导入
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.home_staff_msg_pop, null);


        mPopLayout = (LinearLayout) mMenuView.findViewById(R.id.pop_layout);
        mEdtDeptName = (EditText) mMenuView.findViewById(R.id.edt_dept_name);
        mEdtStaffName = (EditText) mMenuView.findViewById(R.id.edt_staff_name);
        mEdtStaffEdu = (EditText) mMenuView.findViewById(R.id.edt_staff_edu);
        mEdtPhonenum = (EditText) mMenuView.findViewById(R.id.edt_phonenum);
        mEdtOtherPhonenum = (EditText) mMenuView.findViewById(R.id.edt_other_phonenum);
        mEdtWorkPhonenum = (EditText) mMenuView.findViewById(R.id.edt_work_phonenum);

        mEdtDeptName.setText(cell.get(1));
        mEdtStaffName.setText(cell.get(2));
        mEdtStaffEdu.setText(cell.get(3));
        mEdtPhonenum.setText(cell.get(4));
        mEdtOtherPhonenum.setText(cell.get(5));
        mEdtWorkPhonenum.setText(cell.get(6));
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

    private void setTimeEdit(EditText finishTime) {
        finishTime.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                Calendar c = Calendar.getInstance();
                new DatePickDialog(context, c).setOnDateTimeSetListener((dp, year, monthOfYear, dayOfMonth) -> {
                    finishTime.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    // 保存选择后时间
                    c.set(Calendar.YEAR, year);
                    c.set(Calendar.MONTH, monthOfYear);
                    c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                });
            }
            return true;
        });
    }

    private SelStafListener mSelStafListener = null;

    public void setSelStafListener(SelStafListener listener) {
        this.mSelStafListener = listener;
    }

    public interface SelStafListener {
        //        void onSelStafClick(String deptId, String name, String education, String startTime, String endTime);
        void onSelStafClick(String deptId, String name);
    }
}