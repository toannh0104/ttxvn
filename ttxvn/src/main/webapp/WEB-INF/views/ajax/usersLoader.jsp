<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/includes/tags.jsp" %>

<table class="users_table" border="">
    <tr>
        <th><spring:message code="ttxvn.keyworksearching.common.label.no.number"/></th>
        <th><spring:message code="ttxvn.keyworksearching.common.label.username"/></th>
        <th><spring:message code="ttxvn.keyworksearching.common.label.action"/></th>
    </tr>
    <c:if test="${not empty users}">
    	<c:forEach items="${users}" var="user" varStatus="counter">
    		<tr>
		        <td>${counter.count + subtractor}</td>
		        <td>${user.username}</td>
		        <td>
		        	<a href="${pageContext.request.contextPath}/user/update/${user.id}"><spring:message code="ttxvn.keyworksearching.common.label.update"/></a>&nbsp;&nbsp;
		        	<a href="${pageContext.request.contextPath}/user/delete/${user.id}"><spring:message code="ttxvn.keyworksearching.common.label.delete"/></a>
		        </td>
		    </tr>
	    </c:forEach>
    </c:if>
</table>
<c:if test="${not empty users}">
	<div class="page_nav">
	    ${pageNav}
	</div>
</c:if>

<script type="text/JavaScript">
    $(function(){
        $('.page_nav a').click(function(e) {
            $('#ajax_comments').empty().html('<img src="<c:url value="/images/ajax-loader.gif"/>" />');
            $('#ajax_comments').load('<%=request.getContextPath()%>/user/ajax/findByPage?page=' + $(this).text());
            e.preventDefault();
        });
    });
</script>