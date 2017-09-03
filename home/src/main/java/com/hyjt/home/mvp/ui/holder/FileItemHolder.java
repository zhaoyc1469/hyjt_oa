package com.hyjt.home.mvp.ui.holder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hyjt.frame.base.BaseHolder;
import com.hyjt.home.R;

/**
 * @author 赵宇驰
 * @time 2017/7/21  10:12
 * @desc ${TODO}
 */
public class FileItemHolder extends BaseHolder<String> {

    private final TextView cmName;
    private final Button btnFile;

    public FileItemHolder(View v) {
        super(v);
        cmName = (TextView) v.findViewById(R.id.tv_cm_name);
        btnFile = (Button) v.findViewById(R.id.btn_del_file);
    }

    @Override
    public void setData(String data, int position) {

        cmName.setText(data);
        btnFile.setOnClickListener(v -> {

        });

    }
}
