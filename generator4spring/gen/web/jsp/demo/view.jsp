<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<script type="text/javascript">
		function submitForm() {
			//if($("#demoForm").form('validate')) {
				$("#demoForm").submit();
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
					<li><a href="demo/list.do"><i class='icon-list-ul'></i>&nbsp点位列表</a></li>
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
		        
		        <c:if test="${errors != null}" >
			        <div class='alert alert-error'>
						<a class='close' data-dismiss='alert' href='#'>&times;</a>
							<strong>错误信息：</strong>${errors}<i class='icon-adjust'></i>
					</div>
				</c:if>
		        <div class='box-content box-double-padding'>
		            <form:form class='form validate-form' style='margin-bottom: 0;' accept-charset="UTF-8" id="demoForm" name="demoForm"
						modelAttribute="demo" action="demo/save.do"	method="post">
		                <fieldset>
		                    <div class='span4'>
		                        <div class='lead'>
		                            <i class='icon-github text-contrast'></i>
		                            点位基本信息
		                        </div>
		                        <small class='muted'>前端点位基本信息编辑和维护.</small>
		                    </div>
		                    <div class='span7 offset1'>
		                    	<input type="hidden" id='demo.id' name='demo.id' value='${demo.id}'/>
		                        <div class='control-group'>
		                            <label class='control-label'>名称</label>
		                            <div class='controls'>
		                                <input class='span12' id='name' name='name' 
		                                	value="${demo.name}"
		                                	data-rule-required='true' placeholder='名称' type='text' />
		                                
		                            </div>
		                        </div>
		                        <div class='control-group'>
		                            <label class='control-label'>描述</label>
		                            <div class='controls'>
		                                <input class='span12' id='description' name='description' 
		                                	value="${demo.description}"
		                                	data-rule-required='true' placeholder='描述' type='text' />
		                                
		                            </div>
		                        </div>
		                        <div class='control-group'>
		                            <label class='control-label'>添加日期</label>
		                            <div class='controls'>
		                                <input class='span12' id='addTime' name='addTime' 
		                                	value="${demo.addTime}"
		                                	data-rule-required='true' placeholder='添加日期' type='text' />
		                                
		                            </div>
		                        </div>
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