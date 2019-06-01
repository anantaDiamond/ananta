<%@ taglib uri="/WEB-INF/tlds/x.tld" prefix="x"%>
<%@ taglib uri="/WEB-INF/tlds/spring.tld" prefix="s"%>
<%@ taglib uri="/WEB-INF/tlds/spring-form.tld" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.order-card {
	background-color: white;
	padding: 30px 30px 40px 30px;
}
</style>
<script type="text/javascript">

	this.onload = initial;

	function initial() {
		
	}
	
	function showEmail(branch) {
		emailList.innerHTML="";
		<c:forEach var="em" items="${email.branchEmail}" varStatus="x">		
			if ("${em.key}" == branch) {	
				<c:forEach var="e" items="${em.value}" varStatus="x">
					emailList.innerHTML=emailList.innerHTML+"${e}"+"<br/>";
				</c:forEach>			
			}
		</c:forEach>
		
		
	}
	
</script>
<f:form method="post" action="">
	<div class="container" style="margin-bottom: 142px;">

		<br /> <br />
		<div class="card order-card">
			<div class="row">
				
					<div class="col-md-12 col-sm-12 col-12">
						<div class="row">
							<div class="col-md-12 col-sm-12 col-12">
								<h4>Set up</h4>
							</div>
						</div>
						<br />
						<div class="row">
							<div class="col-md-4 col-sm-6 col-6">
								<span>Exchange rate</span> <span>(to THB)</span>
							</div>
							<div class="col-md-8 col-sm-6 col-6" align="left">
								<span>${rate.rate}</span>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4 col-sm-6 col-6">
								<span>Carat size-min</span>
							</div>
							<div class="col-md-8 col-sm-6 col-6" align="left">
								<span>${carat.min}</span>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4 col-sm-6 col-6">
								<span>Carat size-max</span>
							</div>
							<div class="col-md-8 col-sm-6 col-6" align="left">
								<span>${carat.max}</span>
							</div>
						</div>
						<br />
					
				</div>
			</div>

			<hr class="widget-separator"
				style="border-width: 2px; padding-top: 15px; color: #E9ECEF;">
			<div class="row">
				<div class="col-md-12 col-sm-12 col-12">
					<div class="row">
						<div class="col-sm-12">
							<h4>Manage Branch</h4>
						</div>
					</div>
					<br />
					<div class="row">
						<div class="col-md-4 col-sm-4 col-4">
							<span>Branch</span>
						</div>
						<div class="col-md-8 col-sm-8 col-8">
							<span>Email</span>
						</div>
					</div>

					<div class="row">
						<div class="col-md-4 col-sm-4 col-4">
							<f:select type="text" path="branch"
								onchange="showEmail(this.value)">
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
						<div class="col-md-8 col-sm-8 col-8">
							<label id="emailList" />
						</div>

					</div>
					<br /> <br />
				</div>

			</div>
		</div>
	</div>
	</div>
</f:form>