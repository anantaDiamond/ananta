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
<x:link rel="stylesheet" href="/static/css/unsecure.css" />
<x:link rel="stylesheet" href="/static/font/Plantin/style.css" />
<x:link rel="stylesheet" href="/static/font/SweetSans-BoldSC/style.css" />
<x:script type="text/javascript" src="/static/jquery/3.3.1-1/jquery.min.js" />
<x:script type="text/javascript" src="/static/popper.js/1.14.3/popper.min.js" />
<x:script type="text/javascript" src="/static/bootstrap/4.1.3/js/bootstrap.min.js" />
<x:script type="text/javascript" src="/static/js/unsecure.js" />
</head>
<body>
	<nav class="navbar navbar-expand-md fixed-top navbar-bar bg-white">
	<div class="new_header">
		<div class="row">
			<div class="col-12 col-sm-12 col-md-12 middle_header" align="center">
				<x:img src="/static/images/new_logo.png" cssStyle="max-width:200px;" cssClass="navbar-logo" />
			</div>
			
		</div>
	</div>
	</nav>
	<tiles:insertAttribute name="body" />
</body>
</html>