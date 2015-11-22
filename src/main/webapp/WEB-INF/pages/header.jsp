<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="shortcut icon"
	href="http://faviconka.ru/ico/faviconka_ru_12.ico" type="image/x-icon">
<link rel="stylesheet"
	href="<c:url value="/resources/style/style.css"/>">
<link rel="stylesheet"
	href="<c:url value="/resources/style/bootstrap.min.css"/>">
<link rel="stylesheet"
	href="<c:url value="/resources/style/bootstrap-theme.min.css"/>">
<link rel="stylesheet"
	href="<c:url value="/resources/style/jquery.dialogbox.css"/>">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/js/javascript.js"/>"></script>
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script
	src="https://cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdn.datatables.net/1.10.10/js/dataTables.bootstrap.min.js"></script>
<title>DevCor</title>
</head>
<body onload='document.login.username.focus();'>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<a href="<c:url value="/welcome"/>"><img
					src="<c:url value="/resources/img/logo.png"/>" class="img-rounded" /></a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav ">
					<li><a href="<c:url value="/welcome"/>" id="button16">Home</a></li>
					<li><a href="<c:url value="/about"/>" id="button16">About</a></li>
				</ul>
				<security:authorize access="isAuthenticated()">
					<form class="navbar-form navbar-right"
						action="<c:url value="/logout"/>">
						<button type="submit" class="btn btn-success btn-sm">
							<span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
							Log out: ${pageContext.request.userPrincipal.name}
						</button>
					</form>
					<form class="navbar-form navbar-right"
						action="<c:url value="/profile"/>">
						<button type="submit" class="btn btn-success btn-sm">
							<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
							My Profile
						</button>
					</form>
					<form class="navbar-form navbar-right"
						action="<c:url value="/dashboard"/>">
						<button type="submit" class="btn btn-success btn-sm">
							<span class="glyphicon glyphicon-tags" aria-hidden="true"></span>
							Orders
						</button>
					</form>
				</security:authorize>
				<security:authorize access="hasRole('ROLE_ADMIN')">
					<form class="navbar-form navbar-right"
						action="<c:url value="/technicians"/>">
						<button type="submit" class="btn btn-success btn-sm">
							<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
							Management
						</button>
					</form>
				</security:authorize>
			</div>
		</div>
	</nav>