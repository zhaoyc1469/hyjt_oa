package com.hyjt.client.mvp.ui.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hyjt.frame.utils.JsonUtils;
import com.hyjt.home.mvp.model.entity.Resp.PushExtraResp;

import cn.jpush.android.api.JPushInterface;

public class InformReceiver extends BroadcastReceiver {

    public InformReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            //打开自定义的Activity
            Bundle bundle = intent.getExtras();
            String extra = bundle.getString("cn.jpush.android.EXTRA");
            PushExtraResp pushExtraResp = JsonUtils.parseJson(extra, PushExtraResp.class);
            if (!TextUtils.isEmpty(pushExtraResp.getType())){
                if ("Meeting ".equals(pushExtraResp.getType())){
                    if (!TextUtils.isEmpty(pushExtraResp.getId())) {
                        ARouter.getInstance().build("/home/MeetingEditActivity")
                                .withString("id", pushExtraResp.getId())
                                .navigation();
                    }
                } else if ("Email".equals(pushExtraResp.getType())){
                    if (!TextUtils.isEmpty(pushExtraResp.getId())) {
                        ARouter.getInstance().build("/home/EmailDetailsActivity")
                                .withString("id", pushExtraResp.getId())
                                .navigation();
                    }
                } else if ("Notice".equals(pushExtraResp.getType())){
                    if (!TextUtils.isEmpty(pushExtraResp.getId())) {
                        ARouter.getInstance().build("/home/BNoticeEditActivity")
                                .withString("Id", pushExtraResp.getId())
                                .withString("ForbidEdit", "0")
                                .navigation();
                    }
                } else if ("WorkingConference".equals(pushExtraResp.getType())){
                    if (!TextUtils.isEmpty(pushExtraResp.getId())) {
                        ARouter.getInstance().build("/home/ReportTopDetailActivity")
                                .withString("Id", pushExtraResp.getId())
                                .navigation();
                    }
                } else if ("WorkingConsult".equals(pushExtraResp.getType())){
                    if (!TextUtils.isEmpty(pushExtraResp.getId())) {
                        ARouter.getInstance().build("/home/SLConsultEditActivity")
                                .withString("Id", pushExtraResp.getId())
                                .navigation();
                    }
                }
            }
        }
    }
}
