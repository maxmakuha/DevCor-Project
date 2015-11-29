<%@page session="true"%>
<%@include file="header.jsp"%>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootbox.min.js"/>"></script>
<script>
	$(document).on("click", ".confirm", function(e) {
		e.preventDefault();
		var location = $(this).attr('href');
		bootbox.confirm("Delete this User?", function(result) {
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
		<li role="presentation" class="active"><a
			href="<c:url value="/users"/>" style="color: GREEN">Users</a></li>
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

<c:if test="${not empty user}">
	<div class="msg">${user}</div>
</c:if>

<div class="row">
	<div class="col-lg-7" style="margin-left: 10px;">
		<a type="submit" class="btn btn-success btn-sm" href="users/add">Add
			User</a>
	</div>
</div>
<br>
<div class="panel panel-success">
	<div class="panel-heading">
		<h3 class="panel-title">Users</h3>
	</div>
	<br>
	<table class="table table-striped table-bordered" id="pagination">
		<thead bgcolor="#8FBC8F">
			<tr>
				<th>Name</th>
				<th>Surname</th>
				<th>Email</th>
				<th>Password</th>
				<th>Phone Number</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
		</thead>
		<c:forEach var="user" items="${users}">
			<tr>
				<td><c:out value="${user.firstName}" /></td>
				<td><c:out value="${user.lastName}" /></td>
				<td><c:out value="${user.playerEmail}" /></td>
				<td><c:out value="${user.password}" /></td>
				<td><c:out value="${user.phoneNumber}" /></td>
				<td><a href="users/edit/id/${user.playerId}"
					id="${user.playerId}"><span class="glyphicon glyphicon-edit"
						aria-hidden="true"></span></a></td>
				<td><a class="confirm" href="users/delete/id/${user.playerId}"
					id="${user.playerId}"> <span class="glyphicon glyphicon-trash"
						aria-hidden="true"></span>
				</a></td>
			</tr>
		</c:forEach>
	</table>
</div>
<%@include file="footer.jsp"%>