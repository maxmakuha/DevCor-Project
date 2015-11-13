<%@page session="true"%>
<%@include file="header.jsp"%>
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

<div align="center">
	<table border="1" cellpadding="5">
		<caption>
			<h2>List of users</h2>
		</caption>
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Surname</th>
			<th>Email</th>
			<th>Password</th>
			<th>Phone Number</th>
			<th>Role</th>
		</tr>
		<c:forEach var="user" items="${technicians}">
			<tr>
				<td><c:out value="${user.playerId}" /></td>
				<td><c:out value="${user.firstName}" /></td>
				<td><c:out value="${user.lastName}" /></td>
				<td><c:out value="${user.playerEmail}" /></td>
				<td><c:out value="${user.password}" /></td>
				<td><c:out value="${user.phoneNumber}" /></td>
				<td><c:out value="${user.role}" /></td>
			</tr>
		</c:forEach>
	</table>
</div>

<%@include file="footer.jsp"%>