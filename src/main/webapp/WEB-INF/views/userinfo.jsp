
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title><spring:message code="usrinf.title" /></title>
</head>
<body>
<form class="form-container" method="POST" action="/registration/add">
    <h4 align="center" class="form-title">
        <spring:message code="usrinf.title" />
    </h4>
    <table>
        <tr>
            <td align="right"><spring:message code="usrinf.birthday" /></td>
            <td><input class="form-field" type="text" name="dateOfBirthday" /></td>
        </tr>
        <tr>
            <td align="right"><spring:message code="usrinf.avatar" /></td>
            <td><input class="form-field" type="text" name="avatarPath" /></td>
        </tr>
        <tr>
            <td align="right"><spring:message code="usrinf.interests" /></td>
            <td><input class="form-field" type="text" name="interests" /></td>
        </tr>
        <tr>
            <td align="right"><spring:message code="usrinf.profession" /></td>
            <td><input class="form-field" type="text" name="profession" /></td>
        </tr>
        <tr>
            <td align="right"><spring:message code="usrinf.phone" /></td>
            <td><input class="form-field" type="text" name="phone" /></td>
        </tr>
        <tr>
            <td align="right"><spring:message code="usrinf.address" /></td>
            <td><input class="form-field" type="text" name="address" /></td>
        </tr>
        <tr>
            <td align="right"><spring:message code="usrinf.skype" /></td>
            <td><input class="form-field" type="text" name="skype" /></td>
        </tr>
        <tr>
            <td class="submit-container" colspan="2" align="right">
                <input class="submit-button" type="submit" value="Save" />
                <input class="submit-button" type="reset" value="Reset" />
            </td>
        </tr>
    </table>
</form>
</body>
</html>
