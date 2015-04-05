
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="UTF-8">
    <title><spring:message code="label.title" /> </title>
    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet">
    <script src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
    <script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>

    <!-- x-editable (bootstrap version) -->
    <link href="//cdnjs.cloudflare.com/ajax/libs/x-editable/1.4.6/bootstrap-editable/css/bootstrap-editable.css" rel="stylesheet"/>
    <script src="//cdnjs.cloudflare.com/ajax/libs/x-editable/1.4.6/bootstrap-editable/js/bootstrap-editable.min.js"></script>
    <script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
    <script src="/resources/bootstrap-editable/js/main.js"></script>
    <c:url value="/resources/css/style.css" var="cssURL" />
    <c:url value="/resources/css/980.css" var="cssMedia" />
    <link rel="stylesheet" type="text/css" media="screen" href="${cssURL}" />
    <link href="${cssMedia}" rel="stylesheet" type="text/css">
</head>
<body>
<div id="top-bar">
    <img class="left-menu" src="/resources/images/menu.png">
    <ul>
        <li>
            <a href="<c:url value="/index" />">
                <img src="<c:url value="/resources/images/ab.png"/>" />
            </a>
        </li>
        <li>
            <a href="<c:url value="/user/${user.id}" />">
                <img class="mini-photo" src="${user.avatarPath}" />
            </a>
        </li>
        <li>
            <a href="<c:url value="/user/${user.id}" />">
                <h5 class="user-name">${user.firstname}</h5>
            </a>
        </li>
    </ul>
    <div class="search-region">
        <form>
            <span>
                <input type="text" class="search rounded">
                <input class="rounded-button" type="button" value="Искать">
            </span>
        </form>
    </div>
    <ul class="top-menu-2">
        <li>
            <a href="/peoples">
                <h5 class="user-name"><spring:message code="msg.peoples" /></h5>
            </a>
        </li>
        <li>
            <a href="#">
                <img src="/resources/images/friends.png">
                ${countOfProp}
            </a>
        </li>
    </ul>
    <img class="right-menu" src="/resources/images/menu.png">
</div>
