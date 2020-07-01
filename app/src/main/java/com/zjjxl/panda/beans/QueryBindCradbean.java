package com.zjjxl.panda.beans;

public class QueryBindCradbean {


    /**
     * userInfo : {"createTime":"2020-05-29 16:21:38","id":"ff8080816f4045af01703d2131c00034","lastLoginTime":"2020-06-08 10:26:00","mobile":"15689123357","openId":"","orgMemberId":"ff8080816f4045af016f6409510a0000","password":"52c69e3a57331081823331c4e69d3f2e","photo":"https://fdl.ileyouzunyi.com/group1/M01/01/E1/jI_4f15rT4eAKKbvAAAVo37dQ5E128.jpg","unionId":"","userMemberId":"ff8080816f4045af01703d2131c00034","userName":"匿名u52916213707"}
     * cardInfo : {"cardBindingCode":"H363","cardNum":"3104895111100002001","createTime":"2020-07-01 14:04:00","fullName":"刘延朋","id":"ff80808172feb013017308f98e980003","idNumber":"371202199402016814","invalidTime":"2021-07-01 14:04:00","phone":"15689123357","sex":1,"userId":"ff8080816f4045af01703d2131c00034","userLogo":"Im user  pic ","userMemberId":"ff8080816f4045af01703d2131c00034"}
     * status : false
     * msg : 查询失败
     */

    private UserInfoBean userInfo;
    private CardInfoBean cardInfo;
    private boolean status;
    private String msg;

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public CardInfoBean getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(CardInfoBean cardInfo) {
        this.cardInfo = cardInfo;
    }

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

    public static class UserInfoBean {
        /**
         * createTime : 2020-05-29 16:21:38
         * id : ff8080816f4045af01703d2131c00034
         * lastLoginTime : 2020-06-08 10:26:00
         * mobile : 15689123357
         * openId :
         * orgMemberId : ff8080816f4045af016f6409510a0000
         * password : 52c69e3a57331081823331c4e69d3f2e
         * photo : https://fdl.ileyouzunyi.com/group1/M01/01/E1/jI_4f15rT4eAKKbvAAAVo37dQ5E128.jpg
         * unionId :
         * userMemberId : ff8080816f4045af01703d2131c00034
         * userName : 匿名u52916213707
         */

        private String createTime;
        private String id;
        private String lastLoginTime;
        private String mobile;
        private String openId;
        private String orgMemberId;
        private String password;
        private String photo;
        private String unionId;
        private String userMemberId;
        private String userName;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLastLoginTime() {
            return lastLoginTime;
        }

        public void setLastLoginTime(String lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getOpenId() {
            return openId;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
        }

        public String getOrgMemberId() {
            return orgMemberId;
        }

        public void setOrgMemberId(String orgMemberId) {
            this.orgMemberId = orgMemberId;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getUnionId() {
            return unionId;
        }

        public void setUnionId(String unionId) {
            this.unionId = unionId;
        }

        public String getUserMemberId() {
            return userMemberId;
        }

        public void setUserMemberId(String userMemberId) {
            this.userMemberId = userMemberId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }

    public static class CardInfoBean {
        /**
         * cardBindingCode : H363
         * cardNum : 3104895111100002001
         * createTime : 2020-07-01 14:04:00
         * fullName : 刘延朋
         * id : ff80808172feb013017308f98e980003
         * idNumber : 371202199402016814
         * invalidTime : 2021-07-01 14:04:00
         * phone : 15689123357
         * sex : 1
         * userId : ff8080816f4045af01703d2131c00034
         * userLogo : Im user  pic
         * userMemberId : ff8080816f4045af01703d2131c00034
         */

        private String cardBindingCode;
        private String cardNum;
        private String createTime;
        private String fullName;
        private String id;
        private String idNumber;
        private String invalidTime;
        private String phone;
        private int sex;
        private String userId;
        private String userLogo;
        private String userMemberId;

        public String getCardBindingCode() {
            return cardBindingCode;
        }

        public void setCardBindingCode(String cardBindingCode) {
            this.cardBindingCode = cardBindingCode;
        }

        public String getCardNum() {
            return cardNum;
        }

        public void setCardNum(String cardNum) {
            this.cardNum = cardNum;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIdNumber() {
            return idNumber;
        }

        public void setIdNumber(String idNumber) {
            this.idNumber = idNumber;
        }

        public String getInvalidTime() {
            return invalidTime;
        }

        public void setInvalidTime(String invalidTime) {
            this.invalidTime = invalidTime;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserLogo() {
            return userLogo;
        }

        public void setUserLogo(String userLogo) {
            this.userLogo = userLogo;
        }

        public String getUserMemberId() {
            return userMemberId;
        }

        public void setUserMemberId(String userMemberId) {
            this.userMemberId = userMemberId;
        }
    }
}
