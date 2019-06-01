<%@ taglib uri="/WEB-INF/tlds/x.tld" prefix="x"%>
<%@ taglib uri="/WEB-INF/tlds/spring.tld" prefix="s"%>
<%@ taglib uri="/WEB-INF/tlds/spring-form.tld" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.summary-card {
	background-color: white;
	padding: 30px 30px 40px 30px;
}
</style>
<f:form method="post" action="${flowExecutionUrl}">
	<div class="container" style="margin-bottom: 142px;">
		<br/>
		<br/>
		<br/>
		<div class="card summary-card">
		<div class="row">
			<div class="col-sm-12 txt-red" align="center">
				<h4 class="txt-red">Order completed!</h4>
			</div>
		</div>
		<br/>
		<div class="row">
			<div class="col-sm-12" align="center">
				<h5 class="txt-red">Order ID: <span>${orderId}</span></h5>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12 txt-red"  align="center">
				<h5 class="txt-red">If there is any change, please contact to management directly.</h5>
			</div>
		</div>
	<br/><br/>
			<div class="col-sm-12">
				<div class="row">
					<div class="col-md-4 col-sm-4 col-4 nopadding">
						<button class="btn bg-brown txt-white" type="submit" name="_eventId_history">
							<s:message code="button.history" />
						</button>
					</div>
					<div class="col-md-4 col-sm-4 col-4 "></div>
					<div class="col-md-4 col-sm-4 col-4 nopadding" style="text-align: right;">
						<button class="btn bg-brown txt-white" type="submit" name="_eventId_search">
							<s:message code="button.search.again" />
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</f:form>