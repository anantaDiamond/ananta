<%@ taglib uri="/WEB-INF/tlds/x.tld" prefix="x"%>
<%@ taglib uri="/WEB-INF/tlds/spring.tld" prefix="s"%>
<%@ taglib uri="/WEB-INF/tlds/spring-form.tld" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<f:form method="post" action="${flowExecutionUrl}">
	<div class="container" style="margin-bottom: 142px;">
		<div class="row">
		result
		</div>
	</div>

	<nav class="navbar navbar-expand-md navbar-dark x-footer" style="margin-top: 162px;">
		<div class="container">
			<div class="col-sm-12">
				<div class="row">
					<div class="col-sm-5"></div>
					<div class="col-sm-2 nopadding" style="text-align: right;">
						<button class="btn btn-primary x-btn-footer" style="width: 80%;" type="submit" name="_eventId_next">
							<s:message code="button.confirm" />
						</button>
					</div>
					<div class="col-sm-5"></div>
				</div>
			</div>
		</div>
	</nav>
</f:form>