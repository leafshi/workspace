<%@ page import="org.leaf.eos2.shiro.LoginHistory" %>

<div class="fieldcontain ${hasErrors(bean: loginHistoryInstance, field: 'username', 'error')} required">
	<label for="username">
		<g:message code="loginHistory.username.label" default="Username" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="username" maxlength="100" value="${fieldValue(bean: loginHistoryInstance, field: 'username')}" />
</div>

<div class="fieldcontain ${hasErrors(bean: loginHistoryInstance, field: 'loginTime', 'error')} required">
	<label for="loginTime">
		<g:message code="loginHistory.loginTime.label" default="Login Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="loginTime" value="${loginHistoryInstance?.loginTime}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: loginHistoryInstance, field: 'sourceIP', 'error')} required">
	<label for="sourceIP">
		<g:message code="loginHistory.sourceIP.label" default="Source IP" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="sourceIP" maxlength="40" value="${fieldValue(bean: loginHistoryInstance, field: 'sourceIP')}" />
</div>

<div class="fieldcontain ${hasErrors(bean: loginHistoryInstance, field: 'loginType', 'error')} required">
	<label for="loginType">
		<g:message code="loginHistory.loginType.label" default="Login Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="loginType" maxlength="50" value="${fieldValue(bean: loginHistoryInstance, field: 'loginType')}" />
</div>

<div class="fieldcontain ${hasErrors(bean: loginHistoryInstance, field: 'isSucceed', 'error')} ">
	<label for="isSucceed">
		<g:message code="loginHistory.isSucceed.label" default="Is Succeed" />
	</label>
	<g:checkBox name="isSucceed" value="${loginHistoryInstance?.isSucceed}" />
</div>

<div class="fieldcontain ${hasErrors(bean: loginHistoryInstance, field: 'browser', 'error')} required">
	<label for="browser">
		<g:message code="loginHistory.browser.label" default="Browser" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="browser" maxlength="100" value="${fieldValue(bean: loginHistoryInstance, field: 'browser')}" />

</div>

<div class="fieldcontain ${hasErrors(bean: loginHistoryInstance, field: 'platform', 'error')} required">
	<label for="platform">
		<g:message code="loginHistory.platform.label" default="Platform" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="platform" maxlength="100" value="${fieldValue(bean: loginHistoryInstance, field: 'platform')}" />
</div>

<div class="fieldcontain ${hasErrors(bean: loginHistoryInstance, field: 'operatingSystem', 'error')} required">
	<label for="operatingSystem">
		<g:message code="loginHistory.operatingSystem.label" default="Operating System" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="operatingSystem" maxlength="100" value="${fieldValue(bean: loginHistoryInstance, field: 'operatingSystem')}" />
</div>