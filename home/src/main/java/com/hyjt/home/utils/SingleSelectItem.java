package com.hyjt.home.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.hyjt.home.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 点击editText弹出对话框进行单选，默认选择第一项
 *
 * @author 王
 */
public class SingleSelectItem implements View.OnTouchListener {
    private Context mContext;
    private TextView tvSelect;
    private String title;
    private String[] items;

    public SingleSelectItem(Context mContext, TextView tvSelect, String title, String[] items, boolean seled) {
        this.mContext = mContext;
        this.tvSelect = tvSelect;
        this.title = title;
        this.items = items;
        //默认选择第一项
        if (seled) {
            tvSelect.setText(items[0]);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.home_dialog_sel_list, null);
            dialog.setTitle(title);
            dialog.setView(layout)
                    .setCancelable(false)
                    .setNegativeButton("取消", (dialog1, id) -> dialog1.cancel());
            final AlertDialog accAlert = dialog.create();
            final ListView accList = (ListView) layout.findViewById(R.id.accList);

            final List<Map<String, String>> itemData = new ArrayList<>();
            Map<String, String> mp;
            for (String item : items) {
                mp = new HashMap<>();
                mp.put("name", item);
                itemData.add(mp);
            }
            SimpleAdapter czadapter = new SimpleAdapter(mContext, itemData,
                    R.layout.home_dialog_sel_item, new String[]{"name"},
                    new int[]{R.id.tv_simple_text});
            accList.setAdapter(czadapter);
            accList.setOnItemClickListener((arg0, arg1, arg2, arg3) -> {
                Map<String, String> m = itemData.get(arg2);
                tvSelect.setText(m.get("name"));
                accAlert.dismiss();
            });

            InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(tvSelect.getWindowToken(), 0);
            accAlert.show();

        }
        return false;
    }
}