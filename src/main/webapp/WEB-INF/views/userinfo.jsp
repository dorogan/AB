
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="top.jsp"/>

<jsp:include page="menu.jsp" />

<div id="main">
<form class="form-container" method="POST" action="/userinfo/save" >
    <h4 align="center" class="form-title">
        <spring:message code="usrinf.title" />
    </h4>
    <table>
        <tr>
            <td align="right"><spring:message code="label.name" /></td>
            <td><input class="form-field" type="text" name="firstname" value="${user.firstname}"/></td>
        </tr>
        <tr>
            <td align="right"><spring:message code="label.secondname" /></td>
            <td><input class="form-field" type="text" name="lastname" value="${user.lastname}"/></td>
        </tr>
        <tr>
            <td align="right"><spring:message code="usrinf.birthday" /></td>
            <td><input class="form-field" type="date" name="dateOfBirthday" value="${birthday}"/></td>
        </tr>
        <tr>
            <td align="right"><spring:message code="usrinf.interests" /></td>
            <td><input class="form-field" type="text" name="interests" value="${interests}"/></td>
        </tr>
        <tr>
            <td align="right"><spring:message code="usrinf.profession" /></td>
            <td><input class="form-field" type="text" name="profession" value="${profession}"/></td>
        </tr>
        <tr>
            <td align="right"><spring:message code="usrinf.phone" /></td>
            <td><input class="form-field" type="text" name="phone" value="${phone}"/></td>
        </tr>
        <tr>
            <td align="right"><spring:message code="usrinf.address" /></td>
            <td><input class="form-field" type="text" name="address" value="${address}"/></td>
        </tr>
        <tr>
            <td align="right"><spring:message code="usrinf.skype" /></td>
            <td><input class="form-field" type="text" name="skype" value="${skype}"/></td>
        </tr>
        <tr>
            <td class="submit-container" colspan="2" align="right">
                <input class="submit-button" type="submit" value="Save" />
            </td>
        </tr>
    </table>
</form>
<form class="form-container" method="POST" action="/userinfo/avatar" enctype="multipart/form-data">
    <table>
        <tr>
            <td colspan="2"><img src="${pageContext.request.contextPath}${avatarPath}"></td>
        </tr>
        <tr>
            <td align="right"><spring:message code="usrinf.avatar" /></td>
            <td><input type="file" name="file" /></td>
        </tr>
        <tr>
            <td class="submit-container" colspan="2" align="right">
                <input class="submit-button" type="submit" value="Set Avatar" />
            </td>
        </tr>
    </table>
</form>
</div>

<jsp:include page="right.jsp"/>

</body>
</html>
