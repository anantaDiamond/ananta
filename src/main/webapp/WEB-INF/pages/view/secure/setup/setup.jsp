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
<f:form method="post" action="">
	<div class="container" style="margin-bottom: 142px;">
		<br /> <br />
		<div class="card order-card">
			<div class="row">
				<div class="col-sm-12">
					<div class="row">
						<div class="col-sm-12">
							<h4>Set up</h4>
						</div>
					</div>
					<br />
					<div class="row" style="margin-top: 10px">
						<div class="col-sm-4">
							<span>Exchange rate</span> <span>(to THB)</span>
						</div>
						<div class="col-sm-8" align="left">
							<f:input id="rate" path="rate" class="form-control"
								style="width=75%" />
						</div>
					</div>
					<div class="row" style="margin-top: 10px">
						<div class="col-sm-4">
							<span>Carat size-min</span>
						</div>
						<div class="col-sm-8" align="left">
							<f:input id="minCarat" path="minCarat" class="form-control"
								style="width=75%" />
						</div>
					</div>
					<div class="row" style="margin-top: 10px">
						<div class="col-sm-4">
							<span>Carat size-max</span>
						</div>
						<div class="col-sm-8" align="left">
							<f:input id="maxCarat" path="maxCarat" class="form-control"
								style="width=75%" />
						</div>
					</div>
					<br />
				</div>
			</div>
			<hr class="widget-separator"
				style="border-width: 2px; padding-top: 15px; color: #E9ECEF;">
			<div class="row">
				<div class="col-sm-12">
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

					<div class="row" style="margin-top: 10px">
						<div class="col-md-4 col-sm-4 col-4">
							<f:select type="text" path="branch">
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
						<div
							class="input-group control-group after-add-more col-md-8 col-sm-8 col-8">
							<f:input id="email[]" path="email" class="form-control"
								style="width=75%" placeholder="Email" />
							<div class="input-group-btn add-more">
								<button class="btn" type="button">
									<i class="fas fa-plus"></i>
								</button>
							</div>
						</div>
					</div>

				</div>
			</div>
			<br /> <br />
			<div class="row">
				<div class="col-md-12 col-sm-12 col-12">
					<div class="row">
						<div class="col-md-5 col-sm-5 col-4"></div>
						<div class="col-md-2 col-sm-2 col-4 nopadding">
							<button class="btn bg-brown txt-white" type="submit">
								<s:message code="button.apply" />
							</button>
						</div>
						<div class="col-md-5 col-sm-5 col-4"></div>
					</div>
					<br />
				</div>
			</div>
		</div>
	</div>
</f:form>
<div class="copy hide" style="display: none">
	<div class="col-md-4 col-sm-4 col-4"></div>
	<div class="input-group control-group col-md-8 col-sm-8 col-8"
		style="margin-top: 10px">
		<input type="text" name="email" id="email[]" class="form-control"
			style="" placeholder="Email" />
		<button class="btn remove" type="button">
			<i class="fa fa-window-close"></i>
		</button>
	</div>
</div>
<script type="text/javascript">

	this.onload = initial;
	
	function initial() {
		$("body").on("click", ".remove", function() {
			$(this).parents(".control-group").remove();
		});
	}
	
	$(document).ready(function() {
		$(".add-more").click(function() {
			var html = $(".copy").html();
			$(".after-add-more").after(html);
		});

		$("body").on("click", ".remove", function() {
			$(this).parents(".control-group").remove();
		});
	});
</script>