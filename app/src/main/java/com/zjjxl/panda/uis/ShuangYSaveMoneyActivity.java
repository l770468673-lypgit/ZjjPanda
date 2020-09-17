package com.zjjxl.panda.uis;

import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.heyue.adpu_manager.module.BalanceData;
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
import com.zjjxl.panda.adapters.GridViewAdapter;
import com.zjjxl.panda.beans.Bean_BaseCard;
import com.zjjxl.panda.beans.Bean_zjjCreateOrder;
import com.zjjxl.panda.beans.CardCharge_bean;
import com.zjjxl.panda.beans.CardRentApply_Bean;
import com.zjjxl.panda.beans.Gson_AlipayRes;
import com.zjjxl.panda.beans.PramDetal_Bean;
import com.zjjxl.panda.https.HttpManager;
import com.zjjxl.panda.utils.Contants;
import com.zjjxl.panda.utils.LUtils;
import com.zjjxl.panda.utils.ShareUtil;
import com.zjjxl.panda.utils.StatusBarUtil;
import com.zjjxl.panda.utils.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ShuangYSaveMoneyActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private String TAG = "ShuangYSaveMoneyActivity";
    private Button mSavem;
    private Button mkaika;
    private Button mPayquery;
    private Button mRefund;


    private Button mCreateoeder;
    private Button mGetMac2;
    private Button mPayandquery;
    private Button mSavesuccess_nextstep;


    private double money = 0;

    private NfcAdapter mNfcAdapter;
    private PendingIntent mPendingIntent;
    private Parcelable mParcelableExtra;
    private ChargeVeryInfo mData;
    private String mOut_trade_no;
    //    sttsm33c4669e559643098bda148e965c80f6
//sttsmc25456792703445f862a2396d1f17127
    //    private String mOut_trade_no1="sttsmc25456792703445f862a2396d1f17127";
//    private String mOut_trade_no1 = "sttsm33c4669e559643098bda148e965c80f6";
    private String mSubject;
    private OpenCardData mOpenCardData;
    private String STATE_OPEN = "01";
//    private String mTrace_id;

    private String mUnit_code;
    private String mTerminal_no;
    private String mCity_code;
    private String mIssuer_code;

    private String mMCardid;
    private String cardNowbalanceMoney;
    private Long mNowtime;
    private RelativeLayout mZjjrelay_recharge;

    private GridViewAdapter mGridViewAdapter;
    private TextView mLly_writecmomey;
    private int state;
    private RelativeLayout mRely_nfc_bottomlly;
    private GridView mMoney_listview;
    private RelativeLayout mNfc_paymoney_bottomlly;
    private Button mNfc_btnsaveothermoney;
    private Button mPaymoney_bottomlly3;
    private EditText mNfc_saveothermoney;
    private TextView mLly_writecardnum;
    private TextView mSavemoney_back;
    private TextView mSuccess_cardnum;
    private TextView mSuccess_savemoney;
    private TextView mSuccess_countmoney;
    private LinearLayout mLly_savemoney_resp;
    private double[] moneydate = {10, 20, 30, 50, 100, 200};
    private List<Map<String, Object>> mData_list = new ArrayList<>();
    private String mTrade_no;
    private String mode_type = "00";
    private String mode_version = "1500";
    private String channel_type = "ALIPAY_SDK";

    //    02H：圈存
//03H：圈提
//06H：消费
//07H：修改透支限额；
//            09H：复合应用消费。
    private String mPay_time;
    private LinearLayout mLly_alipaysuccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setStatusBarLightMode(getWindow());
        StatusBarUtil.setDrawable(this, R.drawable.fragment_bennefit_boxframe);
        setContentView(R.layout.activity_shuang_y_save_money);
        //平台参数同步
        paramDetail();
        state = 0;
        //========================
        mZjjrelay_recharge = findViewById(R.id.zjjrelay_recharge);
        mZjjrelay_recharge.setVisibility(View.VISIBLE);
        //========================
        mRely_nfc_bottomlly = findViewById(R.id.zjjrely_nfc_bottomlly);
        mRely_nfc_bottomlly.setVisibility(View.GONE);
        mSavemoney_back = findViewById(R.id.savemoney_back);
        mMoney_listview = findViewById(R.id.zjjmoney_listview);
        mLly_alipaysuccess = findViewById(R.id.lly_alipaysuccess);
        mNfc_paymoney_bottomlly = findViewById(R.id.zjjnfc_paymoney_bottomlly);
        mNfc_btnsaveothermoney = findViewById(R.id.zjjnfc_btnsaveothermoney);
        mPaymoney_bottomlly3 = findViewById(R.id.zjjpaymoney_bottomlly3);
        mNfc_saveothermoney = findViewById(R.id.zjjnfc_saveothermoney);
        mLly_writecardnum = findViewById(R.id.zjjlly_writecardnum);
        mLly_writecmomey = findViewById(R.id.zjjlly_writecmomey);
        mNfc_btnsaveothermoney.setOnClickListener(this);

        mGridViewAdapter = new GridViewAdapter(this);
        mMoney_listview.setAdapter(mGridViewAdapter);
        mGridViewAdapter.setData_list(getdate());
        mMoney_listview.setOnItemClickListener(this);
        //========================
        mRefund = findViewById(R.id.refund);
        mPayquery = findViewById(R.id.payquery);

        mSavem = findViewById(R.id.savem);
        mkaika = findViewById(R.id.kaika);
        mCreateoeder = findViewById(R.id.createoeder);
        mGetMac2 = findViewById(R.id.getMac2);
        mPayandquery = findViewById(R.id.payandquery);

        mLly_savemoney_resp = findViewById(R.id.lly_savemoney_resp);
        mSuccess_cardnum = findViewById(R.id.success_cardnum);
        mSuccess_savemoney = findViewById(R.id.success_savemoney);
        mSuccess_countmoney = findViewById(R.id.success_countmoney);
        mSavesuccess_nextstep = findViewById(R.id.savesuccess_nextstep);
        //---------------
        mSavemoney_back.setOnClickListener(this);
        mRefund.setOnClickListener(this);
        mGetMac2.setOnClickListener(this);
        mPayandquery.setOnClickListener(this);
        mPayquery.setOnClickListener(this);
        mkaika.setOnClickListener(this);
        mSavem.setOnClickListener(this);
        mSavesuccess_nextstep.setOnClickListener(this);
        mCreateoeder.setOnClickListener(this);
        initEventAndData();
    }

    private List<Map<String, Object>> getdate() {
        for (int i = 0; i < moneydate.length; i++) {
            Map<String, Object> maps = new HashMap<>();
            maps.put("truemoney", moneydate[i]);
            mData_list.add(maps);
        }
        return mData_list;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //设置处理优于所有其他NFC的处理
        if (mNfcAdapter != null) {
            mNfcAdapter.enableForegroundDispatch(this,
                    mPendingIntent, CardManager.FILTERS, CardManager.TECHLISTS);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zjjnfc_btnsaveothermoney:
                mNfc_saveothermoney.setVisibility(View.VISIBLE);
                mNfc_paymoney_bottomlly.setVisibility(View.VISIBLE);
                mPaymoney_bottomlly3.setVisibility(View.VISIBLE);
                break;
            case R.id.savemoney_back:
            case R.id.savesuccess_nextstep:
                finish();

                break;
            case R.id.createoeder:
//                createOrder(money);
                break;
            case R.id.savem:
//                tozhifuSave();
                break;
            case R.id.kaika:
                //                toQuery();
//                ToastUtils.showToast(this, "正在开卡");
//                mNowtime = System.currentTimeMillis();
//                requestOpenCardDialog();
                break;

            case R.id.payandquery:
//                toQuery();
                break;
            case R.id.getMac2:
//                saveMoneyCommit();
                break;
            case R.id.payquery:
                //平台参数同步
//                paramDetail();
                break;
            case R.id.refund:
                //退款
                refund();
                break;
        }
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

    protected void initEventAndData() {
        //此处adapter需要重新获取，否则无法获取message
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (mNfcAdapter != null) {
            //一旦截获NFC消息，就会通过PendingIntent调用窗口
            mPendingIntent = PendingIntent.getActivity(this,
                    0, new Intent(this,
                            getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        mParcelableExtra = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        if (mParcelableExtra != null) {
            switch (state) {
                case 0:
                    BalanceData data = CardManager.getInfoData(mParcelableExtra);
                    if (data != null) {
                        mMCardid = data.card_id;
                        if (mMCardid != null) {
                            cardNowbalanceMoney = data.balanceMoney;
                            LUtils.d(TAG, "==========卡号是====" + mMCardid);
                            LUtils.d(TAG, "==========qian====" + cardNowbalanceMoney);
                            mLly_writecmomey.setText(cardNowbalanceMoney + "元");
                            mLly_writecardnum.setText(mMCardid);
                            mZjjrelay_recharge.setVisibility(View.GONE);
                            mRely_nfc_bottomlly.setVisibility(View.VISIBLE);
                        } else {
                            ToastUtils.showToast(this, "检查卡片是否损坏");
                        }
                    }
                    break;
                case 1:
                    // 为圈存做请求


                        saveMoneyCommit();

                    break;
            }
        }
    }

    // 更新服务端是否写卡成功
    private void updateResult(CardCharge_bean.DataBeanX mdate_savemoney, double m, String flag) {
        String writestatus;
        final boolean writeSuccess;
        if (flag != null) {
            writestatus = "0";
            writeSuccess = true;
        } else {
            writestatus = "1";
            flag = "FFFFFFFF";//写卡失败默认提交ffffff
            writeSuccess = false;
        }
        String terminal_seq = mdate_savemoney.getData().getTerminal_seq();
        String cardno = mdate_savemoney.getData().getCard_no();
        String time = mdate_savemoney.getData().getTrade_time();
        String cardseq = mdate_savemoney.getData().getCard_seq();
        Call<Bean_BaseCard> bean_baseCardCall = HttpManager.getInstance().
                getHttpClient3().cardChargeConfirm(
                mUnit_code, mTerminal_no, mCity_code, mIssuer_code, mOut_trade_no,
                terminal_seq, cardno, mData.cardType,
                cardseq, time, m, money,
                flag, mode_version, writestatus);
        bean_baseCardCall.enqueue(new Callback<Bean_BaseCard>() {
            @Override
            public void onResponse(Call<Bean_BaseCard> call, Response<Bean_BaseCard> response) {
                state = 0;
                if (writeSuccess) {
                    mLly_alipaysuccess.setVisibility(View.GONE);
                    mLly_savemoney_resp.setVisibility(View.VISIBLE);
                    mRely_nfc_bottomlly.setVisibility(View.GONE);
                    mSuccess_cardnum.setText(cardno);
                    mSuccess_savemoney.setText(money + "");
                    mSuccess_countmoney.setText(m + "");
                } else {
                    ToastUtils.showToast(ShuangYSaveMoneyActivity.this, "重新放卡");
                }
            }

            @Override
            public void onFailure(Call<Bean_BaseCard> call, Throwable t) {
            }
        });

    }

    // 退款
    private void refund() {

        //sttsm1dda73ca4cc94279b7a14b12c24bbd1b
        //20200916188256721463719214241243
        Call<Bean_BaseCard> refund = HttpManager.getInstance().getHttpClient3()
                .payOrderRefund(
//                        "20200916586121979652851142312134",
//                        "sttsm600d372e7fef4a8389b324e101ebf7be",
//                        10.0,
                        mTrade_no,
                        mOut_trade_no,
                        money,
                        "", "", "REFUND");
        refund.enqueue(new Callback<Bean_BaseCard>() {
            @Override
            public void onResponse(Call<Bean_BaseCard> call, Response<Bean_BaseCard> response) {
                if (response.body() != null) {
                    boolean status = response.body().isStatus();
                    if (status) {
                        state = 0;
                        ToastUtils.showToast(ShuangYSaveMoneyActivity.this, response.body().getData().getMessage());
                    } else {
                        ToastUtils.showToast(ShuangYSaveMoneyActivity.this, response.body().getData().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<Bean_BaseCard> call, Throwable t) {
                ToastUtils.showToast(ShuangYSaveMoneyActivity.this, "退款失败");
            }
        });
    }

    // 初始化支付平台参数
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
                        if (paramListBean.getIssuer_name().contains("吉林双阳")) {
                            mUnit_code = paramListBean.getSettle_unit(); // 单元编号
                            mTerminal_no = paramListBean.getVterminal_no();// 虚拟终端号
                            mCity_code = paramListBean.getCity_code();// 城市编号
                            mIssuer_code = paramListBean.getIssuer_code();//发卡机构标识

                            LUtils.d(TAG, "mUnit_code==" + mUnit_code);
                            LUtils.d(TAG, "mTerminal_no==" + mTerminal_no);
                            LUtils.d(TAG, "mCity_code==" + mCity_code);
                            LUtils.d(TAG, "mIssuer_code==" + mIssuer_code);

                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<PramDetal_Bean> call, Throwable t) {

            }
        });
    }

    private void createOrder(double moneys) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm:ss");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        String currdate = simpleDateFormat.format(date) + "T" + simpleDateFormat2.format(date);
        LUtils.d(TAG, "Date获取当前日期时间  m " + currdate);
        Call<Bean_zjjCreateOrder> bean_baseCardCall = HttpManager.getInstance().getHttpClient3().create(
                ShareUtil.getString(Contants.LOGIN_USER_PHONE),
                "",
                channel_type,
                currdate,
                moneys,
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
                if (response.body() != null) {
                    LUtils.d(TAG, "response.body()==" + response.body().getData().toString());
                    Bean_zjjCreateOrder.DataBeanX data =
                            response.body().getData();
                    mOut_trade_no = data.getData().getOut_trade_no();
                    mTrade_no = data.getData().getTrade_no();
                    mSubject = data.getData().getSubject();
//                    mTrace_id = data.getTrace_id();
                    if (moneys > 1) {
                        tozhifuSave();
                    }
                }
            }

            @Override
            public void onFailure(Call<Bean_zjjCreateOrder> call, Throwable t) {
                LUtils.d(TAG, "t" + t);
            }
        });
    }

    //充值申请
    private void saveMoneyCommit() {
        if (mParcelableExtra != null) {
            if (mData == null) {
                mData = CardManager.charge(mParcelableExtra, money, true, mTerminal_no);
            }
            Log.e(TAG, " 充值申请  money==" + money);
            Log.e(TAG, " 充值申请  mTerminal_no==" + mTerminal_no);
            if (mData != null) {
                if (mData.isOpen.equals(STATE_OPEN)) {
                    tosaveMoneyCommit();
                    LUtils.d(TAG, "卡片已激活");
                } else {
//                    提示开卡
                    LUtils.d(TAG, "卡片未激活");
                    ToastUtils.showToast(this, "卡片正在开卡，请务离开");
                    requestOpenCardDialog();
                }
            }
        }

    }

    //充值申请 圈存
    private void tosaveMoneyCommit() {
        Call<CardCharge_bean> bean_baseCardCall = HttpManager.getInstance().getHttpClient3().
                cardChargeApply(mUnit_code, mTerminal_no,
                        mCity_code, mIssuer_code,
                        mOut_trade_no, "0" + mMCardid, mData.cardType,
                        mData.cardIndex, mPay_time, Double.parseDouble(cardNowbalanceMoney), money,
                        mData.domNo, mData.mac1, String.valueOf(mData.algorithmType), mode_version);
        bean_baseCardCall.enqueue(new Callback<CardCharge_bean>() {
            @Override
            public void onResponse(Call<CardCharge_bean> call, Response<CardCharge_bean> response) {
                if (response.body() != null) {
                    CardCharge_bean.DataBeanX.DataBean data1 = response.body().getData().getData();
                    String card_mac = data1.getCard_mac();
                    //写入mac2
                    if (card_mac != null) {
                        LUtils.d(TAG, "card_mac====" + card_mac);
                        try {
                            String mtag = null;
                            String REGEX = "[^(0-9)]";
                            String ticket = Pattern.compile(REGEX).matcher(mPay_time)
                                    .replaceAll("").trim();
                            LUtils.d(TAG, "ticket===========" + ticket);
                            mtag = CardManager.getTag(mParcelableExtra, card_mac, ticket);
                            LUtils.d(TAG, "tag====" + mtag);

                            if (mtag != null) {
                                updateResult(response.body().getData(), Double.parseDouble(cardNowbalanceMoney) + money, mtag);
                            } else {
                                refund();
                                updateResult(response.body().getData(), Double.parseDouble(cardNowbalanceMoney), mtag);
                            }
                        } catch (Exception q) {
                            LUtils.d(TAG, "Exception)====" + q);
                        }
                    } else {
                        ToastUtils.showToast(ShuangYSaveMoneyActivity.this,
                                response.body().getData().getMessage().toString());
                    }
                }
            }


            @Override
            public void onFailure(Call<CardCharge_bean> call, Throwable t) {

            }
        });
    }

    //开卡申请
    private void OpencardApply() {

        LUtils.d(TAG, " 开卡申请 algorithmType" + mOpenCardData.algorithmType);
        Log.e(TAG, " 开卡申请  mOut_trade_no==" + mOut_trade_no);
        Call<CardRentApply_Bean> CardRentApply_BeanCall = HttpManager.getInstance().getHttpClient3()
                .cardRentApply(
                        mUnit_code, mTerminal_no,
                        mCity_code, mIssuer_code,
                        mOut_trade_no, "0" + mMCardid, mOpenCardData.cardType,
                        new SimpleDateFormat("yyyy-MM-dd").format(new Date(mNowtime)),
                        "2040-12-31", mOpenCardData.domNo, mode_type,
                        0.00, String.valueOf(mData.algorithmType), mode_version);
        CardRentApply_BeanCall.enqueue(new Callback<CardRentApply_Bean>() {
            @Override
            public void onResponse(Call<CardRentApply_Bean> call, Response<CardRentApply_Bean> response) {
                if (response.body().isStatus()) {
                    try {
                        String mode_command = response.body().getData().getData().getMode_command();
                        int account_no = response.body().getData().getData().getAccount_no();
                        String terminal_seq = response.body().getData().getData().getTerminal_seq();
                        String mode_version = response.body().getData().getData().getMode_version();
                        String server_time = response.body().getData().getData().getServer_time();
                        LUtils.d(TAG, "------------ 开卡申请mode_command--------" + mode_command);
                        if (mode_command != null) {
                            boolean success = CardManager.commitRequestOpenCard(mParcelableExtra, mode_command);
                            if (success) {
                                OpencardCommit(account_no, terminal_seq, server_time, mode_version, "0");
                                LUtils.d(TAG, "------------开卡 success--------" + success);
                                ToastUtils.showToast(ShuangYSaveMoneyActivity.this, "开卡成功");
                            } else {
                                OpencardCommit(account_no, terminal_seq, server_time, mode_version, "1");
                                LUtils.d(TAG, "------------false--开卡失败------" + success);
                                ToastUtils.showToast(ShuangYSaveMoneyActivity.this, "开卡失败");
                            }

                        }
                    } catch (Exception e) {
                        ToastUtils.showToast(ShuangYSaveMoneyActivity.this, response.body().getData().getMessage());
                    } finally {
                        ToastUtils.showToast(ShuangYSaveMoneyActivity.this, response.body().getData().getMessage());
                    }

                } else {
                    refund();
                }
            }

            @Override
            public void onFailure(Call<CardRentApply_Bean> call, Throwable t) {

            }
        });
    }

    //开卡提交
    private void OpencardCommit(int accountNo, String terminalSeq, String server_time, String mode_version, String status) {
        LUtils.d(TAG, "---------OpencardCommit------");
        Call<Bean_BaseCard> bean_baseCardCall = HttpManager.getInstance().getHttpClient3().
                cardRentConfirm(
                        mUnit_code, mTerminal_no, mCity_code, mIssuer_code,
                        mTrade_no, mOut_trade_no, "0" + mMCardid,
                        mOpenCardData.cardType, accountNo, server_time, terminalSeq, mode_version, status

                );
        bean_baseCardCall.enqueue(new Callback<Bean_BaseCard>() {
            @Override
            public void onResponse(Call<Bean_BaseCard> call, Response<Bean_BaseCard> response) {
                if (response.body().isStatus()) {
                    tosaveMoneyCommit();
//                    state = 1;
                    LUtils.d(TAG, response.body().getData().getMessage());
                }
            }

            @Override
            public void onFailure(Call<Bean_BaseCard> call, Throwable t) {

            }
        });

    }

    private void requestOpenCardDialog() {
        //        获取开卡请求的基本参数
        if (mParcelableExtra != null) {
            if (mOpenCardData == null) {
                mOpenCardData = CardManager.requestOpenCard(mParcelableExtra);
            } else if (mOpenCardData != null) {
                Log.e(TAG, " 获取开卡请求的基本参数  mOpenCardData =" + mOpenCardData.issuerCode);
                Log.e(TAG, " mOpenCardData " + mOpenCardData.cardId);
                Log.e(TAG, " mOpenCardData " + mOpenCardData.cardType);
                Log.e(TAG, " mOpenCardData " + mOpenCardData.algorithmType);
                mNowtime = System.currentTimeMillis();
                OpencardApply();
            } else {
                ToastUtils.showToast(this, "请将卡片贴在读卡区域");
            }

        }


    }

    private void toQuery() {
        Log.e(TAG, "   toQuery  mOut_trade_no== " + mOut_trade_no);
        //在您app需要查询订单的地方调用TSM查询订单的方法
        HashMap params = new HashMap();
        params.put("out_trade_no", mOut_trade_no);//订单号
        TSMPay.reqOrderStatus(params, new BaseSubscriber<PayRequestRec>() {
            @Override
            public void onNext(PayRequestRec payRequestRec) {
                //服务器返回结果
                super.onNext(payRequestRec);
                Log.e(TAG, "   支付服务器返回结果  payAli: " + payRequestRec.trace_id);
                Log.e(TAG, "   支付服务器返回结果  payAli: " + payRequestRec.message);
                Log.e(TAG, "   支付服务器返回结果  payAli: " + payRequestRec.biz_content);
                Log.e(TAG, "   支付服务器返回结果  payAli: " + payRequestRec.server_time);
                Log.e(TAG, "   支付服务器返回结果  payAli: " + payRequestRec.sign);
                Log.e(TAG, "   支付服务器返回结果  payAli: " + payRequestRec.code);
                Log.e(TAG, "   支付服务器返回结果  payAli: " + payRequestRec.biz_type);
                Log.e(TAG, "   支付服务器返回结果  data payAli: " + payRequestRec.data);
                Log.e(TAG, "   支付服务器返回结果  payAli: " + payRequestRec.token);
                Log.e(TAG, "   支付服务器返回结果  payAli: " + payRequestRec.sign_type);
                Gson_AlipayRes gson_alipayRes = new Gson().fromJson(payRequestRec.data, Gson_AlipayRes.class);
                mPay_time = gson_alipayRes.getPay_time();
                state = 1;
                mRely_nfc_bottomlly.setVisibility(View.GONE);
                mLly_alipaysuccess.setVisibility(View.VISIBLE);


            }
            //////////
        });
    }

    BaseCallback mlistener = new BaseCallback() {
        @Override
        public void callBack(BaseResp result) {
            //result 支付结果回调
            Log.e(TAG, " mlistener  支付回调结果  payAli: " + result.trace_id);
            Log.e(TAG, " mlistener  支付回调结果  payAli: " + result.message);
            Log.e(TAG, " mlistener  支付回调结果  payAli: " + result.biz_content);
            Log.e(TAG, " mlistener  支付回调结果  payAli: " + result.server_time);
            Log.e(TAG, " mlistener  支付回调结果  payAli: " + result.sign);
            Log.e(TAG, " mlistener  支付回调结果  payAli: " + result.code);
            Log.e(TAG, " mlistener  支付回调结果  payAli: " + result.biz_type);
            Log.e(TAG, " mlistener  支付回调结果  payAli: " + result.data);
            Log.e(TAG, " mlistener  支付回调结果  payAli: " + result.token);
            Log.e(TAG, " mlistener  支付回调结果  payAli: " + result.sign_type);

        }
    };

    // 支付宝支付
    private void tozhifuSave() {

        Log.e(TAG, "   tozhifuSave  mOut_trade_no== " + mOut_trade_no);
        HashMap params = new HashMap();
        params.put("out_trade_no", mOut_trade_no);//必传字段 订单号
        params.put("trade_amount", money);//必传字段 交易金额（单位元）
        params.put("subject", mSubject);//必传字段   交易说明（subject不能超过16）
        params.put("user_id", ShareUtil.getString(Contants.LOGIN_USER_PHONE));//非//非必传字段(根据业务要求有的话就传入；否则不需要传) 接入商后台结果通知地址必传字段
        //        params.put("notify_url", "");
        TSMPay.payRequest(this, params, mlistener,
                new Alipay.AlipayResultCallBack() {
                    @Override
                    public void onSuccess() {
                        LUtils.d(TAG, "AlipayResultCallBack   onSuccess    ");
                        toQuery();
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mGridViewAdapter.setSeclection(position);
        mGridViewAdapter.notifyDataSetChanged();
//        mRely_nfc_bottomlly.setVisibility(View.GONE);
        double truemoney = (Double) mData_list.get(position).get("truemoney");
        money = truemoney;
//        toAliPay(Yuan2fen.changeY2F(mTruemoney));
        createOrder(money);

    }
}
