<g:javascript src="menu.js"/>
<link rel="stylesheet" href="${resource(dir:'css',file:'menu.css')}" />
<div id="menu">
    <ul class="menu">
    
        <!--BEGIN HOME-->
        <li><a class="parent" href="${createLink(uri: '/')}"><span><g:message code="default.home.label"/></span></a></li>
        <!--END HOME-->
        
        <!--current object -->
        <g:if test="${!['index', 'salesOrder220', 'salesOrder221', 'salesOrder224', 'salesOrder222','auth', 'report', 'buildInfo', 'search', 'resetMyPassword'].contains(controllerName)}">
			<g:set var="entityName" value="${message(code: controllerName+'.label')}" />
			<shiro:hasPermission permission="${controllerName}:list">
			<li><g:link class="${controllerName}" action="list"><span style="color:blue;"><g:message code="default.list.label" args="[entityName]" /></span></g:link></li>
			</shiro:hasPermission>
			
			<shiro:hasPermission permission="${controllerName}:create">
			<li><g:link class="${controllerName}" action="create"><span style="color:blue;"><g:message code="default.new.label" args="[entityName]" /></span></g:link></li>
			</shiro:hasPermission>
        </g:if>

        <!--end current object-->
        <!--BEGIN SETUP-->
        <shiro:hasPermission permission="menu:setting">
        <li>
        	<a href="#" onclick="javascript:none()" class="parent"><span><g:message code="menu.setting.label" default="Setting"/></span></a>
			<ul>

				<shiro:hasPermission permission="user:index">
				<li><a href="${createLink(controller: 'user', action : 'index')}"><span><g:message code="user.label" default="User"/></span></a></li>
				</shiro:hasPermission>

				<shiro:hasPermission permission="role:index">
				<li><a href="${createLink(controller: 'role', action : 'index')}"><span><g:message code="role.label" default="Role"/></span></a></li>
				</shiro:hasPermission>
			
				<shiro:hasPermission permission="profile:index">
				<li><a href="#"><span>---</span></a></li>
				<li><a href="${createLink(controller: 'profile', action : 'index')}"><span><g:message code="profile.label" default="Profile"/></span></a></li>
				</shiro:hasPermission>
				
				<shiro:hasPermission permission="recordType:index">
				<li><a href="${createLink(controller: 'recordType', action : 'index')}"><span><g:message code="recordType.label" default="RecordType"/></span></a></li>
				</shiro:hasPermission>
				
				<shiro:hasPermission permission="group:index">
				<li><a href="${createLink(controller: 'group', action : 'index')}"><span><g:message code="group.label" default="Group"/></span></a></li>
				</shiro:hasPermission>
				
				<shiro:hasPermission permission="shareRole:index">
				<li><a href="${createLink(controller: 'shareRole', action : 'index')}"><span><g:message code="shareRole.label" default="ShareRole"/></span></a></li>
				</shiro:hasPermission>
				
				<shiro:hasPermission permission="workflow:index">
				<li><a href="${createLink(controller: 'workflow', action : 'index')}"><span><g:message code="workflow.label" default="Workflow"/></span></a></li>
				</shiro:hasPermission>

				
				<shiro:hasPermission permission="obConfig:index">
				<li><a href="#"><span>---</span></a></li>
				<li><a href="${createLink(controller: 'obConfig', action : 'index')}"><span><g:message code="obConfig.label" default="Outbound Config"/></span></a></li>
				</shiro:hasPermission>
						
				<shiro:hasPermission permission="menu:buildInfo">
				<li><a href="#"><span>---</span></a></li>
				<li><a href="${createLink(controller: 'buildInfo', action : 'index')}"><span><g:message code="buildInfo.label" default="Build Info"/></span></a></li>
				</shiro:hasPermission>

				<shiro:hasPermission permission="loginHistory:index">
				<li><a href="#"><span>---</span></a></li>
				<li><a href="${createLink(controller: 'loginHistory', action : 'index')}"><span><g:message code="loginHistory.label" default="Login History"/></span></a></li>
				</shiro:hasPermission>
				
				<shiro:hasPermission permission="help:index">
				<li><a href="#"><span>---</span></a></li>
				<li><a href="${createLink(controller: 'help', action : 'index')}"><span><g:message code="help.label" default="Help"/></span></a></li>
				</shiro:hasPermission>

			</ul>
        </li>
        </shiro:hasPermission>
        <!--END SETUP-->
        
        <!--BEGIN b2b-->
        <shiro:hasPermission permission="menu:b2b">
        <li>
        	<a href="#" onclick="javascript:none()" class="parent"><span><g:message code="menu.b2b.label" default="B2B"/></span></a>
			<ul>

				<shiro:hasPermission permission="product:index">
				<li><a href="${createLink(controller: 'product', action : 'index')}"><span><g:message code="product.label" default="Product"/></span></a></li>
				</shiro:hasPermission>

				<shiro:hasPermission permission="category:index">
				<li><a href="${createLink(controller: 'category', action : 'index')}"><span><g:message code="category.label" default="Category"/></span></a></li>
				</shiro:hasPermission>

				<shiro:hasPermission permission="bom:index">
				<li><a href="${createLink(controller: 'bom', action : 'index')}"><span><g:message code="bom.label" default="BOM"/></span></a></li>
				</shiro:hasPermission>

				<shiro:hasPermission permission="industry:index">
				<li><a href="#"><span>---</span></a></li>
				<li><a href="${createLink(controller: 'industry', action : 'index')}"><span><g:message code="industry.label" default="Industry"/></span></a></li>
				</shiro:hasPermission>
				
				<shiro:hasPermission permission="department:index">
				<li><a href="${createLink(controller: 'department', action : 'index')}"><span><g:message code="department.label" default="Department"/></span></a></li>
				</shiro:hasPermission>

				<shiro:hasPermission permission="dealer:index">
				<li><a href="${createLink(controller: 'dealer', action : 'index')}"><span><g:message code="dealer.label" default="Dealer"/></span></a></li>
				</shiro:hasPermission>

				<shiro:hasPermission permission="dealerProduct:index">
				<li><a href="${createLink(controller: 'dealerProduct', action : 'index')}"><span><g:message code="dealerProduct.label" default="Dealer Product"/></span></a></li>
				</shiro:hasPermission>

				
				<shiro:hasPermission permission="staff:index">
				<li><a href="${createLink(controller: 'staff', action : 'index')}"><span><g:message code="staff.label" default="Staff"/></span></a></li>
				</shiro:hasPermission>

				<shiro:hasPermission permission="contract:index">
				<li><a href="#"><span>---</span></a></li>
				<li><a href="${createLink(controller: 'contract', action : 'index')}"><span><g:message code="contract.label" default="Contract"/></span></a></li>
				</shiro:hasPermission>

			</ul>
        </li>
        </shiro:hasPermission>
        <!--END b2b-->		
		
        <!--BEGIN MY ORDER-->
        <shiro:hasPermission permission="menu:mySalesOrder">
        <li>
			<a class="parent" onclick="javascript:none()" href="#"><span><g:message code="menu.myOrder.lable" default="My Order"/></span></a>
			<g:include controller="menu" action="mySalesOrderList" />
		</li>
		</shiro:hasPermission>
		
		<shiro:hasPermission permission="menu:createSalesOrder">
        <li>
			<a href="#" onclick="javascript:none()" class="parent"><span><g:message code="menu.create.order.label" default="Create SalesOrder"/></span></a>
			<ul>
				<shiro:hasPermission permission="salesOrder220:create">
				<li><a href="${createLink(controller: 'salesOrder220', action : 'create')}"><span><g:message code="menu.createSalesOrder220.label"/></span></a></li>
				</shiro:hasPermission>
				
				<shiro:hasPermission permission="salesOrder221:create">
				<li><a href="${createLink(controller: 'salesOrder221', action : 'create')}"><span><g:message code="menu.createSalesOrder221.label"/></span></a></li>
				</shiro:hasPermission>

				<shiro:hasPermission permission="salesOrder222:create">
				<li><a href="${createLink(controller: 'salesOrder222', action : 'create')}"><span><g:message code="menu.createSalesOrder222.label"/></span></a></li>
				</shiro:hasPermission>
				
				<shiro:hasPermission permission="salesOrder224:create">
				<li><a href="${createLink(controller: 'salesOrder224', action : 'create')}"><span><g:message code="menu.createSalesOrder224.label"/></span></a></li>
				</shiro:hasPermission>
			</ul>
        </li>
        </shiro:hasPermission>
		<!--END MY ORDER-->
		
        <!--BEGIN MY ORDER-->
        <shiro:hasPermission permission="menu:reportList">
        <li>
			<a class="parent" onclick="javascript:none()" href="#"><span><g:message code="menu.report.lable" default="Report"/></span></a>
			<g:include controller="menu" action="reportList" />
		</li>
		</shiro:hasPermission>
		
		<!--begin outbound-->
		<shiro:hasPermission permission="menu:outBoundList">
		<li>
			<g:include controller="menu" action="outBoundList" />
    	</li>
		</shiro:hasPermission>
		<!--end Outbound-->
				
		<shiro:hasPermission permission="menu:search">
        <li>
			<a href="${createLink(controller: 'search', action : 'index')}"><span><g:message code="menu.search.label"/></span></a>
        </li>
        </shiro:hasPermission>
        
		<shiro:hasPermission permission="menu:news">
        <li>
			<a href="${createLink(controller: 'entity', action : 'index')}"><span><g:message code="entity.label"/></span></a>
        </li>
        </shiro:hasPermission>


		<shiro:hasPermission permission="menu:resetMyPassword">
        <li>
			<a href="${createLink(controller: 'resetMyPassword', action : 'resetPassword')}"><span><g:message code="user.resetPassword"/></span></a>
        </li>
        </shiro:hasPermission>


        <!--BEGIN SIGNOUT-->
        <li class="last">
            <a href="${createLink(controller: 'auth', action : 'signOut')}" onclick="return confirm('${message(code: 'default.button.signOut.confirm', default: 'Are you sure?')}');"><span><g:message code="default.signOut.label" default="signOut"/>, <shiro:principal/></span></a>
        </li>
        <!--END SIGNOUT-->
    </ul>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$('a[href="http://apycom.com/"]').remove();
});
</script>
