
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

<c:if test="${!empty proposeList}">
  <table class="data">
    <c:forEach items="${proposeList}" var="user">
      <tr>
        <td><h4>${user.firstname}</h4></td>
        <td><h4>${user.lastname}</h4></td>
        <td><h4><a href="/confirm/${user.id}" ><spring:message code="btn.itisfriend" /> - ${user.id} </a> </h4></td>
        <td><h4><a href="/down/${user.id}" ><spring:message code="btn.turnDown" />  - ${user.id}</a> </h4></td>
      </tr>
    </c:forEach>
  </table>
</c:if>

</body>
</html>
