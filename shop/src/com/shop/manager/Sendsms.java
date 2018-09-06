package com.shop.manager;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import org.dom4j.Document;   
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;   
import org.dom4j.Element; 
/**
 * 用的是哪个  互亿无线   可以去官网看文档 10条短信免费
 * @author YJH
 * @category 手机验证码生成
 */
public class Sendsms {
	private static String Url = "http://106.ihuyi.com/webservice/sms.php?method=Submit";
	
	public int send(String phoneNumber){
		
		HttpClient client = new HttpClient(); 
		PostMethod method = new PostMethod(Url);

		client.getParams().setContentCharset("GBK");
		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=GBK");

		int mobile_code = (int)((Math.random()*9+1)*100000);

	    String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。");

		NameValuePair[] data = {//提交短信
			    new NameValuePair("account", "查看用户名是登录用户中心->验证码短信->产品总览->APIID"), //查看用户名是登录用户中心->验证码短信->产品总览->APIID
			    new NameValuePair("password", "查看密码请登录用户中心->验证码短信->产品总览->APIKEY"),  //查看密码请登录用户中心->验证码短信->产品总览->APIKEY
			    //new NameValuePair("password", util.StringUtil.MD5Encode("密码")),
			    new NameValuePair("mobile", phoneNumber), 
			    new NameValuePair("content", content),
		};
		method.setRequestBody(data);

		try {
			client.executeMethod(method);
			
			String SubmitResult =method.getResponseBodyAsString();

			//System.out.println(SubmitResult);

			Document doc = DocumentHelper.parseText(SubmitResult);
			Element root = doc.getRootElement();

			String code = root.elementText("code");
			String msg = root.elementText("msg");
			String smsid = root.elementText("smsid");

			System.out.println(code);
			System.out.println(msg);
			System.out.println(smsid);

			 if("2".equals(code)){
				 System.out.println("短信提交成功");
				 System.out.println(mobile_code);
				 return mobile_code;
				
			}

		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return 0;
	}
//	public static void main(String[] args) {
//		Sendsms smSendsms=new Sendsms();
//		int a=smSendsms.send("18384737807");
//		System.out.println(a);
//	}

}
