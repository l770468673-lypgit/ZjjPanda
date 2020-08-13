package com.zjjxl.panda.beans;

import java.util.List;

public class PramDetal_Bean {


    /**
     * status : true
     * msg : 成功
     * data : {"trace_id":"20200812160735625","biz_type":"SERV_PARAM_SYN","sign_type":"RSA2","sign":"Kkrn2g78572HJTfGPdroqnEWip7YWW103tiIHaHxw/G7xR+HS+CkK+k5HJtU25NscXhoSNf6MP1kmrde1th7XL3YaHwhBR6EjJ7OCWQNG3umJtCcu4gt6Dr4CpGykPi2KWMeW6aAxUf2J3iG7EfqPkrLabWgwicG+x3W0i6iuYAPizSARUPTyCLoBQErzEtYokFyDglylabATag/W7/ViUOWmJHhIRrStMwyXCHl13B2KJvBbsB7IcnVQ0Hfhoi9/H9e6m9x/ostALL77UUtYSyVtMDyx9dhcppt+h93hdITxF5AOqJY23a1bWy2P/QO1MMjUt2KWy9KOAAwkvRtLA==","code":"1000","message":"处理成功。","data":{"param_list":[{"card_iin":"31048950","city_code":"2400","credit_no":"","issuer_code":"05202400","issuer_name":"吉林长白山管委会","settle_unit":"","status":"1","status_name":"正常","verify_code":"","vterminal_no":""},{"card_iin":"31048913","city_code":"2411","credit_no":"","issuer_code":"04832411","issuer_name":"吉林榆树","settle_unit":"","status":"1","status_name":"正常","verify_code":"","vterminal_no":""},{"card_iin":"31048914","city_code":"2412","credit_no":"","issuer_code":"04842412","issuer_name":"吉林农安","settle_unit":"","status":"1","status_name":"正常","verify_code":"","vterminal_no":""},{"card_iin":"31048915","city_code":"2413","credit_no":"","issuer_code":"04852413","issuer_name":"吉林德惠","settle_unit":"","status":"1","status_name":"正常","verify_code":"","vterminal_no":""},{"card_iin":"31048916","city_code":"2415","credit_no":"00000000","issuer_code":"04862415","issuer_name":"吉林九台","settle_unit":"24151001","status":"1","status_name":"正常","verify_code":"00000000000000000000000000000000","vterminal_no":"413124151001"},{"card_iin":"31048922","city_code":"2432","credit_no":"","issuer_code":"04922432","issuer_name":"吉林伊通","settle_unit":"","status":"1","status_name":"正常","verify_code":"","vterminal_no":""},{"card_iin":"31048927","city_code":"2451","credit_no":"","issuer_code":"04972451","issuer_name":"吉林通化","settle_unit":"","status":"1","status_name":"正常","verify_code":"","vterminal_no":""},{"card_iin":"31048928","city_code":"2452","credit_no":"","issuer_code":"04982452","issuer_name":"吉林辉南","settle_unit":"","status":"1","status_name":"正常","verify_code":"","vterminal_no":""},{"card_iin":"31048929","city_code":"2453","credit_no":"","issuer_code":"04992453","issuer_name":"吉林柳河","settle_unit":"","status":"1","status_name":"正常","verify_code":"","vterminal_no":""},{"card_iin":"31048931","city_code":"2455","credit_no":"","issuer_code":"05012455","issuer_name":"吉林集安","settle_unit":"","status":"1","status_name":"正常","verify_code":"","vterminal_no":""},{"card_iin":"31048935","city_code":"2472","credit_no":"","issuer_code":"05052472","issuer_name":"吉林洮南","settle_unit":"","status":"1","status_name":"正常","verify_code":"","vterminal_no":""},{"card_iin":"31048937","city_code":"2477","credit_no":"","issuer_code":"05072477","issuer_name":"吉林镇赉","settle_unit":"","status":"1","status_name":"正常","verify_code":"","vterminal_no":""},{"card_iin":"31048938","city_code":"2478","credit_no":"","issuer_code":"05082478","issuer_name":"吉林通榆","settle_unit":"","status":"1","status_name":"正常","verify_code":"","vterminal_no":""},{"card_iin":"31048951","city_code":"2482","credit_no":"00000000","issuer_code":"05212482","issuer_name":"吉林双阳","settle_unit":"24820000","status":"1","status_name":"正常","verify_code":"00000000000000000000000000000000","vterminal_no":"248200000001"},{"card_iin":"31048947","city_code":"2511","credit_no":"","issuer_code":"05172511","issuer_name":"吉林扶余","settle_unit":"","status":"1","status_name":"正常","verify_code":"","vterminal_no":""},{"card_iin":"31048948","city_code":"2512","credit_no":"","issuer_code":"05182512","issuer_name":"吉林长岭","settle_unit":"","status":"1","status_name":"正常","verify_code":"","vterminal_no":""}],"server_time":"2020-08-12T16:07:35"}}
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
         * trace_id : 20200812160735625
         * biz_type : SERV_PARAM_SYN
         * sign_type : RSA2
         * sign : Kkrn2g78572HJTfGPdroqnEWip7YWW103tiIHaHxw/G7xR+HS+CkK+k5HJtU25NscXhoSNf6MP1kmrde1th7XL3YaHwhBR6EjJ7OCWQNG3umJtCcu4gt6Dr4CpGykPi2KWMeW6aAxUf2J3iG7EfqPkrLabWgwicG+x3W0i6iuYAPizSARUPTyCLoBQErzEtYokFyDglylabATag/W7/ViUOWmJHhIRrStMwyXCHl13B2KJvBbsB7IcnVQ0Hfhoi9/H9e6m9x/ostALL77UUtYSyVtMDyx9dhcppt+h93hdITxF5AOqJY23a1bWy2P/QO1MMjUt2KWy9KOAAwkvRtLA==
         * code : 1000
         * message : 处理成功。
         * data : {"param_list":[{"card_iin":"31048950","city_code":"2400","credit_no":"","issuer_code":"05202400","issuer_name":"吉林长白山管委会","settle_unit":"","status":"1","status_name":"正常","verify_code":"","vterminal_no":""},{"card_iin":"31048913","city_code":"2411","credit_no":"","issuer_code":"04832411","issuer_name":"吉林榆树","settle_unit":"","status":"1","status_name":"正常","verify_code":"","vterminal_no":""},{"card_iin":"31048914","city_code":"2412","credit_no":"","issuer_code":"04842412","issuer_name":"吉林农安","settle_unit":"","status":"1","status_name":"正常","verify_code":"","vterminal_no":""},{"card_iin":"31048915","city_code":"2413","credit_no":"","issuer_code":"04852413","issuer_name":"吉林德惠","settle_unit":"","status":"1","status_name":"正常","verify_code":"","vterminal_no":""},{"card_iin":"31048916","city_code":"2415","credit_no":"00000000","issuer_code":"04862415","issuer_name":"吉林九台","settle_unit":"24151001","status":"1","status_name":"正常","verify_code":"00000000000000000000000000000000","vterminal_no":"413124151001"},{"card_iin":"31048922","city_code":"2432","credit_no":"","issuer_code":"04922432","issuer_name":"吉林伊通","settle_unit":"","status":"1","status_name":"正常","verify_code":"","vterminal_no":""},{"card_iin":"31048927","city_code":"2451","credit_no":"","issuer_code":"04972451","issuer_name":"吉林通化","settle_unit":"","status":"1","status_name":"正常","verify_code":"","vterminal_no":""},{"card_iin":"31048928","city_code":"2452","credit_no":"","issuer_code":"04982452","issuer_name":"吉林辉南","settle_unit":"","status":"1","status_name":"正常","verify_code":"","vterminal_no":""},{"card_iin":"31048929","city_code":"2453","credit_no":"","issuer_code":"04992453","issuer_name":"吉林柳河","settle_unit":"","status":"1","status_name":"正常","verify_code":"","vterminal_no":""},{"card_iin":"31048931","city_code":"2455","credit_no":"","issuer_code":"05012455","issuer_name":"吉林集安","settle_unit":"","status":"1","status_name":"正常","verify_code":"","vterminal_no":""},{"card_iin":"31048935","city_code":"2472","credit_no":"","issuer_code":"05052472","issuer_name":"吉林洮南","settle_unit":"","status":"1","status_name":"正常","verify_code":"","vterminal_no":""},{"card_iin":"31048937","city_code":"2477","credit_no":"","issuer_code":"05072477","issuer_name":"吉林镇赉","settle_unit":"","status":"1","status_name":"正常","verify_code":"","vterminal_no":""},{"card_iin":"31048938","city_code":"2478","credit_no":"","issuer_code":"05082478","issuer_name":"吉林通榆","settle_unit":"","status":"1","status_name":"正常","verify_code":"","vterminal_no":""},{"card_iin":"31048951","city_code":"2482","credit_no":"00000000","issuer_code":"05212482","issuer_name":"吉林双阳","settle_unit":"24820000","status":"1","status_name":"正常","verify_code":"00000000000000000000000000000000","vterminal_no":"248200000001"},{"card_iin":"31048947","city_code":"2511","credit_no":"","issuer_code":"05172511","issuer_name":"吉林扶余","settle_unit":"","status":"1","status_name":"正常","verify_code":"","vterminal_no":""},{"card_iin":"31048948","city_code":"2512","credit_no":"","issuer_code":"05182512","issuer_name":"吉林长岭","settle_unit":"","status":"1","status_name":"正常","verify_code":"","vterminal_no":""}],"server_time":"2020-08-12T16:07:35"}
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
             * param_list : [{"card_iin":"31048950","city_code":"2400","credit_no":"","issuer_code":"05202400","issuer_name":"吉林长白山管委会","settle_unit":"","status":"1","status_name":"正常","verify_code":"","vterminal_no":""},{"card_iin":"31048913","city_code":"2411","credit_no":"","issuer_code":"04832411","issuer_name":"吉林榆树","settle_unit":"","status":"1","status_name":"正常","verify_code":"","vterminal_no":""},{"card_iin":"31048914","city_code":"2412","credit_no":"","issuer_code":"04842412","issuer_name":"吉林农安","settle_unit":"","status":"1","status_name":"正常","verify_code":"","vterminal_no":""},{"card_iin":"31048915","city_code":"2413","credit_no":"","issuer_code":"04852413","issuer_name":"吉林德惠","settle_unit":"","status":"1","status_name":"正常","verify_code":"","vterminal_no":""},{"card_iin":"31048916","city_code":"2415","credit_no":"00000000","issuer_code":"04862415","issuer_name":"吉林九台","settle_unit":"24151001","status":"1","status_name":"正常","verify_code":"00000000000000000000000000000000","vterminal_no":"413124151001"},{"card_iin":"31048922","city_code":"2432","credit_no":"","issuer_code":"04922432","issuer_name":"吉林伊通","settle_unit":"","status":"1","status_name":"正常","verify_code":"","vterminal_no":""},{"card_iin":"31048927","city_code":"2451","credit_no":"","issuer_code":"04972451","issuer_name":"吉林通化","settle_unit":"","status":"1","status_name":"正常","verify_code":"","vterminal_no":""},{"card_iin":"31048928","city_code":"2452","credit_no":"","issuer_code":"04982452","issuer_name":"吉林辉南","settle_unit":"","status":"1","status_name":"正常","verify_code":"","vterminal_no":""},{"card_iin":"31048929","city_code":"2453","credit_no":"","issuer_code":"04992453","issuer_name":"吉林柳河","settle_unit":"","status":"1","status_name":"正常","verify_code":"","vterminal_no":""},{"card_iin":"31048931","city_code":"2455","credit_no":"","issuer_code":"05012455","issuer_name":"吉林集安","settle_unit":"","status":"1","status_name":"正常","verify_code":"","vterminal_no":""},{"card_iin":"31048935","city_code":"2472","credit_no":"","issuer_code":"05052472","issuer_name":"吉林洮南","settle_unit":"","status":"1","status_name":"正常","verify_code":"","vterminal_no":""},{"card_iin":"31048937","city_code":"2477","credit_no":"","issuer_code":"05072477","issuer_name":"吉林镇赉","settle_unit":"","status":"1","status_name":"正常","verify_code":"","vterminal_no":""},{"card_iin":"31048938","city_code":"2478","credit_no":"","issuer_code":"05082478","issuer_name":"吉林通榆","settle_unit":"","status":"1","status_name":"正常","verify_code":"","vterminal_no":""},{"card_iin":"31048951","city_code":"2482","credit_no":"00000000","issuer_code":"05212482","issuer_name":"吉林双阳","settle_unit":"24820000","status":"1","status_name":"正常","verify_code":"00000000000000000000000000000000","vterminal_no":"248200000001"},{"card_iin":"31048947","city_code":"2511","credit_no":"","issuer_code":"05172511","issuer_name":"吉林扶余","settle_unit":"","status":"1","status_name":"正常","verify_code":"","vterminal_no":""},{"card_iin":"31048948","city_code":"2512","credit_no":"","issuer_code":"05182512","issuer_name":"吉林长岭","settle_unit":"","status":"1","status_name":"正常","verify_code":"","vterminal_no":""}]
             * server_time : 2020-08-12T16:07:35
             */

            private String server_time;
            private List<ParamListBean> param_list;

            public String getServer_time() {
                return server_time;
            }

            public void setServer_time(String server_time) {
                this.server_time = server_time;
            }

            public List<ParamListBean> getParam_list() {
                return param_list;
            }

            public void setParam_list(List<ParamListBean> param_list) {
                this.param_list = param_list;
            }

            public static class ParamListBean {
                /**
                 * card_iin : 31048950
                 * city_code : 2400
                 * credit_no :
                 * issuer_code : 05202400
                 * issuer_name : 吉林长白山管委会
                 * settle_unit :
                 * status : 1
                 * status_name : 正常
                 * verify_code :
                 * vterminal_no :
                 */

                private String card_iin;
                private String city_code;
                private String credit_no;
                private String issuer_code;
                private String issuer_name;
                private String settle_unit;
                private String status;
                private String status_name;
                private String verify_code;
                private String vterminal_no;

                public String getCard_iin() {
                    return card_iin;
                }

                public void setCard_iin(String card_iin) {
                    this.card_iin = card_iin;
                }

                public String getCity_code() {
                    return city_code;
                }

                public void setCity_code(String city_code) {
                    this.city_code = city_code;
                }

                public String getCredit_no() {
                    return credit_no;
                }

                public void setCredit_no(String credit_no) {
                    this.credit_no = credit_no;
                }

                public String getIssuer_code() {
                    return issuer_code;
                }

                public void setIssuer_code(String issuer_code) {
                    this.issuer_code = issuer_code;
                }

                public String getIssuer_name() {
                    return issuer_name;
                }

                public void setIssuer_name(String issuer_name) {
                    this.issuer_name = issuer_name;
                }

                public String getSettle_unit() {
                    return settle_unit;
                }

                public void setSettle_unit(String settle_unit) {
                    this.settle_unit = settle_unit;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getStatus_name() {
                    return status_name;
                }

                public void setStatus_name(String status_name) {
                    this.status_name = status_name;
                }

                public String getVerify_code() {
                    return verify_code;
                }

                public void setVerify_code(String verify_code) {
                    this.verify_code = verify_code;
                }

                public String getVterminal_no() {
                    return vterminal_no;
                }

                public void setVterminal_no(String vterminal_no) {
                    this.vterminal_no = vterminal_no;
                }
            }
        }
    }
}
