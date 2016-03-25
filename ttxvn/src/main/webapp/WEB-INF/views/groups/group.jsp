<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
                                        <a href="${pageContext.request.contextPath}/home" title="<spring:message code="homepage.mainMenu.homepage"/>">
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
                                                <a href="${pageContext.request.contextPath}/manage/user" title="<spring:message code="homepage.mainMenu.sub.userMangament"/>">
                                                    <span class="ttx-icon"></span>
                                                    <spring:message code="homepage.mainMenu.sub.userMangament"/>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="${pageContext.request.contextPath}/manage/group" class="active" title="<spring:message code="homepage.mainMenu.groupMangament"/>">
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
                        </div>

                        <div class="nav-menu-right-panel" title="Click to collapse left pane"></div>
                    </div>

                    <div class="right-panel" style="margin-left: 225px;">
                        <div class="row">
                            <div class="col-md-12">
                                <!-- Implement Your HTML code -->

                                <div class="ttx-command">
                                    <p class="ttx-title-text text-uppercase"><b><spring:message code="ttxvn.group.list"/></b></p>
                                </div>

                                <div id="role-filter" class="ttx-box">
                                    <form action="#" method="get" class="form-inline" role="search">
                                        <div class="form-group">
                                            <label for="name-role-filter" class="control-label"><spring:message code="ttxvn.group.name"/></label>
                                            <input id="name-role-filter" type="text" autofocus class="ttx-form-control form-control"  />
                                        </div>
                                        <div class="form-group">
                                            <label for="status-role-filter" class="control-label"><spring:message code="ttxvn.group.status"/></label>
                                            <select id="status-role-filter" class="ttx-form-control form-control">
                                                <option value="0"><spring:message code="ttxvn.group.status.all"/></option>
                                                <option value="1"><spring:message code="ttxvn.group.status.active"/></option>
                                                <option value="2"><spring:message code="ttxvn.group.status.inactive"/></option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <button id="search-role-filter" type="button" class="ttx-btn-user-search btn-default btn" onclick="searchGroup()" ><span class="ttx-icon-user-search ttx-icon"></span><spring:message code="ttxvn.group.search"/></button>
                                        </div>
                                    </form>
                                </div>

                                <div id="role-command" class="ttx-action ttx-action-icon ttx-box">
                                    <a href="#add-role-popup" class="ttx-btn btn btn-danger" data-toggle="modal" data-target="#add-role-popup" onclick="addgroupbutton()"><span class="ttx-icon-user-add ttx-icon"></span><spring:message code="ttxvn.group.adding"/></a>
                                    <a href="#"  class="ttx-btn btn btn-info" onclick="deleteSelectedGroup()"><span class="ttx-icon-user-permission ttx-icon"></span><spring:message code="ttxvn.group.delete"/></a>
                                </div>

                                <div id="role-filter-results" class="ttx-box">
                                    <table id="displayed" class="ttx-table-management table table-bordered table-condensed table-hover table-striped table-responsive">
                                         
                                        <thead>
                                            <tr>
                                                <th class="text-center" >
                                                    <input type="checkbox" id="check-all-group"/>
                                                </th>
                                                <th><spring:message code="ttxvn.group.groupname"/></th>
                                                <th><spring:message code="ttxvn.group.status"/></th>
                                                <th><spring:message code="ttxvn.group.note"/></th>
                                                <th colspan="2" class="text-center"><spring:message code="ttxvn.group.task"/></th>
                                            </tr>
                                        </thead>
                                       	<tbody>
                                        </tbody>
                                    </table>
                                </div>
                                <div id="links-paging-group" class="text-right">
	                                    <ul class="pagination pagination-sm pagination-lg">
	                                    </ul>
	                            </div>
                            </div>
                        </div>
                    </div>

                    <div class="clearfix"></div>
                </div>
            </div>
        </div>

       <jsp:include page="../includes/footer.jsp"/>
<!--     </div> -->

    <!-- command show popup: $('#add-role-popup').modal() -->
    
  <div id="add-role-popup" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="addRoleModalLabel" aria-hidden="true" data-backdrop="static">
        <div class="ttx-large-model-dialog modal-dialog">
            <div class="ttx-model-content modal-content">
                <form method="post" role="form" class="form-horizontal" id="addRoleToNewGroup">
                    <div class="ttx-modal-header modal-header">
                        <button type="button" class="ttx-close close" data-dismiss="modal" onclick="cancelSelect()"><span class="sr-only">Close</span></button>
                        <h4 class="modal-title text-center text-uppercase" id="addRoleModalLabel"><spring:message code="ttxvn.group.adding.title"/></h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="name-add-role" class="control-label col-sm-3"><spring:message code="ttxvn.group.name"/></label>
                            <div class="col-sm-9">
                                <input id="name-add-role" type="text" required class="ttx-form-control form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="active-add-role" class="control-label col-sm-3"><spring:message code="ttxvn.group.status"/></label>
                            <div class="col-sm-9">
                                <label class="radio-inline">
                                    <input type="radio" name="active_add_role" id="active-add-role" value="active" checked="checked"> <spring:message code="ttxvn.group.status.active"/>
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="active_add_role" id="inactive-add-role" value="inactive"> <spring:message code="ttxvn.group.status.inactive"/>
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="description-add-role" class="control-label col-sm-3"><spring:message code="ttxvn.group.description"/></label>
                            <div class="col-sm-9">
                                <textarea id="description-add-role" class="ttx-form-control form-control"></textarea>
                            </div>
                        </div>

                        <div id="role-information">
                            <div class="role-permession group-split">
                                <div class="row">
                                    <div class="col-sm-5">
                                        <div class="ttx-panel panel panel-danger">
                                            <div class="panel-heading text-center text-uppercase" title="<spring:message code="group.add.usergroup.listauthorize"/>"><spring:message code="group.add.usergroup.listauthorize"/></div>
                                            <div class="panel-body">
                                                <select name="roleNotInGroup" style="width: 100%; height:170px" id="roleNotInGroup" multiple="multiple" >
												</select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-2">
                                        <div class="command">
                                            <div class="role-permession-add command-add">
<!--                                                 <button type="button" class="ttx-btn btn btn-info">»</button> -->
                                                <input name="addRoleToGroup" type="button" id="addRoleToGroup" class="ttx-btn btn btn-info" value="&gt;&gt;" onclick="move_list_items('roleNotInGroup','roleInGroup');" >
                                            </div>
                                            <div class="role-permession-remove command-remove">
<!--                                                 <button type="button" class="ttx-btn btn btn-danger">«</button> -->
                                                <input name="removeRoleFromGroup" type="button" id="removeRoleFromGroup" class="ttx-btn btn btn-danger" value="&lt;&lt;" onclick="move_list_items('roleInGroup','roleNotInGroup');" >
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-5">
                                        <div class="ttx-panel panel panel-info">
                                            <div class="panel-heading text-center text-uppercase" title="<spring:message code="group.add.usergroup.authorizegrant"/>"><spring:message code="group.add.usergroup.authorizegrant"/></div>
                                            <div class="panel-body">
                                                <select name="roleInGroup" style="width: 100%;height:170px" id="roleInGroup" multiple="multiple" >
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
                            <button type="button" class="ttx-btn btn btn-info text-uppercase" data-dismiss="modal" onclick="cancelSelect()"><spring:message code="ttxvn.group.cancel.button"/></button>
                            <button type="button" class="ttx-btn btn btn-danger text-uppercase" onclick="createGroup()"><spring:message code="ttxvn.group.save.button"/></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    
    
    
    

    <!-- command show popup: $('#edit-role-popup').modal() -->
    <div id="edit-role-popup" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="editRoleModalLabel" aria-hidden="true" data-backdrop="static">
        <div class="ttx-large-model-dialog modal-dialog">
            <div class="ttx-model-content modal-content">
                <form action="#" method="post" role="form" class="form-horizontal">
                    <div class="ttx-modal-header modal-header">
                        <button type="button" class="ttx-close close" data-dismiss="modal"><span class="sr-only">Close</span></button>
                        <h4 class="modal-title text-center text-uppercase" id="editRoleModalLabel"><spring:message code="group.add.usergroup.newusergroup"/></h4>
                    </div>
                    <div class="modal-body">
                        <div id="edit-role-command" class="ttx-action ttx-action-icon ttx-box">
                            <a href="#add" class="ttx-btn btn btn-danger" data-toggle="modal" data-target="#add-users-to-role-popup"><span class="ttx-icon-user-add ttx-icon"></span> <spring:message code="group.add.usergroup.newuser"/></a>
							<a id="target" class="ttx-btn btn btn-info"><span class="ttx-icon-user-permission ttx-icon"></span> <spring:message code="group.add.usergroup.eliminateuser"/></a>
                        </div>

                        <table id="userGroupDisplayed" class="ttx-table-management table table-bordered table-condensed table-hover table-striped table-responsive" >
                            <thead>
                                <tr>
                                    <th class="text-center">
                                        <input type="checkbox" id="selectAll"/>
                                    </th>
                                    <th class="text-center"><spring:message code="group.add.usergroup.displayname"/></th>
                                    <th class="text-center"><spring:message code="group.add.usergroup.loginname"/></th>
                                    <th class="text-center">Email</th>
                                    <th class="text-center"><spring:message code="group.add.usergroup.phone"/></th>
                                    <th colspan="3" class="text-center"><spring:message code="group.add.usergroup.task"/></th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                        <div id="loadIn-paging" class="text-right">
                                    <ul class="pagination pagination-sm pagination-lg">
                                    </ul>
                                </div>
                    </div>
                    <div class="ttx-modal-footer modal-footer"></div>
                </form>
            </div>
        </div>
    </div>

    <!-- command show popup: $('#add-users-to-role-popup').modal() -->
    <div id="add-users-to-role-popup" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="addUsersToRoleModalLabel" aria-hidden="true" data-backdrop="static">
        <div class="ttx-large-model-dialog modal-dialog">
            <div class="ttx-model-content modal-content">
                <form action="#" method="post" role="form" class="form-horizontal"> <!-- id="saveUserGroup" -->
                    <div class="ttx-modal-header modal-header">
                        <button type="button" class="ttx-close close" data-dismiss="modal" onclick="cancelSelect()"><span class="sr-only">Close</span></button>
                        <h4 class="modal-title text-center text-uppercase" id="addUsersToRoleModalLabel"><spring:message code="group.add.usergroup.groupuser"/></h4>
                    </div>
                    <div class="modal-body">
                        <div id="users-to-role-information">
                            <div class="users-to-role-permession group-split">
                                <div class="row">
                                    <div class="col-sm-5">
                                        <div class="ttx-panel panel panel-danger">
                                            <div class="panel-heading text-center text-uppercase" title="<spring:message code="group.add.usergroup.involveuser"/>"><spring:message code="group.add.usergroup.involveuser"/></div>
                                            <div class="panel-body">
                                                <div class="ttx-form-group">
                                                    <span class="ttx-search-group">
<!--                                                         <input type="search" class="form-control" placeholder="Tìm kiếm" /> -->

                                                        <input id="search_nameNotInGroup" type="text" autofocus placeholder="<spring:message code="group.add.usergroup.search.hint"/>" class="ttx-form-control form-control">
                                                        <button id="search_nameNotInGroupBtn" type="button" class="btn ttx-btn-search" onclick="searchNameNotInGroup()">
                                                            <span class="ttx-search-group"></span>
                                                        </button>
                                                        
                                                    </span>
                                                </div>
                                                <select name="selectLeft" style="width: 100%; height:170px" id="selectLeft" multiple="multiple" >
												</select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-2">
                                        <div class="command">
                                            <div class="users-to-role-add command-add">
                                                <input name="btnRight" type="button" id="btnRight" class="ttx-btn btn btn-info" value="&gt;&gt;" onclick="move_list_items('selectLeft','selectRight');" >
                                            </div>
                                            <div class="users-to-role-remove command-remove">
                                                <input name="btnLeft" type="button" id="btnLeft" class="ttx-btn btn btn-danger" value="&lt;&lt;" onclick="move_list_items('selectRight','selectLeft');" >
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-5">
                                        <div class="ttx-panel panel panel-info">
                                            <div class="panel-heading text-center text-uppercase" title="<spring:message code="group.add.usergroup.notinvolveuser"/>"><spring:message code="group.add.usergroup.notinvolveuser"/></div>
                                            <div class="panel-body">
                                                <div class="ttx-form-group">
                                                    <div class="ttx-search-group">
                                                        <input id="search_nameInGroup" type="text" autofocus placeholder="<spring:message code="group.add.usergroup.search.hint"/>" class="ttx-form-control form-control">
                                                        <button id="search_nameInGroupBtn" type="button" class="btn ttx-btn-search" onclick="searchNameInGroup()">
                                                            <span class="ttx-search-group"></span>
                                                        </button>
                                                    </div>
                                                </div>
                                                <select name="selectRight" style="width: 100%; height:170px" id="selectRight" multiple="multiple" >
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
                            <button type="button" class="ttx-btn btn btn-info text-uppercase" data-dismiss="modal" onclick="cancelSelect()"><spring:message code="ttxvn.group.cancel.button"/></button>
                            <button type="button" class="ttx-btn btn btn-danger text-uppercase" onclick="saveUserGroup()"><spring:message code="ttxvn.group.save.button"/></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
      <!-- command show popup: $('#modified-role-popup').modal() -->
    <div id="modified-role-popup" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="addRoleModalLabel" aria-hidden="true" data-backdrop="static">
        <div class="ttx-large-model-dialog modal-dialog">
            <div class="ttx-model-content modal-content">
                <form action="#" method="post"  role="form" class="form-horizontal">
                    <div class="ttx-modal-header modal-header">
                        <button type="button" class="ttx-close close" data-dismiss="modal"><span class="sr-only">Close</span></button>
                        <h4 class="modal-title text-center text-uppercase" id="addRoleModalLabel"><spring:message code="ttxvn.group.edit"/></h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="name-modified-role" class="control-label col-sm-3"><spring:message code="ttxvn.group.name"/></label>
                            <div class="col-sm-9" id="modified-role-popup-name">
<!--                                 <input id="name-modified-role" type="text" required class="ttx-form-control form-control" /> -->
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="active-modified-role" class="control-label col-sm-3"><spring:message code="ttxvn.group.status"/></label>
                            <div class="col-sm-9" id="modified-role-popup-status">

                            </div>
                        </div>
                        <div class="form-group">
                            <label for="description-modified-role" class="control-label col-sm-3"><spring:message code="ttxvn.group.description"/></label>
                            <div class="col-sm-9" id="modified-role-popup-description">
<!--                                 <textarea id="description-modified-role" class="ttx-form-control form-control"></textarea> -->
                            </div>
                        </div>

                        <div id="role-information">
                            <div class="role-permession group-split">
                                <div class="row">
                                    <div class="col-sm-5">
                                        <div class="ttx-panel panel panel-danger">
                                            <div class="panel-heading text-center text-uppercase" title="<spring:message code='group.add.usergroup.listauthorize'/>"><spring:message code="group.add.usergroup.listauthorize"/></div>
                                            <div class="panel-body">
                                                <select name="editRoleNotInGroup" style="width: 100%;height:170px" id="editRoleNotInGroup" multiple="multiple" >                                        
												</select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-2">
                                        <div class="command">
                                            <div class="role-permession-add command-add">
<!--                                                 <button type="button" class="ttx-btn btn btn-info">»</button> -->
                                                <input name="editRoleToGroup" type="button" id="editRoleToGroup" class="ttx-btn btn btn-info" value="&gt;&gt;" onclick="move_list_items('editRoleNotInGroup','editRoleInGroup');" >
                                            </div>
                                            <div class="role-permession-remove command-remove">
<!--                                                 <button type="button" class="ttx-btn btn btn-danger">«</button> -->
                                                <input name="editRoleToGroup" type="button" id="editRoleToGroup" class="ttx-btn btn btn-danger" value="&lt;&lt;" onclick="move_list_items('editRoleInGroup','editRoleNotInGroup');" >
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-5">
                                        <div class="ttx-panel panel panel-info">
                                            <div class="panel-heading text-center text-uppercase" title="<spring:message code='group.add.usergroup.authorizegrant'/>"><spring:message code="group.add.usergroup.authorizegrant"/></div>
<!--                                             <input name="addRoleToGroup" type="button" id="addRoleToGroup" class="ttx-btn btn btn-info" value="&gt;&gt;" onclick="move_list_items('roleNotInGroup','roleInGroup');" > -->
                                            <div class="panel-body">
                                                <select name="editRoleInGroup" style="width: 100%;height:170px" id="editRoleInGroup" multiple="multiple" >
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
                            <button type="button" class="ttx-btn btn btn-info text-uppercase" data-dismiss="modal"><spring:message code="ttxvn.group.cancel.button"/></button>
                            <button type="button" class="ttx-btn btn btn-danger text-uppercase" onclick="modifiedGroup()"><spring:message code="ttxvn.group.save.button"/></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- command show popup: $('#message-popup').modal() -->
    <!--option 1: ttxModal.showError({message: 'Thông tin trong số điện thoại bạn đã nhập sai.<br />Bạn vui lòng nhập lại!', yesButton: true, yesText: 'Có', noButton: true, noText: 'Không'}) -->
    <!--option 2: ttxModal.showInfo({message: 'Thông tin trong số điện thoại bạn đã nhập sai.<br />Bạn vui lòng nhập lại!', yesButton: true, yesText: 'Có', noButton: true, noText: 'Không'}) -->
    <div id="message-popup" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="messageModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="ttx-model-content modal-content">
                <form action="#" role="form" id="deleteMultipleUsers">
                    <div class="ttx-modal-header modal-header">
                        <button type="button" class="ttx-close close" data-dismiss="modal"><span class="sr-only">Close</span></button>
                        <h4 class="modal-title text-center text-uppercase" id="messageModalLabel"><spring:message code="ttxvn.group.display.message"/></h4>
                    </div>
                    <div class="modal-body">
                        <div id="ttx-message-body" class="ttx-message">

                        </div>
                    </div>
                    <div class="ttx-modal-footer modal-footer">
                        <div class="text-center ttx-yes-no">
                            <button type="button" class="ttx-btn btn btn-info text-uppercase" data-dismiss="modal"><spring:message code="ttxvn.group.no.button"/></button>
                            <button type="submit" class="ttx-btn btn btn-danger text-uppercase"><spring:message code="ttxvn.group.yes.button"/></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    
   
    
    <div id="delete-oneusergroup-popup" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="messageModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="ttx-model-content modal-content">
                <form action="#" method="post" role="form">
                    <div class="ttx-modal-header modal-header">
                        <button type="button" class="ttx-close close" data-dismiss="modal"><span class="sr-only">Close</span></button>
                        <h4 class="modal-title text-center text-uppercase" id="messageModalLabel"><spring:message code="ttxvn.group.display.message"/></h4>
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
                            <button id="no-message-btn" type="button" class="ttx-btn btn btn-info text-uppercase" data-dismiss="modal"><spring:message code="ttxvn.group.no.button"/></button>
                            <button id="yes-message-btn" type="button" class="ttx-btn btn btn-danger text-uppercase" onclick="deleteUserGroup();"><spring:message code="ttxvn.group.yes.button"/></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    
    <!-- command show popup: $('#delete-group-message-popup').modal() -->
 
    <div id="delete-group-message-popup" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="messageModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="ttx-model-content modal-content">
                <form action="#" method="post" role="form">
                    <div class="ttx-modal-header modal-header">
                        <button type="button" class="ttx-close close" data-dismiss="modal"><span class="sr-only">Close</span></button>
                        <h4 class="modal-title text-center text-uppercase" id="messageModalLabel"><spring:message code="ttxvn.group.display.message"/></h4>
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
                            <button id="no-message-btn" type="button" class="ttx-btn btn btn-info text-uppercase" data-dismiss="modal"><spring:message code="ttxvn.group.no.button"/></button>
                            <button id="yes-message-btn" type="button" class="ttx-btn btn btn-danger text-uppercase" onclick="yesDeleteGroup()"><spring:message code="ttxvn.group.yes.button"/></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

     <!-- command show popup: $('#delete-selected-group-message-popup').modal() -->
 
    <div id="delete-selected-group-message-popup" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="messageModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="ttx-model-content modal-content">
                <form action="#" method="post" role="form">
                    <div class="ttx-modal-header modal-header">
                        <button type="button" class="ttx-close close" data-dismiss="modal"><span class="sr-only">Close</span></button>
                        <h4 class="modal-title text-center text-uppercase" id="messageModalLabel"><spring:message code="ttxvn.group.display.message"/></h4>
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
                        <div class="text-center ttx-yes-no" id="yes-no-button-selected-group">
<%--                             <button id="no-message-btn" type="button" class="ttx-btn btn btn-info text-uppercase" data-dismiss="modal"><spring:message code="ttxvn.group.no.button"/></button> --%>
<%--                             <button id="yes-message-btn" type="button" class="ttx-btn btn btn-danger text-uppercase" onclick="yesDeleteSelectedGroup()"><spring:message code="ttxvn.group.yes.button"/></button> --%>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<c:url value="/resources/scripts/jquery-1.11.1.min.js"/>"></script>
<!--     Include all compiled plugins (below), or include individual files as needed -->
    <script src="<c:url value="/resources/bootstrap-3.2.0/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/resources/bootstrap-3.2.0/js/bootstrap-datepicker.js"/>"></script>
    <script src="<c:url value="/resources/bootstrap-3.2.0/js/bootstrap-datepicker.vi.js"/>" charset="UTF-8"></script>
    <script src="<c:url value="/resources/scripts/ttxvn.js"/>"></script>
    <script src="<c:url value="/resources/scripts/notification.js"/>"></script>

    
<script type="text/javascript">
var userId = $("#group-home-user-id").val();
var group = null;
				jQuery(document).ready(function (){
					loadGroup(1,'','');
					$("#target").click(function() {
					    getValueUsingClass();
					});

					$("#search_nameNotInGroup").keyup(function(event){
					    if(event.keyCode == 13){
					        $("#search_nameNotInGroupBtn").click();
					    }
					});
					$("#search_nameInGroup").keyup(function(event){
					    if(event.keyCode == 13){
					        $("#search_nameInGroupBtn").click();
					    }
					});
					
				});
				
			///////load all group ///////////////
				function loadGroup(page,name,status){
					jQuery.ajax({
						cache:false,
						type: 'get',
						url:'<%=request.getContextPath()%>/manage/group/search',
						data: {
		    				page: page,
		    				name: name,
		    				status: status,
		    			},
							dataType : 'json',
							complete: function(data){	
							},
							success: function(data){
 								$("#displayed tbody").html('');
 								$("#links-paging-group ul.pagination").html('');
 								
							     var pageContent = '';
							     var groupName= '';
							     var groupDescription='';
							     if(data.totalPages > 0){
							    	 if(data.content.length>0){
    							 for(var i = 0; i < data.content.length; i++){
    								 	var titleName=data.content[i].name;
										var titleDescription= data.content[i].description;
    									if (data.content[i].description != null && data.content[i].description.length > 85) {
    										groupDescription = data.content[i].description.substring(0, 85);
    										groupDescription = groupDescription.concat("...");
    									} else {
    										groupDescription = data.content[i].description ;
    									}
    									if (data.content[i].name != null && data.content[i].name.length > 25) {
    										groupName = data.content[i].name.substring(0, 20);
    										groupName = groupName.concat("...");
    									} else {
    										groupName = data.content[i].name ;
    									}
	
							        	if(data.content[i].status=="ACTIVE"){
							        		pageContent+="<tr><td class=\"text-center\"><input type=\"checkbox\" class=\"check-group-1\" name=\""+data.content[i].name+"\" value=\""+data.content[i].id+"\">"
							        		+"<td><a href=\"#\"  style=\"text-decoration:none;\" onclick=\"loadIn(1,"+data.content[i].id+")\" data-toggle=\"modal\" data-target=\"#edit-role-popup\" data-toggle=\"tooltip\" data-placement=\"top\" title=\""+titleName+"\">"+groupName
											+"</td></td>"
											+"<td><spring:message code='ttxvn.group.status.active'/></td>"
											+"<td><a style=\"text-decoration:none;color:Black;\" class=\"tooltip-description\" data-toggle=\"tooltip\" data-placement=\"top\" title=\""+titleDescription+"\">"+groupDescription+"<\a></td>"
											+"<td class=\"action-edit text-center\"><a href=\"#\" data-toggle=\"modal\" data-target=\"#modified-role-popup\" onclick=\"loadGroupModified("+data.content[i].id+")\">"+"<span class=\"ttx-icon-user-edit ttx-icon\"></span><spring:message code='ttxvn.group.edit.button'/></a></td>"
											+"<td class=\"action-delete text-center\"><a href=\"#\" onclick=\"deleteRole("+data.content[i].id+",'"+data.content[i].name+"');\"><span class=\"ttx-icon-user-permission ttx-icon\"></span><spring:message code='ttxvn.group.delete.button'/></a></td>"
											+ "</tr>";
							        	}
							        	else{
							        		pageContent+="<tr><td class=\"text-center\"><input type=\"checkbox\" class=\"check-group-1\" name=\""+data.content[i].name+"\" value=\""+data.content[i].id+"\">"
							        		+"<td><a href=\"#\" style=\"text-decoration:none;\" onclick=\"loadIn(1,"+data.content[i].id+")\" data-toggle=\"modal\" data-target=\"#edit-role-popup\" data-toggle=\"tooltip\" data-placement=\"top\" title=\""+titleName+"\">"+groupName
													+"</td></td>"
													+"<td><spring:message code='ttxvn.group.status.inactive'/></td>"
													+"<td><a style=\"text-decoration:none;color:Black;\" data-toggle=\"tooltip\" data-placement=\"top\" title=\""+titleDescription+"\">"+groupDescription+"<\a></td>"
													+"<td class=\"action-edit text-center\"><a href=\"#\" data-toggle=\"modal\" data-target=\"#modified-role-popup\" onclick=\"loadGroupModified("+data.content[i].id+")\">"+"<span class=\"ttx-icon-user-edit ttx-icon\"></span><spring:message code='ttxvn.group.edit.button'/></a></td>"
													+"<td class=\"action-delete text-center\"><a href=\"#\" onclick=\"deleteRole("+data.content[i].id+",'"+data.content[i].name+"');\"><span class=\"ttx-icon-user-permission ttx-icon\"></span><spring:message code='ttxvn.group.delete.button'/></a></td>"
													+ "</tr>";
							        	}
							      
							        }
									$("#displayed tbody").append(pageContent); 
							        var pagging = '';
									if (data.totalPages > 0) {
										if (data.firstPage) {
											pagging += '<li class="disabled"><span>«</span></li>';
										
										} else {
											
 											pagging += '<li><a href="#" onclick="loadGroup(1,\''+name+'\',\''+status+'\')">«</a></li>';	
										}
										$.each(data.pageItems, function(index, item){
											pagging += '<li' + (item.selected ? ' class="active"' : '') + '><a id="pagging-item-'+item.pageNumber+'" name="'+item.pageNumber+'" href="#" onclick="loadGroup('+ item.pageNumber + ',\''+name+'\',\''+status+'\')">' + item.pageNumber + (item.selected ? '<span class="sr-only">(current)</span>' : '') + '</a></li>';
										});
										if (data.lastPage) {
											pagging += '<li class="disabled"><span>»</span></li>';
										} else {
											pagging += '<li><a href="#" onclick="loadGroup(' + data.totalPages + ',\''+name+'\',\''+status+'\')">»</a></li>';	
										}
									}
									$("#links-paging-group ul.pagination").append(pagging);
							            //return false;
							}
							    	 else{
							    		 if(page>1){
							    			 loadGroup((page-1),name,status);
							    		 }
							    	 }
							     }
							     },
						});
					}
				
	/////////////// add/remove user to group /////////////////////
			function move_list_items(sourceid, destinationid)
			{
				$("#" + sourceid + " option:selected").appendTo("#" + destinationid);
			}
			
		///////// load all users in group //////////////
			function loadIn(page, groupId){
				group = groupId;
				loadOut(groupId);
				loadAllUserInGroup(groupId);
				jQuery.ajax({
					type: 'get',
					cache:false,
					url:'<%=request.getContextPath()%>/manage/group/loadUserIn',
						data : {
							groupId: groupId,
							page: page
						},
						dataType : 'json',
						success : function(data) {
							$("#userGroupDisplayed tbody").html('');
							$("#search_nameNotInGroup").html('');
							jQuery('#loadIn-paging ul').html('');
							
							if(data.totalPages > 0){
		    					var content = '';
		    					if(data.content.length>0){
		    					for(var i = 0; i < data.content.length; i++){
		    						content += "<tr><td class=\"text-center\"><input type=\"checkbox\" class=\"checkbox1\" name="+ "\"" + data.content[i].id  + "\" value="+ "\"" + data.content[i].username  + "\"/></td>"
									+"<td>"+data.content[i].firstName+" "+data.content[i].middleName+" "+data.content[i].lastName+"</td>"
									+"<td>"+data.content[i].username+"</td>"
									+"<td>"+data.content[i].email+"</td>"
									+"<td class=\"text-center\">"+data.content[i].phone+"</td>"
									+"<td class=\"action-delete text-center\">" 
										+"<a href=\"#\" data-toggle=\"modal\" data-target=\"#delete-oneusergroup-popup\" onclick=\"deleteOneUS("+groupId+",'"+data.content[i].id+"','"+data.content[i].username+"');\"  ><span class=\"ttx-icon-user-permission ttx-icon\"></span> Xóa</a>"
									+"</td>"
									+"</tr>"
						
		    					}
		    					jQuery('#userGroupDisplayed tbody').append(content);
		    					
		    					var pages = '';
		    					if (data.firstPage) {
		    						pages += '<li class="disabled"><span>«</span></li>';
								} else {
									pages += '<li><a href="#" onclick="loadIn(1,' + groupId + ')">«</a></li>';	
								}
								$.each(data.pageItems, function(index, item){
									pages += '<li' + (item.selected ? ' class="active"' : '') + '><a href="#" onclick="loadIn('+ item.pageNumber + ',' + groupId + ')">' + item.pageNumber + (item.selected ? '<span class="sr-only">(current)</span>' : '') + '</a></li>';
								});
								if (data.lastPage) {
									pages += '<li class="disabled"><span>»</span></li>';
								} else {
									pages += '<li><a href="#" onclick="loadIn(' + data.totalPages + ',' + groupId +')">»</a></li>';	
								}
								jQuery('#loadIn-paging ul').append(pages);
		    				}
		    					else {
		    						if(page>0){
		    							loadIn((page-1), groupId);
		    						}
		    							
		    					}
		    					}
							
						},
					});
				
			}
// 			onclick=\"deleteUserGroup("+groupId+","+response[i].id+")\"
			///////// delete user from group //////////
			function deleteOneUS(groupId, deleteOneuserId, deleteOneuserName){
				jQuery("#delete-oneusergroup-popup .modal-body").html("");
  				jQuery("#delete-oneusergroup-popup .modal-body").append("<p><b><spring:message code='group.add.usergroup.message'/> "+deleteOneuserName+"?</b></p><input id='deleteOneuserId' type='hidden' value='"+deleteOneuserId+"'><input id='groupId' type='hidden' value='"+groupId+"'>");
   				//jQuery('#delete-oneusergroup-popup').modal();
			}
				function deleteUserGroup(){
					var groupId=$("#groupId").val();
					var deleteOneuserId=$("#deleteOneuserId").val();
					$.ajax({
						type:'get',
						cache:false,
						url:'<%=request.getContextPath()%>/manage/group/deleteOneUserGroup',
						data : {
							deleteOneuserId : deleteOneuserId, 
							groupId: groupId
						},
						dataType : 'json',
						complete: function(response) {
							var page = parseInt(jQuery('a', jQuery("div#loadIn-paging ul.pagination").find('li.active')).text());
							jQuery('#delete-oneusergroup-popup').modal('hide');
							loadIn(page,group);
							loadAllUserInGroup(group);
		                }
					});
				}
				
				function searchNameInGroup(){

					//group = groupId;
					var name= $("#search_nameInGroup").val();
					jQuery.ajax({
						type: 'get',
						cache:false,
						url:'<%=request.getContextPath()%>/manage/group/searchNameInGroup',
							data : {
								searchName : name,
								groupId: group
							},
							dataType : 'json',
							success : function(response) {
								$("#selectRight").html('');
								for(var i = 0; i < response.length; i++) {
									jQuery("#selectRight").append("<option  value="+ "\"" + response[i].id  + "\">" + response[i].username + "</option>");
								}
							},
						});
				}
				
				function searchNameNotInGroup(){

					//group = groupId;
					var name= $("#search_nameNotInGroup").val();
					jQuery.ajax({
						type: 'get',
						cache:false,
						url:'<%=request.getContextPath()%>/manage/group/searchNameNotInGroup',
							data : {
								searchName : name,
								groupId: group
							},
							dataType : 'json',
							success : function(response) {
								$("#selectLeft").html('');
								for(var i = 0; i < response.length; i++) {
									jQuery("#selectLeft").append("<option  value="+ "\"" + response[i].id  + "\">" + response[i].username + "</option>");
								}
							},
						});
				}
				
			/////// load all users not in group /////////////
				function loadOut(groupId){
					jQuery.ajax({
						type: 'get',
						cache:false,
						url:'<%=request.getContextPath()%>/manage/group/loadUserNot',
							data : {
								user : userId, 
								groupId: groupId
							},
							dataType : 'json',
							success : function(response) {
								$("#search_nameNotInGroup").html('');
								$("#selectLeft").html('');
								jQuery.each(response, function(key, value) {
									jQuery("#selectLeft").append("<option value="+ "\"" + key  + "\">" + value + "</option>");
								});
							},
						});
				}
				function loadAllUserInGroup(groupId){
					jQuery.ajax({
						type: 'get',
						cache:false,
						url:'<%=request.getContextPath()%>/manage/group/loadUserAllUserInGroup',
							data : {
								user : userId, 
								groupId: groupId
							},
							dataType : 'json',
							success : function(response) {
								$("#search_nameNotInGroup").html('');
								$("#selectRight").html('');
								jQuery.each(response, function(key, value) {
									jQuery("#selectRight").append("<option value="+ "\"" + key  + "\">" + value + "</option>");
								});
							},
						});
				}
			

		//////// save user not in group to group ///////////
				function saveUserGroup(){
					var optionValues = [];
					var optionLeft=[];
					$('#selectRight option').each(function() {
						optionValues.push($(this).val());
					});
					$('#selectLeft option').each(function() {
						optionLeft.push($(this).val());
					});
					jQuery.ajax({
						type:'get',
						cache:false,
						url: '<%=request.getContextPath()%>/manage/group/addUserToGroup',
						traditional:true,
						data:{
							userSelected: optionValues,
							userdeleteSelected: optionLeft,
							groupId: group,
						},
						dataType:'json',
						success:function(response){
							jQuery('#add-users-to-role-popup').modal('hide');
							$("#search_nameNotInGroup").html('');
							$("#search_nameInGroup").html('');
							loadIn(1,group);
							loadAllUserInGroup(group);
							loadOut(group);
						}
					});
					return false;
				}


					function searchGroup(){
						var name= $("#name-role-filter").val();
						var status=$("#status-role-filter option:selected").val();
						loadGroup(1,name,status);

					}
					function createGroup(){
						

						var name = $("#name-add-role").val();
						var status  = $("input:radio[name=active_add_role]:checked").val();
						var description=$("#description-add-role").val();
						var optionValues = [];
						$('#roleInGroup option').each(function() {
							optionValues.push($(this).val());
						});
						jQuery.ajax({
							cache:false,
							type: 'post',
							traditional:true,
							url: '<%=request.getContextPath()%>/manage/group/create',
										data : {

											name : name,
											status : status,
											description : description,
 											roleSelected: optionValues
										},
										dataType : 'json',
										success : function(response) {							
											if (response.message != '')
												ttxModal.showError({
													message : response.message,
													onHidden: function(e){
														jQuery('body').addClass('modal-open');
													}						
												})
											else{
												jQuery('#add-role-popup').modal('hide');
												$("#name-add-role").val('');											
												$("#description-add-role").val('');
												$("#roleNotInGroup").html('');
												$("#roleInGroup").html('');
												loadGroup(1,'','');
												loadAllRole();
												
											}

										}
									});
						return false;
					}
					function addgroupbutton(){
						$("#name-add-role").val('');											
						$("#description-add-role").val('');
						loadAllRole();
					}
					function cancelSelect(){
						$("#name-add-role").val('');											
						$("#description-add-role").val('');

						$("#search_nameNotInGroup").val('');
						$("#search_nameInGroup").val('');

						$("#selectLeft").val('');
						$("#selectRight").val('');


						loadIn(1,group);
						loadOut(group);
						loadAllUserInGroup(group);
					}
					///////DElete group////////
					function deleteRole(id,groupname) {
//  						ttxModal.showInfo({ message: 'Bạn có chắc chắn muốn xóa nhóm '+groupname, yesButton: true, noButton: true });
			 			jQuery("#delete-group-message-popup .modal-body").html("");
          				jQuery("#delete-group-message-popup .modal-body").append("<p><b><spring:message code='ttxvn.group.delete.message.yes'/> "+groupname+"?</b></p><input id='input-delete-group' type='hidden' value='"+id+"'>");
           				jQuery('#delete-group-message-popup').modal();
					}
					function yesDeleteGroup(){				
					var id=$("#input-delete-group").val();
			            jQuery.ajax({
			            	cache:false,
			    			type:'post',
			    			url: '<%=request.getContextPath()%>/manage/group/delete',
			    			data: "groupId="+id,
			    			dataType:'json',
			    			success: function(response){
			    				if(response.message!='')
			    					ttxModal.showError({message: response.message});
			    				
			    			},
				    		error: function(response){
				    			console.log('Error while request..'+response.status+' '+response.statusText);
							},
							complete:function(){
								var page = parseInt(parseInt(jQuery("#pagging-item-2").text()));
								jQuery('#delete-group-message-popup').modal('hide');
			 					loadGroup(page,'','');
							}
			    		});
			 
						
					}
					//// select all checkbox Group////
					$('#check-all-group').click(function(event){
						if(this.checked){
							$('.check-group-1').each(function(){
								this.checked = true; 
								});
							}
						else{
							$('.check-group-1').each(function(){
								this.checked = false; 
							});
						}
					});
					
					
						
					//////// select all checkbox //////////////
						 $('#selectAll').click(function(event) {
						        if(this.checked) {
						            $('.checkbox1').each(function() {
						                this.checked = true;              
						            });
						        }else{
						            $('.checkbox1').each(function() {
						                this.checked = false;                     
						            });         
						        }
						    });
						
						 function getValueUsingClass(){
								var chkArray = [];
								$(".checkbox1:checked").each(function() {
									chkArray.push($(this).val());
								});
								if(chkArray.length > 0){
									jQuery("#message-popup .modal-body").html("");
						    		jQuery("#message-popup .modal-body").append("<p><b><spring:message code='group.add.usergroup.message'/> "+chkArray+"?</b></p>");
						    		jQuery('#message-popup').modal();
					    		} else {
					    			jQuery("#delete-selected-group-message-popup .modal-body").html("");
						        	jQuery("#delete-selected-group-message-popup .modal-body").append("<p><b><spring:message code='groupmanagement.user.in.group.deleteList.validate'/></b></p>");
						        	jQuery('#delete-selected-group-message-popup').modal();
					    		}
							}
						 jQuery("#deleteMultipleUsers").on('submit', function(){
								var names = []
								$.each($('.checkbox1:checked'), function (key, value) {
								    names.push($(value).attr("name"));
								}); 
							 	  
								jQuery.ajax({
									type:'post',
									cache:false,
									url: '<%=request.getContextPath()%>/manage/group/deleteMultipleUserGroup',
									traditional:true,
									data:{
										deletedUser: names,
										groupId: group,
									},
									dataType:'json',
									success:function(response){
										var page=parseInt(jQuery('a', jQuery("div#loadIn-paging ul.pagination").find('li.active')).text());
										jQuery('#message-popup').modal('hide');
										$('#selectAll').attr('checked', false);
										loadIn(page,group);
										loadAllUserInGroup(group);
									}
								});
								return false; 
							});
						 /* $(function () {
							  $('#target').click(function () {
							    var checkValues = $('input[name=checkboxlist]:checked').map(function() {
							        return $(this).parent().text();
							    }).get();
							      alert(checkValues);
							  });
							});	 */
						 

						 	

function deleteSelectedGroup(){
	 var chkArray = [];
       $(".check-group-1:checked").each(function() {
    	   chkArray.push($(this).attr("name"));
       });
       
       $('#yes-no-button-selected-group').html('');
       jQuery("#delete-selected-group-message-popup .modal-body").html("");
    		if(chkArray.length >0){
    			$('#yes-no-button-selected-group').append("<button id=\"no-message-btn\" type=\"button\" class=\"ttx-btn btn btn-info text-uppercase\" data-dismiss=\"modal\">Không</button>"
    	    			+"<button id=\"yes-message-btn\" type=\"button\" class=\"ttx-btn btn btn-danger text-uppercase\" onclick=\"yesDeleteSelectedGroup()\">Có</button>");

    			jQuery("#delete-selected-group-message-popup .modal-body").append("<p><b><spring:message code='ttxvn.group.delete.message.yes'/> "+chkArray+"?</b></p>");	
    		}
    		else{
    
    			jQuery("#delete-selected-group-message-popup .modal-body").append("<p><b><spring:message code='ttxvn.group.uncheck'/></b></p>");
    		}

    	       jQuery('#delete-selected-group-message-popup').modal();

}
function yesDeleteSelectedGroup(){
			var groupIds=[];
			 $(".check-group-1:checked").each(function() {
				 groupIds.push($(this).val());
			  });
			 
			if(groupIds.length>0){
		       		jQuery.ajax({
		       			cache:false,
			            type:'post',
			            url: '<%=request.getContextPath()%>/manage/group/deleteAll',
			            traditional:true,
			            data:{
			             groupIds: groupIds,
			            },
			            dataType:'json',
			            success: function(response){
		   				if(response.message!='')
		   					ttxModal.showError({message: response.message}); 
		   			},
			    		error: function(response){
			    			console.log('Error while request..'+response.status+' '+response.statusText);
						},
						complete:function(){
							var page = parseInt(parseInt(jQuery("#pagging-item-2").text()));
			            	jQuery('#delete-selected-group-message-popup').modal('hide');
			            	loadGroup(page,'','');
			            	$('#check-all-group').prop("checked", false);
			            },
			            
		          });
		       return false;
			}
			else
				jQuery('#delete-selected-group-message-popup').modal('hide');
}
function loadGroupModified(id){
	jQuery.ajax({
		cache:false,
		type: 'get',
		url:'<%=request.getContextPath()%>/manage/group/group-details',
			data:{
				groupId: id,
			},
			dataType : 'json',
			success: function(response) {
				$("#modified-role-popup-name").html('');
				$("#modified-role-popup-description").html('');
				$("#modified-role-popup-status").html('');
				$('#modified-role-popup-name').append("<input id=\"id-modified-role\" value=\""+response.id+"\" type=\"hidden\" />"
						+"<input id=\"name-modified-role\" type=\"text\" required class=\"ttx-form-control form-control\" value=\""+response.name+"\"/>");
				$('#modified-role-popup-description').append("<textarea id=\"description-modified-role\" class=\"ttx-form-control form-control\">"+response.description+"</textarea>");
				if(response.status=="ACTIVE")
				{
					$('#modified-role-popup-status').append("<label class=\"radio-inline\">"
	                        +"<input type=\"radio\" name=\"active_modified_role\" id=\"active-modified-role\" value=\"active\" checked=\"checked\"/><spring:message code='ttxvn.group.status.active'/>"
	                        +"</label>"
	                        +"<label class=\"radio-inline\">"
	                            +"<input type=\"radio\" name=\"active_modified_role\" id=\"inactive-modified-role\" value=\"inactive\"/><spring:message code='ttxvn.group.status.inactive'/>"
	                        +"</label>");
				}
				else{
					$('#modified-role-popup-status').append("<label class=\"radio-inline\">"
	                        +"<input type=\"radio\" name=\"active_modified_role\" id=\"active-modified-role\" value=\"active\"/><spring:message code='ttxvn.group.status.active'/>"
	                        +"</label>"
	                        +"<label class=\"radio-inline\">"
	                            +"<input type=\"radio\" name=\"active_modified_role\" id=\"inactive-modified-role\" value=\"inactive\" checked=\"checked\"/><spring:message code='ttxvn.group.status.inactive'/>"
	                        +"</label>");
				}
			},
		});
		loadRoleIn(id);
		loadRoleNot(id);
	} 
	function modifiedGroup(){
		var id = $("#id-modified-role").val();
		var name= $("#name-modified-role").val();
		var description=$("#description-modified-role").val();
		var status  = $("input:radio[name=active_modified_role]:checked").val();
		var optionValues = [];
		$('#editRoleInGroup option').each(function() {
			optionValues.push($(this).val());
		});
		var leftValues = [];
		$('#editRoleNotInGroup option').each(function(){
			leftValues.push($(this).val());
		});
		 jQuery.ajax({
			 cache:false,
 			type:'post',
 			traditional:true,
 			url: '<%=request.getContextPath()%>/manage/group/update',
 			data:{
 				user:userId,
	             groupId : id,
	             name : name,
	             description : description,
	             status : status,
	             addRoleSelected: optionValues,
	             leftValues:leftValues
	            },
 			dataType:'json',
 			success: function(response){
 				if(response.message!='')
 					{
 					ttxModal.showError({message: response.message,
 						onHidden: function(e){
							jQuery('body').addClass('modal-open');
						}
 						});
 					}
 				else{
 					var page = parseInt(jQuery("#pagging-item-2").text());
 					jQuery('#modified-role-popup').modal('hide');
					loadGroup(page,'','');
 				}
 			}
	    		
 		});
		$("#modified-role-popup-name").val('');
		$("#modified-role-popup-description").val('');
		$("#modified-role-popup-status").val('');
	}
	$("#name-role-filter").keypress(function (event) {
		if (event.which == 13) { 
			searchGroup();
		return false;

		} 
		}); 
	/* $("#add-role-popup").keypress(function(event){
		if(event.which == 27){
			alert(1);
			cancelSelect();
			return false;
		}
	}) */
// 	$("#"add-role-popup)
	function loadAllRole(){
		
		$.ajax({
			cache:false,
			type:'get',
			url:'<%=request.getContextPath()%>/manage/group/loadAllRole',
			dataType : 'json',
			success : function(response) {
				$("#roleNotInGroup").html('');
				$("#roleInGroup").html('');
				jQuery.each(response, function(key, value) {
					jQuery("#roleNotInGroup").append("<option value="+ "\"" + key  + "\">" + value + "</option>");
				});
			},
		})
	}
/////// load roles not in group /////////////
	function loadRoleNot(groupId){

		jQuery.ajax({
			cache:false,
			type: 'get',
			url:'<%=request.getContextPath()%>/manage/group/loadRoleNotIn',
				data : {
					groupId: groupId
				},
				dataType : 'json',
				success : function(response) {
					$("#editRoleNotInGroup").html('');
					for(var i = 0; i < response.length; i++) {
						jQuery("#editRoleNotInGroup").append("<option  value="+ "\"" + response[i].id  + "\">" + response[i].name + "</option>");
					}
				},
			});
	}
	///load roles in group ///////
	function loadRoleIn(id){
		
		$.ajax({
			cache:false,
			type:'get',
			url:'<%=request.getContextPath()%>/manage/group/loadRoleIn',
			data : {
				groupId: id
			},
			dataType : 'json',
			success : function(response) {
				$("#editRoleInGroup").html('');
				for(var i = 0; i < response.length; i++) {
					jQuery("#editRoleInGroup").append("<option  value="+ "\"" + response[i].id  + "\">" + response[i].name + "</option><br/>");
				}
			},
		})
	}
// 	name-role-filter
</script>
</body>
</html>