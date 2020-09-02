package com.zjjxl.panda.beans;

public class CardCharge_bean {

    /**
     * status : true
     * msg : 成功
     * data : {"trace_id":"20200824112817949","biz_type":"SERV_CARD_CHARG_APP","sign_type":"RSA2","sign":"CtmqBUPLl1oGNNrC+xuMpGKyI68q88PBI6h8Ox4wPg/eIoa+YgMffevY70MNg0LzVnOstx7hToV6qE6ygrctgDuZb7iXelKU57fGVg7vqAFigVDj5OAEXpYjNR4/dZYVLdkrK/UF1T5RbSD2iVSRtNdy/FM6Wc7FHT8XznDyKYIf3J1uZqagamBYnii4gMbA3ya+sHRipf+lixyar0BaMxPn4tUZA7ylW7kXVL1fOG23qdTsNR0cAGwHsoHX8EvANuBLd/pxDhJe6/r60B7Epx4qYbQuvMtqKZ/thcegfJBMMFW3I1bLcrpqyu/hDhKPGKS8sapZNR6SqbUlDRav8Q==","code":"1000","message":"处理成功。","data":{"card_mac":"FCF61DB5","card_no":"03104895199800000501","card_seq":"0001","city_code":"2482","issuer_code":"05212482","server_time":"2020-08-24T11:28:18","terminal_no":"248200000001","terminal_seq":"00000001","trade_time":"2020-08-24T11:28:10","unit_code":"24820000"}}
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
        /**
         * trace_id : 20200824112817949
         * biz_type : SERV_CARD_CHARG_APP
         * sign_type : RSA2
         * sign : CtmqBUPLl1oGNNrC+xuMpGKyI68q88PBI6h8Ox4wPg/eIoa+YgMffevY70MNg0LzVnOstx7hToV6qE6ygrctgDuZb7iXelKU57fGVg7vqAFigVDj5OAEXpYjNR4/dZYVLdkrK/UF1T5RbSD2iVSRtNdy/FM6Wc7FHT8XznDyKYIf3J1uZqagamBYnii4gMbA3ya+sHRipf+lixyar0BaMxPn4tUZA7ylW7kXVL1fOG23qdTsNR0cAGwHsoHX8EvANuBLd/pxDhJe6/r60B7Epx4qYbQuvMtqKZ/thcegfJBMMFW3I1bLcrpqyu/hDhKPGKS8sapZNR6SqbUlDRav8Q==
         * code : 1000
         * message : 处理成功。
         * data : {"card_mac":"FCF61DB5","card_no":"03104895199800000501","card_seq":"0001","city_code":"2482","issuer_code":"05212482","server_time":"2020-08-24T11:28:18","terminal_no":"248200000001","terminal_seq":"00000001","trade_time":"2020-08-24T11:28:10","unit_code":"24820000"}
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
             * card_mac : FCF61DB5
             * card_no : 03104895199800000501
             * card_seq : 0001
             * city_code : 2482
             * issuer_code : 05212482
             * server_time : 2020-08-24T11:28:18
             * terminal_no : 248200000001
             * terminal_seq : 00000001
             * trade_time : 2020-08-24T11:28:10
             * unit_code : 24820000
             */

            private String card_mac;
            private String card_no;
            private String card_seq;
            private String city_code;
            private String issuer_code;
            private String server_time;
            private String terminal_no;
            private String terminal_seq;
            private String trade_time;
            private String unit_code;

            public String getCard_mac() {
                return card_mac;
            }

            public void setCard_mac(String card_mac) {
                this.card_mac = card_mac;
            }

            public String getCard_no() {
                return card_no;
            }

            public void setCard_no(String card_no) {
                this.card_no = card_no;
            }

            public String getCard_seq() {
                return card_seq;
            }

            public void setCard_seq(String card_seq) {
                this.card_seq = card_seq;
            }

            public String getCity_code() {
                return city_code;
            }

            public void setCity_code(String city_code) {
                this.city_code = city_code;
            }

            public String getIssuer_code() {
                return issuer_code;
            }

            public void setIssuer_code(String issuer_code) {
                this.issuer_code = issuer_code;
            }

            public String getServer_time() {
                return server_time;
            }

            public void setServer_time(String server_time) {
                this.server_time = server_time;
            }

            public String getTerminal_no() {
                return terminal_no;
            }

            public void setTerminal_no(String terminal_no) {
                this.terminal_no = terminal_no;
            }

            public String getTerminal_seq() {
                return terminal_seq;
            }

            public void setTerminal_seq(String terminal_seq) {
                this.terminal_seq = terminal_seq;
            }

            public String getTrade_time() {
                return trade_time;
            }

            public void setTrade_time(String trade_time) {
                this.trade_time = trade_time;
            }

            public String getUnit_code() {
                return unit_code;
            }

            public void setUnit_code(String unit_code) {
                this.unit_code = unit_code;
            }
        }
    }
}
