<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="header.jsp"%>
<%@page session="true"%>

<html>
<head>
<title>Orders Info</title>
</head>
<body>
	<div class="container">
		<div class="wrapper">
			<div class="panel">
				<c:if test="${not empty message}">
					<div class="msg">${message}</div>
				</c:if>
				<c:if test="${role == 'ROLE_USER'}">
				<form class="navbar-form" action="createOrder">
					<button type="submit" class="btn btn-success btn-sm">
						New order
					</button>
				</form>
				</c:if>
				<h1>All orders:</h1>
				<div class="list-group">
					<c:forEach items="${orders}" var="order">
						<form:form class="list-group-item" action="singleOrder">
							<p>${order.getDescription()}</p>
							<span><b>Creation date:</b> ${order.getCreationDate()}</span>
							<span><b>Urgency status:</b> ${order.getUrgencyStatus()}</span>
							<span><b>Problem type:</b> ${order.getProblemType()}</span>
							<input name="orderId" value="${order.getOrderId()}" style="display:none;"/>
							<input type="submit" class="btn btn-success btn-sm" value="Details"/>
						</form:form>
					</c:forEach>
				</div>
				
				<c:if test="${pageContext.request.userPrincipal.name != null}">
					<h3>
						Orders info : ${pageContext.request.userPrincipal.name} | <a
							href="<c:url value="/logout" />">Logout</a>
					</h3>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>