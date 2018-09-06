package com.yjh.practice.service;


/**
 * 
 * Description 用户业务逻辑，定义与用户登录等功能相关的接口 
 * @author YJH
 * @date 2018年6月1日  
 *
 */
public interface UserService {
	//用户登录时，在页面选择角色，然后输入需要的参数，如果验证码和session中的一致，则进行下一步验证
	//如果role=1，进企业表；如果role=2，进学生表；如果role=9，进系统参数表
	public boolean login(String account, String password, String Verification_Code, String role,String vchidden);
}
