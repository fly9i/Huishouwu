<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="header.jsp"%>
<script type="text/javascript" src="js/sale_active_control.js"></script>
<script type="text/javascript">
	var tab = "sale_old";
</script>
</head>
<%@ include file="body_header.jsp"%>
<div class="sale_container span12" style="height: 1000px;">

	<div id="sale_list" class="tabbable tabs-left" style="height: 1000px;">
		<ul class="nav nav-tabs sale-nav">
		<c:forEach var="type" items="${typeconfig}" varStatus="status">
		
		
			<li class="sale-nav-inner${status.first?' active':''}"><a href="#" fordiv="${type.getName()}"> <img
					style="height: 20px;" src="img/${type.getName()}.jpg" />&nbsp;&nbsp;${type.getDes()}
				<c:if test="${cart.get(type.getName())!=null && cart.get(type.getName())!=0}">
					<span class="badge badge-success count_${type.getName()}">${cart.get(type.getName())}</span>
				</c:if>
			</a></li>
			</c:forEach>
			<!-- 
			<li><a href="#" fordiv="xyj"> <img style="height: 20px;"
					src="img/xyj.jpg" />&nbsp;&nbsp;洗衣机
			</a></li>
			<li><a href="#" fordiv="dn"> <img style="height: 20px;"
					src="img/dn.jpg" />&nbsp;&nbsp;电脑
			</a></li>
			<li><a href="#" fordiv="dsj"> <img style="height: 20px;"
					src="img/ds.jpg" />&nbsp;&nbsp;电视机
			</a></li>
			 -->
		</ul>
		<div class="tab-content">
			<ul>
			<c:forEach var="type" items="${typeconfig}" varStatus="status">
			
				<li id="${type.getName()}" class="${status.first?'content_show':'' }">
					<form id="form_${type.getName()}" class="well form-horizontal">
						<fieldset>
							<legend>${type.getDes() }</legend>
						</fieldset>
						<div class="form_container"></div>
					</form>
				</li>
				 
				</c:forEach>
				
			</ul>
		</div>
	</div>

</div>
<%@ include file="body_footer.jsp"%>
</html>
