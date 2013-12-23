<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../header.jsp"%>
</head>
<body>
	<c:choose>
		<c:when test="${user!=null && user.getRole()==2 }">
				<div style="width:1600px;">
					<table class="table">
						<tr>
							<td>序号</td>
							<td>用户名</td>
							<td>负责人姓名</td>
							<td>企业规模</td>
							<td>Email</td>
							<td>电话</td>
							<td>地址</td>
							<td>展示网址</td>
							<td>经营许可</td>
							<td>展示图片</td>
							<td>操作</td>
						</tr>
						<c:forEach var="col" items="${collectors}" varStatus="status">
							<tr style='background-color:${status.index%2==0?"#EEE5DE":"#FFFACD"}'>
								<td>${status.index+1 }</td>
								<td>${col.getCorpName()}</td>
								<td>${col.getCorpOwner()}</td>
								<td>${col.getCorpSize()}</td>
								<td>${col.getEmail()}</td>
								<td>${col.getCorpPhone()}</td>
								<td>${col.getAddr()}</td>
								<td><a href="${col.getShowSite() }" target="_blank">${col.getShowSite()}</a></td>
								<td><c:if test="${col.getCorpLicense()!=null}"><img style="width: 100px;"
									src="${mainpath}/uploads/collector_file/${col.getCorpLicense()}" /></c:if></td>
									<td><c:if test="${col.getCorpShow()!=null}"><img style="width: 100px;"
									src="${mainpath}/uploads/collector_file/${col.getCorpShow()}" /></c:if></td>
								<td><div class="btn-group">
	  									<button class="btn btn-success">通过</button>
									    <button class="btn btn-warn">忽略</button>
									</div>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
		</c:when>
		<c:otherwise>
			<script type="text/javascript">
				alert("管理界面，请先登录");
				window.location.href = "${mainpath}";
			</script>
		</c:otherwise>
	</c:choose>
</body>
</html>