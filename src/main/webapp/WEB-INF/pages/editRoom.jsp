<%@include file="header.jsp"%>

<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootbox.min.js"/>"></script>

<script>
	$(document).on("click", ".confirm", function(e) {
		e.preventDefault();
		var location = $(this).attr('href');
		bootbox.confirm("Delete this Device?", function(result) {
			if (result) {
				window.location.replace(location);
			}
		});
	});
</script>

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
		<h3 class="panel-title">Edit room: #${roomForm.roomId}</h3>
	</div>
	<c:url var="saveUrl" value="/rooms/edit/id/${roomForm.roomId}" />
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
								<c:forEach var="technician" items="${technicians}">
									<c:choose>
										<c:when
											test="${roomForm.getPlayerObj().getFullName()==technician.getFullName()}">
											<form:option selected="true" value="${technician.playerId}"
												label="${technician.getFullName()}" />
										</c:when>
										<c:otherwise>
											<form:option value="${technician.playerId}"
												label="${technician.getFullName()}" />
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</form:select>
							<form:errors path="playerId" class="control-label" />
						</div>
						<div class="col-sm-5"></div>
					</div>
				</spring:bind>
			</tr>
		</table>
		<form:hidden path="roomId" required="true" />
		<p style="text-align: center">
			<input type="submit" class="btn btn-success" value="Save" /> <input
				type="button" onclick="goBack()" class="btn btn-cancell"
				value="Go back" />
		</p>
	</form:form>
</div>

<c:if test="${not empty device}">
	<div class="msg">${device}</div>
</c:if>

<div class="row">
	<div class="col-lg-7" style="margin-left: 10px;">
		<a type="submit" class="btn btn-success btn-sm"
			href="${roomForm.roomId}/device/add">Add Device</a>
	</div>
</div>
<br />
<div class="panel panel-success">
	<div class="panel-heading">
		<h3 class="panel-title">Devices of this room</h3>
	</div>
	<br>
	<table class="table table-striped table-bordered" id="pagination">
		<thead bgcolor="#8FBC8F">
			<tr>
				<th>Serial Number</th>
				<th>Device Type</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
		</thead>
		<c:forEach var="device" items="${roomDevices}">
			<tr>
				<td><c:out value="${device.deviceSerialId}" /></td>
				<td><c:out value="${device.getDeviceTypeObj().deviceType}" /></td>
				<td><a
					href="<c:url value="/rooms/edit/id/${roomForm.roomId}/devices/edit/id/${device.deviceId}"/>"
					id="${device.deviceId}"><span class="glyphicon glyphicon-edit"
						aria-hidden="true"></span></a></td>
				<td><a class="confirm"
					href="<c:url value="/rooms/edit/id/${roomForm.roomId}/devices/delete/id/${device.deviceId}"/>"
					id="${device.deviceId}"> <span
						class="glyphicon glyphicon-trash" aria-hidden="true"></span>
				</a></td>
			</tr>
		</c:forEach>
	</table>
</div>
<%@include file="footer.jsp"%>