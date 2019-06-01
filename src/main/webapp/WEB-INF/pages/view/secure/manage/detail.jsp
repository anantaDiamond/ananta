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
<f:form method="post" action="">
	<div class="container" style="margin-bottom: 142px;">
		<br /> <br />
		<div class="card order-card">
			<div class="row">
				<div class="col-md-9 col-sm-8 col-7">
					<div class="row">
						<div class="col-sm-12">${orders[0].orderId}</div>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<h3>${orders[0].customerName}</h3>
						</div>
					</div>
				</div>
				<div class="col-md-3 col-sm-4 col-5">
					<div class="row">
						<div class="row">
							<div class="col-sm-12 col-sm-12 col-12 txt-bd-3">
								<f:hidden path="cusOrder.id" />
								<f:hidden path="cusOrder.status" />
								<h4 style="color:#37373C">${c_orderStatusDescription[xform.cusOrder.status]}</h4>
							</div>
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
			<br /> <br />
			<div class="row">
				<div class="col-sm-12">
					<c:forEach var="o" items="${orders}" varStatus="i">
						<div class="row" style="border: solid">
							<div class="col-sm-12">
								<div class="row">
									<div class="col-sm-12">
										<br />
										<div class="row">
											<div class="col-md-4 col-sm-4 col-4">
												<div class="row">
													<div class="col-sm-12">Cost</div>
												</div>
												<div class="row">
													<div class="col-sm-12">
														<fmt:formatNumber type="number" pattern="#,##0.00 THB"
															value="${o.orders[0].diamond.cost}" />
													</div>
												</div>
											</div>
											<div class="col-md-4 col-sm-4 col-4">
												<div class="row">
													<div class="col-sm-12">Est. Price</div>
												</div>
												<div class="row">
													<div class="col-sm-12">
														<fmt:formatNumber type="number" pattern="#,##0.00 THB"
															value="${o.orders[0].price}" />
													</div>
												</div>
											</div>
											<div class="col-md-4 col-sm-4 col-4">
												<div class="row"></div>

												<div class="row">
													<f:hidden path="cusOrder.orders[${i.index}].diamondId" />
													<div class="col-sm-12">
														<f:select type="text"
															path="cusOrder.orders[${i.index}].status">
															<c:forEach var="obj" items="${c_statusDescription}">
																<f:option value="${obj.key}">
																	<c:out value="${obj.value}" />
																</f:option>
															</c:forEach>
														</f:select>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<br />
								<div class="row">
									<div class="col-md-6 col-sm-4 col-4">
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
										<br />
										<div class="row">
											<div class="col-md-12 col-sm-12 col-12">Contact:</div>

										</div>
										<div class="row">
											<div class="col-md-12 col-sm-12 col-12">${o.orders[0].diamond.supplier}</div>

										</div>
										<div class="row">
											<div class="col-md-12 col-sm-12 col-12">${o.orders[0].diamond.phone}</div>

										</div>
										<div class="row">
											<div class="col-md-12 col-sm-12 col-12">${o.orders[0].diamond.lotLocation}</div>
										</div>
										<br /> <br /> <br />
									</div>
									<div class="col-md-6 col-sm-8 col-8">
										<br /> <br /> <br /> <br />
										<br />
										<div class="row">
											<div class="col-md-2 col-sm-2 col-2"></div>
											<div class="col-md-10 col-sm-10 col-10" align="left">SEE
												GIA CERT</div>
										</div>
										<div class="row">
											<div class="col-md-2 col-sm-2 col-2"></div>
											<div class="col-md-10 col-sm-10 col-10" align="left">SEE
												MORE ON RAPNET</div>
										</div>
										<div class="row">
											<div class="col-md-2 col-sm-2 col-2"></div>
											<div class="col-md-10 col-sm-10 col-10" align="left">ENTER NOTES
												<!--<x:a href="#notes" cssClass="btn">ENTER NOTES</x:a>
												<button onclick="noteModal(${i.index})"
													style="margin-top: 10px; color:white">
													<s:message code="button.edit.filter" />
												</button>-->
											</div>
										</div>
										<div class="row">
											<div class="col-md-2 col-sm-2 col-2"></div>
											<div class="col-md-10 col-sm-10 col-10" align="left">SEND
												TO AN EMAIL</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
					<br />
				</div>
			</div>
			<div class="col-md-12 col-sm-12 col-12">
				<div class="row">
					<div class="col-md-5 col-sm-5 col-4"></div>
					<div class="col-sm-2 col-sm-2 col-4 nopadding">
						<button class="btn bg-brown txt-white" type="submit">
							<s:message code="button.save" />
						</button>
					</div>
					<div class="col-md-5 col-sm-5 col-4"></div>
				</div>
			</div>
		</div>		
	</div>
</f:form>
<script type="text/javascript">
	function noteModal(index) {
		//document.getElementById('deleteIndex').value = index
		$('#notes').modal('show');
	}
</script>