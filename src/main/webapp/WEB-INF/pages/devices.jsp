<%@page session="true"%>
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
<div class="wrapper">
	<ul class="nav nav-tabs">
		<li role="presentation"><a href="technicians"
			style="color: GREEN">Technicians</a></li>
		<li role="presentation"><a href="users" style="color: GREEN">Users</a></li>
		<li role="presentation"><a href="rooms" style="color: GREEN">Rooms</a></li>
		<li role="presentation" class="active"><a href="devices"
			style="color: GREEN">Devices</a></li>
		<li role="presentation"><a href="reports" style="color: GREEN">Reports</a></li>
	</ul>
</div>
<div class="row">
	<div class="col-lg-7" style="margin-left: 10px;">
		<a type="submit" class="btn btn-success btn-sm" href="devices/add">Add
			Device</a>
	</div>
</div>
<br>
<div class="panel panel-success">
	<div class="panel-heading">
		<h3 class="panel-title">Devices</h3>
	</div>
	<br>
	<table class="table table-striped table-bordered" id="pagination">
		<thead bgcolor="#8FBC8F">
			<tr>
				<th>ID</th>
				<th>Serial Number</th>
				<th>Device Type</th>
				<th>Room Number</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
		</thead>
		<c:forEach var="device" items="${devices}">
			<tr>
				<td><c:out value="${device.deviceId}" /></td>
				<td><c:out value="${device.deviceSerialId}" /></td>
				<td><c:out value="${device.getDeviceTypeObj().deviceType}" /></td>
				<td><c:out value="${device.getRoomObj().roomNumber}" /></td>
				<td><a href="devices/edit/id/${device.deviceId}"
					id="${device.deviceId}"><span class="glyphicon glyphicon-edit"
						aria-hidden="true"></span></a></td>
				<td><a class="confirm"
					href="devices/delete/id/${device.deviceId}" id="${device.deviceId}">
						<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
				</a></td>
			</tr>
		</c:forEach>
	</table>
</div>
<%@include file="footer.jsp"%>