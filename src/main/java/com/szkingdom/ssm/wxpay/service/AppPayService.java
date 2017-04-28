package com.szkingdom.ssm.wxpay.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.szkingdom.ssm.wxpay.bean.Unifiedorder;
import com.szkingdom.ssm.wxpay.bean.UnifiedorderResult;
import com.szkingdom.ssm.wxpay.client.LocalHttpClient;
import com.szkingdom.ssm.wxpay.util.JsonUtil;
import com.szkingdom.ssm.wxpay.util.MapUtil;
import com.szkingdom.ssm.wxpay.util.SignatureUtil;
import com.szkingdom.ssm.wxpay.util.XMLConverUtil;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * Created by admin on 2017/4/26.
 */

@Service
public class AppPayService {

    protected static Header xmlHeader = new BasicHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_XML.toString());

    protected static final String MCH_URI = "https://api.mch.weixin.qq.com";

    /**
     * 统一下单
     * @param unifiedorder unifiedorder
     * @param key key
     * @return UnifiedorderResult
     */
    public static UnifiedorderResult payUnifiedorder(Unifiedorder unifiedorder, String key){
        Map<String,String> map = MapUtil.objectToMap(unifiedorder,"detail");
        //@since 2.8.8 detail 字段签名处理
        if(unifiedorder.getDetail() != null){
            map.put("detail", JsonUtil.toJSONString(unifiedorder.getDetail()));
        }
        if(key != null){
            String sign = SignatureUtil.generateSign(map,unifiedorder.getSign_type(),key);
            unifiedorder.setSign(sign);
        }
        String unifiedorderXML = XMLConverUtil.convertToXML(unifiedorder);
        HttpUriRequest httpUriRequest = RequestBuilder.post()
                .setHeader(xmlHeader)
                .setUri(MCH_URI+ "/pay/unifiedorder")
                .setEntity(new StringEntity(unifiedorderXML, Charset.forName("utf-8")))
                .build();
        return LocalHttpClient.executeXmlResult(httpUriRequest,UnifiedorderResult.class,unifiedorder.getSign_type(),key);
    }
}
