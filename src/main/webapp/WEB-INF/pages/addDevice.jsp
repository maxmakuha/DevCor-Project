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
			<h3 class="panel-title">Add new device:</h3>
		</div>
		<c:url var="saveUrl" value="/devices/add" />
		<form:form modelAttribute="deviceForm" method="POST"
			action="${saveUrl}">
			<table class="table table-striped table-bordered">
				<tr>
					<spring:bind path="deviceSerialId">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">Serial number:</label>
							<div class="col-sm-10">
								<form:input path="deviceSerialId" type="text"
									class="form-control" id="deviceSerialId"
									placeholder="Serial number" required="true" maxlength="15" />
								<form:errors path="deviceSerialId" class="control-label" />
							</div>
						</div>
						<br>
						<br>
						<br>
					</spring:bind>
				</tr>
				<tr>
					<spring:bind path="deviceTypeId">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">Device type:</label>
							<div class="col-sm-5">
								<form:select path="deviceTypeId" class="form-control"
									required="required">
									<form:option value="0" label="--- Select ---" />
									<form:options items="${deviceTypes}" itemValue="deviceTypeId"
										itemLabel="deviceType" />
								</form:select>
								<form:errors path="deviceTypeId" class="control-label" />
							</div>
							<div class="col-sm-5"></div>
						</div>
						<br>
						<br>
					</spring:bind>
				</tr>
				<tr>
					<spring:bind path="roomId">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">Room number:</label>
							<div class="col-sm-5">
								<form:select path="roomId" class="form-control"
									required="required">
									<form:option value="0" label="--- Select ---" />
									<form:options items="${rooms}" itemValue="roomId"
										itemLabel="roomNumber" />
								</form:select>
								<form:errors path="roomId" class="control-label" />
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