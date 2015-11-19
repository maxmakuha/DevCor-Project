<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@include file="header.jsp"%>
<br>
<br>
<br>
<div class="panel panel-success">
	<div class="panel-heading">
		<h3 class="panel-title">Create new Device:</h3>
	</div>
	<c:url var="saveUrl" value="${room.roomId}/device/add" />
	<form:form modelAttribute="device" method="POST" action="${saveUrl}">
		<table class="table table-striped table-bordered">
			<tr>
				<td><form:label path="deviceSerialId">Serial Number:</form:label></td>
				<td><form:input type="text" class="form-control"
						path="deviceSerialId" required="true" /></td>
			</tr>
			<tr>
				<td><form:label for="deviceTypeOptions" path="deviceTypeId">Device type:</form:label></td>
				<td><form:select path="deviceTypeId" id="deviceTypeOptions"
						required="required">
						<option value="">-- Please choose one</option>
						<form:options items="${deviceTypes}" itemValue="deviceTypeId"
							itemLabel="deviceType" />
					</form:select></td>
			</tr>
			<tr>
				<td><form:label for="roomNumberOptions" path="roomId">Room number: </form:label></td>
			<td id="roomNumberOptions"> <!--<form:select path="roomId" id="roomNumberOptions"
						required="required">
						<form:option value = "${room.roomNumber}" selected>${room.roomNumber}</form:option>
					</form:select>
					${room.roomNumber}--></td>
			</tr>
		</table>
		<p style="text-align: center">
			<input type="submit" class="btn btn-success" value="Create" />
		</p>
	</form:form>
</div>
<%@include file="footer.jsp"%>