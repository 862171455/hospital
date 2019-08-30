package org.java.web;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.java.conf.AlipayConfig;
import org.java.service.*;
import org.java.util.DateUtils;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by GD on 2019/8/22 0022 9:17
 */
@Controller
public class aibbController {
	@Autowired
	private YaocarService yaocarService;
	@Autowired
	private CreateOrderService createOrderService;

	@Autowired
	private YaoorderService yaoorderService;
	@Autowired
	private YaookService yaookService;
	
	@Autowired
	private HisService hisService;
	
	private int ysid;
	private int ksid;
	@RequestMapping("user/brguahao")
	public String pay(@RequestParam Map<String,Object> map,HttpServletResponse response,HttpServletRequest request) throws IOException, AlipayApiException {
		
		map.put("ddid", UuidUtils.uuid());
		System.out.println(map);
		ysid=Integer.parseInt((String) map.get("reg_zzys"));
		ksid=Integer.parseInt((String) map.get("reg_type"));
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
		String subject = "guahao";
		//商品描述，可空
		String body = "xx";
		
		alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
				+ "\"total_amount\":\""+ total_amount +"\","
				+ "\"subject\":\""+ subject +"\","
				+ "\"body\":\""+ body +"\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		//请求
		String result = alipayClient.pageExecute(alipayRequest).getBody();
		request.setAttribute("result",result);
		PrintWriter out = response.getWriter();
		out.println(result);
		return "aibb";
		
	}
	
	@RequestMapping("/pay/aibbyao")
	public String buyyao(@RequestParam Map<String,Object> map,HttpServletResponse response,HttpServletRequest request) throws AlipayApiException, IOException {
		String ddid=UuidUtils.uuid();
		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
		
		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url1);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
		
		//商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no = ddid;
		//付款金额，必填
		String total_amount = (String) map.get("money");
		//订单名称，必填
		String subject = "maiyao";
		//商品描述，可空
		String body = "xx";
		
		alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
				+ "\"total_amount\":\""+ total_amount +"\","
				+ "\"subject\":\""+ subject +"\","
				+ "\"body\":\""+ body +"\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		//请求
		String result = alipayClient.pageExecute(alipayRequest).getBody();
		request.setAttribute("result",result);
		PrintWriter out = response.getWriter();
		out.println(result);
		return "aibb";
	}
	
	
	
	@RequestMapping("aibb/guahaook")
	public String ok(HttpServletRequest request){
		Map<String,Object> user = (Map<String, Object>) request.getSession().getAttribute("user");
		Map<String,Object> map=new HashMap<>();
		map.put("reg_id",request.getParameter("out_trade_no"));//回调带回来的uuid主键
		map.put("money",request.getParameter("total_amount"));
		map.put("reg_zzys",ysid);
		map.put("reg_type",ksid);
		map.put("reg_br",user.get("patient_id"));
		map.put("userId",user.get("patient_username"));
		map.put("time", DateUtils.getnow());
		hisService.upmoney(map);//收益
		createOrderService.registerOrder(map);
		//创建流程实例----挂号单
		return "redirect:/user/findRegisterOrder";
	}
	
	@RequestMapping("aibb/yaook")
	public String yaook(HttpServletRequest request){
		Map<String,Object> map=new HashMap<>();
		//创建流程实例
		map.put("money",request.getParameter("total_amount"));//回调带回来的金钱
		map.put("ddid",request.getParameter("out_trade_no"));//回调带回来的uuid主键
		Map<String,Object> user = (Map<String, Object>) request.getSession().getAttribute("user");
		map.put("userid",user.get("patient_id"));
		List<Map> showcar = yaocarService.showcar(map);
		for(Map<String,Object> m:showcar){
			System.out.println(m);
			m.put("money",map.get("money"));
			m.put("ddid",map.get("ddid"));
			yaocarService.updatetype(map);
			yaoorderService.addorder(m);
		}
		yaookService.addyaook(map);
		yaocarService.delallyao(map);
		map.put("time", DateUtils.getnow());
		hisService.upmoney(map);//收益
		
		return "qt_main";
	}




	@RequestMapping("/payjc")
	public String bujc(@RequestParam Map<String,Object> map,HttpServletResponse response,HttpServletRequest request) throws AlipayApiException, IOException {
		String ddid=UuidUtils.uuid();
		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url2);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

		//商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no = ddid;
		//付款金额，必填
		String type= (String) request.getSession().getAttribute("detailsCheck");
		String total_amount="0";
		if(type.equals("核磁共振")){
			total_amount ="2000";
		}
		if(type.equals("放射治疗")){
			total_amount ="1000";
		}
		if(type.equals("血液检查")){
			total_amount ="200";
		}
		if(type.equals("尿液检查")){
			total_amount ="500";
		}

		//订单名称，必填
		String subject = "jc";
		//商品描述，可空
		String body = "xx";

		alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
				+ "\"total_amount\":\""+ total_amount +"\","
				+ "\"subject\":\""+ subject +"\","
				+ "\"body\":\""+ body +"\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		//请求
		String result = alipayClient.pageExecute(alipayRequest).getBody();
		request.setAttribute("result",result);
		PrintWriter out = response.getWriter();
		out.println(result);
		return "aibb";
	}

	@RequestMapping("aibb/jcok")
	public String jcok(HttpServletRequest request){
		Map<String,Object> map=new HashMap<>();
		map.put("money",request.getParameter("total_amount"));//回调带回来的金钱
		map.put("time", DateUtils.getnow());
		System.out.println(map);
		hisService.upmoney(map);//收益
		return "redirect:/findRegisterOrder";
	}




	@RequestMapping("/payzy")
	public String buzy(@RequestParam Map<String,Object> map,HttpServletResponse response,HttpServletRequest request) throws AlipayApiException, IOException {
		String ddid=UuidUtils.uuid();
		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url3);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

		//商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no = ddid;
		//付款金额，必填
		String type= (String) request.getSession().getAttribute("detailsCheck");

		String total_amount=(String) request.getSession().getAttribute("zy");

		//订单名称，必填
		String subject = "zy";
		//商品描述，可空
		String body = "xx";

		alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
				+ "\"total_amount\":\""+ total_amount +"\","
				+ "\"subject\":\""+ subject +"\","
				+ "\"body\":\""+ body +"\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		//请求
		String result = alipayClient.pageExecute(alipayRequest).getBody();
		request.setAttribute("result",result);
		PrintWriter out = response.getWriter();
		out.println(result);
		return "aibb";
	}

	@RequestMapping("aibb/zyok")
	public String zyok(HttpServletRequest request){
		Map<String,Object> map=new HashMap<>();
		map.put("money",request.getParameter("total_amount"));//回调带回来的金钱
		map.put("time", DateUtils.getnow());
		System.out.println(map);
		hisService.upmoney(map);//收益
		return "redirect:/user/findRegisterOrder";
	}


	@RequestMapping("/payss")
	public String buss(@RequestParam Map<String,Object> map,HttpServletResponse response,HttpServletRequest request) throws AlipayApiException, IOException {
		String ddid=UuidUtils.uuid();
		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url4);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

		//商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no = ddid;
		//付款金额，必填
		String type= (String) request.getSession().getAttribute("operationtype");
		String total_amount="0";
		if(type.equals("阑尾手术")){
			total_amount ="3000";
		}
		if(type.equals("心脏手术")){
			total_amount ="1000";
		}
		if(type.equals("良性肿瘤手术")){
			total_amount ="2000";
		}


		//订单名称，必填
		String subject = "ss";
		//商品描述，可空
		String body = "xx";

		alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
				+ "\"total_amount\":\""+ total_amount +"\","
				+ "\"subject\":\""+ subject +"\","
				+ "\"body\":\""+ body +"\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		//请求
		String result = alipayClient.pageExecute(alipayRequest).getBody();
		request.setAttribute("result",result);
		PrintWriter out = response.getWriter();
		out.println(result);
		return "aibb";
	}

	@RequestMapping("aibb/ssok")
	public String ssok(HttpServletRequest request){
		Map<String,Object> map=new HashMap<>();
		map.put("money",request.getParameter("total_amount"));//回调带回来的金钱
		map.put("time", DateUtils.getnow());
		System.out.println(map+""+"...");
		hisService.upmoney(map);//收益
		return "redirect:/findRegisterOrder";
	}
































	@ResponseBody
	@RequestMapping("user/payi")
	public void pay(){
		System.out.println("ok1");
	}
	@ResponseBody
	@RequestMapping("aibb")
	public void aibb(){
		System.out.println("aibb");
	}
	
	
	
	
	
	
	
	
}
