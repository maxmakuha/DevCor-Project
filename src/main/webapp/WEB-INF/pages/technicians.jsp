<%@page session="true"%>
<%@include file="header.jsp"%>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootbox.min.js"/>"></script>
<script>
	$(document).on("click", ".confirm", function(e) {
		e.preventDefault();
		var location = $(this).attr('href');
		bootbox.confirm("Delete this Technician?", function(result) {
			if (result) {
				window.location.replace(location);
			}
		});
	});
</script>
<div class="wrapper">
	<ul class="nav nav-tabs">
		<li role="presentation" class="active"><a
			href="<c:url value="/technicians"/>" style="color: GREEN">Technicians</a></li>
		<li role="presentation"><a href="<c:url value="/users"/>"
			style="color: GREEN">Users</a></li>
		<li role="presentation"><a href="<c:url value="/rooms"/>"
			style="color: GREEN">Rooms</a></li>
		<li role="presentation"><a href="<c:url value="/devices"/>"
			style="color: GREEN">Devices</a></li>
		<li role="presentation"><a href="<c:url value="/reports"/>"
			style="color: GREEN">Reports</a></li>
		<li role="presentation"><a href="<c:url value="/configuration"/>"
			style="color: GREEN">Configuration panel</a></li>
	</ul>
</div>

<c:if test="${not empty tech}">
	<div class="msg">${tech}</div>
</c:if>

<c:if test="${not empty error}">
	<div class="error">${error}</div>
</c:if>

<div class="row">
	<div class="col-lg-7" style="margin-left: 10px;">
		<a type="submit" class="btn btn-success btn-sm" href="technicians/add">Add
			Technician</a>
	</div>
</div>
<br>
<div class="panel panel-success">
	<div class="panel-heading">
		<h3 class="panel-title">Technicians</h3>
	</div>
	<br>
	<table class="table table-striped table-bordered" id="pagination">
		<thead bgcolor="#8FBC8F">
			<tr>
				<th>Name</th>
				<th>Surname</th>
				<th>Email</th>
				<th>Phone Number</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
		</thead>
		<c:forEach var="technician" items="${technicians}">
			<tr>
				<td><c:out value="${technician.firstName}" /></td>
				<td><c:out value="${technician.lastName}" /></td>
				<td><c:out value="${technician.playerEmail}" /></td>
				<td><c:out value="${technician.phoneNumber}" /></td>
				<td><a href="technicians/edit/id/${technician.playerId}"
					id="${technician.playerId}"><span
						class="glyphicon glyphicon-edit" aria-hidden="true"></span></a></td>
				<td><a class="confirm"
					href="technicians/delete/id/${technician.playerId}"
					id="${technician.playerId}"> <span
						class="glyphicon glyphicon-trash" aria-hidden="true"></span>
				</a></td>
			</tr>
		</c:forEach>
	</table>
</div>
<%@include file="footer.jsp"%>