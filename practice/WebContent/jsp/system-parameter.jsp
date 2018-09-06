<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="Stylesheet" type="text/css" href="/practice/css/index.css" />
<title>实训主页</title>
</head>
<style>

</style>
<body>
	<%@include file="Title.jsp"%>
	
	<div class="right group">
		<dl>
		
		
			<dd>
				 <form action = "/practice/SelectSystemConfigServlet" method="post">
                         <table class="table table-bordered">
								<tr>
                                 	<th>管理员密码</th>
                                 	<td><input type="password" name="pwd" value=""/></td>
                                </tr>
                                
	                                <input type="hidden" name="code" value="${sys.invitationCode }">
	                           
	                            <tr>
		                            <th>企业发布方案开始日期</th>
		                            <td><input type="text" name="releaseProjectStartDate" value="${sys.releaseProjectStartDate }"></td>
	                            </tr>
	                            <tr>
		                            <th>企业发布方案截至日期</th>
		                            <td><input type="text" name="releaseProjectEndDate" value="${sys.releaseProjectEndDate }"></td>
	                            </tr>
	                            <tr>
		                            <th>学生选择案开始日期</th>
		                            <td><input type="text" name="studentSelStartDate" value="${sys.studentSelStartDate }"></td>
	                            </tr>
	                            <tr>
		                            <th>学生选择案截至日期</th>
		                            <td><input type="text" name="studentSelEndDate" value="${sys.studentSelEndDate }"></td>
	                            </tr>
	                            <tr> 
		                            <th>学生最多待选方案数量</th>
		                            <td><input type="text" name="studentSelMaxnum" value="${sys.studentSelMaxnum }"></td>
	                            </tr>
	                            <tr>
	                            	<th>操作选项</th>
	                            	<td>
	                            		<input type="submit"  value="提交" class="btn btn-success">
	                            		<input type="reset"  value="重置" class="btn btn-danger">
	                            	</td>
	                            </tr>
	                    </table>
                    </form>
			</dd>
			
			
			
		</dl>
	</div>
	
</body>
<script type="text/javascript">  
      
</script>  
</html>