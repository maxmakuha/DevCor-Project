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
		<c:if
			test="${requestScope['javax.servlet.forward.request_uri'].contains('technicians')}">
			<c:url var="saveUrl" value="/technicians/add" />
			<div class="panel-heading">
				<h3 class="panel-title">Create new Technician:</h3>
			</div>
		</c:if>
		<c:if
			test="${requestScope['javax.servlet.forward.request_uri'].contains('users')}">
			<c:url var="saveUrl" value="/users/add" />
			<div class="panel-heading">
				<h3 class="panel-title">Create new User:</h3>
			</div>
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
					<spring:bind path="password">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">Password:</label>
							<div class="col-sm-10">
								<form:input path="password" type="password" class="form-control"
									id="password" placeholder="Password" required="true"
									maxlength="64" />
								<form:errors path="password" class="control-label" />
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
			<p style="text-align: center">
				<input type="submit" class="btn btn-success" value="Add" /> <input
					type="button" onclick="goBack()" class="btn btn-cancell"
					value="Go back" />
			</p>
		</form:form>
	</div>
</div>
<%@include file="footer.jsp"%>