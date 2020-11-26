<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"></jsp:include>
<div class="container">
	<form action="/Register.do" style="width: 50%; margin: 0 auto;">
		<table align="center">
			<tr>
				<td>아이디 <input type="text" name="id" required></td>
			</tr>
			<tr>
				<td>비밀번호 <input type="password" name="password" required></td>
			</tr>
			<tr>
				<td>비밀번호 확인<input type="password" name="passwordC" required></td>
			</tr>
			<tr>
				<td>이메일 <input type="text" name="email" required></td>
			</tr>
			<tr>
				<td>전화번호 <input type="text" name="phone" required></td>
			</tr>
			<tr>
				<td>생일 <input type="date" name="birth" required></td>
			</tr>
			<tr>
				<td><input type="submit" value="회원가입" class="button"></td>
			</tr>
		</table>
	</form>
</div>
<jsp:include page="footer.jsp"></jsp:include>