<%@page session="true"%>
<%@include file="header.jsp"%>
<script src="resources/js/bootbox.min.js"></script>
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
		<li role="presentation" class="active"><a href="technicians"
			style="color: GREEN">Technicians</a></li>
		<li role="presentation"><a href="users" style="color: GREEN">Users</a></li>
		<li role="presentation"><a href="rooms" style="color: GREEN">Rooms</a></li>
		<li role="presentation"><a href="devices" style="color: GREEN">Devices</a></li>
		<li role="presentation"><a href="reports" style="color: GREEN">Reports</a></li>
	</ul>
</div>
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
				<th>ID</th>
				<th>Name</th>
				<th>Surname</th>
				<th>Email</th>
				<th>Password</th>
				<th>Phone Number</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
		</thead>
		<c:forEach var="technician" items="${technicians}">
			<tr>
				<td><c:out value="${technician.playerId}" /></td>
				<td><c:out value="${technician.firstName}" /></td>
				<td><c:out value="${technician.lastName}" /></td>
				<td><c:out value="${technician.playerEmail}" /></td>
				<td><c:out value="${technician.password}" /></td>
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