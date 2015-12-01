<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@include file="header.jsp"%>
<%@page session="true"%>

<script src=<c:url value="/resources/js/bootstrap.min.js" />></script>
<script>
$(document).ready(function(){
    $('[data-toggle="popover"]').popover();
});
</script>

<security:authorize access="hasRole('ROLE_USER')" var="isUser"/>
<security:authorize access="hasRole('ROLE_TECHNICIAN')" var="isTech"/>
<security:authorize access="hasRole('ROLE_ADMIN')" var="isAdmin"/>

<br>
<br>
<br>

<c:if test="${not empty message}">
	<div class="msg">${message}</div>
</c:if>

<c:if test="${not empty profile}">
	<div class="msg">${profile}</div>
</c:if>

<c:if test="${isUser}">
	<div class="col-lg-7" style="margin-left: 10px;">
		<a type="submit" class="btn btn-success btn-sm" href="dashboard/order/create">Create order</a>
	</div>
</c:if>

<br>
<br>
<div class="panel panel-success">
	<div class="panel-heading">
		<h3 class="panel-title">Orders</h3>
	</div>
	<br>
	<div>
	<div style="float: left; width: 85%">
	<br>
	<br>
	<br>
	<a href="#" data-toggle="popover" data-html="true" title="Order colors" data-content="
		<div class='order-overdue'></div> - Overdued orders
		<br>
		<div class='order-open-not-overdue'></div> - Opened and not overdued orders
		<br>
		<div class='order-in-progress-not-overdue'></div> - In progress and not overdued orders
		<br>
		<div class='order-finished'></div> - Finished orders
		<br>
		<div class='order-incorrect-or-unsolvable'></div> - Incorrect or Unsolvable orders
	">What does all these colors mean?</a>
	</div>
	<div>
	<c:if test="${isTech}">
	<%@include file="calendar2.jsp"%>
	</c:if>
	</div>
	</div>
	<table class="table table-striped table-bordered" id="pagination">
		<thead bgcolor="#8FBC8F">
			<tr>
				<c:if test="${isTech}">
					<th>Message</th>
					<th>Due date</th>
					<th>Problem type</th>
				</c:if>
				<c:if test="${isUser}">
					<th>Creation date</th>
					<th>Due date</th>
					<th>Problem type</th>
					<th>Room</th>
					<th>Technician</th>
				</c:if>
				<c:if test="${isAdmin}">
					<th>Author</th>
					<th>Technician</th>
					<th>Creation date</th>
					<th>Due date</th>
					<th>Problem type</th>
					<th>Room</th>
					<th>Device serial id</th>
					<th>Execution status</th>
				</c:if>
					<th>View</th>
			</tr>
		</thead>
		<c:forEach var="order" items="${orders}">
			<tr class=
			<c:choose>
				<c:when test="${order.overdue && order.executionStatusId < 3}">
					"order-overdue"
				</c:when>
				<c:when test="${order.executionStatus == 'Open'}">
					"order-open-not-overdue"
				</c:when>
				<c:when test="${order.executionStatus == 'In progress'}">
					"order-in-progress-not-overdue"
				</c:when>
				<c:when test="${order.executionStatus == 'Finished'}">
					"order-finished"
				</c:when>
				<c:otherwise>
					"order-incorrect-or-unsolvable"
				</c:otherwise>
			</c:choose>
			>
				<c:if test="${isTech}">
					<td><c:out value="${order.description}" /></td>
					<td><c:out value="${fn:substring(order.dueDate, 0, 16)}" /></td>
					<td><c:out value="${order.problemType}" /></td>
				</c:if>
				<c:if test="${isUser}">
					<td><c:out value="${fn:substring(order.creationDate, 0, 16)}" /></td>
					<td><c:out value="${fn:substring(order.dueDate, 0, 16)}" /></td>
					<td><c:out value="${order.problemType}" /></td>
					<td><c:out value="${order.roomNumber}" /></td>
					<td><c:out value="${order.technicianName} ${order.technicianSurname}" /></td>
				</c:if>
				<c:if test="${isAdmin}">
					<td><c:out value="${order.authorName} ${order.authorSurname}" /></td>
					<td><c:out value="${order.technicianName} ${order.technicianSurname}" /></td>
					<td><c:out value="${fn:substring(order.creationDate, 0, 16)}" /></td>
					<td><c:out value="${fn:substring(order.dueDate, 0, 16)}" /></td>
					<td><c:out value="${order.problemType}" /></td>
					<td><c:out value="${order.roomNumber}" /></td>
					<td><c:out value="${order.deviceSerialId}" /></td>
					<td><c:out value="${order.executionStatus}" /></td>
				</c:if>
				<td><a href="dashboard/order/id/${order.orderId}"
					id="${order.orderId}"><span class="glyphicon glyphicon-edit"
						aria-hidden="true"></span></a></td>
			</tr>
		</c:forEach>
	</table>
</div>