<%@include file="header.jsp"%>
<div class="container">
	<div class="wrapper">

		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty logout}">
			<div class="msg">${logout}</div>
		</c:if>

		<form name='login' action="j_spring_security_check" method='POST'
			class="form-signin">
			<h3 class="form-signin-heading">Sign in to get started</h3>
			<hr class="colorgraph">
			<br> <input type="text" class="form-control" name="username"
				placeholder="username" /> <input type="password"
				class="form-control" name="password" placeholder="password" />
			<button class="btn btn-large btn-success btn-block" name="Submit"
				value="Login" type="Submit">Login</button>
			<br> <input id="remember_me" name="_spring_security_remember_me"
				type="checkbox" value="true" /> <label for="remember_me"
				class="inline">Remember me</label>
		</form>
	</div>
</div>
<%@include file="footer.jsp"%>
