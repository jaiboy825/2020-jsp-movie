<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"></jsp:include>
<style>
.container {
	width: 500px;
	height: 200px;
	margin: 30px auto;
	border: 1px solid rgba(0, 0, 0, 0.25);
	border-radius: 10px;
	padding: 20px;
}

table {
	width: 100%;
}

tr td {
	padding: 5px;
}

tr>td {
	width: 100%;
	height: 50px;
	display: flex;
	justify-content: space-between;
	line-height: 50px;
}

td>input {
	padding-left : 20px;
	width: 70%;
	height: 50px;
	outline: none;
}

.button {
	padding: 0;
	margin-top: 20px;
	width: 100%;
}
</style>
<div class="container">
	<form action="/Login.do" method="post">
		<table align="center">
			<tr>
				<td>아이디 <input type="text" name="id" required></td>
			</tr>
			<tr>
				<td>비밀번호 <input type="password" name="password" required></td>
			</tr>
			<tr>
				<td><input type="submit" value="로그인" class="button"></td>
			</tr>
		</table>
	</form>
</div>
<jsp:include page="footer.jsp"></jsp:include>