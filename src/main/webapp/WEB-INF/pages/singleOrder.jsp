<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="header.jsp"%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<script>
$(document).ready(function(){
	$.get('/DevCor/getRoomDevices', {
		roomId: $('#roomNumberOptions').val()
	}, function(responseHTML){
		$('#serialPortOptions').html(responseHTML);
		$('#serialPortOptions option[value="${ order.getDeviceId()}"]').attr('selected', 'selected');
	});
	$(
		'#problemTypeOptions option[value="${ order.getProblemTypeId()}"],' + 
		'#roomNumberOptions option[value="${ order.getRoomId()}"], ' +
		'#urgencyStatusOptions option[value="${ order.getUrgencyStatusId()}"], ' + 
		'#technicianOptions option[value="${ order.getTechnicianId()}"]'
		).attr('selected', 'selected');
	$('#executionStatusOptions')
		.prepend('<option value="${order.getExecutionStatusId()}" selected="selected">${order.getExecutionStatus()}</value>');
	
	$('#roomNumberOptions').change(function(){
		$.get('/DevCor/getRoomDevices', {
			roomId: $(this).val()
		}, function(responseHTML){
			$('#serialPortOptions').html(responseHTML);
		});
	});
	
	$('input[type=button]').click(function(){
		window.location = "dashboard";
	});
});
</script>

<title>Order information</title>
<div class="container">
	<div class="wrapper">
		<div id="panel">
			<form:form modelAttribute="order" class="createOrder" action="dashboard">
				<h1>Order #${order.getOrderId()}</h1>
				<form:input path="orderId" value="${order.getOrderId()}"/>
				<c:if test="${role=='ROLE_USER' && 
							order.getAuthorEmail()==pageContext.request.userPrincipal.name && 
							order.getExecutionStatus()=='Open'}">
					<label for="problemTypeOptions">Problem type: </label>
					<form:select path="problemTypeId" id="problemTypeOptions" required="required">
						<form:options items="${problemTypes}" itemValue="problemTypeId" itemLabel="problemType" />
					</form:select>
					<br />
					<label for="descriptionText">Problem description: </label>
						<br />
					<form:textarea path="description" id="descriptionText" value="${order.getDescription() }"/>
					<br />
					<br />
					<label for="roomNumberOptions">Room number: </label>
					<form:select path="roomId" id="roomNumberOptions" required="required">
						<form:options items="${rooms}"  itemValue="roomId" itemLabel="roomNumber" />
					</form:select>
					<br />
					<label for="serialPortOptions">Serial port: </label>
					<form:select path="deviceId" id="serialPortOptions">
						<option value="-1">--Not specified</option>
					</form:select>
					<br/>
				</c:if>
				
				<c:if test="${role=='ROLE_TECHNICIAN' ||
						role=='ROLE_ADMIN' ||
						(role=='ROLE_USER' && (order.getExecutionStatus()!='Open' || order.getAuthorEmail()!=pageContext.request.userPrincipal.name))}">
					<label>Problem type: </label><span>${order.getProblemType()}</span>
					<form:input path="problemTypeId" value="${order.getProblemTypeId()}"/>
					<br />
					<label>Problem description: </label>
					<br />
					<form:textarea path="description" class="description" value="${order.getDescription()}" readonly="true" />
					<br />
					<label>Room number: </label><span>${order.getRoomNumber()}</span>
					<form:input path="roomId" value="${order.getRoomId()}"/>
					<br />
					<label>Serial port: </label><span>${order.getDeviceSerialId()}</span>
					<form:input path="deviceId" value="${order.getDeviceId()}"/>
					<br />
				</c:if>
				
				<c:if test="${((role=='ROLE_USER' && order.getAuthorEmail()==pageContext.request.userPrincipal.name) ||
							 (role=='ROLE_TECHNICIAN' && order.getTechnicianEmail()==pageContext.request.userPrincipal.name)) &&
							order.getExecutionStatus()=='Open'}">
					<label for="urgencyStatusOptions">Urgency status: </label>
					<form:select path="urgencyStatusId" id="urgencyStatusOptions" required="required">
						<form:options items="${urgencyStatuses}" itemValue="urgencyStatusId" itemLabel="urgencyStatus" />
					</form:select>
					<br />
				</c:if>
				
				<c:if test="${not (((role=='ROLE_USER' && order.getAuthorEmail()==pageContext.request.userPrincipal.name) ||
							 (role=='ROLE_TECHNICIAN' && order.getTechnicianEmail()==pageContext.request.userPrincipal.name)) &&
							order.getExecutionStatus()=='Open')}">
					<label>Urgency status:</label><span>${order.getUrgencyStatus()}</span>
					<form:input path="urgencyStatusId" value="${order.getUrgencyStatusId()}"/>
					<br />
				</c:if>
				
				<c:if test="${role=='ROLE_USER' ||
							role=='ROLE_ADMIN' || 
							(role=='ROLE_TECHNICIAN' && (order.getExecutionStatus()=='Finished' || order.getTechnicianEmail()!=pageContext.request.userPrincipal.name))}">
					<label>Execution status:</label><span>${order.getExecutionStatus()}</span>
					<form:input path="executionStatusId" value="${order.getExecutionStatusId()}"/>
					<br />
				</c:if>
				
				<c:if test="${role=='ROLE_TECHNICIAN' && 
							order.getExecutionStatus()!='Finished' &&
							order.getTechnicianEmail()==pageContext.request.userPrincipal.name}">
					<label for="executionStatusOptions">Execution status: </label>
					<form:select path="executionStatusId" id="executionStatusOptions" required="required">
						<c:if test="${order.getExecutionStatus()=='Open'}">
							<form:option value="2">In progress</form:option>
						</c:if>
						<c:if test="${order.getExecutionStatus()=='In progress'}">
							<form:option value="3">Finished</form:option>
						</c:if>
						<c:if test="${order.getExecutionStatus()=='Open' || order.getExecutionStatus()=='In progress'}">
							<form:option value="4">Incorrect</form:option>
							<form:option value="5">Unsolvable</form:option>
						</c:if>
						<c:if test="${order.getExecutionStatus()=='Incorrect' || order.getExecutionStatus()=='Unsolvable'}">
							<form:option value="1">Open</form:option>
						</c:if>
					</form:select>
					<br />
				</c:if>
			
				<label>Creation date:</label><span>${order.getCreationDate()}</span>
				<form:input path="creationDate" value="${order.getCreationDate()}"/>
				<br />
				<label>Due date:</label><span>${order.getDueDate()}</span>
				<form:input path="dueDate" value="${order.getDueDate()}"/>
				<br />
				<label>Author:</label><span>${order.getAuthorName()} ${order.getAuthorSurname()}</span>
				<form:input path="authorId" value="${order.getAuthorId()}"/>
				<br />
				<c:if test="${role=='ROLE_USER' || role=='ROLE_TECHNICIAN'}">
					<label>Technician:</label><span>${order.getTechnicianName()} ${order.getTechnicianSurname()}</span>
					<form:input path="technicianId" value="${order.getTechnicianId()}"/>
					<br />
				</c:if>
				<c:if test="${role=='ROLE_ADMIN'}">
					<label for="technicianOptions">Technician: </label>
					<form:select path="technicianId" id="technicianOptions" required="required">
						<form:options items="${technicians}" itemValue="playerId" itemLabel="fullName" />
					</form:select>
				</c:if>
				<label>Is overdue?:</label><span>${order.getOverdue()}</span>
				<form:input path="overdue" value="${order.getOverdue()}"/>
				<br />
				<input type="submit" value="Confirm changes" />
				<input type="button" value="Go back" />
			</form:form>
		</div>
	</div>
</div>

<%@include file="footer.jsp"%>