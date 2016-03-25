<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.vsii.ttxvn.keywordsearching.entity.User"%>
<%@ include file="/WEB-INF/views/includes/tags.jsp"%>

<jsp:include page="../includes/header.jsp"/>

		<div id="main-panel">
			<div id="main-box">
				<div class="container-fluid">
					<div id="main-menu-box" class="left-panel">
						<div id="nav-menu-topics-panel">
							<nav class="menu-directive">
								 <ul>
                                    <li>
                                        <a href="${pageContext.request.contextPath}/home" title="<spring:message code="homepage.mainMenu.homepage"/>" >
                                            <span class="ttx-icon ttx-icon-home"></span>
                                            <spring:message code="homepage.mainMenu.homepage"/>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="${pageContext.request.contextPath}/manage/user" class="active" title="<spring:message code="homepage.mainMenu.userMangament"/>">
                                            <span class="ttx-icon ttx-icon-user"></span>
                                            <spring:message code="homepage.mainMenu.userMangament"/>
                                        </a>

                                        <ul class="sub-menu open">
                                            <li>
                                                <a href="${pageContext.request.contextPath}/manage/user" class="active" title="<spring:message code="homepage.mainMenu.sub.userMangament"/>">
                                                    <span class="ttx-icon"></span>
                                                    <spring:message code="homepage.mainMenu.sub.userMangament"/>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="${pageContext.request.contextPath}/manage/group" title="<spring:message code="homepage.mainMenu.groupMangament"/>">
                                                    <span class="ttx-icon"></span>
                                                   <spring:message code="homepage.mainMenu.groupMangament"/>
                                                </a>
                                            </li>
                                        </ul>
                                    </li>
                                    
                                    <li>
                                        <a href="${pageContext.request.contextPath}/manage/sourceUrl" title="<spring:message code="homepage.mainMenu.systemConfiguration"/>">
                                            <span class="ttx-icon ttx-icon-settings"></span>
                                            <spring:message code="homepage.mainMenu.systemConfiguration"/>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="${pageContext.request.contextPath}/report/summary" title="<spring:message code="homepage.mainMenu.report"/>">
                                            <span class="ttx-icon ttx-icon-report"></span>
                                            <spring:message code="homepage.mainMenu.report"/>
                                        </a>
                                    </li>
                                </ul>
							</nav>
							 <nav class="menu-topics">
                                <ul>
                                </ul>
                            </nav>
						</div>

						<div class="nav-menu-right-panel"
							title="Click to collapse left pane"></div>
					</div>

					<div class="right-panel" style="margin-left: 225px;">
						<div class="row">
							<div class="col-md-12">
								<!-- Implement Your HTML code -->

								<div class="ttx-command">
									<p class="ttx-title-text text-uppercase">
										<b><spring:message code="usermanagement.keyword.title"/></b>
									</p>
								</div>

								<div id="user-filter" class="ttx-box">
									<form action="#" class="form-inline" role="search">
										<div class="form-group">
											<label for="username-user-filter" class="control-label"><spring:message code="usermanagement.keyword.username.filter"/></label> <input id="username-user-filter" type="text" name="username"
												autofocus class="ttx-form-control form-control" />
										</div>
										<div class="form-group">
											<label for="group-user-filter" class="control-label"><spring:message code="usermanagement.keyword.group.filter"/></label>
											<input id="group-user-filter" type="text"
												class="ttx-form-control form-control" />
										</div>
										<div class="form-group">
											<label for="email-user-filter" class="control-label"><spring:message code="usermanagement.keyword.email.filter"/></label>
											<input id="email-user-filter" type="text" name="email"
												class="ttx-form-control form-control" />
										</div>
										<div class="form-group">
											<button id="search-user-filter" type="button" onclick="searchUser()"
												class="ttx-btn-user-search btn-default btn">
												<span class="ttx-icon-user-search ttx-icon"></span><spring:message code="usermanagement.keyword.search.button"/>
											</button>
										</div>
									</form>
								</div>

								<div id="user-command"
									class="ttx-action ttx-action-icon ttx-box">
									<a href="#add" class="ttx-btn btn btn-danger"
										data-toggle="modal" data-target="#add-user-popup" onclick="refreshAddForm()"><span
										class="ttx-icon-user-add ttx-icon"></span><spring:message code="usermanagement.keyword.add.user"/></a> <a
										href="#delete-all" onclick="deleteListUser()" class="ttx-btn btn btn-info"><span
										class="ttx-icon-user-permission ttx-icon"></span><spring:message code="usermanagement.keyword.delete.user"/></a>
								</div>

								<div id="user-filter-results" class="ttx-box">
								<form  name="form_id" id="form_id"  method="post" action="">
									<table id="displayed"
										class="ttx-table-management table table-bordered table-condensed table-hover table-striped table-responsive">
										<thead>
											<tr>
												<th class="text-center"><input type="checkbox" id="selecctall" name="chkAll"/></th>
												<th><spring:message code="usermanagement.keyword.table.fullname"/></th>
												<th><spring:message code="usermanagement.keyword.table.username"/></th>
												<th><spring:message code="usermanagement.keyword.table.email"/></th>
												<th><spring:message code="usermanagement.keyword.table.group"/></th>
												<th colspan="4" class="text-center"><spring:message code="usermanagement.keyword.table.action"/></th>
											</tr>
										</thead>
										<tbody id="search-users">
										</tbody>
									</table>
									</form>
									<div id="links-paging" class="text-right">
										<ul class="pagination pagination-sm pagination-lg">
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="clearfix"></div>
				</div>
			</div>
		</div>

		<jsp:include page="../includes/footer.jsp"/>

	<!-- command show popup: $('#add-user-popup').modal() -->
	<div id="add-user-popup" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="addUserModalLabel" aria-hidden="true" data-backdrop="static">
		<div class="modal-dialog">
			<div class="ttx-model-content modal-content">
				<form action="#" method="post" role="form" class="form-horizontal" novalidate="novalidate">
					<div class="ttx-modal-header modal-header">
						<button type="button" class="ttx-close close" data-dismiss="modal">
							<span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title text-center text-uppercase"
							id="addUserModalLabel"><spring:message code="usermanagement.keyword.label.addnewuser"/></h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
						<input id="id-user-add" type="hidden" class="ttx-form-control form-control" />
							<label for="username-add-user" class="control-label col-sm-3"><spring:message code="usermanagement.keyword.label.username"/></label>
							<div class="col-sm-9">
								<input id="username-add-user" type="text" required
									class="ttx-form-control form-control" />
							</div>
						</div>
						<div class="form-group">
							<label for="password-add-user" class="control-label col-sm-3"><spring:message code="usermanagement.keyword.label.password"/></label>
							<div class="col-sm-9">
								<input id="password-add-user" type="password" required
									class="ttx-form-control form-control" />
							</div>
						</div>
						<div class="form-group">
							<label for="confirm-password-add-user"
								class="control-label col-sm-3"><spring:message code="usermanagement.keyword.label.confirmpassword"/></label>
							<div class="col-sm-9">
								<input id="confirm-password-add-user" type="password" required
									class="ttx-form-control form-control" />
							</div>
						</div>

						<div class="form-group">
							<label for="email-add-user" class="control-label col-sm-3"><spring:message code="usermanagement.keyword.label.email"/></label>
							<div class="col-sm-9">
								<input id="email-add-user" type="email" required
									class="ttx-form-control form-control" />
							</div>
						</div>

						<div class="form-group">
							<label for="lastname-add-user" class="control-label col-sm-3"><spring:message code="usermanagement.keyword.label.firstname"/></label>
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
										<td><input id="lastname-add-user" type="text"
											class="ttx-form-control form-control" /></td>
										<td class="text-right"><label for="middle-add-user"
											class="control-label"><spring:message code="usermanagement.keyword.label.middname"/></label>&nbsp;</td>
										<td><input id="middle-add-user" type="text"
											class="ttx-form-control form-control" /></td>
										<td class="text-right"><label for="firstname-add-user"
											class="control-label"><spring:message code="usermanagement.keyword.label.lastname"/></label>&nbsp;</td>
										<td><input id="firstname-add-user" type="text"
											class="ttx-form-control form-control" /></td>
									</tr>
								</table>


							</div>
						</div>
						<div class="form-group">
							<label for="birth-date-add-user" class="control-label col-sm-3"><spring:message code="usermanagement.keyword.label.birthofdate"/></label>
							<div class="col-sm-9">
								<div class="form-inline">
									<div class="form-group">
										<label for="birth-date-add-user" class="control-label">
											<span class="ttx-icon-calendar ttx-icon"></span>
										</label>
									</div>
									<div class="form-group">
										<label class="sr-only" for="birth-date-add-user"><spring:message code="usermanagement.keyword.label.birthofdate"/></label> <input id="birth-date-add-user" type="text"
											class="ttx-date-picker ttx-form-control form-control" />
									</div>
								</div>
							</div>
						</div>

						<div class="form-group">
							<label for="male-add-user" class="control-label col-sm-3"><spring:message code="usermanagement.keyword.label.gender"/></label>
							<div class="col-sm-9">
								<label class="radio-inline"> <input type="radio"
									name="sex_add_user" id="male-add-user" value="male" checked/>
									<spring:message code="usermanagement.keyword.label.male"/>
								</label> <label class="radio-inline"> <input type="radio"
									name="sex_add_user" id="female-add-user" value="female" />
									<spring:message code="usermanagement.keyword.label.female"/>
								</label>
							</div>
						</div>

						<div class="form-group">
							<label for="phone-add-user" class="control-label col-sm-3"><spring:message code="usermanagement.keyword.label.phonenumber"/></label>
							<div class="col-sm-9">
								<input id="phone-add-user" type="tel"
									class="ttx-form-control form-control" />
							</div>
						</div>
						<div class="form-group">
							<label for="address-add-user" class="control-label col-sm-3"><spring:message code="usermanagement.keyword.label.address"/></label>
							<div class="col-sm-9">
								<input id="address-add-user" type="text"
									class="ttx-form-control form-control" />
							</div>
						</div>
						<div class="form-group">
							<label for="district-add-user" class="control-label col-sm-3"><spring:message code="usermanagement.keyword.label.district"/></label>
							<div class="col-sm-9">
								<input id="district-add-user" type="text"
									class="ttx-form-control form-control" />
							</div>
						</div>
						<div class="form-group">
							<label for="city-add-user" class="control-label col-sm-3"><spring:message code="usermanagement.keyword.label.province"/></label>
							<div class="col-sm-9">
								<input id="city-add-user" type="text"
									class="ttx-form-control form-control" />
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

	 <!-- command show popup: $('#edit-user-popup').modal() -->
	<div id="edit-user-popup" class="modal fade" tabindex="-1"
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
							<label for="username-edit-user" class="control-label col-sm-3"><spring:message code="usermanagement.keyword.label.username"/></label>
							<div class="col-sm-9">
								<input id="username-edit-user" type="text" required readonly
									class="ttx-form-control form-control" value="anhnd" />
							</div>
						</div>
						<div class="form-group">
							<label for="password-edit-user" class="control-label col-sm-3"><spring:message code="usermanagement.keyword.label.password"/></label>
							<div class="col-sm-7">
								<input id="password-edit-user" type="password" required readonly
									class="ttx-form-control form-control" value="123456" />
							</div>
							<a href="#" data-toggle="modal" data-target="#change-password-user-popup" class="ttx-btn-primary btn btn-primary"><spring:message code="usermanagement.keyword.button.change.password"/></a>
							
						</div>

						<div class="form-group">
							<label for="email-edit-user" class="control-label col-sm-3"><spring:message code="usermanagement.keyword.label.email"/></label>
							<div class="col-sm-9">
								<input id="email-edit-user" type="email" required readonly
									class="ttx-form-control form-control"
									value="ducanhtk5@gmail.com" />
							</div>
						</div>

						<div class="form-group">
							<label for="lastname-edit-user" class="control-label col-sm-3"><spring:message code="usermanagement.keyword.label.firstname"/></label>
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
										<td><input id="lastname-edit-user" type="text"
											class="ttx-form-control form-control" value="Nguyễn" /></td>
										<td class="text-right"><label for="middle-edit-user"
											class="control-label"><spring:message code="usermanagement.keyword.label.middname"/></label>&nbsp;</td>
										<td><input id="middle-edit-user" type="text"
											class="ttx-form-control form-control" value="Đức" /></td>
										<td class="text-right"><label for="firstname-edit-user"
											class="control-label"><spring:message code="usermanagement.keyword.label.lastname"/></label>&nbsp;</td>
										<td><input id="firstname-edit-user" type="text"
											class="ttx-form-control form-control" value="Anh" /></td>
									</tr>
								</table>


							</div>
						</div>
						<div class="form-group">
							<label for="birth-date-edit-user" class="control-label col-sm-3"><spring:message code="usermanagement.keyword.label.userinformation"/></label>
							<div class="col-sm-9">
								<div class="form-inline">
									<div class="form-group">
										<label for="birth-date-edit-user" class="control-label">
											<span class="ttx-icon-calendar ttx-icon"></span>
										</label>
									</div>
									<div class="form-group">
										<label class="sr-only" for="birth-date-edit-user"><spring:message code="usermanagement.keyword.label.birthofdate"/></label> <input id="birth-date-edit-user" type="text"
											class="ttx-date-picker ttx-form-control form-control"
											value="19/07/1989" />
									</div>
								</div>
							</div>
						</div>

						<div class="form-group">
							<label for="male-edit-user" class="control-label col-sm-3"><spring:message code="usermanagement.keyword.label.gender"/></label>
							<div class="col-sm-9">
							<label class="radio-inline">
                                    <input type="radio" name="gender_add_user" id="gender-edit-user-male" value="male" /><spring:message code="usermanagement.keyword.label.male" />
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="gender_add_user" id="gender-edit-user-female" value="female" /> <spring:message code="usermanagement.keyword.label.female" />
                                </label>
							</div>
						</div>

						<div class="form-group">
							<label for="phone-edit-user" class="control-label col-sm-3"><spring:message code="usermanagement.keyword.label.phonenumber"/></label>
							<div class="col-sm-9">
								<input id="phone-edit-user" type="tel"
									class="ttx-form-control form-control" value="0985065975" />
							</div>
						</div>
						<div class="form-group">
							<label for="address-edit-user" class="control-label col-sm-3"><spring:message code="usermanagement.keyword.label.address"/></label>
							<div class="col-sm-9">
								<input id="address-edit-user" type="text"
									class="ttx-form-control form-control" value="15 Phạm Hùng" />
							</div>
						</div>
						<div class="form-group">
							<label for="district-edit-user" class="control-label col-sm-3"><spring:message code="usermanagement.keyword.label.district"/></label>
							<div class="col-sm-9">
								<input id="district-edit-user" type="text"
									class="ttx-form-control form-control" value="Từ Liêm" />
							</div>
						</div>
						<div class="form-group">
							<label for="city-edit-user" class="control-label col-sm-3"><spring:message code="usermanagement.keyword.label.province"/></label>
							<div class="col-sm-9">
								<input id="city-edit-user" type="text"
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
	
	<!-- command show popup: $('#change-password-user-popup').modal() -->
	<div id="change-password-user-popup" class="modal fade" tabindex="-1"
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
					<input id = "id-change-password-user" type = "hidden">
						<div class="form-group">
							<label for="username-change-password-user"
								class="control-label col-sm-3"><spring:message code="usermanagement.keyword.changepassword.username"/></label>
							<div class="col-sm-9">
								<input id="username-change-password-user" type="hidden" required
									class="ttx-form-control form-control" /><label id="label-username"
									class="ttx-control-label-value control-label"></label>
							</div>
						</div>

						<div class="form-group">
							<label for="email-change-password-user"
								class="control-label col-sm-3"><spring:message code="usermanagement.keyword.changepassword.email"/></label>
							<div class="col-sm-9">
								<input id="email-change-password-user" type="hidden" required
									class="ttx-form-control form-control" /> <label id="label-email"
									class="ttx-control-label-value control-label"></label>
							</div>
						</div>
						<div class="form-group">
							<label for="password-change-password-user"
								class="control-label col-sm-3"><spring:message code="usermanagement.keyword.changepassword.oldpassword"/></label>
							<div class="col-sm-9">
								<input id="old-password-change-password-user" type="password"
									required class="ttx-form-control form-control" />
							</div>
						</div>
						<div class="form-group">
							<label for="password-change-password-user"
								class="control-label col-sm-3"><spring:message code="usermanagement.keyword.changepassword.newpassword"/></label>
							<div class="col-sm-9">
								<input id="new-password-change-password-user" type="password"
									required class="ttx-form-control form-control" />
							</div>
						</div>
						<div class="form-group">
							<label for="confirm-password-change-password-user"
								class="control-label col-sm-3"><spring:message code="usermanagement.keyword.changepassword.confirmpassword"/></label>
							<div class="col-sm-9">
								<input id="confirm-password-change-password-user"
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

		<!-- command show popup: $('#edit-roles-user-popup').modal() -->
	<div id="edit-roles-user-popup" class="modal fade" tabindex="-1"
		role="dialog" aria-labelledby="editRolesUserModalLabel"
		aria-hidden="true">
		<div class="ttx-large-model-dialog modal-dialog">
			<div class="ttx-model-content modal-content">
				<form action="#" method="post" role="form" class="form-horizontal" id="saveUserGroup">
					<div class="ttx-modal-header modal-header">
						<button type="button" class="ttx-close close" data-dismiss="modal">
							<span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title text-center text-uppercase"
							id="editRolesUserModalLabel"><spring:message code="user.add.usergroup.groupuser"/></h4>
					</div>
					<div class="modal-body">
						<table id="detailedDisplayed"
							class="ttx-table-management table table-bordered table-condensed table-hover table-striped table-responsive">
							<thead>
								<tr>
									<th class="text-center"><spring:message code="group.add.usergroup.displayname"/></th>
									<th class="text-center"><spring:message code="group.add.usergroup.loginname"/></th>
									<th class="text-center"><spring:message code="user.add.usergroup.gender"/></th>
									<th class="text-center">Email</th>
									<th class="text-center"><spring:message code="group.add.usergroup.phone"/></th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>

						<div id="user-roles-information">
							<h3><spring:message code="user.add.usergroup.groupinfo"/></h3>

							<div class="user-permession group-split">
								<div class="row">
									<div class="col-sm-5">
										<div class="ttx-panel panel panel-danger">
											<div class="panel-heading text-center text-uppercase"
												title="<spring:message code="user.add.usergroup.grouplist"/>"><spring:message code="user.add.usergroup.grouplist"/></div>
											<div class="panel-body">
												<select name="selectLeft" style="width: 100%;height: 115px;" id="selectLeft" multiple="multiple" >
												</select>
											</div>
										</div>
									</div>
									<div class="col-sm-2">
										<div class="command" style="margin-top: 35px;">
											<div class="user-permession-add command-add">
												<input name="btnRight" type="button" id="btnRight" class="ttx-btn btn btn-info" value="&gt;&gt;" onclick="move_list_items('selectLeft','selectRight');" >
											</div>
											<div class="user-permession-remove command-remove">
												<input name="btnLeft" type="button" id="btnLeft" class="ttx-btn btn btn-danger" value="&lt;&lt;" onclick="move_list_items('selectRight','selectLeft');" >
											</div>
										</div>
									</div>
									<div class="col-sm-5">
										<div class="ttx-panel panel panel-info">
											<div class="panel-heading text-center text-uppercase"
												title="<spring:message code="user.add.usergroup.selectedgroup"/>"><spring:message code="user.add.usergroup.selectedgroup"/></div>
											<div class="panel-body">
												<select name="selectRight" style="width: 100%;height: 115px;" id="selectRight" multiple="multiple" >
												</select>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="ttx-modal-footer modal-footer">
						<div class="text-center">
							<button type="button" class="ttx-btn btn btn-info text-uppercase"
								data-dismiss="modal"><spring:message code="user.add.usergroup.cancel"/></button>
							<button type="submit"
								class="ttx-btn btn btn-danger text-uppercase"><spring:message code="user.add.usergroup.save"/></button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	 <!-- command show popup: $('#message-popup').modal() -->
	<!--option 1: ttxModal.showError({message: 'Thông tin trong số điện thoại bạn đã nhập sai.<br />Bạn vui lòng nhập lại!', yesButton: true, yesText: 'Có', noButton: true, noText: 'Không'}) -->
	<!--option 2: ttxModal.showInfo({message: 'Thông tin trong số điện thoại bạn đã nhập sai.<br />Bạn vui lòng nhập lại!', yesButton: true, yesText: 'Có', noButton: true, noText: 'Không'}) -->
	<div id="message-popup" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="messageModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="ttx-model-content modal-content">
				<form action="#" method="post" role="form">
					<div class="ttx-modal-header modal-header">
						<button type="button" class="ttx-close close" data-dismiss="modal">
							<span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title text-center text-uppercase"
							id="messageModalLabel"><spring:message code="homepage.message.title"/></h4>
					</div>
					<div class="modal-body">
						<div id="ttx-message-body" class="ttx-message">
							<!--<p class="text-danger">
                                <b>Thông tin trong số điện thoại bạn đã nhập sai.</b>
                                <br />
                                <b>Bạn vui lòng nhập lại!</b>
                            </p>-->
						</div>
					</div>
					<div class="ttx-modal-footer modal-footer">
						<div class="text-center ttx-yes-no">
							<button id="no-message-btn" type="button"
								class="ttx-btn btn btn-info text-uppercase" data-dismiss="modal"><spring:message code="homepage.message.delete.cancel"/></button>
							<button id="yes-message-btn" type="submit"
								class="ttx-btn btn btn-danger text-uppercase"><spring:message code="homepage.message.delete.apply"/></button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	 <!-- command show popup: $('#delete-user-message-popup').modal() -->
    <div id="delete-user-message-popup" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="deleteTopicMessageModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="ttx-model-content modal-content">
                <form action="#" role="form">
                    <div class="ttx-modal-header modal-header">
                        <button type="button" class="ttx-close close" data-dismiss="modal"><span class="sr-only">Close</span></button>
                        <h4 class="modal-title text-center text-uppercase" id="deleteTopicMessageModalLabel"><spring:message code="usermanagement.user.delete.title"/></h4>
                    </div>
                    <div class="modal-body">
                        <p><b><spring:message code="usermanagement.user.delete.title"/></b></p>
                    </div>
                    <div class="ttx-modal-footer modal-footer">
                        <div class="text-center">
                            <button type="button" class="ttx-btn-default btn btn-default text-uppercase" data-dismiss="modal"><spring:message code="homepage.category.delete.cancel"/></button>
                            <button type="submit" class="ttx-btn-primary btn btn-primary text-uppercase"><spring:message code="homepage.category.delete.apply"/></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    
    <!-- command show popup: $('#delete--all-user-message-popup').modal() -->
    <div id="delete-list-user-message-popup" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="deleteTopicMessageModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="ttx-model-content modal-content">
                <form action="#" role="form">
                    <div class="ttx-modal-header modal-header">
                        <button type="button" class="ttx-close close" data-dismiss="modal"><span class="sr-only">Close</span></button>
                        <h4 class="modal-title text-center text-uppercase" id="deleteTopicMessageModalLabel"><spring:message code="usermanagement.user.delete.title"/></h4>
                    </div>
                    <div class="modal-body">
                        <p><b><spring:message code="usermanagement.user.delete.title"/></b></p>
                    </div>
                    <div class="ttx-modal-footer modal-footer">
                        <div class="text-center">
                            <button type="button" class="ttx-btn-default btn btn-default text-uppercase" data-dismiss="modal"><spring:message code="homepage.category.delete.cancel"/></button>
                            <button type="submit" class="ttx-btn-primary btn btn-primary text-uppercase"><spring:message code="homepage.category.delete.apply"/></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
         </div>
	
	 <!-- command show popup: $('#delete--all-user-message-popup').modal() -->
    <div id="delete-list-user-message-error-popup" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="deleteTopicMessageModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="ttx-model-content modal-content">
                <form action="#" role="form">
                    <div class="ttx-modal-header modal-header">
                        <button type="button" class="ttx-close close" data-dismiss="modal"><span class="sr-only">Close</span></button>
                        <h4 class="modal-title text-center text-uppercase" id="deleteTopicMessageModalLabel"><spring:message code="usermanagement.user.delete.title"/></h4>
                    </div>
                    <div class="modal-body">
                        <p><b><spring:message code="usermanagement.user.delete.title"/></b></p>
                    </div>
                   
                </form>
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
    <script src="<c:url value="/resources/scripts/notification.js"/>"></script>
    <script src="<c:url value="/resources/scripts/jQuery.print.js"/>"></script>
    
    <script type="text/javascript">
   //var user = 1;
   var userSelectIdGlobal=null;
   
	jQuery(document).ready(function (){
		
		loadUser(1, 0, '', '', '');
	});
	
    jQuery("#change-password-user-popup form").submit(function(){
    	
    	var oldPassword = jQuery('#old-password-change-password-user').val();
    	var newPassword = jQuery('#new-password-change-password-user').val();
    	var confirmNewPassword = jQuery('#confirm-password-change-password-user').val(); 	
    	var username = jQuery('#username-change-password-user').val();
    	var idchangepassword = jQuery('#id-change-password-user').val();
    	
		jQuery.ajax({
			cache: false,
			type:'get',
			url: '<%=request.getContextPath()%>/manage/user/changePassword',
			data: {
				idchangepassword: idchangepassword,
				username: username,
				oldPassword: oldPassword,
				newPassword: newPassword,
				confirmNewPassword: confirmNewPassword,
			
			},
			
			dataType:'json',
			success: function(response){
				if(response.message!='') {
					ttxModal.showError({message: response.message});
				} else {	
					jQuery('#change-password-user-popup').modal('hide');
					jQuery('#edit-user-popup').modal('hide');
					refreshForm();
					}
			}
		});
		return false;
	});
    
    function refreshForm()
	{
		jQuery("#old-password-change-password-user").val('');
		jQuery("#new-password-change-password-user").val('');
		jQuery("#confirm-password-change-password-user").val('');
	}

    $(document).ready(function() {
	    $('#selecctall').click(function(event) {  //on click 
	        if(this.checked) { // check select status
	            $('.checkbox1').each(function() { //loop through each checkbox
	                this.checked = true;  //select all checkboxes with class "checkbox1"               
	            });
	        }else{
	            $('.checkbox1').each(function() { //loop through each checkbox
	                this.checked = false; //deselect all checkboxes with class "checkbox1"                       
	            });         
	        }
	    });
	    
	});
    
    function loadUser(page, user, username, groupByName, email){
		
		jQuery.ajax({
			cache: false,
			type: 'get',
			url: '<%=request.getContextPath()%>/manage/user/findUserByPage',
			dataType: 'json',
			data: {
				page: page,
				user: user,
				username: username,
				groupByName: groupByName,
				email: email,
			},
			success: function(data){
				jQuery('.ttx-table-management tbody').html('');
				jQuery('#links-paging ul').html('');
				
				if(data.totalPages > 0){
					var content = '';
					var titleGroupName = '';
					var title = '';
					if(data.content.length>0)
					{
						for(var i = 0; i < data.content.length; i++){
							var titleGroupName = data.content[i].group;
							if (titleGroupName != null && titleGroupName.length > 15) {
	    						title = titleGroupName.substring(0, 10);
	    						title = title.concat("...");
							} else {
								title = titleGroupName;
							}
							
							content += "<tr><td class='text-center'><input type='checkbox' class='checkbox1' name='chk' value='"+data.content[i].username+"'/></td>"
								+"<td><a href='#' onclick=\"loadUserEdit("+data.content[i].id+")\" data-toggle='modal' data-target='#edit-user-popup' style='text-decoration: none;'>"+data.content[i].fullname+"</a></td>"
								+"<td>"+data.content[i].username+"</td>"
								+"<td>"+data.content[i].email+"</td>"
								+"<td><a href='#' data-toggle='tooltip' data-placement='bottom' title='"+data.content[i].group+"' style='color:#3F3F3F;text-decoration: none;'>"+title+"</a></td>"
								+"<td class='action-edit text-center'><a href='#' onclick=\"loadUserEdit("+data.content[i].id+")\" data-toggle='modal' data-target='#edit-user-popup'>"+"<span class='ttx-icon-user-edit ttx-icon'></span><spring:message code='usermanagement.keyword.table.action.edit'/></a></td>"
					            +"<td class='action-delete text-center'><a href='#' onclick=\"deleteUser('"+data.content[i].username+"')\" data-toggle='modal'>"+"<span class='ttx-icon-user-permission ttx-icon'></span><spring:message code='usermanagement.keyword.table.action.delete'/></a></td>"
					            +"<td class=\"action-group text-center\"><a href='#' onclick=\"detailUserMessage("+data.content[i].id+")\" data-toggle=\"modal\" data-target=\"#edit-roles-user-popup\"><span class=\"ttx-icon-user-group ttx-icon\"></span><spring:message code='usermanagement.keyword.table.action.editgroup'/></a></td>"
					            +"</tr>";
					           
						}
						jQuery('.ttx-table-management tbody').append(content);
					}
					else{
						if(page>1){
							loadUser((page-1), user, username, groupByName, email);	
						}
						
					}
					var pages = '';
					var user = jQuery('.menu-topics ul li a.active').parent().val();
					if (data.firstPage) {
						pages += '<li class="disabled"><span>«</span></li>';
					} else {
						pages += '<li><a href="#" onclick="loadUser(1,' + user + ')">«</a></li>';	
					}
					$.each(data.pageItems, function(index, item){
						pages += '<li' + (item.selected ? ' class="active"' : '') + '><a href="#" onclick="loadUser('+ item.pageNumber + ',' + user + ')">' + item.pageNumber + (item.selected ? '<span class="sr-only">(current)</span>' : '') + '</a></li>';
					});
					if (data.lastPage) {
						pages += '<li class="disabled"><span>»</span></li>';
					} else {
						pages += '<li><a href="#" onclick="loadUser(' + data.totalPages + ',' + user + ')">»</a></li>';	
					}
					jQuery('#links-paging ul').append(pages);
				}

			}
		});
	}
    
    $("#username-user-filter").keypress(function (event) {
		  if (event.which == 13) { 
			  searchUser();
		  return false;
		  		} 
	  });
    $("#group-user-filter").keypress(function (event) {
		  if (event.which == 13) { 
			  searchUser();
		  return false;
		  		} 
	  });
    $("#email-user-filter").keypress(function (event) {
		  if (event.which == 13) { 
			  searchUser();
		  return false;
		  		} 
	  });
    
    function searchUser() {
		var username = jQuery("#username-user-filter").val();
		var groupByName = jQuery("#group-user-filter").val();
		var email = jQuery("#email-user-filter").val();
		var user = jQuery('.menu-topics ul li a.active').parent().val();
		loadUser(1, user, username, groupByName, email);
		return false;
	}
    
    
    function detailUserMessage(userSelectedId){
    	userSelectIdGlobal = userSelectedId;
    	loadGroupIn(userSelectedId);
    	loadGroupOut(userSelectedId);
		jQuery.ajax({
			cache: false,
			type: 'get',
			url:'<%=request.getContextPath()%>/manage/user/loadUserSelected',
				data : {
					userSelectedId : userSelectedId
				},
				dataType : 'json',
				success : function(response) {
					$("#detailedDisplayed tbody").html('');
					for(var i = 0; i < response.length; i++){
			        	$("#detailedDisplayed").append("<tr><td>"+response[i].firstName+" "+response[i].middleName+" "+response[i].lastName
						+"</td>"
						+"<td>"+response[i].username+"</td>"
						+"<td>"+response[i].gender+"</td>"
						+"<td>"+response[i].email+"</td>"
						+"<td>"+response[i].phone+"</td>"
						+ "</tr>");
			        }
				},
			});
	}
    function loadGroupIn(userSelectedId){
		jQuery.ajax({
			cache: false,
			type: 'get',
			url:'<%=request.getContextPath()%>/manage/user/loadGroupIn',
				data : {
					userSelectedId : userSelectedId,
				},
				dataType : 'json',
				success : function(response) {
					$("#selectRight").html('');
					jQuery.each(response, function(key, value) {
						jQuery("#selectRight").append("<option value="+ "\"" + key  + "\">" + value + "</option>");
					});
				},
			});
	}
    function loadGroupOut(userSelectedId){
		jQuery.ajax({
			cache: false,
			type: 'get',
			url:'<%=request.getContextPath()%>/manage/user/loadGroupOut',
				data : {
					userSelectedId : userSelectedId,
				},
				dataType : 'json',
				success : function(response) {
					$("#selectLeft").html('');
					jQuery.each(response, function(key, value) {
						jQuery("#selectLeft").append("<option value="+ "\"" + key  + "\">" + value + "</option>");
					});
				},
			});
	}
////////save user not in group to group ///////////
	jQuery("#saveUserGroup").on('submit', function(){
		//var userId=1;
		var optionValues = [];
		$('#selectRight option').each(function() {
			optionValues.push($(this).val());
		});
		var leftValues = [];
		$('#selectLeft option').each(function(){
			leftValues.push($(this).val());
		});
		jQuery.ajax({
			cache: false,
			type:'post',
			url: '<%=request.getContextPath()%>/manage/user/addGroupToUser',
			traditional:true,
			data:{
				groupSelected: optionValues,
				userSelectedId:userSelectIdGlobal,
				//userId:userId,
				leftValues:leftValues
			},
			dataType:'json',
			success:function(response){
				jQuery('#edit-roles-user-popup').modal('hide');
				loadUser(1, 0, '', '', '');
			}
		});
		return false;
	});
    
/////////////// add/remove user to group /////////////////////
	function move_list_items(sourceid, destinationid)
	{
		$("#" + sourceid + " option:selected").appendTo("#" + destinationid);
	}
  //Daild 
  
  function 	refreshAddForm(){
	jQuery("#username-add-user").val('');
	jQuery("#password-add-user").val('');
	jQuery("#confirm-password-add-user").val('');
	jQuery("#email-add-user").val('');
	jQuery("#lastname-add-user").val('');
	jQuery("#middle-add-user").val('');
	jQuery("#firstname-add-user").val('');
	jQuery("#birth-date-add-user").val('');
	jQuery("#phone-add-user").val('');
	jQuery("#address-add-user").val('');
	jQuery("#district-add-user").val('');
	jQuery("#city-add-user").val('');
  }
    
    jQuery("#add-user-popup form").on('submit', function(){
		var name = jQuery("#username-add-user").val();
		var password = jQuery("#password-add-user").val();
		var confirmPassword = jQuery("#confirm-password-add-user").val();
		var email = jQuery("#email-add-user").val();
		var lastName = jQuery("#lastname-add-user").val();
		var middleName = jQuery("#middle-add-user").val();
		var firstName = jQuery("#firstname-add-user").val();
		var birthOfDate = jQuery("#birth-date-add-user").val();
		var sex  = $("input:radio[name=sex_add_user]:checked").val();
		var phoneNumber = jQuery("#phone-add-user").val();
		var address = jQuery("#address-add-user").val();
		var district = jQuery("#district-add-user").val();
		var city = jQuery("#city-add-user").val();
		var userId = jQuery("#id-user-add").val();
		
		jQuery.ajax({
			cache: false,
			type:'post',
			url: '<%=request.getContextPath()%>/manage/user/createUser',
				data : {
					//user : userIdCreate,
 					userID : userId,// bien dung de check edit user
					userName : name,
					userPassword : password,
					userConfirmPassword : confirmPassword,
					userEmail : email,
					userLastname : lastName,
					userMiddleName : middleName,
					userFirstName : firstName,
					userBirthOfDate : birthOfDate,
					userSex : sex,
					userPhoneNumber : phoneNumber,
					userAddress : address,
					userDistrict : district,
					userCity : city
				},
				dataType : 'json',
				success : function(response) {
					if (response.message != '')
						ttxModal.showError({
							message : response.message,
							onHidden: function(e){
								jQuery('body').addClass('modal-open');
							}
						});
					else {
						jQuery('#add-user-popup').modal('hide');
						loadUser();
					}
				}
			});
			return false;
		});
    	
    
    function loadUserEdit(id){
		jQuery.ajax({
			cache: false,
			type:'get',
			url: '<%=request.getContextPath()%>/manage/user/loadUserEdit',
			data: "modifiedUserId="+id,
			dataType:'json',
			success: function(response){
				jQuery("#id-user-add").val(response.modifiedUserId);
				jQuery("#username-edit-user").val(response.userName);
				jQuery("#password-edit-user").val(response.userPassword);
				jQuery("#email-edit-user").val(response.userEmail);
				jQuery("#lastname-edit-user").val(response.userLastname);
				jQuery("#middle-edit-user").val(response.userMiddleName);
				jQuery("#firstname-edit-user").val(response.userFirstName);
				jQuery("#birth-date-edit-user").val(response.userBirthOfDate);
				if(response.userSex == "FEMALE")
					{document.getElementById("gender-edit-user-female").checked = true;}
				else{document.getElementById("gender-edit-user-male").checked = true;}
				jQuery("#phone-edit-user").val(response.userPhoneNumber);
				jQuery("#address-edit-user").val(response.userAddress);
				jQuery("#district-edit-user").val(response.userDistrict);
				jQuery("#city-edit-user").val(response.userCity);
				jQuery('#edit-user-popup').modal();
				
				jQuery("#id-change-password-user").val(response.modifiedUserId);
				jQuery("#username-change-password-user").val(response.userName);
				jQuery("#label-username").text(response.userName);
				jQuery("#label-email").text(response.userEmail);

				
			},
    		error: function(response){
    			console.log('Error while request..'+response.status+' '+response.statusText);
			},
		});
	
		 jQuery("#edit-user-popup form").on('submit', function(){
				var lastName = jQuery("#lastname-edit-user").val();
				var middleName = jQuery("#middle-edit-user").val();
				var firstName = jQuery("#firstname-edit-user").val();
				var birthOfDate = jQuery("#birth-date-edit-user").val();
				var sex;
				if(document.getElementById("gender-edit-user-female").checked == true){
					sex = "FEMALE";
				}else {
					sex = "MALE";
				}
				var phoneNumber = jQuery("#phone-edit-user").val();
				var address = jQuery("#address-edit-user").val();
				var district = jQuery("#district-edit-user").val();
				var city = jQuery("#city-edit-user").val();
				var userId = jQuery("#id-user-add").val();
				
				jQuery.ajax({
					cache: false,
					type:'post',
					url: '<%=request.getContextPath()%>/manage/user/updateUser',
						data : {
							//user : userIdCreate,
		 					userID : userId,// bien dung de check edit user
							userLastname : lastName,
							userMiddleName : middleName,
							userFirstName : firstName,
							userBirthOfDate : birthOfDate,
							userSex : sex,
							userPhoneNumber : phoneNumber,
							userAddress : address,
							userDistrict : district,
							userCity : city
						},
						dataType : 'json',
						success : function(response) {
							if (response.message != '')
								ttxModal.showError({
									message : response.message,
									onHidden: function(e){
										jQuery('body').addClass('modal-open');
									}
									
								});
							else {
								var page = parseInt(jQuery('a', jQuery("div#links-paging ul.pagination").find('li.active')).text());
								jQuery('#edit-user-popup').modal('hide');
								loadUser(page, 0, '', '', '');
							}
						}
					});
					return false;
				});
	}
    
    function deleteUser(userName){
    	jQuery("#delete-user-message-popup .modal-body").html("");
    	jQuery("#delete-user-message-popup .modal-body").append("<p id='"+userName+"'><b><spring:message code='usermanagement.user.delete.title'/> "+userName+"?</b></p>");
    	jQuery('#delete-user-message-popup').modal();
    }


    jQuery("#delete-user-message-popup form").submit(function(){
    	var userName = jQuery("#delete-user-message-popup .modal-body p").attr('id');
    	//alert(userName);
    	jQuery.ajax({
    		cache: false,
    		type:'post',
    		url: '<%=request.getContextPath()%>/manage/user/deleteUser',
    		data: "userName="+userName,
    		dataType:'json',
    		//async: false,
    		success: function(response){
    			if(response.message!='')
    				ttxModal.showError({message: response.message});
    		},
    		error: function(response){
    			console.log('Error while request..'+response.status+' '+response.statusText);
    		},
    		complete:function(){
    			var page = parseInt(jQuery('a', jQuery("div#links-paging ul.pagination").find('li.active')).text());
    			jQuery('#delete-user-message-popup').modal('hide');
    			loadUser(page, 0, '', '', '');
    		}
    	});
    });
    
    function deleteListUser(){
    	var c_value;
    	c_value = "";
    	for (var i=0; i < document.form_id.chk.length; i++) 
    	   { 
    		
    	   if (document.form_id.chk[i].checked) 
    	      { 
    	      c_value += (c_value?',':'')+document.form_id.chk[i].value; 
    	      } 
    	   }
    	if(c_value == "")
    		{
    		jQuery("#delete-list-user-message-error-popup .modal-body").html("");
        	jQuery("#delete-list-user-message-error-popup .modal-body").append("<p><b><spring:message code='usermanagement.user.deleteList.validate'/></b></p>");
        	jQuery('#delete-list-user-message-error-popup').modal();
    		}
    	else
    		{
    	jQuery("#delete-list-user-message-popup .modal-body").html("");
    	jQuery("#delete-list-user-message-popup .modal-body").append("<p><b><spring:message code='usermanagement.user.delete.title'/> "+c_value+"?</b></p>");
    	jQuery('#delete-list-user-message-popup').modal();
    		}
    	jQuery("#delete-list-user-message-popup form").submit(function(){
    		c_value = "";
        	for (var i=0; i < document.form_id.chk.length; i++) 
        	   { 
        		
        	   if (document.form_id.chk[i].checked) 
        	      { 
        	      c_value += (c_value?',':'')+document.form_id.chk[i].value; 
        	      } 
        	   }
    		jQuery.ajax({
    			cache: false,
    			type:'post',
    			url: '<%=request.getContextPath()%>/manage/user/deleteListUser',
    			data: "deleteListUser="+c_value,
    			dataType:'json',
    			async: false,
    			success: function(response){
    				if(response.message!='')
    					ttxModal.showError({message: response.message});
    			},
    			error: function(response){
    				console.log('Error while request..'+response.status+' '+response.statusText);
    			},
    			complete:function(){
    				var page = parseInt(jQuery('a', jQuery("div#links-paging ul.pagination").find('li.active')).text());
    				jQuery('#delete-list-user-message-popup').modal('hide');
    				loadUser(page, 0, '', '', '');
    			}
    		});
    	});
    }
    </script>
</body>
</html>

