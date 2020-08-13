package com.zjjxl.panda.beans;

public class Bean_zjjCreateOrder {


    /**
     * status : true
     * msg : 成功
     * data : {"trace_id":"20200811155148200","biz_type":"SERV_ORDER_CREATE","sign_type":"RSA2",
     * "sign":"YSnVrYi4OuuwQAJyH69No8N5iIXK34KHC35ltTH0EMc6FOliCHtVENEv3UC8yDUzQRbYNwOJdUK6qO1mXBCDQELGu62oq2QqJLhxZ6sj1AyPx5AaWQ8dwowT0RVSFgrUmllVK1n63LslvUZ/IVyXjEhyrJHq8vA/T8Rok1ZQtLXwYAb4YE2RtXw7pc92TjW1KgZve8RfMHUK4vLvFXVwUaxi3cpFdDE9xbUnIMFv5NIIHbS1Xg573VSb3+YD5metJPTySgJMUBCBpyKKrY8hjLY5AELY+PEn26XRAmbBxn06pBIjQYt0wpnCr+TGc2wGaBXliWEER5Ze5Ulq+pI6oA==",
     * "code":"1000","message":"处理成功。",
     * "data":{"expire_time":"2020-08-11T16:07:48",
     * "out_trade_no":"sttsma05f98d83bfb4f00949dd958626ed54c",
     * "server_time":"2020-08-11T15:51:48",
     * "subject":"四通一卡通充值",
     * "trade_no":"20200811512545276592176123312432",
     * "trade_time":"2020-08-11T15:51:49"}}
     */

    private boolean status;
    private String msg;
    private DataBeanX data;

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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {

        @Override
        public String toString() {
            return "DataBeanX{" +
                    "trace_id='" + trace_id + '\'' +
                    ", biz_type='" + biz_type + '\'' +
                    ", sign_type='" + sign_type + '\'' +
                    ", sign='" + sign + '\'' +
                    ", code='" + code + '\'' +
                    ", message='" + message + '\'' +
                    ", data=" + data +
                    '}';
        }

        /**
         * trace_id : 20200811155148200
         * biz_type : SERV_ORDER_CREATE
         * sign_type : RSA2
         * sign : YSnVrYi4OuuwQAJyH69No8N5iIXK34KHC35ltTH0EMc6FOliCHtVENEv3UC8yDUzQRbYNwOJdUK6qO1mXBCDQELGu62oq2QqJLhxZ6sj1AyPx5AaWQ8dwowT0RVSFgrUmllVK1n63LslvUZ/IVyXjEhyrJHq8vA/T8Rok1ZQtLXwYAb4YE2RtXw7pc92TjW1KgZve8RfMHUK4vLvFXVwUaxi3cpFdDE9xbUnIMFv5NIIHbS1Xg573VSb3+YD5metJPTySgJMUBCBpyKKrY8hjLY5AELY+PEn26XRAmbBxn06pBIjQYt0wpnCr+TGc2wGaBXliWEER5Ze5Ulq+pI6oA==
         * code : 1000
         * message : 处理成功。
         * data : {"expire_time":"2020-08-11T16:07:48","out_trade_no":"sttsma05f98d83bfb4f00949dd958626ed54c","server_time":"2020-08-11T15:51:48","subject":"四通一卡通充值","trade_no":"20200811512545276592176123312432","trade_time":"2020-08-11T15:51:49"}
         */

        private String trace_id;
        private String biz_type;
        private String sign_type;
        private String sign;
        private String code;
        private String message;
        private DataBean data;

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

        public String getSign_type() {
            return sign_type;
        }

        public void setSign_type(String sign_type) {
            this.sign_type = sign_type;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
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

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * expire_time : 2020-08-11T16:07:48
             * out_trade_no : sttsma05f98d83bfb4f00949dd958626ed54c
             * server_time : 2020-08-11T15:51:48
             * subject : 四通一卡通充值
             * trade_no : 20200811512545276592176123312432
             * trade_time : 2020-08-11T15:51:49
             */

            private String expire_time;
            private String out_trade_no;
            private String server_time;
            private String subject;
            private String trade_no;
            private String trade_time;

            public String getExpire_time() {
                return expire_time;
            }

            public void setExpire_time(String expire_time) {
                this.expire_time = expire_time;
            }

            public String getOut_trade_no() {
                return out_trade_no;
            }

            public void setOut_trade_no(String out_trade_no) {
                this.out_trade_no = out_trade_no;
            }

            public String getServer_time() {
                return server_time;
            }

            public void setServer_time(String server_time) {
                this.server_time = server_time;
            }

            public String getSubject() {
                return subject;
            }

            public void setSubject(String subject) {
                this.subject = subject;
            }

            public String getTrade_no() {
                return trade_no;
            }

            public void setTrade_no(String trade_no) {
                this.trade_no = trade_no;
            }

            public String getTrade_time() {
                return trade_time;
            }

            public void setTrade_time(String trade_time) {
                this.trade_time = trade_time;
            }
        }
    }
}
