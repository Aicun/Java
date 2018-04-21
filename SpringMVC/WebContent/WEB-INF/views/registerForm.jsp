<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%><!-- spring framework tags -->
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%><!-- spring framework tags -->
<%@ page session="false"%>
<html>
<head>
<title>Spittr</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/style.css" />">
</head>
<body>
	<h1>
		<s:message code="spittr.register" />
	</h1>
	<s:url value="/spittles" htmlEscape="true">
		<s:param name="max" value="60" />
		<s:param name="count" value="20" />
	</s:url>
	<s:escapeBody htmlEscape="true">
		<h1>Hello</h1>
	</s:escapeBody>
	<sf:form method="POST" commandName="spittle" enctype="multipart/form-data">
		<sf:errors path="*" element="div" cssClass="errors" />
		<sf:label path="message" cssErrorClass="error">message</sf:label>:
		<sf:input path="message" cssErrorClass="error" />
		<br />
		<!-- message: <sf:input path="message"/><sf:errors path="message" cssClass="error" /><br/> -->
		time: <sf:input path="time" />
		<br />
		latitude: <sf:input path="latitude" />
		<br />
		longitude: <sf:input path="longitude" />
		<br />
		<label>Profile Picture</label>:
		<input type="file" name="profilePicture"
			accept="image/jpeg,image/png,image/gif" />
		<br />
		<input type="submit" value="Register" />
	</sf:form>
</body>
</html>