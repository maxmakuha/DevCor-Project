<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="header.jsp"%>
<%@page session="true"%>

<c:set var="role" value="${pageContext.request.userPrincipal.authorities.iterator().next().authority}" />
<c:set var="isUser" value="${role == 'ROLE_USER'}"/>
<c:set var="isTech" value="${role == 'ROLE_TECHNICIAN'}"/>
<c:set var="isAdmin" value="${role == 'ROLE_ADMIN'}"/>

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
		<a type="submit" class="btn btn-success btn-sm" href="order/create">Create order</a>
	</div>
</c:if>

<br>
<br>
<div class="panel panel-success">
	<div class="panel-heading">
		<h3 class="panel-title">Orders</h3>
	</div>
	<br>
	<table class="table table-striped table-bordered" id="pagination">
		<thead bgcolor="#8FBC8F">
			<tr>
				<th>ID</th>
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
			<tr
			<c:choose>
				<c:when test="${order.overdue == 'Y' && order.executionStatusId < 3}">
					style="background-color: red;"
				</c:when>
				<c:when test="${order.executionStatus == 'Open'}">
					style="background-color: lightgreen;"
				</c:when>
				<c:when test="${order.executionStatus == 'In progress'}">
					style="background-color: yellow;"
				</c:when>
				<c:when test="${order.executionStatus != 'Finished'}">
					style="background-color: lightgrey;"
				</c:when>
				<c:otherwise>
					style="background-color: cyan;"
				</c:otherwise>
			</c:choose>
			>
				<td><c:out value="${order.orderId}" /></td>
				<c:if test="${isTech}">
					<td><c:out value="${order.description}" /></td>
					<td><c:out value="${order.dueDate}" /></td>
					<td><c:out value="${order.problemType}" /></td>
				</c:if>
				<c:if test="${isUser}">
					<td><c:out value="${order.creationDate}" /></td>
					<td><c:out value="${order.dueDate}" /></td>
					<td><c:out value="${order.problemType}" /></td>
					<td><c:out value="${order.roomNumber}" /></td>
					<td><c:out value="${order.technicianName} ${order.technicianSurname}" /></td>
				</c:if>
				<c:if test="${isAdmin}">
					<td><c:out value="${order.authorName} ${order.authorSurname}" /></td>
					<td><c:out value="${order.technicianName} ${order.technicianSurname}" /></td>
					<td><c:out value="${order.creationDate}" /></td>
					<td><c:out value="${order.dueDate}" /></td>
					<td><c:out value="${order.problemType}" /></td>
					<td><c:out value="${order.roomNumber}" /></td>
					<td><c:out value="${order.deviceSerialId}" /></td>
					<td><c:out value="${order.executionStatus}" /></td>
				</c:if>
				<td><a href="order/id/${order.orderId}"
					id="${order.orderId}"><span class="glyphicon glyphicon-edit"
						aria-hidden="true"></span></a></td>
			</tr>
		</c:forEach>
	</table>
</div>