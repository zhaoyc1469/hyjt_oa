package com.hyjt.home.mvp.ui.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hyjt.home.R;
import com.hyjt.home.mvp.model.entity.Reqs.StaffNameIdKey;

/**
 * @author 赵宇驰
 * @time 2017/7/18  21:30
 * @desc ${TODO}
 */

public class DepartmentPop extends PopupWindow {
    private View mMenuView; // PopupWindow 菜单布局
    private Context context; // 上下文参数
    private StaffNameIdKey StaffNameIdKey; // 上下文参数
    private boolean moreCheck; // 上下文参数

    private EditText editText;
    private TextView allDepartment;
    private TextView geologyDepartment;
    private TextView miningDepartment;
    private TextView projectDepartment;
    private TextView itDepartment;
    private TextView assessDepartment;
    private TextView financeDepartment;
    private TextView recordDepartment;
    private TextView personnelDepartment;

    public  DepartmentPop(Context context, EditText editText, StaffNameIdKey StaffNameIdKey, boolean moreCheck) {
        super(context);
        this.context = context;
        this.editText = editText;
        this.StaffNameIdKey = StaffNameIdKey;
        this.moreCheck = moreCheck;
        Init();
    }

    private void Init() {
        // TODO Auto-generated method stub
        // PopupWindow 导入
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.home_department_sel, null);
        allDepartment = (TextView) mMenuView.findViewById(R.id.tv_all_department);
        geologyDepartment = (TextView) mMenuView.findViewById(R.id.tv_geology_department);
        miningDepartment = (TextView) mMenuView.findViewById(R.id.tv_mining_department);
        assessDepartment = (TextView) mMenuView.findViewById(R.id.tv_assess_department);
        projectDepartment = (TextView) mMenuView.findViewById(R.id.tv_project_department);
        itDepartment = (TextView) mMenuView.findViewById(R.id.tv_it_department);
        financeDepartment = (TextView) mMenuView.findViewById(R.id.tv_finance_department);
        recordDepartment = (TextView) mMenuView.findViewById(R.id.tv_record_department);
        personnelDepartment = (TextView) mMenuView.findViewById(R.id.tv_personnel_department);

        //全体部门
        allDepartment.setOnClickListener(v -> {
            if (OnClickSelEditListener != null) {
                OnClickSelEditListener.onItemClick("", editText, StaffNameIdKey, moreCheck);
            }
        });
        //地勘
        geologyDepartment.setOnClickListener(v -> {
            if (OnClickSelEditListener != null) {
                OnClickSelEditListener.onItemClick("地勘", editText, StaffNameIdKey, moreCheck);
            }
        });
        //矿设
        miningDepartment.setOnClickListener(v -> {
            if (OnClickSelEditListener != null) {
                OnClickSelEditListener.onItemClick("矿设", editText, StaffNameIdKey, moreCheck);
            }
        });
        //评估
        assessDepartment.setOnClickListener(v -> {
            if (OnClickSelEditListener != null) {
                OnClickSelEditListener.onItemClick("评估", editText, StaffNameIdKey, moreCheck);
            }
        });
        //项目
        projectDepartment.setOnClickListener(v -> {
            if (OnClickSelEditListener != null) {
                OnClickSelEditListener.onItemClick("项目部", editText, StaffNameIdKey, moreCheck);
            }
        });
        //信息技术部
        itDepartment.setOnClickListener(v -> {
            if (OnClickSelEditListener != null) {
                OnClickSelEditListener.onItemClick("信息", editText, StaffNameIdKey, moreCheck);
            }
        });
        //财务
        financeDepartment.setOnClickListener(v -> {
            if (OnClickSelEditListener != null) {
                OnClickSelEditListener.onItemClick("财务", editText, StaffNameIdKey, moreCheck);
            }
        });
        //档案
        recordDepartment.setOnClickListener(v -> {
            if (OnClickSelEditListener != null) {
                OnClickSelEditListener.onItemClick("档案室", editText, StaffNameIdKey, moreCheck);
            }
        });
        //人事
        personnelDepartment.setOnClickListener(v -> {
            if (OnClickSelEditListener != null) {
                OnClickSelEditListener.onItemClick("人事", editText, StaffNameIdKey, moreCheck);
            }
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

    private OnItemClickListener OnClickSelEditListener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.OnClickSelEditListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(String Department, EditText editText, StaffNameIdKey StaffNameIdKey, boolean moreCheck);
    }
}
