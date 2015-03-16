
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title></title>
</head>
<body>
<c:if test="${!empty userList}">
  <table class="data">
    <c:forEach items="${userList}" var="user">
      <tr>
        <td><a href="/user/${user.id}"> <h4>${user.firstname} ${user.lastname}</h4> </a></td>
        <td><a href="/add/${user.id}" ><spring:message code="msg.addtofriend" /> - ${user.id}</a> </td>
      </tr>
    </c:forEach>
  </table>
</c:if>
</body>
</html>
