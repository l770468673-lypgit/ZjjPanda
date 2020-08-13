package com.zjjxl.panda.beans;


import java.io.Serializable;

public class BalanceRecordBean implements Serializable {
    /**
     * 金额
     */
    public String money;
    /**
     * 0- 充值 1-消费
     */
    public int type;
    /**
     * 联机序列号
     */
    public String cardIndex;
    /**
     * 交易时间
     */
    public String time;
}
