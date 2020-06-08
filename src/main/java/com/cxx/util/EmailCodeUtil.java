package com.cxx.util;

import org.apache.commons.mail.HtmlEmail;

public class EmailCodeUtil {
		// 创建验证码
		public static String smsCode() {
			String random = (int) ((Math.random() * 9 + 1) * 100000) + "";
			return random;
		}
		
		//邮箱验证码
		public static boolean sendLoginEmail(String name,String emailaddress,String code){
			try {
				HtmlEmail email = new HtmlEmail();//不用更改
				email.setHostName("smtp.qq.com");//需要修改，126邮箱为smtp.126.com,163邮箱为163.smtp.com，QQ为smtp.qq.com
				email.setCharset("UTF-8");
				email.addTo(emailaddress);// 收件地址
	 
				email.setFrom("1138956615@qq.com", "百团纳新网站");//此处填发送邮箱的邮箱地址和用户名,用户名可以任意填写
	 
				email.setAuthentication("1138956615@qq.com", "txnoqmachzsqbafa");//此处填写邮箱地址和客户端授权码
	 
				email.setSubject("百团纳新网站登录验证码");//此处填写邮件名，邮件名可任意填写
				email.setMsg("尊敬的用户"+name+"您好,您本次注册的验证码是:" + code);//此处填写邮件内容
				email.send();
				return true;
			}
			catch(Exception e){
				e.printStackTrace();
				return false;
			}
		}
		
		/*public static void main(String[] args) {
			String code=EmailCodeUtil.smsCode();
			EmailCodeUtil.sendEmail("1138956615@qq.com", code);
		}*/
}
