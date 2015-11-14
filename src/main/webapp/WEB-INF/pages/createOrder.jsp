<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="header.jsp"%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="resources/js/createOrder.js"></script>
<title>${title}</title>
<div class="container">
	<div class="wrapper">
		<div id="panel">
		
			<c:if test="${not empty message}">
				<div>${message}</div>
			</c:if>
			<h2>Please, fill the following fields to create an order</h2>
			<form:form modelAttribute="order" class="createOrder">
				<label for="problemTypeOptions">Problem type: </label>
				<form:select path="problemTypeId" id="problemTypeOptions" required="required">
					<option value="">--Please choose one</option>
					<form:options items="${problemTypes}" itemValue="problemTypeId" itemLabel="problemType" />
				</form:select>
				<br />
				<label for="descriptionText">Problem description: </label>
				<br />
				<form:textarea path="description" id="descriptionText" />
				<br />
				<label for="roomNumberOptions">Room number: </label>
				<form:select path="roomId" id="roomNumberOptions" required="required">
					<option value="">--Please choose one</option>
					<form:options items="${rooms}"  itemValue="roomId" itemLabel="roomNumber" />
				</form:select>
				<br />
				<label for="serialPortOptions">Serial port: </label>
				<form:select path="deviceId" id="serialPortOptions">
					<option value="-1">--Not specified</option>
				</form:select>
				<br />
				<label for="urgencyStatusOptions">Urgency status: </label>	
				<form:select path="urgencyStatusId" id="urgencyStatusOptions" required="required">
					<option value="">--Please choose one</option>
					<form:options items="${urgencyStatuses}" itemValue="urgencyStatusId" itemLabel="urgencyStatus" />
				</form:select>
				<br />
				<input type="submit" value="Complete" />
				<input type="button" value="Cancell" />
			</form:form>
		
		</div>
	</div>
</div>

<%@include file="footer.jsp"%>