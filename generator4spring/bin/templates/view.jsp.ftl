<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<script type="text/javascript">
		function submitForm() {
			//if($("#${model.variableModel}Form").form('validate')) {
				$("#${model.variableModel}Form").submit();
			//}
		}
	</script>
</head>
<body>
		<div class='page-header'>						
			<h1 class='pull-left'>
				<i class='icon-user'></i> <span>点位信息编辑</span>
			</h1>
			<div class='pull-right'>
				<ul class='breadcrumb'>
					<li><a href="dashboard.do"><i class='icon-bar-chart'></i> </a></li>
					<li class='separator'><i class='icon-angle-right'></i></li>
					<li><a href="${model.variableModel}/list.do"><i class='icon-list-ul'></i>&nbsp点位列表</a></li>
					<li class='separator'><i class='icon-angle-right'></i></li>
					<li class='active'>点位信息</li>
				</ul>
			</div>
		</div>
	
		<div class='row-fluid'>
		    <div class='span12 box bordered-box blue-border'>
		        <div class='box-header blue-background'>
		            <div class='title'>
		                <i class='icon-ban-circle'></i>
		                点位信息
		            </div>
		            <div class='actions'>
		                <a href="#" class="btn box-remove btn-mini btn-link"><i class='icon-remove'></i>
		                </a>
		                <a href="#" class="btn box-collapse btn-mini btn-link"><i></i>
		                </a>
		            </div>
		        </div>
		        
		        <c:if test="${r"${"}errors != null}" >
			        <div class='alert alert-error'>
						<a class='close' data-dismiss='alert' href='#'>&times;</a>
							<strong>错误信息：</strong>${r"${"}errors}<i class='icon-adjust'></i>
					</div>
				</c:if>
		        <div class='box-content box-double-padding'>
		            <form:form class='form validate-form' style='margin-bottom: 0;' accept-charset="UTF-8" id="${model.variableModel}Form" name="${model.variableModel}Form"
						modelAttribute="${model.variableModel}" action="${model.variableModel}/save.do"	method="post">
		                <fieldset>
		                    <div class='span4'>
		                        <div class='lead'>
		                            <i class='icon-github text-contrast'></i>
		                            点位基本信息
		                        </div>
		                        <small class='muted'>前端点位基本信息编辑和维护.</small>
		                    </div>
		                    <div class='span7 offset1'>
		                    	<input type="hidden" id='${model.variableModel}.id' name='${model.variableModel}.id' value='${r"${"}${model.variableModel}.id}'/>
		                    	<#list model.fieldList as field>
								<#if field.isEditable>
		                        <div class='control-group'>
		                            <label class='control-label'>${field.displayName}</label>
		                            <div class='controls'>
		                                <input class='span12' id='${field.property}' name='${field.property}' 
		                                	value="${r"${"}${model.variableModel}.${field.property}}"
		                                	data-rule-required='true' placeholder='${field.displayName}' type='text' />
		                                
		                            </div>
		                        </div>
		                        </#if>
		                        </#list>
		                </fieldset>
		                <div class='form-actions' style='margin-bottom: 0;'>
		                    <div class='text-right'>
		                        <a class='btn btn-primary' type='submit' onclick='submitForm();'>
		                            <i class='icon-save'></i>
		                            保存
		                        </a>
		                    </div>
		                </div>
		            </form:form>
		        </div>
		    </div>
		</div>
</body>

</html>