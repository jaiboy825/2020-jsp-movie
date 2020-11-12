<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String directory = application.getRealPath("/upload/");
		int maxSize = 1024 * 1024 * 100;
		String encoding = "UTF-8";

		MultipartRequest multipartRequest = new MultipartRequest(request, directory, maxSize, encoding,
				new DefaultFileRenamePolicy());
		String fileName = multipartRequest.getOriginalFileName("file");
		String fileRealName = multipartRequest.getFilesystemName("file");
	%>
	
	<h1>파일명 : <%= fileName %></h1>
	<h1>실제 파일명 : <%=fileRealName %></h1>
</body>
</html>