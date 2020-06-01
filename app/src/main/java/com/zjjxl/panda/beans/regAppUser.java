package com.zjjxl.panda.beans;

public class regAppUser {

    /**
     * {"userInfo":{"createTime":"2020-05-29 16:21:38","id":"ff8080816f4045af01703d2131c00034","lastLoginTime":"2020-05-29 16:21:38","mobile":"15689123357","openId":"","orgMemberId":"ff8080816f4045af016f6409510a0000","password":"c8837b23ff8aaa8a2dde915473ce0991","photo":"https://fdl.ileyouzunyi.com/group1/M01/01/E1/jI_4f15rT4eAKKbvAAAVo37dQ5E128.jpg","unionId":"","userMemberId":"ff8080816f4045af01703d2131c00034","userName":"匿名u52916213707"},"status":true,"msg":"认证成功"}
     */

    /**
     * userInfo : {"createTime":"2020-01-10 10:10:48","id":"ff8080816f89cb95016f8d37e55a0012","lastLoginTime":"2020-05-29 15:25:45","mobile":"15689123357","openId":"oelNt5cHvCS95m-zhbhOSJSjvfM4","orgMemberId":"ff8080816f4045af016f6409510a0000","password":"903b77606da996aabffa247b46461d14","photo":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJ7WJxtILFKyJuau5ZjqSpmn2BuiaVGJajsIoEgUBPjibRbfjFFxcr8ro6CZIXHWsHIibicERwtJprb1g/132","unionId":"","userMemberId":"ff8080816f4045af01703d2131c00034","userName":"Keep"}
     * status : true
     * msg : 用户存在，返回用户信息
     */

    private UserInfoBean userInfo;
    private boolean status;
    private String msg;

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class UserInfoBean {
        /**
         * createTime : 2020-01-10 10:10:48
         * id : ff8080816f89cb95016f8d37e55a0012
         * lastLoginTime : 2020-05-29 15:25:45
         * mobile : 15689123357
         * openId : oelNt5cHvCS95m-zhbhOSJSjvfM4
         * orgMemberId : ff8080816f4045af016f6409510a0000
         * password : 903b77606da996aabffa247b46461d14
         * photo : https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJ7WJxtILFKyJuau5ZjqSpmn2BuiaVGJajsIoEgUBPjibRbfjFFxcr8ro6CZIXHWsHIibicERwtJprb1g/132
         * unionId :
         * userMemberId : ff8080816f4045af01703d2131c00034
         * userName : Keep
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


    /**
     * userInfo : {"createTime":"2020-03-16 10:24:33","id":"ff80808170d398080170e128360c0000",
     * "lastLoginTime":"","mobile":"15699999999","openId":"","orgMemberId":
     * "ff8080816f4045af016f6409510a0000","photo":
     * "https://fdl.ileyouzunyi.com/group1/M01/01/E1/jI_4f15rT4eAKKbvAAAVo37dQ5E128.jpg","unionId":"",
     * "userMemberId":"ff8080816f4045af0170e12835e50041","userName":"匿名u31610243301"}
     * status : true
     * msg : 认证成功
     */

//    {"userInfo":{"createTime":"2020-01-10 10:10:48","id":"ff8080816f89cb95016f8d37e55a0012",
//    "lastLoginTime":"2020-05-29 15:29:56","mobile":"15689123357","openId":"oelNt5cHvCS95m-zhbhOSJSjvfM4",
//    "orgMemberId":"ff8080816f4045af016f6409510a0000",
//    "password":"903b77606da996aabffa247b46461d14",
//    "photo":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJ7WJxtIL
//    FKyJuau5ZjqSpmn2BuiaVGJajsIoEgUBPjibRbfjFFxcr8ro6CZIXHWsHIibicERwtJprb1g/132",
//    "unionId":"","userMemberId":"ff8080816f4045af01703d2131c00034","userName":"Keep"},
//    "status":true,"msg":"用户存在，返回用户信息"}

}
