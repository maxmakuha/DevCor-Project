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
		window.location = "../../dashboard";
	});
});
</script>

<title>Order information</title>
<br>
<br>
<br>
<div class="panel panel-success">
	<div class="panel-heading">
		<h3 class="panel-title">Order #${order.getOrderId()}</h3>
	</div>
	<form:form modelAttribute="order" class="order-form">
		<form:input path="orderId" type="hidden" value="${order.getOrderId()}"/>
		<table class="table table-striped table-bordered">
			<c:if test="${role=='ROLE_USER' && 
						order.getAuthorEmail() == pageContext.request.userPrincipal.name && 
						order.getExecutionStatus() == 'Open'}">
				<tr>
					<td><label for="problemTypeOptions">Problem type: </label></td>
					<td>
						<form:select path="problemTypeId" id="problemTypeOptions" required="required">
							<form:options items="${problemTypes}" itemValue="problemTypeId" itemLabel="problemType" />
						</form:select>
					</td>
				</tr>
				<tr>
					<td><label for="descriptionText">Problem description: </label></td>
					<td><form:textarea path="description" id="descriptionText" value="${order.getDescription() }"/></td>
				</tr>
				<tr>
					<td><label for="roomNumberOptions">Room number: </label></td>
					<td>
						<form:select path="roomId" id="roomNumberOptions" required="required">
							<form:options items="${rooms}"  itemValue="roomId" itemLabel="roomNumber" />
						</form:select>
					</td>
				</tr>
				<tr>
					<td><label for="serialPortOptions">Serial port: </label></td>
					<td>
						<form:select path="deviceId" id="serialPortOptions">
							<option value="-1">Not specified</option>
						</form:select>
					</td>
				</tr>
			</c:if>
				
			<c:if test="${role=='ROLE_TECHNICIAN' ||
						role=='ROLE_ADMIN' ||
						(role=='ROLE_USER' && (order.getExecutionStatus()!='Open' || order.getAuthorEmail()!=pageContext.request.userPrincipal.name))}">
				<tr>
					<td><label>Problem type: </label></td>
					<td>${order.getProblemType()}</td>
					<form:input path="problemTypeId" value="${order.getProblemTypeId()}"/>
				</tr>
				<tr>
					<td><label>Problem description: </label></td>
					<td><form:textarea path="description" class="description" value="${order.getDescription()}" readonly="true" /></td>
				</tr>
				<tr>
					<td><label>Room number: </label></td>
					<td>${order.getRoomNumber()}</td>
				</tr>
				<form:input path="roomId" type="hidden" value="${order.getRoomId()}"/>
				<tr>
					<td><label>Serial port: </label></td>
					<td>${order.getDeviceSerialId()}</td>
				</tr>
				<form:input path="deviceId" type="hidden" value="${order.getDeviceId()}"/>
			</c:if>
				
			<c:if test="${((role=='ROLE_USER' && order.getAuthorEmail()==pageContext.request.userPrincipal.name) ||
						(role=='ROLE_TECHNICIAN' && order.getTechnicianEmail()==pageContext.request.userPrincipal.name)) &&
						order.getExecutionStatus()=='Open'}">
				<tr>
					<td><label for="urgencyStatusOptions">Urgency status: </label></td>
					<td>
						<form:select path="urgencyStatusId" id="urgencyStatusOptions" required="required">
							<form:options items="${urgencyStatuses}" itemValue="urgencyStatusId" itemLabel="urgencyStatus" />
						</form:select>
					</td>
				</tr>
			</c:if>
				
			<c:if test="${not (((role=='ROLE_USER' && order.getAuthorEmail()==pageContext.request.userPrincipal.name) ||
						 (role=='ROLE_TECHNICIAN' && order.getTechnicianEmail()==pageContext.request.userPrincipal.name)) &&
						order.getExecutionStatus()=='Open')}">
				<tr>
					<td><label>Urgency status:</label></td>
					<td>${order.getUrgencyStatus()}</td>
				</tr>
				<form:input path="urgencyStatusId" type="hidden" value="${order.getUrgencyStatusId()}"/>
			</c:if>
				
			<c:if test="${role=='ROLE_USER' ||
						role=='ROLE_ADMIN' || 
						(role=='ROLE_TECHNICIAN' && (order.getExecutionStatus()=='Finished' || order.getTechnicianEmail()!=pageContext.request.userPrincipal.name))}">
				<tr>
					<td><label>Execution status:</label></td>
					<td>${order.getExecutionStatus()}</td>
				</tr>
				<form:input path="executionStatusId" type="hidden" value="${order.getExecutionStatusId()}"/>
			</c:if>
				
			<c:if test="${role=='ROLE_TECHNICIAN' && 
						order.getExecutionStatus()!='Finished' &&
						order.getTechnicianEmail()==pageContext.request.userPrincipal.name}">
				<tr>
					<td><label for="executionStatusOptions">Execution status: </label></td>
					<td>
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
					</td>
				</tr>
			</c:if>
			
			<tr>
				<td><label>Creation date:</label></td>
				<td>${order.getCreationDate()}</td>
			</tr>
			
			<form:input path="creationDate" type="hidden" value="${order.getCreationDate()}"/>
			
			<tr>
				<td><label>Due date:</label></td>
				<td>${order.getDueDate()}</td>
			</tr>
			
			<form:input path="dueDate" type="hidden" value="${order.getDueDate()}"/>
			
			<tr>
				<td><label>Author:</label></td>
				<td>${order.getAuthorName()} ${order.getAuthorSurname()}</td>
			</tr>
			
			<form:input path="authorId" type="hidden" value="${order.getAuthorId()}"/>
			
			<c:if test="${role=='ROLE_USER' || role=='ROLE_TECHNICIAN'}">
				<tr>
					<td><label>Technician:</label></td>
					<td>${order.getTechnicianName()} ${order.getTechnicianSurname()}</td>
				</tr>
				
				<form:input path="technicianId" type="hidden" value="${order.getTechnicianId()}"/>
			</c:if>
			
			<c:if test="${role=='ROLE_ADMIN'}">
				<tr>
					<td><label for="technicianOptions">Technician: </label></td>
					<td>
						<form:select path="technicianId" id="technicianOptions" required="required">
							<form:options items="${technicians}" itemValue="playerId" itemLabel="fullName" />
						</form:select>
					</td>
			</c:if>
			
			<tr>
				<td><label>Is overdue?:</label></td>
				<td>${order.getOverdue()}</td>
			</tr>
			
			<form:input path="overdue" type="hidden" value="${order.getOverdue()}"/>
		</table>
		<p style="text-align: center">
			<input type="submit" class="btn btn-success" value="Confirm changes" />
			<input type="button" class="btn btn-cancell" value="Go back" />
		</p>
	</form:form>
</div>
<%@include file="footer.jsp"%>