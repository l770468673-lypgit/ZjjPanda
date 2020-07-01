package com.zjjxl.panda.https;

import com.zjjxl.panda.beans.BindCardIsBean;
import com.zjjxl.panda.beans.LoginBean;
import com.zjjxl.panda.beans.QueryBindCradbean;
import com.zjjxl.panda.beans.QueryCZCity;
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

    //http://panda.stone3a.com/foreign/queryAccessChannelList.jspx
    @GET("foreign/queryAccessChannelList.jspx")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<QueryCZCity> queryAccessChannelList();


    //获取验证码
    // @FormUrlEncoded
    //    @POST("ui/register/validate.jspx")
    //    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")

     @FormUrlEncoded
    @POST("foreign/getPhoneVerifyCode.jspx")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<QueryCZCity> getPhoneVerifyCode(@Field("userPhone") String userPhone );

    @POST("/foreign/userBindCard.jspx")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<QueryCZCity> userBindCard(@Body RequestBody body);

    @FormUrlEncoded
    @POST("/foreign/queryUserInfoByUserMid.jspx")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<QueryBindCradbean> queryUserInfoByUserMid(@Field("userMid") String userMid );

    @FormUrlEncoded
    @POST("/foreign/userBindCard.jspx")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<BindCardIsBean> userBindCard2(@Field("userJson") String userJson);




}