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
                                                <a href="${pageContext.request.contextPath}/manage/sourceUrl">
                                                    <span class="ttx-icon"></span>
                                                    <spring:message code='ttxvn.keywordsearching.systemConfig.mainmenu.sourceManagement'/>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="${pageContext.request.contextPath}/manage/fetchFrequency"  class="active">
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
                        </div> 

                        <div class="nav-menu-right-panel" title="Click to collapse left pane"></div>
                    </div>

                    <div class="right-panel" style="margin-left: 225px;">
                        <div class="row">
                            <div class="col-md-12">
                                <!-- Implement Your HTML code -->
                                <div class="ttx-command">
                                    <p class="ttx-title-text text-uppercase"><b><spring:message code='ttxvn.keywordsearching.systemConfig.frequency.title'/></b></p>
                                </div>
                                <div class="col-sm-9 col-md-6 col-xs-9 col-sm-offset-3">
                                    <div class="frequency-edit">
                                        <form action="#" role="form" class="form-horizontal">
                                            <table class="table table-striped table-hover">
                                                <colgroup>
                                                    <col width="150" />
                                                    <col width="70" />
                                                    <col width="150" />
                                                </colgroup>
                                                <tbody></tbody>
                                                <tfoot>
                                                    <tr>
                                                        <td colspan="3" class="text-center">
                                                            <div class="ttx-multi-btn">
                                                                <button type="button" class="ttx-btn btn btn-info" onclick="loadFrequency()"><spring:message code="ttxvn.keywordsearching.systemConfig.frequency.cancel"/></a>
                                                                <button type="submit" class="ttx-btn btn btn-danger"><spring:message code="ttxvn.keywordsearching.systemConfig.frequency.save"/></a>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                </tfoot>
                                            </table>
                                        </form>
                                    </div>
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

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<c:url value="/resources/scripts/jquery-1.11.1.min.js"/>"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<c:url value="/resources/bootstrap-3.2.0/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/resources/bootstrap-3.2.0/js/bootstrap-datepicker.js"/>"></script>
    <script src="<c:url value="/resources/bootstrap-3.2.0/js/bootstrap-datepicker.vi.js"/>" charset="UTF-8"></script>
    <script src="<c:url value="/resources/scripts/ttxvn.js"/>"></script>
    <script src="<c:url value="/resources/scripts/notification.js"/>"></script>
    
    <script type="text/javascript">
    	jQuery(document).ready(function(){
    		loadFrequency();
    	});
    	
    	function loadFrequency(){
    		jQuery.ajax({
    			cache: false,
    			type: 'get',
    			url: '<%=request.getContextPath()%>/manage/fetchFrequency/getAll',
    			dataType: 'json',
    			success: function(response){
    				var number = 1;
    				var content = '';
    				jQuery.each(response, function(key, value){
    					
                    	content += '<tr><td class=\'text-right\'><label><spring:message code='ttxvn.keywordsearching.systemConfig.frequency.type'/> '
                    				+number+'</label></td><td class=\'text-center\'><input type=\'hidden\' value='
                    				+key+'><input type="text" class="ttx-form-control form-control" id="frequency-'+number+'" value='+value+'></td><td class="text-left"><spring:message code='ttxvn.keywordsearching.systemConfig.label.minute'/></td></tr>';
                    	number++;
    				});
    				jQuery(".frequency-edit table tbody").html(content);
    			}
    		});
    	}
    	
    	jQuery('.form-horizontal').on('submit', function(){
    		var ids = [];
    		var frequencies = [];
    		jQuery(".frequency-edit table tbody tr").each(function(){
    			ids.push(jQuery(this).children('td').eq(1).find('input[type=\'hidden\']').val());
    			frequencies.push(jQuery(this).children('td').eq(1).find('input[type=\'text\']').val());
    			
    		});
    		//alert(frequencies);
    		jQuery.ajax({
    			type: 'post',
    			url: '<%=request.getContextPath()%>/manage/fetchFrequency/update',
    			dataType: 'json',
    			data: {
    				ids: ids,
    				frequencies: frequencies
    			},
    			success: function(response){
    				if(response.message !=''){
    					ttxModal.showError({message: response.message});
    				}
    			}
    		});
    		return false;
    	});
    </script>
    
</body>
</html>
