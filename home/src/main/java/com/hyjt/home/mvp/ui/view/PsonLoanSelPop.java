package com.hyjt.home.mvp.ui.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.hyjt.home.R;

import java.util.Calendar;

/**
 * @author 赵宇驰
 * @time 2017/7/20  13:54
 * @desc ${TODO}
 */

public class PsonLoanSelPop extends PopupWindow {
    private View mMenuView; // PopupWindow 菜单布局
    private Context context; // 上下文参数

    private LinearLayout mPopLayout;
    private EditText mEdtPsonloanNum;
    private EditText mEdtStartTime;
    private EditText mEdtEndTime;
    private Button mBtnSel;
    private LinearLayout mLlApproveTeller;
    private EditText mEdtApplyMan;
    private EditText mEdtCompanyName;
    private EditText mEdtDepartment;
    private String type;

    public PsonLoanSelPop(Activity context, String type) {
        super(context);
        this.context = context;
        this.type = type;
        Init();
    }

    private void Init() {
        // PopupWindow 导入
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.home_psonloan_sel, null);
        mPopLayout = (LinearLayout) mMenuView.findViewById(R.id.pop_layout);
        mLlApproveTeller = (LinearLayout) mMenuView.findViewById(R.id.ll_approve_teller);
        mEdtPsonloanNum = (EditText) mMenuView.findViewById(R.id.edt_psonloan_num);
        mEdtStartTime = (EditText) mMenuView.findViewById(R.id.edt_start_time);
        mEdtApplyMan = (EditText) mMenuView.findViewById(R.id.edt_apply_man);
        mEdtCompanyName = (EditText) mMenuView.findViewById(R.id.edt_company_name);
        mEdtDepartment = (EditText) mMenuView.findViewById(R.id.edt_department);
        setTimeEdit(mEdtStartTime);
        mEdtEndTime = (EditText) mMenuView.findViewById(R.id.edt_end_time);
        setTimeEdit(mEdtEndTime);
        mBtnSel = (Button) mMenuView.findViewById(R.id.btn_sel);
        mBtnSel.setOnClickListener(v -> {
            dismiss();
            mSelCmListener.onSelCmClick(
                    mEdtApplyMan.getText().toString().trim()
                    , mEdtCompanyName.getText().toString().trim()
                    , mEdtDepartment.getText().toString().trim()
                    , mEdtPsonloanNum.getText().toString().trim()
                    , mEdtStartTime.getText().toString().trim()
                    , mEdtEndTime.getText().toString().trim()
            );
        });

        if (type.equals("2") || type.equals("3")){
            mLlApproveTeller.setVisibility(View.VISIBLE);
        } else {
            mLlApproveTeller.setVisibility(View.GONE);
        }

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

    private SelCmListener mSelCmListener = null;

    public void setSelCmListener(SelCmListener listener) {
        this.mSelCmListener = listener;
    }


    public interface SelCmListener {
        void onSelCmClick(String personal, String company,
                          String department, String num, String start, String end);
    }
}