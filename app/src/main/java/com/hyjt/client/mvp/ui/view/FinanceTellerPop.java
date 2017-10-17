package com.hyjt.client.mvp.ui.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hyjt.app.R;


public class FinanceTellerPop extends PopupWindow {

    private View mMenuView; // PopupWindow 菜单布局
    private Context context; // 上下文参数

    private LinearLayout mPopLayout;
    private TextView mTvApply;
    private TextView mTvApprove;
    private TextView mTvTeller;
    private TextView mTvPsonLoan;
    private TextView mTvToCompLoan;
    private TextView mTvExpense;

    public FinanceTellerPop(Context context) {
        super(context);
        this.context = context;
        Init();
    }

    private void Init() {
        // PopupWindow 导入
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.pop_teller_finance, null);

        mPopLayout = (LinearLayout) mMenuView.findViewById(R.id.pop_layout);
        mTvPsonLoan = (TextView) mMenuView.findViewById(R.id.tv_pson_loan);
        mTvToCompLoan = (TextView) mMenuView.findViewById(R.id.tv_to_comp_loan);
        mTvExpense = (TextView) mMenuView.findViewById(R.id.tv_expense);


        mTvPsonLoan.setOnClickListener(v -> {
            if (OnClickSelListener != null) {
                OnClickSelListener.onItemClick("1");
            }
        });

        mTvToCompLoan.setOnClickListener(v -> {
            if (OnClickSelListener != null) {
                OnClickSelListener.onItemClick("2");
            }
        });
        mTvExpense.setOnClickListener(v -> {
            if (OnClickSelListener != null) {
                OnClickSelListener.onItemClick("3");
            }
        });

        // 导入布局
        this.setContentView(mMenuView);
        // 设置动画效果
        this.setAnimationStyle(com.hyjt.home.R.style.home_popwindow_anim_style);
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

            int height = mMenuView.findViewById(com.hyjt.home.R.id.pop_layout).getTop();
            int y = (int) event.getY();
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (y < height) {
                    dismiss();
                }
            }
            return true;
        });
    }

    private OnItemClickListener OnClickSelListener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.OnClickSelListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(String type);
    }
}
