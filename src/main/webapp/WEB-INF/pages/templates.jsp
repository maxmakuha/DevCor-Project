<%@include file="header.jsp"%>
	<div class="wrapper">
		<!-- Серая панель по центру-->
		<div id="panel">
			<button type="button" class="btn btn-success">Button</button> 
			<Hr>
				<!-- таблица для профайла -->
				<div class="panel panel-success">
				  <div class="panel-heading">
				    <h3 class="panel-title">Profile</h3>
				  </div>
				  
				  	<table  class="table" id="table">
					<tbody>
						<tr>
							<td>Name:</td>
							<td> <input type="text"  class="form-control" value="Vasia"></td>
						</tr>
						<tr>
							<td>Email:</td>
							<td> <input type="text" class="form-control" value="vasia@gmail.ru"></td>
						</tr>
					</tbody>
					</table>
				 
				</div>
				<!-- dropdown -->
				<div class="dropdown">
					<button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">				    Dropdown
				    <span class="caret"></span>
				  </button>
				  <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
				    <li><a href="#">Action</a></li>
				    <li><a href="#">Another action</a></li>
				    <li><a href="#">Another action</a></li>
				    <li><a href="#">Another action</a></li>
				  </ul>
				</div>

				<Hr>
				<!-- навигация1 -->
				<ul class="nav nav-tabs">
				  <li role="presentation" class="active"><a href="about2">Orders</a></li>
				  <li role="presentation"><a href="about2">Rooms</a></li>
				  <li role="presentation"><a href="about2">Devices</a></li>
				</ul>
				
				<Hr>
				<!-- навигация2 -->
				<ul class="nav nav-tabs">
				 	<li><a href="about2">My Profile</a></li>
				    <li><a href="about2">Orders</a></li>
				  <li role="presentation" class="dropdown">
				    <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
				      Settings <span class="caret"></span>
				    </a>
				    <ul class="dropdown-menu">
				     	<li role="presentation" class="active"><a href="#">Users</a></li>
						  <li role="presentation"><a href="#">Rooms</a></li>
						  <li role="presentation"><a href="#">Devices</a></li>
				    </ul>
				  </li>
				</ul>
				
				<!-- страницы -->
				<nav>
				  <ul class="pagination">
				    <li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
				    <li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
				    <li class=""><a href="#">2 <span class="sr-only"></span></a></li>
				    <li class=""><a href="#">3 <span class="sr-only"></span></a></li>
				    <li class="disabled"><a href="#" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
				  </ul>
				</nav>
				<!-- alert1 -->
				<div class="alert alert-success" role="alert">Good job!</div>
				<!-- alert2 -->
				<div class="alert alert-danger" role="alert">You have overdue Orders</div>
				
				
				  <!-- таблица заказов -->
				  <div class="panel panel-success">
				  <div class="panel-heading">
				    <h3 class="panel-title">Orders</h3>
				  </div>
				  <table class="table" id="table">
					
						<tr>
							<th >Name</th>
							<th>Room</th>
							<th>Execution status</th>
							<th>Overdue</th>
						</tr>
						<tr>
							<td><a href="#">Order1</a></td>
							<td>3-231</td>
							<td>Open</td>
							<td><input type="radio" checked></td>
						</tr>
						<tr>
							<td><a href="#">Order2</a></td>
							<td>1-123</td>
							<td>Open</td>
							<td></td>
						</tr>
					
				  </table>
				</div>
				
				<Hr>
				 <!-- Кнопка с цифрой -->
				<button class="btn btn-success" type="button"> Orders <span class="badge">4</span></button>
				<Hr>
				  <!-- Списки -->
				<ul class="list-group">
				  <li class="list-group-item">Item1</li>
				  <li class="list-group-item">Item2</li>
				  <li class="list-group-item">Item3</li>
				  <li class="list-group-item">Item4</li>
				</ul>
	</div>
</div>
<%@include file="footer.jsp"%>