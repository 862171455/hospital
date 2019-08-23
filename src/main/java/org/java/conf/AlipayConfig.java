package org.java.conf;

import org.springframework.context.annotation.Configuration;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */
@Configuration
public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	
	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id ="2016092800617094";
	
	public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCLm9uF5ms2j0LlVnMl4zpRUM+BVNi7YJjqPVQL0g1+mI33t89S093NIhLDoScJRdl7ujF4g8yPb51RL+bQwuYvNrAZ7YZtduX3aVEm9RpG6scD2SbhJ+Ng/HPWW0uhz9Rat7YH8DsM48VfCH0VGts+F8qs9snM52nDrt7wDmmZ1j3G+Gq7gQ4dqorTlDSwPMmUtzpcHufpEBXoRekIkpl3AXLbsPCrEuD2t8XP3/xNbWXe2xx5V0lSJzPyF2vRh8kpg6bq/hlZFgvwbMWYeFbyzIpDao43EKrzpTQrggf4M3OLlDqUylBlE6MqScahGB9vhHwKN1YAtzMWic9URJMTAgMBAAECggEAH28ESm3LDgnLoh+I5ar34n8zeYdFFUt2oqcUWWT+LNbkgDYetCOH+TwGxY1vYKMdiMIviVcDo/gZj8IdUjvP4CmMFQpqDhOLS92mrALb28PY84DdRCzbAWdWHZmIufI1egeLMNZiNVDDi9Y5c5sMajqpI7CdV0lzgt/Iynw0QrKosioM+ciziADoMNTvCD6EbOHbjFIxQK4pZ1G/Tji9S12nS/QkvihDuzq/+3iELhKVd7+MTJ+epf117xBh1N/TppLcwGAocR0UaHPOl7Ku1jo4+yTy+QVovZU4cP+sxh3vk9JNDHZhT6902/gP30ZKFnTNYTrfGJSZotm6juCc4QKBgQDSJyw5Yi2m0CGIEfIH6goqMgOAn3/2H7GPcq4baBM42G2T3+9JKe/B7y4alVp9Xv9zGF5xTFb4pKWaOt0wsL0GncmJiUk2jbn2T9DjpACHWnIOnFu7tQ1a1louftEEobcjsapKUfAb/sJSnk+G1ZVEdQwAVI6nw+agJ+J+Gk0uFQKBgQCqEN1ZL0/69tpE/7kT6QHlNTVZTwPjO6x9V8SEjdRTbraFyEZTkLvT88lRdjFlAH5nLjcz84sMpGneVrpACItSxwQ9qe54UdJgngmdDcZ2IOfJNojIaU0wR4VZQfWLh7Waa8Alt5XjhKFRdXo3niiMVyIE8EszACsYvH3dHgOuhwKBgGDkPreWVIX8n/kkjPqpJVafCI5c+5p2sl74+N0Z9wLWhm8PaDvLYgeARzlqcvYqvoxKSADkuZSaFOAvE5xHYF+UkD8hVOgaIAs4cY8DJltBkCS8Ym8A4s9m1N1rCuOfy9hhKapqbNH7e4ysAO6huth9PrNISR2AbOyadwq9BaihAoGANch7mBbkXmPvB18wMuG/nbNEGehpPFXE6Sb2mU0ZVwFua8S4qPjezdsNUdvlJyIO16bAAogE84yThqM658FL/nO+HZo+NoL522BfffG7T+R8KSA8BdV3ectEmsPHWccjYaCt9b+ngXqSYeabLjewbyGnOpSb05c8CeiijWlOlMUCgYEAymgfEr41ZPS82NNkOFdt0F1l5nXCMWaqunPx0PIDy5kKL+3KS7Kh8JjVdCpOqI0+rS+bgI8H59s3Igltaa5HqUk7hSzx10H7vNr4Gy7axDI0WMCgD71Df4Hb0729ccC4a2CklDrUXyPRlfG9L2XLaQ7kweZX60emofBYczVRXKs=";
	
	// 支付宝公钥
	public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAi9NqqnqJXPGP72ZNMI/Vkq28HvUF2LLNRv+K07GRzi2UuxP8JjiIRkyKGfcJumQfB5j8wtqAWimw7INddvU8eOPamcRhtwQwtC/nsIABNOle6yw9MGHTFkCDSLPi5LFjSIY3NDriGLUP8EyLVKILOyu0k7s91wR8KSZEqTd4TwMeBGLl8NSOGpPacrH56F7fq+c1vrdS/JPPuACLW4RnQXMDGiFqUScFw48HowOndcv4hAu0EVzbVtFnregsQWQixMabEstguC8GRkFeoOWv0ko3lCFoQjy5RmENPkiuumE/mwB7g9hBpQ3GzGXPaTS0/C/GTgAJLO6qcKPdcJRGvQIDAQAB";
	
	public static String notify_url = "http://localhost:9999/user/pay";
	
	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "://localhost:9999/user/aili";
	
	// 签名方式
	public static String sign_type ="RSA2";
	
	// 字符编码格式
	public static String charset ="utf-8";
	
	// 支付宝网关
	public static String gatewayUrl ="https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	
	/**
	 * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
	 * @param sWord 要写入日志里的文本内容
	 */
	public static void logResult(String sWord) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
			writer.write(sWord);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

