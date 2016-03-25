<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/tags.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><spring:message code="homepage.title"/></title>

    <!-- Bootstrap -->
    <link href="<c:url value="/resources/bootstrap-3.2.0/css/bootstrap.min.css"/>" rel="stylesheet" />
    <link href="<c:url value="/resources/styles/bootstrap-datepicker3.css"/>" rel="stylesheet" />
    <link href="<c:url value="/resources/styles/bootstrap-custom.css"/>" rel="stylesheet" />
    <link href="<c:url value="/resources/styles/style.css"/>" rel="stylesheet" />
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
    <div id="wrapper">
        <div id="header-panel">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-xs-5 col-sm-5">
                        <table>
                            <tr>
                                <td>
                                    <a href="${pageContext.request.contextPath}/home">
                                        <img src="<c:url value="/resources/images/logo.jpg"/>" class="img-responsive" alt="<spring:message code="homepage.logo.alt"/>" />
                                    </a>
                                </td>
                                <td>
                                    <a id="main-collapse-btn" href="#" title="Click to Show/Hide left pane" class="btn" style="padding: 0;">
                                        <img src="<c:url value="/resources/images/btn-ddn.png"/>" class="img-responsive" alt="Show/Hide menu" />
                                    </a>
                                </td>
                            </tr>
                        </table>
                    </div>

                    <div class="col-xs-7 col-sm-7">
                        <div class="text-right">
                            <div class="panel-a">
                            	
                            	<sec:authorize access="authenticated" var="authenticated"/>
                            	<c:choose>
                            		<c:when test="${authenticated}">
                            			<div class="panel-i-b panel-l">
		                                    <div class="panel-i-b welcome">
		                                        <p>
		                                            <i><spring:message code="homepage.header.welcome"/></i>
		                                            <input id="id-restuser-add" type="hidden" value="<sec:authentication property="principal.id" />" />
		                                            <a href="#" id="rest-name-login" ><sec:authentication property="principal.username" /></a>
		                                        </p>
		                                    </div>
		                                    <div class="panel-i-b mail-notification dropdown">
		                                        <a class="notification-alert dropdown-toggle" data-toggle="dropdown" href="#" onclick="removeNotification()">
		                                            <img src="<c:url value="/resources/images/mail.png"/>" alt="" />
		                                            <span>
		                                                <span class="notification-count-value"></span>
		                                            </span>
		                                        </a>
		
		                                        <ul id="show-notification" class="dropdown-menu" role="menu">
		                                        </ul>
		                                    </div>
		                                    <div class="panel-i-b logout">
		                                        <a href="<c:url value="/logout"/>" id="logout" class="btn btn-default"><b><spring:message code="homepage.header.logout"/></b></a>
		                                    </div>
		                                </div>
		
		                                <div class="panel-i-b panel-r">
		                                    <ul id="select-languae">
		                                        <li>
		                                            <a href="?lang=vi">
		                                                <img src="<c:url value="/resources/images/flags/vi-VN.png"/>" alt="Vietnam" />
		                                            </a>
		                                        </li>
		                                        <li>
		                                            <a href="?lang=en">
		                                                <img src="<c:url value="/resources/images/flags/en-US.png"/>" alt="English" />
		                                            </a>
		                                        </li>
		                                    </ul>
		                                </div>
                            		</c:when>
                            		<c:otherwise>
                            			Did not authenticate!
                            		</c:otherwise>
                            	</c:choose>
                            	
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
   	</div>
   
    <!-- command show popup: $('#edit-restuser-popup').modal() -->
	<div id="edit-restuser-popup" class="modal fade" tabindex="-1"
		role="dialog" aria-labelledby="editUserModalLabel" aria-hidden="true" data-backdrop="static">
		<div class="modal-dialog">
			<div class="ttx-model-content modal-content">
				<form action="#" method="post" role="form" class="form-horizontal" novalidate="novalidate">
					<div class="ttx-modal-header modal-header">
						<button type="button" class="ttx-close close" data-dismiss="modal">
							<span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title text-center text-uppercase"
							id="editUserModalLabel"><spring:message code="usermanagement.keyword.label.userinformation"/></h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<div class="col-xs-12 col-sm-12">
								<p>
									<b><spring:message code="usermanagement.keyword.title.message"/></b> <br /> <i><spring:message code="usermanagement.keyword.title.sub.message"/></i>
								</p>
							</div>
						</div>
						<div class="form-group">
							<label for="rest-username-edit-user" class="control-label col-sm-3"><spring:message code="usermanagement.keyword.label.username"/></label>
							<div class="col-sm-9">
								<input id="rest-username-edit-user" type="text" required readonly
									class="ttx-form-control form-control" value="anhnd" />
							</div>
						</div>
						<div class="form-group">
							<label for="rest-password-edit-user" class="control-label col-sm-3"><spring:message code="usermanagement.keyword.label.password"/></label>
							<div class="col-sm-7">
								<input id="rest-password-edit-user" type="password" required readonly
									class="ttx-form-control form-control" value="123456" />
							</div>
							<a href="#" class="ttx-btn-primary btn btn-primary" onclick="clickChange()"><spring:message code="usermanagement.keyword.button.change.password"/></a>
							
						</div>

						<div class="form-group">
							<label for="rest-email-edit-user" class="control-label col-sm-3"><spring:message code="usermanagement.keyword.label.email"/></label>
							<div class="col-sm-9">
								<input id="rest-email-edit-user" type="email" required readonly
									class="ttx-form-control form-control"
									value="ducanhtk5@gmail.com" />
							</div>
						</div>

						<div class="form-group">
							<label for="rest-lastname-edit-user" class="control-label col-sm-3"><spring:message code="usermanagement.keyword.label.firstname"/></label>
							<div class="col-sm-9">
								<table>
									<colgroup>
										<col width="70" />
										<col width="90" />
										<col />
										<col width="50" />
										<col width="70" />
									</colgroup>
									<tr>
										<td><input id="rest-lastname-edit-user" type="text"
											class="ttx-form-control form-control" value="Nguyễn" /></td>
										<td class="text-right"><label for="rest-middle-edit-user"
											class="control-label"><spring:message code="usermanagement.keyword.label.middname"/></label>&nbsp;</td>
										<td><input id="rest-middle-edit-user" type="text"
											class="ttx-form-control form-control" value="Đức" /></td>
										<td class="text-right"><label for="rest-firstname-edit-user"
											class="control-label"><spring:message code="usermanagement.keyword.label.lastname"/></label>&nbsp;</td>
										<td><input id="rest-firstname-edit-user" type="text"
											class="ttx-form-control form-control" value="Anh" /></td>
									</tr>
								</table>


							</div>
						</div>
						<div class="form-group">
							<label for="rest-birth-date-edit-user" class="control-label col-sm-3"><spring:message code="usermanagement.keyword.label.userinformation"/></label>
							<div class="col-sm-9">
								<div class="form-inline">
									<div class="form-group">
										<label for="rest-birth-date-edit-user" class="control-label">
											<span class="ttx-icon-calendar ttx-icon"></span>
										</label>
									</div>
									<div class="form-group">
										<label class="sr-only" for="rest-birth-date-edit-user"><spring:message code="usermanagement.keyword.label.birthofdate"/></label> <input id="rest-birth-date-edit-user" type="text"
											class="ttx-date-picker ttx-form-control form-control"
											/>
									</div>
								</div>
							</div>
						</div>

						<div class="form-group">
							<label for="rest-male-edit-user" class="control-label col-sm-3"><spring:message code="usermanagement.keyword.label.gender"/></label>
							<div class="col-sm-9">
							<label class="radio-inline">
                                    <input type="radio" name="rest-gender_add_user" id="rest-gender-edit-user-male" value="male" /><spring:message code="usermanagement.keyword.label.male" />
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="rest-gender_add_user" id="rest-gender-edit-user-female" value="female" /> <spring:message code="usermanagement.keyword.label.female" />
                                </label>
							</div>
						</div>

						<div class="form-group">
							<label for="rest-phone-edit-user" class="control-label col-sm-3"><spring:message code="usermanagement.keyword.label.phonenumber"/></label>
							<div class="col-sm-9">
								<input id="rest-phone-edit-user" type="tel"
									class="ttx-form-control form-control" value="0985065975" />
							</div>
						</div>
						<div class="form-group">
							<label for="rest-address-edit-user" class="control-label col-sm-3"><spring:message code="usermanagement.keyword.label.address"/></label>
							<div class="col-sm-9">
								<input id="rest-address-edit-user" type="text"
									class="ttx-form-control form-control" value="15 Phạm Hùng" />
							</div>
						</div>
						<div class="form-group">
							<label for="rest-district-edit-user" class="control-label col-sm-3"><spring:message code="usermanagement.keyword.label.district"/></label>
							<div class="col-sm-9">
								<input id="rest-district-edit-user" type="text"
									class="ttx-form-control form-control" value="Từ Liêm" />
							</div>
						</div>
						<div class="form-group">
							<label for="rest-city-edit-user" class="control-label col-sm-3"><spring:message code="usermanagement.keyword.label.province"/></label>
							<div class="col-sm-9">
								<input id="rest-city-edit-user" type="text"
									class="ttx-form-control form-control" value="Hà Nội" />
							</div>
						</div>
					</div>
					<div class="ttx-modal-footer modal-footer">
						<div class="text-center">
							<button type="button"
								class="ttx-btn-default btn btn-default text-uppercase"
								data-dismiss="modal"><spring:message code="ttxvn.group.cancel.button"/></button>
							<button type="submit"
								class="ttx-btn-primary btn btn-primary text-uppercase"><spring:message code="ttxvn.group.save.button"/></button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<!-- command show popup: $('#change-password-restuser-popup').modal() -->
	<div id="change-password-restuser-popup" class="modal fade" tabindex="-1"
		role="dialog" aria-labelledby="changePasswordUserModalLabel"
		aria-hidden="true" data-backdrop="static">
		<div class="modal-dialog">
			<div class="ttx-model-content modal-content">
				<form action="#" method="post" role="form" class="form-horizontal" novalidate="novalidate">
					<div class="ttx-modal-header modal-header">
						<button type="button" class="ttx-close close" data-dismiss="modal" onclick="refreshForm()">
							<span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title text-center text-uppercase"
							id="changePasswordUserModalLabel"><spring:message code="usermanagement.keyword.changepassword.title"/></h4>
					</div>
					<div class="modal-body">
					<input id = "id-change-password-restuser" type = "hidden">
						<div class="form-group">
							<label for="username-change-password-restuser"
								class="control-label col-sm-3"><spring:message code="usermanagement.keyword.changepassword.username"/></label>
							<div class="col-sm-9">
								<input id="username-change-password-restuser" type="hidden" required
									class="ttx-form-control form-control" /><label id="rest-label-username"
									class="ttx-control-label-value control-label"></label>
							</div>
						</div>

						<div class="form-group">
							<label for="email-change-password-restuser"
								class="control-label col-sm-3"><spring:message code="usermanagement.keyword.changepassword.email"/></label>
							<div class="col-sm-9">
								<input id="email-change-password-restuser" type="hidden" required
									class="ttx-form-control form-control" /> <label id="rest-label-email"
									class="ttx-control-label-value control-label"></label>
							</div>
						</div>
						<div class="form-group">
							<label for="password-change-password-restuser"
								class="control-label col-sm-3"><spring:message code="usermanagement.keyword.changepassword.oldpassword"/></label>
							<div class="col-sm-9">
								<input id="old-password-change-password-restuser" type="password"
									required class="ttx-form-control form-control" />
							</div>
						</div>
						<div class="form-group">
							<label for="password-change-password-restuser"
								class="control-label col-sm-3"><spring:message code="usermanagement.keyword.changepassword.newpassword"/></label>
							<div class="col-sm-9">
								<input id="new-password-change-password-restuser" type="password"
									required class="ttx-form-control form-control" />
							</div>
						</div>
						<div class="form-group">
							<label for="confirm-password-change-password-restuser"
								class="control-label col-sm-3"><spring:message code="usermanagement.keyword.changepassword.confirmpassword"/></label>
							<div class="col-sm-9">
								<input id="confirm-password-change-password-restuser"
									type="password" required class="ttx-form-control form-control" />
							</div>
						</div>
					</div>
					<div class="ttx-modal-footer modal-footer">
						<div class="text-center">
							<button type="button"
								class="ttx-btn-default btn btn-default text-uppercase" onclick="refreshForm()"
								data-dismiss="modal"><spring:message code="usermanagement.keyword.changepassword.cancel.button"/></button>
							<button type="submit"
								class="ttx-btn-primary btn btn-primary text-uppercase"><spring:message code="usermanagement.keyword.changepassword.save.button"/></button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>