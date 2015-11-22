<%@page session="true"%>
<%@include file="header.jsp"%>
<%String date= new  java.text.SimpleDateFormat("yyyy-MM-dd").format( System.currentTimeMillis()); %>
<%String reportNum=request.getParameter("reportNum");%>

<div class="wrapper">
	<ul class="nav nav-tabs">
		<li role="presentation"><a href="<c:url value="/technicians"/>" style="color: GREEN">Technicians</a></li>
		<li role="presentation"><a href="<c:url value="/users"/>" style="color: GREEN">Users</a></li>
		<li role="presentation"><a href="<c:url value="/rooms"/>" style="color: GREEN">Rooms</a></li>
		<li role="presentation"><a href="<c:url value="/devices"/>" style="color: GREEN">Devices</a></li>
		<li role="presentation" class="active"><a href="<c:url value="/reports"/>" style="color: GREEN">Reports</a></li>
		<li role="presentation"><a href="<c:url value="/configuration"/>" style="color: GREEN">Configuration panel</a></li>
	</ul>

	<%if (reportNum==null) {%>
	<hr>
	
	<!-- Table of TypeOrders -->
	<div class="panel panel-success">
		<div class="panel-heading">
			<h3 class="panel-title">Reports</h3>
		</div>
		<table class="table table-striped table-bordered" id="report">
			<thead>
				<tr>
					<th>Title</th>
					<th>Description</th>
					<th>From date</th>
					<th>To date</th>
				</tr>
			</thead>

			<tr>
			<form action="reports">
				<td><input type="submit" style="width: 150px" class="btn btn-success" name="reportNum" value="OrdersReport" /></td>
				<td>Information about all Orders during specific period of time.</td>
				<td><input type="date" name="date1" value=<%=date%>	max=<%=date%> min="2010-01-01"></td>
				<td><input type="date" name="date2" value=<%=date%>	max=<%=date%> min="2010-01-01"></td>
			</form>
			</tr>
			
			
			<tr><form action="reports">
				<td><input type="submit" style="width: 150px" class="btn btn-success" name="reportNum" value="TechniciansReport" /></td>
				<td>Information about Technicians, overdue Orders and quantity of Orders of each Execution status during specific period of time.</td>
				<td><input type="date" name="date1" value=<%=date%> max=<%=date%> min="2010-01-01"></td>
				<td><input type="date" name="date2" value=<%=date%> max=<%=date%> min="2010-01-01"></td>
			</form></tr>
			
			<tr><form action="reports">
				<td><input type="submit"  style="width: 150px" class="btn btn-success" name="reportNum" value="DevicesReport" /></td>
				<td>Information about Devices and quantity of Orders where	Problem type = "Hardware" during specific period of time.</td>
				<td><input type="date" name="date1" value=<%=date%>	max=<%=date%> min="2010-01-01"></td>
				<td><input type="date" name="date2" value=<%=date%>	max=<%=date%> min="2010-01-01"></td>
			</form></tr>
		</table>
	</div>
	
	<!-- Table Report1 -->
	<%}else if(reportNum.equals("OrdersReport")){ %>
	<hr>
	<div class="panel panel-success">
		<div class="panel-heading">
			<h3 class="panel-title">OrdersReport</h3>
		</div>
		<table class="table table-striped table-bordered" id="report">
			<thead>
				<tr>
					<th>Creating date</th>
					<th>Due date</th>
					<th>Problem type</th>
					<th>Problem description</th>
					<th>Room number</th>
					<th>Serial number</th>
					<th>Execution status</th>
					<th>Urgency status</th>
					<th>Author</th>
					<th>Overdue</th>
					<th>Technician</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="report" items="${report}">
				<tr>
					<td>${report.creatingDate}</td>
					<td>${report.dueDate}</td>
					<td>${report.problemType}</td>
					<td>${report.problemDescription}</td>
					<td>${report.roomNumber}</td>
					<td>${report.serialNumber}</td>
					<td>${report.executionStatus}</td>
					<td>${report.urgencyStatus}</td>
					<td>${report.author}</td>
					<td>${report.overdue}</td>
					<td>${report.technician}</td>
				</tr>
			</c:forEach>	
			</tbody>
		</table>
	</div>
	<form action="DevCorReport">
	<input type="text" hidden="true" name="exel" value="EXCEL1">
	<button type="submit"  class="btn btn-xs btn-default">to xls</button>
	</form>
		
	<!-- Table Report2 -->
	<%}else if(reportNum.equals("TechniciansReport")){ %>
	<hr>
	<div class="panel panel-success">
		<div class="panel-heading">
			<h3 class="panel-title">TechniciansReport</h3>
		</div>
		<table class="table table-striped table-bordered" id="report">
			<thead>
				<tr>
					<th>Technician</th>
					<th>Open</th>
					<th>In progress</th>
					<th>Unsolvable</th>
					<th>Incorrect</th>
					<th>Finished</th>
					<th>Finished with Overdue</th>
					<th>Not finished with Overdue</th>
					<th>Total</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="report" items="${report2}">
				<tr>
					<td>${report.technician}</td>
					<td>${report.coutOfopenOrders}</td>
					<td>${report.countOfinprogressOrders}</td>
					<td>${report.countOfunsolvableOrders}</td>
					<td>${report.countOfincorrectOrders}</td>
					<td>${report.countOffinishedOrders}</td>
					<td>${report.countOffinishedwithOverdueOrders}</td>
					<td>${report.countOfnotfinishedwithOverdueOrders}</td>
					<td>${report.totalOrders}</td>
				</tr>
			</c:forEach>	
			</tbody>
		</table>
	</div>
	<form action="DevCorReport">
	<input type="text" hidden="true" name="exel" value="EXCEL2">
	<button type="submit"  class="btn btn-xs btn-default">to xls</button>
	</form>
	<!-- Table Report3 -->
	<%} else{%>
	<hr>
	<div class="panel panel-success">
		<div class="panel-heading">
			<h3 class="panel-title">DevicesReport</h3>
		</div>
		<table class="table table-striped table-bordered" id="report">
			<thead>
				<tr>
					<th>Serial number</th>
					<th>Device type</th>
					<th>Room number</th>
					<th>Orders quantity</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="report" items="${report3}">
				<tr>
					<td>${report.serialNumber}</td>
					<td>${report.deviseType}</td>
					<td>${report.roomNumber}</td>
					<td>${report.orderQuantity}</td>
				</tr>
			</c:forEach>	
			</tbody>
		</table>
	</div>
	<form action="DevCorReport">
	<input type="text" hidden="true" name="exel" value="EXCEL3">
	<button type="submit"  class="btn btn-xs btn-default">to xls</button>
	</form>
	<%}%>
	
</div>
<%@include file="footer.jsp"%>