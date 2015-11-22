<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@include file="header.jsp"%>
<br>
<br>
<br>
<div class="panel panel-success">
	<div class="panel-heading">
		<h3 class="panel-title">Edit profile: #${user.playerId}</h3>
	</div>
	<c:if test="${user.getRoleId()==2}">
		<c:url var="saveUrl" value="/technicians/edit/id/${user.playerId}" />
	</c:if>
	<c:if test="${user.getRoleId()==3}">
		<c:url var="saveUrl" value="/users/edit/id/${user.playerId}" />
	</c:if>
	<form:form modelAttribute="user" method="POST" action="${saveUrl}">
		<table class="table table-striped table-bordered">
			<tr>
				<td><form:label path="firstName">First Name:</form:label></td>
				<td><form:input type="text" class="form-control"
						path="firstName" required="true" maxlength="32" /></td>
			</tr>
			<tr>
				<td><form:label path="lastName">Surname:</form:label></td>
				<td><form:input type="text" class="form-control"
						path="lastName" required="true" maxlength="32" /></td>
			</tr>
			<tr>
				<td><form:label path="playerEmail">Email:</form:label></td>
				<td><form:input type="email" class="form-control"
						path="playerEmail" required="true" maxlength="64" /></td>
			</tr>
			<tr>
				<td><form:label path="password">Password:</form:label></td>
				<td><form:input type="text" class="form-control"
						path="password" required="true" maxlength="64" /></td>
			</tr>
			<tr>
				<td><form:label path="phoneNumber">Phone number:</form:label></td>
				<td><form:input type="number" class="form-control"
						path="phoneNumber" required="true" maxlength="15" /></td>
			</tr>
		</table>
		<form:input type="hidden" class="form-control" path="playerId"
			required="true" />
		<form:input type="hidden" class="form-control" path="roleId"
			required="true" />
		<p style="text-align: center">
			<input type="submit" class="btn btn-success" value="Save" />
		</p>
	</form:form>
</div>
<%@include file="footer.jsp"%>