<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/tags.jsp"%>

<jsp:include page="./includes/header.jsp"/>

        <div id="main-panel">
            <div id="main-box">
                <div class="container-fluid">
                    <div id="main-menu-box" class="left-panel">
                        <div id="nav-menu-topics-panel">
                            <nav class="menu-directive">
                                <ul>
                                    <li>
                                        <a href="${pageContext.request.contextPath}/home" title="<spring:message code="homepage.mainMenu.homepage"/>" class="active">
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
                            
                            
           
            <div class="panel-group-custom panel-group" id="accordion">
               <div class="panel panel-default">
                   <div class="panel-heading">
                            <div class="topic-add">
                                <form action="#" method="post" class="ttx-form-group" role="form">
                                    <div class="input-group">
                                        <span class="input-group-addon ttx-text-group"><spring:message code="homepage.category.title"/></span>
                                        <div class="input-group-btn">
                                        <c:if test="${showCreateCategory}">
                                            <button type="button" class="btn ttx-btn-add" data-toggle="modal" data-target="#add-topic-popup" onclick="refreshAddForm()"></button>
                                        </c:if>
                                        <a class="btn ttx-btn-collapse" data-toggle="collapse" data-parent="#accordion" href="#list-of-topics">
                                                            <span class="glyphicon glyphicon-play"></span>
                                         </a>
                                        </div>
                                    </div>
                                </form>
                            </div>
                    </div>
                    <div id="list-of-topics" class="panel-collapse collapse in">
                        <div class="panel-body">
                            <nav class="menu-topics">
                                <ul id="category">
                                    <li>
                                        <a href="#" title="<spring:message code="homepage.category.all"/>" class="active" onclick="loadKeyword(null, '')">
                                            <span class="glyphicon glyphicon-stop"></span>
                                            <spring:message code="homepage.category.all"/>
                                        </a>
                                    </li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                 </div>

			 <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <div class="menu-sources-header">
                                            <form action="#" method="post" class="ttx-form-group" role="form">
                                                <div class="input-group">
                                                    <span class="input-group-addon ttx-text-group" style="height: 35px;" ><spring:message code="homepage.mainMenu.sourceUrl"/></span>
                                                    <div class="input-group-btn">
                                                        <a class="btn ttx-btn-collapse" data-toggle="collapse" data-parent="#accordion" href="#list-of-sources" style="height: 35px">
                                                            <span class="glyphicon glyphicon-play"></span>
                                                        </a>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                             <div id="list-of-sources" class="panel-collapse collapse">
                                 <div class="panel-body">
                                     <nav class="menu-sources">
                                         <ul id="source">
											 <li style="display: none">
                                        		<a href="#" title="#" class="active"> 
                                        		</a>
                                    		</li>
                                         </ul>
                                     </nav>
                                 </div>
                             </div>
                         </div>
                     </div>
		</div>
                        <div class="nav-menu-right-panel" title="Click to collapse left pane"></div>
                    </div>

                    <div class="right-panel" style="margin-left: 222px;">
                        <div class="row">
                            <div id="keywords-box" class="col-xs-12 col-sm-4 col-md-3 col-lg-2-custom">
                                <div id="topics-search-keywords">
                                    <form action="#" class="ttx-form-group" role="form">
                                        <div class="input-group">
                                            <label for="keywords-query-input" class="input-group-addon ttx-text-group" style="width: 104px; height: 35px;"><spring:message code="homepage.keyword.title"/></label>
                                            <span class="ttx-search-group">
                                                <input id="keywords-query-input" type="text" class="form-control" placeholder="<spring:message code="homepage.search.hint"/>" style="height: 35px;"/>
                                                <button type="submit" class="btn ttx-btn-search"></button>
                                            </span>
                                            <div class="input-group-btn">
                                                <button type="button" id="btn-add-keyword" class="btn ttx-btn-add ttx-add-red" data-toggle="modal" data-target="#add-keywords-popup" onclick="refreshAddForm()"></button>
                                            </div>
                                        </div>
                                    </form>
                                </div>

                                <nav id="topics-result">
                                    <ul>
                                        <li class="follow">
                                            <a href="#" title="<spring:message code="homepage.keyword.follow"/>">
                                                <span class="glyphicon glyphicon-play"></span>
                                                <spring:message code="homepage.keyword.follow"/>
                                            </a>
                                            <ol>
                                            </ol>
                                        </li>
                                        <li class="pause-follow">
                                            <a href="#" title="<spring:message code="homepage.keyword.pauseFollow"/>">
                                                <span class="glyphicon glyphicon-play"></span>
                                                <spring:message code="homepage.keyword.pauseFollow"/>
                                            </a>
                                            <ol>
                                            </ol>
                                        </li>
                                        <li class="no-follow">
                                            <a href="#" title="<spring:message code="homepage.keyword.endFollow"/>">
                                                <span class="glyphicon glyphicon-play"></span>
                                                <spring:message code="homepage.keyword.endFollow"/>
                                            </a>
                                            <ol>
                                            </ol>
                                        </li>
                                    </ul>
                                </nav>
                            </div>

                            <div id="links-search-box" class="col-xs-12 col-sm-4 col-md-6 col-lg-6-custom">
                                <div id="links-search-keyword">
                                    <form action="#" class="ttx-form-group" role="form">
                                        <div class="input-group">
                                            <label for="link-query-input" class="input-group-addon ttx-text-group" style="width: 100px; height: 35px;"><spring:message code="homepage.link.title"/></label>
                                            <span class="ttx-search-group">
                                                <input id="link-query-input" name="link-query-input" type="text" class="form-control" style="height: 35px; padding-right: 30px;" placeholder="<spring:message code="homepage.search.hint"/>"  />
                                                <button type="submit" class="btn ttx-btn-search"></button>
                                            </span>
                                            <div class="input-group-addon ttx-group-controls">
                                                <ul>
                                                    <li>
                                                        <a id="print-news-link" href="#" title="Print" onclick="printNewsToPDF();">
                                                            <img src="<c:url value="/resources/images/print.png"/>" alt="Print" style="width: 20px;"/>
                                                        </a>
                                                    </li>
                                                    <li>
                                                        <a href="#" title="Save" onclick="saveNewsToFDF();">
                                                            <img src="<c:url value="/resources/images/floppy.png"/>" alt="Save" style="width: 17px;"/>
                                                        </a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div id="search-wrapper">
	                                <div id="links-result">
	                                </div>
	                                
	                                <input id="keyword-click" type="hidden" class="ttx-form-control form-control" />
	                                <input id="source-click" type="hidden" class="ttx-form-control form-control" />
	                                
									<div id="is-search-keyword-holder" style="display: none;"></div>
									<div id="is-search-titleonly-holder" style="display: none;"></div>
									<div id="is-search-keyword-by-user-holder" style="display:none;"></div>
									<div id="is-search-source-by-url-holder" style="display:none;"></div>
	                                <div id="links-result-printable" style="display: none;" ></div>
	                                <div id="links-paging" class="text-right">
	                                    <ul class="pagination pagination-sm pagination-lg">
	                                    </ul>
	                                </div>
	                            </div>
							</div>
							
                            <div id="news-box" class="col-xs-12 col-sm-4 col-md-3 col-lg-4-custom">
                                <div id="news-recent">
                                    <div class="news-header text-center">
                                        <h4 class="text-uppercase">
                                            <img src="<c:url value="/resources/images/news.png"/>" alt="News" />
                                            <spring:message code="homepage.freshnews.title"/>
                                        </h4>
                                    </div>
	                                    <div class="news-content" id="new-content" style="overflow: hidden; position: relative;height: 800px">
	                                        <ul>
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
		
		<jsp:include page="./includes/footer.jsp"/>
   
    <!-- Modal -->
    <!--Right click-->
    <div id="topic-context-menu" class="right-click" style="display: none;">
        <ul class="ttx-menu">
            <li>
                <a href="#" title="<spring:message code="homepage.category.menu.edit"/>" onclick="editCategory()"><spring:message code="homepage.category.menu.edit"/></a>
            </li>
            <li>
                <a href="#" title="<spring:message code="homepage.category.menu.delete"/>" onclick="deleteCategory()"><spring:message code="homepage.category.menu.delete"/></a>
            </li>
            <li>
                <span>&nbsp;</span>
            </li>
        </ul>
    </div>

    <div id="keywords-context-menu" class="right-click" style="display: none;">
        <ul class="ttx-menu">
            <li>
                <a id="keyword-edit" href="#" title="<spring:message code="homepage.keyword.menu.edit"/>" onclick="editKeyword()"><spring:message code="homepage.keyword.menu.edit"/></a>
            </li>
            <li>
                <a id="keyword-delete" href="#" title="<spring:message code="homepage.keyword.menu.delete"/>" onclick="deleteKeyword()"><spring:message code="homepage.keyword.menu.delete"/></a>
            </li>
            <li>
                <a id="keyword-stop" href="#" title="<spring:message code="homepage.keyword.menu.pauseFollow"/>" onclick="changeKeywordStatus()"><spring:message code="homepage.keyword.menu.pauseFollow"/></a>
            </li>
            <li>
                <a id="keyword-continue" href="#" title="<spring:message code="homepage.keyword.menu.follow"/>" onclick="changeKeywordStatus()"><spring:message code="homepage.keyword.menu.follow"/></a>
            </li>
            <li>
                <span>&nbsp;</span>
            </li>
        </ul>
    </div>
    <!--End Right click-->
    
    <!-- command show popup: $('#add-topic-popup').modal() -->
    <div id="add-topic-popup" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="addTopicModalLabel" aria-hidden="true" data-backdrop="static">
        <div class="modal-dialog">
            <div class="ttx-model-content modal-content">
                <form action="#" role="form">
                    <div class="ttx-modal-header modal-header">
                        <button type="button" class="ttx-close close" data-dismiss="modal"><span class="sr-only">Close</span></button>
                        <h4 class="modal-title text-center text-uppercase" id="addTopicModalLabel"><spring:message code="homepage.category.add.title"/></h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <input id="name-topic-add" type="text" class="ttx-form-control form-control" value=""/>
                            <input id="id-topic-add" type="hidden" class="ttx-form-control form-control" />
                        </div>
                    </div>
                    <div class="ttx-modal-footer modal-footer">
                        <div class="text-center">
                            <button type="button" class="ttx-btn btn btn-info text-uppercase" data-dismiss="modal"><spring:message code="homepage.category.add.cancel"/></button>
                            <button type="submit" class="ttx-btn btn btn-danger text-uppercase"><spring:message code="homepage.category.add.save"/></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- command show popup: $('#delete-topic-message-popup').modal() -->
    <div id="delete-topic-message-popup" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="deleteTopicMessageModalLabel" aria-hidden="true" data-backdrop="static">
        <div class="modal-dialog">
            <div class="ttx-model-content modal-content">
                <form action="#" role="form">
                    <div class="ttx-modal-header modal-header">
                        <button type="button" class="ttx-close close" data-dismiss="modal"><span class="sr-only">Close</span></button>
                        <h4 class="modal-title text-center text-uppercase" id="deleteTopicMessageModalLabel"><spring:message code="homepage.category.delete.title"/></h4>
                    </div>
                    <div class="modal-body">
                        <p><b><spring:message code="homepage.category.delete.message"/></b></p>
                    </div>
                    <div class="ttx-modal-footer modal-footer">
                        <div class="text-center">
                            <button type="button" class="ttx-btn btn btn-info text-uppercase" data-dismiss="modal"><spring:message code="homepage.category.delete.cancel"/></button>
                            <button type="submit" class="ttx-btn btn btn-danger text-uppercase"><spring:message code="homepage.category.delete.apply"/></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- command show popup: $('#add-keywords-popup').modal() -->
    <div id="add-keywords-popup" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="addKeywordsModalLabel" aria-hidden="true" data-backdrop="static">
        <div class="modal-dialog">
            <div class="ttx-model-content modal-content">
                <form action="<%=request.getContextPath()%>/home/ajax/createKeyword" method="post" role="form" class="form-horizontal">
                    <div class="ttx-modal-header modal-header">
                        <button type="button" class="ttx-close close" data-dismiss="modal"><span class="sr-only">Close</span></button>
                        <h4 class="modal-title text-center text-uppercase" id="addKeywordsModalLabel"><spring:message code="homepage.keyword.add.title"/></h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="name-keyword-add" class="control-label col-sm-5"><spring:message code="homepage.keyword.add.name.label"/></label>
                            <div class="col-sm-7">
                                <input id="name-keyword-add" type="text" class="ttx-form-control form-control" value=""/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="start-date-keyword-add" class="control-label col-sm-5"><spring:message code="homepage.keyword.add.start.label"/></label>
                            <div class="col-sm-7">
                                <div class="form-inline">
                                    <div class="form-group">
                                        <label for="start-date-keyword-add" class="control-label">
                                            <span class="ttx-icon-calendar ttx-icon"></span>
                                        </label>
                                    </div>
                                    <div class="form-group">
                                        <label class="sr-only" for="start-date-keyword-add">Start date</label>
                                        <input id="start-date-keyword-add" type="text" class="ttx-date-picker ttx-form-control form-control"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="end-date-keyword-add" class="control-label col-sm-5"><spring:message code="homepage.keyword.add.end.label"/></label>
                            <div class="col-sm-7">
                                <div class="form-inline">
                                    <div class="form-group">
                                        <label for="end-date-keyword-add" class="control-label">
                                            <span class="ttx-icon-calendar ttx-icon"></span>
                                        </label>
                                    </div>
                                    <div class="form-group">
                                        <label class="sr-only" for="end-date-keyword-add">End date</label>
                                        <input id="end-date-keyword-add" type="text" class="ttx-date-picker ttx-form-control form-control"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="topic-keyword-add" class="control-label col-sm-5"><spring:message code="homepage.keyword.add.category.label"/></label>
                            <div class="col-sm-7">
                                <select id="topic-keyword-add" class="ttx-form-control form-control">
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="ttx-modal-footer modal-footer">
                        <div class="text-center">
                            <button type="button" class="ttx-btn btn btn-info text-uppercase" data-dismiss="modal"><spring:message code="homepage.keyword.add.cancel"/></button>
                            <button type="button" class="ttx-btn btn btn-danger text-uppercase" id="save-keyword" onclick="createKeyword()"><spring:message code="homepage.keyword.add.save"/></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    
     <!-- command show popup: $('#edit-keywords-popup').modal() -->
    <div id="edit-keywords-popup" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="addKeywordsModalLabel" aria-hidden="true" data-backdrop="static">
        <div class="modal-dialog">
            <div class="ttx-model-content modal-content">
                <form action="<%=request.getContextPath()%>/home/ajax/createKeyword" method="post" role="form" class="form-horizontal">
                    <div class="ttx-modal-header modal-header">
                        <button type="button" class="ttx-close close" data-dismiss="modal"><span class="sr-only">Close</span></button>
                        <h4 class="modal-title text-center text-uppercase" id="addKeywordsModalLabel"><spring:message code="homepage.keyword.add.title"/></h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="name-keyword-edit" class="control-label col-sm-5"><spring:message code="homepage.keyword.add.name.label"/></label>
                            <div class="col-sm-7">
                                <input id="name-keyword-edit" type="text" class="ttx-form-control form-control" value=""/>
                                <input id="id-keyword-edit" type="hidden" class="ttx-form-control form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="start-date-keyword-edit" class="control-label col-sm-5"><spring:message code="homepage.keyword.add.start.label"/></label>
                            <div class="col-sm-7">
                                <div class="form-inline">
                                    <div class="form-group">
                                        <label for="start-date-keyword-edit" class="control-label">
                                            <span class="ttx-icon-calendar ttx-icon"></span>
                                        </label>
                                    </div>
                                    <div class="form-group">
                                        <label class="sr-only" for="start-date-keyword-edit">Start date</label>
                                        <input id="start-date-keyword-edit" type="text" class="ttx-date-picker ttx-form-control form-control"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="end-date-keyword-edit" class="control-label col-sm-5"><spring:message code="homepage.keyword.add.end.label"/></label>
                            <div class="col-sm-7">
                                <div class="form-inline">
                                    <div class="form-group">
                                        <label for="end-date-keyword-edit" class="control-label">
                                            <span class="ttx-icon-calendar ttx-icon"></span>
                                        </label>
                                    </div>
                                    <div class="form-group">
                                        <label class="sr-only" for="end-date-keyword-edit">End date</label>
                                        <input id="end-date-keyword-edit" type="text" class="ttx-date-picker ttx-form-control form-control"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="topic-keyword-edit" class="control-label col-sm-5"><spring:message code="homepage.keyword.add.category.label"/></label>
                            <div class="col-sm-7">
                                <select id="topic-keyword-edit" class="ttx-form-control form-control">
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="ttx-modal-footer modal-footer">
                        <div class="text-center">
                            <button type="button" class="ttx-btn btn btn-info text-uppercase" data-dismiss="modal"><spring:message code="homepage.keyword.add.cancel"/></button>
                            <button type="button" class="ttx-btn btn btn-danger text-uppercase" onclick="updateKeyword()"><spring:message code="homepage.keyword.add.save"/></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- command show popup: $('#delete-keywords-message-popup').modal() -->
    <div id="delete-keywords-message-popup" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="deleteKeywordsMessageModalLabel" aria-hidden="true" data-backdrop="static">
        <div class="modal-dialog">
            <div class="ttx-model-content modal-content">
                <form action="#" role="form">
                    <div class="ttx-modal-header modal-header">
                        <button type="button" class="ttx-close close" data-dismiss="modal"><span class="sr-only">Close</span></button>
                        <h4 class="modal-title text-center text-uppercase" id="deleteKeywordsMessageModalLabel"><spring:message code="homepage.keyword.delete.title"/></h4>
                    </div>
                    <div class="modal-body">
                        <p><b><spring:message code="homepage.keyword.delete.message"/></b></p>
                    </div>
                    <div class="ttx-modal-footer modal-footer">
                        <div class="text-center">
                            <button type="button" class="ttx-btn btn btn-info text-uppercase" data-dismiss="modal"><spring:message code="homepage.keyword.delete.cancel"/></button>
                            <button type="submit" class="ttx-btn btn btn-danger text-uppercase"><spring:message code="homepage.keyword.delete.apply"/></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div id="search-keyword-error-popup" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="messageModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="ttx-model-content modal-content">
                <form action="#" method="post" role="form">
                    <div class="ttx-modal-header modal-header">
                        <button type="button" class="ttx-close close" data-dismiss="modal"><span class="sr-only">Close</span></button>
                        <h4 class="modal-title text-center text-uppercase" id="messageModalLabel"><spring:message code="homepage.message.title"/></h4>
                    </div>
                    <div class="modal-body">
                        <div id="search-keyword-error-popup-body" class="ttx-message">
                         <p><b><spring:message code="homepage.search.keyword.error"/></b></p>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
     <div id="print-error-popup" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="messageModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="ttx-model-content modal-content">
                <form action="#" method="post" role="form">
                    <div class="ttx-modal-header modal-header">
                        <button type="button" class="ttx-close close" data-dismiss="modal"><span class="sr-only">Close</span></button>
                        <h4 class="modal-title text-center text-uppercase" id="messageModalLabel"><spring:message code="homepage.message.title"/></h4>
                    </div>
                    <div class="modal-body">
                        <div id="print-error-popup-body" class="ttx-message">
                         <p><b><spring:message code="homepage.print.save.PDF.error"/></b></p>
                        </div>
                    </div>
                </form>
            </div>
        </div>
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
    <script src="<c:url value="/resources/scripts/jQuery.print.js"/>"></script>
    <script src="<c:url value="/resources/scripts/jquery.vticker.js"/>"></script>
     <script src="<c:url value="/resources/scripts/spin.js"/>"></script>
    <script type="text/JavaScript">
    	var userId = 1;
    	var category = null;
    	var keywordClick = null;
    	var sourceClick = null;
    	$(document).ready(function () {
    		doSearch(1, '', '', false, false,true, false);
    		loadCategory();
    		loadSourceUrl();
    		loadFreshNews();
    		setInterval(function(){loadFreshNews();}, 30000); // find Fresh News in every 30 minutes
        });
    	
    	$("#topics-search-keywords .ttx-form-group").on('submit',(function(){
    		loadKeyword(category, $("#keywords-query-input").val());
			return false;
		}));
    	
    	function loadKeyword(categoryId, name){
    		jQuery("input[name=link-query-input]").val('');
    		category = categoryId;
    		jQuery.ajax({
    			cache: false,
    			type: 'get',
    			url:'<%=request.getContextPath()%>/keyword/loadKeyword',
    			data: {
    				keyword: name,
    				user: userId,
    				category: categoryId
    			},
    			dataType:'json',
    			success: function(response){
    				jQuery(".follow ol").html("");
    				jQuery(".pause-follow ol").html("");
    				jQuery(".no-follow ol").html("");
    				jQuery.each(response.follow, function(key, value) {
     					jQuery(".follow ol").append("<li value='"+key+"'><a href='#' title='"+value+"' onclick=\"searchNewspaper('" +value+ "')\">"+value+"</a></li>");					
    				});
    				
    				jQuery.each(response.pause, function(key, value) {
    					jQuery(".pause-follow ol").append("<li value='"+key+"'><a href='#' title='"+value+"' onclick=\"searchNewspaper('" +value+ "')\">"+value+"</a></li>");
    				});
    				jQuery.each(response.stop, function(key, value) {
    					jQuery(".no-follow ol").append("<li value='"+key+"'><a href='#' title='"+value+"' onclick=\"searchNewspaper('" +value+ "')\">"+value+"</a></li>");
    				});
    				jQuery('#topics-result ol li a').click(function () {
    				    jQuery(this).parent().siblings().find('a.active').removeClass('active');
    				    jQuery(this).addClass('active');
    				    //console.log('Bạn vừa click vào menu #topics-result');
    				    return false;
    				});
    				
    				
    				
    				jQuery('#topics-result ol li a').bind('contextmenu', function (e) {
    				    hideAllContext();
    				    //console.log("Bạn vừa chọn: " + jQuery(this).text().trim());
    				    jQuery('#keywords-context-menu ul li a').removeClass('disabled');
    				    var parent = jQuery(this).parent().parent();
    				    if(parent.closest('li').attr('class')=='follow'){
    				    	jQuery('#keyword-continue').addClass('disabled');			    	
    				    }
    				    else if(parent.closest('li').attr('class')=='pause-follow'){
    				    	jQuery('#keyword-stop').addClass('disabled');	    	
    				    }
    				    else{
    				    	jQuery('#keyword-continue').addClass('disabled');
    				    	jQuery('#keyword-stop').addClass('disabled');
    				    }
    				    jQuery('#keywords-context-menu').css({ 'top': e.pageY + 'px', 'left': e.pageX + 'px' }).show();
    				    var id = jQuery(this).parent().attr('value');
    				    jQuery('#keywords-context-menu').data('keywordId', id);
    				    jQuery('#keywords-context-menu').data('name',jQuery(this).attr('title'));
    				    e.preventDefault();
    				    return false;
    				});
    				$(window).resize();
    			}
    		});
    	}
    	
    	function loadCategory(){
    		loadKeyword(category,"");
    		jQuery.ajax({
    			cache: false,
    			type: 'get',
    			url:'<%=request.getContextPath()%>/category/loadCategory',
    			data:{
    				user: userId
    			},
    			dataType:'json',
    			success: function(response){
    				jQuery(".menu-topics ul li:first-child").siblings().remove();
    				jQuery("#topic-keyword-add").find('option').remove();
    				jQuery("#topic-keyword-edit").find('option').remove();
    				var count = 0;
    				jQuery.each(response, function(key, value) {
    					count += 1;
    					jQuery("#topic-keyword-add").append("<option value='" + key + "'>" + value + "</option>");
    					jQuery("#topic-keyword-edit").append("<option value='" + key + "'>" + value + "</option>");
        				jQuery(".menu-topics ul").append("<li value='" + key + "'><a href='#' onclick=\"loadKeyword(" + key + ", '')\" title='" + value + "'><span class='glyphicon glyphicon-stop'></span>" + value + "</a></li>");	
    	            });
    				
    				
    				
    				if(count<=20){
						jQuery(".topic-add button").removeClass('disable');
    				}
					else{
							jQuery(".topic-add button").addClass('disable');
						}
    				
    				if('${showCreateCategory}' == 'true') {
	    				jQuery(".menu-topics ul").find('a:visible:not(:first)').bind('contextmenu', function (e) {
	    				    hideAllContext();
	    				    // console.log("Bạn vừa chọn: " + jQuery(this).text().trim());
	    				    jQuery('#topic-context-menu').css({ 'top': e.pageY + 'px', 'left': e.pageX + 'px' }).show();
	    				    var id = jQuery(this).parent().attr('value');
	    				    jQuery('#topic-context-menu').data('categoryId', id);
	    				    jQuery('#topic-context-menu').data('name',jQuery(this).attr('title'));
	    				    e.preventDefault();
	    				    return false;
	    				});
    				}
    				
    				jQuery(".menu-topics ul").find('a:visible').click(function () {
    				    jQuery(this).parent().siblings().find('a.active').removeClass('active');
    				    jQuery(this).addClass('active');
    				   // console.log('Bạn vừa click vào menu .menu-topics');

    				    return false;
    				});
    				
    				$(window).resize();
    			},
    			error: function(response){
    				console.log('Error while request..'+response.status+' '+response.statusText);
    			}
    		});
    	}
    	
    	// ------------------- LOAD SOURCE URL -----------------------
    	function loadSourceUrl(){
    		jQuery.ajax({
    			cache: false,
    			type: 'get',
    			url:'<%=request.getContextPath()%>/source-url/loadSourceUrl',
    			data:{
    				user: userId
    			},
    			dataType:'json',
    			success: function(response){
    				var count = 0;
    				jQuery.each(response, function(key, value) {
    					count += 1;
        				jQuery(".menu-sources ul").append("<li value='" + key + "'><a href='#' onclick=\"searchSourceUrl('"+ value +"')\" title='" + value + "'>" + value + "</a></li>");	
    	            });

    				jQuery('.menu-sources ul li a').click(function () {
    				    jQuery(this).parent().siblings().find('a.active').removeClass('active');
    				    jQuery(this).addClass('active');

    				});
    				
    				$(window).resize();
    			},
    			error: function(response){
    				console.log('Error while request..'+response.status+' '+response.statusText);
    			}
    		});
    	}
    	
    	function searchKeyword(){
    		loadKeyword(category, $("#keywords-query-input").val());
    	}
    	
    	function createKeyword(){
    		var keyword = $("#name-keyword-add").val();
    		var start = $("#start-date-keyword-add").val();
    		var end = $("#end-date-keyword-add").val();
    		var categoryId = $("#topic-keyword-add").val();
    		jQuery.ajax({
    			type:'post',
    			url: '<%=request.getContextPath()%>/keyword/createKeyword',
    			data: {
    				keyword: keyword,
    				start: start,
    				end: end,
    				category: categoryId,
    				user: userId,
    			},
    			dataType: 'json',
    			success: function(response){
    				if(response.message!='')
    					ttxModal.showError({message: response.message});
    				else{
    					jQuery('#add-keywords-popup').modal('hide');
    					loadKeyword(category, '');
    					
    				}
    				
    			},
	    		error: function(response){
					console.log('Error while request..'+response.status+' '+response.statusText);
				}
    		});
    	}
    	function updateKeyword(){
    		var keyword = $("#name-keyword-edit").val();
    		var keywordId = $("#id-keyword-edit").val();
    		var start = $("#start-date-keyword-edit").val();
    		var end = $("#end-date-keyword-edit").val();
    		var categoryId = $("#topic-keyword-edit").val();
    		jQuery.ajax({
    			type:'post',
    			url: '<%=request.getContextPath()%>/keyword/updateKeyword',
    			data: {
    				keyword: keyword,
    				start: start,
    				end: end,
    				category: categoryId,
    				user: userId,
    				keywordId: keywordId
    			},
    			dataType: 'json',
    			success: function(response){
    				if(response.message!='')
    					ttxModal.showError({message: response.message});
    				else{
    					jQuery('#edit-keywords-popup').modal('hide');
    					loadKeyword(category, '');	
    				}
    				
    			},
	    		error: function(response){
					console.log('Error while request..'+response.status+' '+response.statusText);
				}
    		});
    	}
    	
    	
    	
    	function deleteKeyword(){
    		jQuery("#delete-keywords-message-popup .modal-body").html("");
    		jQuery("#delete-keywords-message-popup .modal-body").append("<p><b><spring:message code='homepage.keyword.delete.message'/> "+jQuery("#keywords-context-menu").data("name")+"?</b></p>");
    		jQuery('#delete-keywords-message-popup').modal();
    	}
    	
    	jQuery("#delete-keywords-message-popup form").submit(function(){
    		var id = jQuery('#keywords-context-menu').data('keywordId');
    		jQuery.ajax({
    			type:'post',
    			url: '<%=request.getContextPath()%>/keyword/deleteKeyword',
    			data: "keyword="+id,
    			dataType:'json',
    			async: false,
	    		error: function(response){
	    			console.log('Error while request..'+response.status+' '+response.statusText);
				},
				complete:function(){
					jQuery('#delete-keywords-message-popup').modal('hide');
					loadKeyword(category, '');
				}
    		});
    	});
    	
    	function changeKeywordStatus(){
    		var id = jQuery('#keywords-context-menu').data('keywordId');
    		jQuery.ajax({
    			type:'post',
    			url: '<%=request.getContextPath()%>/keyword/keywordStatus',
    			data: "keyword="+id,
    			dataType:'json',
	    		error: function(response){
	    			console.log('Error while request..'+response.status+' '+response.statusText);
				},
				complete:function(){
					loadKeyword(category, '');
				}
    		});
    	}
    	
    	function editKeyword(){
    		jQuery("#addKeywordsModalLabel").text("<spring:message code='homepage.keyword.edit.title'/>");
    		var id = jQuery('#keywords-context-menu').data('keywordId');
    		jQuery.ajax({
    			type:'get',
    			url: '<%=request.getContextPath()%>/keyword/loadKeywordDetail',
    			data: "keyword="+id,
    			dataType:'json',
    			success: function(response){
    				jQuery("#name-keyword-edit").val(response.keyword);
    				jQuery("#start-date-keyword-edit").val(response.start);
    				jQuery("#end-date-keyword-edit").val(response.end);
    				jQuery("#topic-keyword-edit").val(response.category);
    				jQuery("#id-keyword-edit").val(response.keywordId);
    	    		jQuery('#edit-keywords-popup').modal();
    			},
	    		error: function(response){
	    			console.log('Error while request..'+response.status+' '+response.statusText);
				},
    		});
    	}
    	
    	jQuery("#add-topic-popup form").on('submit', function (){
    		var topicId = jQuery("#id-topic-add").val();
    		var topic = jQuery("#name-topic-add").val();
    		jQuery.ajax({
    			type:'post',
    			url: '<%=request.getContextPath()%>/category/createCategory',
    			data:{
    				category: topic,
    				categoryId: topicId,
    				user: userId
    			},
    			dataType:'json',
    			success:function(response){
    				if(response.message!='')
    					ttxModal.showError({message: response.message});
    				else{
    					jQuery('#add-topic-popup').modal('hide');
    					loadCategory();
    					
    				}
    			}
    		});
    		return false;
    	});
    	
    	function deleteCategory(){
    		jQuery("#delete-topic-message-popup .modal-body").html("");
    		jQuery("#delete-topic-message-popup .modal-body").append("<p><b><spring:message code='homepage.category.delete.message'/> "+jQuery("#topic-context-menu").data("name")+"?</b></p>");
    		jQuery('#delete-topic-message-popup').modal();
    	}
    	
    	jQuery("#delete-topic-message-popup form").submit(function(){
    		var id = jQuery('#topic-context-menu').data('categoryId');
    		jQuery.ajax({
    			type:'post',
    			url: '<%=request.getContextPath()%>/category/deleteCategory',
    			data: "categoryId="+id,
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
					jQuery('#delete-topic-message-popup').modal('hide');
					loadCategory();
				}
    		});
    		return false;
    	});
    	
    	function editCategory(){
    		jQuery("#addTopicModalLabel").text("<spring:message code='homepage.category.edit.title'/>");
    		var id = jQuery('#topic-context-menu').data('categoryId');
    		jQuery.ajax({
    			type:'get',
    			url: '<%=request.getContextPath()%>/category/updateCategory',
    			data: "categoryId="+id,
    			dataType:'json',
    			success: function(response){
    				jQuery("#id-topic-add").val(response.categoryId);
    				jQuery("#name-topic-add").val(response.category);
    				jQuery('#add-topic-popup').modal();
    			},
	    		error: function(response){
	    			console.log('Error while request..'+response.status+' '+response.statusText);
				},
    		});
    	}
    	
    	function refreshAddForm()
    	{
    		jQuery("#id-topic-add").val('');
			jQuery("#name-topic-add").val('');
			jQuery("#name-keyword-add").val('');
			jQuery("#end-date-keyword-add").val('');
			jQuery("#id-keyword-add").val('');
			jQuery("#addTopicModalLabel").text("<spring:message code='homepage.category.add.title'/>");
			jQuery("#addKeywordsModalLabel").text("<spring:message code='homepage.keyword.add.title'/>");
			jQuery("#start-date-keyword-add").datepicker("setDate", new Date());
    	}
    	
    	function searchNewspaper(keyword) {
    		jQuery("#keyword-click").val(keyword);
    		sourceClick = jQuery("#source-click").val();
    		jQuery("nav#topics-result").find('a.active').removeClass('active');
		    jQuery(this).addClass('active');
		    doSearch(1, keyword, sourceClick, true,false,false,true);		    
    	}
    	
    	// -------------SEARCH URL------------
    	function searchSourceUrl(url) {
    		jQuery("input[name=link-query-input]").val('');
    		jQuery("#source-click").val(url);
    		keywordClick = jQuery("#keyword-click").val();
    		jQuery(this).parent().siblings().find('a.active').removeClass('active');
		    jQuery(this).addClass('active');
		    doSearch(1, keywordClick, url,true,false,false,true);
    	}
    	//-------------------------------------
    	
    	jQuery("#links-search-keyword form").on('submit', function(){
    		var linkQuery = $("input:text[name=link-query-input]").val();
    		searchNewspaperByTitle(linkQuery);
    		return false; 
    	});
    	
    	function searchNewspaperByTitle(keyword) {
    		sourceClick = jQuery("#source-click").val();
    		keywordClick = jQuery("#keyword-click").val();
    		doSearch(1, keyword, sourceClick, true,true,false,true);
    	}
    	
    	function doSearch(page, keyword, url, isSearchKeyword, isSearchTitleOnly, isFindAllKeywordByUser, isSearchSourceUrl) {
    		if (isSearchKeyword) { // keep 'searching action' for print function
    			jQuery("div#is-search-keyword-holder").html('true');
    		} else {
    			jQuery("div#is-search-keyword-holder").html('false');
    		}
    		
    		if(isSearchTitleOnly) {
    			jQuery("div#is-search-titleonly-holder").html('true');
    		} else {
    			jQuery("div#is-search-titleonly-holder").html('false');
    		}
    			
    		if(isFindAllKeywordByUser) {
    			jQuery("div#is-search-keyword-by-user-holder").html('true');
    		} else{
    			jQuery("div#is-search-keyword-by-user-holder").html('false');
    		}
    		
    		if(isSearchSourceUrl) {
    			jQuery("div#is-search-source-by-url-holder").html('true');
    		} else{
    			jQuery("div#is-search-source-by-url-holder").html('false');
    		}
    		
    		if (!isSearchTitleOnly) {
    			jQuery("input[name=link-query-input]").val('');
    		}
    		
    		var opts = {
    				 lines: 11, // The number of lines to draw
    				  length: 5, // The length of each line
    				  width: 3, // The line thickness
    				  radius: 6, // The radius of the inner circle
    				  corners: 1, // Corner roundness (0..1)
    				  rotate: 0, // The rotation offset
    				  direction: 1, // 1: clockwise, -1: counterclockwise
    				  color: '#000', // #rgb or #rrggbb or array of colors
    				  speed: 1, // Rounds per second
    				  trail: 60, // Afterglow percentage
    				  shadow: false, // Whether to render a shadow
    				  hwaccel: false, // Whether to use hardware acceleration
    				  className: 'spinner', // The CSS class to assign to the spinner
    				  zIndex: 2e9, // The z-index (defaults to 2000000000)
    				  top: '50%', // Top position relative to parent
    				  left: '50%' // Left position relative to parent
    		        };
    		
    		var getPaperURL = '${pageContext.request.contextPath}' + '/search/findByKeywordUrl?searchCriteria={"keyword":"' + encodeURIComponent(keyword) + '","url":"' + url + '","page":"'+ page + '","searchKeyword":"'+ isSearchKeyword + '","searchInTitleOnly":"'+ isSearchTitleOnly + '","findByAllUserKeywordUnderMonitoring":"'+ isFindAllKeywordByUser + '","searchSourceUrl":"'+ isSearchSourceUrl + '"}';
    		//var getPaperURL = '${pageContext.request.contextPath}' + '/restful/search/findByKeywordUrl';
    		//var params = 'searchCriteria:{"keyword":"' + keyword + '","url":"' + linkQuery + '","page":"'+ page + '"}';
    		$.ajax({
    			cache: false,
				type: 'GET',
				url: getPaperURL,
				//data: JSON.stringify(params),
				dataType: 'json',
				beforeSend: function() {
			        // setting a timeout
			        $("div#links-result").empty();
			        $("div#links-paging ul").empty();
			        $("div#links-result").append("<div id='content-loading' style='margin-top:50px'><div>")
			        var target = document.getElementById('content-loading');
			        var spinner = new Spinner(opts).spin(target)
// 					$("div#links-result").addClass('loading')
			    },
				complete: function(data, status){
				
				},
				success: function(data, status){
					$("div#links-result").empty();
				    $("div#links-paging ul").empty();
					if (data.totalPages > 0) {
						var pageContent = '';
					    $.each(data.content, function(index, item){
					    	pageContent += '<ol>'
					        	+ '<li>'
					            	+ '<div class="link-item">'
					            		+ '<div class="item-checkbox">'
					                    	/* + '<input name="chkNews" type="checkbox" value="' +item.id+ '"/>' */
					                    	 + '<input name="chkNews" type="checkbox" value="' +index+ '"/>'
					                	+ '</div>'
						                + '<div class="item-content">'
						                    + '<h4 class="item-title">'
						                        + '<a href="' + item.url + '" target="_blank">' + item.abbreviateTitle + '</a>'
						                    + '</h4>'
						                    + '<a class="item-link" href="' + item.url + '" target="_blank">' + item.abbreviateUrl + '</a>'
						                    + '<p class="item-description">'
						                        + item.abbreviateContent
						                    + '</p>'
						                + '</div>'
						            + '</div>'
						        + '</li>'
						    + '</ol>';
					    });
					    
					    $("div#links-result").append(pageContent);
						var pagging = '';
						if (data.totalPages > 0) {
							if (data.firstPage) {
								pagging += '<li class="disabled"><span>«</span></li>';
							} else {
								pagging += '<li><a href="#" onclick="doSearch(1,\'' + keyword + '\',\''+url+'\','+isSearchKeyword +','+isSearchTitleOnly +','+isFindAllKeywordByUser+','+isSearchSourceUrl+')">«</a></li>';	
							}
							$.each(data.pageItems, function(index, item){
								pagging += '<li' + (item.selected ? ' class="active"' : '') + '><a href="#" onclick="doSearch('+ item.pageNumber + ',\'' + keyword + '\',\''+url+'\','+isSearchKeyword +','+isSearchTitleOnly +','+isFindAllKeywordByUser+','+isSearchSourceUrl+')">' + item.pageNumber + (item.selected ? '<span class="sr-only">(current)</span>' : '') + '</a></li>';
							});
							if (data.lastPage) {
								pagging += '<li class="disabled"><span>»</span></li>';
							} else {
								pagging += '<li><a href="#" onclick="doSearch(' + data.totalPages + ',\'' + keyword + '\',\''+url+'\', '+isSearchKeyword +','+isSearchTitleOnly +','+isFindAllKeywordByUser+','+isSearchSourceUrl+')">»</a></li>';	
							}
						}
						$("#links-paging ul.pagination").append(pagging);
					}
				},
				error: function(xhr, status, error) {
					$("div#links-result").empty();
				    $("div#links-paging ul").empty();
				    jQuery('#search-keyword-error-popup').modal();

				}
			});
    	}
    	
    	function printNewsToPDF() {
    		var isPrint=true;
    		var isSearchKeyword = jQuery("div#is-search-keyword-holder").text();
    		var isSearchTitleOnly = jQuery("div#is-search-titleonly-holder").text();
    		var isFindAllKeywordByUser=jQuery("div#is-search-keyword-by-user-holder").text();
    		var isSearchSourceUrl=jQuery("div#is-search-source-by-url-holder").text();
    		
    		$("div#links-result-printable").empty();
    		var linkQuery=jQuery("#source-click").val();
    		if(jQuery("div#is-search-titleonly-holder").text()=="true"){
    			keyword=jQuery("#link-query-input").val();
    			linkQuery="";
    		}
    		else{
    			var keyword = jQuery("nav#topics-result").find('a.active').text();
    		}
    		var page = parseInt(jQuery('a', jQuery("div#links-paging ul.pagination").find('li.active')).text());
    		var getPaperURL = '${pageContext.request.contextPath}' + '/search/findByKeywordUrl?searchCriteria={"keyword":"' + keyword + '","url":"' + linkQuery + '","page":"'+ page + '","searchKeyword":"' + isSearchKeyword + '","searchInTitleOnly":"' + isSearchTitleOnly + '","findByAllUserKeywordUnderMonitoring":"'+ isFindAllKeywordByUser + '","searchSourceUrl":"'+ isSearchSourceUrl + '"}'; 
    		$.ajax({
				type: 'GET',
				url: getPaperURL,
				dataType: 'json',
				async: false,
				complete: function(data, status){
				},
				success: function(data, status) {
					var checkedNewsId = [];
					jQuery.each(jQuery("#links-result input:checked"), function(index, item){
						checkedNewsId.push(parseInt(jQuery(item).val()));
						//checkedNewsId.push(index);
		    		});
					if(checkedNewsId.length> 0) {
						if (data.totalPages > 0) {
							var printContent = '';
						    $.each(data.content, function(index, item){
						    	//if (jQuery.inArray(item.id, checkedNewsId) != -1) {
						    	if (jQuery.inArray(index, checkedNewsId) != -1) {
							    	printContent += '<div class="link-item">'
						                + '<div class="item-content">'
						                    + '<h4 class="item-title">'
						                        + item.title
						                    + '</h4>'
						                    + item.url
						                    + '<p class="item-description">'
						                        + item.content
						                    + '</p>'
						                    + '</br></br>'
						                + '</div>'
						            + '</div>';
						    	}
						    });
						    $("div#links-result-printable").append(printContent);
						}
					}
					else{
						jQuery('#print-error-popup').modal();
						isPrint=false;
					}
				},
				error: function(xhr, status, error) {
					alert('An error occurred! ' + ( error ? error : xhr.status));
				}
			});
    		if(isPrint)
    		{
    			jQuery('#links-result-printable').print();
    		}
    			
    		return false;
    	}
    	
    	function saveNewsToFDF() {
    		var isSearchKeyword = jQuery("div#is-search-keyword-holder").text();
    		var isSearchTitleOnly = jQuery("div#is-search-titleonly-holder").text();
    		var isFindAllKeywordByUser=jQuery("div#is-search-keyword-by-user-holder").text();
    		var isSearchSourceUrl=jQuery("div#is-search-source-by-url-holder").text();
    		var page = parseInt(jQuery('a', jQuery("div#links-paging ul.pagination").find('li.active')).text());
    		var linkQuery=jQuery("#source-click").val();
    		if(jQuery("div#is-search-titleonly-holder").text()=="true"){
    			keyword=jQuery("#link-query-input").val();
    			linkQuery="";  				
    		}
    		else{
    			var keyword = jQuery("nav#topics-result").find('a.active').text();
    		}
    		
    		var form = jQuery('<form />', {action: '${pageContext.request.contextPath}' + '/restful/exportnews/generatePDF', method: 'POST'});
    		if ((page == null) || (page == '')) {
    			page = '0';	
    		}
    		
            jQuery('<input />', {type: 'hidden', name: 'keyword', value: keyword}).appendTo(form);
            jQuery('<input />', {type: 'hidden', name: 'url', value: linkQuery}).appendTo(form);
            jQuery('<input />', {type: 'hidden', name: 'searchKeyword', value: isSearchKeyword}).appendTo(form);
            jQuery('<input />', {type: 'hidden', name: 'searchInTitleOnly', value: isSearchTitleOnly}).appendTo(form);
            jQuery('<input />', {type: 'hidden', name: 'findByAllUserKeywordUnderMonitoring', value: isFindAllKeywordByUser}).appendTo(form);
            jQuery('<input />', {type: 'hidden', name: 'searchSourceUrl', value: isSearchSourceUrl}).appendTo(form);
            jQuery('<input />', {type: 'hidden', name: 'page', value: page}).appendTo(form);

            jQuery.each(jQuery("#links-result input:checked"), function(index, item){	
            	jQuery('<input />', {type: 'hidden', name: 'selectNewsIds', value: jQuery(item).val()}).appendTo(form);
    		});
            var checkedNewsId = [];
			jQuery.each(jQuery("#links-result input:checked"), function(index, item){
				checkedNewsId.push(jQuery(item).val());
    		});
			if(checkedNewsId.length>0){
            	return form.appendTo('body').submit().remove();
			}
			else
			{
				jQuery('#print-error-popup').modal();
				return false;
			}
    	}
    	
    	function loadFreshNews(){
    		
    		jQuery.ajax({
    			cache: false,
    			type: "get",
    			url: '<%=request.getContextPath()%>/news/getNews',
    			dataType:'json',
    			success: function(response){
    				jQuery(".news-content ul").html("");
    				jQuery.each(response, function(key, value){
    					var title= value;
//    					if (value != null && value.length > 30) {
//    						title = value.substring(0, 30) + "...";
//   					}
    					jQuery(".news-content ul").append("<li><a href='"+key+"' target='blank' title='"+value+"'>"+title+"</a></li>");
    				});
    			}
    		});
    	}
 
    	$(function(){
    		$("#new-content").vTicker({ 
    		speed: 800,
    		pause: 1500,
    		//animation: 'fade',
    		mousePause: true,
    		showItems: 5,
    		height: 800
    		});
    		});
    </script>
</body>
</html>