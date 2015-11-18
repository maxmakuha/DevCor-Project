<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@include file="header.jsp"%>
<br>
<br>
<br>
<div class="panel panel-success">
	<div class="panel-heading">
		<h3 class="panel-title">Create new Room:</h3>
	</div>
	<c:url var="saveUrl" value="/rooms/add" />
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
						<option value="">-- Please choose one</option>
						<form:options items="${technicians}" itemValue="playerId"
							itemLabel="fullName" />
					</form:select></td>
			</tr>
		</table>
		<p style="text-align: center">
			<input type="submit" class="btn btn-success" value="Add" />
		</p>
	</form:form>
</div>
<%@include file="footer.jsp"%>