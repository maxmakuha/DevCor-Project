<%@page session="true"%>
<%@include file="header.jsp"%>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootbox.min.js"/>"></script>
<script>
	$(document).on("click", ".confirm", function(e) {
		e.preventDefault();
		var location = $(this).attr('href');
		bootbox.confirm("Delete this Room? <br> All devices placed in this room will be deleted!!!", function(result) {
			if (result) {
				window.location.replace(location);
			}
		});
	});
</script>
<div class="wrapper">
	<ul class="nav nav-tabs">
		<li role="presentation"><a href="<c:url value="/technicians"/>"
			style="color: GREEN">Technicians</a></li>
		<li role="presentation"><a href="<c:url value="/users"/>" style="color: GREEN">Users</a></li>
		<li role="presentation" class="active"><a href="<c:url value="/rooms"/>" style="color: GREEN">Rooms</a></li>
		<li role="presentation"><a href="<c:url value="/devices"/>"
			style="color: GREEN">Devices</a></li>
		<li role="presentation"><a href="<c:url value="/reports"/>" style="color: GREEN">Reports</a></li>
		<li role="presentation"><a href="<c:url value="/configuration"/>" style="color: GREEN">Configuration panel</a></li>
	</ul>
</div>

<c:if test="${not empty room}">
	<div class="msg">${room}</div>
</c:if>

<div class="row">
	<div class="col-lg-7" style="margin-left: 10px;">
		<a type="submit" class="btn btn-success btn-sm" href="rooms/add">Add
			Room</a>
	</div>
</div>
<br>
<div class="panel panel-success">
	<div class="panel-heading">
		<h3 class="panel-title">Rooms</h3>
	</div>
	<br>
	<table class="table table-striped table-bordered" id="pagination">
		<thead bgcolor="#8FBC8F">
			<tr>
				<th>Room Number</th>
				<th>Technician</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
		</thead>
		<c:forEach var="room" items="${rooms}">
			<tr>
				<td><c:out value="${room.roomNumber}" /></td>
				<td><c:out value="${room.getPlayerObj().getFullName()}" /></td>
				<td><a href="rooms/edit/id/${room.roomId}"
					id="${room.roomId}"><span class="glyphicon glyphicon-edit"
						aria-hidden="true"></span></a></td>
				<td><a class="confirm"
					href="rooms/delete/id/${room.roomId}" id="${room.roomId}">
						<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
				</a></td>
			</tr>
		</c:forEach>
	</table>
</div>
<%@include file="footer.jsp"%>