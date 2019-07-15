package com.itheima.utils;

import com.github.wxpay.sdk.WXPayUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class WXPayUtils {
    /*
       生成二维码
    */
    public static Map createNative(String out_trade_no, String total_fee){

        //配置文件获取商户编号
        ResourceBundle weixinpay = ResourceBundle.getBundle("weixinpay");
        //1.创建参数
        Map<String, String> param = new HashMap<>();
        param.put("appid", weixinpay.getString("appid"));//公众号
        param.put("mch_id", weixinpay.getString("partner"));//商户号
        param.put("nonce_str", WXPayUtil.generateNonceStr());//随机字符串
        param.put("body", "黑马程序员");//商品描述
        param.put("out_trade_no", out_trade_no);//商户订单号
        param.put("total_fee", total_fee);//总金额（分）
        param.put("spbill_create_ip", "127.0.0.1");//IP
        param.put("notify_url", "http://www.itcast.cn");//回调地址
        param.put("trade_type", "NATIVE");//交易类型
        System.out.println(param);
        try {
            //2.生成要发送的xml
            String xmlParam = WXPayUtil.generateSignedXml(param, weixinpay.getString("partnerkey"));
            System.out.println(xmlParam);
            HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");
            client.setHttps(true);
            client.setXmlParam(xmlParam);
            client.post();
            //3.获得结果
            String result = client.getContent();
            System.out.println(result);
            Map<String, String> resultMap = WXPayUtil.xmlToMap(result);
            Map<String, String> map = new HashMap<>();
            map.put("code_url", resultMap.get("code_url"));//支付地址
            map.put("total_fee", total_fee);//总金额
            map.put("out_trade_no", out_trade_no);//订单号
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean getFlag(String oid){
        while(true){
            Map map = queryPayStatus(oid);
            if(map.get("trade_state").equals("SUCCESS")){
                return true;
            }

            try {
                Thread.sleep(3000);//每间隔几秒访问一次
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    private static  Map queryPayStatus(String out_trade_no) {

        //配置文件获取商户编号
        ResourceBundle weixinpay = ResourceBundle.getBundle("weixinpay");
        //1.创建参数
        Map param = new HashMap();
        param.put("appid", weixinpay.getString("appid"));//公众号
        param.put("mch_id", weixinpay.getString("partner"));//商户号
        param.put("out_trade_no", out_trade_no);//订单号
        param.put("nonce_str", WXPayUtil.generateNonceStr());//随机字符串
        String url = "https://api.mch.weixin.qq.com/pay/orderquery";
        try {
            String xmlParam = WXPayUtil.generateSignedXml(param, weixinpay.getString("partnerkey"));
            HttpClient client = new HttpClient(url);
            client.setHttps(true);
            client.setXmlParam(xmlParam);
            client.post();
            String result = client.getContent();
            Map<String, String> map = WXPayUtil.xmlToMap(result);
            System.out.println(map);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
