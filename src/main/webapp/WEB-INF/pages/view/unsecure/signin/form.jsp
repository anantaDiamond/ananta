<%@ taglib uri="/WEB-INF/tlds/x.tld" prefix="x"%>
<%@ taglib uri="/WEB-INF/tlds/spring.tld" prefix="s"%>
<%@ taglib uri="/WEB-INF/tlds/spring-form.tld" prefix="sf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container">
	<s:hasBindErrors name="xform">
		<div class="row" style="margin-top:100px;">
			<div class="col-sm-12">
				<div class="alert alert-danger" role="alert">
					<h3>
						<strong><s:message code="app.common.error" /></strong>
					</h3>
					<c:forEach var="error" items="${errors.allErrors}">
						<s:message message="${error}" />
						<br />
					</c:forEach>
				</div>
			</div>
		</div>
	</s:hasBindErrors>
	<div class="login-container">
		<sf:form action="signin">
			<div class="form-group">
				<div class="row">
					<div class="col-sm-3"></div>
					<div class="col-sm-6" align="center">
						<sf:input path="name" cssClass="form-control" placeHolder="Name" />
						<div class="invalid-feedback">
							<sf:errors path="name" class="help-inline" />
						</div>
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<div class="row">
					<div class="col-sm-3"></div>
					<div class="col-sm-6" align="center">
						<sf:password path="email" cssClass="form-control"
							placeHolder="Email" />
						<div class="invalid-feedback">
							<sf:errors path="email" class="help-inline" />
						</div>
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<div class="row">
					<div class="col-sm-3"></div>
					<div class="col-sm-6" align="center">
						<button class="btn bg-brown" style="width: 100%; color: #FFFFFF">
							<s:message code="app.common.signin" />
						</button>
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
		</sf:form>
	</div>
</div>
