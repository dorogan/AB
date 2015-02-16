<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
  <title></title>
  <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet">
  <script src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
  <script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>

  <!-- x-editable (bootstrap version) -->
  <link href="//cdnjs.cloudflare.com/ajax/libs/x-editable/1.4.6/bootstrap-editable/css/bootstrap-editable.css" rel="stylesheet"/>
  <script src="//cdnjs.cloudflare.com/ajax/libs/x-editable/1.4.6/bootstrap-editable/js/bootstrap-editable.min.js"></script>
  <script src="/resources/bootstrap-editable/js/main.js"></script>
  <c:url value="/resources/css/style.css" var="cssURL" />
  <link rel="stylesheet" type="text/css" media="screen" href="${cssURL}" />
</head>
<body>
<div class="left-bar">
  <ul>
    <li>
      <a href="<c:url value="/index" />">
        <img src="<c:url value="/resources/images/ab.png"/>" />
      </a>
    </li>
    <li>
      <a href="<c:url value="/action" />">
        <img src="<c:url value="/resources/images/add.png"/>" alt="<spring:message code="label.addaction" />"/>
      </a>
    </li>
    <li>
      <a href="#">
        <img src="<c:url value="/resources/images/actions.png"/>" />
      </a>
    </li>
    <li>
      <a href="/peoples">
        <spring:message code="msg.peoples" />
      </a>
    </li>
    <li>
      <a href="/friends">
        <spring:message code="msg.friends" />
      </a>
    </li>

  </ul>
  <div class="exit">
    <a href="<c:url value="/logout" />">
      <img src="<c:url value="/resources/images/logout.png"/> " alt="<spring:message code="label.addaction" />"/>
    </a>
  </div>
</div>
<h3><spring:message code="label.actions" /></h3>
<c:if test="${!empty actionList}">
  <table class="data">
    <c:forEach items="${actionList}" var="action">
      <tr>
        <td><h4 class="username" data-type="text" data-placement="right" data-title="Enter username">${action.name}</h4></td>
        <td><a  href="delete/${action.id}"><img src="<c:url value="/resources/images/del.png"/>"></a></td>
      </tr>
    </c:forEach>
  </table>
</c:if>
</body>
</html>