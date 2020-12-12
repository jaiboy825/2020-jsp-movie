<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"></jsp:include>
<style>
.container {
	width: 500px;
	height: 500px;
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
<%
String error = (String) request.getAttribute("error");
if (error != null) {
	out.print("<script>alert('" + error + "');</script>");
}
%>
<div class="container">
	<form action="/Register.do"  method="post">
		<table align="center">
			<tr>
				<td>아이디 <input type="text" name="id" required id="id"></td>
			</tr>
			<tr>
				<td>비밀번호 <input type="password" name="password" required id="password"></td>
			</tr>
			<tr>
				<td>비밀번호 확인<input type="password" name="passwordC" required id="passwordC"></td>
			</tr>
			<tr>
				<td>이메일 <input type="text" name="email" required id="email"></td>
			</tr>
			<tr>
				<td>전화번호 <input type="text" name="phone" required id="phone"></td>
			</tr>
			<tr>
				<td>생일 <input type="date" name="birth" required id="birth"></td>
			</tr>
			<tr>
				<td><input type="submit" value="회원가입" class="button" id="register"></td>
			</tr>
		</table>
		<script type="text/javascript">
			register.addEventListener("click", function() {
				if (id.value.trim() == "") {
					alert("아이디를 입력해주세요");
					return;
				}
				
				if(password.value.trim() == "") {
					alert("비밀번호를 입력해주세요");
					return;
				}
				if(passwordC.value.trim() == "") {
					alert("비밀번호 확인란을 입력해주세요");
					return;
				}				
				
				if(email.value.trim() == "") {
					alert("이메일을 입력해주세요");
					return;
				}
				if(phone.value.trim() == ""){
					alert("전화번호를 입력해주세요");
					return;
				}
				if(birth.value.trim() == ""){
					alert("생일을 입력해주세요");
					return;
				}
			});
		</script>
	</form>
</div>
<jsp:include page="footer.jsp"></jsp:include>