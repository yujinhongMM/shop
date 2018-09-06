<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>实训主页</title>
<link rel="Stylesheet" type="text/css" href="/practice/css/index.css" />
</head>
<style>

</style>
<body>
	<%@include file="Title.jsp"%>
	
	<div class="right group" id="show">
		<dl>
		
		 <!--公司被审核了就不能修改信息了，如果没被审核就可以修改  -->
			<dd>
				 <c:choose>
				
                                	<c:when test="${company.auditDate != null }">
                                   
                                            <h2> <label></label> 基本信息 </h2>
                                        
                                    
                                            <label>企业简介：</label>
                                            <input type="text" disabled="disabled" value="${company.profile }" name="profile" ><br><br>
                                        
                                    		<label>联系人：</label>
                                            <input type="text" disabled="disabled" value="${company.contacts}" name="contacts"><br><br>
                                       		 <label>公司地址：</label>
                                            <input type="text" disabled="disabled" value="${company.address}" name="address"><br><br>
                                     		<label>电话：</label>
                                            <input type="text" disabled="disabled" value="${company.phone}" name="phone"><br><br>
                                        
                                	</c:when>
                                	<c:otherwise>
                                	
                                	<form action="${pageContext.request.contextPath }/UpdateCompanyInfo" method="post">
                                    
                                            <h2> <label></label> 基本信息 </h2>
                                        
                                    
                                    
											<label>企业简介：</label>
											<textarea name="profile">${company.profile }</textarea><br><br>
									        <label>联系人：</label>
                                            <input type="text"  value="${company.contacts}" name="contacts"><br><br>
                                        
                                            <label>公司地址：</label>
                                            <input type="text"  value="${company.address}" name="address"><br><br>
                                        	<label>电话：</label>
                                            <input type="text"  value="${company.phone}" name="phone"><br><br>
                                        
	                                        <input type="submit" value="确定" class="btn btn-success"/>
	                                        <input type="reset" value="重置" class="btn btn-warning"/><br><br>
	                                  
	                                
	                                </form>
	                                 
                                 <form action="${pageContext.request.contextPath }/UpdateCompanyInfo" method="post">
									输入原密码<input type="password" name="oldpwd"><br><br>
									新密码<input type="password" name="newpwd"><br><br>
									<input type="submit" value="确认修改" class="btn btn-success"><br>
								</form>
	                                    
	                               
                        </c:otherwise>
                   </c:choose>
			</dd>
		
		</dl>
	</div>
	
</body>
<script type="text/javascript">  

</script>  
</html>