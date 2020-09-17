package com.zjjxl.panda.https;

import com.zjjxl.panda.beans.Bean_BaseCard;
import com.zjjxl.panda.beans.Bean_zjjCreateOrder;
import com.zjjxl.panda.beans.BindCardIsBean;
import com.zjjxl.panda.beans.CardCharge_bean;
import com.zjjxl.panda.beans.CardRentApply_Bean;
import com.zjjxl.panda.beans.LoginBean;
import com.zjjxl.panda.beans.PramDetal_Bean;
import com.zjjxl.panda.beans.QueryBindCradbean;
import com.zjjxl.panda.beans.QueryCZCity;
import com.zjjxl.panda.beans.QueryUserInfo;
import com.zjjxl.panda.beans.SessioIdBean;
import com.zjjxl.panda.beans.SmsCode;
import com.zjjxl.panda.beans.bean_person;
import com.zjjxl.panda.beans.regAppUser;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Define all rest API with server here Use open source Retrofit for http access
 * http://square.github.io/retrofit/
 */
public interface ClientRestAPI {

    // 查询身份信息
    @FormUrlEncoded
    @POST("guard/entranceGuard")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<bean_person> QueryIdCard(@Field("actionId") String actionId,
                                  @Field("reqId") String reqId);

    //    1.用户注册接口
//    接口地址： /foreign/registerAppUser.jspx
    @FormUrlEncoded
    @POST("foreign/registerAppUser.jspx")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<regAppUser> registerAppUser(@Field("phone") String phone,
                                     @Field("password") String password
    );

    //    /foreign/loginAppUser.jspx
    //登录
    @FormUrlEncoded
    @POST("foreign/loginAppUser.jspx")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<LoginBean> loginAppUser(@Field("phone") String phone,
                                 @Field("password") String password
    );

    // 短信验证码获取 /ui/register/sendVerificationCode.jspx
    @FormUrlEncoded
    @POST("ui/register/sendVerificationCode.jspx")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<SmsCode> sendVerificationCode(@Field("mobile") String mobile

    );


    // 短信校验码校验  ：/ui/register/validate.jspx
    @FormUrlEncoded
    @POST("ui/register/validate.jspx")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<SmsCode> validate(@Field("smsActiveCode") String smsActiveCode);

    //https://panda.stone3a.com/ui/mini/login/getSessionId.jspx?

    //     获取ssionID
//    @FormUrlEncoded
    @GET("ui/mini/login/getSessionId.jspx")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<SessioIdBean> getSessionId();

    //请求当前可充值地址
    //http://panda.stone3a.com/foreign/queryAccessChannelList.jspx
    @GET("foreign/queryAccessChannelList.jspx")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<QueryCZCity> queryAccessChannelList();


    //获取验证码
    // @FormUrlEncoded
    //    @POST("ui/register/validate.jspx")
    //    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")

    //27.请获取验证码的接口
    @FormUrlEncoded
    @POST("foreign/getPhoneVerifyCode.jspx")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<QueryCZCity> getPhoneVerifyCode(@Field("userPhone") String userPhone);


    //28.请用户绑定卡信息的接口
    @POST("/foreign/userBindCard.jspx")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<QueryCZCity> userBindCard(@Body RequestBody body);


    //29.查询用户信息的接口
    @FormUrlEncoded
    @POST("/foreign/queryUserInfoByUserMid.jspx")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<QueryBindCradbean> queryUserInfoByUserMid(@Field("userMid") String userMid);

    //29.更新用户oenid
    @FormUrlEncoded
    @POST("/foreign/updateUserOpenId.jspx")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<QueryBindCradbean> updateUserOpenId(@Field("userMid") String userMid, @Field("openId") String openId);


    //28.请用户绑定卡信息的接口
    @FormUrlEncoded
    @POST("/foreign/userBindCard.jspx")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<BindCardIsBean> userBindCard2(@Field("userJson") String userJson);


    // /foreign/updateUserPassword.jspx
    // 重置密码
    @FormUrlEncoded
    @POST("/foreign/updateUserPassword.jspx")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<BindCardIsBean> updateUserPassword(@Field("userMid") String userMid,
                                            @Field("oldPassword") String oldPassword,
                                            @Field("newPassword") String newPassword
    );

    //忘记密码
    @FormUrlEncoded
    @POST("/foreign/resetUserPassword.jspx")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<BindCardIsBean> resetUserPassword(@Field("userMid") String userMid,
                                           @Field("newPassword") String newPassword
    );

    //忘记密码
    @FormUrlEncoded
    @POST("/foreign/queryUserInfoByUserMobile.jspx")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<QueryUserInfo> queryUserInfoByUserMobile(@Field("userMobile") String userMobile
    );

//    、、------------------------  下方是关乎于 交通卡 ------------------------------

//    、、------------------------  //充值申请------------------------------
    //充值申请

    /**
     * String unit_code; // 单元编号 平台参数同步接口获得，与卡城市参数一致
     * String terminal_no; // 虚拟终端号 平台参数同步接口获得，与卡城市参数一致
     * String city_code; // 城市代码
     * String issuer_code; // 发卡机构标识
     * String out_trade_no; // 接入商业务单号
     * String card_no; // 卡应用序列号
     * String card_type; // 卡类型
     * String card_seq; // 卡交易序号
     * String trade_time; // 交易日期时间 格式：yyyy-MM-dd’T’HH:mm:ss
     * double balance_pre; // 卡片交易前余额 单位：元，无余额0.00
     * double trade_amount; // 交易金额 单位：元，如：2.00
     * String card_random; // 卡随机数
     * String card_mac; // MAC1
     * String algorithm_type; // 加密算法标识 国际算法:0,国密算法:1
     * String mode_version; // 交易版本号 当前：1500
     * ==
     * //    {
     * //        "status": "true/false",
     * //            "msg": "系统返回消息",
     * //            "data": "参考附件长白行TSM-支付及业务接口 充值申请返回值"
     * //    }
     */
    @FormUrlEncoded
    @POST("api/card/cardChargeApply.jspx")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<CardCharge_bean> cardChargeApply(@Field("unit_code") String unit_code,
                                          @Field("terminal_no") String terminal_no,
                                          @Field("city_code") String city_code,
                                          @Field("issuer_code") String issuer_code,
                                          @Field("out_trade_no") String out_trade_no,
                                          @Field("card_no") String card_no,
                                          @Field("card_type") String card_type,
                                          @Field("card_seq") String card_seq,
                                          @Field("trade_time") String trade_time,
                                          @Field("balance_pre") double balance_pre,
                                          @Field("trade_amount") double trade_amount,
                                          @Field("card_random") String card_random,
                                          @Field("card_mac") String card_mac,
                                          @Field("algorithm_type") String algorithm_type,
                                          @Field("mode_version") String mode_version);


//    、、------------------------  //充值提交------------------------------

    /**
     * 需要参数
     * //    String unit_code; // 单元编号 平台参数同步接口获得，与卡城市参数一致
     * //    String terminal_no; // 虚拟终端编号 平台参数同步接口获得，与卡城市参数一致
     * //    String city_code; // 城市代码
     * //    String issuer_code; // 发卡机构标识
     * //    String out_trade_no; // 接入商业务单号 trade_no与out_trade_no二选一或都传
     * //    String terminal_seq; // 终端交易序号 申请返回
     * //    String card_no; // 卡应用序列号
     * //    String card_type; // 卡类型
     * //    String card_seq; // 卡交易序号
     * //    String trade_time; // 交易日期时间 格式：yyyy-MM-dd’T’HH:mm:ss
     * //    double balance_aft; // 卡片交易后余额 单位：元，无为 0.00
     * //    double trade_amount; // 交易金额
     * //    String trade_tac; // 交易验证码
     * //    String mode_version; // 交易版本号
     * //    String card_status; // 写卡状态 0：成功 1表示失败
     * //    返回数据
     * //    {
     * //        "status": "true/false",
     * //            "msg": "系统返回消息",
     * //            "data": "参考附件长白行TSM-支付及业务接口 充值提交返回值"
     * //    }
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/card/cardChargeConfirm.jspx")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<Bean_BaseCard> cardChargeConfirm(@Field("unit_code") String unit_code,
                                          @Field("terminal_no") String terminal_no,
                                          @Field("city_code") String city_code,
                                          @Field("issuer_code") String issuer_code,
                                          @Field("out_trade_no") String out_trade_no,
                                          @Field("terminal_seq") String terminal_seq,
                                          @Field("card_no") String card_no,
                                          @Field("card_type") String card_type,
                                          @Field("card_seq") String card_seq,
                                          @Field("trade_time") String trade_time,
                                          @Field("balance_aft") double balance_aft,
                                          @Field("trade_amount") double trade_amount,
                                          @Field("trade_tac") String trade_tac,
                                          @Field("mode_version") String mode_version,
                                          @Field("card_status") String card_status);

    //    、、------------------------  //租卡提交------------------------------
//    接口地址 *

    /**
     * /            /api/card/cardRentConfirm.jspx
     * ///String unit_code; // 单元编号 平台参数同步接口获得，与卡城市参数一致
     * //    String terminal_no; // 终端号 平台参数同步接口获得，与卡城市参数一致
     * //    String city_code; // 城市代码
     * //    String issuer_code; // 发卡机构标识
     * //    String trade_no; // TSM平台单号 trade_no与out_trade_no二选一或都传
     * //    String out_trade_no; // 接入商业务单号 trade_no与out_trade_no二选一或都传
     * //    String card_no; // 卡应用序列号
     * //    String card_type; // 卡类型
     * //    int account_no; // 账户ID 申请原样回传
     * //    String trade_time; // 交易日期时间 格式：yyyy-MM-dd’T’HH:mm:ss，租卡申请原样回传
     * //    String terminal_seq; // 终端交易序号 申请原样回传
     * //    String mode_version; // 售卡版本号 当前：1500
     * //    String ard_status; // 写卡状态 0：成功 1表示失败
     * //
     * //    返回数据
     * //    {
     * //        "status": "true/false",
     * //            "msg": "系统返回消息",
     * //            "data": "参考附件长白行TSM-支付及业务接口 充值提交返回值"
     * //    }
     */
    @FormUrlEncoded
    @POST("api/card/cardRentConfirm.jspx")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<Bean_BaseCard> cardRentConfirm(@Field("unit_code") String unit_code,
                                        @Field("terminal_no") String terminal_no,
                                        @Field("city_code") String city_code,
                                        @Field("issuer_code") String issuer_code,
                                        @Field("trade_no") String trade_no,
                                        @Field("out_trade_no") String out_trade_no,
                                        @Field("card_no") String card_no,
                                        @Field("card_type") String card_type,
                                        @Field("account_no") int account_no,
                                        @Field("trade_time") String trade_time,
                                        @Field("terminal_seq") String terminal_seq,
                                        @Field("mode_version") String mode_version,
                                        @Field("card_status") String card_status
    );


    //    、、------------------------  //租卡申请------------------------------

    /**
     * String unit_code 单元编号 平台参数同步接口获得，与卡城市参数一致
     * String terminal_no 虚拟终端号 平台参数同步接口获得，与卡城市参数一致
     * String city_code 城市代码
     * String issuer_code 发卡机构标识
     * String out_trade_no 接入商业务单号(非必输)
     * String card_no 卡应用序列号
     * String card_type 卡类型
     * String date_begin 售卡起始日期 格式：yyyy-MM-dd
     * String date_end 售卡有效日期 格式：yyyy-MM-dd
     * String card_random 卡随机数
     * String mode_type 售卡模式 00押金/01工本
     * double deposit_amount 押金/工本金额
     * String algorithm_type 加密算法标识 国际算法:0,国密算法:1
     * String mode_version 售卡版本号 当前：1500(2.0.4)
     *
     * @return
     */

    @FormUrlEncoded
    @POST("api/card/cardRentApply.jspx")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<CardRentApply_Bean> cardRentApply(@Field("unit_code") String unit_code,
                                           @Field("terminal_no") String terminal_no,
                                           @Field("city_code") String city_code,
                                           @Field("issuer_code") String issuer_code,
                                           @Field("out_trade_no") String out_trade_no,

                                           @Field("card_no") String card_no,
                                           @Field("card_type") String card_type,
                                           @Field("date_begin") String date_begin,
                                           @Field("date_end") String date_end,

                                           @Field("card_random") String card_random,
                                           @Field("mode_type") String mode_type,
                                           @Field("deposit_amount") double deposit_amount,
                                           @Field("algorithm_type") String algorithm_type,
                                           @Field("mode_version") String mode_version);
    //    、、------------------------  //用户卡信息同步------------------------------

    /**
     * String user_id; // 用户ID
     * String mobile_no; // 手机号 （非必输）
     * String request_time; // 请求时间 （非必输）
     * String create_time; // 用户创建时间 新建同创建时间
     * String update_time; // 用户变更时间
     * String status; // 用户状态 00:无效/10:有效/20:锁定/30:注销 用户状态非10时，不允许进行代扣请款
     * String memo; // 备注 （非必输）
     * String pay_conf; // 支付信息同步--JSON-ARRAY 字符串 后台进行加密处理 详细内容 参考长白行TSM-支付及业务接口 用户卡信息同步请求参数 支付信息同步内容
     * String card_list; // 用户卡列表 详细内容 参考长白行TSM-支付及业务接口 用户卡信息同步请求参数 用户卡列表内容
     * 
     * 返回数据
     * {
     * "status": "true/false",
     * "msg": "系统返回消息",
     * "data": "参考附件长白行TSM-支付及业务接口 用户卡信息同步返回值"
     * }
     */
    @FormUrlEncoded
    @POST("api/user/userSynchronize.jspx")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<Bean_BaseCard> userSynchronize(@Field("user_id") String user_id,
                                        @Field("mobile_no") String mobile_no,
                                        @Field("request_time") String request_time,
                                        @Field("create_time") String create_time,
                                        @Field("update_time") String update_time,
                                        @Field("status") String status,
                                        @Field("memo") String memo,
                                        @Field("pay_conf") String pay_conf,
                                        @Field("card_list") String card_list
    );

    //----------------------------用户支付顺序设置-------------------------

    /**
     * String user_id; // 用户ID
     * String mobile_no; // 手机号 （非必输）
     * String request_time; // 请求时间 （非必输）
     * String pay_conf; // 支付信息同步 -- JSON-ARRAY 字符串 后台进行加密处理 具体内容 参考长白行TSM-支付及业务接口 用户支付顺序设置请求参数 支付信息同步内容
     * 返回数据
     * {
     * "status": "true/false",
     * "msg": "系统返回消息",
     * "data": "参考附件长白行TSM-支付及业务接口 用户支付顺序设置返回值"
     * }
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/user/userPayableSequence.jspx")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<Bean_BaseCard> userPayableSequence(@Field("user_id") String user_id,
                                            @Field("mobile_no") String mobile_no,
                                            @Field("request_time") String request_time,
                                            @Field("create_time") String create_time,
                                            @Field("pay_conf") String pay_conf

    );

    //--------------------    用户支付能力查询------------------------------------

    /**
     * String user_id; // 用户ID
     * String request_time; // 请求时间 yyyy-MM-dd’T’HH:mm:ss （非必输）
     * 返回数据
     * {
     * "status": "true/false",
     * "msg": "系统返回消息",
     * "data": "参考附件长白行TSM-支付及业务接口 用户支付能力查询返回值"
     * }
     */
    @FormUrlEncoded
    @POST("api/user/userPayable.jspx")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<Bean_BaseCard> userPayable(@Field("user_id") String user_id,
                                    @Field("request_time") String request_time
    );

    //---------------用户签约信息同步--------------------

    /**
     * String user_id; // 用户ID
     * String mobile_no; // 手机号 （非必输）
     * String create_time; // 用户创建时间
     * String update_time; // 用户变更时间 新建同创建时间
     * String status; // 用户状态 00:无效/10:有效/20:锁定/30:注销 用户状态非10时，不允许进行代扣请款 有变动需要同步
     * String memo; // 备注 （非必输）
     * String request_time; // 请求时间 yyyy-MM-dd’T’HH:mm:ss （非必输）
     * String pay_conf; // 支付信息同步--JSON-ARRAY 字符串 后台进行加密处理 具体内容参考长白行TSM-支付及业务接口 用户签约信息同步请求参数 支付同步信息内容
     * 
     * 返回数据
     * {
     * "status": "true/false",
     * "msg": "系统返回消息",
     * "data": "参考附件长白行TSM-支付及业务接口 用户签约信息同步返回值"
     * }
     */
    @FormUrlEncoded
    @POST("/api/user/agreement.jspx")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<Bean_BaseCard> agreement(@Field("user_id") String user_id,
                                  @Field("mobile_no") String mobile_no,
                                  @Field("create_time") String create_time,
                                  @Field("update_time") String update_time,
                                  @Field("status") String status,
                                  @Field("memo") String memo,
                                  @Field("request_time") String request_time,
                                  @Field("pay_conf") String pay_conf
    );


    //------------平台参数同步-----------------
    //

    /**
     * /api/sys/paramDetail.jspx
     * 需要参数
     * String param_type; // 参数类型 CITY_CARD_TRADE 城市平台卡交易参数
     * String request_time; // 请求时间 yyyy-MM-dd’T’HH:mm:ss （非必输）
     * 返回数据
     * {
     * "status": "true/false",
     * "msg": "系统返回消息",
     * "data": "参考附件长白行TSM-支付及业务接口 平台参数同步返回值"
     * }
     */
    @FormUrlEncoded
    @POST("/api/sys/paramDetail.jspx")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<PramDetal_Bean> paramDetail(@Field("param_type") String param_type,
                                     @Field("request_time") String request_time
    );
    //-----------------------支付订单退款----------------------------------

    /**
     * String trade_no; // 平台订单号 TSM平台订单号与业务订单号可以全传，必须传一个。 创建原样返回
     * String out_trade_no; // 业务订单号 TSM平台订单号与业务订单号可以全传，必须传一个。创建原样返回
     * double trade_amount; // 退款金额
     * String out_refund_no; // 业务退款单号 （非必输）
     * String trade_time; // 退款时间 yyyy-MM-dd’T’HH: （非必输）
     * String modify_status; // 变更状态 REFUND:对订单进行退款
     * 返回数据
     * {
     * "status": "true/false",
     * "msg": "系统返回消息",
     * "data": "参考附件长白行TSM-支付及业务接口 支付订单退款返回值"
     * }
     */

    @FormUrlEncoded
    @POST("/api/order/payOrderRefund.jspx")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<Bean_BaseCard> payOrderRefund(@Field("trade_no") String trade_no,
                                       @Field("out_trade_no") String out_trade_no,
                                       @Field("trade_amount") double trade_amount,
                                       @Field("out_refund_no") String out_refund_no,
                                       @Field("trade_time") String trade_time,
                                       @Field("modify_status") String modify_status
    );

    //-------------------------支付订单关闭-----------------

    /**
     * String trade_no; // TSM平台订单号 TSM平台 TSM平台订单号与业务订单号可以全传，必须传一个。 创建原样回传
     * String out_trade_no; // 业务订单号 接入商系统 TSM平台订单号与业务订单号可以全传，必须传一个。 创建原样回传
     * String modify_status; // 变更状态 CLOSE:对订单进行关闭；
     * 返回数据
     * {
     * "status": "true/false",
     * "msg": "系统返回消息",
     * "data": "参考附件长白行TSM-支付及业务接口 支付订单关闭返回值"
     * }
     */
    @FormUrlEncoded
    @POST("/api/order/payOrderClose.jspx")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<Bean_BaseCard> payOrderClose(@Field("trade_no") String trade_no,
                                      @Field("out_trade_no") String out_trade_no,
                                      @Field("modify_status") String modify_status

    );
//    、、------------支付订单查询---------------

    /**
     * String trade_no; // TSM平台订单号 TSM平台 TSM平台订单号与业务订单号可以全传，必须传一个。 创建原样回传
     * String out_trade_no; // 业务订单号 接入商系统 TSM平台订单号与业务订单号可以全传，必须传一个。 创建原样回传
     * String request_time; // 请求时间 yyyy-MM-dd’T’HH:mm:ss （非必输）
     * 返回数据
     * {
     * "status": "true/false",
     * "msg": "系统返回消息",
     * "data": "参考附件长白行TSM-支付及业务接口 支付订单查询返回值"
     * }
     */
    @FormUrlEncoded
    @POST("/api/order/payOrderDetail.jspx")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<Bean_BaseCard> payOrderDetail(@Field("trade_no") String trade_no,
                                       @Field("request_time") String request_time

    );

    //----------------------支付订单创建--------------------

    /**
     * String user_id; // 用户ID
     * String out_trade_no; // 业务订单号 （非必输）
     * String channel_type; // 平台支付类型 支付能力查询返回用户选中--ALIPAY_DEBIT
     * String trade_time; // 交易时间 yyyy-MM-dd’T’HH:mm:ss
     * double trade_amount; // 交易金额 两位小数
     * String expire_time; // 订单失效时间 yyyy-MM-dd’T’HH:mm:ss 未设定默认5分钟 最小大于3分钟，最大不超过15分钟 代扣支付方式无效，需要主动关闭 （非必输）
     * String subject; // 订单标题
     * String return_url; // 移动端结果通知地址（或自有SDK）（非必输）
     * String notify_url; // 接入商后台结果通知地址 （非必输）
     * String platform; // 客户端平台 （非必输）
     * String imei; // 手机imei （非必输）
     * String markting; // 营销优惠参数 JSON （非必输）
     * String ext_info; // 扩展参数 JSON （非必输）
     * 返回数据
     * {
     * "status": "true/false",
     * "msg": "系统返回消息",
     * "data": "参考附件长白行TSM-支付及业务接口 支付订单创建返回值"
     * }
     */
    @FormUrlEncoded
    @POST("/api/order/create.jspx")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<Bean_zjjCreateOrder> create(@Field("user_id") String user_id,
                                     @Field("out_trade_no") String out_trade_no,
                                     @Field("channel_type") String channel_type,
                                     @Field("trade_time") String trade_time,
                                     @Field("trade_amount") double trade_amount,
                                     @Field("expire_time") String expire_time,
                                     @Field("subject") String subject,
                                     @Field("return_url") String return_url,
                                     @Field("notify_url") String notify_url,
                                     @Field("platform") String platform,
                                     @Field("imei") String imei,
                                     @Field("markting") String markting,
                                     @Field("ext_info") String ext_info);

//


}