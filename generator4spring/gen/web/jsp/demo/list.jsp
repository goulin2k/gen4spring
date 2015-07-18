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
			
			$("#demoIds").attr("value",names);
			$("#removeForm").submit();
			
		}
	</script>
</head>
<body>

	<!--  Header Menu -->
	<form:form class='form' style='margin-bottom: 0;' accept-charset="UTF-8" id="removeForm" name="removeForm"
			action="demo/remove.do" method="post">
		
		<div class='page-header'>						
			<h1 class='pull-left'>
				<i class='icon-user'></i> <span>demo 信息管理</span>
			</h1>
			<div class='pull-right'>
				<ul class='breadcrumb'>
					<input type="hidden" id="demoIds" name="demoIds" value="" />
					<li><a href="dashboard.do"><i class='icon-bar-chart'></i> </a></li>
					<li class='separator'><i class='icon-angle-right'></i></li>
					<li><a href="demo/view.do"><i class='icon-plus-sign'></i>&nbsp新增</a></li>
					<li class='separator'><i class='icon-angle-right'></i></li>
					<li><a onclick="delrows();"><i class='icon-remove-sign'></i>&nbsp删除</a></li>
					<li class='separator'><i class='icon-angle-right'></i></li>
					<li class='active'>demo 信息列表</li>
				</ul>
			</div>
		</div>
	</form:form>
	
	<form:form class='form' style='margin-bottom: 0;' accept-charset="UTF-8" id="searchForm" name="searchForm"
			action="demo/list.do"	modelAttribute="demo" method="post">
		<input type="hidden" id="pageCount" name="pageCount" value="${page.pageCount}" />
		<input type="hidden" id="pageNumber" name="pageNumber" value="${page.pageNo}" />
		<input type="hidden" id="totalCount" name="totalCount" value="${page.total}" />
		<!-- 查询条件栏 -->
	 	<div class="span12 querybar panel panel-info ">
	 		<div class="form-group">
	 			<div class="col-sm-6">	
					<input id='id' name='id' 
	                	value='${demo.id}'  placeholder='查询' />
	                <button class='btn btn-warning' type='submit' onclick="$('#pageNumber').val(1);$('#totalCount').val('');">查询</button>
			    </div>
		    </div>
		</div>
		
		<div class='span12 box bordered-box blue-border' style='margin-bottom:0;'>
	        <div class='box-header blue-background'>
	            <div class='title'>demo 信息列表</div>
	            <div class='actions'></div>
	        </div>
			<!-- Data Table -->
			<div class='box-content box-no-padding'>
				<div class='responsive-table'>
					<div class='scrollable-area'>
						<table class="table table-bordered table-hover table-striped "
							style="margin-bottom:0;">
							<thead>
								<th></th>
								<th>名称</th>
								<th>描述</th>
								<th>添加日期</th>
							</thead>
		
							<tbody>
								<c:forEach items="${page.rows}" var="demo">
									<tr>
											<td><input type="checkbox" name="checkedIds" id="checkedIds"
												value="${demo.id}">
											</td>
											<td class="table-font">
											<a href="demo/view.do?id=${demo.id}">${demo.name}</a></td>
											<td class="table-font">${demo.name}</td>
											<td class="table-font">${demo.description}</td>
											<td class="table-font"><fmt:formatDate value="${demo.addTime}" type="both" /></td>
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
