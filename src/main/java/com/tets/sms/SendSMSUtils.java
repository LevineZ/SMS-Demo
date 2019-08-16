package com.tets.sms;

import com.github.qcloudsms.*;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;
import com.github.qcloudsms.SmsMobileStatusPuller;
import com.github.qcloudsms.SmsStatusPullCallbackResult;
import java.io.IOException;
import java.util.Random;

public class SendSMSUtils {
    public static final int appid = 1400239070;
    public static final String appkey = "6dc551802025200199b61a752f7a447e";
    public static final int templateId = 389849;
    public static final String smsSign = "";
    String url = "https://yun.tim.qq.com/v5/tlssmssvr/sendsms";
    static String phoneNumber1 = "17726055317";
    static String phoneNumber2 = "15181215096";
    static String phoneNumber3 = "15510805093";

    public static void main(String[] args) {
        try {
//            int beginTime = 1565775745;  // 开始时间（UNIX timestamp）
//            int endTime = 1565785745;    // 结束时间（UNIX timestamp）
            int maxNum = 10;             // 单次拉取最大量
            long beginTime=(System.currentTimeMillis()/1000)-30l;
            long endTime=beginTime+300L;
            String[] phoneNumbers={phoneNumber1,phoneNumber2,phoneNumber3};
            String str="";
            Random random=new Random();
            for (int i = 0; i < 6; i++) {
                str += random.nextInt(10);
            }
            String[] params = {str};
            SmsSingleSender ssender = new SmsSingleSender(appid,appkey);       //单发
            SmsSingleSenderResult result = ssender.sendWithParam("86", "17726055317", templateId, params, smsSign, "", "");
            //System.out.println(result);
            System.out.println("短信验证码为"+str);

            // 拉取短信回执
            SmsMobileStatusPuller mspuller = new SmsMobileStatusPuller(appid, appkey);
            SmsStatusPullCallbackResult callbackResult = mspuller.pullCallback("86", phoneNumbers[0], beginTime, endTime, maxNum);
            System.out.println(callbackResult);
            System.out.println(beginTime);
        } catch (HTTPException e) {
            // HTTP 响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // JSON 解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络 IO 错误
            e.printStackTrace();
        }

    }
}
