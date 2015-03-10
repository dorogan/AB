
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title></title>
</head>

<body>
    <jsp:include page="menu.jsp" />
    <c:if test="${id == cid}" >
        <a href="/userinfo">edit inf</a>
    </c:if>
    <div><img src="${avatarPath}" alt="User photo"></div>
    <div><h2>${name} ${sname}</h2></div>
    <div>
        <p>${mail}</p>
        <p>${birthday}</p>
    </div>
</body>
</html>
