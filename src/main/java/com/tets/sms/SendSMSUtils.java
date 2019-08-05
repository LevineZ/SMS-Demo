package com.tets.sms;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import org.json.JSONException;

import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.util.Random;

public class SendSMSUtils {
    public static void main(String[] args) {
        int appid = ;
        String appkey = ;
        int templateId = ;
        String smsSign= ;
        String str = "";         //随机验证码  六位
        try {
            Random random = new Random();
            for (int i = 0; i < 6; i++) {
                str += random.nextInt(10);
            }
            String[] params = {str, "2"};
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);       //单发
            SmsSingleSenderResult result = ssender.sendWithParam("86", "110", templateId, params, smsSign, "", "");
            System.out.println(result);

        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        } catch (com.github.qcloudsms.httpclient.HTTPException e) {
            e.printStackTrace();
        }
    }
}