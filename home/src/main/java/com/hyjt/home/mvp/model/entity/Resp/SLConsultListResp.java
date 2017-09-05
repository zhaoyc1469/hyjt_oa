package com.hyjt.home.mvp.model.entity.Resp;

import java.util.List;

/**
 * @author 赵宇驰
 * @time 2017/9/5  10:48
 * @desc ${TODO}
 */

public class SLConsultListResp {


    /**
     * total : 3
     * rows : [{"Id":"201709051328320814471d23a6881d3","PID":"20170905-0006","Worker":"李大鹏","CreatePerson":"孙燚龙","CreateTime":"2017-09-05 13:28:32","State":"待回复"},{"Id":"20170905132820953447121af3af9cc","PID":"20170905-0005","Worker":"李大鹏","CreatePerson":"孙燚龙","CreateTime":"2017-09-05 13:28:20","State":"待回复"},{"Id":"201709051327347434471017d05bbdd","PID":"20170905-0004","Worker":"李大鹏","CreatePerson":"孙燚龙","CreateTime":"2017-09-05 13:27:34","State":"待回复"}]
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
         * Id : 201709051328320814471d23a6881d3
         * PID : 20170905-0006
         * Worker : 李大鹏
         * CreatePerson : 孙燚龙
         * CreateTime : 2017-09-05 13:28:32
         * State : 待回复
         */

        private String Id;
        private String PID;
        private String Worker;
        private String CreatePerson;
        private String CreateTime;
        private String State;

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getPID() {
            return PID;
        }

        public void setPID(String PID) {
            this.PID = PID;
        }

        public String getWorker() {
            return Worker;
        }

        public void setWorker(String Worker) {
            this.Worker = Worker;
        }

        public String getCreatePerson() {
            return CreatePerson;
        }

        public void setCreatePerson(String CreatePerson) {
            this.CreatePerson = CreatePerson;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getState() {
            return State;
        }

        public void setState(String State) {
            this.State = State;
        }
    }
}
