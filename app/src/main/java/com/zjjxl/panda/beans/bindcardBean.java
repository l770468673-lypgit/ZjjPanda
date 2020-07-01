package com.zjjxl.panda.beans;


public class bindcardBean {

    private  String userMemberId;
    private  String cardNum;
    private  String bindingCode;
    private  String fullName;
    private  String sex;
    private  String idNumber;
    private  String phone;
    private  String userLogo;
    private  String phoneVerifycode;


    @Override
    public String toString() {
        return "{" +
                "userMemberId='" + userMemberId + '\'' +
                ", cardNum='" + cardNum + '\'' +
                ", bindingCode='" + bindingCode + '\'' +
                ", fullName='" + fullName + '\'' +
                ", sex='" + sex + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", phone='" + phone + '\'' +
                ", userLogo='" + userLogo + '\'' +
                ", phoneVerifycode='" + phoneVerifycode + '\'' +
                '}';
    }

    public bindcardBean(String userMemberId, String cardNum, String bindingCode, String fullName, String sex, String idNumber, String phone, String userLogo, String phoneVerifycode) {
        this.userMemberId = userMemberId;
        this.cardNum = cardNum;
        this.bindingCode = bindingCode;
        this.fullName = fullName;
        this.sex = sex;
        this.idNumber = idNumber;
        this.phone = phone;
        this.userLogo = userLogo;
        this.phoneVerifycode = phoneVerifycode;
    }
}
