<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"></jsp:include>
<style>
.container {
	background-color: rgba(0,0,0,0.4);
}

</style>
<%
String error = (String) request.getAttribute("error");
//error 받기
if (error != null) {
	//null 이 아니라면 alert 창 띄우기
	out.print("<script>alert('" + error + "');</script>");
}
%>
<div class="container">
<!-- 헤더에서 회원가입을 눌렀을때 회원가입에 필요한 정보들을 입력하는 페이지 -->
	<form action="/Register.do"  method="post">
		<div class="register_container">
			<div class="register">
				<div>아이디 <input type="text" name="id" required id="id"></div> <!-- 아이디 -->
				<div>비밀번호 <input type="password" name="password" required id="password"></div> <!-- 비밀번호 -->
				<div>비밀번호 확인<input type="password" name="passwordC" required id="passwordC"></div> <!-- 비밀번호 확인 -->
				<div>이메일 <input type="text" name="email" required id="email"></div> <!-- 이메일 -->
				<div>전화번호 <input type="text" name="phone" required id="phone"></div> <!-- 전화번호  -->
				<div>생일 <input type="date" name="birth" required id="birth"></div> <!-- 생일 -->
				<input type="submit" value="회원가입" class="button" id="register"> <!-- 버튼 -->
			</div>
		</div>
		<script type="text/javascript">
			register.addEventListener("click", function() {
				/* 버튼을 눌렀을때  */
				if (id.value.trim() == "") {
					/* 아이디가 비었다면 alert 띄우기 */
					alert("아이디를 입력해주세요");
					return;
				}
				
				if(password.value.trim() == "") {
					/* 비밀번호가 비었다면 alert 띄우기 */
					alert("비밀번호를 입력해주세요");
					return;
				}
				if(passwordC.value.trim() == "") {
					/* 비밀번호 확인이 비었다면 alert 띄우기 */
					alert("비밀번호 확인란을 입력해주세요");
					return;
				}				
				
				if(email.value.trim() == "") {
					/* 이메일이 비었다면 alert 띄우기 */
					alert("이메일을 입력해주세요");
					return;
				}
				if(phone.value.trim() == ""){
					/* 전화번호가 비었다면 alert 띄우기 */
					alert("전화번호를 입력해주세요");
					return;
				}
				if(birth.value.trim() == ""){
					/* 생일이 비었다면 alert 띄우기 */
					alert("생일을 입력해주세요");
					return;
				}
			});
		</script>
	</form>
</div>
<jsp:include page="footer.jsp"></jsp:include>