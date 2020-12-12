<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String id = (String) request.getAttribute("id");
if (id != null) {
	out.print("<script>alert('" + id + "님이 로그인 하셨습니다');</script>");
	session.setAttribute("id", id);
}
String logout = (String) request.getAttribute("logout");
if (logout != null) {
	out.print("<script>alert('" + logout + "');</script>");
	session.removeAttribute("id");
}
String error = (String) request.getAttribute("error");
if (error != null) {
	out.print("<script>alert('" + error + "');</script>");
}
String ok = (String) request.getAttribute("ok");
if(ok != null) {
	out.print("<script>alert('" + ok + "');</script>");
}
%>
<script type="text/javascript">
	location.href = "/AllMovieList.do";
</script>