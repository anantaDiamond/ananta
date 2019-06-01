<%@ taglib uri="/WEB-INF/tlds/x.tld" prefix="x"%>
<%@ taglib uri="/WEB-INF/tlds/spring.tld" prefix="s"%>
<%@ taglib uri="/WEB-INF/tlds/spring-form.tld" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<style>
.order-card {
	background-color: white;
	padding: 30px 30px 40px 30px;
}

.order-info-card {
	background-color: #FBFAF6;
	
}
</style>
<f:form action="">
	<div class="container" style="margin-bottom: 142px;">
		<br /> <br />
		<div class="card order-card">
		<div class="row">
			<div class="col-md-9 col-sm-8 col-7">
				<div class="row">
					<div class="col-sm-12">${orders[0].orderId}</div>
				</div>
				<div class="row">
					<div class="col-sm-12"><h3>${orders[0].customerName}</h3></div>
				</div>
			</div>
			<div class="col-md-3 col-sm-4 col-5">
				<div class="row">
					<div class="row">
						<div class="col-sm-12"><h4 style="color:#37373C">${c_statusDescription[orders[0].status]}</h4></div>
					</div>
				</div>
			</div>
		</div>
		<br />
		<div class="row">
			<div class="col-md-7 col-sm-7 col-7">
				<div class="row">
					<div class="col-md-4 col-sm-5 col-5">Tel:</div>
					<div class="col-md-8 col-sm-7 col-7">${orders[0].phone}</div>
				</div>
				<div class="row">
					<div class="col-md-4 col-sm-5 col-5">Email:</div>
					<div class="col-md-8 col-sm-7 col-7">${orders[0].email}</div>
				</div>
				<div class="row">
					<div class="col-md-4 col-sm-5 col-5">Branch:</div>
					<div class="col-md-8 col-sm-7 col-7">${c_branchDescription[orders[0].branch]}</div>
				</div>
				<div class="row">
					<div class="col-md-4 col-sm-5 col-5">Sales:</div>
					<div class="col-md-8 col-sm-7 col-7">${orders[0].salesName}</div>
				</div>
				<div class="row">
					<div class="col-md-4 col-sm-5 col-5">Created:</div>
					<div class="col-md-8 col-sm-7 col-7">${orders[0].createDate}</div>
				</div>

			</div>
			<div class="col-md-5 col-sm-5 col-5">
				<div class="row">
					<div class="col-sm-12">Proposed Price:</div>
				</div>
				<div class="row">
					<div class="col-sm-12">${orders[0].proposed}</div>
				</div>

			</div>
		</div>
		<br />
		<div class="row">
			<div class="col-sm-12">
				<c:forEach var="o" items="${orders}" varStatus="i">
					<div class="row" style="border: solid">
						<div class="col-sm-12">
							<br/>
							<div class="row">
								<div class="col-md-7 col-sm-7 col-7">
									<div class="row">
										<div class="col-md-12 col-sm-12 col-12">Est. Price:</div>
									</div>
									<div class="row">
										<div class="col-md-12 col-sm-12 col-12"><fmt:formatNumber type="number" pattern="#,##0.00 THB" value="${o.orders[0].price}" /></div>
									</div>
								</div>
								<div class="col-md-4 col-sm-4 col-4">
									<div class="row">
										<div class="col-sm-12">${c_statusDescription[o.orders[0].status]}</div>
									</div>
								</div>
							</div>
							<br />
							<div class="row">
								<div class="col-md-6 col-sm-6 col-6">
									<div class="row">
										<div class="col-md-12 col-sm-12 col-12">Specifications:</div>
									</div>

									<div class="row">
										<div class="col-md-12 col-sm-12 col-12">-
											${o.orders[0].diamond.clarity}</div>
									</div>
									<div class="row">
										<div class="col-md-12 col-sm-12 col-12">-
											${o.orders[0].diamond.shape}</div>
									</div>
									<div class="row">
										<div class="col-md-12 col-sm-12 col-12">-
											${o.orders[0].diamond.color}</div>
									</div>
									<div class="row">
										<div class="col-md-12 col-sm-12 col-12">-
											${o.orders[0].diamond.comment}</div>
									</div>
									<br/>
								</div>
								<div class="col-md-6 col-sm-6 col-6""></div>
							</div>
						</div>
					</div>
					<br />
				</c:forEach>
			</div>
		</div>
		</div>
	</div>
</f:form>