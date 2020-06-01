package com.zjjxl.panda.uis;import android.app.PendingIntent;import android.content.Intent;import android.content.IntentFilter;import android.nfc.NfcAdapter;import android.nfc.tech.IsoDep;import android.nfc.tech.NfcF;import android.nfc.tech.NfcV;import android.os.Bundle;import android.view.View;import android.widget.AdapterView;import android.widget.Button;import android.widget.EditText;import android.widget.GridView;import android.widget.LinearLayout;import android.widget.RelativeLayout;import android.widget.TextView;import com.crb.cttic.pay.card.bean.CardInfoGather;import com.crb.cttic.pay.device.SmartCardNfcTag;import com.crb.cttic.pay.model.account.pay.CrbPayData;import com.crb.cttic.pay.model.account.pay.ExcepOrder;import com.crb.cttic.pay.model.account.pay.ProductModel;import com.crb.cttic.pay.model.account.pay.RechargeOnLineOrderData;import com.crb.cttic.pay.model.account.pay.RefundApplyData;import com.crb.cttic.pay.mvp.view.activity.model.entity.CardManageData;import com.crb.cttic.pay.pay.PayActivity;import com.crb.cttic.pay.utils.CircleDepositCardUtils;import com.crb.cttic.pay.utils.CtticAppSwithAppUtils;import com.crb.cttic.pay.utils.PlaceOrderUtils;import com.crb.cttic.pay.utils.ReadCardUtils;import com.zjjxl.panda.R;import com.zjjxl.panda.adapters.GridViewAdapter;import com.zjjxl.panda.apps.XLBaseActivity;import com.zjjxl.panda.interfaces.ParamConst;import com.zjjxl.panda.utils.Contants;import com.zjjxl.panda.utils.LUtils;import com.zjjxl.panda.utils.ShareUtil;import com.zjjxl.panda.utils.ToastUtils;import com.zjjxl.panda.utils.Yuan2fen;import java.math.BigDecimal;import java.util.ArrayList;import java.util.HashMap;import java.util.List;import java.util.Map;public class NFCPandaActivity extends XLBaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {    private static String TAG = "NFCActivity";    public static String[][] TECHLISTS;    public static IntentFilter[] FILTERS;    //订单号    private String orderId;    //    private String cardId;    private final String ALIPAY = "02";    static {        TECHLISTS = new String[][]{                {IsoDep.class.getName()},                {NfcV.class.getName()}, {NfcF.class.getName()},};        try {            FILTERS = new IntentFilter[]{                    new IntentFilter(                            NfcAdapter.ACTION_TECH_DISCOVERED, "*/*")};        } catch (IntentFilter.MalformedMimeTypeException e) {            e.printStackTrace();        }    }    private String[] moneydate = {"10", "20", "30", "50", "100", "200"};    //    private String[] moneydate2 = {"19.9", "29.85", "49.75", "99.5", "199"};    private PendingIntent pendingIntent;    private NfcAdapter nfcAdapter;    private int state;    private CardManageData CardMdata = null;    private CardManageData CardMdata2;    //    private boolean depositStatus;    private RelativeLayout mRelay_recharge;    private RelativeLayout mNfc_paymoney_bottomlly;    private LinearLayout mLly_showlayout;    private TextView mLly_writecardnum;    private Button mPaymoney_bottomlly3;    private LinearLayout mLly_writeview, mNfc_bottomlly;    private Button mQuancun;    private Button tuikuan;    private Button mNfc_btnsaveothermoney;    private EditText mNfc_saveothermoney;    private String mAppletNo;    private long mCardBalance;    private GridView mMoney_listview;    private List<Map<String, String>> mData_list = new ArrayList<>();    private GridViewAdapter mGridViewAdapter;    private TextView mLly_writecmomey;    private int mTruemoney;    private TextView mNfc_toptitle;    private RelativeLayout mRely_nfc_bottomlly;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_nfc);        state = 0;        //初始化NFC        initNfc();        initView();    }    private List<Map<String, String>> getdate() {        for (int i = 0; i < moneydate.length; i++) {            Map<String, String> maps = new HashMap<>();            maps.put("truemoney", moneydate[i]);            mData_list.add(maps);        }        return mData_list;    }    private void initView() {        mRelay_recharge = findViewById(R.id.relay_recharge);        mNfc_toptitle = findViewById(R.id.nfc_toptitle_back);        mLly_writeview = findViewById(R.id.lly_writeview);        mNfc_bottomlly = findViewById(R.id.nfc_bottomlly);        mRely_nfc_bottomlly = findViewById(R.id.rely_nfc_bottomlly);        mMoney_listview = findViewById(R.id.money_listview);        mPaymoney_bottomlly3 = findViewById(R.id.paymoney_bottomlly3);        mNfc_paymoney_bottomlly = findViewById(R.id.nfc_paymoney_bottomlly);        mNfc_btnsaveothermoney = findViewById(R.id.nfc_btnsaveothermoney);        mNfc_saveothermoney = findViewById(R.id.nfc_saveothermoney);        mLly_writecardnum = findViewById(R.id.lly_writecardnum);        tuikuan = findViewById(R.id.tuikuan);        mLly_writecmomey = findViewById(R.id.lly_writecmomey);        mNfc_btnsaveothermoney.setOnClickListener(this);        mQuancun = findViewById(R.id.quancun);        mQuancun.setOnClickListener(this);        mPaymoney_bottomlly3.setOnClickListener(this);        mNfc_toptitle.setOnClickListener(this);        tuikuan.setOnClickListener(this);        mMoney_listview.setOnItemClickListener(this);        mGridViewAdapter = new GridViewAdapter(this);        mMoney_listview.setAdapter(mGridViewAdapter);        mGridViewAdapter.setData_list(getdate());    }    @Override    protected void onNewIntent(Intent intent) {        super.onNewIntent(intent);        final SmartCardNfcTag ctticReader = SmartCardNfcTag.getInstance(intent);        switch (state) {            case 0:                //读卡                ReadCardUtils.getInstance().getReadCardInfo(NFCPandaActivity.this, "", ctticReader, new ReadCardUtils.ReadCardUtilsListener() {                    @Override                    public void readCardFail(int errCode, String errMes) {//                        ToastUtils.showToast(NFCPandaActivity.this, "读卡失败 errCode =" + errCode + "errMes=" + errMes);                        ;                    }                    @Override                    public void readCardSuccess(CardInfoGather cardInfoGather) {                        setResult(RESULT_OK, new Intent().putExtra(ParamConst.CARD_INFO_GATHER, cardInfoGather));                        LUtils.i(TAG, "读卡成功 卡信息" + cardInfoGather.toString());                        mLly_writeview.setVisibility(View.VISIBLE);                        mRelay_recharge.setVisibility(View.GONE);                        mAppletNo = cardInfoGather.getPublicBasicInfo().getAppletNo();                        mCardBalance = cardInfoGather.getCardBalance();                        LUtils.i(TAG, "mCardBalance===" + mCardBalance);                        mLly_writecardnum.setText(mAppletNo);                        ShareUtil.putString(Contants.CARD_REAL_MONEY, BigDecimal.valueOf(Long.valueOf((int) mCardBalance)).divide(new BigDecimal(100)).toString());                        mLly_writecmomey.setText(BigDecimal.valueOf(Long.valueOf((int) mCardBalance)).divide(new BigDecimal(100)).toString() + "元");                        LUtils.i(TAG, "mAppletNo===" + mAppletNo);                        //cardId = mAppletNo;                        mRely_nfc_bottomlly.setVisibility(View.VISIBLE);                    }                });                break;            case 1:                //圈存                //                if (depositStatus) {                //                    ToastUtils.showToast(NFCActivity.this,"您已经圈存过了");                //                    return;                //                }                CircleDepositCardUtils.getInstance().toCircleDeposiCard(                        NFCPandaActivity.this, ctticReader, CardMdata2,                        new CircleDepositCardUtils.CircleDepositCardListener() {                            @Override                            public void cardRechargeSuccess() {                                // LogUtil.e("圈存成功");                                ToastUtils.showToast(NFCPandaActivity.this, "圈存成功");                                LUtils.i(TAG, "data == 圈存成功" + CardMdata2);                                //depositStatus = true;                                orderId = null;                            }                            @Override                            public void cardRechargeFail() {                                // LogUtil.e("圈存失败");                                ToastUtils.showToast(NFCPandaActivity.this, "圈存失败");                                LUtils.e(TAG, "data == 圈存失败" + CardMdata2);                                LUtils.e(TAG, "data == ctticReader" + ctticReader);                                cardtuikuan();                            }                        });                break;        }    }    public void quancunMoney(String orderId) {        //圈存        CardMdata2 = null;        final CardManageData data = new CardManageData();        data.setActionType("0x00");        data.setCardNum(mAppletNo);//   mAppletNo        data.setOrderId(orderId);// orderId        data.setSpId("05212482FF");//        data.setPhoneNo("电话号码");        data.setPhoneNo("15689123357");        data.setBalance((int) mCardBalance);        data.setAmount(mTruemoney);        CardMdata2 = data;        state = 1;        LUtils.e(TAG, "圈存 Mdata " + CardMdata2.toString());    }    private void initNfc() {        pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,                getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);        nfcAdapter = NfcAdapter.getDefaultAdapter(this);    }    @Override    protected void onResume() {        super.onResume();        if (nfcAdapter != null) {            nfcAdapter.enableForegroundDispatch(this, pendingIntent,                    FILTERS, TECHLISTS);        }    }    @Override    protected void onPause() {        super.onPause();        if (nfcAdapter != null) {            nfcAdapter.disableForegroundDispatch(this);        }    }    @Override    protected void onActivityResult(int requestCode, int resultCode, Intent data) {        super.onActivityResult(requestCode, resultCode, data);        if (resultCode == PayActivity.PLACE_ORDER_SUCCESS) {            int payState = data.getIntExtra(PayActivity.PAY_STATE, -1);            if (payState == CtticAppSwithAppUtils.PAY_SUCCESS) {                CrbPayData payData = (CrbPayData) data.getSerializableExtra(PayActivity.PAY_RESULT_KET);                orderId = payData.getOrderId();                Integer amout = payData.getAmout();                String tradeTime = payData.getTradeTime();                //ShareStoreManager.getInstance(getApplicationContext()).getAppShareStore().putString("orderId", orderId);                ToastUtils.showToast(this, "支付成功,请再次贴卡充值");//                upLoadOrder(mAppletNo, orderId, amout, tradeTime);                quancunMoney(orderId);            } else {                orderId = null;                ToastUtils.showToast(this, "支付失败");            }        }        //        else if (resultCode == RESULT_OK && requestCode == ParamConst.READ_CARD_INFO_CODE) {        //            CardInfoGather cardInfoGather = (CardInfoGather) data.getSerializableExtra(ParamConst.CARD_INFO_GATHER);        //            if (null != cardInfoGather) {        ////                cardId = cardInfoGather.getPublicBasicInfo().getAppletNo();        ////                LUtils.i(TAG, "card Id is " + cardInfoGather.getPublicBasicInfo().getAppletNo());        ////        ////                ToastUtils.showToast(this, "卡号：" + cardId);        //            }        //        }    }    @Override    public void onClick(View v) {        switch (v.getId()) {            case R.id.nfc_btnsaveothermoney:                mNfc_saveothermoney.setVisibility(View.VISIBLE);                mNfc_paymoney_bottomlly.setVisibility(View.VISIBLE);                mPaymoney_bottomlly3.setVisibility(View.VISIBLE);                break;            case R.id.paymoney_bottomlly3:                String money = mNfc_saveothermoney.getText().toString().trim();                if (money.length() > 0) {                    mTruemoney = Integer.parseInt(money);                    if (mNfc_saveothermoney.getText().toString().trim().length() > 0) {                        toAliPay(Yuan2fen.changeY2F(mTruemoney));                        LUtils.i(TAG, "changeY2F(mTruemoney) ==" + Yuan2fen.changeY2F(mTruemoney));                    }                }                break;            case R.id.tuikuan:                cardtuikuan();                break;            case R.id.nfc_toptitle_back:                finish();                break;            case R.id.quancun:                toAliPay(1);                break;        }    }    private void cardtuikuan() {        PlaceOrderUtils.refundApplication(orderId, "", new PlaceOrderUtils.RefundListenter() {            //PlaceOrderUtils.refundApplication("20020615490158158934", "", new PlaceOrderUtils.RefundListenter() {            @Override            public void refundApplySuccess(RefundApplyData orderData) {                ToastUtils.showToast(NFCPandaActivity.this, "已退款");                orderId = null;            }            @Override            public void refundApplyFail() {                ToastUtils.showToast(NFCPandaActivity.this, "退款失败");            }        });    }    private void toAliPay(int imoney) {        final ProductModel productModel = new ProductModel();        productModel.setAmout(imoney);        productModel.setCardNum(mAppletNo);        //        if (!TextUtils.isEmpty(cardId)) {        //            productModel.setCardNum(cardId);        //        }        productModel.setTradeType(2);        productModel.setPayChannel(ALIPAY);        productModel.setInstanceAid("");        productModel.setSeId("");        productModel.setSdkAccessChannel("zjj100001");        productModel.setMerchant(productModel.getMerchant());        PlaceOrderUtils.excepOrder(mAppletNo, new PlaceOrderUtils.ExcepOrderListener() {            //        PlaceOrderUtils.excepOrder(TextUtils.isEmpty(cardId) ? mAppletNo : cardId, new PlaceOrderUtils.ExcepOrderListener() {            @Override            public void abnormalOrders(ExcepOrder order) {                orderId = order.getOrderId();                ToastUtils.showToast(NFCPandaActivity.this, "请贴卡直接圈存上一次的订单");                LUtils.i(TAG, "orderId ==" + orderId);                quancunMoney(orderId);            }            @Override            public void abnormalOrdersNo() {                ToastUtils.showToast(NFCPandaActivity.this, "支付宝支付");                //商品详情基本信息  -------productModel                PlaceOrderUtils.toPlaceOrder(NFCPandaActivity.this, productModel, new PlaceOrderUtils.PlaceOrderListener() {                    @Override                    public void placeOrderSuccess(RechargeOnLineOrderData orderData) {                        //下单成功去支付                        RechargeOnLineOrderData onLineOrderData = new RechargeOnLineOrderData(orderData.getOrderId(), orderData.getAmout(), orderData.getOrderTime(), orderData.getResultParams());                        CtticAppSwithAppUtils.startPayment(NFCPandaActivity.this, onLineOrderData);                    }                    @Override                    public void placeOrderFail() {                        ToastUtils.showToast(NFCPandaActivity.this, "下单失败");                    }                });            }        });    }    @Override    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {        mGridViewAdapter.setSeclection(position);        mGridViewAdapter.notifyDataSetChanged();//        mRely_nfc_bottomlly.setVisibility(View.GONE);        String truemoney = mData_list.get(position).get("truemoney");        mTruemoney = Integer.parseInt(truemoney);        toAliPay(Yuan2fen.changeY2F(mTruemoney));    }}