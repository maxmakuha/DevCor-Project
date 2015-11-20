<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="header.jsp"%>

<c:set var="role" value="${pageContext.request.userPrincipal.authorities.iterator().next().authority}" />
<c:set var="email" value="${pageContext.request.userPrincipal.name}"/>
<c:set var="isUser" value="${role == 'ROLE_USER'}"/>
<c:set var="isTech" value="${role == 'ROLE_TECHNICIAN'}"/>
<c:set var="isAdmin" value="${role == 'ROLE_ADMIN'}"/>
<c:set var="isOrderAuthor" value="${isUser &&  turple.order.authorEmail == email}" />
<c:set var="isOrderTech" value="${isTech && turple.order.technicianEmail == email}" />
<c:set var="orderIsOpen" value="${turple.order.executionStatus == 'Open'}" />
<c:set var="orderIsInProgress" value="${turple.order.executionStatus == 'In progress'}" />
<c:set var="orderIsFinished" value="${turple.order.executionStatus == 'Finished'}" />
<c:set var="orderIsIncorrect" value="${turple.order.executionStatus == 'Incorrect'}" />
<c:set var="orderIsUnsolvable" value="${turple.order.executionStatus == 'Unsolvable'}" />

<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script>
$(document).ready(function(){
	$.get('/DevCor/getRoomDevices', {
		roomId: $('#roomNumberOptions').val()
	}, function(responseHTML){
		$('#serialPortOptions').html(responseHTML);
		$('#serialPortOptions option[value="${ turple.order.getDeviceId()}"]').attr('selected', 'selected');
	});
	
	$(
		'#problemTypeOptions option[value="${ turple.order.problemTypeId}"],' + 
		'#roomNumberOptions option[value="${ turple.order.roomId}"], ' +
		'#urgencyStatusOptions option[value="${ turple.order.urgencyStatusId}"], ' + 
		'#technicianOptions option[value="${ turple.order.technicianId}"]'
		).attr('selected', 'selected');
	
	$('#executionStatusOptions')
		.prepend('<option value="${turple.order.executionStatusId}" selected="selected">${turple.order.executionStatus}</value>');
	
	$('#roomNumberOptions').change(function(){
		$.get('/DevCor/getRoomDevices', {
			roomId: $(this).val()
		}, function(responseHTML){
			$('#serialPortOptions').html(responseHTML);
		});
	});
	
	$('#executionStatusOptions, #urgencyStatusOptions').change(function(){
		$(".newComment1, .newComment2").show();
		$(".newComment2 td").html($.datepicker.formatDate('yy-mm-dd', new Date()));
		$("#comment-creation-date").val($.datepicker.formatDate('yy-mm-dd', new Date()));
		if( $(this).find(":selected").text() == 'Incorrect' || 
			$(this).find(":selected").text() == 'Unsolvable'){
			$(".newComment1 textarea").prop("required", "true");
		}
	});
	
	$('input[type=button]').click(function(){
		window.location = "../../dashboard";
	});
	
	$('input[type=submit]').click(function(){
		$(".order-form").submit();
	});

});
</script>

<br>
<br>
<br>
<div class="panel panel-success">
	<div class="panel-heading">
		<h3 class="panel-title">Order #${turple.order.orderId}</h3>
	</div>	
	<form:form modelAttribute="turple" class="order-form">
		<form:input path="order.orderId" type="hidden"/>
		<table class="table table-striped table-bordered">
			<c:if test="${isOrderAuthor && orderIsOpen}">
				<tr>
					<td><label for="problemTypeOptions">Problem type: </label></td>
					<td>
						<form:select path="order.problemTypeId" id="problemTypeOptions" required="required">
							<form:options items="${problemTypes}" itemValue="problemTypeId" itemLabel="problemType" />
						</form:select>
					</td>
				</tr>
				<tr>
					<td><label for="descriptionText">Problem description: </label></td>
					<td><form:textarea path="order.description" id="descriptionText"/></td>
				</tr>
				<tr>
					<td><label for="roomNumberOptions">Room number: </label></td>
					<td>
						<form:select path="order.roomId" id="roomNumberOptions" required="required">
							<form:options items="${rooms}"  itemValue="roomId" itemLabel="roomNumber" />
						</form:select>
					</td>
				</tr>
				<tr>
					<td><label for="serialPortOptions">Serial port: </label></td>
					<td>
						<form:select path="order.deviceId" id="serialPortOptions">
							<option value="-1">Not specified</option>
						</form:select>
					</td>
				</tr>
			</c:if>
				
			<c:if test="${isTech || isAdmin || (isUser && (!orderIsOpen || !isOrderAuthor))}">
				<tr>
					<td><label>Problem type: </label></td>
					<td>${turple.order.problemType}</td>
					<form:input path="order.problemTypeId" type="hidden"/>
				</tr>
				<tr>
					<td><label>Problem description: </label></td>
					<td><form:textarea path="order.description" class="description" readonly="true" /></td>
				</tr>
				<tr>
					<td><label>Room number: </label></td>
					<td>${turple.order.roomNumber}</td>
				</tr>
				<form:input path="order.roomId" type="hidden"/>
				<tr>
					<td><label>Serial port: </label></td>
					<td>${turple.order.deviceSerialId}</td>
				</tr>
				<form:input path="order.deviceId" type="hidden"/>
			</c:if>
				
			<c:if test="${(isOrderAuthor || isOrderTech) && orderIsOpen}">
				<tr>
					<td><label for="urgencyStatusOptions">Urgency status: </label></td>
					<td>
						<form:select path="order.urgencyStatusId" id="urgencyStatusOptions" required="required">
							<form:options items="${urgencyStatuses}" itemValue="urgencyStatusId" itemLabel="urgencyStatus" />
						</form:select>
					</td>
				</tr>
			</c:if>
				
			<c:if test="${!((isOrderAthor || isOrderTech) && orderIsOpen)}">
				<tr>
					<td><label>Urgency status:</label></td>
					<td>${turple.order.urgencyStatus}</td>
				</tr>
				<form:input path="order.urgencyStatusId" type="hidden"/>
			</c:if>
				
			<c:if test="${isUser || isAdmin || (isTech && (orderIsFinished || !isOrderTech))}">
				<tr>
					<td><label>Execution status:</label></td>
					<td>${turple.order.executionStatus}</td>
				</tr>
				<form:input path="order.executionStatusId" type="hidden"/>
			</c:if>
				
			<c:if test="${isOrderTech && !orderIsFinished}">
				<tr>
					<td><label for="executionStatusOptions">Execution status: </label></td>
					<td>
						<form:select path="order.executionStatusId" id="executionStatusOptions" required="required">
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
				<td>${turple.order.creationDate}</td>
			</tr>
			
			<form:input path="order.creationDate" type="hidden"/>
			
			<tr>
				<td><label>Due date:</label></td>
				<td>${turple.order.dueDate}</td>
			</tr>
			
			<form:input path="order.dueDate" type="hidden"/>
			
			<tr>
				<td><label>Author:</label></td>
				<td>${turple.order.authorName} ${turple.order.authorSurname}</td>
			</tr>
			
			<form:input path="order.authorId" type="hidden"/>
			
			<c:if test="${isUser || isTech}">
				<tr>
					<td><label>Technician:</label></td>
					<td>${turple.order.technicianName} ${turple.order.technicianSurname}</td>
				</tr>
				
				<form:input path="order.technicianId" type="hidden"/>
			</c:if>
			
			<c:if test="${isAdmin}">
				<tr>
					<td><label for="technicianOptions">Technician: </label></td>
					<td>
						<form:select path="order.technicianId" id="technicianOptions" required="required">
							<form:options items="${technicians}" itemValue="playerId" itemLabel="fullName" />
						</form:select>
					</td>
			</c:if>
			
			<tr>
				<td><label>Is overdue?</label></td>
				<td>${turple.order.overdue}</td>
			</tr>
			
			<form:input path="order.overdue" type="hidden"/>
		</table>
	
		<c:if test="${comments.size() != 0}">
			<h2>Comments</h2>
		
			<table class="table table-bordered comments-table">
				<tr class="newComment1">
					<td>${turple.order.technicianName} ${turple.order.technicianSurname}</td>
					<td rowspan="2"><form:textarea path="comment.comment" placeholder="Your new comment"/></td>
				</tr>
				<tr class="newComment2">
					<td></td>
				</tr>
				<form:input path="comment.orderId" type="hidden" value="${turple.order.orderId}"/>
				<form:input id="comment-creation-date" path="comment.creationDate" type="hidden"/>
			
				<c:forEach var="comment" items="${comments}">
					<tr>
						<td>${turple.order.technicianName} ${turple.order.technicianSurname}</td>
						<td rowspan="2">${comment.comment}</td>
					</tr>
					<tr>
						<td>${comment.creationDate}</td>
					</tr>
				</c:forEach>
			</table>
	</c:if>
	</form:form>
	<br>
	
	<p style="text-align: center">
		<input type="submit" class="btn btn-success" value="Confirm changes" />
		<input type="button" class="btn btn-cancell" value="Go back" />
	</p>
</div>
<%@include file="footer.jsp"%>