<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${!orders.isEmpty()}">
<c:if test="${orders.get(0).getExecutionStatusId()<3}">
<label>There already are such orders for this room: </label><br>
<table class="table table-striped table-bordered" id="pagination">

		<thead bgcolor="#8FBC8F">
			<tr>
				    <th>ID</th>
					<th>Creation date</th>
					<th>Problem type</th>
					<th>Device serial id</th>
					<th>Problem Description</th>
					<th>Execution status</th>
			</tr>
		</thead>	
			
		<c:forEach var="order" items="${orders}">
		<c:if test="${order.executionStatusId < 3 && order.getDeviceId() == device.getDeviceId()}">
		
		
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
					<td><c:out value="${order.creationDate}" /></td>
					<td><c:out value="${order.problemType}" /></td>
					<td><c:out value="${order.deviceSerialId}" /></td>
					<td><c:out value="${order.description}" /></td>
					<td><c:out value="${order.executionStatus}" /></td>
			</tr>
			</c:if>
		</c:forEach>
</table>
</c:if>
</c:if>