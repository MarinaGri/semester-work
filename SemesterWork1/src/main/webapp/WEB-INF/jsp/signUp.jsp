<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<t:signXx title="Sing up page" >
    <form method="POST" action="">
        <t:input name="firstName" value="${firstName}" innerText="First name" type="text" nameTip="${nameTip}">
        </t:input>
        <t:input name="lastName" value="${lastName}" innerText="Last name" type="text" nameTip="${nameTip}">
        </t:input>
        <t:input name="email" value="${email}" innerText="Email" type="email" nameTip="${emailTip}">
        </t:input>
        <t:input name="password" value="${password}" innerText="Password" type="password"
                 nameTip="${passwordTip}" length="20">
        </t:input>
        <div class="mb-3 form-check">
            <input required type="checkbox" class="form-check-input" id="exampleCheck1">
            <label class="form-check-label" for="exampleCheck1">I agree to the processing of data</label>
        </div>
        <button type="submit" class="button">Зарегистрироваться</button>
    </form>
</t:signXx>
