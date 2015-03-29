<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="top.jsp"/>

<jsp:include page="menu.jsp"/>
<div id="main">
<div id="actions">
    <div class="act">
    <h3><spring:message code="label.actions" /></h3>
    <c:if test="${!empty actionList}">
        <table class="data">
            <c:forEach items="${actionList}" var="action">
                <tr>
                    <td class="done-td">
                        <a class="done-action" href="done/${action.id}" data-id="${action.id}"">
                            <img  src="<c:url value="/resources/images/action-status-0.png"/>" />
                        </a>
                    </td>
                    <td class="actions-td">
                        <h4 class="name" data-url="/updateName/${action.id}" data-pk="${action.id}">
                            ${action.name}
                        </h4>
                    </td>
                    <td class="date-td" >
                        <p>${action.date}</p>
                    </td>
                    <td class="details-td">
                        <a href="#" class="action-details" onclick="actionDetails(${action.id})">
                            <img src="<c:url value="/resources/images/action-info.png"/>"></a>
                    </td>
                    <td class="delete-td">
                        <a  href="delete/${action.id}"><img src="<c:url value="/resources/images/del.png"/>"></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    </div>
    <div class="new">
        <a href="/add" id="new-action"  data-type="text" data-pk="1" data-placement="right"
           data-title="Enter action" data-value="" data-url="add" >
            <spring:message code="label.addaction" />
        </a>
    </div>
</div>
<div  id="details-action">
    <form method="post" id="act-form-details" action="javascript:void(null);">
        <h3><spring:message code="actdtls.formname" /></h3>
        <input type="hidden" value="" id="id" name="id"><br>
        <h5><spring:message code="actdtls.description" /></h5>
        <textarea id="act-description" name="act-description"></textarea><br>
        <h5><spring:message code="actdtls.date" /></h5>
        <input type="date" value="" id="act-date" name="act-date"><br>
        <h5><spring:message code="actdtls.time"/></h5>
        <div class="onoffswitch">
            <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="myonoffswitch">
            <label class="onoffswitch-label" for="myonoffswitch">
                <span class="onoffswitch-inner"></span>
                <span class="onoffswitch-switch"></span>
            </label>
        </div>
        <input type="time" name="act-time" id="act-time" value=""/>
        <!--<a href="#" id="act-date" data-format="dd.mm.yyyy" data-value="" ></a>-->
        <h5><spring:message code="actdtls.permission" /></h5>
        <select id="act-permission" name="act-permission">
            <option value="0"><spring:message code="actpermission0" /></option>
            <option value="1"><spring:message code="actpermission1" /></option>
            <option value="2"><spring:message code="actpermission2" /></option>
        </select><br>
        <h5><spring:message code="actdtls.priority" /></h5>
        <input type="radio" name="act-priority" value="0" id="act-priority-0"/>
        <label for="act-priority-0" class="act-priority-0" onclick=""><h6>0</h6></label>
        <input type="radio" name="act-priority" value="1" id="act-priority-1"/>
        <label for="act-priority-1" class="act-priority-1" onclick=""><h6>!</h6></label>
        <input type="radio" name="act-priority" value="2" id="act-priority-2"/>
        <label for="act-priority-2" class="act-priority-2" onclick=""><h6>!!</h6></label>
        <input type="radio" name="act-priority" value="3" id="act-priority-3"/>
        <label for="act-priority-3" class="act-priority-3" onclick=""><h6>!!!</h6></label>
        <br>
        <input id="submit-update" type="submit" value="<spring:message code="actdtls.doneButton" />">
    </form>
</div>
</div>
<script>
    function actionDetails(id){
        $("#details-action").show();
        document.getElementById("id").value = id;
        $.ajax({
            type: 'GET',
            url: '/details/'.concat(id),
            dataType: 'json',
            contentType: 'application/json',
            mimeType: 'application/json',
            success: function(data){
                document.getElementById("act-description").value = data.description;
                document.getElementById("act-date").value = data.date;
                if(data.time == null){
                    document.getElementById("myonoffswitch").checked = false;
                    $("#act-time").hide();
                } else{
                    document.getElementById("myonoffswitch").checked = true;
                    document.getElementById("act-time").value = data.time;
                    $("#act-time").show();
                }
                document.getElementById("act-permission").value = data.permission;
                document.getElementsByName("act-priority").item(data.priority).checked = true;
                //alert(data)
            }
        });

    }
</script>
</body>
</html>