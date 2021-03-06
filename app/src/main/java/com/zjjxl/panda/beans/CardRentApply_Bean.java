package com.zjjxl.panda.beans;

public class CardRentApply_Bean {


    /**
     * status : true
     * msg : 成功
     * data : {"trace_id":"20200814114158095","biz_type":"SERV_CARD_RENT_APP","sign_type":"RSA2","sign":"gU06CiHuUy2VECptud3oVpKzHmfHtR7Zw8XX6T4QH5QpqkWPfXI6pMWWyWpTQvC90iMSOPOWk13RFl8IsGdH6+5No8SZ92NmAUjvXgvmJpyOPEZnNV+9a26bgFHsirs9atmHAKCqvHakQskoMMia5jEFzYSXCy3K2mGEnem7n4mKxrZGMNTXoq8tubr9vv7Bm2oKg5h0wouN+7WFSjyDvmzxROkBUG007OAobMK3W9ywAYDxoQM6ddhEoaXi2RTaduntBL2vE5I7PFOaCez6hCXx55C303m8P4E6Ejk2HyAsmFK8GoQlUZAzobL317wiT2R+R7Xa3zBnpYmfpCV8DA==","code":"1000","message":"处理成功。","data":{"account_no":"0","card_no":"03104895199900000101","city_code":"2482","issuer_code":"05212482","mode_command":"04D6950917010310489519990000010120200814204012314646830C","mode_version":"1500","server_time":"2020-08-14T11:41:58","terminal_no":"248200000001","terminal_seq":"00000001","trade_time":"2020-08-14T11:41:58","unit_code":"24820000"}}
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
         * trace_id : 20200814114158095
         * biz_type : SERV_CARD_RENT_APP
         * sign_type : RSA2
         * sign : gU06CiHuUy2VECptud3oVpKzHmfHtR7Zw8XX6T4QH5QpqkWPfXI6pMWWyWpTQvC90iMSOPOWk13RFl8IsGdH6+5No8SZ92NmAUjvXgvmJpyOPEZnNV+9a26bgFHsirs9atmHAKCqvHakQskoMMia5jEFzYSXCy3K2mGEnem7n4mKxrZGMNTXoq8tubr9vv7Bm2oKg5h0wouN+7WFSjyDvmzxROkBUG007OAobMK3W9ywAYDxoQM6ddhEoaXi2RTaduntBL2vE5I7PFOaCez6hCXx55C303m8P4E6Ejk2HyAsmFK8GoQlUZAzobL317wiT2R+R7Xa3zBnpYmfpCV8DA==
         * code : 1000
         * message : 处理成功。
         * data : {"account_no":"0","card_no":"03104895199900000101","city_code":"2482","issuer_code":"05212482","mode_command":"04D6950917010310489519990000010120200814204012314646830C","mode_version":"1500","server_time":"2020-08-14T11:41:58","terminal_no":"248200000001","terminal_seq":"00000001","trade_time":"2020-08-14T11:41:58","unit_code":"24820000"}
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
             * account_no : 0
             * card_no : 03104895199900000101
             * city_code : 2482
             * issuer_code : 05212482
             * mode_command : 04D6950917010310489519990000010120200814204012314646830C
             * mode_version : 1500
             * server_time : 2020-08-14T11:41:58
             * terminal_no : 248200000001
             * terminal_seq : 00000001
             * trade_time : 2020-08-14T11:41:58
             * unit_code : 24820000
             */

            private int account_no;
            private String card_no;
            private String city_code;
            private String issuer_code;
            private String mode_command;
            private String mode_version;
            private String server_time;
            private String terminal_no;
            private String terminal_seq;
            private String trade_time;
            private String unit_code;

            public int getAccount_no() {
                return account_no;
            }

            public void setAccount_no(int account_no) {
                this.account_no = account_no;
            }

            public String getCard_no() {
                return card_no;
            }

            public void setCard_no(String card_no) {
                this.card_no = card_no;
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

            public String getMode_command() {
                return mode_command;
            }

            public void setMode_command(String mode_command) {
                this.mode_command = mode_command;
            }

            public String getMode_version() {
                return mode_version;
            }

            public void setMode_version(String mode_version) {
                this.mode_version = mode_version;
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
