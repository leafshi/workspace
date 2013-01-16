<table>
    <tbody>

        <tr class="prop">
            <td class="name">
                <label for="password"><g:message code="user.password.label" default="Password" />:</label>
            </td>
            <td class="value ${hasErrors(bean: userInstance, field: 'password', 'errors')}">
                <input type="password" name="password"/>
            </td>
            <td class="name">
                <label for="passwordConfirm"><g:message code="user.passwordConfirm.label" default="Password(Confirm)" />:</label>
            </td>
            <td class="value ${hasErrors(bean: userInstance, field: 'password', 'errors')}">
                <input type="password" name="passwordConfirm"/>
            </td>
        </tr>

    </tbody>
</table>

