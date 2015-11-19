<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="header.jsp"%>
<%@page session="true"%>

<br>
<br>
<br>

<c:if test="${not empty message}">
	<div class="msg">${message}</div>
</c:if>

<c:if test="${role == 'ROLE_USER'}">
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
				<th>Message</th>
				<th>Creation date</th>
				<th>Due date</th>
				<th>Problem type</th>
				<th>View</th>
			</tr>
		</thead>
		<c:forEach var="order" items="${orders}">
			<tr>
				<td><c:out value="${order.getOrderId()}" /></td>
				<td><c:out value="${order.getDescription()}" /></td>
				<td><c:out value="${order.getCreationDate()}" /></td>
				<td><c:out value="${order.getDueDate()}" /></td>
				<td><c:out value="${order.getProblemType()}" /></td>
				<td><a href="order/id/${order.orderId}"
					id="${order.orderId}"><span class="glyphicon glyphicon-edit"
						aria-hidden="true"></span></a></td>
			</tr>
		</c:forEach>
	</table>
</div>