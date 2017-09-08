package com.hyjt.home.mvp.model.entity.Resp;

/**
 * Created by Administrator on 2017/9/8.
 */

public class LUReqsDetailResp {

    private String Title;
    private String   ReportContent;
    private String BossId;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getReportContent() {
        return ReportContent;
    }

    public void setReportContent(String reportContent) {
        ReportContent = reportContent;
    }

    public String getBossId() {
        return BossId;
    }

    public void setBossId(String bossId) {
        BossId = bossId;
    }
}
