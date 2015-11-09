<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@include file="header.jsp"%>
<div class="container">
	<div class="wrapper">
		<div class="jumbotron">
			<h1>Device controller</h1>
			<p class="lead">Got a problem in the classroom? Report it just
				now!!! Our system will help you to report a problem to our technicians
				and fix it in the nearest time.</p>
			<hr>
			<security:authorize access="isAnonymous()">
				<p>Please log in to get started</p>
				<a class="btn btn-large btn-success" href="login">Log In</a>
			</security:authorize>
		</div>
	</div>
</div>
<%@include file="footer.jsp"%>
</body>
</html>