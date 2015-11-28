<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="header.jsp"%>

<script>
	$(document).ready(function() {

		$('#roomNumberOptions').change(function() {
			$.get('/DevCor/getRoomDevices', {
				roomId : $(this).val()
			}, function(responseHTML) {
				$('#serialPortOptions').html(responseHTML);
			})
		});
		
		$('#roomNumberOptions').change(function() {
		$.get('/DevCor/getDuplicateOrdersRoom', {
			roomId : $(this).val()
		}, function(responseHTML) {
			$('#duplicates').html(responseHTML);
		})
		});
		
		$('#serialPortOptions').change(function() {
			$.get('/DevCor/getDuplicateOrdersDevice', {
				roomId : $('#roomNumberOptions').val(),
				deviceId : $(this).val()
			}, function(responseHTML) {
				$('#duplicates').html(responseHTML);
			})
			});

		$('input[type=button]').click(function() {
			window.location = "../dashboard";
		});

		function h(e) {
			$(e).css({
				'height' : 'auto',
				'overflow-y' : 'hidden'
			}).height(e.scrollHeight);
		}
		$('textarea').each(function() {
			h(this);
		}).on('input', function() {
			h(this);
		});
	});
</script>

<br>
<br>
<br>
<div class="panel panel-success">
	<div class="panel-heading">
		<h3 class="panel-title">Please, fill the following fields to
			create an order</h3>
	</div>
	<form:form modelAttribute="order" class="order-and-comment-form">
		<table class="table table-striped table-bordered">
			<tr>
				<td><label for="problemTypeOptions">Problem type: </label></td>
				<td><form:select size="1" path="problemTypeId"
						id="problemTypeOptions" required="required">
						<option value="">Please choose one</option>
						<form:options items="${problemTypes}" itemValue="problemTypeId"
							itemLabel="problemType" />
					</form:select></td>
			</tr>
			<tr>
				<td><label for="descriptionText">Problem description: </label></td>
				<td><form:textarea path="description" id="descriptionText" /></td>
			</tr>
			<tr>
				<td><label for="roomNumberOptions">Room number: </label></td>
				<td><form:select path="roomId" id="roomNumberOptions"
						required="required">
						<option value="">Please choose one</option>
						<form:options items="${rooms}" itemValue="roomId"
							itemLabel="roomNumber" />
					</form:select></td>
			</tr>
			<tr>
				<td><label for="serialPortOptions">Serial port: </label></td>
				<td><form:select size="1" path="deviceId"
						id="serialPortOptions">
						<option value="-1">Not specified</option>
					</form:select></td>
			</tr>
			<tr>
				<td><label for="urgencyStatusOptions">Urgency status: </label></td>
				<td><form:select size="1" path="urgencyStatusId"
						id="urgencyStatusOptions" required="required">
						<option value="">Please choose one</option>
						<form:options items="${urgencyStatuses}"
							itemValue="urgencyStatusId" itemLabel="urgencyStatus" />
					</form:select></td>
			</tr>
		</table>
		<p style="text-align: center">
			<input type="submit" class="btn btn-success" value="Complete" /> <input
				type="button" class="btn btn-cancell" value="Cancel" />
		</p>	
	<div id="duplicates"/>	
	</form:form>
</div>
<%@include file="footer.jsp"%>