<%@include file="../../include/common.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<c:set var="_pageTitle" value="应用列表管理" scope="request"/>
<c:set var="_underAppPack" value="active" scope="request"/>
<c:set var="_activeApp" value="active" scope="request"/>
<c:set var="_module" value="app" scope="request"/>
<c:import url="../../theme/${_theme}/header.jsp"></c:import>
<script type="text/javascript" src="${basePath}static/theme/${_theme}/global.js"></script>
<!-- main content -->
		<div class="page-header"><h1>套餐</h1></div>
		<c:import url="../../theme/default/name_search.jsp">
            <c:param name="action" value="${basePath}app/app_pack/list"/>
        </c:import>
        <br/><br/>
		<div id="list">
			<table class="table table-bordered table-striped">
				<c:choose>
					<c:when test="${not hasDatas}">
						<tr>
							<td>没有应用记录!</td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<th></th>
							<th>编号</th>
							<th>名称</th>
							<th>类别</th>
							<th>操作</th>
						</tr>
						<c:forEach var="data" items="${datas}" varStatus="it">
							<tr>
								<td class="checkbox_td">
									<input type="checkbox" name="ids" value="${data.id}"/>
								</td>
								<td>${fn:escapeXml(data.id)}</td>
								<td>${fn:escapeXml(data.name)}</td>
								<td>${data.type==1?"搜索引擎":(data.type==2?"有图APP":"无图APP")}</td>
								<td class="operation operand2">
									<a class="btn btn-small btn-info" onclick="javascript:showDetail(${it.count},this);return false;"><i class="icon-plus-sign icon-white"></i>详情</a>
								</td>
							</tr>
							<tr id="detail_${it.count }" style="display: none">
								<td></td>
								<td colspan="4">
									<ul>
										<li><strong>app：</strong>
										<c:forEach var="appId" items="${data.appIdsObject}" varStatus="vs">
											<span><c:if test="${not vs.first}">,</c:if>${appMap[appId].name}</span>
										</c:forEach></li>
                                        <li><strong>创建时间：</strong><fmt:formatDate value="${data.gmtCreate}" pattern="yyyy/MM/dd HH:mm:ss" var="gmtCreate"/>${gmtCreate}</li>
                                        <li><strong>更新时间：</strong><fmt:formatDate value="${data.gmtModified}" pattern="yyyy/MM/dd HH:mm:ss" var="gmtModified"/>${gmtModified}</li>
										<li><strong>备注：</strong>${fn:escapeXml(data.remark )}</li>
									</ul>
								</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>
		</div>
		<div class="row-fluid">
			<div class="span4 toolbar">
				<c:import url="../../theme/${_theme}/toolbar.jsp">
					<c:param name="create">${basePath}app/app_pack/create</c:param>
					<c:param name="delete">${basePath}app/app_pack/delete</c:param>
					<c:param name="modify">${basePath}app/app_pack/modify</c:param>
				</c:import>
			</div>
			<div class="span8 paginator">
				<c:import url="../../theme/${_theme}/paginator.jsp"></c:import>
			</div>
		</div>
<!-- end main content -->
<c:import url="../../theme/${_theme}/footer.jsp"></c:import>