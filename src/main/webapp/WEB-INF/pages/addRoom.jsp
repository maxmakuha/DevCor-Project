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
			<h3 class="panel-title">Create new Room:</h3>
		</div>
		<c:url var="saveUrl" value="/rooms/add" />
		<form:form modelAttribute="roomForm" method="POST" action="${saveUrl}">
			<table class="table table-striped table-bordered">
				<tr>
					<spring:bind path="roomNumber">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">Room number:</label>
							<div class="col-sm-10">
								<form:input path="roomNumber" type="text" class="form-control"
									id="roomNumber" placeholder="Room number" required="true"
									maxlength="16" />
								<form:errors path="roomNumber" class="control-label" />
							</div>
						</div>
						<br>
						<br>
						<br>
					</spring:bind>
				</tr>
				<tr>
					<spring:bind path="playerId">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">Technician:</label>
							<div class="col-sm-5">
								<form:select path="playerId" class="form-control"
									required="required">
									<form:option value="0" label="--- Select ---" />
									<form:options items="${technicians}" itemValue="playerId"
										itemLabel="fullName" />
								</form:select>
								<form:errors path="playerId" class="control-label" />
							</div>
							<div class="col-sm-5"></div>
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