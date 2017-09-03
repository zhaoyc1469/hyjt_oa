package com.hyjt.home.mvp.ui.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.hyjt.home.R;
import com.hyjt.home.mvp.model.entity.Reqs.StaffNameIdKey;
import com.hyjt.home.mvp.ui.view.treelistview.Node;
import com.hyjt.home.mvp.ui.view.treelistview.NodeHelper;
import com.hyjt.home.mvp.ui.view.treelistview.NodeTreeAdapter;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 赵宇驰
 * @time 2017/7/20  13:54
 * @desc ${TODO}
 */

public class DeptTreePop extends PopupWindow {
    private Context context; // 上下文参数
    private View.OnClickListener listener;
    private View mDeptView;
    private List<Node> data;
    private EditText editText;
    private StaffNameIdKey DeptIdStr;
    private ListView lvDept;

    public DeptTreePop(Context context, List<Node> data, EditText editText, StaffNameIdKey DeptIdStr ) {
        super(context);
        this.context = context;
        this.data = data;
        this.editText = editText;
        this.DeptIdStr = DeptIdStr;
        Init();
    }

    private void Init() {
        // PopupWindow 导入
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mDeptView = inflater.inflate(R.layout.home_dept_tree, null);
        lvDept = (ListView) mDeptView.findViewById(R.id.lv_dept_tree);

        LinkedList<Node> mLinkedList = new LinkedList<>();
        mLinkedList.addAll(NodeHelper.sortNodes(data));
        NodeTreeAdapter deptTreeAdapter = new NodeTreeAdapter(
                context, lvDept, mLinkedList, editText, DeptIdStr, this);
        lvDept.setAdapter(deptTreeAdapter);

        // 导入布局
        this.setContentView(mDeptView);
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
        mDeptView.setOnTouchListener((v, event) -> {

            int height = mDeptView.findViewById(R.id.pop_layout).getTop();
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