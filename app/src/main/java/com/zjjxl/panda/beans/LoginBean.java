package com.zjjxl.panda.beans;

public class LoginBean {

    /**
     * msg : 登录成功
     * userInfo : {"lastLoginTime":"2020-05-29 15:27:13",
     * "orgMemberId":"ff8080816f4045af016f6409510a0000",
     * "password":"903b77606da996aabffa247b46461d14",
     * "unionId":"","userMemberId":"ff8080816f4045af01703d2131c00034",
     * "createTime":"2020-01-10 10:10:48",
     * "openId":"oelNt5cHvCS95m-zhbhOSJSjvfM4",
     * "mobile":"15689123357",
     * "photo":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJ7WJxtILFKyJuau5ZjqSpmn2Buia
     * VGJajsIoEgUBPjibRbfjFFxcr8ro6CZIXHWsHIibicERwtJprb1g/132",
     * "id":"ff8080816f89cb95016f8d37e55a0012","userName":"Keep"}
     * status : true
     */

    private String msg;
    private UserInfoBean userInfo;
    private boolean status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public static class UserInfoBean {
        /**
         * lastLoginTime : 2020-05-29 15:27:13
         * orgMemberId : ff8080816f4045af016f6409510a0000
         * password : 903b77606da996aabffa247b46461d14
         * unionId :
         * userMemberId : ff8080816f4045af01703d2131c00034
         * createTime : 2020-01-10 10:10:48
         * openId : oelNt5cHvCS95m-zhbhOSJSjvfM4
         * mobile : 15689123357
         * photo : https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJ7WJxtILFKyJuau5ZjqSpmn2BuiaVGJajsIoEgUBPjibRbfjFFxcr8ro6CZIXHWsHIibicERwtJprb1g/132
         * id : ff8080816f89cb95016f8d37e55a0012
         * userName : Keep
         */

        private String lastLoginTime;
        private String orgMemberId;
        private String password;
        private String unionId;
        private String userMemberId;
        private String createTime;
        private String openId;
        private String mobile;
        private String photo;
        private String id;
        private String userName;

        public String getLastLoginTime() {
            return lastLoginTime;
        }

        public void setLastLoginTime(String lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
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

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getOpenId() {
            return openId;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
