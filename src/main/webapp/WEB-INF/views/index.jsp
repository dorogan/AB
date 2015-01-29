<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
  <title></title>
  <c:url value="/resources/css/style.css" var="cssURL" />
  <link rel="stylesheet" type="text/css" media="screen" href="${cssURL}" />
</head>
<body>
<div class="left-bar">
  <ul>
    <li>
      <a href="<c:url value="/index" />">
        <h1><spring:message code="main.title" /></h1>
      </a>
    </li>
    <li>
      <a href="<c:url value="/action" />">
        <img src="/resources/images/add.png" alt="<spring:message code="label.addaction" />"/>
      </a>
    </li>
  </ul>
  <div class="exit">
    <a href="<c:url value="/logout" />">
      <img src="/resources/images/logout.png" alt="<spring:message code="label.addaction" />"/>
    </a>
  </div>
</div>
<h3><spring:message code="label.actions" /></h3>
<c:if test="${!empty actionList}">
  <table class="data">
    <tr>
      <td><spring:message code="label.actionname" /></td>
      <td><spring:message code="label.actiondescription" /></td>
      <td><spring:message code="label.actionpermission" /></td>
    </tr>
    <c:forEach items="${actionList}" var="action">
      <tr>
        <td>${action.name}</td>
        <td>${action.description}</td>
        <td>${action.permission}</td>
        <td><a href="delete/${action.id}"><spring:message code="label.delete" /></a></td>
      </tr>
    </c:forEach>
  </table>
</c:if>
</body>
</html>