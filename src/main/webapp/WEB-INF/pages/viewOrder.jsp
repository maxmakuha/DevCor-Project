<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="header.jsp"%>

<c:set var="role" value="${pageContext.request.userPrincipal.authorities.iterator().next().authority}" />
<c:set var="email" value="${pageContext.request.userPrincipal.name}"/>
<c:set var="isUser" value="${role == 'ROLE_USER'}"/>
<c:set var="isTech" value="${role == 'ROLE_TECHNICIAN'}"/>
<c:set var="isAdmin" value="${role == 'ROLE_ADMIN'}"/>
<c:set var="isOrderAuthor" value="${isUser &&  order.authorEmail == email}" />
<c:set var="isOrderTech" value="${isTech && order.technicianEmail == email}" />
<c:set var="orderIsOpen" value="${order.executionStatus == 'Open'}" />
<c:set var="orderIsInProgress" value="${order.executionStatus == 'In progress'}" />
<c:set var="orderIsFinished" value="${order.executionStatus == 'Finished'}" />
<c:set var="orderIsIncorrect" value="${order.executionStatus == 'Incorrect'}" />
<c:set var="orderIsUnsolvable" value="${order.executionStatus == 'Unsolvable'}" />

<script>
$(document).ready(function(){
	$.get('/DevCor/getRoomDevices', {
		roomId: $('#roomNumberOptions').val()
	}, function(responseHTML){
		$('#serialPortOptions').html(responseHTML);
		$('#serialPortOptions option[value="${ order.getDeviceId()}"]').attr('selected', 'selected');
	});
	
	$(
		'#problemTypeOptions option[value="${ order.problemTypeId}"],' + 
		'#roomNumberOptions option[value="${ order.roomId}"], ' +
		'#urgencyStatusOptions option[value="${ order.urgencyStatusId}"], ' + 
		'#technicianOptions option[value="${ order.technicianId}"]'
		).attr('selected', 'selected');
	
	$('#executionStatusOptions')
		.prepend('<option value="${order.executionStatusId}" selected="selected">${order.executionStatus}</value>');
	
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

<br>
<br>
<br>
<div class="panel panel-success">
	<div class="panel-heading">
		<h3 class="panel-title">Order #${order.orderId}</h3>
	</div>	
	<form:form modelAttribute="order" class="order-form">
		<form:input path="orderId" type="hidden"/>
		<table class="table table-striped table-bordered">
			<c:if test="${isOrderAuthor && orderIsOpen}">
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
					<td><form:textarea path="description" id="descriptionText"/></td>
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
				
			<c:if test="${isTech || isAdmin || (isUser && (!orderIsOpen || !isOrderAuthor))}">
				<tr>
					<td><label>Problem type: </label></td>
					<td>${order.problemType}</td>
					<form:input path="problemTypeId" type="hidden"/>
				</tr>
				<tr>
					<td><label>Problem description: </label></td>
					<td><form:textarea path="description" class="description" readonly="true" /></td>
				</tr>
				<tr>
					<td><label>Room number: </label></td>
					<td>${order.roomNumber}</td>
				</tr>
				<form:input path="roomId" type="hidden"/>
				<tr>
					<td><label>Serial port: </label></td>
					<td>${order.deviceSerialId}</td>
				</tr>
				<form:input path="deviceId" type="hidden"/>
			</c:if>
				
			<c:if test="${(isOrderAuthor || isOrderTech) && orderIsOpen}">
				<tr>
					<td><label for="urgencyStatusOptions">Urgency status: </label></td>
					<td>
						<form:select path="urgencyStatusId" id="urgencyStatusOptions" required="required">
							<form:options items="${urgencyStatuses}" itemValue="urgencyStatusId" itemLabel="urgencyStatus" />
						</form:select>
					</td>
				</tr>
			</c:if>
				
			<c:if test="${!((isOrderAthor || isOrderTech) && orderIsOpen)}">
				<tr>
					<td><label>Urgency status:</label></td>
					<td>${order.urgencyStatus}</td>
				</tr>
				<form:input path="urgencyStatusId" type="hidden"/>
			</c:if>
				
			<c:if test="${isUser || isAdmin || (isTech && (orderIsFinished || !isOrderTech))}">
				<tr>
					<td><label>Execution status:</label></td>
					<td>${order.executionStatus}</td>
				</tr>
				<form:input path="executionStatusId" type="hidden"/>
			</c:if>
				
			<c:if test="${isOrderTech && !orderIsFinished}">
				<tr>
					<td><label for="executionStatusOptions">Execution status: </label></td>
					<td>
						<form:select path="executionStatusId" id="executionStatusOptions" required="required">
							<c:if test="${orderIsOpen}">
								<form:option value="2">In progress</form:option>
							</c:if>
							<c:if test="${orderIsInProgress}">
								<form:option value="3">Finished</form:option>
							</c:if>
							<c:if test="${orderIsOpen || orderIsInProgress}">
								<form:option value="4">Incorrect</form:option>
								<form:option value="5">Unsolvable</form:option>
							</c:if>
							<c:if test="${orderIsIncorrect || orderIsUnsolvable}">
								<form:option value="1">Open</form:option>
							</c:if>
						</form:select>
					</td>
				</tr>
			</c:if>
			
			<tr>
				<td><label>Creation date:</label></td>
				<td>${order.creationDate}</td>
			</tr>
			
			<form:input path="creationDate" type="hidden"/>
			
			<tr>
				<td><label>Due date:</label></td>
				<td>${order.dueDate}</td>
			</tr>
			
			<form:input path="dueDate" type="hidden"/>
			
			<tr>
				<td><label>Author:</label></td>
				<td>${order.authorName} ${order.authorSurname}</td>
			</tr>
			
			<form:input path="authorId" type="hidden"/>
			
			<c:if test="${isUser || isTech}">
				<tr>
					<td><label>Technician:</label></td>
					<td>${order.technicianName} ${order.technicianSurname}</td>
				</tr>
				
				<form:input path="technicianId" type="hidden"/>
			</c:if>
			
			<c:if test="${isAdmin}">
				<tr>
					<td><label for="technicianOptions">Technician: </label></td>
					<td>
						<form:select path="technicianId" id="technicianOptions" required="required">
							<form:options items="${technicians}" itemValue="playerId" itemLabel="fullName" />
						</form:select>
					</td>
			</c:if>
			
			<tr>
				<td><label>Is overdue?</label></td>
				<td>${order.overdue}</td>
			</tr>
			
			<form:input path="overdue" type="hidden"/>
		</table>
		<p style="text-align: center">
			<input type="submit" class="btn btn-success" value="Confirm changes" />
			<input type="button" class="btn btn-cancell" value="Go back" />
		</p>
	</form:form>
</div>
<%@include file="footer.jsp"%>