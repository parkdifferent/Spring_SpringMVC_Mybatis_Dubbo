package com.szkingdom.ssm.wxpay;

import com.alibaba.fastjson.JSON;
import com.szkingdom.ssm.wxpay.bean.Detail;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DetailXmlAdapter extends XmlAdapter<String, Detail> {

	@Override
	public Detail unmarshal(String v) throws Exception {
		return JSON.parseObject(v, Detail.class);
	}

	@Override
	public String marshal(Detail v) throws Exception {
		return "<![CDATA[" + JSON.toJSONString(v) + "]]>";
	}

}
