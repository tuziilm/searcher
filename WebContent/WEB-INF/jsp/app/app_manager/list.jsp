<%@include file="../../include/common.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<c:set var="_pageTitle" value="应用列表管理" scope="request"/>
<c:set var="_underAppManager" value="active" scope="request"/>
<c:set var="_activeApp" value="active" scope="request"/>
<c:set var="_module" value="app" scope="request"/>
<c:import url="../../theme/${_theme}/header.jsp"></c:import>
<script type="text/javascript" src="${basePath}static/theme/${_theme}/global.js"></script>
<!-- main content -->
<div class="page-header"><h1>应用</h1></div>
<c:import url="../../theme/default/name_search.jsp">
  <c:param name="action" value="${basePath}app/app_manager/list"/>
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
          <th>链接</th>
          <th>类型</th>
        </tr>
        <c:forEach var="data" items="${datas}" varStatus="it">
          <tr>
            <td class="checkbox_td">
              <input type="checkbox" name="ids" value="${data.id}"/>
            </td>
            <td>${fn:escapeXml(data.id)}</td>
            <td>${fn:escapeXml(data.name)}</td>
            <td>
              <a href="${fn:escapeXml(data.link)}" target="_blank">链接</a>|
              <c:choose>
                <c:when test="${not empty data.imgPath}">
                  <a href="${basePath}public/file/${fn:escapeXml(data.imgPath)}" target="_blank">图片</a>
                </c:when>
                <c:otherwise>
                  无图
                </c:otherwise>
              </c:choose>
            </td>
            <td>${data.type=='1'?"搜索引擎":(data.type=='2'?"有图APP":"无图APP")}</td>
          </tr>
        </c:forEach>
      </c:otherwise>
    </c:choose>
  </table>
</div>
<div class="row-fluid">
  <div class="span4 toolbar">
    <c:import url="../../theme/${_theme}/toolbar.jsp">
      <c:param name="create">${basePath}app/app_manager/create</c:param>
      <c:param name="delete">${basePath}app/app_manager/delete</c:param>
      <c:param name="modify">${basePath}app/app_manager/modify</c:param>
    </c:import>
  </div>
  <div class="span8 paginator">
    <c:import url="../../theme/${_theme}/paginator.jsp"></c:import>
  </div>
</div>
<!-- end main content -->
<c:import url="../../theme/${_theme}/footer.jsp"></c:import>
