<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Demo</title>
	
	<style type="text/css">
		.content {
			width: 30%;
			margin-left: auto;
			margin-right: auto;
		}
	</style>
</head>
<body>
	<div class="content">
		<h1 style="text-align: center;">DEMO</h1>
		<div>Nhập văn bản:</div>
		<form action="sample" method="post">
			<input type="text" name="input">
			<input type="submit" name="button" value="Phân tích">
		</form>
		<br/>
		<div>
			<a href="statistic.jsp">Quay lại trang thống kê</a>
		</div>
	</div>
</body>
</html>