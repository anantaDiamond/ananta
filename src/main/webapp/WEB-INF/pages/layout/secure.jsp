<%@ taglib uri="/WEB-INF/tlds/x.tld" prefix="x"%>
<%@ taglib uri="/WEB-INF/tlds/spring.tld" prefix="s"%>
<%@ taglib uri="/WEB-INF/tlds/spring-form.tld" prefix="sf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title><s:message code="app.name" /></title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<x:link rel="stylesheet" href="/static/font-awesome/5.2.0/css/all.css" />
<x:link rel="stylesheet" href="/static/bootstrap/4.1.3/css/bootstrap.min.css" />
<x:link rel="stylesheet" href="/static/bootstrap-datepicker-thai/css/datepicker.css" />
<x:link rel="stylesheet" href="/static/css/secure.css" />
<x:link rel="stylesheet" href="/static/font/Plantin/style.css" />
<x:link rel="stylesheet" href="/static/font/SweetSans-BoldSC/style.css" />
<x:script type="text/javascript" src="/static/jquery/3.3.1-1/jquery.min.js" />
<x:script type="text/javascript" src="/static/popper.js/1.14.3/popper.min.js" />
<x:script type="text/javascript" src="/static/bootstrap/4.1.3/js/bootstrap.min.js" />
<x:script type="text/javascript" src="/static/bootstrap-datepicker-thai/js/bootstrap-datepicker.js" />
<x:script type="text/javascript" src="/static/bootstrap-datepicker-thai/js/bootstrap-datepicker-thai.js" />
<x:script type="text/javascript" src="/static/bootstrap-datepicker-thai/js/locales/bootstrap-datepicker.th.js" />
<x:script type="text/javascript" src="/static/js/secure.js" />
<x:script type="text/javascript" src="/static/jquery-blockui/2.70/jquery.blockUI.js" />
</head>
<body class="bg-ivory">
	<tiles:insertAttribute name="nav" />
	<div class="container" style="padding-top: 100px;">
		<s:hasBindErrors name="xform">
			<br />
			<div class="row">
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
		<c:set var="success" value="${success}" />
		<c:if test="${success eq true}">
			<br />
			<div class="alert alert-success" role="alert">
				<h3>
					<strong><s:message code="app.common.success" /></strong>
				</h3>
				<s:message code="app.common.success.th" />
			</div>
		</c:if>
		<c:if test="${not empty failure}">
			<br />
			<div class="alert alert-danger" role="alert">
				<h3>
					<strong><s:message code="app.common.error" /></strong>
				</h3>
				<c:out value="${failure}" />
				<br />
			</div>
		</c:if>
		<tiles:insertAttribute name="body" />
	</div>
	<tiles:insertAttribute name="footer" />
</body>
</html>
