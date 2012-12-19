<%@ page import="org.leaf.eos2.b2b.Contract" %>

<div class="fieldcontain ${hasErrors(bean: contractInstance, field: 'serialNumber', 'error')} required">
	<label for="serialNumber">
		<g:message code="contract.serialNumber.label" default="Serial Number" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="serialNumber" maxlength="18" required="" value="${contractInstance?.serialNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contractInstance, field: 'subject', 'error')} required">
	<label for="subject">
		<g:message code="contract.subject.label" default="Subject" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="subject" maxlength="100" required="" value="${contractInstance?.subject}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contractInstance, field: 'recordType', 'error')} required">
	<label for="recordType">
		<g:message code="contract.recordType.label" default="Record Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="recordType" name="recordType.id" from="${org.leaf.eos2.admin.RecordType.list()}" optionKey="id" required="" value="${contractInstance?.recordType?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contractInstance, field: 'applicant', 'error')} required">
	<label for="applicant">
		<g:message code="contract.applicant.label" default="Applicant" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="applicant" maxlength="10" required="" value="${contractInstance?.applicant}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contractInstance, field: 'filingDate', 'error')} required">
	<label for="filingDate">
		<g:message code="contract.filingDate.label" default="Filing Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="filingDate" precision="day"  value="${contractInstance?.filingDate}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: contractInstance, field: 'effectiveDate', 'error')} required">
	<label for="effectiveDate">
		<g:message code="contract.effectiveDate.label" default="Effective Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="effectiveDate" precision="day"  value="${contractInstance?.effectiveDate}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: contractInstance, field: 'dealer', 'error')} required">
	<label for="dealer">
		<g:message code="contract.dealer.label" default="Dealer" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="dealer" name="dealer.id" from="${org.leaf.eos2.b2b.Dealer.list()}" optionKey="id" required="" value="${contractInstance?.dealer?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contractInstance, field: 'project', 'error')} required">
	<label for="project">
		<g:message code="contract.project.label" default="Project" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="project" maxlength="100" required="" value="${contractInstance?.project}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contractInstance, field: 'industry', 'error')} required">
	<label for="industry">
		<g:message code="contract.industry.label" default="Industry" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="industry" name="industry.id" from="${org.leaf.eos2.b2b.Industry.list()}" optionKey="id" required="" value="${contractInstance?.industry?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contractInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="contract.description.label" default="Description" />
	</label>
	<g:textArea name="description" cols="40" rows="5" maxlength="500" value="${contractInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contractInstance, field: 'contractDetails', 'error')} ">
	<label for="contractDetails">
		<g:message code="contract.contractDetails.label" default="Contract Details" />
	</label>
	<ul class="one-to-many">
		<g:each in="${contractInstance?.contractDetails?}" var="c">
    	<li><g:link controller="contractDetail" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
		</g:each>
		
		<li class="add">
			<g:link controller="contractDetail" action="create" params="['contract.id': contractInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'contractDetail.label', default: 'ContractDetail')])}</g:link>
		</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: contractInstance, field: 'createdBy', 'error')} required">
	<label for="createdBy">
		<g:message code="contract.createdBy.label" default="Created By" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="createdBy" name="createdBy.id" from="${org.leaf.eos2.shiro.User.list()}" optionKey="id" required="" value="${contractInstance?.createdBy?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contractInstance, field: 'lastModifiedBy', 'error')} required">
	<label for="lastModifiedBy">
		<g:message code="contract.lastModifiedBy.label" default="Last Modified By" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="lastModifiedBy" name="lastModifiedBy.id" from="${org.leaf.eos2.shiro.User.list()}" optionKey="id" required="" value="${contractInstance?.lastModifiedBy?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contractInstance, field: 'owner', 'error')} required">
	<label for="owner">
		<g:message code="contract.owner.label" default="Owner" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="owner" name="owner.id" from="${org.leaf.eos2.shiro.User.list()}" optionKey="id" required="" value="${contractInstance?.owner?.id}" class="many-to-one"/>
</div>

