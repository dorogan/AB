<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf8">
  <title><spring:message code="label.title" /></title>
  <c:url value="/resources/css/registry.css" var="cssURL" />
  <link rel="stylesheet" type="text/css" media="screen" href="${cssURL}" />
</head>
<body>

<c:if test="${not empty param.error}">
  <font color="red"> <spring:message code="label.loginerror" />
    : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message} </font>
</c:if>
<form class="form-container" method="POST" action="/registration/add">
  <h4 align="center" class="form-title">
    <spring:message code="label.enterregistry" />
  </h4>
  <table>
    <tr>
      <td align="right"><spring:message code="label.login" /></td>
      <td><input class="form-field" type="text" name="login" /></td>
    </tr>
    <tr>
      <td align="right"><spring:message code="label.name" /></td>
      <td><input class="form-field" type="text" name="firstname" /></td>
    </tr>
    <tr>
      <td align="right"><spring:message code="label.secondname" /></td>
      <td><input class="form-field" type="text" name="lastname" /></td>
    </tr>
    <tr>
      <td align="right"><spring:message code="label.mail" /></td>
      <td><input class="form-field" type="text" name="email" /></td>
    </tr>
    <tr>
      <td align="right"><spring:message code="label.password" /></td>
      <td><input class="form-field" type="password" name="password" /></td>
    </tr>
    <tr>
      <td class="submit-container" colspan="2" align="right">
        <input class="submit-button" type="submit" value="Registration" />
        <input class="submit-button" type="reset" value="Reset" />
      </td>
    </tr>
    <tr>
      <td colspan="2" align="right">
        <a href="<c:url value="/login.jsp" />">
          <spring:message code="label.loginpage" />
        </a><br/>
      </td>
    </tr>
  </table>
</form>
</body>
</html>