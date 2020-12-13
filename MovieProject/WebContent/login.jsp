<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"></jsp:include>
<style>
.container {
	background-color: rgba(0,0,0,0.4);
}
</style>
<div class="container">
<!-- 헤더에서 로그인을 눌렀을떄 아이디 비밀번호를 입력할 화면을 띄울 페이지 -->
	<form action="/Login.do" method="post">
		<div class="login_container">
			<div class = "login">
				<div> 
					아이디 <input type="text" name="id" required id="id"> <!-- 아이디 -->
				</div>
				<div>
					비밀번호 <input type="password" name="password" required id="password"> <!-- 패스워드 -->
				</div>
				<input type="submit" value="로그인" class="button" id="login"> <!-- 버튼 -->
			</div>
		</div>
		<script type="text/javascript">
			login.addEventListener("click", function() {
				//버튼을 눌렀을때 
				if (id.value.trim() == "") {
					//id 가 비어있으면 alert 창 띄우기
					alert("아이디를 입력해주세요");
					return;
				}

				if (password.value.trim() == "") {
					//password 가 비어있으면 alert 창 띄우기
					alert("비밀번호를 입력해주세요");
					return;
				}
			});
		</script>
	</form>
</div>

<jsp:include page="footer.jsp"></jsp:include>