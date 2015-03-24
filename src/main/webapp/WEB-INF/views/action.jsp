
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

    <div class="act">
        <h3><spring:message code="label.actions" /></h3>
        <c:if test="${!empty actionList}">
            <table class="data">
                <c:forEach items="${actionList}" var="action">
                    <tr>
                        <td>
                            <a class="done-action" href="#" data-id="${action.id}" data-link="/done/${action.id}">
                                <img  src="<c:url value="/resources/images/action-status-0.png"/>" />
                            </a>
                        </td>
                        <td>
                            <h4 class="name" data-url="/updateName/${action.id}" data-pk="${action.id}">
                                    ${action.name}
                            </h4>
                        </td>
                        <td>
                            <a  href="delete/${action.id}">
                                <img src="<c:url value="/resources/images/action-info.png"/>"></a>
                        </td>
                        <td>
                            <a  href="delete/${action.id}"><img src="<c:url value="/resources/images/del.png"/>"></a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <a href="#" id="new-action"  data-type="text" data-pk="1" data-placement="right"
           data-title="Enter action" data-value="" data-url="add" >
            <spring:message code="label.addaction" />
        </a>
    </div>