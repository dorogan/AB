
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="top.jsp"/>

<jsp:include page="menu.jsp" />

<div id="main">
    <c:if test="${id == cid}" >
        <a href="/userinfo">edit inf</a>
    </c:if>
    <div><img src="${avatarPath}" alt="User photo"></div>
    <div><h2>${name} ${sname}</h2></div>
    <div>
        <p><spring:message code="label.mail" />: ${mail}</p>
        <p><spring:message code="usrinf.birthday" />: ${birthday}</p>
        <p><spring:message code="usrinf.interests" />: ${interests}</p>
        <p><spring:message code="usrinf.profession" />: ${profession}</p>
        <p><spring:message code="usrinf.phone" />: ${phone}</p>
        <p><spring:message code="usrinf.address" />: ${address}</p>
        <p><spring:message code="usrinf.skype" />: ${skype}</p>
    </div>
</div>
</body>
</html>
