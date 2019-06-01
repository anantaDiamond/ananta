<%@ taglib uri="/WEB-INF/tlds/x.tld" prefix="x"%>
<%@ taglib uri="/WEB-INF/tlds/spring.tld" prefix="s"%>
<%@ taglib uri="/WEB-INF/tlds/spring-form.tld" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.history-card {
	background-color: white;
	padding-bottom: 50px;
	width: 100%;
}
</style>
<script type="text/javascript">
	function filters(status) {
		location.href = "${pageContext.request.contextPath}/manage?status="+status;
	}
</script>
<f:form method="post" action="">
	<div class="container" style="margin-bottom: 142px;">
		<br />
		<div class="row">
			<div class="col-md-5 col-sm-5 col-5 nopadding">
				<h4>HISTORY</h4>
			</div>
			<div class="col-md-4 col-sm-4 col-4"></div>
			<div class="col-md-3 col-sm-3 col-3">
				<f:select path="statusFilter" onchange="filters(this.value);">
					<f:option value="">
						<c:out value="filters" />
					</f:option>
					<c:forEach var="obj" items="${c_orderStatusDescription}">
						<f:option value="${obj.key}">
							<c:out value="${obj.value}" />
						</f:option>
					</c:forEach>
				</f:select>
			</div>
		</div>
		<br />
		<div class="row">
			<div class="history-card">
				<div class="table-responsive">
					<table class="table">
						<thead class="px-0"
							style="color: #FBFAF6; background-color: #BBBBBB; border-bottom: 1px;">
							<tr>
								<th scope="col" width="30%" style="border-bottom: 1px;">DATE</th>
								<th scope="col" width="35%" style="border-bottom: 1px;">CUSTOMER</th>
								<th scope="col" width="35%" style="border-bottom: 1px;">STATUS</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="cus" items="${cusOrder}" varStatus="i">
								<tr style="height: 50px;">
									<th scope="row"><span style="color: #373A3C;"> <c:out
												value="${cus.value[0].createDate}" />
									</span></th>
									<th scope="row"><span style="color: #373A3C;"> <x:a
												href="/history/detail?orderId=${cus.key}">${cus.value[0].customerName}</x:a>
									</span></th>
									<th scope="row"><span style="color: #373A3C;"> <c:out
												value="${c_orderStatusDescription[cus.value[0].status]}" />
									</span></th>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="col-md-12 col-sm-12 col-12" align="center">
					<nav>
							<ul class="pagination justify-content-center">
								<li class="page-item"><a class="page-link" href="#"
									aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
										<span class="sr-only">Previous</span>
								</a></li>
								<c:forEach begin="1" end="${page}" varStatus="i">
									<li class="page-item"><a class="page-link" href="<%=request.getContextPath() %>/manage?page=${i.index}">${i.index}</a></li>
								</c:forEach>
								<li class="page-item"><a class="page-link" href="#"
									aria-label="Next"> <span aria-hidden="true">&raquo;</span>
										<span class="sr-only">Next</span>
								</a></li>
							</ul>
						</nav>
				</div>
				
			</div>
		</div>
	</div>
</f:form>