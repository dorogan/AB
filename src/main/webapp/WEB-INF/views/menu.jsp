<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="left-bar">
    <ul>
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