<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="header.jsp"%>
<script type="text/javascript">
	var tab = "cart";
</script>
<script type="text/javascript" src="js/order.js"></script>
</head>
<%@ include file="body_header.jsp"%>
<div class="span11">
订单：
	<table class="table table-bordered">
		<tr>
			<th>序号</th>
			<th>项目</th>
			<th>特点</th>
			<th>数量</th>
			<th>价格(￥元)</th>
		</tr>
		<c:forEach var="cartItem" items="${cart}" varStatus="status">
			<tr>
				<td>${status.index+1}</td>
				<td>${cartItem.getDes()}</td>
				<td>${cartItem.getFeature()}</td>
				<td>${cartItem.getCount()}</td>
				<td class="price">${cartItem.getPrice()}</td>
			</tr>
		</c:forEach>
		
	</table>
</div>
<div class="span11">
	<form id="order_form" class="form-horizontal" action="order/add" method="post">
		<div class="control-group">
			<label class="control-label" for="phone">电话:</label>
			<div class="controls">
				<input type="text" id="phone" name="phone" placeholder="电话">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="connector">联系人:</label>
			<div class="controls">
				<input type="text" id="connector" name="connector" placeholder="联系人">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="addr1">城市:</label>
			<div class="controls">
				<select id="addr1" name="addr1">
					<option value="bj">北京</option>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="addr2">区/县:</label>
			<div class="controls">
				<input type="text" id="addr2" name="addr2" placeholder="区/县">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="addr3">地域/商圈:</label>
			<div class="controls">
				<input type="text" id="addr3" name="addr3" placeholder="地域/商圈">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="addr4">详细地址:</label>
			<div class="controls">
				<input type="text" id="addr4" name="addr4" placeholder="详细地址">
			</div>
		</div>
		<div class="controls">
			<input type="button" class="btn" id="finish" value="提交" />
		</div>
	</form>
</div>
<%@ include file="body_footer.jsp"%>
</html>
