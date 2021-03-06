package com.zjjxl.panda.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.LocationSource;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zjjxl.panda.R;
import com.zjjxl.panda.beans.QueryBindCradbean;
import com.zjjxl.panda.https.HttpManager;
import com.zjjxl.panda.uis.LoginActivity;
import com.zjjxl.panda.uis.OpenCardActivity;
import com.zjjxl.panda.uis.ShowAccessChannelActivity;
import com.zjjxl.panda.utils.Contants;
import com.zjjxl.panda.utils.LUtils;
import com.zjjxl.panda.utils.ShareUtil;
import com.zjjxl.panda.utils.StatusBarUtil;
import com.zjjxl.panda.utils.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.friends.Wechat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Fragment_Main extends Fragment implements View.OnClickListener, PlatformActionListener, AMapLocationListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    AMapLocationClient mlocationClient;
    AMapLocationClientOption mLocationOption;
    LocationSource.OnLocationChangedListener mListener;
    private String mParam1;
    private String mParam2;


    private Button mBtn_eidbind;
    private Button mBtncard_savemoney;
    private TextView mHome_tv_location;
    private Button mMain_opencard;
    private LinearLayout mFragment_main_bycard1;
    private LinearLayout mFragment_main_bycard2;
    private String mLogin_phone;
    private Platform mWechat;
    private String TAG = "Fragment_Main";

    public Fragment_Main() {
        // Required empty public constructor
    }


    public static Fragment_Main newInstance(String param1, String param2) {
        Fragment_Main fragment = new Fragment_Main();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        StatusBarUtil.setDrawable(getActivity(), R.drawable.mine_title_color);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        StatusBarUtil.setStatusBarLightMode(getActivity().getWindow());
        StatusBarUtil.setDrawable(getActivity(), R.drawable.mine_title_color);
        View inflate = inflater.inflate(R.layout.fragment__main, container, false);

        initView(inflate);

        return inflate;
    }

    @Override
    public void onResume() {
        super.onResume();
        StatusBarUtil.setStatusBarLightMode(getActivity().getWindow());
        StatusBarUtil.setDrawable(getActivity(), R.drawable.mine_title_color);
        mLogin_phone = ShareUtil.getString(Contants.LOGIN_USER_PHONE);
    }

    private void initView(View inflate) {

        mBtn_eidbind = inflate.findViewById(R.id.btn_eidbind);
        mBtncard_savemoney = inflate.findViewById(R.id.btncard_savemoney);
        mMain_opencard = inflate.findViewById(R.id.main_opencard);
        mHome_tv_location = inflate.findViewById(R.id.home_tv_location);
        mFragment_main_bycard1 = inflate.findViewById(R.id.fragment_main_bycard1);
        mFragment_main_bycard2 = inflate.findViewById(R.id.fragment_main_bycard2);

        mBtn_eidbind.setOnClickListener(this);
        mBtncard_savemoney.setOnClickListener(this);
        mMain_opencard.setOnClickListener(this);
        mFragment_main_bycard1.setOnClickListener(this);
        mFragment_main_bycard2.setOnClickListener(this);
        initLocation();

        mWechat = ShareSDK.getPlatform(Wechat.NAME);
        mWechat.setPlatformActionListener(Fragment_Main.this);
    }



    private void initLocation() {
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(getActivity());
            //初始化定位参数
            mLocationOption = new AMapLocationClientOption();
            //设置定位回调监听
            mLocationOption.setOnceLocation(true);
            mLocationOption.setMockEnable(true);
            mLocationOption.setNeedAddress(true);
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();//启动定位
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onClick(View v) {
        if (mLogin_phone != null) {
        switch (v.getId()) {
            case R.id.btncard_savemoney:
                Intent intent = new Intent(getActivity(), ShowAccessChannelActivity.class);
                startActivity(intent);
                break;
            case R.id.main_opencard:
                Intent intent2 = new Intent(getActivity(), OpenCardActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_eidbind:
//                    Intent intent3= new Intent(getActivity(), ShuangYSaveMoneyActivity.class);
//                    startActivity(intent3);
                break;
            case R.id.fragment_main_bycard1:
            case R.id.fragment_main_bycard2:
                checkWeChatlogin();
                break;

        }
        } else {
            ToastUtils.showToast(getActivity(), "请登录后再试");
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }
    }

    private void checkWeChatlogin() {
        mWechat.showUser(null);

    }


    public void toWeChatproject() {
        String appId = "wx066b02355bf9f39b"; // 填应用AppId
        IWXAPI api = WXAPIFactory.createWXAPI(getActivity(), appId);

        WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
        req.userName = "gh_ad3fcc78de0c"; // 小程序原始id
        req.path = "/pages/index/index";                  //拉起小程序页面的可带参路径，不填默认拉起小程序首页
        req.miniprogramType = WXLaunchMiniProgram.Req.MINIPTOGRAM_TYPE_RELEASE;
        api.sendReq(req);
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                amapLocation.getLatitude();//获取纬度
                amapLocation.getLongitude();//获取经度
                amapLocation.getAccuracy();//获取精度信息

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(amapLocation.getTime());
                df.format(date);//定位时间
                mHome_tv_location.setText(amapLocation.getCity());
                Log.e("AmapError",
                        "定位成功 ：：" +
                                "amapLocation.getLocationType():" + amapLocation.getLocationType()
                                + ", 获取纬度:" + amapLocation.getLatitude()
                                + ", 获取经度:" + amapLocation.getLongitude()
                                + ", 城区:" + amapLocation.getDistrict()
                                + ", 获取经度:" + amapLocation.getDistrict()
                                + ", 街道:" + amapLocation.getStreet()
                                + ", 城市:" + amapLocation.getCity()
                                + ", 获取经度:" + amapLocation.getDistrict()
                                + ", 获取精度信息:" + amapLocation.getAccuracy())
                ;
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }
        }
    }
    //    public static Bitmap capture(Activity activity) {
    //        activity.getWindow().getDecorView().setDrawingCacheEnabled(true);
    //        Bitmap bmp = activity.getWindow().getDecorView().getDrawingCache();
    //        return bmp;
    //    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        LUtils.d(TAG, "微信登录成功!");
        Iterator ite = hashMap.entrySet().iterator();
        //        mDialog2.dismiss();

        while (ite.hasNext()) {
            Map.Entry entry = (Map.Entry) ite.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            LUtils.d(TAG, key + "：-------------- " + value);
            LUtils.d(TAG, hashMap.toString());
            LUtils.d(TAG, (String) hashMap.get("unionid"));

        }
        queryUserInfoByUserMid((String) hashMap.get("unionid"));

    }

    private void queryUserInfoByUserMid(String unionid) {
        Call<QueryBindCradbean> queryCZCityCall = HttpManager.getInstance().getHttpClient3().
                updateUserOpenId(ShareUtil.getString(Contants.LOGIN_USERMEMBERID),unionid);
        queryCZCityCall.enqueue(new Callback<QueryBindCradbean>() {
            @Override
            public void onResponse(Call<QueryBindCradbean> call, Response<QueryBindCradbean> response) {
                if (response.body() != null) {
                    toWeChatproject();

                }
            }

            @Override
            public void onFailure(Call<QueryBindCradbean> call, Throwable t) {

            }
        });
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {

        LUtils.d(TAG, "微信登录失败!" + i + throwable);
        Toast.makeText(getActivity(), "微信登录失败!" + i + throwable, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancel(Platform platform, int i) {
        LUtils.d(TAG, "微信取消登录!" + i);
        Toast.makeText(getActivity(), "微信取消登录!" + i, Toast.LENGTH_SHORT).show();
    }
}
