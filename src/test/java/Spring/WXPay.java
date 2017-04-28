package Spring;

import com.szkingdom.ssm.wxpay.bean.Unifiedorder;
import com.szkingdom.ssm.wxpay.bean.UnifiedorderResult;
import com.szkingdom.ssm.wxpay.config.WxpayConfig;
import com.szkingdom.ssm.wxpay.service.AppPayService;
import com.szkingdom.ssm.wxpay.util.PayUtil;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Created by admin on 2017/4/26.
 */


@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:application.xml"})
public class WXPay {



    @org.junit.Test
    public void test() {

        Unifiedorder unifiedorder = new Unifiedorder();
        unifiedorder.setAppid(WxpayConfig.APPID);
        unifiedorder.setMch_id(WxpayConfig.MCH_ID);
        unifiedorder.setNonce_str(UUID.randomUUID().toString().replace("-", ""));

        unifiedorder.setBody("商品信息");
        unifiedorder.setOut_trade_no("123456");
        unifiedorder.setTotal_fee("1");//单位分
        unifiedorder.setSpbill_create_ip("192.168.1.130");//IP
        unifiedorder.setNotify_url(WxpayConfig.UNIFIED_ORDER_CALLBACK_URL);
        unifiedorder.setTrade_type(WxpayConfig.TRADE_TYPE_JSAPI );//JSAPI，NATIVE，APP，MWEB

        //unifiedorder.setOpenid(openid);  //公众号支付

        AppPayService appPayService = new AppPayService();
        UnifiedorderResult unifiedorderResult = appPayService.payUnifiedorder(unifiedorder,WxpayConfig.KEY);

        System.out.println(unifiedorderResult.toString());



        //@since 2.8.5  API返回数据签名验证
       /* if(unifiedorderResult.getSign_status() !=null && unifiedorderResult.getSign_status()){
            String json = PayUtil.generateMchPayJsRequestJson(unifiedorderResult.getPrepay_id(), appid, key);

            //将json 传到jsp 页面
            request.setAttribute("json", json);
            //示例jsp
            request.getRequestDispatcher("pay_example.jsp").forward(request,response);
        }*/




    }


}
