<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<c:choose>
	<c:when test="${sessionScope.user!=null}">

		<table class="table table-bordered">
			<!--tr>
				<td><a href="javascript:;" id="select_all_order">所有订单</a></td>
				
				<td>价格</td>
				<td>类型</td>
				<td>描述</td>
				<td>状态</td>
				<td>操作</td>
			</tr-->
			<c:forEach var="order" items="${orders}" varStatus="status">
				<tr style="background-color: #EEEEEE; font-size: 12px;">
					<td style="height: 15px;" colspan="7">订单号：${order.getOrderid()}&nbsp;&nbsp;提交时间：${order.getCreate()
						}&nbsp;&nbsp;修改时间：${order.getUpdate() }</td>
				</tr>
				<c:forEach var="of" items="${order.getOrderFeatures() }"
					varStatus="ofs">
					<tr>
						<td>${of.getPrice()}.00</td>
						<td>${of.getTypeName()}</td>
						<td>${of.getFeature() }</td>
						<td>${of.getCount() }</td>

						<c:if test="${ofs.index==0}">
							<c:choose>
								<c:when test="${order.getStatus()==1 }">
									<td valign="middle" style="vertical-align: middle;"
										rowspan="${order.getOrderFeatures().size()}">可接单</td>
								</c:when>

								<c:when test="${order.getStatus()==0 }">
									<td valign="middle" style="vertical-align: middle;"
										rowspan="${order.getOrderFeatures().size()}">已接单</td>
								</c:when>
								<c:otherwise>
									<td valign="middle" style="vertical-align: middle;"
										rowspan="${order.getOrderFeatures().size()}">已完成</td>
								</c:otherwise>
							</c:choose>
							<td valign="middle" style="vertical-align: middle;"
								rowspan="${order.getOrderFeatures().size()}">${order.getTotal()}.00</td>
							<c:if test="${user!=null && user.getRole()==2 }">
							<td valign="middle" style="vertical-align: middle;"
								rowspan="${order.getOrderFeatures().size()}"><a
								class="btn btn-danger data_opr" href="javascript:;"
								data="{orderid:'${order.getOrderid()}'}"
								data-loading-text="正在删除">删除</a></td>
							</c:if>
							<c:if test="${user!=null && user.getRole()==0 }">
							<td valign="middle" style="vertical-align: middle;"
								rowspan="${order.getOrderFeatures().size()}"><a
								class="btn btn-danger data_opr" href="javascript:;"
								data="{orderid:'${order.getOrderid()}'}"
								data-loading-text="正在接单">接单</a></td>
							</c:if>
							
						</c:if>
					</tr>
				</c:forEach>
			</c:forEach>
		</table>

	</c:when>
	<c:otherwise>
		<div
			style="padding: 15px; font-size: 20px; font-weight: bold; text-align: center;">管理界面，请先登录</div>
	</c:otherwise>
</c:choose>