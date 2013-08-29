<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table class="table">
			<tr>
				<td><a href="javascript:;" id="select_all_order">所有订单</a></td>
				<td>订单号</td>
				<td>类型</td>
				<td>描述</td>
				<td>状态</td>
				<td>操作</td>
			</tr>
			<c:forEach var="order" items="${orders}" varStatus="status">
				<tr>
					<td><input type="checkbox" value="${order.getOrderid()}" /></td>
					<td>${order.getOrderid()}</td>
					<td>${order.getTypeName()}</td>
					<td>${order.getFeature() }</td>
					<c:choose>
						<c:when test="${order.getStatus()==1 }">
							<td>可接单</td>
						</c:when>

						<c:when test="${order.getStatus()==0 }">
							<td>已接单</td>
						</c:when>
						<c:otherwise>
							<td>已完成</td>
						</c:otherwise>
					</c:choose>
					
					<td><a class="btn">接单</a></td>
				</tr>
			</c:forEach>
		</table>