<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@include file="header.jsp"%>
<br>
<br>
<br>
<div class="panel panel-success">
	<c:if test="${role==2}">
		<c:url var="saveUrl" value="/technicians/add" />
		<div class="panel-heading">
			<h3 class="panel-title">Create new Technician:</h3>
		</div>
	</c:if>
	<c:if test="${role==3}">
		<c:url var="saveUrl" value="/users/add" />
		<div class="panel-heading">
			<h3 class="panel-title">Create new User:</h3>
		</div>
	</c:if>
	<form:form modelAttribute="user" method="POST" action="${saveUrl}">
		<table class="table table-striped table-bordered">
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
			<tr>
				<td><form:label path="playerEmail">Email:</form:label></td>
				<td><form:input type="email" class="form-control"
						path="playerEmail" required="true" /></td>
			</tr>
			<tr>
				<td><form:label path="password">Password:</form:label></td>
				<td><form:input type="text" class="form-control"
						path="password" required="true" /></td>
			</tr>
			<tr>
				<td><form:label path="phoneNumber">Phone number:</form:label></td>
				<td><form:input type="number" class="form-control"
						path="phoneNumber" required="true" /></td>
			</tr>
		</table>
		<p style="text-align: center">
			<input type="submit" class="btn btn-success" value="Add" />
		</p>
	</form:form>
</div>
<%@include file="footer.jsp"%>