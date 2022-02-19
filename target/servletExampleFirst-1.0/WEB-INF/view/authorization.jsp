<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="app_title" value="Авторизация"/>
<%@include file="/WEB-INF/view/header.jsp"%>

<main class="main">
    <div class="reg_window">
        <div class="reg_inner">
            <form method="post" action="/photo/authorization">
                <div class="reg_item reg_btn">
                    <input type="submit" name="registr" value="Зарегистрироваться">
                </div>
            </form>
            <form method="post" action="/photo/authorization">
                <div class="reg_item">
                    <input type="email" required name="email" placeholder="почта">
                </div>
                <div class="reg_item">
                    <input type="password" required name="password" placeholder="пароль">
                </div>
                <div class="reg_item reg_btn">
                    <input type="submit" name="enter" value="готово">
                </div>
            </form>
        </div>
    </div>
</main>

<%@include file="/WEB-INF/view/footer.jsp"%>