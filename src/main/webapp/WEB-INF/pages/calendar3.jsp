<%@include file="header.jsp"%>
<br>
<br>
<br>
<div class="container">
	<div id="calendar"></div>
</div>

<script>
var xmlhttp = new XMLHttpRequest();
xmlhttp.open("GET", "/DevCor/simple", false);
xmlhttp.send();
var order = JSON.parse(xmlhttp.responseText);
var getLength = function(obj) {
    var i = 0, key;
    for (key in obj) {
        if (obj.hasOwnProperty(key)){
            i++;
        }
    }
    return i;
};

$.getScript('http://arshaw.com/js/fullcalendar-1.6.4/fullcalendar/fullcalendar.min.js',function(){
	  var date = new Date();
	  var d = date.getDate();
	  var m = date.getMonth();
	  var y = date.getFullYear();
	  var evs = [];
	  var i = (getLength(order));
	  var j = 0;
	  jQuery.each(order, function(index, value) {
		  var one = {
				  title: order[j].description, 
		    	  start: new Date(order[j].year, order[j].month, order[j].day, order[j].hour, order[j].minute),
		    	  url: 'dashboard/order/id/'+order[j].orderId,
		    	  allDay: false
		  };
		  evs.push(one);
	       return (++j!= i);
	       });
	  $('#calendar').fullCalendar({
	    header: {
	      left: 'prev,next today',
	      center: 'title',
	      right: 'month,agendaWeek,agendaDay'
	    },
	    editable: true,
	    events: evs
	  });
	})
</script>