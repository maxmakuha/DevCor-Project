<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@include file="header.jsp"%>

<script>
	function goBack() {
		window.history.back();
	}
</script>

<br>
<br>
<br>
<div class="panel panel-success">
	<div class="panel-heading">
		<h3 class="panel-title">Edit profile:</h3>
	</div>
	<c:url var="saveUrl" value="/profile" />
	<form:form modelAttribute="profile" method="POST" action="${saveUrl}">
		<table class="table table-striped table-bordered">
			<security:authorize access="hasRole('ROLE_ADMIN')">
				<tr>
					<td><form:label path="firstName">First Name:</form:label></td>
					<td><form:input type="text" class="form-control"
							path="firstName" required="true" /></td>
				</tr>
				<tr>
					<td><form:label path="lastName">Surname:</form:label></td>
					<td><form:input type="text" class="form-control"
							path="lastName" required="true" /></td>
				</tr>
			</security:authorize>
			<security:authorize
				access="hasAnyRole('ROLE_TECHNICIAN','ROLE_USER')">
				<tr>
					<td><label>First Name:</label></td>
					<td><label>${profile.firstName}</label></td>
					<form:hidden path="firstName" value="${profile.firstName}"
						required="true" />
				</tr>
				<tr>
					<td><label>Surname:</label></td>
					<td><label>${profile.lastName}</label></td>
					<form:hidden path="lastName" value="${profile.lastName}"
						required="true" />
				</tr>
			</security:authorize>
			<tr>
				<td><form:label path="playerEmail">Email:</form:label></td>
				<td><form:input type="email" class="form-control"
						path="playerEmail" required="true" /></td>
			</tr>
			<tr>
				<td><form:label path="password">Old Password:</form:label></td>
				<td><form:password path="password" required="true" /></td>
			</tr>
			<tr>
				<td><form:label path="newPassword">New Password:</form:label></td>
				<td><form:password path="newPassword" /></td>
			</tr>
			<tr>
				<td><form:label path="confirmPassword">Confirm Password:</form:label></td>
				<td><form:password path="confirmPassword" /></td>
			</tr>
			<tr>
				<td><form:label path="phoneNumber">Phone number:</form:label></td>
				<td><form:input type="number" class="form-control"
						path="phoneNumber" required="true" /></td>
			</tr>
		</table>
		<form:hidden path="roleId" required="true" />
		<form:hidden path="playerId" required="true" />
		<p style="text-align: center">
			<input type="submit" class="btn btn-success" value="Save" /> <input
				type="button" onclick="goBack()" class="btn btn-cancell"
				value="Go back" />
		</p>
	</form:form>
</div>

<c:if test="${not empty unique}">
	<div class="error">${unique}</div>
</c:if>

<%@include file="footer.jsp"%>