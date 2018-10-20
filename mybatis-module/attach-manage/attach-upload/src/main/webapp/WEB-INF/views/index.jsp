<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>首页</title>
	</head>
	<body>
		${username}
		<form action="fastdfs/upload/file/sample" method="post" enctype="multipart/form-data">
			<div>
				<input name="file" id="file" type="file">
				<input type="submit" value="上传">
			</div>
		</form>
	</body>
</html>