<%@include file="../../include/common.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<c:set var="_pageTitle" value="APP管理" scope="request"/>
<c:set var="_underAppPack" value="active" scope="request"/>
<c:set var="_activeApp" value="active" scope="request"/>
<c:set var="_module" value="app" scope="request"/>
<c:import url="../../theme/${_theme}/header.jsp"></c:import>
<!-- main content -->
		<div class="page-header"><h1>APP上传</h1></div>
		<div id="pageContent">
			<c:import url="../../theme/${_theme}/errors.jsp"></c:import>
			<form action="${basePath}app/app_pack/save" method="post" class="form-horizontal" enctype="multipart/form-data">
				<input name="id" type="hidden" value="${form.id}">
				<input name="_queryString" type="hidden" value="${param.queryString}">
                <div class="control-group required-field">
                    <label class="control-label">名称:</label>
                    <div class="controls">
                        <input name="name" value="${fn:escapeXml(form.name)}" type="text" class="input-large">
                    </div>
                </div>
				<div class="control-group required-field">
					<label class="control-label">类型:</label>
					<div class="controls">
						<select id="type_sel" name="type" class="input-small" onchange="javascript:changeType();">
							<option value="1">搜索引擎</option>
							<option value="2">有图App</option>
							<option value="3">无图App</option>
						</select>
					</div>
				</div>
				<script type="text/javascript">
					document.getElementById("type_sel").value='${searcher:defVal(form.type,1)}';
				</script>
				<div id="_cg_apps" class="control-group required-field">
					<label class="control-label">app:</label>
					<div class="controls">
						<div class="control-search-bar">
							<input id="_app_kw" name="_app_kw" class="input-medium">
							<input class="btn" type="button" onclick="javascript:searchSelected('_app_kw','_apps');" value="搜索">
							<input class="btn" type="button" onclick="javascript:showSelected('_apps');" value="选择项">
							<input type="checkbox" onchange="javascript:toggleAllSelected('_apps', this);">全选/全不选
						</div>
						<div id="_apps1">
							<c:forEach var="app" items="${apps1}">
								<span class="selLabel"><input ${searcher:contains(form.appIdsObject, app.id)?"checked=\"checked\"":""} type="checkbox" name="appIds" value="${app.id}"><span>${app.name}</span></span>
							</c:forEach>
						</div>
						<div id="_apps2">
							<c:forEach var="app" items="${apps2}">
								<span class="selLabel"><input ${searcher:contains(form.appIdsObject, app.id)?"checked=\"checked\"":""} type="checkbox" name="appIds" value="${app.id}"><span>${app.name}</span></span>
							</c:forEach>
						</div>
						<div id="_apps3">
							<c:forEach var="app" items="${apps3}">
								<span class="selLabel"><input ${searcher:contains(form.appIdsObject, app.id)?"checked=\"checked\"":""} type="checkbox" name="appIds" value="${app.id}"><span>${app.name}</span></span>
							</c:forEach>
						</div>
					</div>
				</div>
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
<script src="${basePath}static/jquery/jquery-ui.js"></script>
<script>
	function changeType(){
		var type = $("#type_sel").val();
		if(type==1){
			document.getElementById('_apps1').style.display = "block";
			document.getElementById('_apps2').style.display = "none";
			document.getElementById('_apps3').style.display = "none";
		}else if(type==2){
			document.getElementById('_apps1').style.display = "none";
			document.getElementById('_apps2').style.display = "block";
			document.getElementById('_apps3').style.display = "none";
		}else{
			document.getElementById('_apps1').style.display = "none";
			document.getElementById('_apps2').style.display = "none";
			document.getElementById('_apps3').style.display = "block";
		}
		showSelected("_apps");
	}
	function searchSelected(kwElemId, resultDivId){
		var type = $("#type_sel").val();
		var kw = $("#"+kwElemId).val();
		$("#"+resultDivId+type+" .selLabel").each(function(){
			if(($("span",this).html()+"").indexOf(kw)==-1){
				$(this).hide();
			}else{
				$(this).show();
			}
		});
	}
	function showSelected(resultDivId){
		var type = $("#type_sel").val();
		$("#"+resultDivId+type+" .selLabel").each(function(){
			if($("input[type=checkbox]",this)[0].checked){
				$(this).show();
			}else{
				$(this).hide();
			}
		});
	}
	function toggleAllSelected(resultDivId, elem){
		var type = $("#type_sel").val();
		$("#"+resultDivId+type+" .selLabel").each(function(){
			if($(this).is(":visible")){
				$($("input[type=checkbox]",this)[0]).attr("checked",elem.checked);
			}
		});
	}
	showSelected("_apps");
	changeType();
</script>