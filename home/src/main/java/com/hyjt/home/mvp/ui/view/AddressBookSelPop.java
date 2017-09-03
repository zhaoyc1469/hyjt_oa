package com.hyjt.home.mvp.ui.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.hyjt.home.R;
import com.hyjt.home.mvp.model.entity.Reqs.StaffNameIdKey;
import com.hyjt.home.mvp.ui.view.treelistview.Node;

import java.util.Calendar;
import java.util.List;

/**
 * @author 赵宇驰
 * @time 2017/7/20  13:54
 * @desc ${TODO}
 */

public class AddressBookSelPop extends PopupWindow {
    private View mMenuView; // PopupWindow 菜单布局
    private Context context; // 上下文参数

    private EditText mEdtDeptName;
    private EditText mEdtStaffName;
    //    private EditText mEdtStaffEdu;
//    private EditText mEdtStartTime;
//    private EditText mEdtEndTime;
    private Button mBtnSel;
    private List<Node> deptList;
    private StaffNameIdKey dept;
    private DeptTreePop deptTreePop;
    private View viewById;

    public AddressBookSelPop(Activity context, List<Node> deptList, StaffNameIdKey dept, View viewById) {
        super(context);
        this.context = context;
        this.deptList = deptList;
        this.dept = dept;
        this.viewById = viewById;
        Init();
    }

    private void Init() {
        // PopupWindow 导入
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.home_address_book_sel, null);

        mEdtDeptName = (EditText) mMenuView.findViewById(R.id.edt_dept_name);
        deptTreePop = new DeptTreePop(context, deptList, mEdtDeptName, dept);

        mEdtDeptName.setOnClickListener(v -> {
            deptTreePop.showAtLocation(viewById,
                    Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
        });
        mEdtStaffName = (EditText) mMenuView.findViewById(R.id.edt_staff_name);
//        mEdtStaffEdu = (EditText) mMenuView.findViewById(R.id.edt_staff_edu);
//
//        mEdtStaffEdu.setOnTouchListener(new GetSingleSelectItem(
//                context, mEdtStaffEdu, "最高学历", Constant.mostEducationArr, false));
//
//        mEdtStartTime = (EditText) mMenuView.findViewById(R.id.edt_start_time);
//        setTimeEdit(mEdtStartTime);
//        mEdtEndTime = (EditText) mMenuView.findViewById(R.id.edt_end_time);
//        setTimeEdit(mEdtEndTime);
        mBtnSel = (Button) mMenuView.findViewById(R.id.btn_sel);
        mBtnSel.setOnClickListener(v -> {
            dismiss();
            mSelStafListener.onSelStafClick(
                    dept.getSendKey()
                    , mEdtStaffName.getText().toString().trim()
//                    , mEdtStaffEdu.getText().toString().trim()
//                    , mEdtStartTime.getText().toString()
//                    , mEdtEndTime.getText().toString()
            );
        });


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