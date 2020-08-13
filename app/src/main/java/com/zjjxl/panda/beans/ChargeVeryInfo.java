package com.zjjxl.panda.beans;

public class ChargeVeryInfo {
    /**
     * 卡应用序列号
     */
    public String cardSeq;
    /**
     * 交易序号
     */
    public String cardIndex;
    /**
     * 卡片余额
     */
    public String cardLeft;
    /**
     * 加密算法标识 国际算法:0,国密算法:1
     */
    public int algorithmType;

    /**
     * 卡类型
     */
    public String cardType;
    /**
     * 城市代码
     */
    public String cityCode;
    /**
     * 随机数
     */
    public String domNo;
    /**
     * 发卡机构代码
     */
    public String issuerCode;
    /**
     * 秘钥版本号
     */
    public String keyVer;

    /**
     * mac1
     */
    public String mac1;
    /**
     * 算法表示
     */
    public String mathFlag;

    public String paymentType;

    /**
     * 卡启用状态  01已开启
     */
    public String isOpen;

}
