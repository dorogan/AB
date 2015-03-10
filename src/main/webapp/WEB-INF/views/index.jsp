<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="menu.jsp"/>

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