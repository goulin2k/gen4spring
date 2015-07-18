<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="/jsp/flatty/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<script type="text/javascript">
		function delrows() {
			
			var chenked=$("input[type='checkbox']:checked").val([]); 
			if(chenked.length == 0) {
				alert("请选择要删除的数据行！");
				return;
			}
			var names = ""; 
			for(var i=0;i<chenked.length;i++){ 
				if(i==0) {
					names += chenked[i].value
				}else {
					names += "," + chenked[i].value; 
				}
			} 
			
			$("#${model.variableModel}Ids").attr("value",names);
			$("#removeForm").submit();
			
		}
	</script>
</head>
<body>

	<!--  Header Menu -->
	<form:form class='form' style='margin-bottom: 0;' accept-charset="UTF-8" id="removeForm" name="removeForm"
			action="${model.variableModel}/remove.do" method="post">
		
		<div class='page-header'>						
			<h1 class='pull-left'>
				<i class='icon-user'></i> <span>${model.variableModel} 信息管理</span>
			</h1>
			<div class='pull-right'>
				<ul class='breadcrumb'>
					<input type="hidden" id="${model.variableModel}Ids" name="${model.variableModel}Ids" value="" />
					<li><a href="dashboard.do"><i class='icon-bar-chart'></i> </a></li>
					<li class='separator'><i class='icon-angle-right'></i></li>
					<li><a href="${model.variableModel}/view.do"><i class='icon-plus-sign'></i>&nbsp新增</a></li>
					<li class='separator'><i class='icon-angle-right'></i></li>
					<li><a onclick="delrows();"><i class='icon-remove-sign'></i>&nbsp删除</a></li>
					<li class='separator'><i class='icon-angle-right'></i></li>
					<li class='active'>${model.variableModel} 信息列表</li>
				</ul>
			</div>
		</div>
	</form:form>
	
	<form:form class='form' style='margin-bottom: 0;' accept-charset="UTF-8" id="searchForm" name="searchForm"
			action="${model.variableModel}/list.do"	modelAttribute="${model.variableModel}" method="post">
		<input type="hidden" id="pageCount" name="pageCount" value="${r"${page.pageCount}"}" />
		<input type="hidden" id="pageNumber" name="pageNumber" value="${r"${page.pageNo}"}" />
		<input type="hidden" id="totalCount" name="totalCount" value="${r"${page.total}"}" />
		<!-- 查询条件栏 -->
	 	<div class="span12 querybar panel panel-info ">
	 		<div class="form-group">
	 			<div class="col-sm-6">	
					<input id='${model.key}' name='${model.key}' 
	                	value='${r"${"}${model.variableModel}.${model.key}}'  placeholder='查询' />
	                <button class='btn btn-warning' type='submit' onclick="$('#pageNumber').val(1);$('#totalCount').val('');">查询</button>
			    </div>
		    </div>
		</div>
		
		<div class='span12 box bordered-box blue-border' style='margin-bottom:0;'>
	        <div class='box-header blue-background'>
	            <div class='title'>${model.variableModel} 信息列表</div>
	            <div class='actions'></div>
	        </div>
			<!-- Data Table -->
			<div class='box-content box-no-padding'>
				<div class='responsive-table'>
					<div class='scrollable-area'>
						<table class="table table-bordered table-hover table-striped "
							style="margin-bottom:0;">
							<thead>
								<#list model.fieldList as field>
								<#if field.isListInTable>
								<th>${field.displayName}</th>
								</#if>
								</#list>
							</thead>
		
							<tbody>
								<c:forEach items="${r"${page.rows}"}" var="${model.variableModel}">
									<tr>
										<#list model.fieldList as field>
										<#if field.isListInTable>
											<#if field.isEditLink>
											<td class="table-font">
											<a href="${model.variableModel}/view.do?${model.key}=${r"${"}${model.variableModel}.${model.key}}">${r"${"}${model.variableModel}.${field.property}}</a></td>
											</#if>
											<#if field.isKey>
											<td><input type="checkbox" name="checkedIds" id="checkedIds"
												value="${r"${"}${model.variableModel}.${field.property}}">
											</td>
											<#elseif field.dataType="Date">
											<td class="table-font"><fmt:formatDate value="${r"${"}${model.variableModel}.${field.property}}" type="both" /></td>
											<#else>
											<td class="table-font">${r"${"}${model.variableModel}.${field.property}}</td>
											</#if>
										</#if>
										</#list>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				
				<!--  Page show -->
				<div class="ui-pagelist">
					<div style="float: right;" id="pager"></div>
				</div>
			</div>
		</div>
	</form:form>
</body>
</html>
