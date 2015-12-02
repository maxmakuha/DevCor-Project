<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="false"%>
<html>
<head>
<link rel="shortcut icon"
	href="http://faviconka.ru/ico/faviconka_ru_12.ico" type="image/x-icon">
<link rel="stylesheet"
	href="<c:url value="/resources/style/bootstrap.min.css"/>">
<link rel="stylesheet"
	href="<c:url value="/resources/style/bootstrap-theme.min.css"/>">

<title>Error 500</title>
</head>
<body>
	<p style="text-align: center">
		<img src="<c:url value="/resources/img/500.png"/>" /> <br> <a
			class="btn btn-large btn-success" href="/DevCor/welcome">Home</a>
	</p>
	<!--  ${ex.message} -->
</body>
</html>