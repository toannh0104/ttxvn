<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/tags.jsp"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><spring:message code="login.header.title" /></title>

<!-- Bootstrap -->
<link
	href="<c:url value="/resources/bootstrap-3.2.0/css/bootstrap.min.css"/>"
	rel="stylesheet">
<link
	href="<c:url value="/resources/styles/bootstrap-datepicker3.css"/>"
	rel="stylesheet" />
<link href="<c:url value="/resources/styles/bootstrap-custom.css"/>"
	rel="stylesheet" />
<link href="<c:url value="/resources/styles/style.css"/>"
	rel="stylesheet" />

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
					<div class="col-xs-6 col-sm-6">
						<table>
							<tr>
								<td><a href="<%=request.getContextPath()%>"
									title="<spring:message code="homepage.title"/>"> <img
										src="<c:url value="/resources/images/logo.jpg"/>"
										class="img-responsive"
										alt="<spring:message code="homepage.logo.alt"/>" />
								</a></td>
							</tr>
						</table>
					</div>

					<div class="col-xs-6 col-sm-6">
						<div class="text-right">
							<div class="panel-a">
								<div class="panel-i-b panel-r">
									<ul id="select-languae">
										<li><a href="?lang=vi"> <img
												src="<c:url value="/resources/images/flags/vi-VN.png"/>"
												alt="Vietnam" />
										</a></li>
										<li><a href="?lang=en"> <img
												src="<c:url value="/resources/images/flags/en-US.png"/>"
												alt="English" />
										</a></li>
									</ul>
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
					<div class="row">
						<div
							class="col-xs-offset-3 col-xs-6 col-sm-offset-3 col-sm-6 col-md-offset-4 col-md-4">
							<div id="login-box" class="ma-t">
								<div class="box-h">
									<div class="h-t text-center">
										<h3 class="text-uppercase">
											<spring:message code="login.title" />
										</h3>
									</div>
									<div class="h-i">
										<img src="<c:url value="/resources/images/lock.png"/>"
											alt="Lock" width="32" height="32" />
									</div>
								</div>
								<div class="box-c">
									<form action="<c:url value="/login" />" method="post" role="form" id="login-form" class="form-horizontal">
										<div class="form-group">
											<label for="username-login" class="col-sm-5 control-label">
												<spring:message code="login.title.username" />
											</label>
											<div class="col-sm-7">
												<input id="username-login" name="username" type="text" required autofocus class="form-control ttx-form-control" />
											</div>
										</div>
										<div class="form-group">
											<label for="password-login" class="col-sm-5 control-label">
												<spring:message code="login.title.password" />
											</label>
											<div class="col-sm-7">
												<input id="password-login" name="password" type="password" required class="form-control ttx-form-control" />
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-offset-5 col-sm-7">
												<label>                
													<input id="remember-login" name="rememberme" type="checkbox" value="true"/> <spring:message code="login.title.remember.account" />
												</label>
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-offset-5 col-sm-7">
												<button type="submit" id="loginSumbit" class="ttx-btn btn btn-danger text-uppercase">
													<spring:message code="login.button.signin" />
												</button>
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-offset-5 col-sm-7">
												<a id="call-recovery-password" class="text-red" href="#">
													<ins>
														<spring:message code="login.title.fogot.password" />
													</ins>
												</a>
											</div>
										</div>
										<c:if test="${param.error != null}">
											<div class="ttx-message alert alert-success">
												<p class="text-danger">
													<b><spring:message code="login.message.validate" /></b>
												</p>
											</div>
										</c:if>
										<c:if test="${param.logout != null}">
											<div class="alert alert-success">
												<b><spring:message code="logout.message.validate" /></b>
											</div>
										</c:if>
									</form>

									<div id="recovery-password-box" style="display: none;">
										<div id="recovery-success-message" class="ttx-message">
											<p class="text-danger">
												<b><spring:message
														code="login.message.send.email.success" /></b>
											</p>
										</div>

										<div id="recovery-error-message" class="ttx-message">
											<p class="text-danger">
												<b><spring:message code="login.message.email.validate" /></b>
											</p>
										</div>

										<form id="recovery-password-form" action="" role="form"
											class="">
											<div class="form-group">
												<label for="username-email-recovery"><spring:message
														code="login.title.email" /></label> <input
													id="username-email-recovery" type="text"
													class="ttx-form-control form-control" required
													placeholder="<spring:message code="login.title.placeholder.email"/>" />
											</div>

											<div class="form-group">
												<div class="ttx-multi-btn text-center">
													<button id="cancel-recovery-password" type="button"
														class="ttx-btn btn btn-danger text-uppercase"
														data-dismiss="modal">
														<spring:message code="login.button.cancel" />
													</button>
													<button id="send-recovery-password" type="submit"
														class="ttx-btn btn btn-info text-uppercase">
														<spring:message code="login.button.recovery.password" />
													</button>
												</div>
											</div>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div id="footer-panel">
			<div class="container-fluid">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<p id="copyright" class="text-center">
							<spring:message code="homepage.footer.copyright" />
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>



	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="<c:url value="/resources/scripts/jquery-1.11.1.min.js"/>"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="<c:url value="/resources/bootstrap-3.2.0/js/bootstrap.min.js"/>"></script>
	<script src="<c:url value="/resources/bootstrap-3.2.0/js/bootstrap-datepicker.js"/>"></script>
	<script src="<c:url value="/resources/bootstrap-3.2.0/js/bootstrap-datepicker.vi.js"/>" charset="UTF-8"></script>
	<script src="<c:url value="/resources/scripts/ttxvn.js"/>"></script>
	<script src="<c:url value="/resources/scripts/jQuery.print.js"/>"></script>
	<script src="<c:url value="/resources/bootstrap-3.2.0/js/jquery.cookie.min.js"/>"></script>
	<script src="<c:url value="/resources/bootstrap-3.2.0/js/jquery.cookie.min.js"/>"></script>

	<script type="text/javascript">
		jQuery('#call-recovery-password').click(function () {
		    jQuery('#login-validate-summary').hide();
		    jQuery('#recovery-success-message, #recovery-error-message').hide();
		    jQuery('#recovery-password-form').show();
		    jQuery('#recovery-password-form')[0].reset();
		    jQuery('#recovery-password-box').toggle();

		    if (jQuery('#recovery-password-box').is(':visible')) {
		        jQuery('#username-email-recovery').focus();
		    } else {
		        jQuery('#username-login').focus();
		    }
		});

		jQuery('#cancel-recovery-password').click(function () {
		    jQuery('#call-recovery-password').trigger('click');
		});

		jQuery('#send-recovery-password').click(function () {
		    console.log('');
		    var email = jQuery('#username-email-recovery').val();
		 
			jQuery.ajax({
				cache: false,
				type : 'get',
				url : '<%=request.getContextPath()%>/anonymous/recovery-password',
				data : {
					email : email
				},
				dataType : 'json',
				success : function(response) {
					if (response.message == "fail") {
						jQuery('#recovery-error-message').show();
						jQuery('#username-email-recovery').focus();
					} else {
						jQuery('#recovery-error-message, #recovery-password-form').hide(); 
						jQuery('#recovery-success-message').show();
					}

				}
			});

		    return false;
		});
		
	</script>
</body>
</html>