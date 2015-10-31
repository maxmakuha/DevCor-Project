<%@include file="header.jsp"%>
<div class = "container">
	<div class="wrapper">
		<p class="lead">
			The system shall allow user to log in using email and password.
			The system shall not allow creating objects (Order, Administrator, Technician, User, Roomand Device) with emptyrequired parameters.
			<br>The system shall allow user to interact with it using one of the following roles: 
		</p>
		<ul>
			<li>Administrator</li>
			<li>Technician</li>
			<li>User</li>
		</ul>
		<p class="lead">
			<br>The system shall automatically delete Order after 5 years from the date of Finished Execution status. 
			The system shall allow user to log out system.
		</p>
		<img src="img/2.PNG" class="img-rounded">
	</div>
</div>
<%@include file="footer.jsp"%>
</body>
</html>