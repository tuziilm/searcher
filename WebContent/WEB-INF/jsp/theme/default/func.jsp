<%@ include file="../../include/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:choose>
	<c:when test="${_module=='system' }">
		<c:if test="${searcher:isAdmin()}">
			<li class="${_underSysUser}"><a href="${basePath}sysuser/list">系统用户</a></li>
		</c:if>
		<li class="${_underUserInfo}"><a href="${basePath}sysuser/${isUnderUserInfo?'info_modify':'modify'}/${searcher:uid()}">信息修改</a></li>
	</c:when>
	<c:when test="${_module=='app' }">
		<li class="${_underAppManager}"><a href="${basePath}app/app_manager/list">App列表管理</a></li>
		<li class="${_underAppPack}"><a href="${basePath}app/app_pack/list">分类管理</a></li>
	</c:when>
</c:choose>
