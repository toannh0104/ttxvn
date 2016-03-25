<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/tags.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="ttxvn.keyworksearching.label.create.source.url"/></title>
</head>
<body>
	<h3><spring:message code="ttxvn.keyworksearching.label.create.source.url"/></h3>
	<br>
	<form name="createSourceUrlForm" action="${pageContext.request.contextPath}/source-url/update/${sourceUrl.id}" method="POST">
      <table>
        <tr>
            <td><spring:message code="ttxvn.keyworksearching.common.label.url"/>: </td>
            <td>
            	<input name="url" type="text" value="${sourceUrl.url}"/>
            </td>
        </tr>
        <tr>
            <td><spring:message code="ttxvn.keyworksearching.common.label.source.type"/>: </td>
            <td>
            	<select name="sourceType">
					<c:if test="${not empty sourceUrlTypes}">
				    	<c:forEach items="${sourceUrlTypes}" var="sut">
				    		<option value="${sut}" <c:if test="${sourceUrl.sourceType == sut}">selected="selected"</c:if>><spring:message code="${sut.messageCode}"/></option>
					    </c:forEach>
				    </c:if>
				</td>
            </td>
        </tr>
        <tr>
            <td><spring:message code="ttxvn.keyworksearching.common.label.reliability"/>: </td>
            <td>
            	<input name="reliability" type="text" value="${sourceUrl.reliability}"/>
            </td>
        </tr>
        <tr>
            <td><spring:message code="ttxvn.keyworksearching.common.label.frequency"/>: </td>
            <td>
            	<select name="fetchFrequencyId">
            		<c:if test="${not empty fetchFrequencies}">
            			<c:forEach items="${fetchFrequencies}" var="fetchFrequency">
            				<option value="${fetchFrequency.id}" <c:if test="${sourceUrl.fetchFrequency.id == fetchFrequency.id}">selected="selected"</c:if>><spring:message code="${fetchFrequency.nameCode}"/></option>
            			</c:forEach>
            		</c:if>
            	</select>
            </td>
        </tr>
        <tr>
            <td colspan="2">
            	<input type="hidden" name="categoryId" value="${sourceUrl.category.id}">
            	<input type="hidden" name="langCode" value="${sourceUrl.langCode}">
            	<input type="hidden" name="createdUserId" value="1">
            	<input type="hidden" name="lastModifiedUserId" value="1">
            	<input type="submit" value="<spring:message code="ttxvn.keyworksearching.common.label.save"/>" />
            </td>
        </tr>
      </table>
  </form>
</body>
</html>