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
        <td>
            <a href="delete/${action.id}">
                <img src="<c:url value="/resources/images/action-status-0.png"/>">&nbsp;&nbsp;&nbsp;
            </a>
        </td>
        <td>
            <h4 class="name" >
                ${action.name}
            </h4>
        </td>
        <td>
            <a  href="delete/${action.id}">&nbsp;&nbsp;&nbsp;
                <img src="<c:url value="/resources/images/action-info.png"/>"></a>
        </td>
        <td>
            <a  href="delete/${action.id}"><img src="<c:url value="/resources/images/del.png"/>"></a>&nbsp;&nbsp;&nbsp;
        </td>
      </tr>
    </c:forEach>
      <tr>
          <td colspan="2">
              <a href="#" id="new-action" data-type="text" data-pk="1" data-placement="right"
                 data-title="Enter action" data-value="" data-url="add" >
                <spring:message code="label.addaction" />
              </a>
          </td>
      </tr>
  </table>
</c:if>
</body>
</html>