package org.java.service;

import com.alipay.api.AlipayApiException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by GD on 2019/8/22 0022 10:23
 */
public interface AiService {
	public void ali(HttpServletResponse response, HttpServletRequest request,Map<String,Object> map) throws AlipayApiException, IOException;
}
