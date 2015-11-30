<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@include file="header.jsp"%>

<script>
	function goBack() {
		window.history.back();
	}
</script>

<br>
<br>
<br>
<div class="panel panel-success">
	<div class="panel-heading">
		<h3 class="panel-title">Add new device:</h3>
	</div>
	<c:url var="saveUrl" value="/devices/add" />
	<form:form modelAttribute="device" method="POST" action="${saveUrl}">
		<table class="table table-striped table-bordered">
			<tr>
				<td><form:label path="deviceSerialId">Serial Number:</form:label></td>
				<td><form:input type="text" class="form-control"
						path="deviceSerialId" required="true" maxlength="15" /></td>
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
				<td><form:select path="roomId" id="roomNumberOptions"
						required="required">
						<option value="">-- Please choose one</option>
						<form:options items="${rooms}" itemValue="roomId"
							itemLabel="roomNumber" />
					</form:select></td>
			</tr>
		</table>
		<p style="text-align: center">
			<input type="submit" class="btn btn-success" value="Add" />
			<input type="button" onclick="goBack()" class="btn btn-cancell" value="Go back" />
		</p>
	</form:form>
</div>

<c:if test="${not empty unique}">
	<div class="error">${unique}</div>
</c:if>

<%@include file="footer.jsp"%>