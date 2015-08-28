<%@include file="../../include/common.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<c:set var="_pageTitle" value="App管理" scope="request"/>
<c:set var="_underAppManager" value="active" scope="request"/>
<c:set var="_activeApp" value="active" scope="request"/>
<c:set var="_module" value="app" scope="request"/>
<c:import url="../../theme/${_theme}/header.jsp"></c:import>
<!-- main content -->
<div class="page-header"><h1>App上传</h1></div>
<div id="pageContent">
  <c:import url="../../theme/${_theme}/errors.jsp"></c:import>
  <form action="${basePath}app/app_manager/save" method="post" class="form-horizontal" enctype="multipart/form-data" onsubmit="return onSubmitApp(this);">
    <input name="id" type="hidden" value="${form.id}">
    <input name="_queryString" type="hidden" value="${param.queryString}">
    <div class="control-group required-field">
      <label class="control-label">名称:</label>
      <div class="controls">
        <input name="name" value="${fn:escapeXml(form.name)}" type="text" class="input-large">
      </div>
    </div>
    <div id="_cg_file" class="control-group required-field">
      <label class="control-label">图片路径:</label>
      <div class="controls">
        <input name="imgFile" type="file"/>
      </div>
    </div>
    <div class="control-group required-field">
      <label class="control-label">链接地址:</label>
      <div class="controls">
        <input name="link" value="${fn:escapeXml(form.link)}" type="text" class="input-large">
      </div>
    </div>
    <div class="control-group required-field">
      <label class="control-label">类型:</label>
      <div class="controls">
        <select id="type_sel" name="type" class="input-small">
          <option value="1">搜索引擎</option>
          <option value="2">有图App</option>
          <option value="3">无图App</option>
        </select>
      </div>
    </div>
    <script type="text/javascript">
      document.getElementById("type_sel").value='${searcher:defVal(form.type,1)}';
    </script>
    <div class="control-group required-field">
      <label class="control-label">备注:</label>
      <div class="controls">
        <input name="remark" value="${fn:escapeXml(form.remark)}" type="text" class="input-large">
      </div>
    </div>
    <div class="form-actions">
      <input class="btn btn-primary" type="submit" value="保存">
      <button type="button" class="btn" onclick="javascript:history.go(-1)">取消</button>
    </div>
  </form>
</div>
<!-- end main content -->
<c:import url="../../theme/${_theme}/footer.jsp"></c:import>