package com.zjjxl.panda.beans;

public class Gson_AlipayRes {

    /**
     * out_trade_no : sttsm007dae99019e42a6a50982d1988772d4
     * pay_status : SUCCESS
     * pay_time : 2020-08-24T10:34:20
     * server_time : 2020-08-24T10:34:22
     * trade_no : 20200824342751593746219243214321
     */

    private String out_trade_no;
    private String pay_status;
    private String pay_time;
    private String server_time;
    private String trade_no;

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getPay_status() {
        return pay_status;
    }

    public void setPay_status(String pay_status) {
        this.pay_status = pay_status;
    }

    public String getPay_time() {
        return pay_time;
    }

    public void setPay_time(String pay_time) {
        this.pay_time = pay_time;
    }

    public String getServer_time() {
        return server_time;
    }

    public void setServer_time(String server_time) {
        this.server_time = server_time;
    }

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }
}
