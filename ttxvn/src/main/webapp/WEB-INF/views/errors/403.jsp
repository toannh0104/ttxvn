<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/tags.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><spring:message code="ttxvn.keywordsearching.security.access.denied"/></title>
    <!-- Bootstrap -->
    <link href="<c:url value="/resources/bootstrap-3.2.0/css/bootstrap.min.css"/>" rel="stylesheet" />
    <link href="<c:url value="/resources/styles/bootstrap-datepicker3.css"/>" rel="stylesheet" />
    <link href="<c:url value="/resources/styles/bootstrap-custom.css"/>" rel="stylesheet" />
    <link href="<c:url value="/resources/styles/style.css"/>" rel="stylesheet" />
</head>
<body>
    <div id="wrapper">
    	<div id="header-panel">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-xs-12 col-sm-5">
                        <table>
                            <tr>
                                <td>
                                    <a href="${pageContext.request.contextPath}/home">
                                        <img src="<c:url value="/resources/images/logo.jpg"/>" class="img-responsive" alt="<spring:message code="homepage.logo.alt"/>" />
                                    </a>
                                </td>
                                <td>
                                </td>
                            </tr>
                        </table>
                    </div>

                    <div class="col-xs-12 col-sm-7">
                        <div class="text-right">
                            <div class="panel-a">
                                <div class="panel-i-b panel-l">
                                    <div class="panel-i-b welcome">
                                    </div>
                                    <div class="panel-i-b mail-notification dropdown">
                                    </div>
                                    <div class="panel-i-b logout">
                                    </div>
                                </div>

                                <div class="panel-i-b panel-r">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
    	<div id="main-panel">
            <div id="main-box">
                <div class="container-fluid">
               		<p align="center" style="margin-top: 5%; color: red; font-weight: bold;"><spring:message code="ttxvn.keywordsearching.security.access.denied.msg"/></p>
                </div>
           	</div>
    	</div>
    </div>
</body>
</html>