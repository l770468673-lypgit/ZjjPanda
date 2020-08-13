package com.zjjxl.panda.beans;

import java.io.Serializable;
import java.util.List;

public class BalanceData  implements Serializable {
    /**
     * 消费充值记录
     */
    public List<BalanceRecordBean> recordList;
    /**
     *余额
     */
    public String balanceMoney;
    /**
     * 卡号
     */
    public String card_id;
    /**
     * 发卡机构代码
     */
    public String issuerCode;

}