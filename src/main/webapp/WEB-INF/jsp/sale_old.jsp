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
		<ul class="nav nav-tabs">
			<li class="active"><a href="#" fordiv="bx"> <img
					style="height: 20px;" src="img/bx.jpg" />&nbsp;&nbsp;冰箱
			</a></li>
			<li><a href="#" fordiv="xyj"> <img style="height: 20px;"
					src="img/xyj.jpg" />&nbsp;&nbsp;洗衣机
			</a></li>
			<li><a href="#" fordiv="dn"> <img style="height: 20px;"
					src="img/xsq.jpg" />&nbsp;&nbsp;电脑
			</a></li>
			<li><a href="#" fordiv="dsj"> <img style="height: 20px;"
					src="img/ds.jpg" />&nbsp;&nbsp;电视机
			</a></li>
		</ul>
		<div class="tab-content">
			<ul>
				<li id="bx" class="content_show">
					<form id="form_bx" class="well form-horizontal">
						<fieldset>
							<legend>冰箱</legend>
							<div class="control-group">
								<label class="control-label" for="rl">容量</label>
								<div class="controls">
									<label class="radio inline">
										 <input type="radio" name="rl">10L
									</label>
									<label class="radio inline">
										 <input type="radio"  name="rl">20L
									</label>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="xs">形式</label>
								<div class="controls">
									<input type="radio"/>
								</div>
							</div>
						</fieldset>
					</form>
				</li>
				
				<li id="xyj">
					<form id="form_xyj" class="well form-horizontal">
						<fieldset>
							<legend>洗衣机</legend>
						</fieldset>
					</form></li>
				<li id="dn">
					<form id="form_dn" class="well form-horizontal">
						<fieldset>
							<legend>电脑</legend>
						</fieldset>
					</form>
				</li>
				<li id="dsj">
					<form id="form_dsj" class="well form-horizontal">
						<fieldset>
							<legend>电视机</legend>
						</fieldset>
					</form>
				</li>
			</ul>
		</div>
	</div>

</div>
<%@ include file="body_footer.jsp"%>
</html>
