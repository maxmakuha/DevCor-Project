<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="shortcut icon"
	href="http://faviconka.ru/ico/faviconka_ru_12.ico" type="image/x-icon">
<link rel="stylesheet" href="resources/style/style.css">
<link rel="stylesheet" href="resources/style/bootstrap.min.css">
<link rel="stylesheet" href="resources/style/bootstrap-theme.min.css">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="resources/js/bootstrap.min.js"></script>

<title>DevCor</title>
</head>
<body onload='document.login.username.focus();'>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<a href="welcome"> <img src="resources/img/logo.png"
					class="img-rounded"></a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav ">
					<li><a href="welcome" id="button16">Home</a></li>
					<li><a href="about" id="button16">About</a></li>
				</ul>
				<security:authorize access="isAuthenticated()">
					<form class="navbar-form navbar-right" action="logout">
						<button type="submit" class="btn btn-success btn-sm">
							<span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
							Log out: ${pageContext.request.userPrincipal.name}
						</button>
					</form>
					<form class="navbar-form navbar-right" action="profile">
						<button type="submit" class="btn btn-success btn-sm">
							<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
							My Profile
						</button>
					</form>
					<form class="navbar-form navbar-right" action="dashboard">
						<button type="submit" class="btn btn-success btn-sm">
							<span class="glyphicon glyphicon-tags" aria-hidden="true"></span>
							Orders
						</button>
					</form>
				</security:authorize>
				<security:authorize access="hasRole('ROLE_ADMIN')">
					<form class="navbar-form navbar-right" action="technicians">
						<button type="submit" class="btn btn-success btn-sm">
							<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
							Management
						</button>
					</form>
				</security:authorize>
			</div>
		</div>
	</nav>