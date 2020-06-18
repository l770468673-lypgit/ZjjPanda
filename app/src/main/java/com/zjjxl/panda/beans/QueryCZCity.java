package com.zjjxl.panda.beans;

import java.util.List;

public class QueryCZCity {

    /**
     * msg : 查询成功
     * extra : [{"isUse":1,"accessChannel":"xm100002","cityName":"双阳","createTime":"2020-06-16 11:01:42","id":"002"},{"isUse":1,"accessChannel":"zjj100001","cityName":"张家界","createTime":"2020-06-16 11:01:26","id":"001"}]
     * status : true
     */

    private String msg;
    private boolean status;
    private List<ExtraBean> extra;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<ExtraBean> getExtra() {
        return extra;
    }

    public void setExtra(List<ExtraBean> extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return "QueryCZCity{" +
                "msg='" + msg + '\'' +
                ", status=" + status +
                ", extra=" + extra +
                '}';
    }

    public static class ExtraBean {
        @Override
        public String toString() {
            return "ExtraBean{" +
                    "isUse=" + isUse +
                    ", accessChannel='" + accessChannel + '\'' +
                    ", cityName='" + cityName + '\'' +
                    ", createTime='" + createTime + '\'' +
                    ", id='" + id + '\'' +
                    '}';
        }

        /**
         * isUse : 1
         * accessChannel : xm100002
         * cityName : 双阳
         * createTime : 2020-06-16 11:01:42
         * id : 002
         */


        private int isUse;
        private String accessChannel;
        private String cityName;
        private String createTime;
        private String id;

        public int getIsUse() {
            return isUse;
        }

        public void setIsUse(int isUse) {
            this.isUse = isUse;
        }

        public String getAccessChannel() {
            return accessChannel;
        }

        public void setAccessChannel(String accessChannel) {
            this.accessChannel = accessChannel;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
