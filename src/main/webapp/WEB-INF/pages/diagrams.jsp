<%@page session="true"%>
<%@include file="header.jsp"%>
<script src="https://www.google.com/jsapi"></script>
  <script>
   google.load("visualization", "1", {packages:["corechart"]});
   google.setOnLoadCallback(drawChart);
   function drawChart() {
	var open =document.getElementById("open").firstChild.nodeValue;
	var inProgress =document.getElementById("inProgress").firstChild.nodeValue;
	var unsolvable =document.getElementById("unsolvable").firstChild.nodeValue;
	var incorrect =document.getElementById("incorrect").firstChild.nodeValue;
	var finished =document.getElementById("finished").firstChild.nodeValue;
	var overdueFinished =document.getElementById("overdueFinished").firstChild.nodeValue;
	var notOverdueFinished =document.getElementById("notOverdueFinished").firstChild.nodeValue;
	
    var data = google.visualization.arrayToDataTable([
     ['status', 'count'],
   	 ['open',   parseInt(open)],
     ['in Progress', parseInt(inProgress)],
     ['unsolvable',   parseInt(unsolvable)],
     ['incorrect', parseInt(incorrect)],
     ['finished', parseInt(finished)],
     ['finished with overdue ',parseInt(overdueFinished)],
     ['not finished with overdue',parseInt(notOverdueFinished)]
    ]);
    var options = {
     is3D: true,
    };
    var chart = new google.visualization.PieChart(document.getElementById('diagram'));
     chart.draw(data, options);
   }
  </script>
<div class="wrapper">
<hr>
	<div class="panel panel-success">
		<div class="panel-heading">
			<h3 class="panel-title">${report.technician}</h3>
		</div>
		<table class="table table-striped table-bordered" id="technitiansReport">
			<thead>
				<tr>
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
				<tr>
					<td id="open">${report.coutOfopenOrders}</td>
					<td id="inProgress">${report.countOfinprogressOrders}</td>
					<td id="unsolvable">${report.countOfunsolvableOrders}</td>
					<td id="incorrect">${report.countOfincorrectOrders}</td>
					<td id="finished">${report.countOffinishedOrders}</td>
					<td id="overdueFinished">${report.countOffinishedwithOverdueOrders}</td>
					<td id="notOverdueFinished">${report.countOfnotfinishedwithOverdueOrders}</td>
					<td id="total">${report.totalOrders}</td>
				</tr>
			
			</tbody>
		</table>
</div>
<div id="diagram"></div>
</div>
<%@include file="footer.jsp"%>