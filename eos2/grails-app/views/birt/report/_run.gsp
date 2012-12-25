<table>
    <tbody>
        <g:each var="p" in="${parameters}">
            <g:if test="${p.defaultVal && !reportParams[showAll]}" >
                <input type="hidden" name="${p.name}" value="${reportParams[p.defaultVal]}" /> 
            </g:if>
            <g:else>
                <g:if test="${p.hidden}">
                    <input type="hidden" name="${p.name}" />
                </g:if>
                <g:else>
                    <tr class="prop">
                        <td valign="top" class="name">
                            <label for='${p.name}'>${p.promptText?:p.name}:</label>
                        </td>
                        <td valign="top" class="value">
                            <g:if test="${p.type==7}">
                                <g:if test="${showAll ||!reportParams[p.name]}">
                                    <g:if test="${reportParams[p.name]}">
                                        <g:textField name="${p.name}" value="${reportParams[p.name]}" />
                                        <script type="text/javascript">
                                            $(function() {
                                                $( "#${p.name}" ).attr("readonly", true).datepicker({dateFormat: 'yy-mm-dd'});
                                            });
                                        </script>
                                    </g:if>
                                    <g:else>
                                        <g:textField name="${p.name}" value="" />
                                        <script type="text/javascript">
                                            $(function() {
                                                $( "#${p.name}" ).attr("readonly", true).datepicker({dateFormat: 'yy-mm-dd'});
                                            });
                                        </script>
                                    </g:else>
                                </g:if>
                                <g:else>
                                    <g:textField name="${p.name}" value="${reportParams[p.name]}" class="readonly" readonly="readonly"/>
                                </g:else>
                            </g:if>
                            <g:else>
                                <g:if test="${showAll!=null || reportParams[p.name]==null }">
                                    <g:if test="${p.listEntries?.size()>0}">
                                        <g:select name="${p.name}" from="${p.listEntries}" value="${reportParams[p.name]?:p.defaultVal}" optionKey="value" optionValue="label"/>
                                    </g:if>
                                    <g:else>
                                        <g:textField name="${p.name}" value="${reportParams[p.name]?:p.defaultVal}" />
                                    </g:else>
                                </g:if>
                                <g:else>
                                    <g:textField name="${p.name}" value="${reportParams[p.name]}" class="readonly" readonly="readonly"/>
                                </g:else>
                            </g:else>
                            <g:if test="${!p.allowBlank }">
                                <span class="unit"><g:message code="birt.reportParameters.required.label" /> </span>
                            </g:if>
                        </td>
                        <td valign="top" class="value">${p.helpText}</td>
                    </tr>
                </g:else>
            </g:else>
        </g:each>
    </tbody>
</table>

