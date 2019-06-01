<%@ taglib uri="/WEB-INF/tlds/x.tld" prefix="x"%>
<%@ taglib uri="/WEB-INF/tlds/spring.tld" prefix="s"%>
<%@ taglib uri="/WEB-INF/tlds/spring-form.tld" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="navbar navbar-expand-md fixed-top navbar-bar bg-white">
	<div class="new_header">
		<div class="row">
			<div class="col-sm-1 col-md-1 col-1 left_header">
				<x:a href="/homepage">
					<i class="fas fa-home" style="color: #1f1a16;"></i>
				</x:a>
			</div>
			<div class="col-10 col-sm-10 col-md-10 middle_header" align="center">
				<x:img src="/static/images/new_logo.png" cssStyle="max-width:200px;" cssClass="navbar-logo" />
			</div>
			<div class="col-sm-1 col-md-1 col-1"></div>
		</div>
	</div>
</nav>
