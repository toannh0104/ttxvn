<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/tags.jsp"%>
<jsp:include page="/WEB-INF/views/includes/header.jsp"/>

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
                                        <a href="${pageContext.request.contextPath}/manage/user" title="<spring:message code="homepage.mainMenu.userMangament"/>">
                                            <span class="ttx-icon ttx-icon-user"></span>
                                            <spring:message code="homepage.mainMenu.userMangament"/>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="${pageContext.request.contextPath}/manage/sourceUrl" class="active" title="<spring:message code="homepage.mainMenu.systemConfiguration"/>">
                                            <span class="ttx-icon ttx-icon-settings"></span>
                                            <spring:message code="homepage.mainMenu.systemConfiguration"/>
                                        </a>
                                        <ul class="sub-menu open">
                                            <li>
                                                <a href="${pageContext.request.contextPath}/manage/sourceUrl" class="active">
                                                    <span class="ttx-icon"></span>
                                                    <spring:message code='ttxvn.keywordsearching.systemConfig.mainmenu.sourceManagement'/>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="${pageContext.request.contextPath}/manage/fetchFrequency">
                                                    <span class="ttx-icon"></span>
                                                    <spring:message code='ttxvn.keywordsearching.systemConfig.mainmenu.crawlingFrequency'/>
                                                </a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li>
                                        <a href="${pageContext.request.contextPath}/report/summary" title="<spring:message code="homepage.mainMenu.report"/>">
                                            <span class="ttx-icon ttx-icon-report"></span>
                                            <spring:message code="homepage.mainMenu.report"/>
                                        </a>
                                    </li>
                                </ul>
                            </nav>

                            <div class="topic-add">
                                <form action="#" method="post" class="ttx-form-group" role="form">
                                    <div class="input-group" style="width:100%;">
                                        <span class="input-group-addon ttx-text-group"><spring:message code="homepage.category.title"/></span>
                                        <div class="input-group-btn">
                                            <!-- <button type="button" class="btn ttx-btn-add" data-toggle="modal" data-target="#add-topic-popup"></button> -->
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <nav class="menu-topics">
                                <ul>
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
                                    <div class="row">
                                        <div class="col-xs-4 col-sm-4 col-md-4">
                                            <form action="#" role="search" class="ttx-form-group">
                                                <div class="ttx-search-group">
                                                    <input type="search" class="form-control" placeholder='<spring:message code='homepage.search.hint'/>' />
                                                    <button type="submit" class="btn ttx-btn-search">
                                                        <span class="ttx-search-group"></span>
                                                    </button>
                                                </div>
                                            </form>
                                        </div>
                                        <div class="col-xs-4 col-sm-4 col-md-offset-1 col-md-3">
                                            <div class="text-right">
                                                <button type="button" class="ttx-btn-add btn ttx-btn-group" data-toggle="modal" onclick="checkCategory()">
                                                    <span class="ttx-icon-add ttx-icon"></span>
                                                    <spring:message code='ttxvn.keywordsearching.systemConfig.sourceUrl.addLink'/>
                                                </button>
                                            </div>
                                        </div>
                                        <div class="col-xs-4 col-sm-4 col-md-offset-1 col-md-3">
                                            <div class="text-right">
                                                <button type="button" class="ttx-btn-remove btn ttx-btn-group" onclick="deleteMultiItem()">
                                                    <span class="ttx-icon-remove ttx-icon"></span>
                                                    <spring:message code='ttxvn.keywordsearching.systemConfig.sourceUrl.deleteLink'/>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
							<form  name="form_id" id="form_id"  method="post" action="">
                                <table class="ttx-table-management table table-bordered table-striped table-hover table-responsive">
                                    <thead>
                                        <tr>
                                            <th class="text-center">
                                                <input type="checkbox" id="select-all"/>
                                            </th>
                                            <th><spring:message code='ttxvn.keywordsearching.systemConfig.sourceUrl.table.link'/></th>
                                            <th><spring:message code='ttxvn.keywordsearching.systemConfig.sourceUrl.table.type'/></th>
                                            <th><spring:message code='ttxvn.keywordsearching.systemConfig.sourceUrl.table.reliability'/></th>
                                            <th><spring:message code='ttxvn.keywordsearching.systemConfig.sourceUrl.table.frequency'/></th>
                                            <th><spring:message code='ttxvn.keywordsearching.systemConfig.sourceUrl.table.deep'/></th>
                                            <th colspan="2"><spring:message code='ttxvn.keywordsearching.systemConfig.sourceUrl.table.action'/></th>
                                        </tr>
                                    </thead>
                                    <tbody>
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

                    <div class="clearfix"></div>
                </div>
            </div>
        </div>

        <jsp:include page="/WEB-INF/views/includes/footer.jsp"/>
    </div>

    <!-- Modal -->
    <!-- command show popup: $('#add-link-popup').modal()-->
    <div id="add-link-popup" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="addLinkModalLabel" aria-hidden="true" data-backdrop="static">
        <div class="modal-dialog">
            <div class="ttx-model-content modal-content">
                <form action="#" class="form-horizontal" role="form" novalidate>
                    <div class="ttx-modal-header modal-header">
                        <button type="button" class="ttx-close close" data-dismiss="modal"><span class="sr-only"></span></button>
                        <h4 class="modal-title text-center text-uppercase" id="addLinkModalLabel"><spring:message code='ttxvn.keywordsearching.systemConfig.sourceUrl.add.title'/></h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="url-link" class="col-sm-2 control-label"><spring:message code='ttxvn.keywordsearching.systemConfig.sourceUrl.label.url'/></label>
                            <div class="col-sm-10">
                                <input id="url-link" type="text" class="ttx-form-control form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="type-link" class="col-sm-2 control-label"><spring:message code='ttxvn.keywordsearching.systemConfig.sourceUrl.label.type'/></label>
                            <div class="col-sm-10">
                                <select id="type-link" class="ttx-form-control form-control">
                                    <option value="ttxvn.keywordsearching.sourceUrlType.newspaper"><spring:message code="ttxvn.keywordsearching.sourceUrlType.newspaper"/></option>
                                    <option value="ttxvn.keywordsearching.sourceUrlType.socialNetwork"><spring:message code="ttxvn.keywordsearching.sourceUrlType.socialNetwork"/></option>
                                    <option value="ttxvn.keywordsearching.sourceUrlType.forum"><spring:message code="ttxvn.keywordsearching.sourceUrlType.forum"/></option>
                                    <option value="ttxvn.keywordsearching.sourceUrlType.other"><spring:message code="ttxvn.keywordsearching.sourceUrlType.other"/></option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="trust-rate-link" class="col-sm-2 control-label"><spring:message code='ttxvn.keywordsearching.systemConfig.sourceUrl.label.reliability'/></label>
                            <div class="col-sm-10">
                                <div class="form-inline">
                                    <input id="trust-rate-link" type="text" class="ttx-form-control col-sm-1 form-control"/>
                                    &nbsp;
                                    <label for="trust-rate-link" class="control-label">(0-100)</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="frequency-link" class="col-sm-2 control-label"><spring:message code='ttxvn.keywordsearching.systemConfig.sourceUrl.label.frequency'/></label>
                            <div class="col-sm-10">
                                <select id="frequency-link" class="ttx-form-control form-control">
                                </select>
                            </div>
                        </div>
                       <div class="form-group">
                            <label for="deep-link" class="col-sm-2 control-label"><spring:message code='ttxvn.keywordsearching.systemConfig.sourceUrl.label.deep'/></label>
                            <div class="col-sm-10">
                                <select id="deep-link" class="ttx-form-control form-control">
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="ttx-modal-footer modal-footer">
                        <div class="text-center">
                            <button type="button" class="ttx-btn btn btn-info text-uppercase" data-dismiss="modal"><spring:message code='ttxvn.keywordsearching.systemConfig.sourceUrl.cancel'/></button>
                            <button type="submit" class="ttx-btn btn btn-danger text-uppercase"><spring:message code='ttxvn.keywordsearching.systemConfig.sourceUrl.save'/></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- command show popup: $('#edit-link-popup').modal()-->
    <div id="edit-link-popup" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="editLinkModalLabel" aria-hidden="true" data-backdrop="static">
        <div class="modal-dialog">
            <div class="ttx-model-content modal-content">
                <form action="#" class="ttx-form-more form-horizontal" role="form">
                    <div class="ttx-modal-header modal-header">
                        <button type="button" class="ttx-close close" data-dismiss="modal"><span class="sr-only"></span></button>
                        <h4 class="modal-title text-center text-uppercase" id="editLinkModalLabel"><spring:message code="ttxvn.keywordsearching.systemConfig.sourceUrl.edit.title"/></h4>
                    </div>
                    <div class="more-information">
                        <table class="table-condensed">
                        </table>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="url-link-edit" class="col-sm-2 control-label"><spring:message code='ttxvn.keywordsearching.systemConfig.sourceUrl.label.url'/></label>
                            <div class="col-sm-10">
                                <input id="url-link-edit" type="text" class="ttx-form-control form-control" />
                                <input id="url-link-id" type="hidden" value=""/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="type-link-edit" class="col-sm-2 control-label"><spring:message code='ttxvn.keywordsearching.systemConfig.sourceUrl.label.type'/></label>
                            <div class="col-sm-10">
                                <select id="type-link-edit" class="ttx-form-control form-control">
                                    <option value="ttxvn.keywordsearching.sourceUrlType.newspaper"><spring:message code="ttxvn.keywordsearching.sourceUrlType.newspaper"/></option>
                                    <option value="ttxvn.keywordsearching.sourceUrlType.socialNetwork"><spring:message code="ttxvn.keywordsearching.sourceUrlType.socialNetwork"/></option>
                                    <option value="ttxvn.keywordsearching.sourceUrlType.forum"><spring:message code="ttxvn.keywordsearching.sourceUrlType.forum"/></option>
                                    <option value="ttxvn.keywordsearching.sourceUrlType.other"><spring:message code="ttxvn.keywordsearching.sourceUrlType.other"/></option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="trust-rate-link-edit" class="col-sm-2 control-label"><spring:message code='ttxvn.keywordsearching.systemConfig.sourceUrl.label.reliability'/></label>
                            <div class="col-sm-10">
                                <div class="form-inline">
                                    <input id="trust-rate-link-edit" type="number" class="ttx-form-control col-sm-1 form-control" />
                                    &nbsp;
                                    <label for="trust-rate-link-edit" class="control-label">(0-100)</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="frequency-link" class="col-sm-2 control-label"><spring:message code='ttxvn.keywordsearching.systemConfig.sourceUrl.label.frequency'/></label>
                            <div class="col-sm-10">
                                <select id="frequency-link" class="ttx-form-control form-control">
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="deep-link-edit" class="col-sm-2 control-label"><spring:message code='ttxvn.keywordsearching.systemConfig.sourceUrl.label.deep'/></label>
                            <div class="col-sm-10">
                                <select id="deep-link-edit" class="ttx-form-control form-control">
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="ttx-modal-footer modal-footer">
                        <div class="text-center">
                            <button type="button" class="ttx-btn btn btn-info text-uppercase" data-dismiss="modal"><spring:message code='ttxvn.keywordsearching.systemConfig.sourceUrl.cancel'/></button>
                            <button type="submit" class="ttx-btn btn btn-danger text-uppercase"><spring:message code='ttxvn.keywordsearching.systemConfig.sourceUrl.save'/></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- command show popup: $('#delete-link-popup').modal()-->
    <div id="delete-link-popup" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="deleteLinkModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="ttx-model-content modal-content">
                <form action="#" class="form-horizontal" role="form">
                    <div class="ttx-modal-header modal-header">
                        <button type="button" class="ttx-close close" data-dismiss="modal"><span class="sr-only">Close</span></button>
                        <h4 class="modal-title text-center text-uppercase" id="deleteLinkModalLabel"><spring:message code='ttxvn.keywordsearching.systemConfig.sourceUrl.delete.title'/></h4>
                    </div>
                    <div class="modal-body">
                        <div class="message-text">
                            <p><b></b></p>
                            <ul class="link-item">
                            </ul>
                        </div>
                    </div>
                    <div class="ttx-modal-footer modal-footer">
                        <div class="text-center">
                            <button type="button" class="ttx-btn btn btn-info text-uppercase" data-dismiss="modal"><spring:message code="ttxvn.keywordsearching.systemConfig.sourceUrl.delete.cancel"/></button>
                            <button type="submit" class="ttx-btn btn btn-danger text-uppercase"><spring:message code='ttxvn.keywordsearching.systemConfig.sourceUrl.delete.apply'/></button>
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
                <form action="#" method="post" role="form">
                    <div class="ttx-modal-header modal-header">
                        <button type="button" class="ttx-close close" data-dismiss="modal"><span class="sr-only">Close</span></button>
                        <h4 class="modal-title text-center text-uppercase" id="messageModalLabel"><spring:message code='ttxvn.keywordsearching.systemConfig.sourceUrl.delete.title'/></h4>
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
                            <button id="no-message-btn" type="button" class="ttx-btn btn btn-info text-uppercase" data-dismiss="modal"><spring:message code='homepage.message.delete.cancel'/></button>
                            <button id="yes-message-btn" type="submit" class="ttx-btn btn btn-danger text-uppercase"><spring:message code="homepage.message.delete.apply"/></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
     <!-- command show popup: $('#delete--all-source-message-popup').modal() -->
    <div id="delete-list-source-message-error-popup" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="deleteTopicMessageModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="ttx-model-content modal-content">
                <form action="#" role="form">
                    <div class="ttx-modal-header modal-header">
                        <button type="button" class="ttx-close close" data-dismiss="modal"><span class="sr-only">Close</span></button>
                        <h4 class="modal-title text-center text-uppercase" id="deleteTopicMessageModalLabel"><spring:message code="sourcemanagement.source.delete.title"/></h4>
                    </div>
                    <div class="modal-body">
                        <p><b><spring:message code="sourcemanagement.source.delete.title"/></b></p>
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
    <script type="text/javascript">
    	var user = 1;
    	var selectedCategory;
    	jQuery(document).ready(function(){
    		loadCategory();
    		loadFrequency();
    		loadSourceUrl(1, 0, '');
    	});
    	
    	function loadFrequency()
    	{
    		jQuery('.col-sm-10 #frequency-link').html('');
    		jQuery.ajax({
    			type: 'get',
    			url:'<%=request.getContextPath()%>/manage/fetchFrequency/getAll',
    			dataType: 'json',
    			success: function(response){
    				jQuery.each(response, function(key, value){
    					jQuery('.col-sm-10 #frequency-link').append("<option value='"+key+"'>"+value+" <spring:message code='ttxvn.keywordsearching.systemConfig.label.minute'/></option>");
    				});
    			}
    		});
    	}
    	
    	function loadCategory(){
    		jQuery('.menu-topics ul').html('');
    		jQuery.ajax({
    			cache: false,
    			type: 'get',
    			url: '<%=request.getContextPath()%>/manage/sourceUrl/getCategories',
    			dataType: 'json',
    			data: {
    				user: user
    			},
    			success: function(response){
    				var content= '';
    				for(var i = 0; i < response.length; i++){
    					if(i == 0){
    						if(response[i].urlNo >= 100)
    							jQuery('.ttx-btn-add').attr('disabled','disabled');
    						else jQuery('.ttx-btn-add').removeAttr('disabled');
    					}
    					content += "<li value='"+response[i].categoryId+"'><a href='#' title='"+response[i].categoryName+"' onclick=\"loadSourceUrl(1,"+response[i].categoryId+", '')\"><span class='glyphicon glyphicon-stop'></span>"
						+response[i].categoryName+" ("+response[i].urlNo+")</a></li>";
    				}
    				jQuery('.menu-topics ul').append(content);
    				jQuery(".menu-topics ul li:first-child a").addClass("active");
    				jQuery(".menu-topics ul").find('a:visible').click(function () {
    				    jQuery(this).parent().siblings().find('a.active').removeClass('active');
    				    jQuery(this).addClass('active');
    				   // console.log('Bạn vừa click vào menu .menu-topics');

    				    return false;
    				});
    			} 
    		});
    	}
    	
    	function loadSourceUrl(page, category, url){
    		//jQuery('.col-md-12').html('');
    		jQuery.ajax({
    			cache: false,
    			type: 'get',
    			url: '<%=request.getContextPath()%>/manage/sourceUrl/findURLByPage',
    			dataType: 'json',
    			data: {
    				page: page,
    				category: category,
    				url: url
    			},
    			success: function(data){
    				jQuery('.ttx-table-management tbody').html('');
    				jQuery('#links-paging ul').html('');
    				jQuery('#add-link-popup #trust-rate-link').val('');
    				
    				if(data.totalPages > 0){
    					var content = '';
    					for(var i = 0; i < data.content.length; i++){
    						content += "<tr><td class='text-center'><input type='checkbox' class='url-item' name='"+data.content[i].url+"' value='"+data.content[i].sourceUrlId+"'/></td>"
    							+"<td>"+data.content[i].url+"</td>"
    							+"<td>"+data.content[i].sourceTypeCode+"</td>"
    							+"<td class='text-center'>"+data.content[i].reliability+"</td>"
    							+"<td class='text-center'>"+data.content[i].frequency+"</td>"
    							+"<td class='text-center'>"+data.content[i].deep+"</td>"
    							+"<td class='text-center ttx-action'><a href='#' onclick=\"editSourceUrl("+data.content[i].sourceUrlId+")\"><span class='ttx-icon-edit ttx-icon'></span><span class='sr-only'>Chinh sua</span></a></td>"
    							+"<td class='text-center ttx-action'><a href='#' onclick=\"deleteItem("+data.content[i].sourceUrlId+", '"+data.content[i].url+"')\"><span class='ttx-icon-delete ttx-icon'></span><span class='sr-only'>Xoa</span></a></td></tr>";
    					}
    					jQuery('.ttx-table-management tbody').append(content);
    					
    					var pages = '';
    					var category = jQuery('.menu-topics ul li a.active').parent().val();
    					if (data.firstPage) {
    						pages += '<li class="disabled"><span>«</span></li>';
						} else {
							pages += '<li><a href="#" onclick="loadSourceUrl(1,' + category + ',\''+url+'\')">«</a></li>';	
						}
						$.each(data.pageItems, function(index, item){
							pages += '<li' + (item.selected ? ' class="active"' : '') + '><a href="#" onclick="loadSourceUrl('+ item.pageNumber + ',' + category + ',\''+url+'\')">' + item.pageNumber + (item.selected ? '<span class="sr-only">(current)</span>' : '') + '</a></li>';
						});
						if (data.lastPage) {
							pages += '<li class="disabled"><span>»</span></li>';
						} else {
							pages += '<li><a href="#" onclick="loadSourceUrl(' + data.totalPages + ',' + category + ',\''+url+'\')">»</a></li>';	
						}
						jQuery('#links-paging ul').append(pages);
    				}
    			}
    		});
    	}
    	
    	jQuery("#add-link-popup form").on('submit', function(){
    		var url = jQuery('#add-link-popup #url-link').val();
    		var type = jQuery('#add-link-popup #type-link').val();
    		var reliability = jQuery('#add-link-popup #trust-rate-link').val();
    		var frequency = jQuery('#add-link-popup #frequency-link').val();
    		var deep = jQuery('#add-link-popup #deep-link').val();
    		var category = jQuery('.menu-topics ul li a.active').parent().val();
    		jQuery.ajax({
    			type: 'post',
    			url: '<%=request.getContextPath()%>/manage/sourceUrl/create',
    			data: {
    				url: url,
    				type: type,
    				reliability: reliability,
    				frequency: frequency,
    				deep: deep,
    				category: category,
    				user: user
    			},
    			dataType: 'json',
    			success: function(response){
    				if(response.message != ''){
    					ttxModal.showError({message: response.message});
    				}
    				else{ 
    					jQuery('#add-link-popup').modal('hide');
    					jQuery('#add-link-popup #trust-rate-link').val('');
    					loadCategory();
    					loadSourceUrl(1, 0,'');
    				}
    			}
    		});
    		return false;
    	});
    	
    	function checkCategory(){
    		var category = jQuery('.menu-topics ul li a.active').parent().val();
    		if(category==0)
    			ttxModal.showError({message: '<spring:message code='ttxvn.keywordsearching.systemConfig.sourceUrl.add.alert'/>',
					onHidden: function(e){
						jQuery('body').addClass('modal-open');
					}});
    		else{ 
    			jQuery('#add-link-popup').modal();
    			jQuery('#add-link-popup #url-link').val('');
        		jQuery('#add-link-popup #trust-rate-link').val('');	
    		}
    			
    	}
    	
    	function editSourceUrl(sourceId){
    		jQuery.ajax({
    			type: 'get',
    			url: '<%=request.getContextPath()%>/manage/sourceUrl/update',
    			data:{urlId: sourceId},
    			dataType: 'json',
    			success: function(data){
    				jQuery('#edit-link-popup #url-link-edit').val(data.url);
    				jQuery('#edit-link-popup #type-link-edit').val(data.sourceType);
    				jQuery('#edit-link-popup #deep-link-edit').val(data.deep);
    				jQuery('#edit-link-popup #trust-rate-link-edit').val(data.reliability);
    				jQuery('#edit-link-popup #frequency-link').val(data.frequencyId);
    				jQuery('#edit-link-popup #url-link-id').val(data.urlId);
    	            var content = "<tr><td><spring:message code='ttxvn.keywordsearching.systemConfig.sourceUrl.edit.label.oldUrl'/>"+data.url+"</td>"
    	             		+"<td><spring:message code='ttxvn.keywordsearching.systemConfig.sourceUrl.edit.label.type'/>"+data.type+"</td>"
    	             		+"<td class='text-right'><spring:message code='ttxvn.keywordsearching.systemConfig.sourceUrl.edit.label.reliability'/>"+data.reliability+"</td></tr>";
    	    		jQuery('.table-condensed').html(content);
    	    		jQuery('#edit-link-popup').modal();
    			}
    		});
    	}
    	
    	jQuery('#edit-link-popup form').on('submit', function(){
    		var url = jQuery('#edit-link-popup #url-link-edit').val();
    		var type = jQuery('#edit-link-popup #type-link-edit').val();
    		var reliability = jQuery('#edit-link-popup #trust-rate-link-edit').val();
    		var frequency = jQuery('#edit-link-popup #frequency-link').val();
    		var deep = jQuery('#edit-link-popup #deep-link-edit').val();
    		var category = jQuery('.menu-topics ul li a.active').parent().val();
    		var urlId = jQuery('#edit-link-popup #url-link-id').val();
    		jQuery.ajax({
    			type: 'post',
    			url: '<%=request.getContextPath()%>/manage/sourceUrl/update',
    			data: {
    				urlId: urlId,
    				url: url,
    				type: type,
    				reliability: reliability,
    				frequency: frequency,
    				deep: deep,
    				category: category,
    				user: user
    			},
    			dataType: 'json',
    			success: function(response){
    				if(response.message != ''){
    					ttxModal.showError({message: response.message});
    				}
    				else{ 
    					jQuery('#edit-link-popup').modal('hide');
    					var page = jQuery('#links-paging ul li a.active').text();
    					loadSourceUrl(page, category,'');	
    				}
    			}
    		});
    		return false;
    	});
    	
    	function deleteItem(urlId, url){
    		jQuery("#delete-link-popup .message-text p b").text("<spring:message code='ttxvn.keywordsearching.systemConfig.sourceUrl.delete.message'/>");
    		var deletedItem = "<li class='link-item' value='"+urlId+"'><b>"+url+"</b></li>";
    		jQuery("#delete-link-popup .message-text .link-item").html(deletedItem);
    		jQuery('#delete-link-popup').modal();
    	}
    	
    	function deleteMultiItem(){ 	
        	var check = jQuery(".url-item:checked");
        	
        	if(check.length == 0)
    		{
	    		jQuery("#delete-list-source-message-error-popup .modal-body").html("");
	        	jQuery("#delete-list-source-message-error-popup .modal-body").append("<p><b><spring:message code='sourcemanagement.source.deleteList.validate'/></b></p>");
	        	jQuery('#delete-list-source-message-error-popup').modal();
    		}
    		else
    		{
    			jQuery("#delete-link-popup .message-text p b").text("<spring:message code='ttxvn.keywordsearching.systemConfig.sourceUrl.delete.message'/>");
        		var content = '';
        		jQuery(".url-item:checked").each(function(){
        			content += "<li class='link-item' value='"+jQuery(this).val()+"'><b>"+jQuery(this).attr('name')+"</b></li>";
        		});
        		
        		jQuery("#delete-link-popup .message-text .link-item").html(content);
        		jQuery('#delete-link-popup').modal();
    		}
    		
    	}
    	
    	jQuery('#delete-link-popup').on('submit', function(){
    		var items = [];
    		jQuery('#delete-link-popup .message-text .link-item li').each(function(){
    			items.push(String(jQuery(this).val()));
    		});
    		
    		jQuery.ajax({
    			type: 'post',
    			url: '<%=request.getContextPath()%>/manage/sourceUrl/deleteUrl',
    			dataType: 'json',
    			data: {items: items},
    			complete: function(){
    				jQuery('#delete-link-popup').modal('hide');
    				loadSourceUrl(1, 0,'');
    				loadCategory();
    			}
    		});
    		return false;
    	});
    	
    	jQuery('#select-all').click(function selectAll(){
    		//alert(jQuery('.ttx-table-management tbody tr td input[type=checkbox]').val());
    		jQuery('.url-item').prop("checked", jQuery(this).prop("checked"));
    	});
    	
    	jQuery('.ttx-command form').on('submit', function(){
    		var url = jQuery('.ttx-command form input[type=search]').val();
    		var category = jQuery('.menu-topics ul li a.active').parent().val();
    		loadSourceUrl(1, category,url);
    		return false;
    	});
    	
    	$('#type-link').change(function() {
    	    if ($(this).val() === 'ttxvn.keywordsearching.sourceUrlType.other') {
    	    	jQuery('#add-link-popup #trust-rate-link').val('0');
    	    }
    	    else{
    	    	jQuery('#add-link-popup #trust-rate-link').val('');
    	    }
    	});
    	
    	$('#type-link-edit').change(function() {
    	    if ($(this).val() === 'ttxvn.keywordsearching.sourceUrlType.other') {
    	    	jQuery('#edit-link-popup #trust-rate-link-edit').val('0');
    	    }
    	    else{
    	    	jQuery('#edit-link-popup #trust-rate-link-edit').val('');
    	    }
    	});
    	
    </script>
</body>
</html>