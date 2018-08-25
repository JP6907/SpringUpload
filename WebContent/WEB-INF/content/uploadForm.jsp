<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>文件上传</title>       
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">   
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
 </head>
 <h3>文件上传</h3>
  <br>
 <center>
	<form action="upload" enctype="multipart/form-data" method="post">
	<table>
		<tr>
			<td><label>文件描述：</label></td>
			<td><input type="text" name="description"/></td>
		</tr>
		<tr>
			<td><label>请选择文件：</label></td>
			<td><input type="file" name="file"/></td>
		</tr>
		<tr>
			<td><input type="submit" value="上传"/></td>
		</tr>
	</table>
	</form>
	</center> 
 
</html>