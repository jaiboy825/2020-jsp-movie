<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String id = (String) request.getAttribute("id");
//id 받기
if (id != null) {
	//id가 null이 아니라면 alert 띄우기
	out.print("<script>alert('" + id + "님이 로그인 하셨습니다');</script>");
	session.setAttribute("id", id); //세션에 로그인 시키기
}
String logout = (String) request.getAttribute("logout"); //logout 받기
if (logout != null) {
	//null 아니라면 alert 띄우기
	out.print("<script>alert('" + logout + "');</script>");
	session.removeAttribute("id"); //세션에서 id 삭제하기
}
String success = (String) request.getAttribute("success"); //success 받기
if (success != null) {
	//null 아니라면 alert 띄우기
	out.print("<script>alert('" + success + "');</script>");
}

String error = (String) request.getAttribute("error"); //error 받기
if (error != null) {
	//null 아니라면 alert 띄우기
	out.print("<script>alert('" + error + "');</script>");
}
String ok = (String) request.getAttribute("ok"); // ok 받기
if (ok != null) {
	//null 아니라면 alert 띄우기
	out.print("<script>alert('" + ok + "');</script>");
}
%>
<script type="text/javascript">
	location.href = "/AllMovieList.do";
	/* 모든 작업 후 /AllMovieList.do로 보낸다 */
</script>