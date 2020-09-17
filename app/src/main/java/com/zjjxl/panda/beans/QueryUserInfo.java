package com.zjjxl.panda.beans;

public class QueryUserInfo {

    /**
     * userInfo : {"createTime":"2020-05-29 16:21:38","id":"ff8080816f4045af01703d2131c00034","lastLoginTime":"2020-09-02 12:33:29","mobile":"15689123357","openId":"onV8dxDL5y1Uce7o0oStKV7p75qw","orgMemberId":"ff8080816f4045af016f6409510a0000","password":"52c69e3a57331081823331c4e69d3f2e","photo":"https://fdl.ileyouzunyi.com/group1/M01/01/E1/jI_4f15rT4eAKKbvAAAVo37dQ5E128.jpg","unionId":"","userMemberId":"ff8080816f4045af01703d2131c00034","userName":"匿名u52916213707"}
     * status : true
     * msg : 查询成功
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
         * createTime : 2020-05-29 16:21:38
         * id : ff8080816f4045af01703d2131c00034
         * lastLoginTime : 2020-09-02 12:33:29
         * mobile : 15689123357
         * openId : onV8dxDL5y1Uce7o0oStKV7p75qw
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
}
