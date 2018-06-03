<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>Insert title here</title>
</head>
<body style="font-size: 30px;">
	<form method="post" action="${pageContext.request.contextPath}/test/doUpload.do"
		enctype="multipart/form-data">
		<input type="file" name="file" id="file" /><br /> <input
			type="submit" value="上傳" />
	</form>
</body>
</html>