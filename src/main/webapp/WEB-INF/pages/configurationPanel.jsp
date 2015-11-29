<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page session="true"%>
<%@include file="header.jsp"%>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootbox.min.js"/>"></script>
<script>
function wordForm(singular, amount){
	switch(amount){
		case 0:
			return "";
		case 1:
			return amount + ' ' + singular + ' ';
		default:
			return amount + ' ' + singular + 's ';
	}
}

function formatTimeInterval(m) {
	var minutes = m % 60;
	var hours = Math.floor(m / 60) % 24;
	var days  = Math.floor(m / (60 * 24));
	return wordForm("day", days) + wordForm("hour", hours) + wordForm("minute", minutes);
}

$(document).ready(function(){
	
	$(".timeInterval").html(function(i, minutes){
		return formatTimeInterval(minutes);
	});
	
	$(".days-hours-minutes div:nth-child(1) input").val(
			Math.floor($(".days-hours-minutes input[type='hidden']").val() / (60 * 24)));
	
	$(".days-hours-minutes div:nth-child(2) input").val(
			Math.floor(($(".days-hours-minutes input[type='hidden']").val() / 60) % 24));
	
	$(".days-hours-minutes div:nth-child(3) input").val(
			Math.floor($(".days-hours-minutes input[type='hidden']").val() % 60));
	
	$(document).on("click", ".problemTypeConfirm", function(e) {
		e.preventDefault();
		var location = $(this).attr('href');
		bootbox.confirm("Delete this problem type?", function(result) {
			if (result) {
				window.location.replace(location);
			}
		});
	});
	$(document).on("click", ".deviceTypeconfirm", function(e) {
		e.preventDefault();
		var location = $(this).attr('href');
		bootbox.confirm("Delete this device type?", function(result) {
			if (result) {
				window.location.replace(location);
			}
		});
	});
	$(document).on("click", ".urgStatusConfirm", function(e) {
		e.preventDefault();
		var location = $(this).attr('href');
		bootbox.confirm("Delete this urgency status?", function(result) {
			if (result) {
				window.location.replace(location);
			}
		});
	});
	
	$("#urgStatus input[type='submit']").click(function(){
		$(".days-hours-minutes input[type='hidden']").val(
				$(".days-hours-minutes div:nth-child(1) input").val() * 60 * 24 +
				$(".days-hours-minutes div:nth-child(2) input").val() * 60 +
				$(".days-hours-minutes div:nth-child(3) input").val() * 1);
	});
});
</script>

<div class="wrapper">
	<ul class="nav nav-tabs">
		<li role="presentation"><a href="technicians"
			style="color: GREEN">Technicians</a></li>
		<li role="presentation"><a href="<c:url value="/users"/>"
			style="color: GREEN">Users</a></li>
		<li role="presentation"><a href="<c:url value="/rooms"/>"
			style="color: GREEN">Rooms</a></li>
		<li role="presentation"><a href="<c:url value="/devices"/>"
			style="color: GREEN">Devices</a></li>
		<li role="presentation"><a href="<c:url value="/reports"/>"
			style="color: GREEN">Reports</a></li>
		<li role="presentation" class="active"><a
			href="<c:url value="/configuration"/>" style="color: GREEN">Configuration
				panel</a></li>
	</ul>

	<Hr>
	<div id="panel">

		<c:if test="${action == 'editProblemType'}">
			<c:url var="saveUrl"
				value="/configuration/problemType/edit/id/${problem.problemTypeId}" />
			<form:form modelAttribute="problem" method="POST" action="${saveUrl}">
				<table class="table table-striped table-bordered">
					<tr>
						<td><form:label path="problemType">Title:</form:label></td>
						<td><form:input type="text" class="form-control"
								path="problemType" required="true" maxlength="20" /></td>
						<td><input type="submit" class="btn btn-success" value="Save" /></td>
					</tr>
				</table>
				<form:input type="hidden" class="form-control" path="problemTypeId"
					required="true" />
			</form:form>
		</c:if>


		<c:if test="${action == 'addProblemType'}">
			<c:url var="saveUrl" value="/configuration/problemType/add" />
			<form:form modelAttribute="problem" method="POST" action="${saveUrl}">
				<table class="table table-striped table-bordered">
					<tr>
						<td><form:label path="problemType">Title:</form:label></td>
						<td><form:input type="text" class="form-control"
								path="problemType" required="true" maxlength="20" /></td>
						<td><input type="submit" class="btn btn-success" value="Add" /></td>
					</tr>
				</table>
			</form:form>
		</c:if>

		<c:if test="${action == 'editDeviceType'}">
			<c:url var="saveUrl"
				value="/configuration/deviceType/edit/id/${deviceType.deviceTypeId}" />
			<form:form modelAttribute="deviceType" method="POST"
				action="${saveUrl}">
				<table class="table table-striped table-bordered">
					<tr>
						<td><form:label path="deviceType">Title:</form:label></td>
						<td><form:input type="text" class="form-control"
								path="deviceType" required="true" maxlength="20" /></td>
						<td><input type="submit" class="btn btn-success" value="Save" /></td>
					</tr>
				</table>
				<form:input type="hidden" class="form-control" path="deviceTypeId"
					required="true" />
			</form:form>
		</c:if>

		<c:if test="${action == 'addDeviceType'}">
			<c:url var="saveUrl" value="/configuration/deviceType/add" />
			<form:form modelAttribute="deviceType" method="POST"
				action="${saveUrl}">
				<table class="table table-striped table-bordered">
					<tr>
						<td><form:label path="deviceType">Title:</form:label></td>
						<td><form:input type="text" class="form-control"
								path="deviceType" required="true" maxlength="20" /></td>
						<td><input type="submit" class="btn btn-success" value="Add" /></td>
					</tr>
				</table>
			</form:form>
		</c:if>

		<c:if test="${action == 'editUrgStatus'}">
			<c:url var="saveUrl"
				value="/configuration/urgStatus/edit/id/${urgStatus.urgencyStatusId}" />
			<form:form modelAttribute="urgStatus" method="POST"
				action="${saveUrl}">
				<table class="table table-striped table-bordered">
					<tr>
						<td><form:label path="urgencyStatus">Title:</form:label></td>
						<td><form:label path="urgencyStatus">Time interaval:</form:label></td>
						<td><form:label path="urgencyStatus">Save:</form:label></td>
					</tr>
					<tr>
						<td><form:input type="text" class="form-control"
								path="urgencyStatus" required="true" maxlength="20" /></td>
						<td class="days-hours-minutes">
							<div><input type="number" min=0/><span>days</span></div>
							<div><input type="number" min="0" max="24"/><span>hours</span></div>
							<div><input type="number" min="0" max="60"/><span>minutes</span></div>
							<form:hidden path="minutes"/>
						</td>
						<td><input type="submit" class="btn btn-success" value="Save" /></td>
					</tr>
				</table>
				<form:input type="hidden" class="form-control"
					path="urgencyStatusId" required="true" />
			</form:form>
		</c:if>

		<c:if test="${action == 'addUrgStatus'}">
			<c:url var="saveUrl" value="/configuration/urgStatus/add" />
			<form:form modelAttribute="urgStatus" method="POST"
				action="${saveUrl}">
				<table class="table table-striped table-bordered">
					<tr>
						<td><form:label path="urgencyStatus">Title:</form:label></td>
						<td><form:label path="urgencyStatus">Minutes:</form:label></td>
						<td><form:label path="urgencyStatus">Save:</form:label></td>
					</tr>
					<tr>
						<td><form:input type="text" class="form-control"
								path="urgencyStatus" required="true" maxlength="20" /></td>
						<td class="days-hours-minutes">
							<div><input type="number" min=0/><span>days</span></div>
							<div><input type="number" min="0" max="24"/><span>hours</span></div>
							<div><input type="number" min="0" max="60"/><span>minutes</span></div>
							<form:hidden path="minutes"/>
						</td>
						<td><input type="submit" class="btn btn-success" value="Add" /></td>
					</tr>
				</table>
			</form:form>
		</c:if>

		<c:if test="${ empty action}">
			<table class="table  table-bordered">
				<caption>Problem Type</caption>
				<thead bgcolor="#8FBC8F">
					<tr>
						<th>ID</th>
						<th>Title</th>
						<th>Edit</th>
						<th>Delete</th>
					</tr>
				</thead>
				<tbody bgcolor="#fff">
					<c:forEach var="problemType" items="${problemType}">
						<tr>
							<td><c:out value="${problemType.problemTypeId}" /></td>
							<td><c:out value="${problemType.problemType}" /></td>

							<td><a
								href="configuration/problemType/edit/id/${problemType.problemTypeId}"
								id="${problemType.problemTypeId}"><span
									class="glyphicon glyphicon-edit" aria-hidden="true"></span></a></td>
							<td><a class="problemTypeConfirm"
								href="configuration/problemType/delete/id/${problemType.problemTypeId}"
								id="${problemType.problemTypeId}"> <span
									class="glyphicon glyphicon-trash" aria-hidden="true"></span>
							</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<form action="configuration/problemType/add">
				<input type="submit" class="btn btn-success"
					value="Add new problem type" required/>
			</form>

			<Hr>
			<table class="table table-bordered">
				<caption>Device Type</caption>
				<thead bgcolor="#8FBC8F">
					<tr>
						<th>ID</th>
						<th>Title</th>
						<th>Edit</th>
						<th>Delete</th>
					</tr>
				</thead>
				<tbody bgcolor="#fff">
					<c:forEach var="deviceType" items="${deviceType}">
						<tr>
							<td><c:out value="${deviceType.deviceTypeId}" /></td>
							<td><c:out value="${deviceType.deviceType}" /></td>

							<td><a
								href="configuration/deviceType/edit/id/${deviceType.deviceTypeId}"
								id="${deviceType.deviceTypeId}"><span
									class="glyphicon glyphicon-edit" aria-hidden="true"></span></a></td>
							<td><a class="deviceTypeconfirm"
								href="configuration/deviceType/delete/id/${deviceType.deviceTypeId}"
								id="${deviceType.deviceTypeId}"> <span
									class="glyphicon glyphicon-trash" aria-hidden="true"></span>
							</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<form action="configuration/deviceType/add">
				<input type="submit" class="btn btn-success"
					value="Add new device type" required/>
			</form>

			<Hr>
			<table class="table table-bordered">
				<caption>Urgency Status</caption>
				<thead bgcolor="#8FBC8F">
					<tr>
						<th>ID</th>
						<th>Title</th>
						<th>Time interval</th>
						<th>Edit</th>
						<th>Delete</th>
					</tr>
				</thead>
				<tbody bgcolor="#fff">
					<c:forEach var="urgStatus" items="${urgStatus}">
						<tr>
							<td><c:out value="${urgStatus.urgencyStatusId}" /></td>
							<td><c:out value="${urgStatus.urgencyStatus}" /></td>
							<td class="timeInterval"><c:out value="${urgStatus.minutes}" /></td>
							<td><a
								href="configuration/urgStatus/edit/id/${urgStatus.urgencyStatusId}"
								id="${urgStatus.urgencyStatusId}"><span
									class="glyphicon glyphicon-edit" aria-hidden="true"></span></a></td>
							<td><a class="urgStatusConfirm"
								href="configuration/urgStatus/delete/id/${urgStatus.urgencyStatusId}"
								id="${urgStatus.urgencyStatusId}"> <span
									class="glyphicon glyphicon-trash" aria-hidden="true"></span>
							</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<form action="configuration/urgStatus/add">
				<input type="submit" class="btn btn-success"
					value="Add new urgency status" required/>
			</form>
		</c:if>
	</div>
</div>
<%@include file="footer.jsp"%>