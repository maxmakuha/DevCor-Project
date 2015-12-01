<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@include file="header.jsp"%>

<c:set var="email" value="${pageContext.request.userPrincipal.name}"/>
<security:authorize access="hasRole('ROLE_USER')" var="isUser"/>
<security:authorize access="hasRole('ROLE_TECHNICIAN')" var="isTech"/>
<security:authorize access="hasRole('ROLE_ADMIN')" var="isAdmin"/>
<c:set var="isOrderAuthor" value="${isUser &&  orderAndComment.order.authorEmail == email}" />
<c:set var="isOrderTech" value="${isTech && orderAndComment.order.technicianEmail == email}" />
<c:set var="orderIsOpen" value="${orderAndComment.order.executionStatus == 'Open'}" />
<c:set var="orderIsInProgress" value="${orderAndComment.order.executionStatus == 'In progress'}" />
<c:set var="orderIsFinished" value="${orderAndComment.order.executionStatus == 'Finished'}" />
<c:set var="orderIsIncorrect" value="${orderAndComment.order.executionStatus == 'Incorrect'}" />
<c:set var="orderIsUnsolvable" value="${orderAndComment.order.executionStatus == 'Unsolvable'}" />

<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootbox.min.js"/>"></script>
<script>
function getCurrentDateAndTime(){
	var s = function(a,b){return(1e15+a+"").slice(-b)};
	
	if (typeof d === 'undefined'){
		d = new Date();
	};

	return d.getFullYear() + '-' + 
		s(d.getMonth()+1,2) + '-' +
		s(d.getDate(),2) + ' ' +
		s(d.getHours(),2) + ':' +
		s(d.getMinutes(),2) + ':' +
		s(d.getSeconds(),2);
}

$(document).ready(function(){
	$.get('/DevCor/getRoomDevices', {
		roomId: $('#roomNumberOptions').val()
	}, function(responseHTML){
		$('#serialPortOptions').html(responseHTML);
		$('#serialPortOptions option[value="${ orderAndComment.order.getDeviceId()}"]').attr('selected', 'selected');
	});
	
	$(
		'#problemTypeOptions option[value="${ orderAndComment.order.problemTypeId}"],' + 
		'#roomNumberOptions option[value="${ orderAndComment.order.roomId}"], ' +
		'#urgencyStatusOptions option[value="${ orderAndComment.order.urgencyStatusId}"], ' + 
		'#technicianOptions option[value="${ orderAndComment.order.technicianId}"]'
		).attr('selected', 'selected');
	
	$('#executionStatusOptions')
		.prepend('<option value="${orderAndComment.order.executionStatusId}" selected="selected">${orderAndComment.order.executionStatus}</value>');
	
	$('#roomNumberOptions').change(function(){
		$.get('/DevCor/getRoomDevices', {
			roomId: $(this).val()
		}, function(responseHTML){
			$('#serialPortOptions').html(responseHTML);
		});
	});
	
	$('#executionStatusOptions, #urgencyStatusOptions').change(function(){
		if( $(this).find(":selected").text() == 'Incorrect' ||
			$(this).find(":selected").text() == 'Unsolvable'){
			$(".comment-button").click();
			$(".newComment1 textarea").prop("required", "true");
		} else {
			$(".newComment1 textarea").removeAttr("required");
		}
	});
	
	$(".comment-button").click(function(){
		$(this).hide();
		$(".edit-button").click();
		$(".newComment1, .newComment2").show();
		$(".newComment2 td").html(getCurrentDateAndTime().slice(0, -3));
		$("#comment-creation-date").val(getCurrentDateAndTime());
	})
	
	$('input[type=button]:last-of-type').click(function(){
		window.location = "/DevCor/dashboard";
	});
	
	function h(e) {
	    $(e).css({'height':'auto','overflow-y':'hidden'}).height(e.scrollHeight);
	}
	$('textarea').each(function () {
	  h(this);
	}).on('input', function () {
	  h(this);
	});
	
	$(".order-and-comment-form").find("input, textarea, select").prop("disabled", true);
	$(".order-and-comment-form input[type='button']").prop("disabled", false);
	
	$(".edit-button").click(function(){
		$(".order-and-comment-form").find("input, textarea, select").prop("disabled", false);
		$(this).hide();
		$(".order-and-comment-form input[type='submit']").show();
	});
	
	$(".delete-button").click(function(e){
		e.preventDefault();
		var location = $(this).attr('href');
		bootbox.confirm("Are you sure you want to delete this order?", function(result) {
			if (result) {
				window.location.replace(location);
			}
		});
	});
	
	$("#comment-creation-date").val(getCurrentDateAndTime());
});
</script>

<br>
<br>
<br>
<div class="panel panel-success">
	<div class="panel-heading">
		<h3 class="panel-title">Order #${orderAndComment.order.orderId}</h3>
	</div>	
	<form:form modelAttribute="orderAndComment" class="order-and-comment-form">
		<form:hidden path="order.orderId"/>
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
					<td><form:textarea path="order.description" id="descriptionText" maxlength="1000" /></td>
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
					<td>${orderAndComment.order.problemType}</td>
					<form:hidden path="order.problemTypeId"/>
				</tr>
				<tr>
					<td><label>Problem description: </label></td>
					<td><form:textarea path="order.description" class="description" readonly="true" /></td>
				</tr>
				<tr>
					<td><label>Room number: </label></td>
					<td>${orderAndComment.order.roomNumber}</td>
				</tr>
				<form:hidden path="order.roomId"/>
				<tr>
					<td><label>Serial port: </label></td>
					<td>${orderAndComment.order.deviceSerialId}</td>
				</tr>
				<form:hidden path="order.deviceId"/>
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
				
			<c:if test="${!((isOrderAuthor || isOrderTech) && orderIsOpen)}">
				<tr>
					<td><label>Urgency status:</label></td>
					<td>${orderAndComment.order.urgencyStatus}</td>
				</tr>
				<form:hidden path="order.urgencyStatusId"/>
			</c:if>
				
			<c:if test="${isUser || isAdmin || (isTech && (orderIsFinished || !isOrderTech))}">
				<tr>
					<td><label>Execution status:</label></td>
					<td>${orderAndComment.order.executionStatus}</td>
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
				<td>${fn:substring(orderAndComment.order.creationDate, 0, 16)}</td>
			</tr>
			
			<form:hidden path="order.creationDate"/>
			<tr>
				<td><label>Due date:</label></td>
				<td>${fn:substring(orderAndComment.order.dueDate, 0, 16)}</td>
			</tr>
			
			<form:hidden path="order.dueDate"/>
			
			<tr>
				<td><label>Author:</label></td>
				<td>${orderAndComment.order.authorName} ${orderAndComment.order.authorSurname}</td>
			</tr>
			
			<form:hidden path="order.authorId"/>
			
			<c:if test="${isUser || isTech}">
				<tr>
					<td><label>Technician:</label></td>
					<td>${orderAndComment.order.technicianName} ${orderAndComment.order.technicianSurname}</td>
				</tr>
				
				<form:hidden path="order.technicianId"/>
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
				<td>${orderAndComment.order.overdue ? 'Yes' : 'No'}</td>
			</tr>
			
		</table>
	
		<p style="text-align: center">
			<c:if test="${isAdmin || isOrderAuthor || isOrderTech}">	
				<input type="submit" class="btn btn-success" value="Confirm changes" style="display:none;" />
				<input type="button" class="btn btn-success edit-button" value="Edit" />
			</c:if>
			<c:if test="${isOrderTech}">
				<input type="button" class="btn btn-success comment-button" value="Add comment" />
			</c:if>
			<c:if test="${isOrderAuthor && orderIsOpen}">
				<a class="btn btn-danger delete-button" href="../delete/id/${orderAndComment.order.orderId}">Delete order</a>
			</c:if>
			<input type="button" class="btn btn-cancell" value="Go back" />
		</p>
	
		<c:if test="${comments.size() != 0}">
			<h2>Comments</h2>
		</c:if>
		<c:if test="${comments.size() == 0 }">
			<h2>No comments</h2>
		</c:if>
		
		<table class="table table-bordered comments-table">
			<c:if test="${isOrderTech}">
				<tr class="newComment1">
					<td>${orderAndComment.order.technicianName} ${orderAndComment.order.technicianSurname}</td>
					<td rowspan="2"><form:textarea path="comment.comment" placeholder="Your new comment"/></td>
				</tr>
				<tr class="newComment2"><td></td></tr>
				<form:hidden path="comment.orderId" value="${orderAndComment.order.orderId}"/>
				<form:hidden id="comment-creation-date" path="comment.creationDate"/>
			</c:if>
			<c:if test="${comments.size() != 0}">
				<c:forEach var="comment" items="${comments}">
					<tr>
						<td>${orderAndComment.order.technicianName} ${orderAndComment.order.technicianSurname}</td>
						<td rowspan="2">${comment.comment}</td>
					</tr>
					<tr>
						<td>${fn:substring(comment.creationDate, 0, 16)}</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
	</form:form>
	<br>
</div>
<%@include file="footer.jsp"%>