<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<html>
<head>
<title>${title}</title>
</head>
<body>
	<h2>Order : ${order}</h2>
	<h2>Comment : ${comment}</h2>
	<h2>Room : ${room}</h2>
	<h2>Device : ${device}</h2>
	<h2>Player : ${player}</h2>
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h3>
			Orders info : ${pageContext.request.userPrincipal.name} | <a
				href="<c:url value="/logout" />">Logout</a>
		</h3>
	</c:if>
</body>
</html>