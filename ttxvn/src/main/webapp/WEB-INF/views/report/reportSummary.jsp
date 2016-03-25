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
                                            <spring:message code="homepage.mainMenu.homepage" />
                                        </a>
                                    </li>
                                    <li>
                                        <a href="${pageContext.request.contextPath}/manage/user/"
										title="<spring:message code="homepage.mainMenu.userMangament"/>">
											<span class="ttx-icon ttx-icon-user"></span> <spring:message
												code="homepage.mainMenu.userMangament" />
										</a>

                                        <!-- <ul class="sub-menu">
                                            <li>
                                                <a href="3_25_user_management.html" title="Quản trị người dùng">
                                                    <span class="ttx-icon"></span>
                                                    Quản trị người dùng
                                                </a>
                                            </li>
                                            <li>
                                                <a href="3_32_role_management.html" title="Quản trị nhóm người dùng">
                                                    <span class="ttx-icon"></span>
                                                    Quản trị nhóm người dùng
                                                </a>
                                            </li>
                                        </ul> -->
                                    </li>
                                    <li>
                                        <a
										href="${pageContext.request.contextPath}/manage/sourceUrl"
										title="<spring:message code="homepage.mainMenu.systemConfiguration"/>">
											<span class="ttx-icon ttx-icon-settings"></span> <spring:message
												code="homepage.mainMenu.systemConfiguration" />
										</a>

                                        <!-- <ul class="sub-menu">
                                            <li>
                                                <a href="3_12_system_management.html">
                                                    <span class="ttx-icon"></span>
                                                    Quản lý nguồn tin
                                                </a>
                                            </li>
                                            <li>
                                                <a href="3_16_frequency_management.html">
                                                    <span class="ttx-icon"></span>
                                                    Tần suất thu thập
                                                </a>
                                            </li>
                                        </ul> -->
                                    </li>
                                    <li>
                                        <a
										href="${pageContext.request.contextPath}/report/summary"
										title="<spring:message code="homepage.mainMenu.report"/>"  class="active"> 
											<span class="ttx-icon ttx-icon-report"></span> <spring:message
												code="homepage.mainMenu.report" />
									</a>
                                    </li>
                                </ul>
                            </nav>
                        </div>

                        <div class="nav-menu-right-panel" title="Click to collapse left pane"></div>
                    </div>

                    <div class="right-panel" style="margin-left: 225px;">
                        <!-- Implement Your HTML code -->
                        <div class="row">
                            <div class="col-md-8">
                                <div class="text-report">
                                    <span class="ttx-text-report"><spring:message
												code="ttxvn.report.title" /></span>
                                    <span class="ttx-btn-report"></span>
                                </div>
                                <div class="background-report">
                                    <div class="ttx-label-option">
                                        <div class="row">
                                            <div class="col-md-4">
                                                <div class="row">
                                                    <form class="form-horizontal" role="form">
                                                        <div class="form-group">
                                                            <label for="ttx-subject" class="control-label col-md-5 col-sm-3"><spring:message
												code="ttxvn.report.category" /></label>
                                                            <div class="col-md-7 col-sm-9">
                                                                <select id="ttx-subject" type="text"
                                                                        class="ttx-form-control form-control">
                                                                    <option value="0"><spring:message
												code="ttxvn.report.all" /></option>
																	<c:forEach items="${categories}" var="category">
																		<option value="${category.id}">${category.name}</option>
																	</c:forEach>
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="row">
                                                    <form class="form-horizontal" role="form">
                                                        <div class="form-group">
                                                            <label for="ttx-kye" class="control-label col-md-5 col-sm-3"><spring:message
												code="ttxvn.report.keyword" /></label>
                                                            <div class="col-md-7 col-sm-9">
                                                                <select id="ttx-kye" type="text"
                                                                        class="ttx-form-control form-control">
                                                                    <option value="0"><spring:message code="ttxvn.report.all" /></option>															
																	<c:forEach items="${keywords}" var="keyword">
	        															<option value="${keyword.key}">${keyword.value}</option>
	    															</c:forEach>
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="row">
                                                    <form class="form-horizontal" role="form">
                                                        <div class="form-group">
                                                            <label for="ttx-news" class="control-label col-md-5 col-sm-3"><spring:message code="ttxvn.report.sourceurl" /></label>
                                                            <div class="col-md-7 col-sm-9">
                                                                <select id="ttx-news" type="text"
                                                                        class="ttx-form-control form-control">
                                                                    <option value="0"><spring:message code="ttxvn.report.all" /></option>
																	<c:forEach items="${sourceUrls}" var="sourceUrl">
																		<option value="${sourceUrl.id}">${sourceUrl.url}</option>
																	</c:forEach>
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4" style="padding: 0">
                                <div class="row ttx-from-to-date">
                                    <div class="col-md-6">
                                        <div class="from-date-div">
                                            <form class="form-horizontal" role="form">
                                                <div class="form-group ttx-from-date">
                                                    <label for="from-date" class="control-label"><spring:message
												code="ttxvn.report.from" /></label>
                                                    <label for="from-date" class="control-label">
                                                        <span class="ttx-icon-calendar ttx-icon fromdate-span"></span>
                                                    </label>
                                                </div>
                                            </form>
                                            <span class="ttx-btn-drop-1"></span>
                                        </div>
                                        <div class="form-group background-from-date">
                                            <label class="sr-only" for="from-date">Start date</label>
                                            <input id="from-date" type="text" class="ttx-date-picker ttx-form-control form-control">
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="to-date-div">
                                            <form class="form-horizontal" role="form">
                                                <div class="form-group ttx-to-date">
                                                    <label for="to-date" class="control-label"><spring:message
												code="ttxvn.report.to" /></label>
                                                    <label for="to-date" class="control-label">
                                                        <span class="ttx-icon-calendar ttx-icon todate-span"></span>
                                                    </label>
                                                </div>
                                            </form>
                                            <span class="ttx-btn-drop-2"></span>
                                        </div>
                                        <div class="form-group background-to-date">
                                            <label class="sr-only" for="to-date">End date</label>
                                            <input id="to-date" type="text" class="ttx-date-picker ttx-form-control form-control">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="ttx-box">
                            <div class="button-report">
                                <a onclick="generateReport(1)" href="#" class="ttx-btn btn btn-danger"><spring:message
												code="ttxvn.report.report" /></a>
                            </div>
                        </div>

                        <table id="report-summary-results" class="ttx-table-management table table-bordered table-striped table-responsive table-hover">
                            <thead>
                                <tr>
                                    <th><spring:message
												code="ttxvn.report.keyword" /></th>
                                    <th><spring:message
												code="ttxvn.report.category" /></th>
                                    <th><spring:message
												code="ttxvn.report.start" /></th>
                                    <th><spring:message
												code="ttxvn.report.end" /></th>
                                    <th><spring:message
												code="ttxvn.report.source" /></th>
                                    <th class="text-center"><spring:message
												code="ttxvn.report.numappearance" /></th>
                                    <th class="text-center"><spring:message
												code="ttxvn.report.numnews" /></th>
                                </tr>
                            </thead>
                            <tbody id="report-summary-results-tbody">
                                                               
                            </tbody>
                        </table>

                        <div class="ttx-btn-excel">
                            <div id="report-summary-command" class="text-right">
                                <div class="ttx-action ttx-action-icon ttx-box">
                                    <a onclick="saveReportToExcel();" href="#" class="ttx-btn btn btn-info">
                                        <span class="txt-icon-xlsx ttx-icon"></span>
                                        <spring:message
												code="ttxvn.report.export" />
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="links-paging" class="text-right">
                        <ul class="pagination pagination-sm pagination-lg"></ul>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>

        <jsp:include page="/WEB-INF/views/includes/footer.jsp"/>
    </div>
    
    
    <div id="message-popup" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="messageModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="ttx-model-content modal-content">
                <form action="#" method="post" role="form">
                    <div class="ttx-modal-header modal-header">
                        <button type="button" class="ttx-close close" data-dismiss="modal"><span class="sr-only">Close</span></button>
                        <h4 class="modal-title text-center text-uppercase" id="messageModalLabel"><spring:message code="homepage.message.title"/></h4>
                    </div>
                    <div class="modal-body">
                        <div id="ttx-message-body" class="ttx-message">
                        </div>
                    </div>
                    <div class="ttx-modal-footer modal-footer">
                        <div class="text-center ttx-yes-no">
                            <button id="no-message-btn" type="button" class="ttx-btn btn btn-info text-uppercase" data-dismiss="modal"><spring:message code="homepage.message.delete.cancel"/></button>
                            <button id="yes-message-btn" type="submit" class="ttx-btn btn btn-danger text-uppercase"><spring:message code="homepage.message.delete.apply"/></button>
                        </div>
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
	
	<script type="text/JavaScript">
    	var userId = 1;
    	var category = null;
    	$(document).ready(function () {    		 		    
    		
        });
    	  	    	
		function generateReport(page) {
			var category = jQuery(jQuery("select#ttx-subject")).find('option:selected').val();
			var keyword = jQuery(jQuery("select#ttx-kye")).find('option:selected').val();			
			var sourceUrl = jQuery(jQuery("select#ttx-news")).find('option:selected').val();
					
			var startDate = $('#from-date').val();
			var endDate = $('#to-date').val();	
			
			var d1 = startDate.split('-');
			d1 = new Date(d1.pop(), d1.pop() - 1, d1.pop());
			var d2 = endDate.split('-');
			d2 = new Date(d2.pop(), d2.pop() - 1, d2.pop());				
					
			if ((startDate!=null && startDate!="") && (endDate!=null && endDate != "") && (d1 > d2)) {						
				ttxModal.showError({message: '<spring:message code="ttxvn.report.validatedate"/>'});
				$("tbody#report-summary-results-tbody").empty();
			} else { 
				var getPaperURL = '${pageContext.request.contextPath}' + '/report/summary/getSummaryReport?reportCriteria={"category":"'+ category + '","keyword":"' + keyword + '","sourceUrl":"' + sourceUrl + '","startDate":"' + startDate + '","endDate":"' + endDate + '","page":"' + page + '"}';			
				$.ajax({
					type : 'GET',
					url : getPaperURL,
					//data: JSON.stringify(params),
					dataType : 'json',
					complete : function(data, status) {
					},
					success : function(data, status) {
						$("tbody#report-summary-results-tbody").empty();
						$("div#links-paging ul").empty();
						if (data.totalPages > 0) {
							var pageContent = '';
							$.each(									
								data.content,function(index, item) {																	
									pageContent += '<tr>'
											+ '<td>' + item.keyWord + '</td>'
											+ '<td>' + item.category + '</td>'										
											+ '<td>' + item.startDate + '</td>'
											+ '<td>' + item.endDate + '</td>'
											+ '<td>' + item.url + '</td>'
											+ '<td class="text-center">' + item.numOfAppearance + '</td>'
											+ '<td class="text-center">' + item.numOfNews + '</td>'
											'</tr>';
								});
							$("tbody#report-summary-results-tbody").append(pageContent);
	
							var pagging = '';
							if (data.totalPages > 0) {
								if (data.firstPage) {
									pagging += '<li class="disabled"><span>«</span></li>';
								} else {
									pagging += '<li><a href="#" onclick="generateReport(1)">«</a></li>';
								}
								$.each(
									data.pageItems,
									function(index, item) {
										pagging += '<li'
												+ (item.selected ? ' class="active"' : '')
												+ '><a href="#" onclick="generateReport(' + item.pageNumber + ')">'
												+ item.pageNumber
												+ (item.selected ? '<span class="sr-only">(current)</span>' : '')
												+ '</a></li>';
									});
								if (data.lastPage) {
									pagging += '<li class="disabled"><span>»</span></li>';
								} else {
									pagging += '<li><a href="#" onclick="generateReport('+ data.totalPages + ')">»</a></li>';
								}
							}
							$("#links-paging ul.pagination").append(pagging);
						}
					},
					error : function(xhr, status, error) {
						alert('An error occurred! '
								+ (error ? error : xhr.status));
					}
				});
			} 
		}
		
		function saveReportToExcel() {
    		var isSearchKeyword = jQuery("div#is-search-keyword-holder").text();
    		var linkQuery = $("input:text[name=link-query-input]").val();
    		var keyword = jQuery("nav#topics-result").find('a.active').text();
    		var page = jQuery('a', jQuery("div#links-paging ul.pagination").find('li.active')).text().charAt(0);
    		var form = jQuery('<form />', {action: '${pageContext.request.contextPath}' + '/report/export', method: 'POST'});
    		if ((page == null) || (page == '')) {
    			page = '0';
    		}
            jQuery('<input />', {type: 'hidden', name: 'keyword', value: keyword}).appendTo(form);
            jQuery('<input />', {type: 'hidden', name: 'url', value: linkQuery}).appendTo(form);
            jQuery('<input />', {type: 'hidden', name: 'searchKeyword', value: isSearchKeyword}).appendTo(form);
            jQuery('<input />', {type: 'hidden', name: 'page', value: page}).appendTo(form);
            jQuery.each(jQuery("#links-result input:checked"), function(index, item){	
            	jQuery('<input />', {type: 'hidden', name: 'selectNewsIds', value: jQuery(item).val()}).appendTo(form);
    		});
            return form.appendTo('body').submit().remove();
    	}
    	
    	
		
	</script>
	
</body>
</html>



