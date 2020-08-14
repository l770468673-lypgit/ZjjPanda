package com.zjjxl.panda.uis;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.crb.cttic.pay.mvp.view.activity.model.entity.CardManageData;
import com.heyue.adpu_manager.module.BalanceData;
import com.heyue.adpu_manager.module.BalanceRecordBean;
import com.heyue.adpu_manager.module.ChargeVeryInfo;
import com.heyue.adpu_manager.module.OpenCardData;
import com.heyue.adpu_manager.nfc.CardManager;
import com.heyue.pay.TSMPay;
import com.heyue.pay.alipay.Alipay;
import com.heyue.pay.api.BaseSubscriber;
import com.heyue.pay.listener.BaseCallback;
import com.heyue.pay.protocol.BaseResp;
import com.heyue.pay.protocol.PayRequestRec;
import com.zjjxl.panda.R;
import com.zjjxl.panda.beans.Bean_BaseCard;
import com.zjjxl.panda.beans.Bean_zjjCreateOrder;

import com.zjjxl.panda.beans.CardRentApply_Bean;
import com.zjjxl.panda.beans.PramDetal_Bean;
import com.zjjxl.panda.https.HttpManager;
import com.zjjxl.panda.utils.LUtils;
import com.zjjxl.panda.utils.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.HTTP;

public class ShuangYSaveMoneyActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = "ShuangYSaveMoneyActivity";
    private Button mSavem;
    private Button mkaika;
    private Button mPayquery;
    private Button mQancun;
    private Button mCreateoeder;


    private NfcAdapter mNfcAdapter;
    private PendingIntent mPendingIntent;
    private Parcelable mParcelableExtra;
    private TextView mTextv1;
    private ChargeVeryInfo mData;
    private String mOut_trade_no;
    private String mSubject;
    private OpenCardData mOpenCardData;
    private String STATE_OPEN = "01";
    private String mTrace_id;
    private String mUnit_code;
    private String mTerminal_no;
    private String mCity_code;
    private String mIssuer_code;
    private String mCredit_no;
    private String mMCardid;
    private Long mNowtime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shuang_y_save_money);

        mPayquery = findViewById(R.id.payquery);
        mQancun = findViewById(R.id.quancun);
        mSavem = findViewById(R.id.savem);
        mkaika = findViewById(R.id.kaika);
        mCreateoeder = findViewById(R.id.createoeder);
        mTextv1 = findViewById(R.id.textv1);

        mPayquery.setOnClickListener(this);
        mQancun.setOnClickListener(this);
        mkaika.setOnClickListener(this);
        mSavem.setOnClickListener(this);
        mCreateoeder.setOnClickListener(this);
        initEventAndData();
    }

    /**
     * 暂停Activity，界面获取焦点，按钮可以点击
     */
    @Override
    public void onPause() {
        super.onPause();
        //恢复默认状态
        if (mNfcAdapter != null)
            mNfcAdapter.disableForegroundDispatch(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //设置处理优于所有其他NFC的处理
        if (mNfcAdapter != null)
            mNfcAdapter.enableForegroundDispatch(this,
                    mPendingIntent, CardManager.FILTERS, CardManager.TECHLISTS);
    }


    protected void initEventAndData() {
        //此处adapter需要重新获取，否则无法获取message
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        //一旦截获NFC消息，就会通过PendingIntent调用窗口
        mPendingIntent = PendingIntent.getActivity(this,
                0, new Intent(this,
                        getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        mParcelableExtra = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        if (mParcelableExtra != null) {
            BalanceData data = CardManager.getInfoData(mParcelableExtra);
            if (data != null) {
                mMCardid = data.card_id;
                LUtils.d(TAG, "==========卡号是====" + mMCardid);
                mTextv1.setText("卡号是：==" + mMCardid);
            }
            //{"status":true,"msg":"成功","data":{"trace_id":"20200812230824343",
            // "biz_type":"SERV_CARD_RENT_APP","code":"2001","message":"卡片随机数非法。"}}
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.createoeder:
                createOrder();
                break;
            case R.id.savem:
                tozhifuSave();
                break;
            case R.id.kaika:
                //                toQuery();
                ToastUtils.showToast(this, "正在开卡");
                mNowtime = System.currentTimeMillis();
                requestOpenCardDialog();
                break;
            case R.id.quancun:
                quancun();
                break;
            case R.id.payquery:
                //平台参数同步
                paramDetail();
                break;
        }

    }


    private void paramDetail() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm:ss");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        String currdate = simpleDateFormat.format(date) + "T" + simpleDateFormat2.format(date);
        Call<PramDetal_Bean> city_card_trade =
                HttpManager.getInstance().getHttpClient3().paramDetail("CITY_CARD_TRADE", currdate);
        city_card_trade.enqueue(new Callback<PramDetal_Bean>() {
            @Override
            public void onResponse(Call<PramDetal_Bean> call, Response<PramDetal_Bean> response) {

                if (response.body() != null) {
                    PramDetal_Bean.DataBeanX.DataBean data = response.body().getData().getData();
                    List<PramDetal_Bean.DataBeanX.DataBean.ParamListBean> param_list = data.getParam_list();
                    for (int i = 0; i < param_list.size(); i++) {
                        PramDetal_Bean.DataBeanX.DataBean.ParamListBean paramListBean = param_list.get(i);
                        LUtils.d(TAG, "paramListBean.getCity_code()==" + paramListBean.getCity_code());
                        if (paramListBean.getIssuer_name().equals("吉林双阳")) {
                            mUnit_code = paramListBean.getSettle_unit(); // 单元编号
                            mTerminal_no = paramListBean.getVterminal_no();// 虚拟终端号
                            mCity_code = paramListBean.getCity_code();// 城市编号
                            mIssuer_code = paramListBean.getIssuer_code();//发卡机构标识
                            mCredit_no = paramListBean.getCredit_no(); //卡应用序列号
                            LUtils.d(TAG, "mUnit_code==" + mUnit_code);
                            LUtils.d(TAG, "mTerminal_no==" + mTerminal_no);
                            LUtils.d(TAG, "mCity_code==" + mCity_code);
                            LUtils.d(TAG, "mIssuer_code==" + mIssuer_code);
                            LUtils.d(TAG, "mCredit_no==" + mCredit_no);
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<PramDetal_Bean> call, Throwable t) {

            }
        });
    }


    private void createOrder() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm:ss");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        String currdate = simpleDateFormat.format(date) + "T" + simpleDateFormat2.format(date);
        LUtils.d(TAG, "Date获取当前日期时间  m " + currdate);
        Call<Bean_zjjCreateOrder> bean_baseCardCall = HttpManager.getInstance().getHttpClient3().create(
                "15689123357",
                "",
                "ALIPAY_SDK",
                currdate,
                0.01,
                "",
                "订单标题",
                "",
                "",
                "",
                "",
                "",
                ""
        );
        bean_baseCardCall.enqueue(new Callback<Bean_zjjCreateOrder>() {
            @Override
            public void onResponse(Call<Bean_zjjCreateOrder> call, Response<Bean_zjjCreateOrder> response) {
                LUtils.d(TAG, "Dresponse");
                if (response.body() != null) {
                    LUtils.d(TAG, "response.body()==" + response.body().getData().toString());
                    Bean_zjjCreateOrder.DataBeanX data =
                            response.body().getData();
                    mOut_trade_no = data.getData().getOut_trade_no();
                    mSubject = data.getData().getSubject();
                    mTrace_id = data.getTrace_id();
                }
            }

            @Override
            public void onFailure(Call<Bean_zjjCreateOrder> call, Throwable t) {
                LUtils.d(TAG, "t" + t);
            }
        });
    }

    private void quancun() {
        if (mParcelableExtra == null) {
            return;
        }
        mData = CardManager.charge(mParcelableExtra, 0.01, false, null);
        if (mData != null) {
            //校验是否能圈存
            //后台需要校验的话在此进行校验 否则就走后面一步
            if (mData.isOpen.equals(STATE_OPEN)) {

            } else {
                //提示开卡
                ToastUtils.showToast(this, "请先开卡");
            }

        } else {
            ToastUtils.showToast(this, "不支持的类型");
            LUtils.d(TAG, "   不支持的类型" + mData);
        }

    }

    //开卡申请
    private void OpencardApply() {
        LUtils.d(TAG, " mOpenCardData.domNo" + mOpenCardData.domNo);

        Call<CardRentApply_Bean> CardRentApply_BeanCall = HttpManager.getInstance().getHttpClient3()
                .cardRentApply(
                        mUnit_code, mTerminal_no,
                        mCity_code, mIssuer_code,
                        "接入商业务单号", "0" + mMCardid, mOpenCardData.cardType,
                        new SimpleDateFormat("yyyy-MM-dd").format(new Date(mNowtime)),
                        "2040-12-31", mOpenCardData.domNo, "00",
                        0.00, "1", "1500");
        CardRentApply_BeanCall.enqueue(new Callback<CardRentApply_Bean>() {
            @Override
            public void onResponse(Call<CardRentApply_Bean> call, Response<CardRentApply_Bean> response) {
                if (response.body() != null) {
                    try {
                        String mode_command = response.body().getData().getData().getMode_command();
                        int account_no = response.body().getData().getData().getAccount_no();
                        String terminal_seq = response.body().getData().getData().getTerminal_seq();
                        String mode_version = response.body().getData().getData().getMode_version();
                        String server_time = response.body().getData().getData().getServer_time();
                        if (mode_command != null) {
                            boolean success = CardManager.commitRequestOpenCard(mParcelableExtra, mode_command);
                            if (success) {
                                OpencardCommit(account_no, terminal_seq, server_time, mode_version, "0");
                                LUtils.d(TAG, "------------success--------" + success);
                                ToastUtils.showToast(ShuangYSaveMoneyActivity.this, "开卡成功");
                            } else {
                                OpencardCommit(account_no, terminal_seq, server_time, mode_version, "1");
                                LUtils.d(TAG, "------------success--------" + success);
                                ToastUtils.showToast(ShuangYSaveMoneyActivity.this, "开卡失败");
                            }

                        }
                    }catch (Exception e){
                        ToastUtils.showToast(ShuangYSaveMoneyActivity.this, response.body().getData().getMessage());

                    }finally {
                        ToastUtils.showToast(ShuangYSaveMoneyActivity.this, response.body().getData().getMessage());
                    }


                }
            }

            @Override
            public void onFailure(Call<CardRentApply_Bean> call, Throwable t) {

            }
        });


    }

    //开卡提交
    private void OpencardCommit(int accountNo, String terminalSeq, String server_time, String mode_version, String status) {
        Call<Bean_BaseCard> bean_baseCardCall = HttpManager.getInstance().getHttpClient3().cardRentConfirm(
                mUnit_code, mTerminal_no, mCity_code, mIssuer_code, mOut_trade_no, "", "0" + mMCardid,
                mOpenCardData.cardType, accountNo, server_time, terminalSeq, mode_version, status
        );
        bean_baseCardCall.enqueue(new Callback<Bean_BaseCard>() {
            @Override
            public void onResponse(Call<Bean_BaseCard> call, Response<Bean_BaseCard> response) {


            }

            @Override
            public void onFailure(Call<Bean_BaseCard> call, Throwable t) {

            }
        });

    }

    private void requestOpenCardDialog() {
        //        获取开卡请求的基本参数
        mOpenCardData = CardManager.requestOpenCard(mParcelableExtra);
        Log.e(TAG, " mOpenCardData =" + mOpenCardData.issuerCode);
        Log.e(TAG, " mOpenCardData " + mOpenCardData.cardId);
        Log.e(TAG, " mOpenCardData " + mOpenCardData.cardType);
        Log.e(TAG, " mOpenCardData " + mOpenCardData.algorithmType);
        OpencardApply();

    }

    //    private void toQuery() {
    //        //在您app需要查询订单的地方调用TSM查询订单的方法
    //        HashMap params = new HashMap();
    //        params.put("out_trade_no", mOut_trade_no);//订单号
    //        TSMPay.reqOrderStatus(params, new BaseSubscriber<PayRequestRec>() {
    //            @Override
    //            public void onNext(PayRequestRec payRequestRec) {
    //                //服务器返回结果
    //                super.onNext(payRequestRec);
    //                Log.e(TAG, "toQuery   支付服务器返回结果  payAli: " + payRequestRec);
    //            }
    //        });
    //    }

    BaseCallback mlistener = new BaseCallback() {
        @Override
        public void callBack(BaseResp result) {
            //result 支付结果回调
            Log.e("tag", " mlistener  支付回调结果  payAli: " + result.trace_id);
            Log.e("tag", " mlistener  支付回调结果  payAli: " + result.message);
            Log.e("tag", " mlistener  支付回调结果  payAli: " + result.biz_content);
            Log.e("tag", " mlistener  支付回调结果  payAli: " + result.server_time);
            Log.e("tag", " mlistener  支付回调结果  payAli: " + result.sign);
            Log.e("tag", " mlistener  支付回调结果  payAli: " + result.code);
            Log.e("tag", " mlistener  支付回调结果  payAli: " + result.biz_type);
            Log.e("tag", " mlistener  支付回调结果  payAli: " + result.data);
            Log.e("tag", " mlistener  支付回调结果  payAli: " + result.token);
            Log.e("tag", " mlistener  支付回调结果  payAli: " + result.sign_type);

        }
    };

    // 支付宝支付
    private void tozhifuSave() {
        HashMap params = new HashMap();
        params.put("out_trade_no", mOut_trade_no);//必传字段 订单号
        params.put("trade_amount", "0.01");//必传字段 交易金额（单位元）
        params.put("subject", mSubject);//必传字段   交易说明（subject不能超过16）
        params.put("user_id", "15689123357");//非//非必传字段(根据业务要求有的话就传入；否则不需要传) 接入商后台结果通知地址必传字段
        //        params.put("notify_url", "");
        TSMPay.payRequest(this, params, mlistener,
                new Alipay.AlipayResultCallBack() {
                    @Override
                    public void onSuccess() {
                        LUtils.d(TAG, "AlipayResultCallBack   onSuccess    ");
                    }

                    @Override
                    public void onDealing() {
                        LUtils.d(TAG, "AlipayResultCallBack   onDealing    ");
                    }

                    @Override
                    public void onError(int error_code) {
                        LUtils.d(TAG, "AlipayResultCallBack   onError    ");
                    }

                    @Override
                    public void onCancel() {
                        LUtils.d(TAG, "AlipayResultCallBack   onCancel    ");
                    }
                });
    }
}
