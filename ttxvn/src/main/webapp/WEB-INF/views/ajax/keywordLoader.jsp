<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/includes/tags.jsp" %>

<ol>
	<c:if test="${not empty keywords}">
		<c:forEach items="${keywords}" var="keyword">
			<li><a href="#" onclick="doPagging(1,'${keyword.name}')">${keyword.name}</a></li>
		</c:forEach>
	</c:if>
</ol>