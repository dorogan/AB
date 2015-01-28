<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf8">
	<title><spring:message code="label.title" /></title>
	<c:url value="/resources/css/login.css" var="cssURL" />
	<link rel="stylesheet" type="text/css" media="screen" href="${cssURL}" />
</head>
<body>

<c:if test="${not empty param.error}">
	<font color="red"> <spring:message code="label.loginerror" />
	: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message} </font>
</c:if>
<form class="form-container" method="POST" action="<c:url value="/j_spring_security_check" />">
	<h4 align="center" class="form-title">
		<spring:message code="label.authorityplease" />
	</h4>
<table>
	<tr>
		<td align="right"><spring:message code="label.login" /></td>
		<td><input class="form-field" type="text" name="j_username" /></td>
	</tr>
	<tr>
		<td align="right"><spring:message code="label.password" /></td>
		<td><input class="form-field" type="password" name="j_password" /></td>
	</tr>
	<tr>
		<td align="right">
			<spring:message code="label.remember" />
			<input type="checkbox" name="_spring_security_remember_me" /></td>
		<td colspan="2" align="right">
			<input class="submit-button"type="submit" value="Login" />
			<input class="submit-button" type="reset" value="Reset" />
		</td>
	</tr>
	<tr>
		<td colspan="2" align="right">
			<a href="<c:url value="/registration" />">
				<spring:message code="label.registry" />
			</a><br/>
		</td>
	</tr>
</table>
</form>
</body>
</html>