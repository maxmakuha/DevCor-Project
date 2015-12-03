<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@include file="header.jsp"%>

<script>
	function goBack() {
		window.history.back();
	}
</script>

<br>
<br>
<br>
<div class="container">
	<div class="panel panel-success">
		<div class="panel-heading">
			<h3 class="panel-title">Edit profile:</h3>
		</div>
		<c:url var="saveUrl" value="/profile" />
		<form:form modelAttribute="playerForm" method="POST"
			action="${saveUrl}">
			<table class="table table-striped table-bordered">
				<security:authorize
					access="hasAnyRole('ROLE_TECHNICIAN','ROLE_USER')">
					<tr>
						<td><label>Name:</label></td>
						<td><label>${playerForm.firstName}</label></td>
						<form:hidden path="firstName" value="${playerForm.firstName}"
							required="true" />
					</tr>
					<tr>
						<td><label>Surname:</label></td>
						<td><label>${playerForm.lastName}</label></td>
						<form:hidden path="lastName" value="${playerForm.lastName}"
							required="true" />
					</tr>
				</security:authorize>
				<security:authorize access="hasRole('ROLE_ADMIN')">
					<tr>
						<spring:bind path="firstName">
							<div class="form-group ${status.error ? 'has-error' : ''}">
								<label class="col-sm-2 control-label">Name:</label>
								<div class="col-sm-10">
									<form:input path="firstName" type="text" class="form-control"
										id="firstName" placeholder="Name" required="true"
										maxlength="32" />
									<form:errors path="firstName" class="control-label" />
									<br>
								</div>
							</div>
						</spring:bind>
					</tr>
					<tr>
						<spring:bind path="lastName">
							<div class="form-group ${status.error ? 'has-error' : ''}">
								<label class="col-sm-2 control-label">Surname:</label>
								<div class="col-sm-10">
									<form:input path="lastName" type="text" class="form-control"
										id="lastName" placeholder="Surname" required="true"
										maxlength="32" />
									<form:errors path="lastName" class="control-label" />
									<br>
								</div>
							</div>
						</spring:bind>
					</tr>
				</security:authorize>
				<tr>
					<spring:bind path="playerEmail">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">Email:</label>
							<div class="col-sm-10">
								<form:input path="playerEmail" type="email" class="form-control"
									id="playerEmail" placeholder="Email" required="true"
									maxlength="64" />
								<form:errors path="playerEmail" class="control-label" />
								<br>
							</div>
						</div>
					</spring:bind>
				</tr>
				<tr>
					<spring:bind path="password">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">Password:</label>
							<div class="col-sm-10">
								<form:password path="password" class="form-control"
									id="password" placeholder="Password" required="true"
									maxlength="64" />
								<form:errors path="password" class="control-label" />
							</div>
						</div>
					</spring:bind>
				</tr>
				<tr>
					<spring:bind path="newPassword">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">New password:</label>
							<div class="col-sm-10">
								<form:password path="newPassword" class="form-control"
									id="newPassword" placeholder="New password" maxlength="64" />
								<form:errors path="newPassword" class="control-label" />
							</div>
						</div>
					</spring:bind>
				</tr>
				<tr>
					<spring:bind path="confirmPassword">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">Confirm password:</label>
							<div class="col-sm-10">
								<form:password path="confirmPassword" class="form-control"
									id="confirmPassword" placeholder="Confirm password"
									maxlength="64" />
								<form:errors path="confirmPassword" class="control-label" />
							</div>
						</div>
					</spring:bind>
				</tr>
				<tr>
					<spring:bind path="phoneNumber">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">Phone number:</label>
							<div class="col-sm-10">
								<form:input path="phoneNumber" type="number"
									class="form-control" id="phoneNumber"
									placeholder="Phone Number" required="true" maxlength="15" />
								<form:errors path="phoneNumber" class="control-label" />
								<br>
							</div>
						</div>
					</spring:bind>
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
</div>

<c:if test="${not empty match}">
	<div class="error">${match}</div>
</c:if>

<%@include file="footer.jsp"%>