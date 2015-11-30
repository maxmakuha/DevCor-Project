<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet"
	href="<c:url value="/resources/style/style.css"/>">

<table id="calendar2">
  <thead>
    <tr><td colspan="2">Prev</td><td colspan="3"></td><td colspan="2">Next</td></tr>
   <tr><td >Mon</td><td>Tue</td><td>Wed</td><td>Thu</td><td>Fri</td><td>Sat</td><td>Sun</td></tr>
	</thead>
  <tbody>
</table>

<div id="calendarTable">
  <c:forEach var="order" items="${orders}">
  <c:if test="${order.executionStatusId < 3}">
  <div data-dd="${order.getDueDate().getDate()}" data-mm="${order.getDueDate().getMonth()+1}" 
  data-yyyy="${order.getDueDate().getYear()+1900}"
  data-text="${order.getDescription()}" data-link="dashboard/order/id/${order.getOrderId()}"></div>
  </c:if>
  </c:forEach>
</div>

<script>
function Calendar2(id, year, month) {
	var Dlast = new Date(year,month+1,0).getDate(),
	D = new Date(year,month,Dlast),
	DNlast = new Date(D.getFullYear(),D.getMonth(),Dlast).getDay(),
	DNfirst = new Date(D.getFullYear(),D.getMonth(),1).getDay(),
	calendar = '<tr>',
	month=["January","February","March","April","May","June","July","August","September","October","November","December"];
	soob = document.querySelectorAll('#calendarTable div');;
	if (DNfirst != 0) {
	for(var i = 1; i < DNfirst; i++) calendar += '<td>';
	}else{
	for(var i = 0; i < 6; i++) calendar += '<td>';
	}
	for(var i = 1; i <= Dlast; i++) {
	calendar += '<td>' + i;
	if (new Date(D.getFullYear(),D.getMonth(),i).getDay() == 0) {
	calendar += '<tr>';
	}}
	for(var i = DNlast; i < 7; i++) calendar += '<td>&nbsp;';
	document.querySelector('#'+id+' tbody').innerHTML = calendar;
	document.querySelector('#'+id+' thead td:nth-child(2)').innerHTML = month[D.getMonth()] +' '+ D.getFullYear();
	document.querySelector('#'+id+' thead td:nth-child(2)').dataset.month = D.getMonth();
	document.querySelector('#'+id+' thead td:nth-child(2)').dataset.year = D.getFullYear();

	for (var k = 0; k < soob.length; k++) {
	if (soob[k].dataset.yyyy == D.getFullYear() && soob[k].dataset.mm - 1 == D.getMonth()) {
	var mytd = document.querySelectorAll('#'+id+' tbody td');
	for (var p = 0; p < mytd.length; p++) {
	if (soob[k].dataset.dd == mytd[p].innerHTML) {
	if (soob[k].dataset.link) {
	mytd[p].innerHTML = '<a href="' + soob[k].dataset.link + '" target="_blank">' + mytd[p].innerHTML + '</a>';
	}
	if (soob[k].dataset.text) {
	mytd[p].title = soob[k].dataset.text;
	}}}}}}
	Calendar2("calendar2", new Date().getFullYear(), new Date().getMonth());
	document.querySelector('#calendar2 thead tr:nth-child(1) td:nth-child(1)').onclick = function() {
	Calendar2("calendar2", document.querySelector('#calendar2 thead td:nth-child(2)').dataset.year, parseFloat(document.querySelector('#calendar2 thead td:nth-child(2)').dataset.month)-1);
	}
	document.querySelector('#calendar2 thead tr:nth-child(1) td:nth-child(3)').onclick = function() {
	Calendar2("calendar2", document.querySelector('#calendar2 thead td:nth-child(2)').dataset.year, parseFloat(document.querySelector('#calendar2 thead td:nth-child(2)').dataset.month)+1);
	} 
</script>