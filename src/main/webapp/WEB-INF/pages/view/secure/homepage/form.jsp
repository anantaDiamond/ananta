<%@ taglib uri="/WEB-INF/tlds/x.tld" prefix="x"%>
<%@ taglib uri="/WEB-INF/tlds/spring.tld" prefix="s"%>
<%@ taglib uri="/WEB-INF/tlds/spring-form.tld" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.home-card {
	background-color: white;
	padding-bottom: 50px;
	border-style: none;
	width: 100%;
}
</style>
<f:form method="post" action="">
	<div class="container" style="margin-bottom: 142px;">
		<br /> <br />
	
		<div class="row">
			<div class="col-md-12" align="center">
				<x:a href="/search" >
					<button class="btn" style="width: 10.125em; color:#BBBBBB; border: 1px solid #998473;" type="button">
						<span style="color: #32271D; font-size: 1em;"><s:message
								code="button.search.now" /></span>
					</button>
				</x:a>
			</div>
		</div>
		<br/>
		<div class="row">
			<div class="col-md-12" align="center">
				<x:a href="/history">
					<button class="btn" style="width: 10.125em; color:#BBBBBB; border: 1px solid #998473;" type="button">
						<span style="color: #32271D; font-size: 1em;"><s:message
								code="button.history" /></span>
					</button>
				</x:a>
			</div>
		</div>
		<br/>
		<c:if test="${xagent.role eq 'M'}">
			<div class="row">
				<div class="col-md-12" align="center">
					<x:a href="/manage">
						<button class="btn" style="width: 10.125em; color:#BBBBBB; border: 1px solid #998473;" type="button">
						<span style="color: #32271D; font-size: 1em;"><s:message
									code="button.manage" /></span>
						</button>
					</x:a>
				</div>
			</div>
			<br/>
			<div class="row">
				<div class="col-md-12" align="center">
					<x:a href="/setup">
						<button class="btn" style="width: 10.125em; color:#BBBBBB; border: 1px solid #998473;" type="button">
						<span style="color: #32271D; font-size: 1em;"><s:message
									code="button.setup" /></span>
						</button>
					</x:a>
				</div>
			</div>
		</c:if>
		<br/>
		<br/>
		<br/>
		<div class="row">
			<div class="col-md-12" align="center">
				<x:a href="/signout">
						<span style="color: #000000; font-size: 1em;"><s:message
								code="app.common.logout" /></span>
				</x:a>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12" align="center">
				<x:a href="/log">
						<span style="color: #000000; font-size: 1em;"><s:message
								code="button.view.log" /></span>
				</x:a>
			</div>
		</div>
	</div>
</f:form>