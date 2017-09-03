package com.hyjt.home.mvp.model.entity.Resp;

import java.util.List;

/**
 * @author 赵宇驰
 * @time 2017/8/23  17:09
 * @desc ${TODO}
 */

public class BlocNoticeListResp {
    /**
     * total : 102
     * rows : [{"Id":"20170614153713203211660af14eaca","Title":"安全用电通告","CreateTime":"2017-06-14"},{"Id":"201705261357240879528fc1e40b78d","Title":"翟力墨、张小建同志的人事任免通知","CreateTime":"2017-05-26"},{"Id":"201705231052351383092b0e703cf5e","Title":"2017年端午节放假通知","CreateTime":"2017-05-23"},{"Id":"201705221315297169497382dafc103","Title":"关于公司人员考取执业资格证书奖励机制暂行管理办法","CreateTime":"2017-05-22"},{"Id":"2017042710530256251496d3c960699","Title":"2017年劳动节放假通知","CreateTime":"2017-04-27"},{"Id":"201704251324176869876d2bca08c5f","Title":"关于环宇综合管理平台外网网址变更通知","CreateTime":"2017-04-25"},{"Id":"201704211339172412499946a20ac02","Title":"辽宁环宇集团职工签名确认函","CreateTime":"2017-04-21"},{"Id":"201703310844485264465af22269fba","Title":"关于公司实施加密软件的通知","CreateTime":"2017-03-31"},{"Id":"201703291558216487173a6d37c132b","Title":"2017年清明节放假通知","CreateTime":"2017-03-29"},{"Id":"2017032915572770063167f86c46b11","Title":"人事任免通知","CreateTime":"2017-03-29"},{"Id":"20170314084958652291559a2db8633","Title":"关于选拔后备干部的通知-第二批名单公示","CreateTime":"2017-03-14"},{"Id":"2017030914223674384243be2a6cb00","Title":"关于选拔后备干部的通知","CreateTime":"2017-03-09"},{"Id":"201701181322145009363da86b41de2","Title":"2017年春节放假通知","CreateTime":"2017-01-18"},{"Id":"201701101606233998825ddb03de782","Title":"关于2017年新年会餐的通知","CreateTime":"2017-01-10"},{"Id":"201612301315009732124f95bb89059","Title":"安全用电通告","CreateTime":"2016-12-30"},{"Id":"201612291322545333324d2b238e894","Title":"2017年元旦放假通知","CreateTime":"2016-12-29"},{"Id":"20161129144707934365512d3c1c900","Title":"劳动合同补充条款","CreateTime":"2016-11-29"},{"Id":"2016092914012962227822989f6b26d","Title":"2016年国庆节放假通知","CreateTime":"2016-09-29"},{"Id":"20160919095514356208022c1ce5011","Title":"人事通知","CreateTime":"2016-09-19"},{"Id":"201609141311143273980898ba108d3","Title":"关于食堂就餐安排通知","CreateTime":"2016-09-14"}]
     */

    private int total;
    private List<RowsBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * Id : 20170614153713203211660af14eaca
         * Title : 安全用电通告
         * CreateTime : 2017-06-14
         */

        private String Id;
        private String Title;
        private String CreateTime;

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }
    }
}
