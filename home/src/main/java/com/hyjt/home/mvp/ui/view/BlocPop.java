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
 * @time 2017/7/20  13:54
 * @desc ${TODO}
 */

public class BlocPop extends PopupWindow {
    private View mMenuView; // PopupWindow 菜单布局
    private Context context; // 上下文参数
    private View.OnClickListener listener;

    private LinearLayout llBloc;
    private LinearLayout llBlocHeating;
    private LinearLayout llProject;
    private LinearLayout llMining;

    public BlocPop(Activity context, View.OnClickListener listener) {
        super(context);
        this.context = context;
        this.listener = listener;
        Init();
    }

    private void Init() {
        // PopupWindow 导入
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.home_bloc_sel, null);
        llBloc = (LinearLayout) mMenuView.findViewById(R.id.ll_bloc);
        llMining = (LinearLayout) mMenuView.findViewById(R.id.ll_mining);
        llProject = (LinearLayout) mMenuView.findViewById(R.id.ll_project);
        llBlocHeating = (LinearLayout) mMenuView.findViewById(R.id.ll_bloc_heating);

        llBloc.setOnClickListener(listener);
        llMining.setOnClickListener(listener);
        llProject.setOnClickListener(listener);
        llBlocHeating.setOnClickListener(listener);


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