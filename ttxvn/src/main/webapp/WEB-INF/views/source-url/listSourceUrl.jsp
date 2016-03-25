<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/includes/tags.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="ttxvn.keyworksearching.label.create.source.url"/></title>
	<link rel="stylesheet" type="text/css" href="<c:url value="/styles/styles.css"/>"/>
	<script src="<c:url value="/scripts/jquery-1.11.1.min.js"/>"></script>
    <script type="text/JavaScript">
    	$(document).ready(function () {
            // Put an animated GIF image insight of content
            // $('#ajax_comments').empty().html('<img src="<c:url value="/images/ajax-loader.gif"/>" />');
            // $('#ajax_comments').load('<%=request.getContextPath()%>/source-url/ajax/findURLByPage');
        });
    </script>
</head>
<body>
	<h2><spring:message code="ttxvn.keyworksearching.label.create.source.url"/></h2>
	<div id="searchWrapper" style="margin:5px 0;">
		<input type="text" name="urlSearching">
		<input type="button" value="Search" onclick="search()">
	</div>
	<div id="ajax_comments" style="margin:5px 0;"></div>
</body>

<script type="text/JavaScript">
function search() {
	   var url = $('input[name=urlSearching]').val();
       $('#ajax_comments').empty().html('<img src="<c:url value="/images/ajax-loader.gif"/>" />');
       $('#ajax_comments').load('<%=request.getContextPath()%>/source-url/ajax/findURLByPage?page=' + $(this).text() + '&categoryId=1&urlSearching=' + url);
       e.preventDefault();
};
</script>    
</html>