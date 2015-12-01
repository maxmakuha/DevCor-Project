<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script src=<c:url value="/resources/js/bootstrap.min.js" />></script>
<c:if test="${!orders.isEmpty()}">
<div class="panel panel-success">
<label>There already are such orders for this room: </label><br>
<table class="table table-striped table-bordered" id="pagination">

		<thead bgcolor="#8FBC8F">
			<tr>
					<th>Problem Description</th>
					<th>Creation date</th>
					<th>Problem type</th>
					<th>Device serial id</th>
			</tr>
		</thead>	
			
		<c:forEach var="order" items="${orders}">
		<c:if test="${order.executionStatusId < 3}">
			<tr class=
			<c:choose>
				<c:when test="${order.overdue == 'Y' && order.executionStatusId < 3}">
					"order-overdue"
				</c:when>
				<c:when test="${order.executionStatus == 'Open'}">
					"order-open-not-overdue"
				</c:when>
				<c:when test="${order.executionStatus == 'In progress'}">
					"order-in-progress-not-overdue"
				</c:when>
			</c:choose>
			>
					<td><c:out value="${order.description}" /></td>
					<td><c:out value="${fn:substring(order.creationDate, 0, 16)}" /></td>
					<td><c:out value="${order.problemType}" /></td>
					<td><c:out value="${order.deviceSerialId}" /></td>
			</tr>
			</c:if>
		</c:forEach>
</table>
</div>
</c:if>