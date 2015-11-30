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
		<h3 class="panel-title">Edit device: #${device.deviceId}</h3>
	</div>
	<c:url var="saveUrl" value="${page}" />
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
						<c:forEach var="dtype" items="${deviceTypes}">
							<c:choose>
								<c:when
									test="${device.getDeviceTypeObj().getDeviceType()==dtype.getDeviceType()}">
									<form:option selected="true" value="${dtype.deviceTypeId}"
										label="${dtype.getDeviceType()}" />
								</c:when>
								<c:otherwise>
									<form:option value="${dtype.deviceTypeId}"
										label="${dtype.getDeviceType()}" />
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select></td>
			</tr>
			<tr>
				<td><form:label for="roomNumberOptions" path="roomId">Room number: </form:label></td>
				<td><form:select path="roomId" id="roomNumberOptions"
						required="required">
						<c:forEach var="room" items="${rooms}">
							<c:choose>
								<c:when
									test="${device.getRoomObj().getRoomNumber()==room.getRoomNumber()}">
									<form:option selected="true" value="${room.roomId}"
										label="${room.getRoomNumber()}" />
								</c:when>
								<c:otherwise>
									<form:option value="${room.roomId}"
										label="${room.getRoomNumber()}" />
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select></td>
			</tr>
		</table>
		<form:hidden path="deviceId" required="true" />
		<p style="text-align: center">
			<input type="submit" class="btn btn-success" value="Save" />
			<input type="button" onclick="goBack()" class="btn btn-cancell" value="Go back" />
		</p>
	</form:form>
</div>

<c:if test="${not empty unique}">
	<div class="error">${unique}</div>
</c:if>

<%@include file="footer.jsp"%>