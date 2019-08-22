package org.java.web;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.java.conf.AlipayConfig;
import org.java.util.UuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by GD on 2019/8/22 0022 9:17
 */
@Controller
public class aibbController {
	
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping("user/brguahao")
	public String pay(@RequestParam Map<String,Object> map,HttpServletResponse response) throws IOException, AlipayApiException {
		
		map.put("ddid", UuidUtils.uuid());
		System.out.println(map);
		System.out.println("123");
		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
		
		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
		
		//商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no = (String) map.get("ddid");
		//付款金额，必填
		String total_amount = (String) map.get("reg_money");
		//订单名称，必填
		String subject = "挂号";
		//商品描述，可空
		String body = "";
		
		alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
				+ "\"total_amount\":\""+ total_amount +"\","
				+ "\"subject\":\""+ subject +"\","
				+ "\"body\":\""+ body +"\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		//请求
		String result = alipayClient.pageExecute(alipayRequest).getBody();
		System.out.println(result);
		request.setAttribute("result",result);
		PrintWriter out = response.getWriter();
		out.println(result);
		return "aibb";
		
	}
	
	
	@ResponseBody
	@RequestMapping("aibb/ok")
	public void ok(){
		System.out.println("ok");
	}
	
	
	
	
	
	
	
	
	
}
