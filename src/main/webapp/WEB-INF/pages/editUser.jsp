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
			<h3 class="panel-title">Edit profile: #${playerForm.playerId}</h3>
		</div>
		<c:if test="${playerForm.getRoleId()==2}">
			<c:url var="saveUrl"
				value="/technicians/edit/id/${playerForm.playerId}" />
		</c:if>
		<c:if test="${playerForm.getRoleId()==3}">
			<c:url var="saveUrl" value="/users/edit/id/${playerForm.playerId}" />
		</c:if>
		<form:form modelAttribute="playerForm" method="POST"
			action="${saveUrl}">
			<table class="table table-striped table-bordered">
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
							</div>
						</div>
					</spring:bind>
				</tr>
			</table>
			<form:hidden path="password" required="true" />
			<form:hidden path="playerId" required="true" />
			<form:hidden path="roleId" required="true" />
			<p style="text-align: center">
				<input type="submit" class="btn btn-success" value="Save" /> <input
					type="button" onclick="goBack()" class="btn btn-cancell"
					value="Go back" />
			</p>
		</form:form>
	</div>
</div>
<%@include file="footer.jsp"%>