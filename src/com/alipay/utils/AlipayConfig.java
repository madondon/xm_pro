package com.alipay.utils;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016100100638422";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCDchqa9Q1WViIsT0Ag1eKNiP+wjTtts1UScPh5ZNrJ/udISyumZxbzFtby/aXGwCyrVFUDYU228cWNhqzTqjWGiMfE5URFR9wZ756VR8Hahy28a5QGb7c6F6VFn++WlynSkvW4d2pV8X5VgvzudWDobWFSMSB47X9z37jexYArMh13fWzgpZluUfK+fOA6EZ0/nrE1egG3LfE1HueaN+ZpS+mnCJfCf0MOD5MbHjif18r+A56wY39iUa44o23g6ygDT0Q3YPQ7BjLu3jTQ+/AKIA7GiRa5ZtJdoLT4/vgnwFWusEzHNhSXzQOthpxCydGizYnmyqxCeIpqNrKPXNPnAgMBAAECggEAUqPDTFbymqwucfMlWz99XwSZ1HMGwPJ1ctGpthCNaV+kIxR3jffUOuLKIx0LQ4iAzPqJNEXRqUoZrN/V+oFRg5KtHgISSQhjvb6j0jC6FNzWCVcNSnqkFJbXJo3mAKRq++O/LsmNBhl9Ft/yiy0qzxYtkJIoWz7d2Yj3Xr+G1CCVuhBfm/SLd719H96T7XW+gPI/+feBeuGmZwe0KiHUuHLZ5dLF9kmOiUH9BeFJQWeh5uVxGmlHAx7Y3c824lbcehH0ljbv25gIF9vqfxpvr1RxfnrESUgqowNXAoz+zNA8w0xvavHxobtFgTqnwr74ZLm0+n8Nzgp8C8Aost7yAQKBgQDUVup/Cd9fWx00AysH9JlrSikZAIaFvjDOCLuFaYaT+gP03mZUK6/F7uYvyOyctgKnkldZRvhIjEnefaNHeHaAREBjpBz69kT6QTVI9Ka11UdyMTLqI3X6uwuSPlS2f152qgnOBLEzCAqEkJtAPeUMu3yyUOqlvHiMZ2BrrgO85wKBgQCeeR3k6Jx9ldGldFYgfh96WzN39ZSOoNYC8Q1UknAUKJ2QnH3U3UbWitnLwJlvnAOBT26cqFlt+A+Nl/7I+K06H0J6wNXtFmfKmdClXCzGYe2J4dy7zFve0P0YAYagzpSZDgyz/EYv2vhs1K81yYQtxu0SyHGSl4Tr/DzFskxRAQKBgQC2x/KnVHpBfQ8QXMHH4VfHMVlbOkaSDfu4/29cElBURNzvBhqTzd9KxLDE5XI4G70BPkmOgjbrTEeyC/hnZ1yqYRYEL5r6tM7/lO4nVOHEmEkeFRe80LWYFTaKtrj5N367KvexrfxS71MOdeQOUrk+Uo2ueVTjQe+K/NnVF0AEdQKBgCJ5dLBkjg6f9gCJY2ckKNLkXjj9erjgck4nhF/ej3VDNKvN723MSWnIwQKbPoXSKc0Kq6R21yWeLjv+UdYEiyK2xC6SHre6BRBFD+e5OqBZk2RId01/cojxa+25/0CiUB9PuCG7gsVMXHjId+k6kn+m49IPOu3J+oef0tSrKgEBAoGAbFktO3SPH9aDpWk9yVPRTtLeQXEOY2SfBcdR33h0AskHXIxGPwcfyzTQKIE7YjKNm+z1vp0ZzfXPrG1AA1PPK+NXDWBa3yb4SNl2rZZzPhMJCFi8Gq94iyhAsccgF7Wydw+4SAGVf0h8pYY5aOElnHRwM6LFWYcy6m/jtLr5q0Q=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuJJ31t/UW8/qQlw4wxLjcItNo0W49azHh820dcW+MZ0YJZXrH8Kbp0KoYpk35E3eg6tylsU8RtGnd44TukOHW1xgdD87mybioBi7Rly2a2avei87myvDAq4lfi6C7VnktHn6Eu2iUSjS01ZiIOKzWUsqyZNEaJ4f4tsoulg1t8+aUAjAYQLRvIqeR7eaUDJUBZueyAD9XGE/2M7sh9Vrat4H8QR4sv0NpNzkHdTys1umMr8Sw7ZiKmdfPNYvDi7W1Re1CSZokTIfBmThpG/e+XNtRSFZKbIs653h4Vw7x1nDQnRCuLDMslCbaQCBTCv/Hhq0LfgBtSD793/v+K+BpQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8080/0218MI-MAX/alipay/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/0218MI-MAX/alipay/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	//public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
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

