<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/includes/tags.jsp" %>

<table class="users_table" border="">
    <tr>
        <th><spring:message code="ttxvn.keyworksearching.common.label.no.number"/></th>
        <th><spring:message code="ttxvn.keyworksearching.common.label.url"/></th>
        <th><spring:message code="ttxvn.keyworksearching.common.label.source.type"/></th>
        <th><spring:message code="ttxvn.keyworksearching.common.label.reliability"/></th>
        <th><spring:message code="ttxvn.keyworksearching.common.label.frequency"/></th>
        <th><spring:message code="ttxvn.keyworksearching.common.label.action"/></th>
    </tr>
    
    <c:if test="${not empty page.getContent()}">
    	<c:forEach items="${page.getContent()}" var="sourceUrl" varStatus="counter">
    		<tr>
    			<td>${counter.count + subtractor}</td>
		        <td>${sourceUrl.url}</td>
		        <td><spring:message code="${sourceUrl.sourceType.messageCode}"/></td>
		        <td>${sourceUrl.reliability}</td>
		        <td><spring:message code="${sourceUrl.fetchFrequency.nameCode}"/></td>
		        <td>
		        	<a href="${pageContext.request.contextPath}/source-url/update/${sourceUrl.id}"><spring:message code="ttxvn.keyworksearching.common.label.update"/></a>&nbsp;&nbsp;
		        	<a href="${pageContext.request.contextPath}/source-url/delete/${sourceUrl.id}"><spring:message code="ttxvn.keyworksearching.common.label.delete"/></a>
		        </td>
		    </tr>
    	</c:forEach>
    </c:if>
</table>
<!-- Pagination Bar -->
<div class="page_nav">
	<c:if test="${page.firstPage}">
		<span>«</span>
	</c:if>
	<c:if test="${not page.firstPage}">
		<a href="" title='Go to first page'>«</a>
	</c:if>

	<c:forEach items="${page.pageItems}" var="item">
		<a href="#"><span>${item.pageNumber}</span></a>
	</c:forEach>

	<c:if test="${page.lastPage}">
		<span>»</span>
	</c:if>
	<c:if test="${not page.lastPage}">
		<a title='Go to last page'>»</a>	
	</c:if>
</div>

<script type="text/JavaScript">
    $(function(){
        $('.page_nav a').click(function(e) {
        	var url = $('input[name=urlSearching]').val();
        	$('#ajax_comments').empty().html('<img src="<c:url value="/images/ajax-loader.gif"/>" />');
        	
        	if ($(this).index() == 0) {
        		$('#ajax_comments').load('<%=request.getContextPath()%>/source-url/ajax/findURLByPage?page=' + '1' + '&categoryId=1&urlSearching=' + url);
        	} else if ($(this).is(':last-child')) {
        		$('#ajax_comments').load('<%=request.getContextPath()%>/source-url/ajax/findURLByPage?page=' + '${page.totalPages}' + '&categoryId=1&urlSearching=' + url);
        	} else {
                $('#ajax_comments').load('<%=request.getContextPath()%>/source-url/ajax/findURLByPage?page=' + $(this).text() + '&categoryId=1&urlSearching=' + url);	
        	}
        	e.preventDefault();
        });
    });
</script>