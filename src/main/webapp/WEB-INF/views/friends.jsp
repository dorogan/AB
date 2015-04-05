
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="top.jsp" />

<jsp:include page="menu.jsp" />

<div id="main">
<c:if test="${!empty proposeList}">
  <table class="peoples-list">
    <c:forEach items="${proposeList}" var="user">
      <tr>
        <td rowspan="2" width="130px">
            <img class="user-avatar" src="${user.avatarPath}">
        </td>
        <td class="user-name">
            <a href="/user/${user.id}">
                <h3>${user.firstname} ${user.lastname}</h3>
            </a>
        </td>
      </tr>
    <tr>
        <td class="add-button">
            <a href="/confirm/${user.id}" >
                <div class="add-to-friend-button">
                    <spring:message code="btn.itisfriend" />
                </div>
            </a>
            <a href="/down/${user.id}" >
                <div class="add-to-friend-button">
                    <spring:message code="btn.turnDown" />
                </div>
            </a>
        </td>
      </tr>
    </c:forEach>
  </table>
</c:if>
</div>
<jsp:include page="right.jsp"/>

</body>
</html>
