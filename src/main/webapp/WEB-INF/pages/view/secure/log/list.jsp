<%@ taglib uri="/WEB-INF/tlds/x.tld" prefix="x"%>
<%@ taglib uri="/WEB-INF/tlds/spring.tld" prefix="s"%>
<%@ taglib uri="/WEB-INF/tlds/spring-form.tld" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.log-card {
	background-color: white;
	padding-bottom: 50px;
	width: 100%;
}
</style>
<f:form action="">
	<input type="hidden" name="page" />
	<div class="container" style="margin-bottom: 142px;">
		<br />
		<div class="row">
			<div class="col-md-12 col-sm-12 col-12">
				<h4>LOG</h4>
			</div>
		</div>
		<br />
		<div class="row">
			<div class="log-card">
				<div class="table-responsive">
					<table class="table">
						<thead class="px-0"
							style="color: #FBFAF6; background-color: #BBBBBB; border-bottom: 1px;">
							<tr>
								<th scope="col" width="30%" style="border-bottom: 1px;">DATE</th>
								<th scope="col" width="35%" style="border-bottom: 1px;">EMAIL</th>
								<th scope="col" width="35%" style="border-bottom: 1px;">BRANCH</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="log" items="${obj}" varStatus="i">
								<tr style="height: 50px;">
									<td><div>${log.createDate}</div></td>
									<td><div>${log.email}</div></td>
									<td><div>${c_branchDescription[log.branch]}</div></td>
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
									<li class="page-item"><a class="page-link" href="<%=request.getContextPath() %>/log?page=${i.index}">${i.index}</a></li>
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