<%@ taglib uri="/WEB-INF/tlds/x.tld" prefix="x"%>
<%@ taglib uri="/WEB-INF/tlds/spring.tld" prefix="s"%>
<%@ taglib uri="/WEB-INF/tlds/spring-form.tld" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.color-checkbox {
	cursor: pointer;
}

.image-multi-checkbox {
	border: solid;
	border-color: #bbbbbb;
	background-color: white;
}

.color-checkbox input[type="checkbox"] {
	display: none;
}

.color-checkbox .checkedIcon {
	display: none;
}

.color-checkbox-checked .checkedIcon {
	display: block !important;
	position: absolute;
	background-color: transparent;
	top: -10px;
	right: 10px;
}

.color-checkbox .caratIcon {
	display: none;
}

.color-checkbox-checked .caratIcon {
	display: block !important;
	position: absolute;
	background-color: transparent;
	top: -10px;
	right: 30px;
}
</style>
<f:form method="post" action="${flowExecutionUrl}">
	<div class="container" >	
		<br />
		<div class="row">
			<div class="col-md-12" align="center">
				<h5>START YOUR SEARCH HERE</h5>
			</div>
		</div>
		<br />
		<div class="row">
			<div class="col-md-12" align="center">CARAT SIZE</div>
		</div>
		<br />
		<div class="row">
			<div class="col-md-3 col-sm-3 col-3" align="right"></div>
			<div class="col-md-2 col-sm-2 col-2" " align="right">
				<f:input path="minCarat" />
			</div>
			<div class="col-md-2 col-sm-2 col-2" " align="center">to</div>
			<div class="col-md-2 col-sm-2 col-2" align="left">
				<f:input path="maxCarat" />
			</div>
			<div class="col-md-3 col-sm-3 col-3" align="right"></div>
		</div>
		<br />
		<div class="row">
			<div class="col-md-12" align="center">COLOR</div>
		</div>
		<br />
		<div class="row" align="center">
			<div class="col-md-4 col-sm-3 col-3"></div>
			<div class="col-md-6 col-sm-6 col-7"  align="center">
				<div class="row">
					<c:forTokens items="D,E,F,G,H,I,J" delims="," var="colors"
						varStatus="i">
						<div class="col-md-1 col-sm-1 col-1 image-multi-checkbox" align="center">						
								<label class="color-checkbox"> 
								<span>${colors}</span> 
								<f:checkbox path="color" value="${colors}" id="color[${i.index}]"/>
								<x:img cssClass="checkedIcon" src="/static/images/icon-diamond.svg" />
								</label>						
						</div>
					</c:forTokens>
				</div>
			</div>
			<div class="col-md-2 col-sm-3 col-2"></div>
		</div>
		<br />
		<div class="row">
			<div class="col-md-12" align="center">CLARITY</div>
		</div>
		<br />
		<div class="row">
			<div class="col-md-12" align="center">
				<div class="row">
					<div class="col-md-3 col-sm-2 col-3"></div>
					<div class="col-md-7 col-sm-9 col-7" align="center">
						<div class="row" align="center">
							<c:forTokens items="FL,IF,VVS1,VVS2,VS1" delims=","
								var="clarities" varStatus="i">
								<div class="col-md-2 col-sm-2 col-4 image-multi-checkbox">								
										<label class="color-checkbox" > 
										<span>${clarities}</span> 
										<f:checkbox path="clarity" value="${clarities}" id="clarity[${i.index}]"/>
										<x:img cssClass="caratIcon" src="/static/images/icon-diamond.svg" />
										</label>
									
								</div>
							</c:forTokens>
						</div>
					</div>
					<div class="col-md-2 col-sm-1 col-2"></div>
				</div>
			</div>
		</div>
		<br />
	</div>
	<div class="container">
		<div class="col-sm-12">
			<div class="row">
				<div class="col-md-5 col-sm-4 col-2"></div>
				<div class="col-md-2 col-sm-4 col-8 nopadding" align="center">
					<button class="btn btn-primary x-btn-footer" type="submit"
						name="_eventId_next">
						<s:message code="button.apply" />
					</button>
				</div>
				<div class="col-sm-5 col-sm-4 col-2"></div>
			</div>
		</div>
	</div>
	<br/>
</f:form>
<script type="text/javascript">
	$(".color-checkbox").each(function() {
		if ($(this).find('input[type="checkbox"]').first().attr("checked")) {
			$(this).addClass('color-checkbox-checked .checkedIcon');
			$(this).addClass('color-checkbox-checked .caratIcon');
		} else {
			$(this).removeClass('color-checkbox-checked .checkedIcon');
			$(this).removeClass('color-checkbox-checked .caratIcon');
		}
	});
	
	$(".color-checkbox").on("click", function(e) {		
		var $checkbox = $(this).find('input[type="checkbox"]');
		$checkbox.prop("checked", !$checkbox.prop("checked"))	
		if ($checkbox.prop("checked")) {
			$(this).addClass('color-checkbox-checked .checkedIcon');
			$(this).addClass('color-checkbox-checked .caratIcon');
		} else {
			$(this).removeClass('color-checkbox-checked .checkedIcon');
			$(this).removeClass('color-checkbox-checked .caratIcon');
		}
	});
</script>