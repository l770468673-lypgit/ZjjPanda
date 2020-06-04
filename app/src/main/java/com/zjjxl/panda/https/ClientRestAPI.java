package com.zjjxl.panda.https;

import com.zjjxl.panda.beans.LoginBean;
import com.zjjxl.panda.beans.SessioIdBean;
import com.zjjxl.panda.beans.SmsCode;
import com.zjjxl.panda.beans.regAppUser;

import retrofit2.Call;
import retrofit2.http.Field;
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
}