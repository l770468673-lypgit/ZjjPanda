package com.zjjxl.panda.beans;

public class Bean_BaseCard {


    /**
     * status : true
     * msg : 成功
     * data : {"trace_id":"20200915172321992","biz_type":"SERV_ORDER_REFUND","code":"2001","message":"平台订单号与业务订单号不能都为空。"}
     */

    private boolean status;
    private String msg;
    private DataBean data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * trace_id : 20200915172321992
         * biz_type : SERV_ORDER_REFUND
         * code : 2001
         * message : 平台订单号与业务订单号不能都为空。
         */

        private String trace_id;
        private String biz_type;
        private String code;
        private String message;

        public String getTrace_id() {
            return trace_id;
        }

        public void setTrace_id(String trace_id) {
            this.trace_id = trace_id;
        }

        public String getBiz_type() {
            return biz_type;
        }

        public void setBiz_type(String biz_type) {
            this.biz_type = biz_type;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
