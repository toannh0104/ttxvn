<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/tags.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title><spring:message code="ttxvn.keyworksearching.label.create.source.url"/></title>
	<!-- Bootstrap -->
	<link rel="stylesheet" type="text/css" href="<c:url value="/bootstrap-3.2.0/css/bootstrap.min.css"/>"/>
	<link rel="stylesheet" type="text/css" href="<c:url value="/bootstrap-3.2.0/css/signin.css"/>"/>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<c:url value="/scripts/jquery-1.11.1.min.js"/>"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<c:url value="/bootstrap-3.2.0/js/bootstrap.min.js"/>"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="<c:url value="/bootstrap-3.2.0/js/ie10-viewport-bug-workaround.js"/>"></script>
</head>
<body>
	<div class="container-fluid">
		<div class="page-header">
	    	<h3><spring:message code="ttxvn.keyworksearching.label.create.source.url"/></h1>
	    </div>
      
		<div class="row">
			 <div class="col-md-12">
			 	<form class="form-horizontal" role="form" name="createSourceUrlForm" action="${pageContext.request.contextPath}/source-url/create" method="POST">
				   <div class="form-group">
				      <label for="url" class="col-sm-2 control-label"><spring:message code="ttxvn.keyworksearching.common.label.url"/></label>
				      <div class="col-sm-6">
				      	<input type="text" class="form-control" id="url" name="url" placeholder="Enter URL" value="${sourceUrl.url}">
				      </div>
				   </div>
				   <div class="form-group">
				      <label for="sourceType" class="col-sm-2 control-label"><spring:message code="ttxvn.keyworksearching.common.label.source.type"/></label>
				      <div class="col-sm-6">
				      	<select name="sourceType" class="form-control">
							<c:if test="${not empty sourceUrlTypes}">
						    	<c:forEach items="${sourceUrlTypes}" var="sut">
						    		<option value="${sut}"><spring:message code="${sut.messageCode}"/></option>
							    </c:forEach>
						    </c:if>
						</select>
				      </div>
				   </div>
				   <div class="form-group">
				      <label for="reliability" class="col-sm-2 control-label"><spring:message code="ttxvn.keyworksearching.common.label.reliability"/></label>
				      <div class="col-sm-6">
				      	<input type="text" class="form-control" id="reliability" name="reliability" placeholder="Enter reliability" value="${sourceUrl.reliability}">
				      </div>
				   </div>
				   <div class="form-group">
				      <label for="fetchFrequencyId" class="col-sm-2 control-label"><spring:message code="ttxvn.keyworksearching.common.label.frequency"/></label>
				      <div class="col-sm-6">
				      	<select name="fetchFrequencyId" class="form-control">
							<c:if test="${not empty fetchFrequencies}">
		            			<c:forEach items="${fetchFrequencies}" var="fetchFrequency">
		            				<option value="${fetchFrequency.id}"><spring:message code="${fetchFrequency.nameCode}"/></option>
		            			</c:forEach>
		            		</c:if>
						</select>
				      </div>
				   </div>
				   <div class="form-group">
				      <div class="col-sm-offset-2 col-sm-10">
				      	<input type="hidden" name="categoryId" value="1">
		            	<input type="hidden" name="langCode" value="${langCode}">
		            	<input type="hidden" name="createdUserId" value="1">
		            	<input type="hidden" name="lastModifiedUserId" value="1">
				        <button type="submit" class="btn btn-default"><spring:message code="ttxvn.keyworksearching.common.label.save"/></button>
				      </div>
				   </div>
				</form>
			 </div>
		</div>
	</div>
</body>
</html>