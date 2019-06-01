<%@ taglib uri="/WEB-INF/tlds/x.tld" prefix="x"%>
<%@ taglib uri="/WEB-INF/tlds/spring.tld" prefix="s"%>
<%@ taglib uri="/WEB-INF/tlds/spring-form.tld" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<style>
.diamond-checkbox {
	cursor: pointer;
}

.diamond-checkbox input[type="checkbox"] {
	display: none;
}

.diamond-checkbox .checkedIcon {
	display: none;
}

.diamond-checkbox-checked .checkedIcon {
	display: block !important;
	position: absolute;
	background-color: white;
	color: #32271D;
	top: -1px;
	height:40px;
}

.result-card {
	background-color: white;
	padding: 20px;
	border-style: none;
}

.filter-card {
	padding: 20px;
}

.diamond-card {
	background-color: white;
	padding: 20px;
	margin:5px;
}
</style>
<script type="text/javascript">
	this.onload = initial;

	function initial() {
		showColor();
		showClarity();
	}

	function showColor() {
		color.innerHTML = "";
		<c:forEach var="c" items="${xform.color}" varStatus="i">
		color.innerHTML = color.innerHTML + "${c}" + ", ";
		</c:forEach>
		color.innerHTML = color.innerHTML.substr(0, color.innerHTML.length - 2);
	}

	function showClarity() {
		clarity.innerHTML = "";
		<c:forEach var="c" items="${xform.clarity}" varStatus="i">
		clarity.innerHTML = clarity.innerHTML + "${c}" + ", ";
		</c:forEach>
		clarity.innerHTML = clarity.innerHTML.substr(0,
				clarity.innerHTML.length - 2);
	}
</script>
<f:form method="post" action="${flowExecutionUrl}">
	<div class="container">
		<br /> <br />
		<div class="row">
			<div class="col-sm-12">
				<h4>YOUR RESULTS</h4>
			</div>
		</div>
		<br />
		<div class="card result-card">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-4">
					<div class="card filter-card">
						<div class="row">
							<div class="col-sm-12">Size</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<c:if test="${!empty xform.minCarat}">
									<label>${xform.minCarat}</label>
								</c:if>
								<c:if test="${!empty xform.maxCarat}">
									<label>- ${xform.maxCarat}</label>
								</c:if>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-3 col-sm-3 col-4">
					<div class="card filter-card">
						<div class="row">
							<div class="col-sm-12">Color</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<label id="color" />
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-3 col-sm-3 col-4">
					<div class="card filter-card">
						<div class="row">
							<div class="col-sm-12">Clarity</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<label id="clarity" />
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-3 col-sm-3">
					<button class="btn" type="submit" name="_eventId_back"
						style="margin-top: 10px;">
						<s:message code="button.edit.filter" />
					</button>
				</div>
			</div>
		</div>
	</div>
	<br />
	<hr class="widget-separator"
		style="border-width: 2px; padding-top: 15px; color: #E9ECEF;">

	<div class="container">

		<div class="row">
			<div class="col-sm-12">
				<c:if test="${!empty result}">
					<div class="row">
						<div class="col-md-6 col-sm-6 col-6">
							<span>Showing ${result.size()} results</span>
						</div>
						<div class="col-md-6 col-sm-6 col-6" align="right">
							<span>Updated ${result[0].updatedTime}</span>

						</div>
					</div>
					<c:forEach var="diamond" items="${result}" varStatus="i">
						<div class="card diamond-card">
						<div class="row" >
							<div class="col-md-2 col-sm-1 col-1" >
								<label path="color" class="diamond-checkbox">
									<div>
										<div><i class="fas fa-plus"></i></div>
										<f:checkbox path="diamonds" value="${diamond.id}"
											id="diamonds[${i.index}]" />
										<div class="checkedIcon"><i class="fas fa-check"></i></div>
									</div>
								</label>
							</div>
							<div class="col-md-7 col-sm-7 col-7" align="left">
								<div class="row">
									<div class="col-md-4 col-sm-5 col-6">Clarity</div>
									<div class="col-md-8 col-sm-7 col-6">${diamond.clarity}</div>
								</div>
								<div class="row">
									<div class="col-md-4 col-sm-5 col-6">Shape</div>
									<div class="col-md-8 col-sm-7 col-6">${diamond.shape}</div>
								</div>
								<div class="row">
									<div class="col-md-4 col-sm-5 col-6">Color</div>
									<div class="col-md-8 col-sm-7 col-6">${diamond.color}</div>
								</div>
								<div class="row">
									<div class="col-md-4 col-sm-5 col-6">Comments</div>
									<div class="col-md-8 col-sm-7 col-6"></div>
								</div>
							</div>
							<div class="col-md-3 col-sm-4 col-4">
								<div class="row">
									<div class="col-sm-12">Price</div>
								</div>
								<div class="row">
									<div class="col-sm-12">
										<fmt:formatNumber type="number" pattern="#,##0.00 THB"
											value="${diamond.price}" /></div>
								</div>
								<div class="row">
									<div class="col-sm-12">SEE GIA CERT</div>
								</div>
							</div>
							</div>
						</div>
					</c:forEach>
					<br />
					<br />
					<div class="row">
						<div class="col-md-5 col-sm-5 col-4"></div>
						<div class="col-md-2 col-sm-2 col-4 nopadding" align="center">
							<button class="btn btn-primary x-btn-footer"
								type="submit" name="_eventId_next">
								<s:message code="button.confirm" />
							</button>
						</div>
						<div class="col-md-5 col-sm-5 col-4"></div>
					</div>
					<br/>
				</c:if>
				
			</div>
		</div>
		<c:if test="${empty result}">
			<span>Not found!</span>
		</c:if>
	</div>
</f:form>
<script type="text/javascript">
	$(".diamond-checkbox").each(function() {
		if ($(this).find('input[type="checkbox"]').first().attr("checked")) {
			$(this).addClass('diamond-checkbox-checked .checkedIcon');
		} else {
			$(this).removeClass('diamond-checkbox-checked .checkedIcon');
		}
	});

	$(".diamond-checkbox").on("click", function(e) {
		var $checkbox = $(this).find('input[type="checkbox"]');
		$checkbox.prop("checked", !$checkbox.prop("checked"))
		if ($checkbox.prop("checked")) {
			$(this).addClass('diamond-checkbox-checked .checkedIcon');
		} else {
			$(this).removeClass('diamond-checkbox-checked .checkedIcon');
		}
	});
</script>