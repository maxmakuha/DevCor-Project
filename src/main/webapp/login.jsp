<html>
<head>
  <%@include file="includes.jsp"%>
  <title>DevCor</title>
</head>
<body>
<%@include file="header.jsp"%>
<div class = "container">
	<div class="wrapper">
		<form action="" method="post" name="Login_Form" class="form-signin">       
		    <h3 class="form-signin-heading">Sign in to get started</h3>
			  <hr class="colorgraph"><br>
			  <input type="text" class="form-control" name="Username" placeholder="Username" required="" autofocus="" />
			  <input type="password" class="form-control" name="Password" placeholder="Password" required=""/>     		  	 
			  <button class="btn btn-large btn-success btn-block"  name="Submit" value="Login" type="Submit">Login</button>  			
		</form>			
	</div>
</div>
    <%@include file="footer.jsp"%>
 </body>
</html>
