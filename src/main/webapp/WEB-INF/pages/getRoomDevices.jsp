<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<option value="-1">Not specified</option>
<c:forEach items="${devices}" var="device">
	<option value="${device.getDeviceId()}">${device.getDeviceSerialId()}</option>
</c:forEach>