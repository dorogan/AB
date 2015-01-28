<%@ page language="java" contentType="text/html; charset=utf8"
		 pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf8">
	<title><spring:message code="label.title" /></title>
</head>
<body>

<a href="<c:url value="/index" />">
	<h2><spring:message code="label.title" /></h2>
</a><br/>

<a href="<c:url value="/logout" />">
	<spring:message code="label.logout" />
</a>

<form:form method="post" action="add" commandName="action">

	<table>
		<tr>
			<td><form:label path="name">
				<spring:message code="label.actionname" />
			</form:label></td>
			<td><form:input path="name" /></td>
		</tr>
		<tr>
			<td><form:label path="description">
				<spring:message code="label.actiondescription" />
			</form:label></td>
			<td><form:input path="description" /></td>
		</tr>
		<tr>
			<td><form:label path="permission">
				<spring:message code="label.actionpermission" />
			</form:label></td>
			<td><form:input path="permission" /></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit"
								   value="<spring:message code="label.addaction"/>" /></td>
		</tr>
	</table>
</form:form>


</body>
</html>