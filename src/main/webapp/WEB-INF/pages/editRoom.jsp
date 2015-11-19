<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@include file="header.jsp"%>
<br>
<br>
<br>
<div class="panel panel-success">
	<div class="panel-heading">
		<h3 class="panel-title">Edit room: #${room.roomId}</h3>
	</div>
	<c:url var="saveUrl" value="/rooms/edit/id/${room.roomId}" />
	<form:form modelAttribute="room" method="POST" action="${saveUrl}">
		<table class="table table-striped table-bordered">
			<tr>
				<td><form:label path="roomNumber">Room Number:</form:label></td>
				<td><form:input type="text" class="form-control"
						path="roomNumber" required="true" /></td>
			</tr>
			<tr>
				<td><form:label for="technicianOptions" path="playerId">Technician:</form:label></td>
				<td><form:select path="playerId" id="technicianOptions"
						required="required">
						<form:options items="${technicians}" itemValue="playerId"
							itemLabel="fullName" />
					</form:select></td>
			</tr>
		</table>
		<form:input type="hidden" class="form-control" path="roomId"
			required="true" />
		<p style="text-align: center">
			<input type="submit" class="btn btn-success" value="Save" />
		</p>
	</form:form>
</div>
<div class="row">
	<div class="col-lg-7" style="margin-left: 10px;">
		<a type="submit" class="btn btn-success btn-sm" href="${room.roomId}/device/add">Add
			Device</a>
	</div>
</div>
<br/>
<div class="panel panel-success">
	<div class="panel-heading">
		<h3 class="panel-title">Devices of this room</h3>
	</div>
	<br>
	<table class="table table-striped table-bordered" id="pagination">
		<thead bgcolor="#8FBC8F">
			<tr>
				<th>ID</th>
				<th>Serial Number</th>
				<th>Device Type</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
		</thead>
		<c:forEach var="device" items="${roomDevices}">
			<tr>
				<td><c:out value="${device.deviceId}" /></td>
				<td><c:out value="${device.deviceSerialId}" /></td>
				<td><c:out value="${device.getDeviceTypeObj().deviceType}" /></td>
				<td><a href="../devices/edit/id/${device.deviceId}"
					id="${device.deviceId}"><span class="glyphicon glyphicon-edit"
						aria-hidden="true"></span></a></td>
				<td><a class="confirm"
					href="../devices/delete/id/${device.deviceId}" id="${device.deviceId}">
						<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
				</a></td>
			</tr>
		</c:forEach>
	</table>
</div>
<%@include file="footer.jsp"%>