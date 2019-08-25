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
	
	public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCEglEZ0IRovd8bZq22YauMXItXPnNydQ2gbHLMBsvRWUCuO5QIsAvu7orARdbC7/jPDRWhVxqRkwEZkvxFtdnxpNZuWno3Wk60Bx7IueGIqLeceQs4LLZtWnnMZa/1O4TqiNi4qOZPiLny0Gn++VL15AZfmS2hnZH0BVrwIgIpHXn/JXRr8zwSUvNmbOLZabc6Ce7HBc5PCsGBrJpfd+VbpgGrSpd9NSNHas98iMNDW6Yc2y5M0RKc4oZwznfPvUItaHpQSk///hlbEWhwGmxgCjXsu5buNI42uzm8oRP3Dbha/ohFPBMMCeaU7/UqjWUhzvOXfT7fnfv5Irc69CstAgMBAAECggEAYx8Yi7bvVWqy+kxUpZlqOhUAJsMQmQ32o4p8VSLAVFnviGYI1nbadmD6adnUTP41oKypAYem2D6fFAJfs8XUwm4L+YC067MiLIkm/gAONav/rYzeV6caz7JeagenGErtooO7mma94cDlWD2PDKx04nswSVLMT5kPFPrsi7Tk6NuN+m8TWq5ZNEtiHBUqPpGltg+rFQ0R017jvvgqdgLXaSSsc7MHZ2qkKlsyzxQ9TiTa4W2uX7sgxzfVQKtAGr+jOtQEz3HHdf4MNTbNvoRBrGA3FjF7YTYdkjXJyZqyQ10KnzjcgjRppjmc4C3TBn5RFz4tHsyjqxK1Q7hN+1SiAQKBgQDR/NFGld0mYvkNG6sruqHnUMz4YwrSWqipqmzCwWKopzWvVmcU/oZq8Jzz9e3RgLE1i3BA2F7noDGEPWxIo13HlbAr7cg4a52nXIi2kSvqRl33F6Oix9JxrIGhe4dkl78vQS0ulQGCJs9cM33qQvG9XhJDNJJmOGEjXgODWO6kmwKBgQChi14iFpQkixDtGq1LvOFbO9eGkyTkDofnjz/1yfYWCq8KBC5lnYCKG/zT89pg3uULmxCcLGAeRmwDk6GtziD+TDxRiqlolVm/7iRrE07CA3A84JQvimLJR54LOTkfLG2LICZCp3N8XrHVxqc/b4nRwv0q40i+oPAlBX6xRhoX1wKBgQCpY2llqQpmJryESGeBtOFZeSJ+01YtbZhUlPd6+wicx5VUxTwOAWto5IOXgDDBKTDJ4ptAk8mqytxmsi2hQrcpgqREK2GxsY3RLaHSs7RbHTw7UERd0Rb/P4qqOr1y9b3xXoAV7Xj0Bl6LH7ZYvHvIqBPPRp85+gzRvMrUr26CNQKBgDThVz4XYAUaVL8YxXOOb8KaszGFR0U0EH7XYJuHsfuDIctIlRuEVEhhYwDKyUh+jkGfT++fgxNGACOnlvw7BofX0sLqfgwTH575SIiYeGaAE6SJ61gol6/FttF7uNwggfdR3dRh1ib3JIWTErpsO5QjnRYF8pEcQ6AKzW8gQHhfAoGBAJ9t4XouLZU1ej5fAL3LMRrWetgl9jvOJBa/fi+Ov89uBEmH3Ww6cIjH8/KWJte0ooU87Dddnu+XHWolCCeHsxZhXxX+kOVUtty0MhF4UFbGXPKuBODO2weqHWYxYSfA+FLR6bCmSJNOC7mWs3nbcL2eQXcxKOjc1t4FOr1nw6Nx";
	
	public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAi9NqqnqJXPGP72ZNMI/Vkq28HvUF2LLNRv+K07GRzi2UuxP8JjiIRkyKGfcJumQfB5j8wtqAWimw7INddvU8eOPamcRhtwQwtC/nsIABNOle6yw9MGHTFkCDSLPi5LFjSIY3NDriGLUP8EyLVKILOyu0k7s91wR8KSZEqTd4TwMeBGLl8NSOGpPacrH56F7fq+c1vrdS/JPPuACLW4RnQXMDGiFqUScFw48HowOndcv4hAu0EVzbVtFnregsQWQixMabEstguC8GRkFeoOWv0ko3lCFoQjy5RmENPkiuumE/mwB7g9hBpQ3GzGXPaTS0/C/GTgAJLO6qcKPdcJRGvQIDAQAB";
	public static String notify_url = "http://localhost:9999/user/payi";
	
	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:9999/aibb/guahaook";
	public static String return_url1 = "http://localhost:9999/aibb/yaook";
	
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

