<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="header.jsp"%>
<script type="text/javascript">
	var tab = "cart";
</script>
<script type="text/javascript" src="js/cart.js"></script>
</head>
<%@ include file="body_header.jsp"%>
<c:choose>
	<c:when test="${cart!=null && !cart.isEmpty() }">
		<div class="span11" style="padding: 10px;">
			<table class="table table-bordered span9">
				<tr>
					<th>序号</th>
					<th>项目</th>
					<th>特点</th>
					<th>数量</th>
					<th>价格(￥元)</th>
					<th>删除</th>
				</tr>
				<c:forEach var="cartItem" items="${cart}" varStatus="status">
					<tr>
						<td>${status.index+1}</td>
						<td>${cartItem.getDes()}</td>
						<td>${cartItem.getFeature()}</td>
						<td>${cartItem.getCount()}</td>
						<td class="price">${cartItem.getPrice()}</td>
						<td><a href="order/del/${cartItem.getFid()}">删除</a></td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="3">总计:<span class="total"
						style="font-weight: bold; font-size: 25px;"></span></td>
					<td colspan="3"><a class="btn btn-primary" id="finish">结算</a></td>
				</tr>
			</table>
		</div>

	</c:when>
	<c:otherwise>
		<div class="span11" style="height: 50px; text-align: center;">
			空的回收车！<a href="home">返回首页</a>
		</div>

	</c:otherwise>
</c:choose>
<%@ include file="body_footer.jsp"%>
</html>
