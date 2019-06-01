<%@ taglib uri="/WEB-INF/tlds/x.tld" prefix="x"%>
<%@ taglib uri="/WEB-INF/tlds/spring.tld" prefix="s"%>
<%@ taglib uri="/WEB-INF/tlds/spring-form.tld" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<style>
.customer-info-card {
	background-color: white;
	padding: 30px 30px 40px 30px;
}
.additional-info-card {
	background-color: white;
	padding: 30px 30px 40px 30px;
}
.diamond-card {
	background-color: white;
	padding: 20px;
	margin: 5px;
}
</style>
<script type="text/javascript">

	this.onload = initial;

	function initial() {
		iniDatepicker();
	}

	function iniDatepicker(){
		
		$("#dateNeed").addClass("datepicker");
		
		$('.datepicker').datepicker({
			language: 'th-th',
			templates: {
			    leftArrow: '&laquo;',
			    rightArrow: '&raquo;'
			},
			forceParse: true,
			todayBtn: true,
			todayHighlight: true
		});
		
		$('.datepicker-dropdown').css("z-index", 9999);
	}
	
	function openDeleteModal(index) {
		document.getElementById('index').value = index;
	}
	
</script>
<f:form method="post" action="${flowExecutionUrl}">
	<div class="container" style="margin-bottom: 142px;">
	<input type="hidden" id="index" name="index" />
		<br /> <br />
		<div class="row">
			<div class="col-sm-12"><h4>PRICE REQUEST</h4></div>
		</div>
		<div class="row">
			<div class="col-sm-12"><h5>CONFIRMATION FORM</h5></div>
		</div>
		<br />
		<div class="row">
			<div class="col-sm-12">
				<c:if test="${!empty xform.diamonds}">
					<c:forEach var="id" items="${xform.diamonds}" varStatus="i">
						<c:forEach var="diamond" items="${result}" varStatus="x">
							<c:if test="${id eq diamond.id}">
								<div class="card diamond-card">
								<div class="row">
									<div class="col-md-8 col-sm-6 col-6" align="left">
										<div class="row">
											<div class="col-md-3 col-sm-6 col-6">Clarity</div>
											<div class="col-md-9 col-sm-6 col-6">${diamond.clarity}</div>
										</div>
										<div class="row">
											<div class="col-md-3 col-sm-6 col-6">Shape</div>
											<div class="col-md-9 col-sm-6 col-6">${diamond.shape}</div>
										</div>
										<div class="row">
											<div class="col-md-3 col-sm-6 col-6">Color</div>
											<div class="col-md-9 col-sm-6 col-6">${diamond.color}</div>
										</div>
										<div class="row">
											<div class="col-md-3 col-sm-6 col-6">Comments</div>
											<div class="col-md-9 col-sm-6 col-6"></div>
										</div>
									</div>
									<div class="col-md-3 col-sm-5 col-5">
										<div class="row">
											<div class="col-sm-12">Price</div>
										</div>
										<div class="row">
											<div class="col-sm-12">
												<fmt:formatNumber type="number" pattern="#,##0.00 THB" value="${diamond.price}" />
											</div>
										</div>
									</div>
									<div class="col-md-1 col-sm-1 col-1">
										<div class="row">
											<div class="col-sm-12 col-md-12 col-12">												
												<button type="submit" class="btn nopadding" name="_eventId_delete" onclick="openDeleteModal('${i.index}');">
													<i class="far fa-trash-alt fa-sm" style="color: #32271D; cursor: pointer;" ></i>																							
												</button>											
											</div>
										</div>
									</div>
									</div>
								</div>
							</c:if>
						</c:forEach>
					</c:forEach>
				</c:if>
			</div>
		</div>
		<br/>
		<hr class="widget-separator"
		style="border-width: 2px; padding-top: 15px; color: #E9ECEF;">
		<div class="row">
			<div class="col-sm-12"><h5>CUSTOMER INFORMATION</h5></div>
		</div>
		<br />
		<div class="card customer-info-card">
		<div class="row">
			<div class="col-md-7 col-sm-7 col-7">Name</div>
			<div class="col-md-1 col-sm-1 col-1"></div>
			<div class="col-md-4 col-sm-4 col-4"></div>
		</div>
		<div class="row">
			<div class="col-md-7 col-sm-7">
				<f:input path="customer.name" />
			</div>
			<div class="col-md-1 col-sm-1"></div>
			<div class="col-sm-4"></div>
		</div>
		<div class="row">
			<div class="col-md-7 col-sm-7">Email</div>
			<div class="col-md-1 col-sm-1"></div>
			<div class="col-sm-4"></div>
		</div>
		<div class="row">
			<div class="col-md-7 col-sm-7">
				<f:input path="customer.email" />
			</div>
			<div class="col-md-1 col-sm-1"></div>
			<div class="col-sm-4"></div>
		</div>
		<div class="row">
			<div class="col-md-7 col-sm-7">Phone</div>
			<div class="col-md-1 col-sm-1"></div>
			<div class="col-sm-4"></div>
		</div>
		<div class="row">
			<div class="col-md-7 col-sm-7">
				<f:input path="customer.phone" />
			</div>
			<div class="col-md-1 col-sm-1"></div>
			<div class="col-sm-4"></div>
		</div>
		</div>
		<br />
		<br/>
		<div class="row">
			<div class="col-sm-12"><h5>ADDITIONAL INFORMATION</h5></div>
		</div>
		<br />
	
		<div class="card additional-info-card normalSize">
		<div class="row">
			<div class="col-md-7 col-sm-6 col-6">Salesperson</div>	
			<div class="col-md-1 col-sm-1 col-1"></div>	
			<div class="col-md-3 col-sm-5 col-5">Branch Code</div>
		</div>
		<div class="row">
			<div class="col-md-7 col-sm-6 col-6">
				<f:input path="sales.salesPerson" />
			</div>
			<div class="col-md-1 col-sm-1 col-1"></div>
			<div class="col-md-4 col-sm-5 col-5">
				<f:select type="text" path="sales.branchCode">
					<f:option value="">
						<c:out value="Please select value" />
					</f:option>
					<c:forEach var="obj" items="${c_branchDescription}">
						<f:option value="${obj.key}">
							<c:out value="${obj.key}" /> - <c:out value="${obj.value}" />
						</f:option>
					</c:forEach>
				</f:select>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12 col-12">Proposed Price</div>
		</div>
		<div class="row">
			<div class="col-sm-12 col-12">
				<f:textarea path="sales.proposedPrice" />
			</div>
		</div>
		<div class="row">
			<div class="col-md-7 col-sm-6 col-6">Quotation Number</div>
			<div class="col-md-1 col-sm-1 col-1"></div>
			<div class="col-md-4 col-sm-5 col-5">Date needed</div>
		</div>
		<div class="row">
			<div class="col-md-7 col-sm-6 col-6">
				<f:input path="quotationNumber" />
			</div>
			<div class="col-md-1 col-sm-1 col-1"></div>
			<div class="col-md-3 col-sm-4 col-4">
				<f:input id="dateNeed" path="dateNeed" class="form-control"
					style="text-indent:8px;" placeholder="dd/mm/yyyy" />
			</div>
			<div class="col-md-1 col-sm-1 nopadding col-1">
				<f:label path="dateNeed">
					<i class="far fa-calendar-alt" style="color:#32271D;"></i>
				</f:label>
			</div>
		</div>
		<!--  
		<div class="row">
			<div class="col-12 d-block d-sm-none">Salesperson</div>	
		</div>
		<div class="row">
			<div class="col-12 d-block d-sm-none">
				<f:input path="sales.salesPerson" />
			</div>
		</div>
		<div class="row">
			<div class="col-12 d-block d-sm-none">Branch Code</div>
		</div>
		<div class="row">
			<div class="col-12 d-block d-sm-none">
				<f:select type="text" path="sales.branchCode">
					<f:option value="">
						<c:out value="Please select value" />
					</f:option>
					<c:forEach var="obj" items="${c_branchDescription}">
						<f:option value="${obj.key}">
							<c:out value="${obj.key}" /> - <c:out value="${obj.value}" />
						</f:option>
					</c:forEach>
				</f:select>
			</div>
		</div>
		<div class="row">
			<div class="col-12 d-block d-sm-none">Proposed Price</div>
		</div>
		<div class="row">
			<div class="col-12 d-block d-sm-none">
				<f:textarea path="sales.proposedPrice" />
			</div>
		</div>
		<div class="row">
			<div class="col-12 d-block d-sm-none">Quotation Number</div>
		</div>
		<div class="row">
			<div class="col-12 d-block d-sm-none">
				<f:input path="quotationNumber" />
			</div>
		</div>
		<div class="row">
			<div class="col-12 d-block d-sm-none">Date needed</div>
		</div>
		<div class="row">
			<div class="col-5 d-block d-sm-none">
				<f:input id="dateNeed" path="dateNeed" class="form-control"
					style="text-indent:8px;" placeholder="dd/mm/yyyy" />
			</div>
			<div class="col-1 d-block d-sm-none" align="left">
				<f:label path="dateNeed">
					<i class="far fa-calendar-alt" style="color:#32271D;"></i>
				</f:label>
			</div>
			<div class="col-6 d-block d-sm-none"></div>
		</div>
		</div>
		-->
		</div>
		<br/><br/>
		
		<div class="col-sm-12">
			<div class="row">
				<div class="col-md-5 col-sm-5 col-4"></div>
				<div class="col-md-2 col-sm-2 col-4 nopadding" align="center">
					<button class="btn bg-brown txt-white" type="submit" name="_eventId_next">
						<s:message code="button.submit" />
					</button>
				</div>
				<div class="col-md-5 col-sm-5 col-4"></div>
			</div>
		</div>
		
	</div>
	<br/>
</f:form>