<%@page session="false"%>
<html>
<head>
<link rel="shortcut icon"
	href="http://faviconka.ru/ico/faviconka_ru_12.ico" type="image/x-icon">
<link rel="stylesheet" href="resources/style/style.css">
<link rel="stylesheet" href="resources/style/bootstrap.min.css">
<link rel="stylesheet" href="resources/style/bootstrap-theme.min.css">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="resources/js/bootstrap.min.js"></script>

<title>Error403</title>
</head>
<body>
	
	<div class="jumbotron" id="error">
			<img src="resources/img/403.png" />
			<p class="lead">You don`t have permission to access this page!</p>
			<a class="btn btn-large btn-success" href="welcome">Home</a>
		</div>
	<%@include file="footer.jsp"%>
</body>
</html>