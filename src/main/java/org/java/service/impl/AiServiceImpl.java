package org.java.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.java.conf.AlipayConfig;
import org.java.service.AiService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by GD on 2019/8/22 0022 10:24
 */
@Service
public class AiServiceImpl implements AiService {
	@Override
	public void ali(HttpServletResponse response, HttpServletRequest request, Map<String,Object> map) throws AlipayApiException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
		//设置请求参数
		AlipayTradePagePayRequest aliPayRequest = new AlipayTradePagePayRequest();
		aliPayRequest.setReturnUrl(AlipayConfig.return_url);
		aliPayRequest.setNotifyUrl(AlipayConfig.notify_url);
		
		//商户订单号，后台可以写一个工具类生成一个订单号，必填
		String order_number = (String) map.get("ddid");
		//付款金额，从前台获取，必填
		String total_amount = (String) map.get("reg_money");
		//订单名称，必填
		String subject = new String("挂号");
		aliPayRequest.setBizContent("{\"out_trade_no\":\"" + order_number + "\","
				+ "\"total_amount\":\"" + total_amount + "\","
				+ "\"subject\":\"" + subject + "\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		//请求
		String result = alipayClient.pageExecute(aliPayRequest).getBody();
		//输出
		out.println(result);//以下写自己的订单代码
		
	}
}
